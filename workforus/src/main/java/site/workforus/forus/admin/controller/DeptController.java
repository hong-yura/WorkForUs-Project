package site.workforus.forus.admin.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import lombok.extern.slf4j.Slf4j;
import site.workforus.forus.admin.model.DeptDTO;
import site.workforus.forus.admin.service.DeptService;

@Slf4j
@Controller
@RequestMapping(value = "/admin")
public class DeptController {
	
	
	@Autowired
	private DeptService deptService;
	
	// 부서 전체 조회
	@GetMapping(value = "/dept_manage")
	public String getDeptAll(Model model, HttpSession session
			, DeptDTO deptDto) {
		
		List<DeptDTO> deptDatas = deptService.getDeptAll(); 
		model.addAttribute("deptDatas", deptDatas);
		log.info("data: {}", deptDatas);
		
		return "admin/dept_manage";
	}
	
	// 부서 상세 조회
	@ResponseBody
	@GetMapping(value = "/departments/detail/{deptNo}", produces="application/json; charset=utf-8")
	public ResponseEntity<Object> getDeptDetail(@PathVariable("deptNo") int deptNo) {
		log.info("getDeptDetail(no): {}", deptNo);
		// 로그인 세션 추가하기
		
		ResponseEntity<Object> data = deptService.getDeptDetail(deptNo);
		
		/*
		DeptDTO data = deptService.getDeptDetail(deptNo);
		
		JSONObject json = new JSONObject();
		
		json.put("deptNo", data.getDeptNo());
		json.put("deptName", data.getDeptName());
		json.put("deptMngId", data.getDeptMngId());
		json.put("deptAddDt", data.getDeptAddDt() + "");
		json.put("deptModDt", data.getDeptModDt() + "");
		
		return json.toJSONString();
		*/
		
		return data;
	}
	
	
	// 부서 추가 폼 요청
	@GetMapping(value = "/dept-add")
	public String addDept() {
		// 로그인 세션 추가하기
		return "admin/dept-add";
	}
	
	// 부서 추가 저장 요청
	@ResponseBody
	@PostMapping(value = "/departments", produces="application/json; charset=utf-8")
	public ResponseEntity<Object> addDept(@RequestBody DeptDTO deptDto) {
		// 로그인 세션 추가하기
		log.info("addDept(DeptDTO={})", deptDto);
		
		ResponseEntity<Object> datas = deptService.addDept(deptDto);
		
		/*
		DeptDTO data = new DeptDTO();
		data.setDeptName(deptDto.getDeptName());
		data.setDeptMngId(deptDto.getDeptMngId());
		boolean result = deptService.addDept(data);
		
		JSONObject json = new JSONObject();
		if(result) {
			json.put("title", "Success");
			json.put("message", "부서 추가 작업이 완료 되었습니다.");
			return json.toJSONString();
		} else {
			json.put("title", "Fail");
			json.put("message", "부서 추가 작업 중 알 수 없는 문제가 발생하였습니다.");
			return json.toJSONString();
		}
		*/
		
		return datas;
	}
	
	// 부서 수정 폼 요청
	@GetMapping(value = "dept-modify")
	public String modifyDept() {
		// 로그인 세션 추가하기
		return "admin/dept-modify";
	}
	
	// 부서 수정 저장 요청
	@ResponseBody
	@PutMapping(value = "/departments", produces="application/json; charset=utf-8")	
	public ResponseEntity<Object> modifyDept(@RequestBody DeptDTO deptDto) {
		// 로그인 세션 추가하기
		log.info("modifyDept(deptDto={})", deptDto);
		
		ResponseEntity<Object> datas = deptService.modifyDept(deptDto);
		
		/*
		DeptDTO data = deptService.getDeptDetail(deptDto.getDeptNo());
		data.setDeptName(deptDto.getDeptName());
		data.setDeptMngId(deptDto.getDeptMngId());
		
		boolean result = deptService.modifyDept(data);
		
		JSONObject json = new JSONObject();
		if(result) {
			json.put("title", "Success");
			json.put("message", "부서 수정이 완료 되었습니다.");
			return json.toJSONString();
		} else {
			json.put("title", "Fail");
			json.put("message", "수정 작업 중 알 수 없는 문제가 발생하였습니다.");
			return json.toJSONString();
		}
		*/
		
		// 수정 권한 없음
		return datas;
	}
	
	// 부서 삭제
	@ResponseBody
	@DeleteMapping(value ="/dept_delete", produces="application/json; charset=utf-8")
	public ResponseEntity<Object> removeDept(@RequestParam int deptNo) {
		// 로그인 세션 추가하기
		log.info("removeDept(deptNo): {}", deptNo);
		//DeptDTO data = deptService.getDeptDetail(deptNo);
		
		
		var datas = deptService.removeDept(deptNo);
		
		System.out.println(deptNo);
		
		/*
		DeptDTO data = deptService.getDeptDetail(deptNo);
		
		JSONObject json = new JSONObject();
		if(data == null) {
			// 이미 삭제된 상태
			json.put("title", "Already Deleted");
			json.put("message", "해당 부서는 이미 삭제 처리 되었습니다.");
			return json.toJSONString();
		} else {
			// 권한 조건
			// 삭제 가능
			try {
				boolean result = deptService.removeDept(deptNo);
				json.put("title", "Success");
				json.put("message", "삭제 처리가 완료 되었습니다.");
				return json.toJSONString();
			} catch (Exception e) {
				json.put("title", "Fail");
				json.put("message", "삭제 작업 중 알 수 없는 문제가 발생하였습니다.");
				return json.toJSONString();
			}
			
			
			// 삭제 권한 없음
		}
		*/
		return datas;
	}
	
}
