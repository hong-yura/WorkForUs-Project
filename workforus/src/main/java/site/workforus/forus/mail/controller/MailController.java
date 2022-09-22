package site.workforus.forus.mail.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import site.workforus.forus.commute.controller.CommuteController;
import site.workforus.forus.mail.model.ReceiveMailDTO;
import site.workforus.forus.mail.model.SendMailDTO;
import site.workforus.forus.mail.service.MailService;

@Controller
@RequestMapping(value="/mail")
public class MailController {
	
	private static final Logger logger = LoggerFactory.getLogger(MailController.class);

	@Autowired
	private MailService service;
	
	// 받은메일함
	@RequestMapping(value="", method=RequestMethod.GET)
	public String getData(Model model, Principal principal) {
		//String empId = "A2022100";
		String empId = principal.getName();

		List<ReceiveMailDTO> dataList = service.selectDatas(empId);
		System.out.println(dataList);
		model.addAttribute("dataList", dataList);
		return "mail/mailHome";
	}
	
	// 메일작성 페이지로 이동
	@RequestMapping(value="/mailWrite", method=RequestMethod.GET)
	public String writeMail(Model model) {
		
		return "mail/mailWrite";
	}
	
	// 메일 전송 요청
	@PostMapping(value="mailWrite", produces="application/json; charset=UTF-8")
	public String writeMailPost(Model model, Principal principal ,@ModelAttribute SendMailDTO sendMailDto) {	// 로그인세션 필요
			
		// 테스트값
		// String empId = "A2022100";
		String empId = principal.getName();

		String id = service.insertMailWrite(empId, sendMailDto);
		
		 if(id != null) {
			 return "redirect:/mail";
		 } else {
			 return "mail/mailWrite";
		 }
	}
	
	// 보낸메일함
	@RequestMapping(value="/mailSend", method=RequestMethod.GET)
	public String mailSend(Model model) {
		
		return "mail/mailSend";
	}
	
	// 메일 상세
	@GetMapping(value="detail")
	public String getDetail(Model model, Principal principal, @RequestParam String mailId) {
		// 테스트 값
	//	String empId = "A2022100";
		String empId = principal.getName();

		ReceiveMailDTO receiveData = service.selectReceiveData(empId, mailId);
		System.out.println(receiveData);

		model.addAttribute("receiveData", receiveData);		
		return "/mail/mailDetail";
	}
	
}
