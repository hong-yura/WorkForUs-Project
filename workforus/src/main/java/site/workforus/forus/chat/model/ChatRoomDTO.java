package site.workforus.forus.chat.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("chatRoomDto")
public class ChatRoomDTO {
    private String chatRoomNo;
    private String chatTitle;

    public ChatRoomDTO() {}

    @Builder
    public ChatRoomDTO(String chatRoomNo, String chatTitle) {
        this.chatRoomNo = chatRoomNo;
        this.chatTitle =chatTitle;
    }
}
