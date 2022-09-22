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

import site.workforus.forus.calendar.model.CalendarShareDTO;
import site.workforus.forus.global.dto.ResponseDTO;
import site.workforus.forus.mapper.CalendarShareMapper;

@Service
public class CalendarShareService {

	@Autowired
	private SqlSession session;

	public ResponseEntity<Object> addCalendarShare(CalendarShareDTO calendarShareDTO) {

		CalendarShareMapper mapper = session.getMapper(CalendarShareMapper.class);

		boolean insertResult = mapper.insertData(calendarShareDTO);

		ResponseDTO<CalendarShareDTO> result;

		ResponseEntity<Object> res;

		if (insertResult) {
			result = new ResponseDTO<>("SUCCESS", 200, calendarShareDTO);
			res = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result = new ResponseDTO<>("FAIL", 400, "캘린더 공유에 실패하였습니다.", calendarShareDTO);
			res = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return res;
	}

	public ResponseEntity<Object> selectAll() {

		CalendarShareMapper mapper = session.getMapper(CalendarShareMapper.class);

		List<CalendarShareDTO> datas = mapper.selectAll();

		ResponseDTO<List<CalendarShareDTO>> result = new ResponseDTO<>("SUCCESS", 200, datas);

		ResponseEntity<Object> res = new ResponseEntity<>(result, HttpStatus.OK);

		return res;
	}

	public ResponseEntity<Object> selectById(int calShrId) {

		CalendarShareMapper mapper = session.getMapper(CalendarShareMapper.class);

		List<CalendarShareDTO> datas = mapper.selectById(calShrId);

		ResponseDTO<List<CalendarShareDTO>> result = new ResponseDTO<>("SUCCESS", 200, datas);

		ResponseEntity<Object> res = new ResponseEntity<>(result, HttpStatus.OK);

		return res;
	}

	public ResponseEntity<Object> selectByCalId(int calId) {
		CalendarShareMapper mapper = session.getMapper(CalendarShareMapper.class);

		List<CalendarShareDTO> datas = mapper.selectByCalId(calId);

		ResponseDTO<List<CalendarShareDTO>> result = new ResponseDTO<>("SUCCESS", 200, datas);

		ResponseEntity<Object> res = new ResponseEntity<>(result, HttpStatus.OK);

		return res;
	}

	public ResponseEntity<Object> selectByEmpIdAndCalId(String empId, int calId) {
		CalendarShareMapper mapper = session.getMapper(CalendarShareMapper.class);

		List<CalendarShareDTO> datas = mapper.selectByEmpIdAndCalId(empId, calId);

		ResponseDTO<List<CalendarShareDTO>> result = new ResponseDTO<>("SUCCESS", 200, datas);

		ResponseEntity<Object> res = new ResponseEntity<>(result, HttpStatus.OK);

		return res;
	}

	public ResponseEntity<Object> selectJoinCalByEmpId(String empId) {
		CalendarShareMapper mapper = session.getMapper(CalendarShareMapper.class);

		List<CalendarShareDTO> datas = mapper.selectJoinCalByEmpId(empId);

		ResponseDTO<List<CalendarShareDTO>> result = new ResponseDTO<>("SUCCESS", 200, datas);

		ResponseEntity<Object> res = new ResponseEntity<>(result, HttpStatus.OK);

		return res;
	}

	public ResponseEntity<Object> deleteCalendarShare(int calShrId) {
		CalendarShareMapper mapper = session.getMapper(CalendarShareMapper.class);

		boolean deleteResult = mapper.deleteById(calShrId);

		Map<String, Integer> map = new HashMap<>();

		map.put("calShrId", calShrId);

		JSONObject json = new JSONObject(map);

		ResponseDTO<JSONObject> result;

		ResponseEntity<Object> res;

		if (deleteResult) {
			result = new ResponseDTO<>("SUCCESS", 200, json);
			res = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result = new ResponseDTO<>("FAIL", 400, "캘린더 공유 삭제에 실패하였습니다.", json);
			res = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return res;
	}

}
