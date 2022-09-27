package site.workforus.forus.survey.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.mapper.SurveyMapper;
import site.workforus.forus.survey.controller.SurveyController;
import site.workforus.forus.survey.model.ObjectiveDistractorDTO;
import site.workforus.forus.survey.model.SurveyDTO;
import site.workforus.forus.survey.model.SurveyQuestionDTO;

@Service
public class SurveyService {
	@Autowired
	private SqlSession session;
	
	private static final Logger logger = LoggerFactory.getLogger(SurveyController.class);

	// 설문지 리스트
	public List<SurveyDTO> selectAllSurvey() {
		SurveyMapper mapper = session.getMapper(SurveyMapper.class);
		List<SurveyDTO> datas = mapper.selectAllSurvey();
		logger.info("getData(surveyList={})", datas);
		return datas;
	}

	// 설문지 하나 
	public SurveyDTO selectSurvey(int surveyNo) {
		SurveyMapper mapper = session.getMapper(SurveyMapper.class);
		SurveyDTO data = mapper.selectSurvey(surveyNo);
		return data;
	}

	// 설문지 질문
	public List<SurveyQuestionDTO> selectQuestionList(int surveyNo) {
		SurveyMapper mapper = session.getMapper(SurveyMapper.class);
		List<SurveyQuestionDTO> datas = mapper.selectQustionList(surveyNo);
		return datas;
	}
	
	// 설문조사 소요시간
	public int getTakeTime(int surveyNo) {
		// 서술형은 120초, 객관식은 20초, 일정은 20초로 설정한다.
		List<SurveyQuestionDTO> questionList = selectQuestionList(surveyNo); 
		logger.info("getTakeTime(questionList={})", questionList);
		
		int text=0; int object=0; int schedule=0;
		// for문을 돌리면서 질문 타입의 개수를 센다.
		for(SurveyQuestionDTO data : questionList) {
			if(data.getTypeNo() == 1) {
				text++;
			}else if (data.getTypeNo() == 2) {
				object++;
			}else if(data.getTypeNo() == 3) {
				schedule++;
			}
		}
		logger.info("getTakeTime(text={}, object={}, schedule={})", text, object, schedule);
		
		int takeTime = ((text * 120) + (object * 20) + (schedule * 20))/60; // 분 단위로 하고 싶어서 60으로 나눔
		
		return takeTime;
	}

	public List<ObjectiveDistractorDTO> selectDistractorList(List<SurveyQuestionDTO> questionDatas) {
		// questiondatas에 있는 quesNo 중에서 객관식만 가지고 와야 한다. 그리고 그 no에 해당하는 distractor를 가져와야 한다.
		List<Integer> objectiveType = new ArrayList<Integer>();
		for(SurveyQuestionDTO question : questionDatas) {
			if(question.getTypeNo() == 2) {
				objectiveType.add(question.getQuesNo());
			}
		}
		logger.info("selectDistractorList(objectiveType={})", objectiveType);
		
		// 가지고 있는 객관식 유형인 질문번호로 선택지를 찾는다. 
		SurveyMapper mapper = session.getMapper(SurveyMapper.class);
		List<ObjectiveDistractorDTO> datas = mapper.selectDistractorList(objectiveType);
		logger.info("selectDistractorList(datas={})", datas);
		return datas;
	}

	
}
