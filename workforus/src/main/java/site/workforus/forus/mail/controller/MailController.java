package site.workforus.forus.mail.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;


import site.workforus.forus.commute.controller.CommuteController;
import site.workforus.forus.mail.model.ReceiveMailDTO;
import site.workforus.forus.mail.model.SendMailDTO;
import site.workforus.forus.mail.model.TempMailDTO;
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

	// 임시 메일 저장
	@PostMapping(value="saveTemp")
	public String saveTempMail(Model model, Principal principal ,@ModelAttribute SendMailDTO sendMailDto) {
		String empId = principal.getName();
		
		boolean result = service.insertTempMail(empId, sendMailDto);
		
		return "redirect:/mail";
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
		} else {
			// 보낸 메일정보 가져옴
			ReceiveMailDTO sendData = service.selectSendData(empId, mailId);
			model.addAttribute("sendData", sendData);
		}
		return "/mail/mailSendDetail";
	}
	
	// 임시저장메일 상세
	@GetMapping(value="tempDetail")
	public String tempDetail(Model model, Principal principal, @RequestParam int mailId) {
		
		String empId = principal.getName();
		
		TempMailDTO tempDetail = service.selectTempMail(empId, mailId);
		
		// 보낸 메일정보 가져옴
		model.addAttribute("tempDetail", tempDetail);
		
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
	
	// 외부 발송메일 상세
	@GetMapping(value="tempMail")
	public String sendOutDetail(Model model, Principal principal) {
		
		String empId = principal.getName();
		
		List<TempMailDTO> dataList = service.selectTempData(empId);
		
		model.addAttribute("dataList", dataList);
		return "/mail/mailTemp";
	}
	
	// 메일 휴지통으로 보내기
	@GetMapping(value="trash")
	public String trashMail(Model model, Principal principal, @RequestParam String mailId) {
		String empId = principal.getName();
		int result = service.updateMailFolder(empId, mailId);
		
		return "redirect:/mail";
	}
	
	// 휴지통 목록
	@GetMapping(value="trashMail")
	public String trashList(Model model, Principal principal) {
		String empId = principal.getName();
		
		List<ReceiveMailDTO> dataList = service.selectTrashDatas(empId);
		model.addAttribute("dataList", dataList);
		return "/mail/mailHome";
	}
	
	// 기본 메일함으로 복구
	@GetMapping(value="recoveryMail")
	public String recoveryMail(Model model, Principal principal, @RequestParam String mailId) {
		String empId = principal.getName();
		int result = service.updateRecoveryMail(empId, mailId);
		
		return "redirect:/mail";
	}
	
	// 메일 답장
	@GetMapping(value="replyMail")
	public String replyMail(Model model, Principal principal
			, @RequestParam String email
			, @RequestParam String title
			, @RequestParam String content) {
		
		model.addAttribute("email", email);
		model.addAttribute("title", title);
		model.addAttribute("content", content);
		
		return "/mail/mailWrite";
	}
	
	// 메일영구삭제
	@PostMapping(value="/maildelete", produces = "application/json; charset=UTF-8")
	@ResponseBody //ajax로 인해 responsebody와 produces가 필요함
	public String delete(Principal principal, @RequestParam String mailId){
		String empId = principal.getName();
		
		ReceiveMailDTO receiveData = service.selectReceiveData(empId, mailId);

		JSONObject json = new JSONObject();
		
		if(receiveData == null) {
			//이미 삭제
			json.put("title", "삭제가 된 데이터");
			json.put("message", "해당 데이터는 이미 삭제가 되었습니다");
			return json.toJSONString();
		} else {
			// 삭제
			try {
				// 삭제 성공
				boolean result = service.updateDeleteMail(empId, mailId);
				json.put("title", "삭제 완료");
				json.put("message", "삭제 처리가 완료되었습니다.");
				return json.toJSONString();					
			} catch (Exception e) { 
				// 삭제 실패
				json.put("title", "삭제 실패");
				json.put("message", "삭제 작업 중 알 수 없는 오류가 발생하였습니다.");
				return json.toJSONString();
			}				
		}
	}
	
	
	
	
}
