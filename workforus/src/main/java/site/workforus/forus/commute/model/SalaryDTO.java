package site.workforus.forus.commute.model;

import java.util.Date;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Alias("salaryDto")
public class SalaryDTO {
	private int salaryId;
	private String salaryLogDt;
	private int salaryLog;
	private int salaryTax;
	private int incomeTax;
	private String empId;
	
	public SalaryDTO(int salaryId, String salaryLogDt, int salaryLog, int salaryTax, int incomeTax, String empId) {
		super();
		this.salaryId = salaryId;
		this.salaryLogDt = salaryLogDt;
		this.salaryLog = salaryLog;
		this.salaryTax = salaryTax;
		this.incomeTax = incomeTax;
		this.empId = empId;
	}

	

	
}
