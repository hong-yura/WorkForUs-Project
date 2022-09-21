package site.workforus.forus.board.service;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import site.workforus.forus.board.controller.BoardController;
import site.workforus.forus.board.model.BoardPostDTO;
import site.workforus.forus.board.model.PostUploadFileDTO;
import site.workforus.forus.mapper.BoardPostMapper;

@Service
public class BoardPostService {
	@Autowired
	private SqlSession session;
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	// 게시글 리스트 가져오기 -> paging 해야 한다. 
	public List<BoardPostDTO> selectAll(int boardId) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		
		List<BoardPostDTO> datas = mapper.selectPostAll(boardId);
		return datas;
	}

	// 게시글 하나 가져오기 -> empService 사용할 수 있을 때 구현 예정 
	public BoardPostDTO getPostData(int postId) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		BoardPostDTO data = mapper.selectBoardDetail(postId);
		logger.info("BoardPostService(getPostData={})", data);
		return data;
	}

	// 게시글 개수 가져오기 -> 총 갯수 - 공지 갯수
	public int getPostCnt(int boardId) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		int postCnt = mapper.generalPostCount(boardId);
		
		return postCnt;
	}
	
	// 게시글 추가
	public int addPostData(BoardPostDTO postDto) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		// 다음 시퀀스 불러오기(postId)
		int postId = mapper.selectPostId(); // 다음 게시글 id가 몇 번인지 알아야 한다.
		
		postDto.setPostId(postId); 			// 위에서 구한 postId값을 넣어준다.
		int result = mapper.insertBoardPost(postDto);	// 게시글 저장
		if(result > 0) {
			return postId;
		}else {
			return 0; // 만약 실패하면 0을 리턴
		}
	}
	
	// 게시글 삭제
	public boolean deletePostData(int postId) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		int result = mapper.deleteBoardPost(postId);
		return result == 1 ? true : false;
	}
	
	// 게시글 수정
	public boolean updatePostData(BoardPostDTO postDto) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		int result = mapper.updateBoardPost(postDto);
		return result == 1 ? true : false;
	}

	// 게시글 파일 업로드
	public void addUploadFileData(PostUploadFileDTO fileData) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		mapper.insertPostUploadFile(fileData);
	}

	public int selectCurrentPostId(int boardId) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		int currentId = mapper.selectCurrentPostId(boardId);
		return currentId;
	}

	// 게시글 파일 가져오기
	public List<PostUploadFileDTO> getFiles(int postId) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		List<PostUploadFileDTO> files = mapper.selectFiles(postId);
		logger.info("BoardPostService - getFiles(files={})", files);
		return files;
	}

	public int selectGroupNo(int postId) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		Integer groupNo = mapper.selectGroupNo(postId);
		if(groupNo==null) {
			return 0;
		}else {
			return groupNo;
		}
	}

	public int selectMaxSort(int postId, int groupNo) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		HashMap<String, Integer> data = new HashMap<String, Integer>();
		data.put("groupNo", groupNo);
		data.put("postId", postId);
		Integer maxSort = mapper.selectMaxSort(data);
		if(maxSort == null) {
			return 0;
		}else {
			return maxSort;
		}
		
	}
}
