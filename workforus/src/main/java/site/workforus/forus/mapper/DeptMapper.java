package site.workforus.forus.mapper;

import java.util.List;

import site.workforus.forus.admin.model.DeptDTO;

public interface DeptMapper {
	// DAO 역할을 한다.
	
	public List<DeptDTO> selectDeptAll();
	
	public DeptDTO selectDeptDetail(int no);
	
	public boolean insertDept(DeptDTO data);
	
	public List<DeptDTO> updateDept(DeptDTO deptDto);
	
	public int deleteDept(int no);
	
}
