package com.example.springwebfluxwaiterdemo.luo.controller.mongo;

import com.example.springwebfluxwaiterdemo.luo.model.mongo.User;
import com.example.springwebfluxwaiterdemo.luo.repo.mongo.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 模式1：RequestMapping
 * 模式2：RouterFunction
 */
@Slf4j
@RestController
@RequestMapping("user")
public class UserWeb {

    final UserRepository userRepository;

    //构造方法注入：recommanded:跟spring的耦合度低
    public UserWeb(UserRepository userRepo) {
        this.userRepository = userRepo;
    }

    /**
     * 数组形式 返回数据
     *
     * @return
     */
    @RequestMapping("/")
    public Flux<User> getAll() {

        return userRepository.findAll();
    }

    //用 流的方式返回MediaType：springframework.http
    @RequestMapping(value = "/stream/all", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<User> getAll2() {

        return userRepository.findAll();
    }

    @GetMapping(value = "/add", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<User> add() {
        User user = new User();
        user.setAge(21);
        user.setName("df");
        user.setId("1");
        return userRepository.save(user);
    }

    //del
    @GetMapping(value = "/del/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id) {
        userRepository.deleteById(id);//无返回值
        Mono<User> userMono = userRepository.findById(id);
        //异步用flatMap
        return userMono.flatMap(u ->
                userRepository.delete(u)
                        .then(Mono.just(new ResponseEntity<Void>(HttpStatus.OK)))
                        .defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND))
        );

    }

    @PutMapping(value = "/edit/{id}")
    public Mono<ResponseEntity<User>> edit(@PathVariable String id,
                                           @RequestBody User user) {
        Mono<User> mono = userRepository.findById(id);
        return mono.flatMap(u -> {
            u.setAge(user.getAge());
            u.setName(user.getName());
            return userRepository.save(u);
        }).map(i ->
                new ResponseEntity<User>(HttpStatus.OK)
        ).defaultIfEmpty(new ResponseEntity<User>(HttpStatus.NOT_FOUND));

    }

    @GetMapping(value = "/get/{id}")
    public Mono<ResponseEntity<User>> edit(@PathVariable String id) {
        Mono<User> mono = userRepository.findById(id);
        return mono.map(i ->
                new ResponseEntity<User>(i, HttpStatus.OK)
        ).defaultIfEmpty(new ResponseEntity<User>(HttpStatus.NOT_FOUND));


    }
}
