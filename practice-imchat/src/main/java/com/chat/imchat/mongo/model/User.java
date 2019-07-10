package com.chat.imchat.mongo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;
import java.util.Random;

@Data
@NoArgsConstructor
@ToString
public class User {

    //用户名
    private String username;
    //密码
    private String pasword;

    //昵称
    private String nickname;

    //头像
    private String avatar;

    //登陆时间
    private Date joinTime;

    public User(String username, String pasword, String nickname) {
        this.username = username;
        this.pasword = pasword;
        this.nickname = nickname;
        this.avatar = "/image/avatar/avatar" + new Random().nextInt(10) + ".jpg";
        this.joinTime = new Date();
    }
}
