package site.workforus.forus.board.controller;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import site.workforus.forus.board.model.BoardPostDTO;
import site.workforus.forus.board.model.PostLikeDTO;
import site.workforus.forus.board.service.BoardPostService;

@Controller
@RequestMapping(value="/post")
public class PostLikeController {
	@Autowired
	private BoardPostService postService;
	
	private static final Logger logger = LoggerFactory.getLogger(PostLikeController.class);
	
	@PutMapping (value="/like", produces="application/json; charset=utf-8")
	@ResponseBody
	public ResponseEntity<Object> modifyLike(@RequestBody PostLikeDTO likeDto)  {

		logger.info("modifyLike(postDto={})", likeDto);
		ResponseEntity<Object> data = postService.modifyLike(likeDto.getPostId());
		logger.info("modifyLike(ResponseEntity={})", data);

		return data;
	}
}
