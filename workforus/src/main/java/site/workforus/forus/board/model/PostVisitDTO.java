package site.workforus.forus.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.time.LocalDateTime;

@ToString
@Getter
@Setter
@Alias("postVisitDto")
public class PostVisitDTO {
    private int visitId;
    private int postId;                 // 게시글ID
    private String empId;               // 회원ID
    private LocalDateTime lastVisit;    // 방문날짜

}
