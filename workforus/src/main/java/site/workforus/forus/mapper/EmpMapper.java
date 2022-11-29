package site.workforus.forus.mapper;

import java.util.List;

import site.workforus.forus.employee.model.EmpDTO;

public interface EmpMapper {

	// 사원 이름
	public String selectEmpNm(String empId);
	
	// 부서 이름
	public String selectDeptNm(String empId);

	public EmpDTO selectEmployee(EmpDTO empDto);

	public int insertEmployee(EmpDTO empDto);
	
	public List<EmpDTO> selectEmployeeAll();

	public EmpDTO selectEmployeeInfo(String empId);

	public int updateEmployeeInfo(EmpDTO empDto);

	public int uploadImage(EmpDTO empDto);
}
