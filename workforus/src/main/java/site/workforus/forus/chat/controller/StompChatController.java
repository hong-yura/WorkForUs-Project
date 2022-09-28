/*
package site.workforus.forus.chat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;

import lombok.RequiredArgsConstructor;
import site.workforus.forus.chat.model.ChatMessageDTO;
import site.workforus.forus.employee.model.LoginVO;

@Controller
@RequiredArgsConstructor
public class StompChatController {
	
	@Autowired
	private SimpMessagingTemplate template; // 특정 Broker로 메세지를 전달
	
	// Client가  SEND 할 수 있는 경로
	// config에서 설정한 applicationDestinationPrefixes와 @MessageMapping 경로가 병합됨
	// "/pub"
	@MessageMapping(value="/chat/enter")
	public void enter(ChatMessageDTO message, Authentication auth) {
		LoginVO loginVo = (LoginVO) auth.getPrincipal();
		message.setChatCont(loginVo.getUsername() + "님이 채팅방에 입장하였습니다.");
		System.out.println(message.getChatCont());
		template.convertAndSend("/sub/chat/room/" + message.getChatRoomNo(), message);
	}
	
	@MessageMapping(value = "/chat/message")
    public void message(ChatMessageDTO message){
        template.convertAndSend("/sub/chat/room/" + message.getChatRoomNo(), message);
    }
 */
//	@MessageMapping(value="/chat/message") // /pub/chat/message
//	@SendTo("/sub/roomId") // 전달하려는 곳
//	public ChatMessageDTO message(ChatMessageDTO message) throws Exception {
//		System.out.println("message: " + message.getChatCont());
//		return message;
////		template.convertAndSend("/sub/chat/room/" + message.getChatRoomNo(), message);
//	}
//	
//	@MessageMapping("/out") // /pub/chat/meesage
//	@SendTo("/sub/out")
//	public String outroom(String message) throws Exception {
//		System.out.println("out message: " + message);
//		return message;
//	}
//	
//	@MessageMapping("/in") // /pub/chat/meesage
//	@SendTo("/sub/in")
//	public String inroom(String message) throws Exception {
//		System.out.println("in message: " + message);
//		return message;
//	}
	
	
//}
