package site.workforus.forus.mapper;

import java.util.List;
import site.workforus.forus.chat.model.ChatRoomDTO;

public interface ChatMapper {
	public int createChatRoom(String username);

	public List<ChatRoomDTO> selectChatRooms();

	public ChatRoomDTO findRoomById(int id);
}
