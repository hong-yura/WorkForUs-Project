package site.workforus.forus.address.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import site.workforus.forus.address.model.AddressDTO;
import site.workforus.forus.address.service.AddressService;
import site.workforus.forus.admin.model.DeptDTO;
import site.workforus.forus.employee.model.EmpDTO;

@Controller
@RequestMapping(value = "/address")
public class AddressController {
	
	private static final Logger logger = LoggerFactory.getLogger(AddressController.class);
	
	@Autowired
	private AddressService addrService;
	
	// 공용 주소록 조회
	@GetMapping(value = "")
	public String getAddress(Model model, HttpSession session, Principal principal) {
		
		String empId = principal.getName();
				
		List<AddressDTO> data = addrService.getAddress(empId);
		model.addAttribute("addrData", data);
		return "address/address";
	}
	
	
}
