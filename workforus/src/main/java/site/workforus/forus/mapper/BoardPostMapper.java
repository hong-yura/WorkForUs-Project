package site.workforus.forus.mapper;

import java.util.List;

import site.workforus.forus.board.model.BoardPostDTO;
import site.workforus.forus.board.model.PostCommentDTO;

public interface BoardPostMapper { 

	// 게시글 가져오기
	public List<BoardPostDTO> selectPostAll(int boardId);
	
	// 검색한 게시글 가져오기
	public List<BoardPostDTO> selectSearchData(int boardId, String search); // 특정 단어로 검색한 경우 -> 한 개가 아닐 수도 있음 
	
	// 게시글 상세
	public BoardPostDTO selectBoardDetail(int postId);
	
	// 댓글 가져오기
	public List<PostCommentDTO> selectPostComment(int postId);
	
	// 댓글 갯수
	public int selectCommentCount(int postId);
	
	// 게시글 추가  
	public int insertBoardPost(BoardPostDTO boardPostDto);
	
	// 게시글 수정
	public int updateBoardPost(BoardPostDTO boardPostDto);
	
	// 게시글 삭제
	public int deleteBoardPost(int postId);

	// 게시글 갯수 가져오기
	public int generalPostCount(int boardId);
	

}
