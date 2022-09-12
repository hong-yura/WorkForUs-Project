package site.workforus.forus.admin.controller;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import site.workforus.forus.admin.model.DeptDTO;
import site.workforus.forus.admin.service.DeptService;

@Controller
public class MembersMandgeController {
	
	private static final Logger logger = LoggerFactory.getLogger(MembersMandgeController.class);
	
	@Autowired
	private DeptService deptService;
	
	@RequestMapping(value = "/members", method = RequestMethod.GET)
	public String getAll(Model model, DeptDTO deptDto) {
		
		List<DeptDTO> deptDatas = deptService.getAll(); 
		model.addAttribute("deptDatas", deptDatas);
		logger.info("data: {}", deptDatas);
		
		return "admin/members";
	}
	

}
