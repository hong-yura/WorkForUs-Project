package site.workforus.forus.commute.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.commute.controller.CommuteController;
import site.workforus.forus.commute.model.CommuteDTO;
import site.workforus.forus.mapper.CommuteMapper;


@Service
public class CommuteService{
	
	private static final Logger logger = LoggerFactory.getLogger(CommuteService.class);

	@Autowired
	private SqlSession session;
	
	// 값 출력
	public CommuteDTO selectData(String empId){
		CommuteMapper mapper = session.getMapper(CommuteMapper.class);
		String today = today();

		CommuteDTO data = mapper.selectByEmpId(empId, today);
		return data;
	}
	
	public CommuteDTO selectData(int commuteNo){
		CommuteMapper mapper = session.getMapper(CommuteMapper.class);
		String today = today();
		
		CommuteDTO data = mapper.selectByCmtNo(commuteNo);
		return data;
	}
	
	// 주간 근무시간 계산
	public CommuteDTO selectdata(String empId, String weekday) {
		CommuteMapper mapper = session.getMapper(CommuteMapper.class);
		
		CommuteDTO data = mapper.selectByEmpId(empId, weekday);
		return data;
	}
	
	// 출근시간 기록
	public boolean insertIntime(String empId) throws Exception {
		String today = today();
		Date nowTime = nowTime();
		
		CommuteMapper mapper = session.getMapper(CommuteMapper.class);
		
		int result = mapper.insertIntime(empId, today, nowTime);
		return result == 1 ? true : false;
	}
	
	// 퇴근시간, 근무시간, 추가시간 기록
	public boolean updateOuttime(String empId) throws Exception {
		String today = today();
		Date nowTime = nowTime();
		
		CommuteMapper mapper = session.getMapper(CommuteMapper.class);
		
		// 퇴근시간 update
		int result = mapper.updateOuttime(empId, today, nowTime);
		
		// 퇴근시간 가져옴
		String getoffTime = mapper.selectGetoff(empId, today);
		
		// 출근시간 가져옴
		CommuteDTO data = mapper.selectByEmpId(empId, today);
		String inTime = data.getCommuteTime();
		
		// 근무시간 계산값을 가져옴
		Date workTime = workTime(inTime, getoffTime);
		
		// 추가된시간 계산
		Date addedTime = _updateAddedtime(workTime);
		
		// 근무시간 update문
		int res2 = mapper.updateWorktime(empId, today, workTime);
		
		mapper.updateAddedtime(empId, today, addedTime);
		
		return result == 1 ? true : false;
	}
	
	// 오늘날짜 가져옴
	public String today() {
		Calendar cal = Calendar.getInstance();
		
		// 오늘날짜
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH) + 1;
		int date = cal.get(Calendar.DAY_OF_MONTH);
		String today = String.format("%02d%02d%02d", year, month, date); 
		
		return today;
	}
	
	// 현재시간 가져옴
	public Date nowTime() throws Exception {
		Calendar cal = Calendar.getInstance();

		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		String inTime = String.format("%02d:%02d:%02d", hour, minute, second); 	
		// 현재시간을 date타입으로 형변환
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		Date nowTime = sdf.parse(inTime);		

		return nowTime;
	}
	
	// 8시간 이후에 추가 근무시간
	private Date _updateAddedtime(Date workTime) throws Exception {
		SimpleDateFormat defaultSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		Date defaultTime = defaultSdf.parse("1970-01-01 08:00:00");
		Date addedTime = null;
		if(!workTime.before(defaultTime)) {	 // 총근무시간이 8시간 이상
			if(workTime.after(defaultTime)) {	
				long time3 = (workTime.getTime() - defaultTime.getTime())/1000;   
				long hour = time3 / (60 * 60);		
				long minute = ((time3 % (60 * 60))) / 60;
				long second = ((time3 % (60 * 60))) % 60;	
				
				String time = String.format("%02d:%02d:%02d", hour, minute, second);
				addedTime = sdf.parse(time);
			} 
			return addedTime;					
		}
		return defaultSdf.parse("1970-01-01 00:00:00");
	}
	
	// 시간차이 구하기 (정해진 출근시간(9시) - 퇴근시간 - 1시간(점심시간)
	// 1. 9시 이전에 출근하면 9시부터 빼야함
		// a. 1시 이후에 퇴근하면 점심시간 빼야하고
		// b. 1시 이전이면 점심시간 빼면안됨
	// 2. 9시 이후에 출근하면 출근시간에서 빼야함 
		// a. 1시 이후에 퇴근하면 점심시간 빼야하고
		// b. 1시 이전이면 점심시간 빼면안됨
	public Date workTime(String inTime, String getoffTime) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		SimpleDateFormat defaultSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		// 출근시간
		inTime = inTime.substring(11, 19);
		Date incommute = sdf.parse(inTime);
		
		// 퇴근시간
		getoffTime = getoffTime.substring(11, 19);
		Date time2 = sdf.parse(getoffTime);		// 퇴근시간

		//기본시간
		Date defaultTime = defaultSdf.parse("1970-01-01 09:00:00");	// 9시
		Date lastTime = defaultSdf.parse("1970-01-01 23:00:00");	// 만약 23시 이후에 출근한다면 점심시간을 빼지않도록 해야한다.
		Date lunchTime = sdf.parse("13:00:00");
		
		
		// 9시 이전 출근
		if(incommute.before(defaultTime) || incommute.equals(defaultTime)) {
			// a. 1시 이후 퇴근
			if(time2.after(lunchTime) || time2.equals(lunchTime)) {				
				return _afterlunch(time2, "09:00:00");
			}
			// b. 1시 이전 퇴근
			else {
				return _beforelunch(time2, "09:00:00");
			}
		} 
		// 9시 이후 출근
		else {
//			// 23시 이후에 출근
//			if(incommute.after(lastTime) || incommute.equals(lastTime)) {
//				return _beforelunch(time2, inTime);
//			} else {
				// a. 1시 이후 퇴근
				if(time2.after(lunchTime) || time2.equals(lunchTime)) {
					return _afterlunch(time2, inTime);		
				}
				// b. 1시 이전 퇴근
				else {
					return _beforelunch(time2, inTime);		
				}
//			}
		}
	}
	
	
	// 점심시간 전에 퇴근
	private Date _beforelunch(Date time2, String inTime) throws Exception { // 퇴근시간, 출근시간
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		
		Date time1 = sdf.parse(inTime);		// 출근시간
		long time3 = (time2.getTime() - time1.getTime())/1000;   
		long hour = time3 / (60 * 60);
		long minute = ((time3 % (60 * 60))) / 60;
		long second = ((time3 % (60 * 60))) % 60;	
		
		String time = String.format("%02d:%02d:%02d", hour, minute, second);
		Date workTime = sdf.parse(time);
		
		
		return workTime;
	}
	
	// 점심시간 이후에 퇴근
	private Date _afterlunch(Date time2, String inTime) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		Date time1 = sdf.parse(inTime);		// 출근시간
		long time3 = (time2.getTime() - time1.getTime())/1000;
		// 근무시간이 1시간이 안되면 점심시간 뺄때 음수가 안되도록
		if(time3 <= 3600) {
			time3 += 3600;
		}
		long hour = time3 / (60 * 60) - 1;		// 점심시간 1시간 제외
		long minute = ((time3 % (60 * 60))) / 60;
		long second = ((time3 % (60 * 60))) % 60;	
		
		String time = String.format("%02d:%02d:%02d", hour, minute, second);
		Date workTime = sdf.parse(time);
		
		return workTime;
	}
	
	// 주간시간 업데이트 
	public void updateWeek(String empId, Date weekAddtime, Date weekWorktime) {
		CommuteMapper mapper = session.getMapper(CommuteMapper.class);
		String today = today();

		mapper.updateWeekAdd(empId, today, weekAddtime);
		mapper.updateWeekWork(empId, today, weekWorktime);
	}
	
	// 이번주 근무상태를 출력하기위해 전날 정보를 찾는 메서드
	public CommuteDTO beforeSelect(String empId) {
		CommuteMapper mapper = session.getMapper(CommuteMapper.class);

		Calendar cal = Calendar.getInstance();
		cal.getTime();
		CommuteDTO tmp = null;
		int cntDay = cal.get(Calendar.DAY_OF_WEEK);
		
		if(cal.get(Calendar.DAY_OF_WEEK) != 1) { //일요일일때는 전날 정보를 가져올 필요가 없음!			
			int i = 0;
			while(i < cntDay - 1) {
				cal.set(Calendar.DAY_OF_WEEK, i + 1);				
				cal.getTime();										// api에 따르면 getTime을해야 dayofweek이 반영된다함
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH) + 1;
				int date = cal.get(Calendar.DAY_OF_MONTH);
				String beforeDate = String.format("%02d%02d%02d", year, month, date); 

				CommuteDTO data = mapper.selectByEmpId(empId, beforeDate);
				if(data != null) {
					tmp = data;					
				}
				i++;
			}
		}
		return tmp;
		

	}
	
	
	// 년월에 해당하는 근무리스트
	public List<CommuteDTO> getList(String empId, String yearmonth1) {
		CommuteMapper mapper = session.getMapper(CommuteMapper.class);
		
		List<CommuteDTO> result = mapper.selectList(empId, yearmonth1);
		return result;
	}
	
	// 년월에 해당하는 근무리스트 갯수 
	public int cntList(String empId, String yearmonth1) {
		CommuteMapper mapper = session.getMapper(CommuteMapper.class);

		int result = mapper.selectCntList(empId, yearmonth1);
		return result;
	}
	
	// 출근시간은 있고 퇴근시간이 없다면 23:59:59으로 설정해주기
	public boolean updateGetoff(String empId) throws Exception {
		CommuteMapper mapper = session.getMapper(CommuteMapper.class);
		int number = 0;
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		int cntDay = cal.get(Calendar.DAY_OF_WEEK);
		
		if(cal.get(Calendar.DAY_OF_WEEK) != 1) { //일요일일때는 전날 정보를 가져올 필요가 없음!			
			int i = 0;
			while(i < cntDay - 1) {
				cal.set(Calendar.DAY_OF_WEEK, i + 1);				
				cal.getTime();										
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH) + 1;
				int date = cal.get(Calendar.DAY_OF_MONTH);
				String beforeDate = String.format("%02d%02d%02d", year, month, date); 

				CommuteDTO data = mapper.selectByEmpId(empId, beforeDate);
				if(data != null) {
					if(data.getCommuteTime() != null && data.getGetoffTime() == null) {		// 출근시간만 있고 퇴근시간 없을때
						mapper.updateGetoffTime(empId, beforeDate);							// 퇴근시간 업데이트
						number++;																// 이에따른 추가근무시간이랑 주간근무시간 업데이트해야함  
						Date workTime = workTime(data.getCommuteTime(), "1970-01-01 23:59:59");
						Date addedTime = _updateAddedtime(workTime);
						mapper.updateWorktime(empId, beforeDate, workTime);
						mapper.updateAddedtime(empId, beforeDate, addedTime);
						// 주간 추가시간이랑 주간 근무시간은 어떻게 할것인가........
						CommuteDTO temp = mapper.selectByEmpId(empId, beforeDate);
						updateWeek(empId, temp);
					}					
				}
				i++;
			}
		}
		return number >= 1 ? true : false;
	}

	
	// 주단위 근무시간 계산 (24시간 넘어가는거 나타내기위해)
	public String calculate(String weekWorktime) throws Exception { 
		SimpleDateFormat defaultSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		Date time1 = defaultSdf.parse("1970-01-01 00:00:00"); // 기본시간 
		Date time2 = defaultSdf.parse(weekWorktime);		// 이번주 근무시간
		long time3 = (time2.getTime() - time1.getTime())/1000;   
		long hour = (time3 / (60 * 60));
		long minute = ((time3 % (60 * 60))) / 60;
		long second = ((time3 % (60 * 60))) % 60;	
		String time = String.format("%02d:%02d:%02d", hour, minute, second);
		
		return time;
	}
	
	// 40시, 50시간 퍼센테이지
	public long progress(String weekWorktime) throws Exception {
		SimpleDateFormat defaultSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date defaultTime = defaultSdf.parse("1970-01-02 16:00:00");
		Date weekTime = defaultSdf.parse(weekWorktime);
		System.out.println(weekTime);
		double progress = 0;
		if(weekTime.getTime()/defaultTime.getTime() < 1) {
			double dftTime = defaultTime.getTime() * 1.0 + 32400000;	// 음수가 나와서 한국시간 더해줌 
			double wkTime = weekTime.getTime() * 1.0 + 32400000; 		// 음수가 나와서 한국시간 더해줌 
			System.out.println(dftTime);
			System.out.println(wkTime + "@@@");
			progress = (wkTime / dftTime);
			progress = Double.parseDouble(String.format("%.2f", progress));
		} else {
			progress = 1;
		}
		return (long) (progress * 100);
	}
	
	// 이번주 잔여시간 구하기
	public String remainTime(String weekWorktime) throws Exception {
		SimpleDateFormat defaultSdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		
		Date defaultTime = defaultSdf.parse("1970-01-02 16:00:00");
		Date weekTime = defaultSdf.parse(weekWorktime);
		String remainTime = "00:00:00";
		if(defaultTime.getTime() - weekTime.getTime() > 0) {
			long diffTime = (defaultTime.getTime() - weekTime.getTime())/1000;
			long hour = (diffTime / (60 * 60));
			long minute = ((diffTime % (60 * 60))) / 60;
			long second = ((diffTime % (60 * 60))) % 60;	
			
			remainTime = String.format("%02d:%02d:%02d", hour, minute, second);
		}
		return remainTime;
	}
	
	
	// 주간근무시간, 주간추가시간 
	public void updateWeek(String empId, CommuteDTO data) throws Exception {
		Calendar cal = Calendar.getInstance();
		CommuteMapper mapper = session.getMapper(CommuteMapper.class);

		// 주단위 시간 업데이트 
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		SimpleDateFormat timeformat = new SimpleDateFormat("HH:mm:ss");
		int today = 0;
		if(Calendar.DAY_OF_WEEK != 1) {
			today = cal.get(Calendar.DAY_OF_WEEK);	// 일요일이 아니면 해당하는 숫자로 변경
		}
		int addSum = 0;
		int workSum = 0;
		// 월 ~ 토
		if(today != 0) {
			
			for(int i = 0; i < today; i++) {
				cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY + i);
				String weekday = sdf.format(cal.getTime()); // 일요일~토요일해당 날짜
				
				CommuteDTO calData = selectdata(empId, weekday);
				if(calData != null) {	// 출근기록이 있음
					if(calData.getGetoffTime() != null) {		// 그날에 퇴근시간이 있다면.. 
						
						calData.setWorkTime(calData.getWorkTime().substring(11));
						calData.setAddedTime(calData.getAddedTime().substring(11));
						
						Date addtime = timeformat.parse(calData.getAddedTime());
						Date worktime = timeformat.parse(calData.getWorkTime());
						
						Date weekAddtime = timeformat.parse("00:00:00");
						Date weekWorktime = timeformat.parse("00:00:00");
						
						int addsecond = addtime.getHours() * 3600 + addtime.getMinutes() * 60 + addtime.getSeconds();
						addSum += addsecond;
						long hour1 = addSum / (60 * 60);		
						long minute1 = ((addSum % (60 * 60))) / 60;
						long second1 = ((addSum % (60 * 60))) % 60;	
						
						String tmp1 = String.format("%02d:%02d:%02d", hour1, minute1, second1);
						weekAddtime = timeformat.parse(tmp1);
						
						int worksecond = worktime.getHours() * 3600 + worktime.getMinutes() * 60 + worktime.getSeconds();
						workSum += worksecond;
						long hour2 = workSum / (60 * 60);		
						long minute2 = ((workSum % (60 * 60))) / 60;
						long second2 = ((workSum % (60 * 60))) % 60;	
						
						String tmp2 = String.format("%02d:%02d:%02d", hour2, minute2, second2);
						weekWorktime = timeformat.parse(tmp2);
						mapper.updateWeekAdd(empId, data.getCommuteDt(), weekAddtime);
						mapper.updateWeekWork(empId, data.getCommuteDt(), weekWorktime);
						
						
					}
				}	
				
			}
		} else {
			for(int i = 0; i <= today; i++) {
				cal.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY + i);
				String weekday = sdf.format(cal.getTime()); // 일요일~토요일해당 날짜
				
				CommuteDTO calData = selectdata(empId, weekday);
				if(calData != null) {
					
					if(calData.getGetoffTime() != null) {		// 그날에 퇴근시간이 있다면.. 
						
						calData.setWorkTime(calData.getWorkTime().substring(11));
						calData.setAddedTime(calData.getAddedTime().substring(11));
						
						Date addtime = timeformat.parse(calData.getAddedTime());
						Date worktime = timeformat.parse(calData.getWorkTime());
						
						Date weekAddtime = timeformat.parse("00:00:00");
						Date weekWorktime = timeformat.parse("00:00:00");
						
						int addsecond = addtime.getHours() * 3600 + addtime.getMinutes() * 60 + addtime.getSeconds();
						addSum += addsecond;
						long hour1 = addSum / (60 * 60);		
						long minute1 = ((addSum % (60 * 60))) / 60;
						long second1 = ((addSum % (60 * 60))) % 60;	
						
						String tmp1 = String.format("%02d:%02d:%02d", hour1, minute1, second1);
						weekAddtime = timeformat.parse(tmp1);
						
						int worksecond = worktime.getHours() * 3600 + worktime.getMinutes() * 60 + worktime.getSeconds();
						workSum += worksecond;
						long hour2 = workSum / (60 * 60);		
						long minute2 = ((workSum % (60 * 60))) / 60;
						long second2 = ((workSum % (60 * 60))) % 60;	
						
						String tmp2 = String.format("%02d:%02d:%02d", hour2, minute2, second2);
						weekWorktime = timeformat.parse(tmp2);
						mapper.updateWeekAdd(empId, data.getCommuteDt(), weekAddtime);
						mapper.updateWeekWork(empId, data.getCommuteDt(), weekWorktime);

					}
				}
				
				
			}
		}
		
	}
	
	
	public List<CommuteDTO> selectList(String empId, int year, int month){
		
		month += 1;
		String yearstr = Integer.toString(year);
		String monthstr = null;
		if(month < 10) {
			monthstr = "0" + month;
		} else {
			monthstr = month + "";
		}
		
		String yearmonth1 = yearstr + monthstr;
		System.out.println(yearmonth1);
		
		List<CommuteDTO> listData = getList(empId, yearmonth1);

		return listData;
		
	}
	
	
}
