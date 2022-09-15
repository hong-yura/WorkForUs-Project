package site.workforus.forus.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.board.model.BoardDTO;
import site.workforus.forus.board.model.BoardParticipDTO;
import site.workforus.forus.mapper.BoardMapper;

@Service
public class BoardParticipService {
	@Autowired
	private SqlSession session;

	public List<BoardParticipDTO> selectAll(BoardDTO boardDto) {
		// 해당 게시판의 멤버를 가져와야 한다.
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		List<BoardParticipDTO> data = mapper.selectParticipList(boardDto);
		
		return data;
	}
} 
