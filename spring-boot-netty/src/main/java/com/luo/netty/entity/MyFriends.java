package com.luo.netty.entity;

import java.io.Serializable;

/**
 * (MyFriends)实体类
 *
 * @author makejava
 * @since 2020-04-05 11:33:42
 */
public class MyFriends implements Serializable {
    private static final long serialVersionUID = -33805550249826897L;

    private String id;

    private String myUserId;

    private String myFriendUserId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMyUserId() {
        return myUserId;
    }

    public void setMyUserId(String myUserId) {
        this.myUserId = myUserId;
    }

    public String getMyFriendUserId() {
        return myFriendUserId;
    }

    public void setMyFriendUserId(String myFriendUserId) {
        this.myFriendUserId = myFriendUserId;
    }

}