package site.workforus.forus.socket;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import site.workforus.forus.employee.model.EmpDTO;
import site.workforus.forus.employee.model.LoginVO;

public class Chatting extends TextWebSocketHandler{
	
	private List<WebSocketSession> list = new ArrayList<WebSocketSession>();
	private Map<String, WebSocketSession> sessionMap = new HashMap<String, WebSocketSession>();
	
	// 연결 후 (세션을 적절한 컬렉션에 저장)
	public void afrerConnectionEstablished(WebSocketSession session) throws Exception {
		Map<String, Object> map = session.getAttributes();
		
		LoginVO loginVo = new LoginVO();
		
		String username = loginVo.getUsername();
		
		
		if(map.get(username) != null) {
			username = ((EmpDTO)map.get(username)).getEmpNm();
		}
		
		for(Entry<String, WebSocketSession> entry: sessionMap.entrySet()) {
			entry.getValue().sendMessage(new TextMessage("<p>" + username + " 님이 접속하였습니다."));
		}
		sessionMap.put(username, session);
		
		super.afterConnectionEstablished(session);
	}
	
	// 메시지 수신
	protected void handlerTextMessage(WebSocketSession session, TextMessage message) throws Exception {
		super.handleTextMessage(session, message);
		
		Map<String, Object> map = session.getAttributes();
		LoginVO loginVo = new LoginVO();
		
		String username = loginVo.getUsername();
		
		if(map.get(username) != null) {
			username = ((EmpDTO)map.get(username)).getEmpNm();
		}
		
		for(Entry<String, WebSocketSession> entry: sessionMap.entrySet()) {
			WebSocketSession ws = entry.getValue();
			ws.sendMessage(new TextMessage("<p>" + username + " 님이 보낸 메시지<br>" + message.getPayload() + "</p>"));
		}
	}
	
	// 연결 종료 후
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		Map<String, Object> map = session.getAttributes();
		
		LoginVO loginVo = new LoginVO();
		
		String username = loginVo.getUsername();
		
		System.out.println(username);
		
		if(map.get(username) != null) {
			username = ((EmpDTO)map.get(username)).getEmpNm();
		}
		
		for(Entry<String, WebSocketSession> entry: sessionMap.entrySet()) {
			entry.getValue().sendMessage(new TextMessage("<p>" + username + " 님이 접속을 종료하였습니다."));
		}
		sessionMap.remove(username, session);
		
		super.afterConnectionClosed(session, status);
	}
}
