package site.workforus.forus.board.model;

public class BoardDTO {
	private int boardId;
	private String boardNm;
	private String invLink;
	private String deptYn;
//	private DeptDTO deptObj; // deptDTO 클래스가 아직 생성이 안 됨 -> 유라언니
	private BoardCategoryDTO categoryObj;
	private BoardParticipDTO participObj;
	
	
	public int getBoardId() {
		return boardId;
	}


	public void setBoardId(int boardId) {
		this.boardId = boardId;
	}
	public void setBoardId(String boardId) {
		this.boardId = Integer.parseInt(boardId);
	}


	public String getBoardNm() {
		return boardNm;
	}


	public void setBoardNm(String boardNm) {
		this.boardNm = boardNm;
	}


	public String getInvLink() {
		return invLink;
	}


	public void setInvLink(String invLink) {
		this.invLink = invLink;
	}


	public String getDeptYn() {
		return deptYn;
	}


	public void setDeptYn(String deptYn) {
		this.deptYn = deptYn;
	}


//	public DeptDTO getDeptObj() {
//		return deptObj;
//	}
//
//
//	public void setDeptObj(DeptDTO deptObj) {
//		this.deptObj = deptObj;
//	}


	public BoardCategoryDTO getCategoryObj() {
		return categoryObj;
	}


	public void setCategoryObj(BoardCategoryDTO categoryObj) {
		this.categoryObj = categoryObj;
	}


	public BoardParticipDTO getParticipObj() {
		return participObj;
	}


	public void setParticipObj(BoardParticipDTO participObj) {
		this.participObj = participObj;
	}


	@Override
	public String toString() {
		return "BoardDTO [boardId=" + boardId + ", boardNm=" + boardNm + ", invLink=" + invLink + ", deptYn=" + deptYn
				+ ", categoryObj=" + categoryObj + ", participObj=" + participObj + "]";
	}
	
	
	
	
	
}
