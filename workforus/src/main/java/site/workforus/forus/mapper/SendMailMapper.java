package site.workforus.forus.mapper;

import site.workforus.forus.mail.model.SendMailDTO;

public interface SendMailMapper {

	// 발신메일목록
	SendMailDTO selectByEmpId(String empId);
	
	// 메일발신저장
	boolean insertSendMail(SendMailDTO sendMailDto);

}
