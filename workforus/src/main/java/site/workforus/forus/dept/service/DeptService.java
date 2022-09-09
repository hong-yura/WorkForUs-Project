package site.workforus.forus.dept.service;


import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.dept.model.DeptDTO;
import site.workforus.forus.mapper.DeptMapper;

@Service
public class DeptService {
	
	@Autowired
	private SqlSession session;

	public List<DeptDTO> selectAll() {
		// Mapper 인터페이스 객체 반환
		DeptMapper mapper = session.getMapper(DeptMapper.class);
		
		// 오버라이딩 된 인터페이스의 메서드 실행 
		List<DeptDTO> datas = mapper.selectAll();
		
		return datas;
	}
	
}
