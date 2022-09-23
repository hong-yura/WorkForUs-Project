package site.workforus.forus.mapper;

import java.util.List;

import site.workforus.forus.board.model.BoardDTO;
import site.workforus.forus.board.model.BoardParticipDTO;
import site.workforus.forus.employee.model.EmpDTO;
import site.workforus.forus.employee.model.LoginVO;


public interface BoardMapper {
	// 게시판 가져오기
	public List<BoardDTO> selectAll(LoginVO loginVo); // Mapper.xml의 id와 같게 한다.
	// 게시판 하나만 가져오기
	public BoardDTO selectBoardData(int boardId);
	
	// 게시판 추가
	public int insertBoard(BoardDTO boardDto);
	
	// 게시판 지우기
	public int deleteBoard(int boardId);

	// 게시판 멤버 가져오기 -> 해당 게시판 id 필요
	public List<BoardParticipDTO> selectParticipList(BoardDTO boardDto);
	
} 