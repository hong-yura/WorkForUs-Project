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
	private int participNo;		// 참여 명단 번호
	private String empId;		// 참여 사원 id
	private int boardId;		// 게시판 id
	
	public BoardParticipDTO() {}

	public BoardParticipDTO(int participNo, String empId, int boardId) {
		super();
		this.participNo = participNo;
		this.empId = empId;
		this.boardId = boardId;
	}
}