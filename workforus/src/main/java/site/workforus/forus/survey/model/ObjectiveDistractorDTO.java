package site.workforus.forus.survey.model;


import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("distractorDto")
public class ObjectiveDistractorDTO {
	// 객관식 선택지
	private int distractorNo;
	private int quesNo;
	private String distractorContent;
	
	public ObjectiveDistractorDTO () {}

	public ObjectiveDistractorDTO(int distractorNo, int quesNo, String distractorContent) {
		super();
		this.distractorNo = distractorNo;
		this.quesNo = quesNo;
		this.distractorContent = distractorContent;
	}

}
