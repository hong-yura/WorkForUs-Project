package site.workforus.forus.chat.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.chat.model.ChatRoomDTO;
import site.workforus.forus.employee.model.EmpDTO;
import site.workforus.forus.mapper.ChatMapper;
import site.workforus.forus.mapper.EmpMapper;

@Service
public class ChatService {
	
	private Map<String, ChatRoomDTO> chatRoomDTOMap;
	
	@Autowired
	private SqlSession session;
	
	private void init() {
		chatRoomDTOMap = new LinkedHashMap<>(); 
	}
	
	// 사원 목록 조회
	public List<EmpDTO> selectEmployeeAll() {
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		
		List<EmpDTO> result = mapper.selectEmployeeAll();
		
		return result;
	}

	// 채팅방 생성
	public void createChatRoom(String username) {
		ChatMapper mapper = session.getMapper(ChatMapper.class);
		
		System.out.println(username);
		
		int result = mapper.createChatRoom(username);
	}

	// 채팅방 목록 조회
//	public List<ChatRoomDTO> selectChatRooms(String username) {
//		ChatMapper mapper = session.getMapper(ChatMapper.class);
//		
//		List<ChatRoomDTO> chatRoom = mapper.selectChatRooms(username);
//		
//		return chatRoom;
//	}
	
	public List<ChatRoomDTO> selectChatRooms() {
		ChatMapper mapper = session.getMapper(ChatMapper.class);
		
		List<ChatRoomDTO> chatRoom = mapper.selectChatRooms();
		
		return chatRoom;
	}
	

	// 채팅방 들어가기
	public ChatRoomDTO findRoomById(int id) {
		ChatMapper mapper = session.getMapper(ChatMapper.class);
		
		ChatRoomDTO chatRoomDto = mapper.findRoomById(id);
		
		System.out.println(chatRoomDto);
		
		return chatRoomDto;
	}
	
	
}
