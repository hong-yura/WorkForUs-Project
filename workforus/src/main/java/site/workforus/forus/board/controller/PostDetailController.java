package site.workforus.forus.board.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

import site.workforus.forus.board.model.BoardPostDTO;
import site.workforus.forus.board.service.BoardPostService;

// 게시글 상세 페이지
@Controller
public class PostDetailController {
	@Autowired
	private BoardPostService postService;
	
//	public BoardPostDTO getDetailData(Model model, HttpSession session
//									, @RequestParam int postId) { // 어떤 post id인지 알아야 한다.
//		
//		// 게시판 데이터를 가지고 와야 한다.
//		BoardPostDTO postData = postService.getPostData(postId);// postId -> 해당 게시글 데이터를 가져온다.
//		
//		return ;
//	}
	
}
