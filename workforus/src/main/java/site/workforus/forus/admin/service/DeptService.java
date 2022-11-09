package site.workforus.forus.admin.service;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import site.workforus.forus.admin.model.DeptDTO;
import site.workforus.forus.global.dto.ResponseDTO;
import site.workforus.forus.mapper.DeptMapper;

@Service
public class DeptService {
	
	@Autowired
	private SqlSession session;

	public List<DeptDTO> getDeptAll() {
		// Mapper 인터페이스 객체 반환
		DeptMapper mapper = session.getMapper(DeptMapper.class);
		// 오버라이딩 된 인터페이스의 메서드 실행 
		List<DeptDTO> datas = mapper.selectDeptAll();
		return datas;
	}
	
	
	@SuppressWarnings("unchecked")
	public DeptDTO getDeptDetail(int deptNo) {
		DeptMapper mapper = session.getMapper(DeptMapper.class);
		
		DeptDTO data = mapper.selectDeptDetail(deptNo);
		
		/*
		Map<String, String> map = new HashMap<>();
		
		JSONObject json = new JSONObject(map);
		
		json.put("deptNo", data.getDeptNo());
		json.put("deptName", data.getDeptName());
		json.put("deptMngId", data.getDeptMngId());
		json.put("deptAddDt", data.getDeptAddDt() + "");
		json.put("deptModDt", data.getDeptModDt() + "");
		
		json.toJSONString();
		
		ResponseDTO<JSONObject> result = new ResponseDTO<JSONObject>("Success", 200, "상세 조회를 성공하였습니다.", json);
		
		ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(result, HttpStatus.OK);
		
		return responseEntity;
		*/
		return data;
	}
	
	
	public ResponseEntity<Object> addDept(DeptDTO deptDto) {
		DeptMapper mapper = session.getMapper(DeptMapper.class);
		
		boolean addResult = mapper.insertDept(deptDto);
		
		ResponseDTO<DeptDTO> result;
		
		ResponseEntity<Object> responseEntity;
		
		if(addResult) {
			result = new ResponseDTO<DeptDTO>("Success", 200, "부서 추가 작업이 완료 되었습니다.", deptDto);
			responseEntity = new ResponseEntity<Object>(result, HttpStatus.OK);
		} else {
			result = new ResponseDTO<DeptDTO>("Fail", 400, "부서 추가 작업 중 알 수 없는 문제가 발생하였습니다.", deptDto);
			responseEntity = new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		
		return responseEntity;
	}
	
	
	public ResponseEntity<Object> modifyDept(DeptDTO deptDto) {
		DeptMapper mapper = session.getMapper(DeptMapper.class);
		
		boolean modifyResult = mapper.updateDept(deptDto);
		
		ResponseDTO<DeptDTO> result;
		
		ResponseEntity<Object> responseEntity;
		
		if(modifyResult) {
			result = new ResponseDTO<DeptDTO>("Success", 200, "부서 정보 수정이 완료 되었습니다.", deptDto);
			responseEntity = new ResponseEntity<Object>(result, HttpStatus.OK);
		} else {
			result = new ResponseDTO<DeptDTO>("Fail", 400, "수정 작업 중 알 수 없는 문제가 발생하였습니다.", deptDto);
			responseEntity = new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		
		return responseEntity;
	}
	
	
	public boolean removeDept(int deptNo) {
		DeptMapper mapper = session.getMapper(DeptMapper.class);
		
		int removeResult = mapper.deleteDept(deptNo);
		
		/*
		Map<String, Integer> map = new HashMap<>();
		
		map.put("deptNo", deptNo);
		
		JSONObject json = new JSONObject();
		
		
		ResponseDTO<JSONObject> result;
		
		ResponseEntity<Object> responseEntity;
		System.out.println(removeResult);
		if(removeResult) {
			result = new ResponseDTO<>("Success", 200, "삭제 처리가 완료 되었습니다.", json);
			responseEntity = new ResponseEntity<Object>(result, HttpStatus.OK);
		} else {
			result = new ResponseDTO<>("Fail", 400, "삭제 작업 중 알 수 없는 문제가 발생하였습니다.", json);
			responseEntity = new ResponseEntity<Object>(result, HttpStatus.BAD_REQUEST);
		}
		
		return removeResult;
		*/
		
		return removeResult == 1 ? true : false;
	}	
	
	
}
