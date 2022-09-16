package site.workforus.forus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.workforus.forus.commute.model.SalaryDTO;

public interface SalaryMapper {
	
	// 근무일자 출력하기
	public int selectCnt(@Param("yearMonth")String yearMonth,@Param("empId") String empId);
	
	// 계약된 월급 가져옴
	public int selectEmpSalary(String empId);
	
	// 이전 월급기록 가져옴
	public List<SalaryDTO> selectAll(String empId);
	
	// 이번 달 저장 기록 있는지 가져옴
	public SalaryDTO selectByToday(@Param("empId")String empId, @Param("yearMonth")String yearMonth);
	
	public boolean insertThisMonth(@Param("empId")String empId, @Param("yearMonth")String yearMonth
			,@Param("calSalaryNum")int calSalaryNum, @Param("commonTaxNum")int commonTaxNum, @Param("incomeTaxNum")int incomeTaxNum);

	public boolean updateThisMonth(@Param("empId")String empId, @Param("yearMonth")String yearMonth
			,@Param("todaySalary")int todaySalary, @Param("calTax")int calTax, @Param("calIncome")int calIncome);
	
}
