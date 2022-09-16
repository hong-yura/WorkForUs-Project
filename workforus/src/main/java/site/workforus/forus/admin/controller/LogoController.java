package site.workforus.forus.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/admin")
public class LogoController {
	
	// private static final Logger logger = LoggerFactory.getLogger(LogoController.class);
	
	@GetMapping(value = "/logo")
	public String getPage() {
		
		return "admin/logo";
		
	}
	
	
	
}
