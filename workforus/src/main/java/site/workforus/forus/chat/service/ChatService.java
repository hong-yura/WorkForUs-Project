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
	
	public List<EmpDTO> selectEmployeeAll() {
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		
		List<EmpDTO> result = mapper.selectEmployeeAll();
		
		return result;
	}

	public ChatRoomDTO insertChatRoom(String username) {
		ChatMapper mapper = session.getMapper(ChatMapper.class);
		
		System.out.println(username);
		
		int result = mapper.insertChatRoom(username);
		
		ChatRoomDTO room = ChatRoomDTO.create(username);
		chatRoomDTOMap.put(room.getChatRoomNo(), room);
		
		System.out.println(result);
		
		return room;
	}

	public List<ChatRoomDTO> selectChatRoom(String username) {
		ChatMapper mapper = session.getMapper(ChatMapper.class);
		
		List<ChatRoomDTO> chatRoom = mapper.selectChatRoom(username);
		
		return chatRoom;
	}
	
	public ChatRoomDTO findRoomById(String id) {
		return chatRoomDTOMap.get(id);
	}
	
}
