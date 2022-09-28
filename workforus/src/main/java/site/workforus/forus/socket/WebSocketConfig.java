package site.workforus.forus.socket;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.RequiredArgsConstructor;

// Websocket을 활성화하기 위한 config
@Configuration
// @EnableWebSocketMessageBroker
@EnableWebSocket
//public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
public class WebSocketConfig implements WebSocketConfigurer {
	
	@Autowired
	private Chatting chatting;
	
	@Override
	public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
		registry.addHandler(chatting, "stomp/chat").setAllowedOrigins("*");
		
	}
	/*
	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		registry.addEndpoint("/stomp/chat") // WebSocket이 웸소켓 핸드셰이크 커넥션을 생셩할 경로
				.setAllowedOrigins("http://localhost");
	}
	
	@Override
	public void configureMessageBroker(MessageBrokerRegistry config) {
		config.setApplicationDestinationPrefixes("/pub"); // Client 에서 send 요청 처리
		config.enableSimpleBroker("/sub"); // 해당 경로로 SimpleBroker를 등록. SimpleBroker는 해당하는 경로를 SUBSCRIBE하는 Client에게 메세지를 전달하는 간단한 작업을 수행
	}
	*/
}
