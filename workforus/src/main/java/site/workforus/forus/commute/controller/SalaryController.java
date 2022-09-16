package site.workforus.forus.commute.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import site.workforus.forus.commute.model.SalaryDTO;
import site.workforus.forus.commute.service.CommuteService;
import site.workforus.forus.commute.service.SalaryService;


@Controller
@RequestMapping("/salary")
public class SalaryController extends HttpServlet {
	 
	@Autowired
	 private SalaryService service;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String getData(Model model) throws Exception { 
		// 테스용 empId
		String empId = "A2022100";
		
//		SalaryDTO data = service.selectData(empId);
		
		
		return "commute/salary";
	}
}
