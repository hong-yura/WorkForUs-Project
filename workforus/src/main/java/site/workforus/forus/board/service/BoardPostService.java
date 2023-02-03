package site.workforus.forus.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;
import site.workforus.forus.board.controller.BoardController;
import site.workforus.forus.board.model.BoardPostDTO;
import site.workforus.forus.board.model.PostLikeDTO;
import site.workforus.forus.board.model.PostVisitDTO;
import site.workforus.forus.mapper.BoardPostMapper;

@Service
public class BoardPostService {
	@Autowired
	private SqlSession session;
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	// 게시글 리스트 가져오기 -> paging 해야 한다. 
	public List<BoardPostDTO> selectAll(int boardId, String search, String searchType) {
		logger.info("selectAll(boardId={}, search={}, searchType={})", boardId, search, searchType);
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		List<BoardPostDTO> datas = new ArrayList<BoardPostDTO>();
		if(search == null && searchType == null) {
			// 검색 기능 사용 x
			datas = mapper.selectPostAll(boardId);
			logger.info("selectAll(검색x datas={})", datas);
		}else {
			// 검색 기능 사용 o
			HashMap<String, Object> searchData = new HashMap<String, Object>();
			searchData.put("boardId", boardId);
			searchData.put("search", search);
			searchData.put("searchType", searchType);
			
			datas = mapper.selectSearchData(searchData);
			logger.info("selectAll(검색O searchData={})", searchData);
		}
		
		return datas;
	}
	
	// 공지가 아닌 게시글만 따로 가져와야 함..
	public List<BoardPostDTO> selectNotNotice(int boardId, String search, String searchType) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		
		List<BoardPostDTO> datas = new ArrayList<BoardPostDTO>();
		if(search == null) {
			// 검색 기능 사용 x
			datas = mapper.selectNotNoticeList(boardId);
			logger.info("selectNotNotice(공지X 검색X datas={})", datas);
		}else {
			// 검색 기능 사용 o
			HashMap<String, Object> searchData = new HashMap<String, Object>();
			searchData.put("boardId", boardId);
			searchData.put("search", search);
			searchData.put("searchType", searchType);
			
			datas = mapper.selectNotNoticeSearchList(searchData);
			logger.info("selectNotNotice(공지X 검색O searchData={})", searchData);
		}
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
		logger.info("updatePostData(postDto={}, result={})", postDto, result);
		return result == 1 ? true : false;
	}



	// 현재 postId
	public int selectCurrentPostId(int boardId) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		int currentId = mapper.selectCurrentPostId(boardId);
		return currentId;
	}



	// 현재 groupNO
	public int selectGroupNo(int postId) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		Integer groupNo = mapper.selectGroupNo(postId);
		if(groupNo==null) {
			return 0;
		}else {
			return groupNo;
		}
	}

	// sort의 최대값
	public int selectMaxSort(int postId, int groupNo) {
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		HashMap<String, Integer> data = new HashMap<String, Integer>();
		data.put("postId", postId);
		data.put("groupNo", groupNo);
		Integer maxSort = mapper.selectMaxSort(data);
		logger.info("selectMaxSort(postId={}, grouopNo={}, maxSort={})", postId, groupNo, maxSort);
		if(maxSort == null) {
			return 0; 		// 만약 새로운 groupNo의 sort를 구하는 거면 0일 수밖에 없음 즉, 본댓글을 추가할 시
		}else {
			return maxSort; // 대댓을 추가할 때는 sort의 최대값이 무조건 1이상이다. 
		}
		
	}

//	// 게시글 좋아요
//	public BoardPostDTO likeUp(BoardPostDTO postDto,  HttpSession httpSession) {
//		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
//		int result = mapper.updateLikeUp(postDto);
//		logger.info("likeUp(db에 like + 1을 한 후 postDto={})", postDto);
//		if(result == 1) {
//			// 성공했다면 -> 전달받은 현재 게시글 데이터에서 likeCnt를 + 1 해줘야 한다.
//			// ajax로 할 거기 때문에 안 그러면 리로딩을 해야지만 좋아요가 올라감 즉, db엔 증가했지만 브라우저엔 반영이 안 됨
//			postDto.setLikeCnt(postDto.getLikeCnt() + 1);
//			logger.info("likeUp(+1을 따로 해준 경우 postDto={})", postDto);
//			return postDto;
//		}else {
//			return null;
//		}
//	}


	// like수 변경
	@Transactional
    public ResponseEntity<Object> modifyLike(int postId) {
		logger.info("modifyLike(postId={})", postId);

		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);

		BoardPostDTO postDto = mapper.selectLikeCntByPostId(postId);
		logger.info("modifyLike(postDto={})", postDto);

		boolean likedYn = _likedYn(postId);

		if(likedYn){
			// likeUp
			postDto.setLikeCnt(postDto.getLikeCnt() + 1);
			logger.info("modifyLike(likeUp 성공)");
		}else {
			// likeDown
			postDto.setLikeCnt(postDto.getLikeCnt() - 1);
			logger.info("modifyLike(likeDown 성공)");
		}
		mapper.updateLikeCnt(postDto);
		logger.info("modifyLike(수정 후 postDto={})", postDto);
		ResponseEntity<Object> responseEntity = new ResponseEntity<>(postDto, HttpStatus.OK);
		return responseEntity;
    }
	// 게시글 좋아요를 누른 기록이 있는지 확인
	private boolean _likedYn(int postId){
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		// spring security 에서 사용자 정보 가져오기
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		UserDetails userDetails = (UserDetails)principal;

		String empId = userDetails.getUsername();

		logger.info("_likeYn(postId={}, empId={})", postId, empId);

		List<PostLikeDTO> data = mapper.selectLikeByPostIdAndUserId(postId,empId);
		logger.info("_likeYn(data={})", data);

		if(data.isEmpty()){
			// 데이터 추가
			mapper.insertLike(postId, empId);
			logger.info("_likedYn(insertLike 성공)");
			return true;
		}else {
			// 데이터 삭제
			mapper.deleteLike(postId, empId);
			logger.info("_likedYn(deleteLike 성공)");
			return false;
		}
	}

	// 방문기록이 있는지 확인
	public boolean modifyVisit(int postId, String empId){

		// 게시글을 방문한 사원이 접속 기록을 확인
		// 1. 접속 기록 없음
		// 		-> 접속 기록을 저장해주고, 방문자수 cnt + 1 을 해준다.
		// 2. 접속 기록 있음
		// 		2.1. 접속 기록이 있지만 방문한 지 3일이 지났다면
		//			-> 접속 기록을 수정해주고, 방문자수 cnt + 1 을 해준다.
		// 		2.2. 접속 기록이 있지만 방문한 지 3일이 지나지 않았다면
		//			-> 그대로 유지
		// postId, empId, last-visit
		BoardPostMapper mapper = session.getMapper(BoardPostMapper.class);
		PostVisitDTO visitDto = mapper.selectVisitByPostIdAndEmpId(postId, empId);

		if(visitDto == null){	// 만약 방문기록이 없다면


		}



		return true;
	}



}
