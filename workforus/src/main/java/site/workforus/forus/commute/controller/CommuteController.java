package site.workforus.forus.commute.controller;

import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import oracle.jdbc.proxy.annotation.Post;
import site.workforus.forus.commute.model.CommuteDTO;
import site.workforus.forus.commute.service.CommuteService;


@Controller
@RequestMapping(value="/work")
public class CommuteController{
	
	private static final Logger logger = LoggerFactory.getLogger(CommuteController.class);

	 @Autowired
	 private CommuteService service;
	
	// 근태 페이지 조회
	@RequestMapping(value="", method=RequestMethod.GET)
	public String getData(Model model, HttpSession session, Principal principal) throws Exception { 	

		String empId = principal.getName();
		
		/*
		 * empId가 일치하고 commuteDt가 있는지 체크
		 * 1. commuteDt가 일치하는게 없다면 -> 출근하면됨
		 * 2. commuteDt가 일치하는게 있다면 
		 * 	  a. commuteTime이 나타나야함 
		 * 	  b. 출근버튼이 다시 눌리면 안됨
		 */
		
		// 오늘날짜랑, 사원id -> 오늘날짜에 출근기록이 있음
		CommuteDTO data = service.selectData(empId);
		        
        service.updateGetoff(empId);
        
        // 이번달 출근기록 리스트
		Calendar cal = Calendar.getInstance();
		int year1 = cal.get(Calendar.YEAR);
		int month1 = cal.get(Calendar.MONTH);
        List<CommuteDTO> listData = service.selectList(empId, year1, month1);
		model.addAttribute("listData", listData);
		
		// 금일 출근기록이 있음
		if(data != null) {
			service.getData(data);
		}
		
		String remainTime = "00:00:00";
		long progress = 0;
		if(data == null || (data.getCommuteTime() != null && data.getGetoffTime() == null)) {
			if(service.beforeSelect(empId) != null) {
				CommuteDTO temp = service.beforeSelect(empId); // 이번주에 마지막 출근기록 DTO
				if(temp.getGetoffTime()!= null) {
					
					String tempTime = (service.calculate(temp.getWeekWorktime()));
					remainTime = service.remainTime(temp.getWeekWorktime());
					progress = service.progress(temp.getWeekWorktime());	
					temp.setWeekAddtime(temp.getWeekAddtime().substring(11));
					
					session.setAttribute("weekWorktime", tempTime);
					session.setAttribute("weekAddtime", temp.getWeekAddtime());
				}
			
			}
		} else {
			String tempTime = (service.calculate(data.getWeekWorktime()));
			remainTime = service.remainTime(data.getWeekWorktime());
			progress = service.progress(data.getWeekWorktime());	
			data.setWeekAddtime(data.getWeekAddtime().substring(11));
			data.setWeekWorktime(data.getWeekWorktime().substring(11));
			
			session.setAttribute("weekWorktime", tempTime);
			session.setAttribute("weekAddtime", data.getWeekAddtime());
			
		}
		
		session.setAttribute("data", data);
		session.setAttribute("remainTime", remainTime);
		session.setAttribute("progress", progress);
	
		return "commute/commute";
	}
	
	
	
	// 출근 기록
	@PostMapping(value="/in", produces="application/json; charset=UTF-8")
	@ResponseBody
	public String commuteIn(Model model, HttpSession session, @RequestParam String intime, Principal principal) throws Exception {
		JSONObject json = new JSONObject();
		
		String empId = principal.getName();
		
		// 날짜와 아이디 검색해서 출근기록 있으면 실행되면 안돼...
		CommuteDTO data = service.selectData(empId);
		
		if(data == null) {
			service.insertIntime(empId);			
		}
		return json.toJSONString();
	}
	
	// 퇴근 기록
	@RequestMapping(value="/out")
	@ResponseBody
	public String commuteOut(Model model, HttpSession session, Principal principal) throws Exception {
		JSONObject json = new JSONObject();
		String empId = principal.getName();

		Date nowTime = service.nowTime();
		Date defaultTime = new Date(nowTime.getYear(), nowTime.getMonth(), nowTime.getDate(), 9, 0, 0);
		CommuteDTO data = service.selectData(empId);

		if(data != null) {	// 출근기록이 있음
			if(nowTime.after(defaultTime)) { // 9시 이전 퇴근 불가능
				service.updateOuttime(empId); // 퇴근시간 업데이트
				service.updateWeek(empId, data);
			}			
		}
		
		model.addAttribute("data", data);
		
		return json.toJSONString();
	}
	
	// 근태기록 조회하기
	@GetMapping(value="/record")
	public String commuteRec(Model model, Principal principal, @RequestParam int year, @RequestParam int month) {
		String empId = principal.getName();
		
		// 해당월 기록
		List<CommuteDTO> listData = service.selectList(empId, year, month);
		
		model.addAttribute("listData", listData);
		model.addAttribute("year", year);
		model.addAttribute("month", month + 1);
		return "/commute/commuteCalendar";
	}

} 	
