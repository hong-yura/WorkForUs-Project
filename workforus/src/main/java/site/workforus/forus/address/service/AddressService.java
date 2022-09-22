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
	
	public List<AddressDTO> getData(String empNm) {
		AddressMapper mapper = session.getMapper(AddressMapper.class);
		
		List<AddressDTO> data = mapper.selectData(empNm);
		return data;
	}
	
	
}
