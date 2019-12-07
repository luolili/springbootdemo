package com.example.springwebfluxwaiterdemo.luo.controller;

import com.example.springwebfluxwaiterdemo.luo.model.Coffee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
public class TestController {
    /**
     * 2019-12-07 17:28:41.234  INFO 11436 ---    : get1 st
     * 2019-12-07 17:29:01.235  INFO 11436 ---      : get1 end,some
     *
     * @return
     */
    @GetMapping(path = "/1")
    public String halo() {
        log.info("get1 st");
        String res = doSomething();
        log.info("get1 end,{}", res);
        return res;
    }

    private String doSomething() {
        try {
            TimeUnit.SECONDS.sleep(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "some";
    }

    /**
     * 2019-12-07 17:29:14.208  INFO 11436 ---   : get1 st
     * 2019-12-07 17:29:14.211  INFO 11436 ---       : get1 end
     *
     * @return
     */
    @GetMapping(path = "/2")
    public Mono<String> halo2() {
        log.info("get1 st");
        Mono<String> res = Mono.fromSupplier(this::doSomething);
        log.info("get1 end");
        return res;
    }

}
