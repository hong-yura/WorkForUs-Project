package site.workforus.forus.survey.model;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("questionDto")
public class SurveyQuestionDTO {
	private int questNo;
	private int surveyNo;
	private int typeNo;
	private String quesContent;
	
	public SurveyQuestionDTO() {}

	public SurveyQuestionDTO(int questNo, int surveyNo, int typeNo, String quesContent) {
		super();
		this.questNo = questNo;
		this.surveyNo = surveyNo;
		this.typeNo = typeNo;
		this.quesContent = quesContent;
	}
}
