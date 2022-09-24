package site.workforus.forus.survey.model;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("surveyDto")
public class SurveyDTO {
	private int surveyNo;				// 설문no
	private String empId;				// 제출자
	private String surveyTitle;			// 제목
	private Date startDt;				// 시작일
	private Date endDt;					// 종료일
	private String progress;			// 진행
	private String resultOpenYn;		// 결과 공개여부
	private String surveyModifyYn;		// 수정여부
	private int responseCnt; 			// 참여인원
	
	public SurveyDTO() {}

	public SurveyDTO(int surveyNo, String empId, String surveyTitle, Date startDt, Date endDt, String progress,
			String resultOpenYn, String surveyModifyYn, int responseCnt) {
		super();
		this.surveyNo = surveyNo;
		this.empId = empId;
		this.surveyTitle = surveyTitle;
		this.startDt = startDt;
		this.endDt = endDt;
		this.progress = progress;
		this.resultOpenYn = resultOpenYn;
		this.surveyModifyYn = surveyModifyYn;
		this.responseCnt = responseCnt;
	}
}
