package site.workforus.forus.chat.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import site.workforus.forus.chat.model.ChatMessageDTO;

@Controller
@RequiredArgsConstructor
public class StompChatController {
	private SimpMessagingTemplate template; // 특정 Broker로 메세지를 전달
	
	// Client가  SEND 할 수 있는 경로
	// config에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
	// "/pub"
	@MessageMapping(value="/chat/enter")
	public void enter(ChatMessageDTO message) {
		message.setChatCont(message.getEmpId() + "님이 채팅방에 입장하였습니다.");
		template.convertAndSend("/sub/chat/room/" + message.getChatRoomNo(), message);
	}
	
	@MessageMapping(value="/chat/message")
	public void message(ChatMessageDTO message) {
		template.convertAndSend("/sub/chat/room/" + message.getChatRoomNo(), message);
	}
}
