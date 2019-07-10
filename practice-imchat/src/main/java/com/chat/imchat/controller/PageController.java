package com.chat.imchat.controller;

import com.chat.imchat.config.security.UserPrincipal;
import com.chat.imchat.mongo.model.User;
import com.chat.imchat.mongo.service.RelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Controller
public class PageController {

    @Autowired
    private RelationService relationService;

    @GetMapping("/")
    public String index() {
        return "index";
    }

    @GetMapping("/chat")
    public String chat(@AuthenticationPrincipal UserPrincipal userPrincipal, Model model) {
        model.addAttribute("user", userPrincipal);
        String username = userPrincipal.getUsername();
        List<User> freinds = relationService.listFriend(username);

        model.addAttribute("friends", freinds);
        return "chat";

    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }
}
