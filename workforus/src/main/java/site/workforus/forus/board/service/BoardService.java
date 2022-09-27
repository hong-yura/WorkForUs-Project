package site.workforus.forus.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.board.model.BoardDTO;
import site.workforus.forus.board.model.BoardParticipDTO;
import site.workforus.forus.employee.model.LoginVO;
import site.workforus.forus.mapper.BoardMapper;

@Service
public class BoardService {
	private static final Logger logger = LoggerFactory.getLogger(BoardService.class);   
	
	@Autowired
	private SqlSession session;

	// 모든 게시판 
	public List<BoardDTO> selectAll(LoginVO login) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		
		List<BoardDTO> datas = mapper.selectAll(login);
		return datas;
		
	}

	// 게시판 데이터
	public BoardDTO selectBoardData(int boardId) {
		// 해당 게시판의 정보만 가지고 와야 한다.
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		BoardDTO data = mapper.selectBoardData(boardId);
		return data;
	}

	// 게시판 추가
	public BoardDTO addBoard(BoardDTO boardDto) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		
		// 다음 시퀀스가 뭔지 알아야 함
		int nextBoardSeq = _nextBoardSeq();
		boardDto.setBoardId(nextBoardSeq);
		
		// 참여자 명단 추가
		int result = mapper.insertBoard(boardDto);
		int participResult = addParticip(boardDto.getBoardId(), boardDto.getBoardManager()); // 게시판을 만든 사람을 넣어준다.
		logger.info("addBoard(boardDto={})", boardDto);
		
		
		// 추가를 해줄 때에는 시퀀스 사용 x
		if(result == 1 && participResult == 1) {
			BoardDTO boardData = selectBoardData(boardDto.getBoardId());
			return boardData;
		}else {
			return null;
		}
	}
	
	// 다음 게시판 시퀀스
	private int _nextBoardSeq() {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		int result = mapper.nextBoardSeq();
		return result;
	}
	
	// 명단 추가
	public int addParticip(int boardId, String empId) {
		BoardMapper mapper = session.getMapper(BoardMapper.class);
		BoardParticipDTO participDto = new BoardParticipDTO();
		participDto.setBoardId(boardId);
		participDto.setEmpId(empId);
		
		int result = mapper.insertParticip(participDto);
		return result;
	}
	
	
}