package site.workforus.forus.calendar.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/calendar")
public class CalendarController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String getPage() {
		return "calendar/calendar";
	}

}
