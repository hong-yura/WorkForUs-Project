package site.workforus.forus.mapper;

import java.util.List;
import site.workforus.forus.chat.model.ChatRoomDTO;

public interface ChatMapper {
	public int insertChatRoom(String username);

	public List<ChatRoomDTO> selectChatRoom(String username);
}
