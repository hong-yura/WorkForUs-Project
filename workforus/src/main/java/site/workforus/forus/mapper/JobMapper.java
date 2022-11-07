package site.workforus.forus.mapper;

import java.util.List;

import site.workforus.forus.admin.model.JobDTO;

public interface JobMapper {

	public List<JobDTO> selectJobData(String empId);
	
	public List<JobDTO> selectEmpJob(String empId);
	
}
