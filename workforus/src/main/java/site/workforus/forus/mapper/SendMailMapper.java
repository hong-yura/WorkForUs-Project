package site.workforus.forus.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.workforus.forus.employee.model.EmpDTO;
import site.workforus.forus.mail.model.ReceiveMailDTO;
import site.workforus.forus.mail.model.SendMailDTO;
import site.workforus.forus.mail.model.TempMailDTO;

public interface SendMailMapper {

	// 발신메일목록
	public SendMailDTO selectByEmpId(String empId);
	
	// 메일발신저장
	public boolean insertSendMail(SendMailDTO sendMailDto);

	
	// 수신메일목록
	public List<ReceiveMailDTO> selectReceiveAll(String empId);

	// 발신메일목록
	public List<ReceiveMailDTO> selectSendAll(String empId);
	
	// 수신메일 상세 조회
	public ReceiveMailDTO selectDetail(@Param("empId") String empId, @Param("mailId") String mailId);
	
	// 발신메일 상세 조회
	public ReceiveMailDTO selectSendDetail(@Param("empId") String empId, @Param("mailId") String mailId);

	// 사원 이메일리스트
	public List<String> selectEmailList();
	
	// 사원 이메일
	public String selectEmail(String empId);
	
	// email로 이름 가져오기
	public String selectName(String empEmail);
	
	// 메일id 출력
	public String selectMailId();
	
	// 받은목록 추가 
	public int insertReceiveMail(SendMailDTO sendMailDTO);
	
	// 외부로 전송한 메일 저장
	public void insertReceiveOutMail(SendMailDTO data);
		
	// 읽은 시간 update
	public void updateReadTime(@Param("empId") String empId, @Param("mailId") String mailId);
	
	// 읽은 여부 'N'으로 변경
	public void updateReadFl(String mailId);

	// 외부로 전송한 메일 조회
	public List<ReceiveMailDTO> selectSendOutAll(String empId);

	// 외부로 전송한 메일 상세 조회
	public ReceiveMailDTO selectOneOut(@Param("empId") String empId, @Param("mailId") String mailId);

	// 임시저장
	public int insertTempMail(SendMailDTO sendMailDto);

	// 임시보관함 조회
	public List<TempMailDTO> selectTempList(String empId);

	// 안읽은메일개수
	public int selectCntMail(String empId);
	
	// 회원정보
	public String selectEmp(String empId);
	
	// 부서이름 
	public String selectdeptNm(String empId);
  
	// 임시보관함 메일 상세
	public TempMailDTO selectTempMail(@Param("sendEmpId") String sendEmpId, @Param("tempMailId") int tempMailId);

	// 메일 휴지통으로 이동
	public int updateMailFolder(@Param("empId") String empId, @Param("mailId") String mailId);

	// 휴지통 목록
	public List<ReceiveMailDTO> selectReceiveTrash(String empId);

	// 메일 복구
	public int updateRecoveryMail(@Param("empId") String empId, @Param("mailId") String mailId);

	// 메일 영구삭제
	public int updateDeleteMail(@Param("empId") String empId, @Param("mailId") String mailId);




}
