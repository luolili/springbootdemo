package com.luo.springbootdemo.controller;

import com.luo.springbootdemo.pojo.User;
import com.luo.springbootdemo.util.JSONResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class UserController {
    @RequestMapping("/user")
    public User getUser() {

        User user = new User();
        user.setName("hihi");
        user.setBirthday(new Date());
        user.setAge(11);
        return user;


    }

    @RequestMapping("/userJson")
    public JSONResult getUserJson() {

        User user = new User();
        user.setName("hihi");
        user.setPassword("1122");
        user.setBirthday(new Date());
        user.setAge(11);
        user.setDesc("hello");
        return JSONResult.ok(user);


    }
}
