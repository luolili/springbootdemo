package com.chat.imchat.model.controller;

import com.alibaba.fastjson.JSON;
import com.chat.imchat.model.BaseMessage;
import com.chat.imchat.model.ChatMessage;
import com.chat.imchat.mongo.model.User;
import com.chat.imchat.mongo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.messaging.simp.SimpMessagingTemplate;

import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 发送消息
 */
@Controller
public class ChatController {

    private static final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd HH:mm");

    //自动注入消息发送模板
    @Autowired
    private SimpMessagingTemplate simpMessageTemplate;

    @Autowired
    private UserService userService;


    //Principal from java.security
    @MessageMapping("/all")
    public void all(Principal principal, String message) {
        ChatMessage chatMessage = this.createMessage(principal.getName(), message);
        //以json字符串的形式把消息发送出去
        simpMessageTemplate.convertAndSend("/topic/notice", JSON.toJSONString(chatMessage));
    }


    public void chat(Principal principal, String message) {
        BaseMessage baseMessage = JSON.parseObject(message, BaseMessage.class);
        baseMessage.setSender(principal.getName());
        this.send(baseMessage);

    }

    @Async
    public void send(BaseMessage baseMessage) {
        baseMessage.setDate(new Date());
        ChatMessage chatMessage = this.createMessage(baseMessage.getSender(), baseMessage.getContext());


        simpMessageTemplate.convertAndSendToUser(baseMessage.getReceiver(), "/topic/chat", JSON.toJSONString(chatMessage));

    }

    private ChatMessage createMessage(String username, String message) {
        ChatMessage chatMessage = new ChatMessage();
        chatMessage.setUsername(username);
        User user = userService.getByUsername(username);
        chatMessage.setAvatar(user.getAvatar());
        chatMessage.setNickname(user.getNickname());

        chatMessage.setContent(message);
        chatMessage.setSendTime(simpleDateFormat.format(new Date()));
        return chatMessage;

    }


}
