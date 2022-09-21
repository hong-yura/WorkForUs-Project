package site.workforus.forus.board.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

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

import site.workforus.forus.board.model.BoardDTO;
import site.workforus.forus.board.model.BoardParticipDTO;
import site.workforus.forus.board.model.BoardPostDTO;
import site.workforus.forus.board.model.PostCommentDTO;
import site.workforus.forus.board.model.PostUploadFileDTO;
import site.workforus.forus.board.service.BoardParticipService;
import site.workforus.forus.board.service.BoardPostService;
import site.workforus.forus.board.service.BoardService;
import site.workforus.forus.board.service.PostCommentService;
import site.workforus.forus.employee.model.EmpDTO;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@Autowired
	private BoardService boardService;
	@Autowired
	private BoardPostService postService;
	@Autowired
	private BoardParticipService participService;
	@Autowired
	private PostCommentService commentService;

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);   
	
	@GetMapping(value="")
	public String getData(Model model, HttpSession session,Authentication auth //@RequestParam(required=false) int boardId 
			) { // 사용자가 어떤 게시판 url로 이동했는지 파라미터로 전달한다.
		// 임의의 empDto
		EmpDTO empDto = new EmpDTO(); 
//		LoginVO loginVo = (LoginVO) auth.getPrincipal();
		empDto.setEmpId("A2022105"); empDto.setEmpNm("원빈");
		int boardId = 2; // 임의로 넣어줌 -> 이건 나중에 URL로 넣어주기
		
//		session.setAttribute("empId", loginVo.getUsername());
		
//		아래 거는 나중에 추가해주기
//		session.setAttribute("loginData", empDto);
//		session.getAttribute("loginData");
//		if(boardId==0) { // 만약 boardId 가 없다면 기본적으로 1로 설정해줘야 한다.
//			boardId = 1;
//		}
		
		// 게시판 가져오기 -> navbar에 들어갈 내용
		List<BoardDTO> boardNav = boardService.selectAll(empDto); // 사원에 해당하는 게시판 데이터를 가지고 옴
	
		// 사원이 선택한 게시판 가져오기 
		BoardDTO boardData = boardService.selectBoardData(boardId);
		
		// 게시글 가져오기 -> 사원이 어떤 게시판을 클릭했는지 확인 해야 한다. -> url을 통해서 어떤 
		List<BoardPostDTO> postList = postService.selectAll(boardId); // 어떤 게시판의 게시글인지 확인을 해야 한다.
		
		// 참여명단을 가져와야 한다. 
		List<BoardParticipDTO> participList = participService.selectAll(boardData);
		
		// 게시글 갯수 notice_yn = 'N'인 게시글만 가지고 온다. 
		int postCnt = postService.getPostCnt(boardId);
		
		model.addAttribute("boardList", boardNav);
		model.addAttribute("postList", postList);
		model.addAttribute("boardData", boardData);
		model.addAttribute("participList", participList);
		model.addAttribute("postCnt", postCnt);
		return "/board/list";
		 
	}
	// 게시글 상세 
	@GetMapping(value="/detail")
	public String getDetailData(Model model, HttpSession session
									, @RequestParam String postId) { // 어떤 post id인지 알아야 한다.

		logger.info("postId={}", postId);
		// 얘는 나중에 session 으로 변경해주기
		EmpDTO loginData = new EmpDTO(); 
		loginData.setEmpId("A2022105"); loginData.setEmpNm("원빈");
		int pId = Integer.parseInt(postId); // String 으로 받아져서 다시 int형으로 변환해야함 
		// 게시글 데이터를 가지고 와야 한다.
		BoardPostDTO postData = postService.getPostData(pId);// postId -> 해당 게시글 데이터를 가져온다.
		logger.info("getDetailData(postId={})", postId);
		logger.info("getDetailData(postData={})", postData);
		// commentDto 가져오기 -> 어떤 post의 댓글인지
		List<PostCommentDTO> commentList = commentService.selectComment(pId);
		
		// 댓글 갯수
		int commentCnt = commentService.selectCommentCount(pId);
		
		// 파일 가져오기
		List<PostUploadFileDTO> files = postService.getFiles(pId);
		logger.info("getDetailData=(files={})", files);
		
		// 게시글 댓글 마지막 groupNo을 가지고 온다.
		int groupNo = postService.selectGroupNo(pId);
		
		// 게시글 댓글 구현 -> 해당 게시글에 대한 댓글을 찾아서 가져온다.
		model.addAttribute("postData", postData);
		model.addAttribute("loginData", loginData); // 이건 나중에 session 있으면 필요 없음
		model.addAttribute("commentList", commentList);
		model.addAttribute("commentCnt", commentCnt);
		model.addAttribute("groupNo", groupNo);
		model.addAttribute("files", files);
		return "/board/detail";
	}
	
	// 댓글추가 -> ajax로 변경
	@PostMapping(value="/comment/add" , produces="applicaton/json; charset=utf-8")
	@ResponseBody
	public String InsertComment(Model model, HttpSession session
							  , @RequestParam(value="depth", required=false) String depth
							  , @RequestParam(value="groupNo", required=false) String groupNo
							  , @RequestParam(value="content", required=false) String content
							  , @RequestParam(value="postId", required=false) String postId) {
		
		logger.info("InsertComment(depth={}, groupNo={}, content={}, postId={})", depth, groupNo, content, postId);
		JSONObject json = new JSONObject();
		int dep = 0;
		if(depth != null) { // 만약 null이 아니라면
			dep = Integer.parseInt(depth);
		}
		int gno = 0;
		if(groupNo != null) {
			gno = Integer.parseInt(groupNo);
		}
		int pId = Integer.parseInt(postId);
		
		PostCommentDTO commentDto = new PostCommentDTO();
		// sort 구하기
		int maxSort = postService.selectMaxSort(pId, gno); // groupNo은 몇 번째 그룹의 sort를 구할지 알아야 하기 때문에
		// 댓글을 전달 받는다. 
		// 작성한 사람의 id 를 넣어준다.
		commentDto.setEmpId("A2022105"); // -> 나중에는 session에 저장되어 있는 정보를 넣어준다.
		commentDto.setEmpNm("원빈");
		commentDto.setGroupNo(gno);
		commentDto.setContent(content);
		commentDto.setDepth(dep);
		logger.info("InsertComment(postId={})", pId);
		commentDto.setPostId(pId);
		commentDto.setSort(maxSort + 1); 
		
		// 댓글 저장
		boolean result = commentService.addComment(commentDto);
		try {
			// 만약 댓글 저장에 성공했다면
			json.put("code","success");
			json.put("message","댓글이 저장되었습니다.");
			json.put("commentDto", commentDto);
		} catch (Exception e){
			// 만약 댓글 저장에 실패했다면
			json.put("code", "error");
			json.put("message", "댓글 저장에 실패하였습니다.");
		}
		return json.toJSONString();
	}
	
	// 게시글 추가 화면
	@GetMapping(value="/post/add")
	public String getAddPost(Model model, HttpSession session
							, int boardId) { // 게시판 id만 있으면 됨
		model.addAttribute("boardId", boardId);
		// summernote 이미지 저장하기 위해서는 postId를 session에 저장해줘야 함
		int postId = postService.selectCurrentPostId(boardId); // postId 최대값
		session.setAttribute("postId", postId);
		
		return "/board/add";
	}

	// 게시글 추가 요청
	@PostMapping(value="/post/add")
	public String addPost(Model model, HttpSession session, HttpServletRequest request, int boardId // 어디 게시판에 추가할지 알아야함
						, @ModelAttribute BoardPostDTO postDto // 저장할 데이터
						, @RequestParam("postFiles") MultipartFile[] files) throws IllegalStateException, IOException {
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
		}
	
		// 게시글을 먼저 저장해야 한다. 
		postDto.setWriter("A2022100"); // -> 나중에는 session에 저장된 loginData.empId 넘기기
		int postId = postService.addPostData(postDto); // 상세화면으로 넘어가야 하기 때문에 postId를 받아와야 한다.
		
		// 파일이 없을 수도 있으니까
		if (files.length != 0) {
			// 내부경로로 저장
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
				fileData.setFileNm(savedFileName); 		// 실제 파일 이름
				fileData.setFileType(extension);		// 파일 확장자
				fileData.setPostId(postId);				// 게시글 id
				fileData.setSummYn("N");				// summernote 아님
				fileData.setUploadLocation(realPath);	// 실제 경로(loacation)
				fileData.setUploadUrl(request.getContextPath() + "/static/images/board/" + savedFileName);	// url
				
				// DB에 파일 저장
				postService.addUploadFileData(fileData);
			}
		}
		
		// 댓글 가져오기
		List<PostCommentDTO> commentList = commentService.selectComment(postId);
		int commentCnt = commentService.selectCommentCount(postId);
		
		logger.info("postData={}", postDto);
		model.addAttribute("postData", postDto);
		model.addAttribute("commentList", commentList);
		model.addAttribute("commentCnt", commentCnt);
		
		if(postId > 0) { // 저장 성공시
			return "redirect:/board/detail?postId=" + postId; // 해당 게시글로 이동해야 한다. 
		}else {			 // 저장 실패시
			return "/board/post/add";
		}
	}
	
	//게시글 수정 화면
	@GetMapping (value="/post/modify")
	public String getModifyPost(Model model, HttpSession session
							, int postId) {
		session.setAttribute("postId", postId); // 파일 저장할 때 필요함
		
		return "/board/modify";
	}
	
	//게시글 수정
	@PostMapping (value="/post/modify")
	public String modifyPost(Model model, HttpSession session
							, int postId
							, @ModelAttribute BoardPostDTO postDto) { // 저장될 데이터
		
		return "/board/modify";
	}
		
}