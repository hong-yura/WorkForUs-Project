package site.workforus.forus.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import site.workforus.forus.admin.model.DeptDTO;
import site.workforus.forus.admin.service.DeptService;
import site.workforus.forus.board.model.BoardDTO;
import site.workforus.forus.board.model.BoardParticipDTO;
import site.workforus.forus.board.model.BoardPostDTO;
import site.workforus.forus.board.model.PostCommentDTO;
import site.workforus.forus.board.model.PostUploadFileDTO;
import site.workforus.forus.board.service.BoardParticipService;
import site.workforus.forus.board.service.BoardPostService;
import site.workforus.forus.board.service.BoardService;
import site.workforus.forus.board.service.PostCommentService;
import site.workforus.forus.board.service.UploadFileService;
import site.workforus.forus.employee.model.EmpDTO;
import site.workforus.forus.employee.model.LoginVO;

@Controller
@RequestMapping(value="/board")
@MultipartConfig
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardPostService postService;
	@Autowired
	private BoardParticipService participService;
	@Autowired
	private PostCommentService commentService;
	@Autowired
	private UploadFileService fileService;
	@Autowired
	private DeptService deptService;
	
	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);   
	
	@GetMapping(value="")
	public String getData(Model model, HttpSession session, Authentication auth
						, @RequestParam(value="bId",required=false) int bId
						, @RequestParam(value="search", required=false) String search
						, @RequestParam(value="searchType", required=false) String searchType) { // 사용자가 어떤 게시판 url로 이동했는지 파라미터로 전달한다.
		logger.info("getData(bId={})", bId);
		logger.info("getData(search={})", search);
		logger.info("getData(searchType={})", searchType);
		
		// 로그인 데이터 가져오기
		LoginVO loginVo =  (LoginVO)auth.getPrincipal();
		logger.info("getData(loginVo={})", loginVo);
		
		// 게시판 가져오기 -> navbar에 들어갈 내용
		List<BoardDTO> boardNav = boardService.selectAll(loginVo); // 사원에 해당하는 게시판 데이터를 가지고 옴
		logger.info("getData(boardNav={})", boardNav);
		
		// 사원이 선택한 게시판 가져오기 
		BoardDTO boardData = boardService.selectBoardData(bId);
		
		// 게시글 가져오기 -> 사원이 어떤 게시판을 클릭했는지 확인 해야 한다. -> url을 통해서
		// 검색 기능을 사용하지 않았다면 -> 모든 게시글을 가지고 온다.
		// 검색 기능을 사용했다면 -> 검색한 게시판에 해당하는 데이터만 가지고 온다.
		List<BoardPostDTO> postList = postService.selectAll(bId, search,searchType); // 어떤 게시판의 게시글인지 확인을 해야 한다.
		logger.info("getData(postList)={}", postList);
		
		List<BoardPostDTO> notNotice = postService.selectNotNotice(bId, search, searchType);
		logger.info("getData(notNotice)={}", notNotice);
		
		// 부서를 가지고 와야 한다. -> 게시판 생성할 때 필요
		List<DeptDTO> deptDto = deptService.getDeptAll();
		
		
		// 참여명단을 가져오기 
		List<BoardParticipDTO> participList = participService.selectAll(boardData);
		
		// 게시글 갯수 notice_yn = 'N'인 게시글만 가지고 온다. 
		int postCnt = postService.getPostCnt(bId);
		
		model.addAttribute("boardList", boardNav);
		model.addAttribute("postList", postList);
		model.addAttribute("notNotice", notNotice);
		model.addAttribute("boardData", boardData);
		model.addAttribute("participList", participList);
		model.addAttribute("postCnt", postCnt);
		model.addAttribute("deptData", deptDto);
		return "/board/list";
		 
	}
	
	// 게시판 생성 페이지
	@PostMapping(value="/add")
	public String addBoard(Model model, Authentication auth 
						, @ModelAttribute BoardDTO boardDto
						, @RequestParam int currentBoardId) {
		
		logger.info("addBoard(boardDto={})", boardDto);
		boardDto.setBoardManager(auth.getName()); // empId 넣어주기
		
		logger.info("addBoard(boardDto={})", boardDto);
		
		// 저장한 후 상세 데이터를 가지고 와야 한다.
		BoardDTO boardData = boardService.addBoard(boardDto);
		logger.info("게시판 추가 후 : addBoard(boardData={})", boardData);
		
		if(boardData != null) {
			return "redirect:/board?bId=" + boardData.getBoardId();
		}else {
			return "/board?bId=" + currentBoardId;
		}
		
	}
	
	
	// 게시글 상세 
	@GetMapping(value="/detail")
	public String getDetailData(Model model, HttpSession session, Authentication auth
									, @RequestParam String postId) { // 어떤 post id인지 알아야 한다.

		// 얘는 나중에 session 으로 변경해주기
		LoginVO loginVo = (LoginVO)auth.getPrincipal();
		logger.info("getData(loginVo={})", loginVo);

		int pId = Integer.parseInt(postId); // String 으로 받아져서 다시 int형으로 변환해야함
		// 게시글 데이터를 가지고 와야 한다.
		BoardPostDTO postData = postService.getPostData(pId);// postId -> 해당 게시글 데이터를 가져온다.
		logger.info("getDetailData(postId={})", postId);
		logger.info("getDetailData(postData={})", postData);
		// commentDto 가져오기 -> 어떤 post의 댓글인지
		List<PostCommentDTO> commentList = commentService.selectAllComment(pId);
		
		// 댓글 갯수
		int commentCnt = commentService.selectCommentCount(pId);
		
		// 파일 가져오기
		List<PostUploadFileDTO> files = fileService.getFiles(pId);
		logger.info("getDetailData=(files={})", files);
		
		// 게시글 댓글 마지막 groupNo을 가지고 온다.
		int groupNo = postService.selectGroupNo(pId);
		
		// 게시글 댓글 구현 -> 해당 게시글에 대한 댓글을 찾아서 가져온다.
		model.addAttribute("postData", postData); 
		model.addAttribute("loginId", loginVo.getUsername()); 
		logger.info("detail(loginVo={})", loginVo);
		
		model.addAttribute("commentList", commentList);
		model.addAttribute("commentCnt", commentCnt);
		model.addAttribute("groupNo", groupNo + 1); // + 1을 한 이유 : 댓글 추가기능에서 groupNo 서버에 전달할 때 null값이 전달되는 걸 방지 
		model.addAttribute("files", files);
		return "/board/post-detail";
	}
	
	// 본댓글 추가
	@PostMapping(value="/comment/add")
	public String InsertComment(Model model, HttpSession session, Authentication auth
							  , @ModelAttribute PostCommentDTO commentDto
							  ) {
		
		logger.info("InsertComment(commentDto={})",commentDto);
		commentDto.setDepth(0);
		
		LoginVO loginVo = (LoginVO)auth.getPrincipal();
		// loginVo 에서 empId를 가지고 와서 empNm을 찾아야 함 -> empNm은 내가 따로..?
		EmpDTO empDto = commentService.selectEmpDto(loginVo.getUsername());
		logger.info("InsertComment(loginVo.getUsername={})", loginVo.getUsername());
		logger.info("InsertComment(empDto={})", empDto);
		
		// sort 구하기
		int maxSort = postService.selectMaxSort(commentDto.getPostId(), commentDto.getGroupNo()); // groupNo은 몇 번째 그룹의 sort를 구할지 알아야 하기 때문에
		commentDto.setSort(maxSort + 1); 
		commentDto.setEmpId(empDto.getEmpId());
		commentDto.setEmpNm(empDto.getEmpNm());
		
		// 댓글 저장
		boolean result = commentService.addComment(commentDto);
		if (result) {
			return "redirect: /board/detail?postId=" + commentDto.getPostId(); 
		}else {
			model.addAttribute("message", "댓글 저장에 실패하였습니다.");
			return  "/board/detail?postId" + commentDto.getPostId();
		}
	}
	
	// 대댓 추가
	@PostMapping(value="/comment/add2")
	public String InsertComment2(Model model, Authentication auth
							  , @ModelAttribute PostCommentDTO commentDto
							  ) {
		
		logger.info("InsertComment(commentDto={})",commentDto);
		commentDto.setDepth(1);
		
		LoginVO loginVo = (LoginVO)auth.getPrincipal();
		// loginVo 에서 empId를 가지고 와서 empNm을 찾아야 함 -> empNm은 내가 따로..?
		EmpDTO empDto = commentService.selectEmpDto(loginVo.getUsername());
		
		// sort 구하기
		int maxSort = postService.selectMaxSort(commentDto.getPostId(), commentDto.getGroupNo()); // groupNo은 몇 번째 그룹의 sort를 구할지 알아야 하기 때문에
		commentDto.setSort(maxSort + 1); 
		commentDto.setEmpId(empDto.getEmpId());
		commentDto.setEmpNm(empDto.getEmpNm());
		
		// 댓글 저장
		boolean result = commentService.addComment(commentDto);
		if (result) {
			return "redirect: /board/detail?postId=" + commentDto.getPostId(); 
		}else {
			model.addAttribute("message", "댓글 저장에 실패하였습니다.");
			return  "/board/detail?postId" + commentDto.getPostId();
		}
	}
		
	
	
	// 게시글 추가 화면
	@GetMapping(value="/post/add")
	public String getAddPost(Model model, HttpSession session
							, int boardId) { // 게시판 id만 있으면 됨
		model.addAttribute("boardId", boardId);
		// summernote 이미지 저장하기 위해서는 postId를 session에 저장해줘야 함
		int postId = postService.selectCurrentPostId(boardId); // postId 최대값
		session.setAttribute("postId", postId);
		
		return "/board/post-add";
	}

	// 게시글 추가 요청
	@PostMapping(value="/post/add")
	public String addPost(Model model, HttpSession session, Authentication auth
						, HttpServletRequest request, int boardId // 어디 게시판에 추가할지 알아야함
						, @ModelAttribute BoardPostDTO postDto // 저장할 데이터
						, @RequestParam(value="postFiles", required = false) MultipartFile[] files) throws IllegalStateException, IOException {
		// 게시판 정보가 필요
		logger.info("addPost=(boardId={}, files={})", boardId, files);
		
		// 만약 content도 file도 올리지 않았다면 
		
		// 만약 임시저장이 null이면 N을 해준다.
		if(postDto.getTemporaryYn()==null) {
			postDto.setTemporaryYn("N");
		}
		// 만약 공지가 null이면 N을 해준다.
		if(postDto.getNoticeYn()==null) {
			postDto.setNoticeYn("N");
			logger.info("postDto.getNoticeYn={}",postDto.getNoticeYn());
		}
		LoginVO loginVo = (LoginVO)auth.getPrincipal();
		// 게시글을 먼저 저장해야 한다. 
		postDto.setWriter(loginVo.getUsername()); // -> 나중에는 session에 저장된 loginData.empId 넘기기
		int postId = postService.addPostData(postDto); // 상세화면으로 넘어가야 하기 때문에 postId를 받아와야 한다.
		
		// 파일이 없을 수도 있으니까
		if (files[0].getSize()!= 0) { // 만약 size가 0이라면 -> 파일 없는 거임
			// 내부경로로 저장
			logger.info("addPost(files={})", files.toString());
			String realPath = request.getServletContext().getRealPath("/resources"); // static의 진짜 파일위치 찾기
			logger.info("addPost(realPath={})", realPath);
			String fileRoot = realPath+"/images/board/"; 				// 저장할 위치
			logger.info("addPost(fileRoot={})", fileRoot);
			
			PostUploadFileDTO fileData = new PostUploadFileDTO(); 		// fileData 리스트를 전달해줘야 한다.
			
			for(MultipartFile file : files) {
				
				String originalFileName = file.getOriginalFilename();	//오리지날 파일명
				String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
				String savedFileName = UUID.randomUUID() + extension;		//저장될 파일 명 -> randomUUID -> 파일명 랜덤으로
				File targetFile = new File(fileRoot + savedFileName);	 // 저장할 위치와 
				
				file.transferTo(targetFile); // 파일 생성
				
				// uploadFileDTO에 저장을 해줘야 한다.
				fileData.setFileNm(originalFileName); 		// 실제 파일 이름
				fileData.setFileType(extension);			// 파일 확장자
				fileData.setPostId(postId);					// 게시글 id
				fileData.setSummYn("N");					// summernote 아님
				fileData.setUploadLocation(realPath);		// 실제 경로(loacation)
				fileData.setUploadUrl(request.getContextPath() + "/static/images/board/" + savedFileName);	// url
				
				// DB에 파일 저장
				fileService.addUploadFileData(fileData);
			}
		}
		
		// 댓글 가져오기
		List<PostCommentDTO> commentList = commentService.selectAllComment(postId);
		int commentCnt = commentService.selectCommentCount(postId);
		
		logger.info("postData={}", postDto);
		model.addAttribute("postData", postDto);
		model.addAttribute("commentList", commentList);
		model.addAttribute("commentCnt", commentCnt);
		
		if(postId > 0) { // 저장 성공시
			return "redirect:/board/detail?postId=" + postId; // 해당 게시글로 이동해야 한다. 
		}else {			 // 저장 실패시
			return "/board/post-add";
		}
	}
	
	//게시글 수정 화면
	@GetMapping (value="/post/modify")
	public String getModifyPost(Model model//, HttpSession session
							, int postId) {
//		session.setAttribute("postId", postId); // 파일 저장할 때 필요함
		// 게시글 데이터 가져오기
		BoardPostDTO postData = postService.getPostData(postId);
		List<PostUploadFileDTO> fileData = fileService.getFiles(postId);
		model.addAttribute("postData", postData);
		model.addAttribute("fileData", fileData);
		
		return "/board/post-modify";
	}
	
	//게시글 수정
	@PostMapping (value="/post/modify")
	public String modifyPost(Model model, HttpSession session, HttpServletRequest request
							, int postId
							, @ModelAttribute BoardPostDTO postDto
							, @RequestParam(value="postFiles", required = false) MultipartFile[] files) throws IllegalStateException, IOException { // 저장될 데이터
		
		logger.info("modifyPost(postDto={}, postId={})", postDto, postId);
//	
		// 게시글 저장 
		boolean result = postService.updatePostData(postDto);
		
		if(result) {	// 만약 게시글 update를 성공하면
			// 파일이 없을 수도 있으니까
			if (files[0].getSize()!= 0) { // 만약 size가 0이라면 -> 파일 없는 거임
				// 내부경로로 저장
				logger.info("addPost(files={})", files.toString());
				String realPath = request.getServletContext().getRealPath("/resources"); // static의 진짜 파일위치 찾기
				logger.info("addPost(realPath={})", realPath);
				String fileRoot = realPath+"/images/board/"; 				// 저장할 위치
				logger.info("addPost(fileRoot={})", fileRoot);
				
				PostUploadFileDTO fileData = new PostUploadFileDTO(); 		// fileData 리스트를 전달해줘야 한다.
				
				for(MultipartFile file : files) {
					
					String originalFileName = file.getOriginalFilename();	//오리지날 파일명
					String extension = originalFileName.substring(originalFileName.lastIndexOf("."));	//파일 확장자
					String savedFileName = UUID.randomUUID() + extension;		//저장될 파일 명 -> randomUUID -> 파일명 랜덤으로
					File targetFile = new File(fileRoot + savedFileName);	 // 저장할 위치와 
					
					file.transferTo(targetFile); // 파일 생성
					
					// uploadFileDTO에 저장을 해줘야 한다.
					fileData.setFileNm(originalFileName); 		// 실제 파일 이름
					fileData.setFileType(extension);			// 파일 확장자
					fileData.setPostId(postId);					// 게시글 id
					fileData.setSummYn("N");					// summernote 아님
					fileData.setUploadLocation(realPath);		// 실제 경로(loacation)
					fileData.setUploadUrl(request.getContextPath() + "/static/images/board/" + savedFileName);	// url
					
					// DB에 파일 저장
					boolean fileSave = fileService.addUploadFileData(fileData);
				}
			}
		}
		return "redirect:/board/detail?postId="+ postDto.getPostId(); // 저장에 성공하면 메인 화면으로 리다이렉트
			
	}
	
	// 게시글 삭제
	@PostMapping(value="/post/delete", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String deletePost(Model model, @RequestParam int postId) {
		logger.info("deletePost(postId={})", postId);
		
		JSONObject json = new JSONObject();
		// 게시글을 삭제 하기 전에 관련된 것들을 모두 삭제해줘야 한다. -> file, comment
		boolean delFile = fileService.deleteFile(postId);
		boolean delComment = commentService.deleteAllComment(postId);
		// 게시글 데이터 삭제 
		boolean result = postService.deletePostData(postId);
		
		if(result && delFile && delComment) { // 모두 삭제가 완료 되었다면
			json.put("massage", "삭제 완료");
			return json.toJSONString();
		}else {
			json.put("message", "삭제 실패");
			return json.toJSONString();
		}
		
	}
	
	// 댓글 삭제
	@PostMapping(value="/comment/delete", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String deleteComment(Model model
							, @RequestParam int commentId
							, @RequestParam int postId) {
		
		logger.info("deleteComment(commentId={}, postId={})", commentId, postId);
		
		JSONObject json = new JSONObject();
		// 본댓을 삭제하려면 본댓의 정보를 가지고 와서 postId랑 groupId 를 가지고 소속된 모든 댓글을 지워준다. 
		// postId 랑 groupId 를 줘야 한다. 그냥 commentId를 주면 안됨 관련 댓글도 다 지워야 하니까 
		// 만약 본 댓글이면 다 지워야 함 대댓이면 ? 해당 댓글만 지우면 됨 
		boolean result = commentService.deleteComment(commentId);
		logger.info("deleteComment(result={})", result);
		
		int commentCnt = commentService.selectCommentCount(postId);
		
		if(result) { // 모두 삭제가 완료 되었다면
			logger.info("result == true");
			json.put("code", "seccess");
			json.put("commentCnt", commentCnt);
			return json.toJSONString();
		}else {
			json.put("code", "error");
			return json.toJSONString();
		}
		
	}
}