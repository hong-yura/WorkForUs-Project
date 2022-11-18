package site.workforus.forus.address.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.address.model.AddressDTO;
import site.workforus.forus.mapper.AddressMapper;

@Service
public class AddressService {
	
	@Autowired SqlSession session;

	public List<AddressDTO> getAddressAll() {
		AddressMapper mapper = session.getMapper(AddressMapper.class);
		List<AddressDTO> datas = mapper.selectAddressAll();

		return datas;
	}

	public List<AddressDTO> getAddressByDept(String deptNm) {
		AddressMapper mapper = session.getMapper(AddressMapper.class);
		List<AddressDTO> data = mapper.selectAddressByDept(deptNm);

		return data;
	}
	
}
