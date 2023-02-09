package site.workforus.forus.board.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import site.workforus.forus.board.model.PostLikeDTO;
import site.workforus.forus.board.model.ViewCntDTO;
import site.workforus.forus.board.service.BoardPostService;
import site.workforus.forus.employee.model.LoginVO;

@Controller
@RequestMapping(value="/post")
public class PostController {
	@Autowired
	private BoardPostService postService;
	
	private static final Logger logger = LoggerFactory.getLogger(PostController.class);
	
	@PutMapping (value="/like", produces="application/json; charset=utf-8")
	@ResponseBody
	public ResponseEntity<Object> modifyLike(@RequestBody PostLikeDTO likeDto)  {

		logger.info("modifyLike(postDto={})", likeDto);
		ResponseEntity<Object> data = postService.modifyLike(likeDto.getPostId());
		logger.info("modifyLike(ResponseEntity={})", data);

		return data;
	}

	// 게시글 조회수 변경
	@PatchMapping(value = "/view", produces = "application/json; charset=utf-8")
	@ResponseBody
	public ResponseEntity<Object> modifyView(@RequestBody ViewCntDTO viewCntDto
			, Authentication auth){
		LoginVO loginVo = (LoginVO)auth.getPrincipal();
		logger.info("PostController 조회수 변경 메소드 loginVo {}", loginVo);
		logger.info("PostController 조회수 변경 메소드 PostVisitDTO {}", viewCntDto);

		ResponseEntity<Object> data = postService.modifyVisit(viewCntDto, loginVo.getUsername());
		logger.info("PostController 조회수 변경 후 ResponseEntity {}", data);

		return data;
	}
}
