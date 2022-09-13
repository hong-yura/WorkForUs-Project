package site.workforus.forus.board.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("boardCategoryDto")  
public class BoardCategoryDTO {
	private int categoryId;
	private String categoryNm;
	private String deptYn;
	
	public BoardCategoryDTO () {}
	
	public BoardCategoryDTO(int categoryId, String categoryNm, String deptYn) {
		super();
		this.categoryId = categoryId;
		this.categoryNm = categoryNm;
		this.deptYn = deptYn;
	}  
	
	
} 