package site.workforus.forus.chat.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@Getter
@Setter
@ToString
@Alias("chatParticipantDto")
public class ChatParticipantDTO {

    private String empId;

    private String chatRoomNo;

    public ChatParticipantDTO() {}

    @Builder
    public ChatParticipantDTO(String empId, String chatRoomNo) {
        this.empId = empId;
        this.chatRoomNo = chatRoomNo;
    }
}
