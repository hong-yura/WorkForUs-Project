package site.workforus.forus.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/admin")
public class AdminFileUploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(AdminFileUploadController.class);
	
	@GetMapping(value = "/thema")
	public String getPage() {
		
		return "admin/thema";
		
	}
	
	
	
}
