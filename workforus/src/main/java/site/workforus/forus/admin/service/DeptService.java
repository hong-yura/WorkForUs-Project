package site.workforus.forus.admin.service;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.admin.model.DeptDTO;
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
	
	public boolean addDept(DeptDTO deptDto) {
		DeptMapper mapper = session.getMapper(DeptMapper.class);
		int result = mapper.insertDept(deptDto);		
		return result == 1? true : false;
	}
	
	public boolean modifyDept(DeptDTO data) {
		DeptMapper mapper = session.getMapper(DeptMapper.class);
		int result = mapper.updateDept(data);
		return result == 1? true : false;
	}
	
	
	public boolean removeDept(int no) {
		DeptMapper mapper = session.getMapper(DeptMapper.class);
		int result = mapper.deleteDept(no);
		return result == 1? true : false;
	}	
	
	
}
