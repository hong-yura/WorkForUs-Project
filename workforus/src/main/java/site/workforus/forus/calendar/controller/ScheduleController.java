package site.workforus.forus.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import site.workforus.forus.calendar.model.ScheduleDTO;
import site.workforus.forus.calendar.service.ScheduleService;

@RestController
@RequestMapping(value = "/schedule")
public class ScheduleController {

	@Autowired
	ScheduleService scheduleService;

	@GetMapping(value = "")
	public ResponseEntity<Object> getSchedules(@RequestParam(value = "empId", required = false) String empId,
			@RequestParam(value = "calId", required = false) Integer calId) {

		ResponseEntity<Object> datas = null;

		if (calId != null)
			datas = scheduleService.selectByCalId(calId);
		else if (empId != null)
			datas = scheduleService.selectByEmpId(empId);
		else
			datas = scheduleService.selectAll();

		return datas;
	}

	@GetMapping(value = "/{scheId}")
	public ResponseEntity<Object> getScheduleByScheId(@PathVariable("scheId") int scheId) {

		ResponseEntity<Object> datas = scheduleService.selectByScheduleId(scheId);

		return datas;
	}

	@PostMapping(value = "")
	public ResponseEntity<Object> addSchedule(@RequestBody ScheduleDTO scheduleDTO) {

		ResponseEntity<Object> datas = scheduleService.addSchedule(scheduleDTO);

		return datas;
	}

	@PutMapping(value = "")
	public ResponseEntity<Object> modifySchedule(@RequestBody ScheduleDTO scheduleDTO) {

		ResponseEntity<Object> datas = scheduleService.updateSchedule(scheduleDTO);

		return datas;
	}

	@DeleteMapping(value = "/{scheId}")
	public ResponseEntity<Object> deleteSchedule(@PathVariable("scheId") int scheId) {

		ResponseEntity<Object> datas = scheduleService.deleteSchedule(scheId);

		return datas;
	}

	@DeleteMapping(value = "")
	public ResponseEntity<Object> deleteScheduleByCalId(
			@RequestParam(value = "calId", required = false) Integer calId) {

		ResponseEntity<Object> datas = scheduleService.deleteScheduleByCalId(calId);

		return datas;
	}
}
