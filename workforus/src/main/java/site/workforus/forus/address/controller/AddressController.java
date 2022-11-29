package site.workforus.forus.address.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import site.workforus.forus.address.model.IndividualAddressDTO;
import site.workforus.forus.address.service.AddressService;
import site.workforus.forus.employee.service.EmpService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
@RequestMapping(value = "/address")
public class AddressController {
	
	@Autowired
	private AddressService addrService;

	@Autowired
	private EmpService empService;

	// 공용 주소록
	@GetMapping(value = "")
	public String getAddressPage() {
		log.info("getAddressPage");
		return "address/address";
	}

	// 공용 주소록 조회
	@ResponseBody
	@GetMapping(value = "/list", produces="application/json; charset=utf-8")
	public Map<String, Object> getAddressAll() {
		log.info("getAddressAll");

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("addrData", addrService.getAddressAll());

		return data;
	}


	// 부서 주소록
	@GetMapping(value = "/department")
	public String getDepartmentPage() {
		log.info("getDepartmentPage");
		return "address/department";
	}

	//부서 주소록 조회
	@ResponseBody
	@GetMapping(value = "/department", produces="application/json; charset=utf-8")
	public Map<String, Object> getDepartment(Authentication empId) {
		log.info("getDepartmentPage(empId)={}", empId.getName());

		String deptNm = empService.getDeptNm(empId.getName());
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("addrData", addrService.getAddressByDept(deptNm));

		return data;
	}


	// 개인 주소록
	@GetMapping(value = "/personal")
	public String getPersonalPage() {
		log.info("getPersonalPage");
		return "address/personal";
	}

	// 개인 주소록 조회
	@ResponseBody
	@GetMapping(value = "/personal", produces="application/json; charset=utf-8")
	public Map<String, Object> getPersonal(@AuthenticationPrincipal Authentication empId) {
		log.info("getPersonal(empId)={}", empId.getName());

		Map<String, Object> data = new HashMap<String, Object>();
		data.put("addrData", addrService.getAddrByEmpId(empId.getName()));

		return data;
	}


}
