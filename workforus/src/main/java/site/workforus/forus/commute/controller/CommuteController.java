package site.workforus.forus.commute.controller;

import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
	public String getData(Model model) { 	

		// 테스트용 empId값
		String empId = "A2022100";
		
		// 근태내역 정보 조회
		// Employee loginData = (Employee)session.getAttribute("loginData");
		// String empId = loginData.getEmpId(); 
		 
		  List<CommuteDTO> data = service.selectData(empId);
		  model.addAttribute("data", data);
		  logger.info("data: {}", data);
		
		return "commute/commute";
	}
	
	// 연봉조회
	@RequestMapping(value="/salary", method=RequestMethod.GET)
	public String getSalary(Model model) {
		return "commute/salary";
	}
	
	
	
	// 출근 기록
	@RequestMapping(value="/in")
	public String recordIn() {
		
		return "redirect:/commute/main";
	}
	/*
	
	// 퇴근 기록
	@RequestMappings(value="/out")
	public String recordOut() {
		return "redirect:/commute/main";
	}
	*/
} 	
