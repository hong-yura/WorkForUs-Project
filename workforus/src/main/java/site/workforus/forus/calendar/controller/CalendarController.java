package site.workforus.forus.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import site.workforus.forus.calendar.model.CalendarDTO;
import site.workforus.forus.calendar.model.CalendarShareDTO;
import site.workforus.forus.calendar.service.CalendarService;
import site.workforus.forus.calendar.service.CalendarShareService;

@Controller
@RequestMapping(value = "/calendar")
public class CalendarController {

	@Autowired
	CalendarService calendarService;

	@Autowired
	CalendarShareService calendarShareService;

	@GetMapping(value = "")
	public String getPage() {
		return "calendar/calendar";
	}

	@ResponseBody
	@GetMapping(value = "/list")
	public ResponseEntity<Object> getCalendar(@RequestParam(value = "empId", required = false) String empId) {

		ResponseEntity<Object> datas = empId == null ? calendarService.selectAll()
				: calendarService.selectByEmpId(empId);

		return datas;
	}

	@ResponseBody
	@GetMapping(value = "/list/{calId}")
	public ResponseEntity<Object> getCalendarByCalId(@PathVariable("calId") int calId) {

		ResponseEntity<Object> datas = calendarService.selectByCalId(calId);

		return datas;
	}

	@ResponseBody
	@PostMapping(value = "/list")
	public ResponseEntity<Object> addCalendar(@RequestBody CalendarDTO calendarDTO) {

		ResponseEntity<Object> datas = calendarService.addCalendar(calendarDTO);

		return datas;

	}

	@ResponseBody
	@PutMapping(value = "/list")
	public ResponseEntity<Object> modifyCalendar(@RequestBody CalendarDTO calendarDTO) {

		ResponseEntity<Object> datas = calendarService.updateCalendar(calendarDTO);

		return datas;
	}

	@ResponseBody
	@DeleteMapping(value = "/list/{calId}")
	public ResponseEntity<Object> deleteCalendar(@PathVariable("calId") int calId) {

		ResponseEntity<Object> datas = calendarService.deleteCalendar(calId);

		return datas;
	}

	@ResponseBody
	@GetMapping(value = "/share")
	public ResponseEntity<Object> getCalendarShare(@RequestParam(value = "calId", required = false) Integer calId) {

		ResponseEntity<Object> datas = calId == null ? calendarShareService.selectAll()
				: calendarShareService.selectByCalId(calId);

		return datas;
	}

	@ResponseBody
	@GetMapping(value = "/share/{empId}")
	public ResponseEntity<Object> getSharingCalendarsByEmpId(@PathVariable("empId") String empId) {

		ResponseEntity<Object> datas = calendarShareService.selectJoinCalByEmpId(empId);

		return datas;
	}

	@ResponseBody
	@PostMapping(value = "/share")
	public ResponseEntity<Object> addCalendarShare(@RequestBody CalendarShareDTO calendarShareDTO) {

		ResponseEntity<Object> datas = calendarShareService.addCalendarShare(calendarShareDTO);

		return datas;

	}

	@ResponseBody
	@DeleteMapping(value = "/share/{calShrId}")
	public ResponseEntity<Object> deleteCalendarShare(@PathVariable("calShrId") int calShrId) {

		var datas = calendarShareService.deleteCalendarShare(calShrId);

		return datas;
	}
}
