package site.workforus.forus.board.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.ibatis.type.Alias;

@ToString
@Getter
@Setter
@Alias("postLikeDto")
public class PostLikeDTO {
    private int likeId;
    private int postId;
    private String empId;

    public PostLikeDTO() {}
    public PostLikeDTO(int likeId, int postId, String empId){
        this.likeId = likeId;
        this.postId = postId;
        this.empId = empId;
    }
}
