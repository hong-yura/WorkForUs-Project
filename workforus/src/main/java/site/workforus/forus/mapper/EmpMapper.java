package site.workforus.forus.mapper;

import java.util.List;

import site.workforus.forus.employee.model.EmpDTO;

public interface EmpMapper {
	public EmpDTO selectEmployee(EmpDTO empDto);

	public int insertEmployee(EmpDTO empDto);
	
	public List<EmpDTO> selectEmployeeAll();
}
