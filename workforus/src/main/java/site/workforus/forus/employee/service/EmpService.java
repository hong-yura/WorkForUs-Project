package site.workforus.forus.employee.service;

import javax.servlet.http.HttpSession;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import site.workforus.forus.employee.model.EmpDTO;
import site.workforus.forus.employee.model.LoginVO;
import site.workforus.forus.mapper.EmpMapper;

@Service
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class EmpService implements UserDetailsService{

	@Autowired
	private SqlSession session;
	
	@Autowired
	private BCryptPasswordEncoder pwEncoder;
	
	public boolean signup(EmpDTO empDto) {
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		
		empDto.setEmpPw(pwEncoder.encode(empDto.getEmpPw()));
		empDto.setEmpCheckPw(pwEncoder.encode(empDto.getEmpCheckPw()));
		int result = mapper.insertEmployee(empDto);
		
		return result == 1 ? true : false;
	}

	@Override
	public LoginVO loadUserByUsername(String empId) throws UsernameNotFoundException {
		LoginVO loginVo = new LoginVO();
		
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		
		EmpDTO empDto = new EmpDTO();
		empDto.setEmpId(empId);
		
		empDto = mapper.selectEmployee(empDto);
		
		if(empDto == null) {
			return null;
		}
		loginVo.setUsername(empDto.getEmpId());
		loginVo.setPassword(empDto.getEmpPw());
		return new LoginVO(empDto);
	}

	public EmpDTO selectEmployeeInfo(String empId) {
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		
		EmpDTO empDto = mapper.selectEmployeeInfo(empId);
		
		return empDto;
	}

	public boolean updateEmployeeInfo(EmpDTO empDto) {
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		
		int result = mapper.updateEmployeeInfo(empDto);
		
		return result == 1 ? true : false;
	}

	public boolean uploadImage(EmpDTO empDto) {
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		
		int result = mapper.uploadImage(empDto);
		
		return result == 1 ? true : false;
	}
	
}
