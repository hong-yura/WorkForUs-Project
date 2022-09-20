package site.workforus.forus.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.workforus.forus.commute.model.CommuteDTO;

// 매개변수가 여러개면 @Param을 붙여서 값의 이름 지정해줘야함 
public interface CommuteMapper {

	
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
	
	// 없는 퇴근기록 업데이트
	public void updateGetoffTime(@Param("empId")String empId, @Param("beforeDate")String beforeDate);
	
	// 근무기록 조회
	public List<CommuteDTO> selectList(@Param("empId") String empId, @Param("yearmonth1")String yearmonth1);
	
	public int selectCntList(@Param("empId") String empId, @Param("yearmonth1")String yearmonth1);
}
