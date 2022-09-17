package site.workforus.forus.board.model;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("postCommentDto")
public class PostCommentDTO {	
	private int commentId;			// 댓글 id
	private int postId;				// 게시글 id
	private String content;			// 댓글 내용
	private String empId;			// 작성자 id
	private String empNm;			// 작성자명
	private int groupNo;			// 몇 번째 댓글인지 확인
	private int depth;				// 댓글 계층(댓글인지, 대댓인지)
	private int sort;				// 댓글 정렬
	private String anonymity_yn; 	// 익명 여부 
	// 삭제 여부는 쿼리문에서 확인할 거라서 일부러 뺌 -> 필요하면 나중에 추가 예정
	private Date addDate;			// 작성일
	
	public PostCommentDTO() {}

	public PostCommentDTO(int commentId, int postId, String content, String empId, String empNm, int groupNo, int depth,
			int sort, String anonymity_yn, Date addDate) {
		super();
		this.commentId = commentId;
		this.postId = postId;
		this.content = content;
		this.empId = empId;
		this.empNm = empNm;
		this.groupNo = groupNo;
		this.depth = depth;
		this.sort = sort;
		this.anonymity_yn = anonymity_yn;
		this.addDate = addDate;
	}
}
