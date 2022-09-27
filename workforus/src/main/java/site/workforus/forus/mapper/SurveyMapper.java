package site.workforus.forus.mapper;

import java.util.List;

import site.workforus.forus.survey.model.ObjectiveDistractorDTO;
import site.workforus.forus.survey.model.SurveyDTO;
import site.workforus.forus.survey.model.SurveyQuestionDTO;

public interface SurveyMapper {

	// 설문 리스트 가져오기
	public List<SurveyDTO> selectAllSurvey();

	// 설문 하나 가져오기
	public SurveyDTO selectSurvey(int surveyNo);

	// 질문가져오기
	public List<SurveyQuestionDTO> selectQustionList(int surveyNo);

	// 객관식 선택지 가져오기
	public List<ObjectiveDistractorDTO> selectDistractorList(List<Integer> objectiveType);


}
