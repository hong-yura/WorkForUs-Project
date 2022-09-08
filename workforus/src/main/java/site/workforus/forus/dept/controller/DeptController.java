package site.workforus.forus.dept.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import site.workforus.forus.dept.model.DeptDTO;
import site.workforus.forus.dept.service.DeptService;

@Controller
@RequestMapping(value = "/dept")
public class DeptController {
	
	@Autowired
	private DeptService deptService;
	
	@GetMapping(value = "")
	public String getData(Model model, HttpSession session) {
		
		return "";
	}
	

}
