package site.workforus.forus.mapper;

import java.util.List;

import site.workforus.forus.admin.model.AdminFileUploadDTO;

public interface AdminFileUploadMapper {
	
	public int insertAdminFile(AdminFileUploadDTO data);
	
	public List<AdminFileUploadDTO> selectAdminFile(int adminFileId);
}
