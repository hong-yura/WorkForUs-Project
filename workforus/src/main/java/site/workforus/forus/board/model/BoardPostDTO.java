package site.workforus.forus.board.model;

import java.sql.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("boardPostDto")  
public class BoardPostDTO {
	private int postId;	// 게시글 id
	
	private String writer;		// 작성자 (emp_id)
	
	private int boardId;		// 게시판 id
	
	private String postTitle;	// 게시글 제목
	
	private String postPw;		// 게시글 패스워드
	
	private String content;		// 게시글 내용
	
	private Date addDate;		// 작성일 
	
	private Date modifyDate;	// 수정일
	
	private Date removeDate;	// 삭제일
	
	private int viewCnt;		// 조회수
	
	private String noticeYn;	// 공지 여부 
	
	private String temporaryYn; // 임시 저장
	
	private String reportYn;	// 신고 여부
	
	private int likeCnt;		// 추천수

	public BoardPostDTO() {}

	public BoardPostDTO(int postId, String writer, int boardId, String postTitle, String postPw, String content,
			Date addDate, Date modifyDate, Date removeDate, int viewCnt, String noticeYn,
			String temporaryYn, String reportYn, int likeCnt) {
		super();
		this.postId = postId;
		this.writer = writer;
		this.boardId = boardId;
		this.postTitle = postTitle;
		this.postPw = postPw;
		this.content = content;
		this.addDate = addDate;
		this.modifyDate = modifyDate;
		this.removeDate = removeDate;
		this.viewCnt = viewCnt;
		this.noticeYn = noticeYn;
		this.temporaryYn = temporaryYn;
		this.reportYn = reportYn;
		this.likeCnt = likeCnt;
	}
	
	
}
