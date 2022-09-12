package site.workforus.forus.mapper;

import java.util.Date;

import org.apache.ibatis.annotations.Param;

import site.workforus.forus.commute.model.CommuteDTO;

// 매개변수가 여러개면 @Param을 붙여서 값의 이름 지정해줘야함 
public interface commuteMapper {

	
	public CommuteDTO selectByEmpId(@Param("empId")String empId, @Param("today") String today);
	public CommuteDTO selectByCmtNo(int commuteNo);
	
	// 출근시간 
	public int insertIntime(@Param("empId")String	 empId, @Param("today")String today, @Param("nowTime")Date nowTime);
	// 퇴근시간
	public int updateOuttime(@Param("empId")String empId, @Param("today")String today
							, @Param("nowTime")Date nowTime);
	
	// 퇴근시간 출력문
	public String selectGetoff(@Param("empId")String empId, @Param("today")String today);
	
	// 근무시간 계산 업데이트
	public int updateWorktime(@Param("empId")String empId, @Param("today")String today
							, @Param("realTime")Date realTime);
	
	// 추가근무시간 업데이트
	public int updateAddedtime(@Param("empId")String empId, @Param("today")String today
			, @Param("addTime")Date addTime);
	
	public int updateWeekAdd(@Param("empId")String empId, @Param("today")String today
			, @Param("weekAdd")Date weekAdd);
	
	public int updateWeekWork(@Param("empId")String empId, @Param("today")String today
			, @Param("weekWork")Date weekWork);
	
}
