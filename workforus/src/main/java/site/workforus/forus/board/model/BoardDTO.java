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
	private int boardId;
	private String boardNm;
	private String invLink;
	private String deptYn;
	private DeptDTO deptObj; 
	private BoardCategoryDTO categoryObj;
	private BoardParticipDTO participObj;
	
	public BoardDTO () {}
	
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