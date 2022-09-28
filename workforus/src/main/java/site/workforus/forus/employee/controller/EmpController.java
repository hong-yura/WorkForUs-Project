package site.workforus.forus.employee.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.multipart.MultipartFile;

import com.ibm.icu.text.SimpleDateFormat;

import site.workforus.forus.employee.model.EmpDTO;
import site.workforus.forus.employee.model.LoginVO;
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
	
	@GetMapping(value="/info")
	public String info(Model model, Authentication auth, HttpSession session) {
		LoginVO loginVo = (LoginVO) auth.getPrincipal();
		
		EmpDTO info = service.selectEmployeeInfo(loginVo.getUsername());
		
		System.out.println(info.getEmpBirth());
		
		if(info.getEmpBirth() != null) {
			LocalDate date = info.getEmpBirth();
	        String empBirth = date.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
	        System.out.println(empBirth);
	        
	        String empBirthYear1 = empBirth.substring(0, 4);
	        int empBirthYear = Integer.parseInt(empBirthYear1);
	        String empBirthMonth1 = empBirth.substring(4, 6);
	        int empBirthMonth = Integer.parseInt(empBirthMonth1);
	        String empBirthDay1 = empBirth.substring(6);
	        int empBirthDay = Integer.parseInt(empBirthDay1);
	        System.out.println(empBirthYear);
	        System.out.println(empBirthMonth);
	        System.out.println(empBirthDay);
	        model.addAttribute("empBirthYear", empBirthYear);
	        model.addAttribute("empBirthMonth", empBirthMonth);
	        model.addAttribute("empBirthDay", empBirthDay);
		}
		model.addAttribute("info", info);
		return "login/info";
	}
	
	@PostMapping(value="/info/modify", produces="application/json; charset=utf-8")
	@ResponseBody
	public String info(Model model, @RequestParam("empNm") String empNm, @RequestParam("empGender") String empGender,
					   @RequestParam("empBirth") String empBirth, @RequestParam("empZipCode") int empZipCode, @RequestParam("empAddrAll") String empAddrAll,
					   @RequestParam("empAddr") String empAddr, @RequestParam("empAddrDetail") String empAddrDetail, @RequestParam("empAddrExtra") String empAddrExtra,
					   @RequestParam("empEmail") String empEmail, @RequestParam("empPhone") String empPhone,
					   Authentication auth) {
		
		JSONObject json = new JSONObject();
		
		LoginVO loginVo = (LoginVO) auth.getPrincipal();
		System.out.println(empGender);
		System.out.println(empBirth);
		System.out.println(empAddr);
		
		EmpDTO empDto = new EmpDTO();
		
		if(empNm != null || empGender != null || empBirth != null ||  empAddr != null || empAddrDetail != null || empAddrExtra != null || empEmail != null || empPhone != null) {
			empDto.setEmpId(loginVo.getUsername());
			empDto.setEmpNm(empNm);
			empDto.setEmpGender(empGender);
			LocalDate date = LocalDate.parse(empBirth, DateTimeFormatter.ofPattern("yyyyMMdd"));
			empDto.setEmpBirth(date);
			empDto.setEmpZipCode(empZipCode);
			empDto.setEmpAddrAll(empAddrAll);
			empDto.setEmpAddr(empAddr);
			empDto.setEmpAddrDetail(empAddrDetail);
			empDto.setEmpAddrExtra(empAddrExtra);
			empDto.setEmpEmail(empEmail);
			empDto.setEmpPhone(empPhone);
		}
		boolean result = service.updateEmployeeInfo(empDto);
		
		System.out.println(result);
		
		return json.toJSONString();
	}
	
	
	@PostMapping(value="/info/image", produces="application/json; charset=utf-8")
	@ResponseBody
	public String image(@RequestParam("uploadImage") MultipartFile file, 
						HttpServletRequest request, Authentication auth) throws Exception {
		JSONObject json = new JSONObject();
		LoginVO loginVo = (LoginVO) auth.getPrincipal();
		EmpDTO empDto = service.selectEmployeeInfo(loginVo.getUsername());
		
		System.out.println(file.getOriginalFilename());
		
		String realPath = request.getServletContext().getRealPath("/resources");
		String url = request.getContextPath() + "/static/images/faces/" + file.getOriginalFilename();
		
		System.out.println(realPath);
		System.out.println(url);
		
		empDto.setEmpPhotoNm(file.getOriginalFilename());
		empDto.setEmpPhotoUrl(url);
		
		boolean result = service.uploadImage(empDto);
		System.out.println(result);
		file.transferTo(new File(realPath + "/images/faces/" + file.getOriginalFilename()));
		
		json.put("loc", "/static/images/faces/" + file.getOriginalFilename());
		
		return json.toJSONString();
	}
	
}
