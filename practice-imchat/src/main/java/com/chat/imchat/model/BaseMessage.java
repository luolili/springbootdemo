package com.chat.imchat.model;

import lombok.Data;

import java.util.Date;

/**
 * 对消息的建模
 */
@Data
public class BaseMessage {
    //消息类型
    private String type;
    //消息内容
    private String context;
    //消息发送者
    private String sender;
    //接受者类型
    private String toType;
    //接受者
    private String receiver;

    //发送时间
    private Date date;

}
