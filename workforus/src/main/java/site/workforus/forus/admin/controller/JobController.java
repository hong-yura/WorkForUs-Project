package site.workforus.forus.admin.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import site.workforus.forus.admin.model.JobDTO;
import site.workforus.forus.admin.service.JobService;
import site.workforus.forus.employee.model.EmpDTO;
import site.workforus.forus.employee.model.LoginVO;
import site.workforus.forus.employee.service.EmpService;

@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class JobController {
	
	@Autowired
	private JobService jobService;
	
	/*
	// 직급별 사원 조회
	@GetMapping(value = "/job_list")
	public String getJobData(Model model, Principal principal) {
		
		String empId = principal.getName();
		
		List<JobDTO> data = jobService.getJobData(empId);
		model.addAttribute("data", data);
		log.info("data: {}", data);
		return "admin/job_list";
	}
	*/
	
	// 사원별 직급 조회
	@GetMapping(value = "/job_list")
	public String getEmpJob(Model model, Principal principal) {
		
		// 전체 사원 조회해서 직급 가져오기
		//List<EmpDTO> empDatas = EmpService
		
		String empId = principal.getName();
		
		List<JobDTO> empJobData = jobService.getEmpJob(empId);
		model.addAttribute("empJobData", empJobData);
		log.info("getEmpJob(empJobData={})", empJobData);
		return "admin/job_list";
	}
	
	
	
	
	
	
}
