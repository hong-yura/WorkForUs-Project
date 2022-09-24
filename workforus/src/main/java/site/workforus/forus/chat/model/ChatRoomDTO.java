package site.workforus.forus.chat.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.apache.ibatis.type.Alias;
import org.springframework.web.socket.WebSocketSession;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import oracle.sql.DATE;

@Getter
@Setter
@ToString
@Alias("chatRoomDto")
public class ChatRoomDTO {
	private int chatRoomNo;
	private String empId;
	private String chatTitle;
	private String chatProfile;
	private String chatLastTime;
	private String chatLastCont;
	private String ChatRoomType;
	private Set<WebSocketSession> sessions = new HashSet<>();
}
