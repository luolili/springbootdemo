package com.example.firstdemo.controller;

import com.example.firstdemo.pojo.User;
import com.example.firstdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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


    //---
    private UserRepository userRepository;

    //autowiring constructor
    @Autowired
    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/person/save")
    public User save(@RequestParam String name) {

        User user = new User();
        user.setName(name);
        if (userRepository.save(user)) {
            System.out.println("user :" + user);
        }
        return user;


    }
}
