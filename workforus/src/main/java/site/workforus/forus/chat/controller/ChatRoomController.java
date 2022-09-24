package site.workforus.forus.chat.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import site.workforus.forus.chat.model.ChatRoomDTO;
import site.workforus.forus.chat.service.ChatService;
import site.workforus.forus.employee.model.EmpDTO;
import site.workforus.forus.employee.model.LoginVO;

@Controller
@RequiredArgsConstructor
@RequestMapping(value="/chat")
public class ChatRoomController {
	
	private static final Logger logger = LoggerFactory.getLogger(ChatRoomController.class);
	
	@Autowired
	private ChatService chatService;
	
	// 채팅 채널 조회
	@GetMapping(value="")
	public String home(Model model, HttpSession session) {
		LoginVO loginVo = (LoginVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		List<EmpDTO> empDtos = chatService.selectEmployeeAll();
		List<ChatRoomDTO> chatRoom = chatService.selectChatRooms();
//		List<ChatRoomDTO> chatRoom = chatService.selectChatRooms(loginVo.getUsername());
		
		EmpDTO emp = new EmpDTO();
		
//		session.setAttribute("empId", loginVo.getUsername());
		
		model.addAttribute("empDtoDatas", empDtos);
		model.addAttribute("chatRoomDatas", chatRoom);
		model.addAttribute("loginEmp", loginVo);
		
		return "chat/chat";
	}
	
	
	// 채팅방 개설
	@PostMapping(value="/room/add", produces="application/json; charset=utf-8")
	@ResponseBody
	public String chatRoomAdd(@RequestParam String id, Model model) {
		JSONObject json = new JSONObject();
		chatService.createChatRoom(id);
		return json.toJSONString();
	}
	
	// 채팅방 들어가기
	@PostMapping(value="/room/roomId")
	public String chatRoomEnter(@RequestParam int id, Model model) {
		System.out.println(id);
		ChatRoomDTO chatRoomDto = chatService.findRoomById(id);
		System.out.println(chatRoomDto);
		model.addAttribute("chatRoomDto", chatRoomDto);
		return "chat/chat";
	}
	
	@GetMapping(value="/room/{roomId}",produces="application/json; charset=utf-8")
	@ResponseBody
	public String chatRoom(@PathVariable("roomId") int roomId) {
		JSONObject json = new JSONObject();
		return json.toJSONString();
	}
	
}