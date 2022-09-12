package site.workforus.forus.calendar.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import site.workforus.forus.calendar.model.ScheduleDTO;
import site.workforus.forus.global.dto.ResponseDTO;
import site.workforus.forus.mapper.ScheduleMapper;

@Service
public class ScheduleService {

	@Autowired
	private SqlSession session;

	public ResponseEntity<Object> addSchedule(ScheduleDTO scheduleDTO) {

		ScheduleMapper mapper = session.getMapper(ScheduleMapper.class);

		boolean insertResult = mapper.insertData(scheduleDTO);

		ResponseDTO<ScheduleDTO> result;

		ResponseEntity<Object> res;

		if (insertResult) {
			result = new ResponseDTO<>("SUCCESS", 200, scheduleDTO);
			res = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result = new ResponseDTO<>("FAIL", 400, "일정 생성에 실패하였습니다.", scheduleDTO);
			res = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return res;
	}

	public ResponseEntity<Object> selectAll() {

		ScheduleMapper mapper = session.getMapper(ScheduleMapper.class);

		List<ScheduleDTO> datas = mapper.selectAll();

		var result = new ResponseDTO<List<ScheduleDTO>>("SUCCESS", 200, datas);

		ResponseEntity<Object> res = new ResponseEntity<>(result, HttpStatus.OK);

		return res;
	}

	public ResponseEntity<Object> selectByEmpId(String empId) {

		ScheduleMapper mapper = session.getMapper(ScheduleMapper.class);

		List<ScheduleDTO> datas = mapper.selectAllByEmpId(empId);

		ResponseDTO<List<ScheduleDTO>> result = new ResponseDTO<List<ScheduleDTO>>("SUCCESS", 200, datas);

		ResponseEntity<Object> res = new ResponseEntity<>(result, HttpStatus.OK);

		return res;
	}

	public ResponseEntity<Object> selectByCalId(int calId) {

		ScheduleMapper mapper = session.getMapper(ScheduleMapper.class);

		List<ScheduleDTO> datas = mapper.selectAllByCalId(calId);

		ResponseDTO<List<ScheduleDTO>> result = new ResponseDTO<List<ScheduleDTO>>("SUCCESS", 200, datas);

		ResponseEntity<Object> res = new ResponseEntity<>(result, HttpStatus.OK);

		return res;
	}

	public ResponseEntity<Object> selectByScheduleId(int scheId) {

		ScheduleMapper mapper = session.getMapper(ScheduleMapper.class);

		List<ScheduleDTO> datas = mapper.selectByScheduleId(scheId);

		ResponseDTO<List<ScheduleDTO>> result = new ResponseDTO<List<ScheduleDTO>>("SUCCESS", 200, datas);

		ResponseEntity<Object> res = new ResponseEntity<>(result, HttpStatus.OK);

		return res;
	}

	public ResponseEntity<Object> updateSchedule(ScheduleDTO scheduleDTO) {

		ScheduleMapper mapper = session.getMapper(ScheduleMapper.class);

		boolean updateResult = mapper.updateData(scheduleDTO);

		ResponseDTO<ScheduleDTO> result;

		ResponseEntity<Object> res;

		if (updateResult) {
			result = new ResponseDTO<>("SUCCESS", 200, scheduleDTO);
			res = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result = new ResponseDTO<>("FAIL", 400, "일정 수정에 실패하였습니다.", scheduleDTO);
			res = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return res;
	}

	public ResponseEntity<Object> deleteSchedule(int scheId) {
		ScheduleMapper mapper = session.getMapper(ScheduleMapper.class);

		boolean deleteResult = mapper.deleteById(scheId);

		Map<String, Integer> map = new HashMap<>();

		map.put("scheduleId", scheId);

		JSONObject json = new JSONObject(map);

		ResponseDTO<JSONObject> result;

		ResponseEntity<Object> res;

		if (deleteResult) {
			result = new ResponseDTO<>("SUCCESS", 200, json);
			res = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result = new ResponseDTO<>("FAIL", 400, "일정 삭제에 실패하였습니다.", json);
			res = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return res;
	}

	public ResponseEntity<Object> deleteScheduleByCalId(int calId) {
		ScheduleMapper mapper = session.getMapper(ScheduleMapper.class);

		boolean deleteResult = mapper.deleteAllByCalId(calId);

		Map<String, Integer> map = new HashMap<>();

		map.put("calId", calId);

		JSONObject json = new JSONObject(map);

		ResponseDTO<JSONObject> result;

		ResponseEntity<Object> res;

		if (deleteResult) {
			result = new ResponseDTO<>("SUCCESS", 200, json);
			res = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result = new ResponseDTO<>("FAIL", 400, "일정 삭제에 실패하였습니다.", json);
			res = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return res;
	}
}
