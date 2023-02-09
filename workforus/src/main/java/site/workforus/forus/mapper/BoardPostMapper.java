package site.workforus.forus.mapper;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import site.workforus.forus.board.model.*;

public interface BoardPostMapper { 

	// 게시글 가져오기
	public List<BoardPostDTO> selectPostAll(int boardId);
	
	// 검색한 게시글 가져오기
	public List<BoardPostDTO> selectSearchData(HashMap<String, Object> searchData); // 특정 단어로 검색한 경우 -> 한 개가 아닐 수도 있음 
	
	// 게시글 상세
	public BoardPostDTO selectBoardDetail(int postId);
	
	// 댓글 가져오기
	public List<PostCommentDTO> selectAllPostComment(int postId);

	// 댓글 하나 가져오기
	public PostCommentDTO selectPostComment(int commentId);

	
	// 댓글 갯수
	public int selectCommentCount(int postId);
	
	// 게시글 추가  
	public int insertBoardPost(BoardPostDTO boardPostDto);
	
	// postId 가져오기 -> 다음 시퀀스
	public int selectPostId();
	
	// 게시글 파일 업로드
	public int insertPostUploadFile(PostUploadFileDTO fileData);
		
	// 게시글 수정
	public int updateBoardPost(BoardPostDTO boardPostDto);
	
	// 게시글 삭제
	public int deleteBoardPost(int postId);

	// 게시글 갯수 가져오기
	public int generalPostCount(int boardId);

	// 게시글 현재 Id 찾기
	public int selectCurrentPostId(int boardId);

	// 게시글 업로드 파일 가져오기
	public List<PostUploadFileDTO> selectFiles(int postId);

	// 게시글 마지막 groupNo 가져오기
	public Integer selectGroupNo(int postId);

	// 게시글 댓글 추가
	public int insertComment(PostCommentDTO commentDto);

	// sort 최댓값 구하기
	public Integer selectMaxSort(HashMap<String, Integer> data);


	// 파일 삭제
	public int deleteFile(int postId);
	
	// 모든 댓글 삭제
	public int deleteAllComment(int postId);

	// 대댓 + 본댓 삭제
	public int deleteComment(HashMap<String, Integer> data);

	// 대댓 삭제
	public int deleteSecComment(int commentId);

	// 공지 아닌 게시글
	public List<BoardPostDTO> selectNotNoticeList(int bId);

	public List<BoardPostDTO> selectNotNoticeSearchList(HashMap<String, Object> search);

	// update likeCnt(tb_board_posts)
	public int updateLikeCnt(BoardPostDTO postDto);

	// like 기록 조회(tb_post_likes) @Param : 파라미터명을 그대로 사용하기 위함
    public List<PostLikeDTO> selectLikeByPostIdAndUserId(@Param("postId")int postId, @Param("empId") String empId);

	// like 기록 추가(tb_post_likes)
	public int insertLike(@Param("postId") int postId, @Param("empId") String empId); // action : up or down

	// like 기록 삭제(tb_post_likes)
	public int deleteLike(@Param("postId") int postId, @Param("empId") String empId);


	// likeCnt 데이터 가져오기(tb_board_posts)
	public BoardPostDTO selectLikeCntByPostId(int postId);

	// visit 데이터 가져오기(tb_post_visits)
	public PostVisitDTO selectVisitByPostIdAndEmpId(@Param("postId") int postId, @Param("empId") String empId);

	// visit 데이터 추가
	public int insertVisit(@Param("postId")int postId, @Param("empId")String empId, @Param("lastVisit")LocalDateTime lastVisit);

	// visit 시각 업데이트
	public int updateVisit(PostVisitDTO visitDto);

	// tb_board_posts 테이블의 view_cnt 수정
	public int updatePostViewCnt(BoardPostDTO boardPostDTO);
}
