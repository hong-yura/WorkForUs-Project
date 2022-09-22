package site.workforus.forus.commute.controller;

import java.io.IOException;
import java.text.DecimalFormat;
import java.util.List;

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
import site.workforus.forus.employee.model.EmpDTO;


@Controller
@RequestMapping("/salary")
public class SalaryController extends HttpServlet {
	
	@Autowired
	 private SalaryService service;
	
	@RequestMapping(value="", method=RequestMethod.GET)
	public String getData(Model model) throws Exception { 
		// 테스용 empId
		String empId = "A2022100";
		
		// ',' 찍기 위해 format설정
		DecimalFormat decFormat = new DecimalFormat("###,###");
		
		int empSalary = service.selectEmpSalary(empId); 		// 계약된 월급
		int todaySalary = service.getTodaySalary(empSalary);	// 오늘까지의 월급
		int calTax = (int) (todaySalary * 0.088993);			// 오늘까지의 기본세금
		int calIncome = service.calIncome(todaySalary);			// 오늘까지의 소득세
		int diffSalary = todaySalary - calTax - calIncome;		// 오늘까지 지급예정 금액
		
		String salary = decFormat.format(empSalary);
		String calSalary = decFormat.format(todaySalary);
		String commonTax = decFormat.format(calTax);
		String incomeTax = decFormat.format(calIncome);
		String realSalary = decFormat.format(diffSalary);		
		
		
		model.addAttribute("empSalary", salary);
		model.addAttribute("calSalary", calSalary);
		model.addAttribute("commonTax", commonTax);
		model.addAttribute("incomeTax", incomeTax);
		model.addAttribute("realSalary", realSalary);
		
		service.updateAll(empId, todaySalary, calTax, calIncome);
		
		// 이전 월급 목록 불러오기
		List<SalaryDTO> salaryList = service.selectAll(empId);
		model.addAttribute("salaryList", salaryList);
		
		
		return "commute/salary";
	}
	
}
