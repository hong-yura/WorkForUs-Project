package site.workforus.forus.admin.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import site.workforus.forus.admin.model.DeptDTO;
import site.workforus.forus.admin.service.DeptService;
import site.workforus.forus.employee.model.EmpDTO;

@Controller
public class DeptController {
	
	private static final Logger logger = LoggerFactory.getLogger(DeptController.class);
	
	@Autowired
	private DeptService deptService;
	
	// 부서 전체 조회
	@GetMapping(value = "/dept_manage")
	public String getDeptAll(Model model, DeptDTO deptDto) {
		
		List<DeptDTO> deptDatas = deptService.getDeptAll(); 
		model.addAttribute("deptDatas", deptDatas);
		logger.info("data: {}", deptDatas);
		
		return "admin/dept_manage";
	}
	
	// 부서 추가 폼 요청
	@GetMapping(value = "/dept_add")
	public String addDept(Model model, EmpDTO emDto) {
		DeptDTO deptDatas = deptService.addDept();
		model.addAttribute("deptDatas", deptDatas);
		logger.info("addDept: {}", deptDatas);
		
		return "admin/dept_add";
	}
	
	// 부서 추가 저장 요청
	@PostMapping(value = "/dept_add", produces="application/json; charset=utf-8")
	public String addDept(Model model, EmpDTO emDto, DeptDTO deptDto) {
		
		return null;
	}
	
}
