package site.workforus.forus.commute.service;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.commute.model.CommuteDTO;
import site.workforus.forus.commute.model.SalaryDTO;
import site.workforus.forus.mapper.SalaryMapper;

@Service
public class SalaryService {
	
	private static final Logger logger = LoggerFactory.getLogger(CommuteService.class);

	@Autowired
	private SqlSession session;
	

	
}
