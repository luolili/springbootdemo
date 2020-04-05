package com.luo.netty.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * (FriendsRequest)实体类
 *
 * @author makejava
 * @since 2020-04-05 11:33:42
 */
public class FriendsRequest implements Serializable {
    private static final long serialVersionUID = -79044355710848469L;

    private String id;

    private String sendUserId;

    private String acceptUserId;

    private Date requestDateTime;


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

    public Date getRequestDateTime() {
        return requestDateTime;
    }

    public void setRequestDateTime(Date requestDateTime) {
        this.requestDateTime = requestDateTime;
    }

}