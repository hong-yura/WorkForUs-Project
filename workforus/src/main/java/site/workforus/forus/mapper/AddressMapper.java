package site.workforus.forus.mapper;

import java.util.List;

import site.workforus.forus.address.model.AddressDTO;

public interface AddressMapper {

	public List<AddressDTO> selectAddressAll();

	public List<AddressDTO> selectAddressByDept(String deptNm);
	
	
}
