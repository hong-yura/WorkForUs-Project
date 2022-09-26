package site.workforus.forus.calendar.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import site.workforus.forus.calendar.model.CalendarDTO;
import site.workforus.forus.calendar.model.CalendarShareDTO;
import site.workforus.forus.calendar.service.CalendarService;
import site.workforus.forus.calendar.service.CalendarShareService;
import site.workforus.forus.employee.model.LoginVO;

@Slf4j
@Controller
@RequestMapping(value = "/calendar")
public class CalendarController {

	@Autowired
	CalendarService calendarService;

	@Autowired
	CalendarShareService calendarShareService;

	@GetMapping(value = "")
	public String getPage(Model model, Authentication auth) {

		LoginVO loginVO = (LoginVO) auth.getPrincipal();

		log.info("loginData={}", loginVO.getUsername());

		String empId = loginVO.getUsername();

		model.addAttribute("empId", empId);

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
	@GetMapping(value = "/recent")
	public ResponseEntity<Object> getRecentCalendar(@RequestParam(value = "empId", required = true) String empId) {

		ResponseEntity<Object> datas = calendarService.selectRecendDate(empId);

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
	public ResponseEntity<Object> getCalendarShare(@RequestParam(value = "calId", required = false) Integer calId,
			@RequestParam(value = "empId", required = false) String empId) {
		ResponseEntity<Object> datas;
		if (calId != null && empId != null)
			datas = calendarShareService.selectByEmpIdAndCalId(empId, calId);
		else if (calId != null)
			datas = calendarShareService.selectByCalId(calId);
		else if (empId != null)
			datas = calendarShareService.selectJoinCalByEmpId(empId);
		else
			datas = calendarShareService.selectAll();

		return datas;
	}

	@ResponseBody
	@GetMapping(value = "/share/{calShrId}")
	public ResponseEntity<Object> getSharingCalendarsByEmpId(@PathVariable("calShrId") int calShrId) {

		ResponseEntity<Object> datas = calendarShareService.selectById(calShrId);

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

	@ResponseBody
	@GetMapping(value = "/share/emp-list")
	public ResponseEntity<Object> getEmpList() {
		ResponseEntity<Object> datas = calendarShareService.getEmpList();

		return datas;
	}
}
