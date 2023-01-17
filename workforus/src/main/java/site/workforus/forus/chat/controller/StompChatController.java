package site.workforus.forus.chat.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import site.workforus.forus.chat.model.ChatMessageDTO;
import site.workforus.forus.chat.service.ChatMessageService;

@Controller
@RequiredArgsConstructor
public class StompChatController {

    private final SimpMessagingTemplate template;

    @Autowired
    private ChatMessageService chatMessageService;

    @MessageMapping(value = "/chat/enter")
    public void enter(ChatMessageDTO message) {
        System.out.println("enter connection");
        System.out.println(message.getChatWriter());
        // message.setChatMessage(message.getChatWriter() + " 님이 채팅방에 입장하였습니다.");
//        template.convertAndSend("/sub/chat/room/" + message.getChatRoomNo() , message);
    }

    @MessageMapping(value = "/chat/message")
    public void message(ChatMessageDTO message) {
        System.out.println("message connection");
        System.out.println(message);
        chatMessageService.insertRoomDatas(message);
        template.convertAndSend("/sub/chat/room/" + message.getChatRoomNo(), message);
    }
}
