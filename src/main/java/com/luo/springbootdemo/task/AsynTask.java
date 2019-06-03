package com.luo.springbootdemo.task;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Component;

import java.util.concurrent.Future;

/**
 * if it is not asyn, total time is the sum time of three tasks
 * if not ,the total time is the max time of three tasks
 * scene:
 * email/instant message
 */
@Component
public class AsynTask {

    @Async
    public Future<Boolean> doTask01() throws Exception {
        long start = System.currentTimeMillis();
        Thread.sleep(1000);
        long end = System.currentTimeMillis();

        System.out.println("taks 01 take:" + (end - start) + "ms");
        return new AsyncResult<>(true);

    }

    @Async
    public Future<Boolean> doTask02() throws Exception {
        long start = System.currentTimeMillis();
        Thread.sleep(600);
        long end = System.currentTimeMillis();

        System.out.println("taks 02 take:" + (end - start) + "ms");
        return new AsyncResult<>(true);

    }

    @Async
    public Future<Boolean> doTask03() throws Exception {
        long start = System.currentTimeMillis();
        Thread.sleep(500);
        long end = System.currentTimeMillis();

        System.out.println("taks 03 take:" + (end - start) + "ms");
        return new AsyncResult<>(true);

    }
}
