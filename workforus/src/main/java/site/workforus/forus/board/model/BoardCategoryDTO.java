package site.workforus.forus.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardCategoryDTO {
	private int categoryId;
	private String categoryNm;
	private String deptYn;
}
