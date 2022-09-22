package site.workforus.forus.socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class Chatting extends TextWebSocketHandler{
	private Map<String, WebSocketSession> sessionMap = new HashMap<String, WebSocketSession>();
	
	// 연결 후 (세션을 적절한 컬렉션에 저장)
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		System.out.println("afterConnectionEstablished: " + session);
		
		Map<String, Object> map = session.getAttributes();
		// empId 알아내기
		String username = session.getPrincipal().getName();
		
		System.out.println(username);
		
		for(Entry<String, WebSocketSession> entry: sessionMap.entrySet()) {
			entry.getValue().sendMessage(new TextMessage(username + " 님이 접속하였습니다.\n"));
		}
		if(sessionMap.get(username) != null) {
			sessionMap.replace(username,session);
			System.out.println(sessionMap);
			System.out.println(sessionMap.get(username));
		} else {
			sessionMap.put(username, session);
			System.out.println(sessionMap);
			System.out.println(sessionMap.get(username));
		}
		super.afterConnectionEstablished(session);
	}
	
	// 메시지 수신
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {
		System.out.println("handleTextmessage: " + session + " : " + message);
		
		Map<String, Object> map = session.getAttributes();
		
		System.out.println(map);
		String username = session.getPrincipal().getName();
		
		for(Entry<String, WebSocketSession> entry: sessionMap.entrySet()) {
			WebSocketSession ws = entry.getValue();
			System.out.println(ws);
			ws.sendMessage(new TextMessage(username + " 님이 보낸 메시지\n" + message.getPayload() + "\n"));
		}
	}
	
	// 연결 종료 후
	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		Map<String, Object> map = session.getAttributes();
		
		String username = session.getPrincipal().getName();
		
		System.out.println(username);
		
		for(Entry<String, WebSocketSession> entry: sessionMap.entrySet()) {
			entry.getValue().sendMessage(new TextMessage(username + " 님이 접속을 종료하였습니다."));
		}
		sessionMap.remove(username, session);
		
		super.afterConnectionClosed(session, status);
	}
}
