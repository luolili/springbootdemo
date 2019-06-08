package com.example.springreactordemo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

/**
 * simple reactor demo
 */
@SpringBootApplication
@Slf4j
public class SpringreactordemoApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringreactordemoApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {


        Flux.range(1, 6)
                .publishOn(Schedulers.elastic())
                .doOnRequest(n -> log.info("request {} number", n))
                .doOnComplete(() -> log.info("Publish complete 1"))
                //open it: map in the elastic thres pool,not main thread
                //.publishOn(Schedulers.elastic())
                .map(i -> {
                    log.info("publish {}, {}", Thread.currentThread(), i);
                    //return 10 / (i - 3);
                    return i;
                })
                .doOnComplete(() -> log.info("Publish complete 1"))

                //open it: subscribe on single
                .subscribeOn(Schedulers.single())
                .subscribe(i -> log.info("Subscribe {}: {}", Thread.currentThread(), i),
                        e -> log.error("error {}", e.toString()),
                        () -> log.info("Subscriber COMPLETE"),
                        s -> s.request(4)
                );

    }
}
