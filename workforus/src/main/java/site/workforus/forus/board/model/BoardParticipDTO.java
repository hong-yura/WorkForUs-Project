package site.workforus.forus.board.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter 
@ToString
@Alias("boardParticipDto")
public class BoardParticipDTO { 
	private int participNo;
	private String empId;
	
	public BoardParticipDTO() {}

	public BoardParticipDTO(int participNo, String empId) {
		super();
		this.participNo = participNo;
		this.empId = empId;
	}
	
}