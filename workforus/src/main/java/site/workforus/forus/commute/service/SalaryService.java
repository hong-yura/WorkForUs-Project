package site.workforus.forus.commute.service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ibm.icu.util.ChineseCalendar;

import site.workforus.forus.commute.model.CommuteDTO;
import site.workforus.forus.commute.model.SalaryDTO;
import site.workforus.forus.employee.model.EmpDTO;
import site.workforus.forus.mapper.SalaryMapper;

@Service
public class SalaryService {
	
	private static final Logger logger = LoggerFactory.getLogger(CommuteService.class);

	@Autowired
	private SqlSession session;
	
	// 계약된 월급을 가져옴
	public int selectEmpSalary(String empId) {
		SalaryMapper mapper = session.getMapper(SalaryMapper.class);
		
		int empSalary = mapper.selectEmpSalary(empId);
		
		return empSalary;
	}
	
	
	// salary를 계산하기 위해 근무일자를 반환 
	public int selectCnt(String empId, String yearMonth) {
		SalaryMapper mapper = session.getMapper(SalaryMapper.class);
		
		int cntCommute = mapper.selectCnt(yearMonth, empId);
		
		return cntCommute;
		
	}

	// 오늘날짜에 말일을 나눈 값 구하기
	private double _calDate() {
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		
		int dateNum = cal.get(Calendar.DAY_OF_MONTH);
		int dateMax = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
		
		double calDate = (dateNum * 1.0) / (dateMax * 1.0);
		calDate = (Math.round(calDate * 100)) / 100.0;		// 소수점 두번째 자리까지만 가져옴
		return calDate;
	}

	// 오늘까지의 월급 구하기
	public int getTodaySalary(int empSalary) {
		double numSalary = empSalary * 1.0;
		double calDate = _calDate();
		int calSalary = (int) (numSalary * calDate);
		
		return calSalary;
	}

	// 소득세 계산하는 식 (근로간이세액표를 참고하여 계산하기 쉽게 임의로 값을 변경하였다)
	public int calIncome(int todaySalary) {
		double commonTax = 0;
		if(todaySalary >= 250 && todaySalary < 300) {
			commonTax = todaySalary * 0.015;
		} else if(todaySalary >=300 && todaySalary < 350) {
			commonTax = todaySalary * 0.028;
		} else if(todaySalary >= 350 && todaySalary < 400) {
			commonTax = todaySalary * 0.041;
		} else if(todaySalary >= 400) {
			commonTax = todaySalary * 0.05;
		} else {
			commonTax = todaySalary * 0.008;
		}
					
		int calIncome = (int) (commonTax * 1);

		return calIncome;
	}

	// 이전 월급 기록을 가져옴 
	public List<SalaryDTO> selectAll(String empId) {
		SalaryMapper mapper = session.getMapper(SalaryMapper.class);
		
		List<SalaryDTO> data = mapper.selectAll(empId);
		
		return data;
	}
	
	// 이번달기록 업데이트 및 저번달 기록 최종 업데이트
	public void updateAll(String empId, int todaySalary, int calTax, int calIncome) {
		SalaryMapper mapper = session.getMapper(SalaryMapper.class);
		Calendar cal = Calendar.getInstance();
		cal.getTime();
		int month = cal.get(Calendar.MONTH) + 1;
		String strMonth = "";
		
		if(month < 10) {
			strMonth = "0" + month;
		} else {
			strMonth = "" + month;
		}
		String yearMonth = (cal.get(Calendar.YEAR) + "") + strMonth;
		SalaryDTO existCheck = mapper.selectByToday(empId, yearMonth);
		
		
		// 이번달 저장정보가 존재 유무
		if(existCheck == null) {
			mapper.insertThisMonth(empId, yearMonth, todaySalary, calTax, calIncome);		// 이번달 기록을 추가
			_calPrevMonth(empId);
		} else {
			mapper.updateThisMonth(empId, yearMonth, todaySalary, calTax, calIncome);		// 이번달 기록을 업데이트
		}
		
		
		
	}

	// 저번달 기록
	private void _calPrevMonth(String empId) {
		SalaryMapper mapper = session.getMapper(SalaryMapper.class);
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) - 1, 1); 	// 저번달
		cal.getTime();
		
		int month = cal.get(Calendar.MONTH) + 1;
		String strMonth = "";
		
		// 달형식 mm으로 지정
		if(month < 10) {
			strMonth = "0" + month;
		} else {
			strMonth = "" + month;
		}
		String yearMonth = (cal.get(Calendar.YEAR) + "") + strMonth;
		System.out.println(yearMonth);
		// 포함된 공휴일 갯수
		String strI = "";
		Set<String> setHoliday = new HashSet<String>();
		int cntHoliday = 0;
		for(int i = 1; i <= 31; i++) {
			if(i < 10) {
				strI = "0" + i; 
			} else {
				strI = i + "";
			}
			cal.getTime();
			boolean isContain = holidayArray(Integer.toString(cal.get(Calendar.YEAR))).contains(yearMonth + strI);
			if(isContain) {
				cntHoliday++;
				setHoliday.add(yearMonth + strI);
			}
		}
		System.out.println(cntHoliday);
		
		// 주말 갯수
		String prevMonth = "";
		String strJ = "";
		cal.set(Calendar.YEAR, Integer.parseInt(yearMonth.substring(0,4)));
		cal.set(Calendar.MONTH, Integer.parseInt(yearMonth.substring(4)) - 1);
		cal.getTime();
		
		for(int j = 1; j <= cal.getActualMaximum(Calendar.DAY_OF_MONTH); j++){
			cal.set(Calendar.DATE, j);
			if(j < 10) {
				strJ = "0" + j;
			} else {
				strJ = "" + j; 
			}
			if(cal.get(Calendar.DAY_OF_WEEK) == 1 || cal.get(Calendar.DAY_OF_WEEK)	 == 7) {
				if(cal.get(Calendar.MONTH) + 1 < 10) {
					prevMonth = "0" + (cal.get(Calendar.MONTH) + 1); 
				} else {
					prevMonth = "" + (cal.get(Calendar.MONTH) + 1);
				}
				String weekendDate = cal.get(Calendar.YEAR) + prevMonth + strJ;
				setHoliday.add(weekendDate);
			}
		}
		System.out.println(setHoliday.size());
		
		int prevMonthSalary = selectEmpSalary(empId) * selectCnt(empId, yearMonth)/(cal.getActualMaximum(Calendar.DAY_OF_MONTH) - setHoliday.size());
		int calTax =  (int)(prevMonthSalary * 0.088993);
		int calIncome = calIncome(prevMonthSalary);
		
		
		mapper.updatePrevMonth(empId, yearMonth, prevMonthSalary, calTax, calIncome);
	}
	
	
	/*
	 * 해당년도에 공휴일을 반환함
	 */
	
    static Set<String> holidaysSet = new HashSet<>();
    public static final int LD_SUNDAY = 7;
    public static final int LD_SATURDAY = 6;
    public static final int LD_MONDAY = 1;
    
    //음력날짜를 양력날짜로 변환
    public String Lunar2Solar(String yyyymmdd) {
        ChineseCalendar cc = new ChineseCalendar();

        if (yyyymmdd == null)
            return null;

        String date = yyyymmdd.trim();
        if (date.length() != 8) {
            if (date.length() == 4)
                date = date + "0101";
            else if (date.length() == 6)
                date = date + "01";
            else if (date.length() > 8)
                date = date.substring(0, 8);
            else
                return null;
        }

        cc.set(ChineseCalendar.EXTENDED_YEAR, Integer.parseInt(date.substring(0, 4)) + 2637);   // 년, year + 2637
        cc.set(ChineseCalendar.MONTH, Integer.parseInt(date.substring(4, 6)) - 1);              // 월, month -1
        cc.set(ChineseCalendar.DAY_OF_MONTH, Integer.parseInt(date.substring(6)));              // 일

        LocalDate solar = Instant.ofEpochMilli(cc.getTimeInMillis()).atZone(ZoneId.of("UTC")).toLocalDate();

        int y = solar.getYear();
        int m = solar.getMonth().getValue();
        int d = solar.getDayOfMonth();

        StringBuilder ret = new StringBuilder();
        ret.append(String.format("%04d", y));
        ret.append(String.format("%02d", m));
        ret.append(String.format("%02d", d));

        return ret.toString();
    }

    public Set<String> holidayArray(String yyyy){
        holidaysSet.clear();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        // 양력 휴일
        holidaysSet.add(yyyy+"0101");   // 신정
        holidaysSet.add(yyyy+"0301");   // 삼일절
        holidaysSet.add(yyyy+"0505");   // 어린이날
        holidaysSet.add(yyyy+"0606");   // 현충일
        holidaysSet.add(yyyy+"0815");   // 광복절
        holidaysSet.add(yyyy+"1003");   // 개천절
        holidaysSet.add(yyyy+"1009");   // 한글날
        holidaysSet.add(yyyy+"1225");   // 성탄절

        // 음력 휴일

        String prev_seol = LocalDate.parse(Lunar2Solar(yyyy+"0101"), formatter).minusDays(1).toString().replace("-","");
        holidaysSet.add(yyyy+prev_seol.substring(4));        // ""
        holidaysSet.add(yyyy+_SolarDays(yyyy, "0101"));  // 설날
        holidaysSet.add(yyyy+_SolarDays(yyyy, "0102"));  // ""
        holidaysSet.add(yyyy+_SolarDays(yyyy, "0408"));  // 석탄일
        holidaysSet.add(yyyy+_SolarDays(yyyy, "0814"));  // ""
        holidaysSet.add(yyyy+_SolarDays(yyyy, "0815"));  // 추석
        holidaysSet.add(yyyy+_SolarDays(yyyy, "0816"));  // ""

        return holidaysSet;
    }

    private String _SolarDays(String yyyy, String date){
        return Lunar2Solar(yyyy+date).substring(4);
    }


}


