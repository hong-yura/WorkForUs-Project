package site.workforus.forus.chat.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import site.workforus.forus.chat.model.ChatParticipantDTO;
import site.workforus.forus.chat.model.ChatRoomDTO;
import site.workforus.forus.employee.model.EmpDTO;
import site.workforus.forus.mapper.ChatMapper;
import site.workforus.forus.mapper.EmpMapper;

import java.util.List;
import java.util.UUID;

@Service
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class ChatRoomService {

    @Autowired
    private SqlSession session;

    public boolean createChatRoom(String chatTitle) {
        ChatMapper mapper = session.getMapper(ChatMapper.class);

        ChatRoomDTO chatRoomDTO = new ChatRoomDTO();

        chatRoomDTO.setChatRoomNo(UUID.randomUUID().toString());
        chatRoomDTO.setChatTitle(chatTitle);

        int result = mapper.insertChatRoom(chatRoomDTO);

        return result == 1 ? true : false;
    }

    public List<ChatRoomDTO> findRoomAll() {
        ChatMapper mapper = session.getMapper(ChatMapper.class);

        List<ChatRoomDTO> chatRoomDTO = mapper.findRoomAll();

        return chatRoomDTO;
    }

    public List<EmpDTO> selectEmployeeAll() {
        EmpMapper mapper = session.getMapper(EmpMapper.class);

        List<EmpDTO> result = mapper.selectEmployeeAll();

        return result;
    }

    public ChatRoomDTO findByRoomId(String roomId) {
        ChatMapper mapper = session.getMapper(ChatMapper.class);

        ChatRoomDTO chatRoomDTO = mapper.findByRoomId(roomId);

        return chatRoomDTO;
    }

    public EmpDTO selectEmployeeInfo(String user) {
        EmpMapper mapper = session.getMapper(EmpMapper.class);

        EmpDTO empDTO = mapper.selectEmployeeInfo(user);

        return empDTO;
    }

    public String findByRoomNo(String chatTitle) {
        ChatMapper mapper = session.getMapper(ChatMapper.class);

        ChatRoomDTO chatRoomDTO = mapper.findByRoomNo(chatTitle);
        String chatRoomNo = chatRoomDTO.getChatRoomNo();

        return chatRoomNo;
    }
}
