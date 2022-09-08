package site.workforus.forus.commute.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.commute.model.CommuteDTO;
import site.workforus.forus.mapper.commuteMapper;


@Service
public class CommuteService{
	
	
	@Autowired
	private SqlSession session;
	
	public List<CommuteDTO> selectData(String empId){
		commuteMapper mapper = session.getMapper(commuteMapper.class);
		
		List<CommuteDTO> data = mapper.selectData();
		return data;
		
	}
		
	
}
