package site.workforus.forus.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.board.model.BoardPostDTO;
import site.workforus.forus.mapper.BoardMapper;
import site.workforus.forus.mapper.BoardPostMapper;

@Service
public class BoardPostService {
	@Autowired
	private SqlSession session;

	// 게시글 리스트 가져오기 -> paging 해야 한다. 
	public List<BoardPostDTO> selectAll(int boardId) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		
		List<BoardPostDTO> datas = mapper.selectPostAll(boardId);
		return datas;
		
	}

	// 게시글 하나 가져오기 -> empService 사용할 수 있을 때 구현 예정 
	public BoardPostDTO getPostData(int postId) {
		
		return null;
	}

	// 게시글 개수 가져오기 -> 총 갯수 - 공지 갯수
	public int getPostCnt(int boardId) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		int postCnt = mapper.generalPostCount(boardId);
		
		return postCnt;
	}
}
