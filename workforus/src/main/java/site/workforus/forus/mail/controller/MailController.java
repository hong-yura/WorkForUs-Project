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
		String empId = principal.getName();

		List<ReceiveMailDTO> dataList = service.selectDatas(empId);
		model.addAttribute("dataList", dataList);
		return "mail/mailHome";
	}
	
	// 보낸메일함
	@RequestMapping(value="/mailSend", method=RequestMethod.GET)
	public String mailSend(Model model, Principal principal) {
		
		String empId = principal.getName();
		
		List<ReceiveMailDTO> dataList = service.selectSend(empId);
		model.addAttribute("dataList", dataList);
		System.out.println(dataList);
		return "mail/mailSend";
	}
	
	// 메일작성 페이지로 이동
	@RequestMapping(value="/mailWrite", method=RequestMethod.GET)
	public String writeMail(Model model) {
		
		return "mail/mailWrite";
	}
	
	// 메일 전송 요청
	@PostMapping(value="mailWrite", produces="application/json; charset=UTF-8")
	public String writeMailPost(Model model, Principal principal ,@ModelAttribute SendMailDTO sendMailDto) {	// 로그인세션 필요
			
		String empId = principal.getName();

		String id = service.insertMailWrite(empId, sendMailDto);
		
		 if(id != null) {
			 return "redirect:/mail";
		 } else {
			 return "mail/mailWrite";
		 }
	}

	// 메일 상세
	@GetMapping(value="detail")
	public String getDetail(Model model, Principal principal, @RequestParam String mailId) {
		
		String empId = principal.getName();
		
		// 받은메일정보 가져옴
		ReceiveMailDTO receiveData = service.selectReceiveData(empId, mailId);
		// 최초 읽은 기록만 업데이트되게함
		if(receiveData.getMailReadFl().equals("N")) {
			// 읽은시간 수정
			service.updateReadTime(empId, mailId);
		}
		model.addAttribute("receiveData", receiveData);					
		return "/mail/mailDetail";
	}
	
	// 전송메일 상세
	@GetMapping(value="sendDetail")
	public String sendDetail(Model model, Principal principal, @RequestParam String mailId) {
		
		String empId = principal.getName();
		
		ReceiveMailDTO outData = service.selectOneOut(empId, mailId);
		if(outData.getEmpId().equals("outMail")) {
			model.addAttribute("sendData", outData);
			System.out.println(outData);
		} else {
			// 보낸 메일정보 가져옴
			ReceiveMailDTO sendData = service.selectSendData(empId, mailId);
			model.addAttribute("sendData", sendData);
		}
		return "/mail/mailSendDetail";
	}
	
	// 안읽은 상태로 변경
	@GetMapping(value="/modRead")
	public String modReadFl(Model model, @RequestParam String mailId) {
		service.updateReadFl(mailId);
		
		return "redirect:/mail";
	}
	
	// 외부 전송 메일 목록 조회
	@GetMapping(value="outMail")
	public String outMail(Model model, Principal principal) {
		
		String empId = principal.getName();
		
		// 외부 발송메일목록
		List<ReceiveMailDTO> dataList = service.selectOutSend(empId);
		model.addAttribute("dataList", dataList);
		
		
		return "/mail/mailOutSend";
	}
	
//	// 외부 발송메일 상세
//	@GetMapping(value="sendDetail")
//	public String sendOutDetail(Model model, Principal principal, @RequestParam String mailId) {
//		
//		String empId = principal.getName();
//		
//		// 외부 발송메일상세
//		ReceiveMailDTO outData = service.selectOneOut(empId);
//		model.addAttribute("outData", outData);
//		
//		model.addAttribute("sendData", Data);
//		
//		return "/mail/mailSendDetail";
//	}
//	

}
