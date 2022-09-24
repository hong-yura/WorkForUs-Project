package site.workforus.forus.survey.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import site.workforus.forus.survey.model.SurveyDTO;
import site.workforus.forus.survey.service.SurveyService;

@Controller
@RequestMapping(value="/survey")
public class SurveyController {
	
	@Autowired
	private SurveyService surveyService;
	
	private static final Logger logger = LoggerFactory.getLogger(SurveyController.class);
	
	@GetMapping(value="")
	public String getData(Model model, Authentication auth) {
		
		// 설문 객체 전달
		List<SurveyDTO> surveyList = surveyService.selectAllSurvey();
		logger.info("getData(surveyList={})", surveyList);
		
		model.addAttribute("surveyList", surveyList);
		return "/survey/list";
	}
	
	
}
