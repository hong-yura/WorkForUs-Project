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

import site.workforus.forus.calendar.model.CalendarDTO;
import site.workforus.forus.global.dto.ResponseDTO;
import site.workforus.forus.mapper.CalendarMapper;

@Service
public class CalendarService {

	@Autowired
	private SqlSession session;

	public ResponseEntity<Object> addCalendar(CalendarDTO calendarDTO) {

		CalendarMapper mapper = session.getMapper(CalendarMapper.class);

		boolean insertResult = mapper.insertData(calendarDTO);

		ResponseDTO<CalendarDTO> result;

		ResponseEntity<Object> res;

		if (insertResult) {
			result = new ResponseDTO<>("SUCCESS", 200, calendarDTO);
			res = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result = new ResponseDTO<>("FAIL", 400, "캘린더 생성에 실패하였습니다.", calendarDTO);
			res = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return res;

	}

	public ResponseEntity<Object> selectAll() {

		CalendarMapper mapper = session.getMapper(CalendarMapper.class);

		List<CalendarDTO> datas = mapper.selectAll();

		var result = new ResponseDTO<List<CalendarDTO>>("SUCCESS", 200, datas);

		ResponseEntity<Object> res = new ResponseEntity<>(result, HttpStatus.OK);

		return res;
	}

	public ResponseEntity<Object> selectByCalId(int calId) {

		CalendarMapper mapper = session.getMapper(CalendarMapper.class);

		List<CalendarDTO> datas = mapper.selectByCalId(calId);

		ResponseDTO<List<CalendarDTO>> result = new ResponseDTO<List<CalendarDTO>>("SUCCESS", 200, datas);

		ResponseEntity<Object> res = new ResponseEntity<>(result, HttpStatus.OK);

		return res;
	}

	public ResponseEntity<Object> selectByEmpId(String empId) {
		CalendarMapper mapper = session.getMapper(CalendarMapper.class);

		List<CalendarDTO> datas = mapper.selectByEmpId(empId);

		ResponseDTO<List<CalendarDTO>> result = new ResponseDTO<List<CalendarDTO>>("SUCCESS", 200, datas);

		ResponseEntity<Object> res = new ResponseEntity<>(result, HttpStatus.OK);

		return res;
	}

	public ResponseEntity<Object> selectRecendDate(String empId) {
		CalendarMapper mapper = session.getMapper(CalendarMapper.class);

		List<CalendarDTO> datas = mapper.selectRecentData(empId);

		ResponseDTO<List<CalendarDTO>> result = new ResponseDTO<>("SUCCESS", 200, datas);

		ResponseEntity<Object> res = new ResponseEntity<>(result, HttpStatus.OK);

		return res;
	}

	public ResponseEntity<Object> updateCalendar(CalendarDTO calendarDTO) {
		CalendarMapper mapper = session.getMapper(CalendarMapper.class);

		boolean updateResult = mapper.updateData(calendarDTO);

		ResponseDTO<CalendarDTO> result;

		ResponseEntity<Object> res;

		if (updateResult) {
			result = new ResponseDTO<>("SUCCESS", 200, calendarDTO);
			res = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result = new ResponseDTO<>("FAIL", 400, "일정 수정에 실패하였습니다.", calendarDTO);
			res = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return res;
	}

	public ResponseEntity<Object> deleteCalendar(int calId) {
		CalendarMapper mapper = session.getMapper(CalendarMapper.class);

		boolean deleteResult = mapper.deleteById(calId);

		Map<String, Integer> map = new HashMap<>();

		map.put("calId", calId);

		JSONObject json = new JSONObject(map);

		ResponseDTO<JSONObject> result;

		ResponseEntity<Object> res;

		if (deleteResult) {
			result = new ResponseDTO<>("SUCCESS", 200, json);
			res = new ResponseEntity<>(result, HttpStatus.OK);
		} else {
			result = new ResponseDTO<>("FAIL", 400, "캘린더 삭제에 실패하였습니다.", json);
			res = new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
		}

		return res;
	}
}
