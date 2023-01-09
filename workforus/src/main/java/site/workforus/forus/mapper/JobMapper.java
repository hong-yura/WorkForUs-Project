package site.workforus.forus.mapper;

import site.workforus.forus.admin.model.JobDTO;

import java.util.List;

public interface JobMapper {

	public List<JobDTO> selectJobsByEmpId();
	
}
