package site.workforus.forus.commute.service;

import java.util.Calendar;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.commute.model.CommuteDTO;
import site.workforus.forus.commute.model.SalaryDTO;
import site.workforus.forus.employee.model.EmpDTO;
import site.workforus.forus.mapper.SalaryMapper;

@Service
public class SalaryService {
	
	private static final Logger logger = LoggerFactory.getLogger(CommuteService.class);

	@Autowired
	private SqlSession session;
	
	public int selectEmpSalary(String empId) {
		SalaryMapper mapper = session.getMapper(SalaryMapper.class);
		
		int empSalary = mapper.selectEmpSalary(empId);
		
		return empSalary;
	}
	
	
	// salary를 계산하기 위해 근무일자를 반환 
	public int selectCnt(String empId, String yearMonth) {
		SalaryMapper mapper = session.getMapper(SalaryMapper.class);
		
		int cntCommute = mapper.selectCnt(yearMonth, empId);
		
		// 근무일이 20일 이상이면 그냥 주자.. 시간 남으면 법정공휴일 + 주말제외하는거 만들기  (휴가도 제외필요함)
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
		} else {
			mapper.updateThisMonth(empId, yearMonth, todaySalary, calTax, calIncome);		// 이번달 기록을 업데이트
		}
		
	}


}
