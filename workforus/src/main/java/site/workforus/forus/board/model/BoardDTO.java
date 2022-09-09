package site.workforus.forus.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import site.workforus.forus.dept.model.DeptDTO;
@Getter
@Setter
@ToString
public class BoardDTO {
	private int boardId;
	private String boardNm;
	private String invLink;
	private String deptYn;
	private DeptDTO deptObj; 
	private BoardCategoryDTO categoryObj;
	private BoardParticipDTO participObj;
	
}
