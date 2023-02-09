package site.workforus.forus.board.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

import java.time.LocalDate;
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

    public PostVisitDTO(){}
    public PostVisitDTO(int visitId, int postId, String empId, LocalDateTime lastVisit){
        this.visitId = visitId;
        this.postId = postId;
        this.empId = empId;
        this.lastVisit = lastVisit;
    }
    public PostVisitDTO(int postId, String empId, LocalDateTime lastVisit){
        this.postId = postId;
        this.empId = empId;
        this.lastVisit = lastVisit;
    }

    public PostVisitDTO setAll(int postId, String empId, LocalDateTime lastVisit){
        PostVisitDTO data = new PostVisitDTO(postId,empId, lastVisit);
        return data;
    }

}
