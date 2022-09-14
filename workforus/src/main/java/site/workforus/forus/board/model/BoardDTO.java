package site.workforus.forus.board.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import site.workforus.forus.admin.model.DeptDTO;
@Getter
@Setter
@ToString
@Alias("boardDto") 
public class BoardDTO {
	private int boardId;			// 게시판 id
	private int categoryId;			// 카테고리 id
	private String boardNm;			// 게시판 이름
	private String deletedYn;		// 삭제 여부
	private String publicYn;		// 전체공개 여부
	private String anonymityYn;		// 익명 여부
	private String invLink;			// 게시판 url
	private int deptNo;				// 부서 번호 -> 어떤 부서의 게시판인지
	private int deptYn;				// 게시판 타입
	private String boardManager;	// 운영자

	public BoardDTO() {}

	public BoardDTO(int boardId, int categoryId, String boardNm, String deletedYn, String publicYn, String anonymityYn, String invLink,
			int deptNo, int deptYn, String boardManager) { 
		super();
		this.boardId = boardId;
		this.categoryId = categoryId;
		this.boardNm = boardNm;
		this.deletedYn = deletedYn;
		this.publicYn = publicYn;
		this.anonymityYn = anonymityYn;
		this.invLink = invLink;
		this.deptNo = deptNo;
		this.deptYn = deptYn;
		this.boardManager = boardManager;
	}
}