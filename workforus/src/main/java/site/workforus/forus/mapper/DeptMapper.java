package site.workforus.forus.mapper;

import java.util.List;

import site.workforus.forus.admin.model.DeptDTO;
import site.workforus.forus.employee.model.EmpDTO;

public interface DeptMapper {
	// DAO 역할을 한다.
	
	public List<DeptDTO> selectDeptAll();
	
	public DeptDTO selectDeptDetail(int deptNo);
	
	public boolean insertDept(DeptDTO deptDto);
	
	public boolean updateDept(DeptDTO deptDto);
	
	public int deleteDept(int deptNo);
	
	public EmpDTO selectEmpId();
	
}
