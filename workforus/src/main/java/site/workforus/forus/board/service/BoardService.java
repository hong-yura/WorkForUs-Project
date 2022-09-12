package site.workforus.forus.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.board.model.BoardDTO;

@Service
public class BoardService {
	
	@Autowired
	private SqlSession session;
	
	public List<BoardDTO> selectDatas(){
		
		return ;
	}
}
