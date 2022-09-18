package site.workforus.forus.board.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("postUploadDto")  
public class PostUploadFileDTO {
	private int fileId;				// 파일 id
	private int postId;				// 게시글 id 
	private String fileNm;			// 파일명 
	private String uploadLocation;	// 파일 경로(real path)
	private String uploadUrl;		// 파일 url
	private String fileType;		// 파일 확장자
	private String summ_yn;			// summernote인지 아닌지 
	
	public PostUploadFileDTO () {}

	public PostUploadFileDTO(int fileId, int postId, String fileNm, String uploadLocation, String uploadUrl,
			String fileType, String summ_yn) {
		super();
		this.fileId = fileId;
		this.postId = postId;
		this.fileNm = fileNm;
		this.uploadLocation = uploadLocation;
		this.uploadUrl = uploadUrl;
		this.fileType = fileType;
		this.summ_yn = summ_yn;
	}
	
}
