package site.workforus.forus.chat.model;

import java.sql.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ChatMessageDTO {
	private int chatNo;
	private int chatRoomNo;
	private String empId;
	private String chatCont;
	private Date chatSendDt;
	private String chatType;
	private int chatNoRead;
}
