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
	private int boardId;					// 게시판 id
	private String boardNm;					// 게시판 이름
	private String invLink;					// 게시판 url
	private String deptYn;					// 게시판 타입
	private DeptDTO deptObj; 				// 부서 
	private BoardCategoryDTO categoryObj;	// 카테고리 가져올 때 사용 
	private BoardParticipDTO participObj;	// 게시판 가져올 때 사용
	
	public BoardDTO() {}
	
	public BoardDTO(int boardId, String boardNm, String invLink, String deptYn, DeptDTO deptObj,
			BoardCategoryDTO categoryObj, BoardParticipDTO participObj) {
		super();
		this.boardId = boardId;
		this.boardNm = boardNm;
		this.invLink = invLink;
		this.deptYn = deptYn;
		this.deptObj = deptObj;
		this.categoryObj = categoryObj;
		this.participObj = participObj;
	}
	
	
}