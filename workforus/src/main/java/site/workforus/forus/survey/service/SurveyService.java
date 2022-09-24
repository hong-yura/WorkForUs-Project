package site.workforus.forus.survey.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.mapper.SurveyMapper;
import site.workforus.forus.survey.controller.SurveyController;
import site.workforus.forus.survey.model.SurveyDTO;

@Service
public class SurveyService {
	@Autowired
	private SqlSession session;
	
	private static final Logger logger = LoggerFactory.getLogger(SurveyController.class);

	public List<SurveyDTO> selectAllSurvey() {
		SurveyMapper mapper = session.getMapper(SurveyMapper.class);
		List<SurveyDTO> datas = mapper.selectAllSurvey();
		logger.info("getData(surveyList={})", datas);
		return datas;
	}

	
}
