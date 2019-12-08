package com.example.springwebclientdemo.api;

import com.example.springwebclientdemo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class TestWeb {
    @Autowired
    IUserApi userApi;

    @GetMapping("/")
    public void test() {
        Flux<User> users = userApi.getAllUser();

        users.subscribe(System.out::println);
    }
}
