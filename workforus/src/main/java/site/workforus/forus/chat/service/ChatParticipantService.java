package site.workforus.forus.chat.service;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import site.workforus.forus.chat.model.ChatParticipantDTO;
import site.workforus.forus.mapper.ChatMapper;

import java.util.List;

@Service
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class ChatParticipantService {

    @Autowired
    private SqlSession session;

    @Transactional(readOnly = true)
    public boolean insertChatParticipants(List<String> memberList, String chatRoomNo) {
        ChatMapper mapper = session.getMapper(ChatMapper.class);

        ChatParticipantDTO chatParticipantDTO = new ChatParticipantDTO();

        int result = 0;
        for(String member : memberList) {
            chatParticipantDTO.setEmpId(member);
            chatParticipantDTO.setChatRoomNo(chatRoomNo);
            result = mapper.insertChatParticipants(chatParticipantDTO);
        }

        return result == 1 ? true : false;
    }
}
