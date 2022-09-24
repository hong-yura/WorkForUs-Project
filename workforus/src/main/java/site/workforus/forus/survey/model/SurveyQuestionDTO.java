package site.workforus.forus.survey.model;


import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("questionDto")
public class SurveyQuestionDTO {
	private int quesNo;
	private int surveyNo;
	private int typeNo;
	private String quesContent;
	
	public SurveyQuestionDTO() {}

	public SurveyQuestionDTO(int quesNo, int surveyNo, int typeNo, String quesContent) {
		super();
		this.quesNo = quesNo;
		this.surveyNo = surveyNo;
		this.typeNo = typeNo;
		this.quesContent = quesContent;
	}
}
