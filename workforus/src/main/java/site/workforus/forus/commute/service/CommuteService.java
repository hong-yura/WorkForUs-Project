package site.workforus.forus.commute.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.commute.controller.CommuteController;
import site.workforus.forus.commute.model.CommuteDTO;
import site.workforus.forus.mapper.commuteMapper;


@Service
public class CommuteService{
	
	private static final Logger logger = LoggerFactory.getLogger(CommuteService.class);

	@Autowired
	private SqlSession session;
	
	// 값 출력
	public CommuteDTO selectData(String empId){
		commuteMapper mapper = session.getMapper(commuteMapper.class);
		String today = today();
		logger.info("today:{}", today);
		CommuteDTO data = mapper.selectByEmpId(empId, today);
		return data;
	}
	
	public CommuteDTO selectData(int commuteNo){
		commuteMapper mapper = session.getMapper(commuteMapper.class);
		String today = today();
		
		CommuteDTO data = mapper.selectByCmtNo(commuteNo);
		return data;
	}
	
	// 주간 근무시간 계산
	public CommuteDTO selectdata(String empId, String weekday) {
		commuteMapper mapper = session.getMapper(commuteMapper.class);
		
		CommuteDTO data = mapper.selectByEmpId(empId, weekday);
		return data;
	}
	
	// 출근시간 기록
	public boolean insertIntime(String empId) throws Exception {
		String today = today();
		Date nowTime = nowTime();
		
		commuteMapper mapper = session.getMapper(commuteMapper.class);
		
		int result = mapper.insertIntime(empId, today, nowTime);
		return result == 1 ? true : false;
	}
	
	// 퇴근시간, 근무시간, 추가시간 기록
	public boolean updateOuttime(String empId) throws Exception {
		String today = today();
		Date nowTime = nowTime();
		
		commuteMapper mapper = session.getMapper(commuteMapper.class);
		
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
		logger.info("nowTime:{}", nowTime);
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
		Date defaultTime = defaultSdf.parse("1970-01-01 09:00:00");
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
			// a. 1시 이후 퇴근
			if(time2.after(lunchTime) || time2.equals(lunchTime)) {
				return _afterlunch(time2, inTime);		
			}
			// b. 1시 이전 퇴근
			else {
				return _beforelunch(time2, inTime);		
			}
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
		
		logger.info("workTime은 {}", workTime);	
		
		return workTime;
	}
	
	// 점심시간 이후에 퇴근
	private Date _afterlunch(Date time2, String inTime) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");

		Date time1 = sdf.parse(inTime);		// 출근시간
		long time3 = (time2.getTime() - time1.getTime())/1000;   
		long hour = time3 / (60 * 60) - 1;		// 점심시간 1시간 제외
		long minute = ((time3 % (60 * 60))) / 60;
		long second = ((time3 % (60 * 60))) % 60;	
		
		String time = String.format("%02d:%02d:%02d", hour, minute, second);
		Date workTime = sdf.parse(time);
		
		logger.info("workTime은 {}", workTime);	
		return workTime;
	}
	
	// 주간시간 업데이트 
	public void updateWeek(String empId, Date weekAddtime, Date weekWorktime) {
		commuteMapper mapper = session.getMapper(commuteMapper.class);
		String today = today();

		mapper.updateWeekAdd(empId, today, weekAddtime);
		mapper.updateWeekWork(empId, today, weekWorktime);
	}
	
	// 이번주 근무상태를 출력하기위해 전날 정보를 찾는 메서드
	public CommuteDTO beforeSelect(String empId) {
		commuteMapper mapper = session.getMapper(commuteMapper.class);

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
				System.out.println(beforeDate);
				CommuteDTO data = mapper.selectByEmpId(empId, beforeDate);
				if(data != null) {
					tmp = data;					
				}
				i++;
			}
			
		}
		return tmp;
		

	}
	
}
