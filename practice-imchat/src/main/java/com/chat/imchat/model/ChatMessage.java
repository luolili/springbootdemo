package com.chat.imchat.model;

import lombok.Data;

/**
 * 聊天信息的建模
 */
@Data
public class ChatMessage {
    //用户名
    private String username;
    //昵称
    private String nickname;
    //化身
    private String avatar;
    //内容
    private String content;
    //发送时间
    private String sendTime;

}
