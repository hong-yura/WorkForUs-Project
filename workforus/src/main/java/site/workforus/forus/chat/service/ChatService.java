package site.workforus.forus.chat.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.chat.model.ChatRoomDTO;
import site.workforus.forus.employee.model.EmpDTO;
import site.workforus.forus.mapper.ChatMapper;
import site.workforus.forus.mapper.EmpMapper;

@Service
public class ChatService {
	
	@Autowired
	private SqlSession session;
	
	public List<EmpDTO> selectEmployeeAll() {
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		
		List<EmpDTO> result = mapper.selectEmployeeAll();
		
		return result;
	}

	public void insertChatRoom(String username) {
		ChatMapper mapper = session.getMapper(ChatMapper.class);
		
		System.out.println(username);
		
		int result = mapper.insertChatRoom(username);
		
		System.out.println(result);
	}

	public List<ChatRoomDTO> selectChatRoom(String username) {
		ChatMapper mapper = session.getMapper(ChatMapper.class);
		
		List<ChatRoomDTO> chatRoom = mapper.selectChatRoom(username);
		
		return chatRoom;
	}
}
