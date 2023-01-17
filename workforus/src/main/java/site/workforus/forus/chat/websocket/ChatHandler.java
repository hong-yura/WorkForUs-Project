package site.workforus.forus.chat.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
public class ChatHandler extends TextWebSocketHandler {
    private List<WebSocketSession> sessionList = new ArrayList<WebSocketSession>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("#ChattingHandler, afterConnectionEstablished");
        sessionList.add(session);

        log.info(session.getPrincipal().getName() + "님이 입장하셨습니다.");
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info("#ChattingHandler, handleMessage");

        log.info(session.getId() + ": " + message);

        String payload = message.getPayload();
        log.info("payload : " + payload);

        log.info("session : " + session);
        log.info("message : " + message);

        for(WebSocketSession s : sessionList) {
            s.sendMessage(new TextMessage(session.getPrincipal().getName() + ":" + message.getPayload()));
        }
    }


    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        log.info("#ChattingHandler, afterConnectionClosed");

        log.info("status : " + status);
        log.info("session : " + session);

        sessionList.remove(session);

        log.info(session.getPrincipal().getName() + "님이 퇴장하셨습니다.");
    }
}
