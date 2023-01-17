package site.workforus.forus.mapper;

import site.workforus.forus.chat.model.ChatMessageDTO;
import site.workforus.forus.chat.model.ChatParticipantDTO;
import site.workforus.forus.chat.model.ChatRoomDTO;

import java.util.List;

public interface ChatMapper {
    public int insertChatRoom(ChatRoomDTO chatRoomDTO);

    public List<ChatRoomDTO> findRoomAll();

    public ChatRoomDTO findByRoomId(String roomId);

    public int insertRoomDatas(ChatMessageDTO message);

    public List<ChatMessageDTO> findMessageById(String roomId);

    public ChatRoomDTO findByRoomNo(String chatTitle);

    public int insertChatParticipants(ChatParticipantDTO chatParticipantDTO);
}
