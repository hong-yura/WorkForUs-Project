package site.workforus.forus.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import site.workforus.forus.chat.service.ChatMessageService;
import site.workforus.forus.chat.service.ChatParticipantService;
import site.workforus.forus.chat.service.ChatRoomService;
import site.workforus.forus.employee.model.LoginVO;

import java.util.List;

@Controller
@RequestMapping(value = "/chat")
@Slf4j
public class ChatRoomController {
    @Autowired
    private ChatRoomService chatRoomService;

    @Autowired
    private ChatMessageService chatMessageService;

    @Autowired
    private ChatParticipantService chatParticipantService;

    @PostMapping(value = "/room/add", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String insertChatRoom(@RequestParam("title") String chatTitle, @RequestParam("members[]") List<String> memberList) {
        log.info("chatTitle : " + chatTitle);

        JSONObject json = new JSONObject();

        chatRoomService.createChatRoom(chatTitle);

        String chatRoomNo = chatRoomService.findByRoomNo(chatTitle);

        System.out.println(chatRoomNo);
        System.out.println(memberList);

//        chatParticipantService.insertChatParticipants(memberList, chatRoomNo);

        return json.toJSONString();
    }

    @GetMapping(value = "/room")
    public String getRoom(@RequestParam("roomId") String roomId, Model model) {
        log.info("# get Chat Room, roomID : " + roomId);

        LoginVO user = (LoginVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("userid", user.getUsername());
        model.addAttribute("empDtoDatas", chatRoomService.selectEmployeeAll());
        model.addAttribute("empDto", chatRoomService.selectEmployeeInfo(user.getUsername()));
        model.addAttribute(
        "chatRoomDatas", chatRoomService.findRoomAll());
        model.addAttribute("room", chatRoomService.findByRoomId(roomId));
        model.addAttribute("message", chatMessageService.findMessageById(roomId));
        return "chat/chat";
    }
}
