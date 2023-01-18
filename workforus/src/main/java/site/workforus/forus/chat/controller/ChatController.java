package site.workforus.forus.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import site.workforus.forus.chat.model.ChatRoomDTO;
import site.workforus.forus.chat.service.ChatRoomService;
import site.workforus.forus.employee.model.EmpDTO;
import site.workforus.forus.employee.model.LoginVO;

import java.util.List;

@Controller
@Slf4j
@RequestMapping(value="/chat")
public class ChatController {

    @Autowired
    private ChatRoomService chatRoomService;

    @GetMapping("")
    public String chat(Model model) {
        log.info("@ChatController, chat GET()");

        LoginVO user = (LoginVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<EmpDTO> empDtos = chatRoomService.selectEmployeeAll();
        List<ChatRoomDTO> chatRooms = chatRoomService.findRoomAll();

        System.out.println(chatRooms);

        model.addAttribute("userid", user.getUsername());
        model.addAttribute("empDtoDatas", empDtos);
        model.addAttribute("chatRoomDatas", chatRooms);

        return "chat/rooms";
    }
}
