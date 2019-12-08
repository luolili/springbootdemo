package com.example.springwebclientdemo.api;

import com.example.springwebclientdemo.model.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@ApiServer("http://localhost:8080/user")
public interface IUserApi {

    @GetMapping("/get")
    Flux<User> getAllUser();

    @GetMapping("/get/{id}")
    Mono<User> getUserById(@PathVariable String id);
}
