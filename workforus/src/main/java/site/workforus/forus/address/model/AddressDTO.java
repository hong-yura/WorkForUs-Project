package site.workforus.forus.address.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import site.workforus.forus.employee.model.EmpDTO;

@Getter
@Setter
@ToString
@Alias("addressDto")
public class AddressDTO {
	private int addrId;     // 주소록
	private String empId;   // 사번
	private EmpDTO empObj;

	public AddressDTO() {
	}

	public AddressDTO(int addrId, String empId, EmpDTO empObj) {
		super();
		this.addrId = addrId;
		this.empId = empId;
		this.empObj = empObj;
	}

}