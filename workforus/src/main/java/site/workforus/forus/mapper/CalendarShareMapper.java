package site.workforus.forus.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.workforus.forus.calendar.model.CalendarShareDTO;

public interface CalendarShareMapper {

	public boolean insertData(CalendarShareDTO calendarShareDTO);

	public List<CalendarShareDTO> selectAll();

	public List<CalendarShareDTO> selectById(int calShrId);

	public List<CalendarShareDTO> selectByCalId(int empId);

	public List<CalendarShareDTO> selectByEmpIdAndCalId(@Param("empId") String empId, @Param("calId") int calId);

	public List<CalendarShareDTO> selectJoinCalByEmpId(String empId);

	public boolean deleteById(int calShrId);

}
