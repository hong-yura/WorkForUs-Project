package site.workforus.forus.chat.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import site.workforus.forus.employee.model.LoginVO;

@Controller
@Slf4j
@RequestMapping(value="/chat")
public class ChatController {

    @GetMapping("")
    public String chat(Model model) {
        LoginVO user = (LoginVO) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("@ChatController, chat GET()");

        model.addAttribute("userid", user.getUsername());

        return "chat/chat";
    }
}
