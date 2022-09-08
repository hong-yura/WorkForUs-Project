package site.workforus.forus.dept.model;

import java.util.*;

public class DeptDTO {
	private int deptNo;
	private String deptName;
	private String deptMngId;
	private Date deptAddDt;
	private Date deptModDt;
	
	public DeptDTO() {}
	
	public DeptDTO(int deptNo, String deptName, String deptMngId, Date deptAddDt, Date deptModDt) {
		super();
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.deptMngId = deptMngId;
		this.deptAddDt = deptAddDt;
		this.deptModDt = deptModDt;
	}

	public int getDeptNo() {
		return deptNo;
	}

	public void setDeptNo(int deptNo) {
		this.deptNo = deptNo;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getDeptMngId() {
		return deptMngId;
	}

	public void setDeptMngId(String deptMngId) {
		this.deptMngId = deptMngId;
	}

	public Date getDeptAddDt() {
		return deptAddDt;
	}

	public void setDeptAddDt(Date deptAddDt) {
		this.deptAddDt = deptAddDt;
	}

	public Date getDeptModDt() {
		return deptModDt;
	}

	public void setDeptModDt(Date deptModDt) {
		this.deptModDt = deptModDt;
	}

	@Override
	public String toString() {
		return "DeptDTO [deptNo=" + deptNo + ", deptName=" + deptName + ", deptMngId=" + deptMngId + ", deptAddDt="
				+ deptAddDt + ", deptModDt=" + deptModDt + "]";
	}
	
	
}
