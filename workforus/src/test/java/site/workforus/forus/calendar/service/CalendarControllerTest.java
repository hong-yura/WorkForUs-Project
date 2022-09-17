package site.workforus.forus.calendar.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import lombok.extern.slf4j.Slf4j;
import site.workforus.forus.calendar.controller.CalendarController;

@Slf4j
class CalendarControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private CalendarService calendarService;

	@BeforeEach
	public void setUp() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(new CalendarController()).build();

	}

	@Test
	@DisplayName("화면 조회 테스트")
	void testGetPage() throws Exception {
		this.mockMvc.perform(get("/calendar")).andExpect(status().isOk()).andExpect(view().name("calendar/calendar"));
	}
//
//	@Test
//	void testGetList() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetAllByCalId() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testAddCalendar() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testModCalendar() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeleteCalendar() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetShareList() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetShareByEmpId() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testAddCalendarShare() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testDeleteCalendarShare() {
//		fail("Not yet implemented");
//	}

}
