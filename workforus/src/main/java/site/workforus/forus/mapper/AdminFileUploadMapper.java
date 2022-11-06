package site.workforus.forus.mapper;

import java.util.List;

import site.workforus.forus.admin.model.AdminFileUploadDTO;

public interface AdminFileUploadMapper {
	
	public int insertAdminFile(AdminFileUploadDTO data);
	
	public AdminFileUploadDTO selectAdminFile(int adminFileId);
	
	public AdminFileUploadDTO selectMaxAdminFileId(int adminFileId);
}
