package site.workforus.forus.board.model;

public class BoardCategoryDTO {
	private int categoryId;
	private String categoryNm;
	private String deptYn;
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public void setCategoryId(String categoryId) {
		this.categoryId = Integer.parseInt(categoryId);
	}
	public String getCategoryNm() {
		return categoryNm;
	}
	public void setCategoryNm(String categoryNm) {
		this.categoryNm = categoryNm;
	}
	public String getDeptYn() {
		return deptYn;
	}
	public void setDeptYn(String deptYn) {
		this.deptYn = deptYn;
	}
	@Override
	public String toString() {
		return "BoardCategoryDTO [categoryId=" + categoryId + ", categoryNm=" + categoryNm + ", deptYn=" + deptYn + "]";
	}
	
	
}
