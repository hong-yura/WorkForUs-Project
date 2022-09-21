package site.workforus.forus.mail.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.mail.model.SendMailDTO;
import site.workforus.forus.mapper.SendMailMapper;

@Service
public class MailService {

	@Autowired
	private SqlSession session;
	
	public SendMailDTO selectData(String empId) {
		SendMailMapper mapper = session.getMapper(SendMailMapper.class);
		
		SendMailDTO data = mapper.selectByEmpId(empId);
		return data;
	}

	// 메일 전송 요청
	public String mailWrite(String empId, SendMailDTO data) {
		SendMailMapper mapper = session.getMapper(SendMailMapper.class);

		SendMailDTO sendMailDto = new SendMailDTO();
		
		sendMailDto.setMailTitle(data.getMailTitle()); 
		sendMailDto.setMailContent(data.getMailContent()); 
		sendMailDto.setMailSendTime(data.getMailSendTime()); 
		sendMailDto.setEmpId(empId); 
		sendMailDto.setReceiveId(data.getReceiveId()); 
		
		boolean result = mapper.insertSendMail(sendMailDto);

    	if(result) {
    		return sendMailDto.getMailId();
    	}
    	return "";
    }
}
