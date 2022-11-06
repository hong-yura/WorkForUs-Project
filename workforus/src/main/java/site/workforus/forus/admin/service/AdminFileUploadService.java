package site.workforus.forus.admin.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.admin.model.AdminFileUploadDTO;
import site.workforus.forus.mapper.AdminFileUploadMapper;


@Service
public class AdminFileUploadService {
	
	@Autowired
	private SqlSession session;
	
	public boolean addAdminFile(AdminFileUploadDTO data) {
		AdminFileUploadMapper mapper = session.getMapper(AdminFileUploadMapper.class);
		int result = mapper.insertAdminFile(data);
		return result == 1 ? true : false;
	}
	
	public AdminFileUploadDTO getAdminFile(int adminFileId) {
		AdminFileUploadMapper mapper = session.getMapper(AdminFileUploadMapper.class);
		AdminFileUploadDTO data = mapper.selectAdminFile(adminFileId);
		return data;
	}
	
	public AdminFileUploadDTO getMaxAdminFileId(int adminFileId) {
		AdminFileUploadMapper mapper = session.getMapper(AdminFileUploadMapper.class);
		AdminFileUploadDTO maxId = mapper.selectMaxAdminFileId(adminFileId);
		return maxId;
	}
	
	
}
