package site.workforus.forus.board.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.board.model.PostCommentDTO;
import site.workforus.forus.employee.model.EmpDTO;
import site.workforus.forus.mapper.BoardPostMapper;
import site.workforus.forus.mapper.EmpMapper;


@Service
public class PostCommentService {
	
	@Autowired
	private SqlSession session;
	
	private static final Logger logger = LoggerFactory.getLogger(PostCommentService.class);

	// 댓글 가져오기
	public List<PostCommentDTO> selectAllComment(int postId) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		List<PostCommentDTO> data = mapper.selectAllPostComment(postId);
		
		return data;
	}
	
	// 댓글 하나 가져오기
	public PostCommentDTO selectComment(int commentId) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		PostCommentDTO data = mapper.selectPostComment(commentId);
		
		return data;
	}

	// 댓글 갯수
	public int selectCommentCount(int postId) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		int commentCnt = mapper.selectCommentCount(postId);
		return commentCnt;
	}

	// 댓글 추가
	public boolean addComment(PostCommentDTO commentDto) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		int result = mapper.insertComment(commentDto);
		logger.info("addComment(commentDto={})", commentDto);
		logger.info("addComment(result={})", result);
		return result==1? true : false;
		
	}

	// 게시글 관련 모든 댓글 삭제 -> 게시글 삭제 할 때 필요
	public boolean deleteAllComment(int postId) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		int result = mapper.deleteAllComment(postId);
		return result == 1 ? true : false;
	}
	
	// 본댓 + 대댓 삭제
	public boolean deleteComment(int commentId) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		// 만약 commentId의 depth=1이라면 deleteSecComment()를 실행
		PostCommentDTO commentDto = mapper.selectPostComment(commentId);
		logger.info("deleteComment(commentId={}, commentDto={})", commentId, commentDto);
		if(commentDto.getDepth() == 0) { // 본댓글이라는 것 -> 본댓, 대댓 삭제
			HashMap<String, Integer> data = new HashMap<String, Integer>();
			data.put("postId", commentDto.getPostId() );
			data.put("groupNo", commentDto.getGroupNo());
			
			int result = mapper.deleteComment(data);
			if(result >= 1) {
				return true;
			}else {
				return false;
			}
		}else { // 만약 depth = 1이라면 대댓이라는 것
			return deleteSecComment(commentId); // 대댓만 삭제
			
		}
		
	}
	
	
	// 대댓 삭제 
	public boolean deleteSecComment(int commentId) {
		logger.info("deleteSecComment(commentId={})", commentId);
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		int result = mapper.deleteSecComment(commentId);
		logger.info("deleteSecComment(deleSecComment result={})", result);
		return result == 1? true:false;
	}

	// 객체를 가지고 와야 한다. 
	public EmpDTO selectEmpDto(String empId) {
		EmpDTO empDto = new EmpDTO(); 
		empDto.setEmpId(empId);
		EmpMapper mapper = session.getMapper(EmpMapper.class);
		EmpDTO data = mapper.selectEmployee(empDto);
		return data;
	}
	
}
