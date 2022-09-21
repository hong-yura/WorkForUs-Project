package site.workforus.forus.mail.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("sendMailDto")
public class SendMailDTO {
	private String mailId;			// 메일번호
	private String empId;			// 발신자사번
	private String receiveId;		// 수신자사번
	private String mailRef;			// 메일참조
	private String mailTitle;		// 메일제목
	private String mailContent;		// 메일본문
	private Date mailSendTime;		// 발신시간
	private String mailEssential;	// 중요도 여부
	
	public SendMailDTO() {}
	
	@Override
	public String toString() {
		return "SendMailDTO [mailId=" + mailId + ", empId=" + empId + ", receiveId=" + receiveId + ", mailRef="
				+ mailRef + ", mailTitle=" + mailTitle + ", mailContent=" + mailContent + ", mailSendTime="
				+ mailSendTime + ", mailEssential=" + mailEssential + "]";
	}

	public SendMailDTO(String mailId, String empId, String receiveId, String mailRef, String mailTitle,
			String mailContent, Date mailSendTime, String mailEssential) {
		super();
		this.mailId = mailId;
		this.empId = empId;
		this.receiveId = receiveId;
		this.mailRef = mailRef;
		this.mailTitle = mailTitle;
		this.mailContent = mailContent;
		this.mailSendTime = mailSendTime;
		this.mailEssential = mailEssential;
	}
	

}
