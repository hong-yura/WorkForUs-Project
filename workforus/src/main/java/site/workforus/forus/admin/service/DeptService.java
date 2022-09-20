package site.workforus.forus.admin.service;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.admin.model.DeptDTO;
import site.workforus.forus.admin.model.DeptVO;
import site.workforus.forus.mapper.DeptMapper;

@Service
public class DeptService {
	
	@Autowired
	private SqlSession session;

	public List<DeptDTO> getDeptAll() {
		// Mapper 인터페이스 객체 반환
		DeptMapper mapper = session.getMapper(DeptMapper.class);
		
		// 오버라이딩 된 인터페이스의 메서드 실행 
		List<DeptDTO> datas = mapper.selectDeptAll();
		
		return datas;
	}
	
	public DeptDTO getDeptDetail(int no) {
		DeptMapper mapper = session.getMapper(DeptMapper.class);
		DeptDTO result = mapper.selectDeptDetail(no);
		return result;
	}
	
	public int addDept(DeptDTO deptDto) {
		DeptMapper mapper = session.getMapper(DeptMapper.class);
		
		int result = mapper.insertDept(deptDto);
		
		// if deptMngId가 null이면 참조할 객체가 없으므로 실패
		
		// if 부서명 중복인지 확인
		
		return result;
	}
	
	public boolean removeDept(int no) {
		DeptMapper mapper = session.getMapper(DeptMapper.class);
		int result = mapper.deleteDept(no);
		return result == 1? true : false;
	}	
	
	
}
