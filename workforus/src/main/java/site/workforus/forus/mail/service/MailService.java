package site.workforus.forus.mail.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import site.workforus.forus.mail.model.ReceiveMailDTO;
import site.workforus.forus.mail.model.SendMailDTO;
import site.workforus.forus.mail.model.TempMailDTO;
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
	
	// 발신 메일 리스트 조회
	public List<ReceiveMailDTO> selectSend(String empId) {
		SendMailMapper mapper = session.getMapper(SendMailMapper.class);
		
		List<ReceiveMailDTO> datas = mapper.selectSendAll(empId);
		return datas;
	}
	
	/* 지울예정 
	public SendMailDTO selectData(String empId) {
		SendMailMapper mapper = session.getMapper(SendMailMapper.class);
		
		SendMailDTO data = mapper.selectByEmpId(empId);
		return data;
	}
	*/
	
	// 메일 전송 요청
	public String insertMailWrite(String empId, SendMailDTO data) {
		SendMailMapper mapper = session.getMapper(SendMailMapper.class);

		data.setEmpId(empId);
		
		// 보낸메일함 추가
		boolean result = mapper.insertSendMail(data);
		
		// 현재 메일id
		String mailId = mapper.selectMailId();
		
		// 사원 이메일 리스트
		List<String> emailList = mapper.selectEmailList();
		
		// 메일전송한사람 이메일주소
		String sendEmail = mapper.selectEmail(empId);
		
		// 받는사람 이메일이 사원테이블에 있다면 수신목록도 추가 필요
		if(emailList.contains(data.getReceiveEmail())) {
			int idx = emailList.indexOf(data.getReceiveEmail());		// 받는사람 이메일 번호
			data.setReceiveEmail(emailList.get(idx));					// 받는사람 이메일을 저장
			data.setEmpId(sendEmail);									// 보낸사람 empId 저장
			data.setMailId(mailId);										// 메일id
			mapper.insertReceiveMail(data);
		} else {	// 사원목록에 없다면 emp_id를 'outMail'로 지정함
			data.setEmpId(sendEmail);									
			data.setMailId(mailId);	
			mapper.insertReceiveOutMail(data);
		}
    	if(result) {
    		return mailId;
    	}
    	return "";
    }

	// 받은메일 상세내용 조회
	public ReceiveMailDTO selectReceiveData(String empId, String mailId) {
		SendMailMapper mapper = session.getMapper(SendMailMapper.class);
		// 보낸사람 empId가 필요해요..email로 불러와야할듯
		ReceiveMailDTO detailData = mapper.selectDetail(empId, mailId);
		
		detailData.getEmpObj().setEmpNm(mapper.selectName(detailData.getMailSendEmail()));
		return detailData;
	}
	
	// 보낸메일 상세내용 조회
	public ReceiveMailDTO selectSendData(String empId, String mailId) {
		SendMailMapper mapper = session.getMapper(SendMailMapper.class);
		ReceiveMailDTO detailData = mapper.selectSendDetail(empId, mailId);
		
		return detailData;
	}
	
	// 메일 읽은 시간 update
	public void updateReadTime(String empId, String mailId) {
		SendMailMapper mapper = session.getMapper(SendMailMapper.class);
		mapper.updateReadTime(empId, mailId);
	}
	
	// 안읽음 버튼 -> 읽음여부만 'N'으로 변경
	public void updateReadFl(String mailId) {
		SendMailMapper mapper = session.getMapper(SendMailMapper.class);
		mapper.updateReadFl(mailId);
	}

	// 외부 발신메일 가져오기
	public List<ReceiveMailDTO> selectOutSend(String empId) {
		SendMailMapper mapper = session.getMapper(SendMailMapper.class);
		
		List<ReceiveMailDTO> datas = mapper.selectSendOutAll(empId);
		return datas;
	}

	// 외부 발신메일 상세 조회
	public ReceiveMailDTO selectOneOut(String empId, String mailId) {
		SendMailMapper mapper = session.getMapper(SendMailMapper.class);
		ReceiveMailDTO detailData = mapper.selectOneOut(empId, mailId);
		
		return detailData;
	}

	// 메일 임시저장
	public boolean insertTempMail(String empId, SendMailDTO sendMailDto) {
		SendMailMapper mapper = session.getMapper(SendMailMapper.class);

		sendMailDto.setEmpId(empId);
		// 임시보관함 저장
		int result = mapper.insertTempMail(sendMailDto);
		
		return result == 1 ? true : false;
	}

	// 임시저장 메일 조회
	public List<TempMailDTO> selectTempData(String empId) {
		SendMailMapper mapper = session.getMapper(SendMailMapper.class);
		List<TempMailDTO> data = mapper.selectTempList(empId);
		
		return data;
	}

	// 휴지통으로 이동
	public int updateMailFolder(String empId, String mailId) {
		SendMailMapper mapper = session.getMapper(SendMailMapper.class);
		
		int result = mapper.updateMailFolder(empId, mailId);
		return result;
	}
	
	// 휴지통메일 목록
	public List<ReceiveMailDTO> selectTrashDatas(String empId) {
		SendMailMapper mapper = session.getMapper(SendMailMapper.class);
		List<ReceiveMailDTO> datas = mapper.selectReceiveTrash(empId);
		return datas;
	}

	public int updateRecoveryMail(String empId, String mailId) {
		SendMailMapper mapper = session.getMapper(SendMailMapper.class);
		
		int result = mapper.updateRecoveryMail(empId, mailId);
		return result;
	}


	


}
