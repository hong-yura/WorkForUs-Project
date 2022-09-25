package site.workforus.forus;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import site.workforus.forus.board.model.BoardDTO;
import site.workforus.forus.board.model.BoardPostDTO;
import site.workforus.forus.board.service.BoardPostService;
import site.workforus.forus.board.service.BoardService;
import site.workforus.forus.employee.model.LoginVO;

@Controller
public class HomeController {

	@Autowired
	private BoardService boardService;
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model, HttpSession session, Authentication auth) {
		logger.info("Welcome home! The client locale is {}.", locale);
		
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		
		String formattedDate = dateFormat.format(date);
		
		
		// 로그인 데이터 가져오기
		LoginVO loginVo = (LoginVO)auth.getPrincipal();
		logger.info("getData(loginVo={})", loginVo);
				
		// 게시판 가져오기 -> navbar에 들어갈 내용
		List<BoardDTO> boardNav = boardService.selectAll(loginVo); // 사원에 해당하는 게시판 데이터를 가지고 옴
				
		
		session.setAttribute("boardList", boardNav);
		model.addAttribute("serverTime", formattedDate );
		
		return "home";
	}
	
}
