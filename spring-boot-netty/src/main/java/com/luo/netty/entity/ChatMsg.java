package com.luo.netty.entity;

import java.io.Serializable;

/**
 * (ChatMsg)实体类
 *
 * @author makejava
 * @since 2020-04-05 11:33:27
 */
public class ChatMsg implements Serializable {
    private static final long serialVersionUID = 651216320761687367L;

    private String id;

    private String sendUserId;

    private String acceptUserId;

    private Integer msg;
    /**
     * 消息是否读了
     */
    private String signFlag;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSendUserId() {
        return sendUserId;
    }

    public void setSendUserId(String sendUserId) {
        this.sendUserId = sendUserId;
    }

    public String getAcceptUserId() {
        return acceptUserId;
    }

    public void setAcceptUserId(String acceptUserId) {
        this.acceptUserId = acceptUserId;
    }

    public Integer getMsg() {
        return msg;
    }

    public void setMsg(Integer msg) {
        this.msg = msg;
    }

    public String getSignFlag() {
        return signFlag;
    }

    public void setSignFlag(String signFlag) {
        this.signFlag = signFlag;
    }

}