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
	private int categoryId;		// 카테고리 id
	private String categoryNm;	// 카테고리명
	private String deptYn;		// 부서 타입
	
	public BoardCategoryDTO() {}
	
	public BoardCategoryDTO(int categoryId, String categoryNm, String deptYn) {
		super();
		this.categoryId = categoryId;
		this.categoryNm = categoryNm;
		this.deptYn = deptYn;
	}
	
} 