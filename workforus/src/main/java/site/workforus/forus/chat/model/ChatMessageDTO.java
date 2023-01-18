package site.workforus.forus.chat.model;

import lombok.*;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Alias("chatMessageDto")
public class ChatMessageDTO {
    private int chatNo;
    private String chatRoomNo;
    private String chatWriter;
    private String chatMessage;

    @Builder
    public ChatMessageDTO(String chatRoomNo, String chatWriter, String chatMessage) {
        this.chatRoomNo = chatRoomNo;
        this.chatWriter = chatWriter;
        this.chatMessage = chatMessage;
    }
}