package site.workforus.forus.mapper;

import java.util.List;

import site.workforus.forus.board.model.BoardDTO;


public interface BoardMapper {
	public List<BoardDTO> selectAll();
} 
