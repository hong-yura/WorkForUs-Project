package site.workforus.forus.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.board.model.PostCommentDTO;
import site.workforus.forus.mapper.BoardPostMapper;


@Service
public class PostCommentService {
	
	@Autowired
	private SqlSession session; 
	
	private static final Logger logger = LoggerFactory.getLogger(PostCommentService.class);

	public List<PostCommentDTO> selectComment(int postId) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		List<PostCommentDTO> data = mapper.selectPostComment(postId);
		
		return data;
	}
	
	public int selectCommentCount(int postId) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		int commentCnt = mapper.selectCommentCount(postId);
		return commentCnt;
	}

	public boolean addComment(PostCommentDTO commentDto) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		int result = mapper.insertComment(commentDto);
		logger.info("addComment(commentDto={})", commentDto);
		logger.info("addComment(result={})", result);
		return result==1? true : false;
		
	}
	
	
}
