package site.workforus.forus.employee.controller;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import site.workforus.forus.employee.model.EmpDTO;
import site.workforus.forus.employee.service.EmpService;
import site.workforus.forus.employee.service.MailSendService;


@Controller
public class EmpController {
	private static final Logger logger = LoggerFactory.getLogger(EmpController.class);
	
	@Autowired
	private EmpService service;
	
	@Autowired
	private MailSendService mailService;
	
	@GetMapping(value="/login")
	public String login(Model model) {
		return "login/login";
	}
	
	@PostMapping(value="/login")
	public String login(Model model, HttpSession session) {
		return "login/login";
	}
	
	@GetMapping(value="/signup") 
	public String signup(Model model) {
		return "login/signup";
	}
	
	@PostMapping(value="/signup")
	public String signup(Model model
		  , EmpDTO empDto
		  , @RequestParam String empId, @RequestParam String empNm, @RequestParam String empPw
		  , @RequestParam String empCheckPw, @RequestParam String empEmail, @RequestParam String empAssistEmail) {
		empDto.setEmpId(empId);
		empDto.setEmpNm(empNm);
		empDto.setEmpPw(empPw);
		empDto.setEmpCheckPw(empCheckPw);
		empDto.setEmpEmail(empEmail);
		empDto.setEmpAssistEmail(empAssistEmail);
		
		boolean result = service.signup(empDto);
		
		if(result) {
			return "redirect:/login";
		}
		return "login/signup";
	}
	
	@GetMapping(value="/login/fail") 
	public String loginFail(Model model) {
		return "login/login_fail";
	}
	
	@GetMapping(value="/mailCheck")
	@ResponseBody
	public String mailCheck(String email) {
		System.out.println("이메일 인증 요청 들어옴!");
		System.out.println("이메일 인증 이메일: " + email);
		return mailService.joinEmail(email);
	}
}
