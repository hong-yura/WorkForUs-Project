package site.workforus.forus.admin.controller;

import javax.servlet.annotation.MultipartConfig;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;
import site.workforus.forus.calendar.controller.CalendarController;

@Slf4j
@Controller
@MultipartConfig
@RequestMapping(value = "/admin")
public class AdminFileUploadController {
	
	@GetMapping(value = "/thema")
	public String getPage() {
		
		return "admin/thema";
		
	}
	
	@PostMapping()
	public String adminImageUpload() {
		return null;
		
	}
	
	
	
}
