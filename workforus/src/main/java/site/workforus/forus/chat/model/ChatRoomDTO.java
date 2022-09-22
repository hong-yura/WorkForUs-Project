package site.workforus.forus.chat.model;

import org.apache.ibatis.type.Alias;

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
}
