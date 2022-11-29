package site.workforus.forus.employee.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Objects;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("empDto")
public class EmpDTO {
	private String empId;
	private String empNm;
	private String empPw;
	private String empCheckPw;
	private String empEmail;
	private String empAssistEmail;
	private String empGender;
	private String empPhone;
	private String empTel;
	private LocalDate empBirth;
	private int empZipCode;
	private String empAddrAll;
	private String empAddr;
	private String empAddrDetail;
	private String empAddrExtra;
	private Date empHireDt;
	private Date empResignDt;
	private String empTenure;
	private String empResignFl;
	private String empPhotoUrl;
	private String empPhotoNm;
	private String empStatus;
	private int empLoginFail;
	private String empLoginLock;
	private String empFirstLogin;
	private int deptNo;
	private String jobId;
	private int empSalary;
	private String empStamp;
	
	public EmpDTO() {}

	public EmpDTO(String empId, String empNm, String empPw, String empCheckPw, String empEmail, String empAssistEmail,
			String empGender, String empPhone, String empTel, LocalDate empBirth, int empZipCode, String empAddrAll,
			String empAddr, String empAddrDetail, String empAddrExtra, Date empHireDt, Date empResignDt,
			String empTenure, String empResignFl, String empPhotoUrl, String empPhotoNm, String empStatus,
			int empLoginFail, String empLoginLock, String empFirstLogin, int deptNo, String jobId, int empSalary,
			String empStamp) {
		super();
		this.empId = empId;
		this.empNm = empNm;
		this.empPw = empPw;
		this.empCheckPw = empCheckPw;
		this.empEmail = empEmail;
		this.empAssistEmail = empAssistEmail;
		this.empGender = empGender;
		this.empPhone = empPhone;
		this.empTel = empTel;
		this.empBirth = empBirth;
		this.empZipCode = empZipCode;
		this.empAddrAll = empAddrAll;
		this.empAddr = empAddr;
		this.empAddrDetail = empAddrDetail;
		this.empAddrExtra = empAddrExtra;
		this.empHireDt = empHireDt;
		this.empResignDt = empResignDt;
		this.empTenure = empTenure;
		this.empResignFl = empResignFl;
		this.empPhotoUrl = empPhotoUrl;
		this.empPhotoNm = empPhotoNm;
		this.empStatus = empStatus;
		this.empLoginFail = empLoginFail;
		this.empLoginLock = empLoginLock;
		this.empFirstLogin = empFirstLogin;
		this.deptNo = deptNo;
		this.jobId = jobId;
		this.empSalary = empSalary;
		this.empStamp = empStamp;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		EmpDTO empDTO = (EmpDTO) o;
		return Objects.equals(empId, empDTO.empId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(empId);
	}
}
