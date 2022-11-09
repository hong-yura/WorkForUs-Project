package site.workforus.forus.admin.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import site.workforus.forus.employee.model.EmpDTO;

@Getter
@Setter
@ToString
@Alias("deptDto")
public class DeptDTO {
	private int deptNo; // 부서코드
	private String deptName; // 부서명
	private String deptMngId; // 부서책임자(부장 사번)
	private Date deptAddDt; // 부서 생성일
	private Date deptModDt; // 부서 최종 수정일
	
	private EmpDTO EmpObj;

	public DeptDTO() {
	}

	public DeptDTO(int deptNo, String deptName, String deptMngId, Date deptAddDt, Date deptModDt) {
		super();
		this.deptNo = deptNo;
		this.deptName = deptName;
		this.deptMngId = deptMngId;
		this.deptAddDt = deptAddDt;
		this.deptModDt = deptModDt;
	}

}
