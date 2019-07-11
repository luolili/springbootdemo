package com.chat.imchat.api;

import com.chat.imchat.config.security.UserPrincipal;
import com.chat.imchat.mongo.model.User;
import com.chat.imchat.mongo.service.RelationService;
import com.chat.imchat.mongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 常用接口
 */
@RestController
@RequestMapping("/api/common")
public class CommonApi {

    @Autowired
    private UserService userService;

    @Autowired
    private RelationService relationService;


    //从HttpServletRequest 里面获取表单里面的属性值
    @PostMapping("/register")
    public boolean register(HttpServletRequest request) {
        String username = request.getParameter("username");

        String password = request.getParameter("password");
        String nickname = request.getParameter("nickname");
        User user = new User(username, password, nickname);
        return userService.addUser(user);
    }

    //添加好友
    @PostMapping("/addFriend")
    public boolean addFriend(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestParam String friend) {
        return relationService.addFriend(userPrincipal.getUsername(), friend);
    }
}
