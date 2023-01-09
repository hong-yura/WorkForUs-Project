package site.workforus.forus.admin.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.workforus.forus.admin.model.JobDTO;
import site.workforus.forus.mapper.JobMapper;

import java.util.List;

@Service
public class JobService {

    @Autowired SqlSession session;

    public List<JobDTO> getJobsByEmpId() {
        JobMapper mapper = session.getMapper(JobMapper.class);
        List<JobDTO> datas = mapper.selectJobsByEmpId();

        return datas;
    }
	
	
}
