package site.workforus.forus.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.board.model.BoardDTO;
import site.workforus.forus.employee.model.LoginVO;
import site.workforus.forus.mapper.BoardMapper;

@Service
public class BoardService {
	 
	@Autowired
	private SqlSession session;

	public List<BoardDTO> selectAll(LoginVO loginVo) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		
		List<BoardDTO> datas = mapper.selectAll(loginVo);
		return datas;
		
	}

	public BoardDTO selectBoardData(int boardId) {
		// 해당 게시판의 정보만 가지고 와야 한다.
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		BoardDTO data = mapper.selectBoardData(boardId);
		return data;
	}
	

}