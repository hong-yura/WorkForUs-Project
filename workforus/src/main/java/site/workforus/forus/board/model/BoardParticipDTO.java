package site.workforus.forus.board.model;

import org.apache.ibatis.type.Alias;

@Alias("boardParticipDto")
public class BoardParticipDTO {
	private int participNo;
	private String empId;

	public int getParticipNo() {
		return participNo;
	}

	public void setParticipNo(int participNo) {
		this.participNo = participNo;
	}

	public void setParticipNo(String participNo) {
		this.participNo = Integer.parseInt(participNo);
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	@Override
	public String toString() {
		return "BoardParticipDTO [participNo=" + participNo + ", empId=" + empId + "]";
	}

}
