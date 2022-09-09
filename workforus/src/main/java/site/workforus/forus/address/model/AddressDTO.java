package site.workforus.forus.address.model;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Alias("addressDto")
public class AddressDTO {
	private int addrId; // 주소록
	private int addrIndvId; // 개인주소록
	private String empId; // 사번

	public AddressDTO() {
	}

	public AddressDTO(int addrId, int addrIndvId, String empId) {
		super();
		this.addrId = addrId;
		this.addrIndvId = addrIndvId;
		this.empId = empId;
	}

}