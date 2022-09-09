package site.workforus.forus.commute.model;

import org.apache.ibatis.type.Alias;

@Alias("commuteDto")
public class CommuteDTO {
	private int commuteNo;
	private String empId;
	private String commuteDt;
	private String commuteTime;
	private String getoffTime;
	private String addedTime;
	private String workTime;

	public int getCommuteNo() {
		return commuteNo;
	}

	public void setCommuteNo(int commuteNo) {
		this.commuteNo = commuteNo;
	}

	public String getEmpId() {
		return empId;
	}

	public void setEmpId(String empId) {
		this.empId = empId;
	}

	public String getCommuteDt() {
		return commuteDt;
	}

	public void setCommuteDt(String commuteDt) {
		this.commuteDt = commuteDt;
	}

	public String getCommuteTime() {
		return commuteTime;
	}

	public void setCommuteTime(String commuteTime) {
		this.commuteTime = commuteTime;
	}

	public String getGetoffTime() {
		return getoffTime;
	}

	public void setGetoffTime(String getoffTime) {
		this.getoffTime = getoffTime;
	}

	public String getAddedTime() {
		return addedTime;
	}

	public void setAddedTime(String addedTime) {
		this.addedTime = addedTime;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	@Override
	public String toString() {
		return "CommuteDTO [commuteNo=" + commuteNo + ", empId=" + empId + ", commuteDt=" + commuteDt + ", commuteTime="
				+ commuteTime + ", getoffTime=" + getoffTime + ", addedTime=" + addedTime + ", workTime=" + workTime
				+ "]";
	}

}
