package com.example.springwebfluxwaiterdemo.luo.controller.mongo;

import com.example.springwebfluxwaiterdemo.luo.model.mongo.User;
import com.example.springwebfluxwaiterdemo.luo.repo.mongo.UserRepository;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class UserHandler {
    final UserRepository userRepository;

    //构造方法注入：recommanded:跟spring的耦合度低
    public UserHandler(UserRepository userRepo) {
        this.userRepository = userRepo;
    }

    public Mono<ServerResponse> getAllUser(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(userRepository.findAll(), User.class);
    }

    public Mono<ServerResponse> createUser(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .body(userRepository.saveAll(userMono), User.class);
    }

    public Mono<ServerResponse> delUser(ServerRequest request) {
        Mono<User> userMono = request.bodyToMono(User.class);
        String id = request.pathVariable("id");


        return userRepository.findById(id)
                .flatMap(u -> userRepository.delete(u)
                        .then(ServerResponse.ok().build())
                        .switchIfEmpty(ServerResponse.notFound().build()));
    }
}
