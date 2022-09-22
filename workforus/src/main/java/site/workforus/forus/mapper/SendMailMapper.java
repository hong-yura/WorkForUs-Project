package site.workforus.forus.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.workforus.forus.mail.model.ReceiveMailDTO;
import site.workforus.forus.mail.model.SendMailDTO;

public interface SendMailMapper {

	// 발신메일목록
	public SendMailDTO selectByEmpId(String empId);
	
	// 메일발신저장
	public boolean insertSendMail(SendMailDTO sendMailDto);

	// 수신메일목록
	public List<ReceiveMailDTO> selectReceiveAll(String empId);

	// 발신목록 추가
	public ReceiveMailDTO selectDetail(@Param("empId") String empId, @Param("mailId") String mailId);
	
	// 사원 이메일리스트
	public List<String> selectEmailList();
	
	// 사원 이메일
	public String selectEmail(String empId);
	
	public String selectMailId();
	
	// 받은목록 추가 
	public int insertReceiveMail(SendMailDTO sendMailDTO);
}
