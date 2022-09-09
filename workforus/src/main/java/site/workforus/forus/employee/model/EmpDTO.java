package site.workforus.forus.employee.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class EmpDTO {
	private String empId;
	private String empNm;
	private String empPw;
	private String empCheckPw;
	private String empEmail;
	private String empAssistEmail;
}
