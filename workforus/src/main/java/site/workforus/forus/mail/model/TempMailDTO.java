package site.workforus.forus.mail.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import site.workforus.forus.employee.model.EmpDTO;

@Getter
@Setter
@Alias("tempMailDto")
public class TempMailDTO {
	private int tempMailId;				// 임시메일번호
	private String sendEmpId;			// 전송하는사람 사번
	private String tempMailTitle;		// 메일제목
	private String tempMailContent;		// 본문
	private String receiveEmail;		// 받는사람 이메일
	private Date saveTime;				// 저장시간
	private EmpDTO empObj;

	public TempMailDTO() {}
	
	public TempMailDTO(int tempMailId, String sendEmpId, String tempMailTitle, String tempMailContent,
			String receiveEmail, Date saveTime) {
		super();
		this.tempMailId = tempMailId;
		this.sendEmpId = sendEmpId;
		this.tempMailTitle = tempMailTitle;
		this.tempMailContent = tempMailContent;
		this.receiveEmail = receiveEmail;
		this.saveTime = saveTime;
	}
	
	
	
	
	
}
