package site.workforus.forus.admin.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.admin.model.JobDTO;
import site.workforus.forus.employee.model.EmpDTO;
import site.workforus.forus.employee.model.LoginVO;
import site.workforus.forus.mapper.JobMapper;

@Service
public class JobService {
	
	@Autowired
	private SqlSession session;
	
	public List<JobDTO> getJobData(String empId) {
		JobMapper mapper = session.getMapper(JobMapper.class);
		List<JobDTO> data = mapper.selectJobData(empId);
		return data;
	}
	
	public List<JobDTO> getEmpJob(String empId) {
		JobMapper mapper = session.getMapper(JobMapper.class);
		List<JobDTO> data = mapper.selectEmpJob(empId);
		return data;
	}
	
	
}
