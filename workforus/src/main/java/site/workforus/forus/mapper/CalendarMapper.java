package site.workforus.forus.mapper;

import java.util.List;

import site.workforus.forus.calendar.model.CalendarDTO;

public interface CalendarMapper {

	public boolean insertData(CalendarDTO calendarDTO);

	public List<CalendarDTO> selectAll();

	public List<CalendarDTO> selectByEmpId(String empId);

	public List<CalendarDTO> selectByCalId(int calId);

	public List<CalendarDTO> selectRecentData(String empId);

	public boolean updateData(CalendarDTO calendarDTO);

	public boolean deleteById(int calId);

}