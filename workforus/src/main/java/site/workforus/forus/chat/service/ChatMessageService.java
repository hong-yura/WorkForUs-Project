package site.workforus.forus.chat.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import site.workforus.forus.chat.model.ChatMessageDTO;
import site.workforus.forus.mapper.ChatMapper;

import java.util.List;

@Service
public class ChatMessageService {

    @Autowired
    private SqlSession session;

    public boolean insertRoomDatas(ChatMessageDTO message) {
        ChatMapper mapper = session.getMapper(ChatMapper.class);
        int result = mapper.insertRoomDatas(message);
        return result == 1 ? true : false;
    }

    public List<ChatMessageDTO> findMessageById(String roomId) {
        ChatMapper mapper = session.getMapper((ChatMapper.class));
        List<ChatMessageDTO> chatMessageDTO = mapper.findMessageById(roomId);

        System.out.println(chatMessageDTO);

        return chatMessageDTO;
    }
}
