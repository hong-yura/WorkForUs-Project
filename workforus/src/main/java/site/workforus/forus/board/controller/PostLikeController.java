package site.workforus.forus.board.controller;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import site.workforus.forus.board.model.BoardPostDTO;
import site.workforus.forus.board.service.BoardPostService;

@Controller
@RequestMapping(value="/post")
public class PostLikeController {
	@Autowired
	private BoardPostService postService;
	
	private static final Logger logger = LoggerFactory.getLogger(PostLikeController.class);
	
	@PostMapping(value="/like")
	@ResponseBody
	public String likeUp(Model model, HttpSession session
					 , @RequestParam String postId)  {
		
		JSONObject json = new JSONObject();
		// post정보를 가지고 온다.
		BoardPostDTO postDto = postService.getPostData(Integer.parseInt(postId));
		logger.info("+1하기 전의 데이터 : like(postDto={})", postDto);

		// postId 에 해당 하는 게시글의 좋아요 수를 올려준다.
		BoardPostDTO result = postService.likeUp(postDto, session); // session에 login 정보를 저장해야 한다.
		logger.info("+1하고 난 후 데이터 : likeUp(result={}, postDto={})", result, postDto);
		if(result != null) {
			int likeCnt = postDto.getLikeCnt();
			json.put("code", "success");
			json.put("likeCnt", likeCnt);
		}else {
			json.put("code", "error");
			json.put("message", "데이터가 존재하지 않습니다.");
		}
		return json.toJSONString(); 
		
	}
}
