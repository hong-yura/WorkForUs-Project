package site.workforus.forus.board.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import site.workforus.forus.board.model.BoardDTO;
import site.workforus.forus.board.model.BoardParticipDTO;
import site.workforus.forus.board.model.BoardPostDTO;
import site.workforus.forus.board.model.PostCommentDTO;
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
	public String getData(Model model, HttpSession session //, @RequestParam int boardId
							 ) { // 사용자가 어떤 게시판 url로 이동했는지 파라미터로 전달한다.
		// 임의의 empDto
		EmpDTO empDto = new EmpDTO(); 
		empDto.setEmpId("A2022100"); empDto.setEmpNm("김나영");
		int boardId = 1; // 임의로 넣어줌 -> 이건 나중에 URL로 넣어주기
		
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
									, @RequestParam int postId) { // 어떤 post id인지 알아야 한다.

		// 얘는 나중에 session 으로 변경해주기
		EmpDTO loginData = new EmpDTO(); 
		loginData.setEmpId("A2022100"); loginData.setEmpNm("김나영");
		
		// 게시글 데이터를 가지고 와야 한다.
		BoardPostDTO postData = postService.getPostData(postId);// postId -> 해당 게시글 데이터를 가져온다.
		
		// commentDto 가져오기 -> 어떤 post의 댓글인지
		List<PostCommentDTO> commentList = commentService.selectComment(postId);
		
		// 댓글 갯수
		int commentCnt = commentService.selectCommentCount(postId);
		
		// 게시글 댓글 구현 -> 해당 게시글에 대한 댓글을 찾아서 가져온다.
		model.addAttribute("postData", postData);
		model.addAttribute("loginData", loginData); // 이건 나중에 session 있으면 필요 없음
		model.addAttribute("commentList", commentList);
		model.addAttribute("commentCnt", commentCnt);
		return "/board/detail";
	}
	
	// 댓글추가
	@PostMapping(value="/comment/add")
	public String InsertComment(Model model, HttpSession session
							  , @ModelAttribute PostCommentDTO commentDto) {
		
		logger.info("InsertComment(PostCommentDTO={})", commentDto);
		
		// 댓글을 전달 받는다. 
		// depth, group
		return "/board/detail";
	}
	
	// 게시글 추가 화면
	@GetMapping(value="/post/add")
	public String getAddPost(Model model, HttpSession session
							, int boardId) { // 게시판 id만 있으면 됨
		model.addAttribute("boardId", boardId);
		
		return "/board/add";
	}

	// 게시글 추가 요청
	@PostMapping(value="/post/add")
	public String addPost(Model model, HttpSession session
						, int boardId) {
		// 게시판 정보가 필요
		logger.info("addPost=(boardId={})", boardId);
		
		
		return "/board/list";
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