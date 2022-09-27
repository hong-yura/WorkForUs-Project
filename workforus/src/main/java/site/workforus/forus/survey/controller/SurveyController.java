package site.workforus.forus.survey.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import site.workforus.forus.survey.model.ObjectiveDistractorDTO;
import site.workforus.forus.survey.model.SurveyDTO;
import site.workforus.forus.survey.model.SurveyQuestionDTO;
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
		return "/survey/survey_list";
	}
	
	@GetMapping(value="/detail")
	public String getDetail(Model model, Authentication auth
						  , @RequestParam int surveyNo) {
		logger.info("getDetail(surveyNo={})", surveyNo);
		
		// 해당 survey 정보 가지고 오기
		SurveyDTO surveyDto = surveyService.selectSurvey(surveyNo);
		logger.info("getDetail(surveyNo={}, surveyDto={})", surveyNo, surveyDto);
		
		// 소요시간을 구해줘야 한다.
		int takeTime = surveyService.getTakeTime(surveyNo);
		
		model.addAttribute("takeTime", takeTime);
		model.addAttribute("surveyData", surveyDto);
		return "/survey/survey_detail";
	}

	
	
	@GetMapping(value="/question")
	public String getQestion(Model model
						   , @RequestParam int surveyNo) {
		logger.info("getQestion(surveyNo={})", surveyNo);
		
		// 설문조사 데이터 가져오기
		SurveyDTO surveyData = surveyService.selectSurvey(surveyNo);
		logger.info("getQestion(surveyData={})", surveyData);
		// 해당 설문조사에 대한 질문 가져오기
		List<SurveyQuestionDTO> questionDatas = surveyService.selectQuestionList(surveyNo);
		logger.info("getQestion(questionDatas={})", questionDatas);
		
		// 객관식 선택지 가져오기
		List<ObjectiveDistractorDTO> distractorData = surveyService.selectDistractorList(questionDatas);
		
		model.addAttribute("distractorData", distractorData);
		model.addAttribute("questionDatas", questionDatas);
		model.addAttribute("surveyData", surveyData);
		return "/survey/survey_question";
	}
	
//	@PostMapping(value="/response")
//	public String addResponse(Model model
//							) {
//		
//	}
	
	
	
}


