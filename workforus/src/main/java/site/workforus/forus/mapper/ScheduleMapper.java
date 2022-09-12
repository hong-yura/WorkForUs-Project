package site.workforus.forus.mapper;

import java.util.List;

import site.workforus.forus.calendar.model.ScheduleDTO;

public interface ScheduleMapper {

	public boolean insertData(ScheduleDTO calendarDTO);

	public List<ScheduleDTO> selectAll();

	public List<ScheduleDTO> selectAllByEmpId(String empId);

	public List<ScheduleDTO> selectAllByCalId(int calId);

	public List<ScheduleDTO> selectByScheduleId(int scheduleId);

	public boolean updateData(ScheduleDTO scheduleDTO);

	public boolean deleteById(int scheduleId);

	public boolean deleteAllByCalId(int calId);

}