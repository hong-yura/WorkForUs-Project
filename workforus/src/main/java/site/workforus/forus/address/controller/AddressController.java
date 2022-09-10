package site.workforus.forus.address.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/address")
public class AddressController {

	@GetMapping(value = "")
	public String getPage() {
		
		return "address/address";
	}
	
}
