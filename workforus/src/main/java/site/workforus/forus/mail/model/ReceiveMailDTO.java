package site.workforus.forus.mail.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import site.workforus.forus.employee.model.EmpDTO;

@Getter
@Setter
@Alias("receiveMailDto")
public class ReceiveMailDTO {
	private String mailId;			// 메일id
	private String empId;			// 받는사람 사번
	private int mailFolder;			// 메일보관함
	private String mailSendEmail;	// 전송한사람 이메일
	private Date mailReadTime;		// 읽은시간
	private String spamId;			// 스팸번호
	private SendMailDTO mailObj;
	private EmpDTO empObj;
	
	public ReceiveMailDTO() {}

	@Override
	public String toString() {
		return "ReceiveMailDTO [mailId=" + mailId + ", empId=" + empId + ", mailFolder=" + mailFolder
				+ ", mailSendEmail=" + mailSendEmail + ", mailReadTime=" + mailReadTime + ", spamId=" + spamId
				+ ", mailObj=" + mailObj + ", empObj=" + empObj + "]";
	}

	public ReceiveMailDTO(String mailId, String empId, int mailFolder, String mailSendEmail, Date mailReadTime,
			String spamId, SendMailDTO mailObj, EmpDTO empObj) {
		super();
		this.mailId = mailId;
		this.empId = empId;
		this.mailFolder = mailFolder;
		this.mailSendEmail = mailSendEmail;
		this.mailReadTime = mailReadTime;
		this.spamId = spamId;
		this.mailObj = mailObj;
		this.empObj = empObj;
	}
	
	
	
}
