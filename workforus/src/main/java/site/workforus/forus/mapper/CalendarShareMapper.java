package site.workforus.forus.mapper;

import java.util.List;

import site.workforus.forus.calendar.model.CalendarShareDTO;

public interface CalendarShareMapper {

	public boolean insertData(CalendarShareDTO calendarShareDTO);

	public List<CalendarShareDTO> selectAll();

	public List<CalendarShareDTO> selectByCalId(int calId);

	public List<CalendarShareDTO> selectByEmpId(String empId);

	public List<CalendarShareDTO> selectJoinCalByEmpId(String empId);

	public boolean deleteById(int calShrId);

}
