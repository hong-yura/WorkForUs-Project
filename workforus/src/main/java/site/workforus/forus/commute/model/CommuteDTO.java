package site.workforus.forus.commute.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("commuteDto")
public class CommuteDTO {
	private int commuteNo;			// 근태번호
	private String empId;			// 사원ID
	private String commuteDt;		// 근무일자
	private String commuteTime;		// 출근시간	
	private String getoffTime;		// 퇴근시간
	private String addedTime;		// 연장근무시간
	private String workTime;		// 실제근로시간	
	private String weekAddtime;		// 주간연장근무시간
	private String weekWorktime;	// 주간근무시간

	@Override
	public String toString() {
		return "CommuteDTO [commuteNo=" + commuteNo + ", empId=" + empId + ", commuteDt=" + commuteDt + ", commuteTime="
				+ commuteTime + ", getoffTime=" + getoffTime + ", addedTime=" + addedTime + ", workTime=" + workTime
				+ "]";
	}
	
	public CommuteDTO(int commuteNo, String empId, String commuteDt, String commuteTime, String getoffTime,
			String addedTime, String workTime, String weekAddtime, String weekWorktime) {
		super();
		this.commuteNo = commuteNo;
		this.empId = empId;
		this.commuteDt = commuteDt;
		this.commuteTime = commuteTime;
		this.getoffTime = getoffTime;
		this.addedTime = addedTime;
		this.workTime = workTime;
		this.weekAddtime = weekAddtime;
		this.weekWorktime = weekWorktime;
	}

}
