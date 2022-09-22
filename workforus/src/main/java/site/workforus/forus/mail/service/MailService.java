package site.workforus.forus.mail.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import site.workforus.forus.mail.model.ReceiveMailDTO;
import site.workforus.forus.mail.model.SendMailDTO;
import site.workforus.forus.mapper.SendMailMapper;

@Service
public class MailService {

	@Autowired
	private SqlSession session;
	
	// 수신 메일목록
	public List<ReceiveMailDTO> selectDatas(String empId) {
		SendMailMapper mapper = session.getMapper(SendMailMapper.class);
		
		List<ReceiveMailDTO> datas = mapper.selectReceiveAll(empId);
		return datas;
	}
	
	
	public SendMailDTO selectData(String empId) {
		SendMailMapper mapper = session.getMapper(SendMailMapper.class);
		
		SendMailDTO data = mapper.selectByEmpId(empId);
		return data;
	}

	// 메일 전송 요청
	public String insertMailWrite(String empId, SendMailDTO data) {
		SendMailMapper mapper = session.getMapper(SendMailMapper.class);

		data.setEmpId(empId);
		
		// 보낸메일함 추가
		boolean result = mapper.insertSendMail(data);
		
		// 전송시간으로 보낸 메일id
		String mailId = mapper.selectMailId();
		
		System.out.println(mailId + "메일아이디 있을텐데 시뱅이 없는척 지리죠?");
		// 사원 이메일 리스트
		List<String> emailList = mapper.selectEmailList();
		
		// 메일전송한사람 이메일주소
		String sendEmail = mapper.selectEmail(empId);
		
		// 받는사람 이메일이 사원테이블에 있다면 수신목록도 추가 필요
		if(emailList.contains(data.getReceiveEmail())) {
			
			int idx = emailList.indexOf(data.getReceiveEmail());
			data.setReceiveEmail(emailList.get(idx));
			data.setEmpId(sendEmail);
			data.setMailId(mailId);
			mapper.insertReceiveMail(data);
		}
    	if(result) {
    		return mailId;
    	}
    	return "";
    }


	public ReceiveMailDTO selectReceiveData(String empId, String mailId) {
		SendMailMapper mapper = session.getMapper(SendMailMapper.class);

		ReceiveMailDTO detailData = mapper.selectDetail(empId, mailId);
		
		return detailData;
	}
}
