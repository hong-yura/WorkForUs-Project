package site.workforus.forus.admin.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("adminFileUploadDto")
public class AdminFileUploadDTO {
	private int adminFileId;	// 파일id
	private String adminFileNm; // 실제 파일명
	private String adminUuidFileNm; // 변경된 파일명
	private String adminUploadPath; // 파일 업로드 경로
	private long adminFileSize;	// 파일 사이즈
	
	public AdminFileUploadDTO() {
	}
	
	public AdminFileUploadDTO(int adminFileId, String adminFileNm, String adminUuidFileNm
			, String adminUploadPath, long adminFileSize) {
		super();
		this.adminFileId = adminFileId;
		this.adminFileNm = adminFileNm;
		this.adminUuidFileNm = adminUuidFileNm;
		this.adminUploadPath = adminUploadPath;
		this.adminFileSize = adminFileSize;
	}
	
}
