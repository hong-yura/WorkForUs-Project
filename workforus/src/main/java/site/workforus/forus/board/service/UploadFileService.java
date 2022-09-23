package site.workforus.forus.board.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.board.controller.BoardController;
import site.workforus.forus.board.model.PostUploadFileDTO;
import site.workforus.forus.mapper.BoardPostMapper;

@Service
public class UploadFileService {
	@Autowired
	private SqlSession session;
	private static final Logger logger = LoggerFactory.getLogger(UploadFileService.class);
	
	// 파일 삭제
	public boolean deleteFile(int postId) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		int result = mapper.deleteFile(postId);
		return  result == 1?true : false;
	}
	
	// 게시글 파일 업로드
	public boolean addUploadFileData(PostUploadFileDTO fileData) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		int result = mapper.insertPostUploadFile(fileData);
		return result == 1?true:false;
	}
	
	// 게시글 파일 가져오기
	public List<PostUploadFileDTO> getFiles(int postId) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		List<PostUploadFileDTO> files = mapper.selectFiles(postId);
		logger.info("BoardPostService - getFiles(files={})", files);
		return files;
	}
	
}
