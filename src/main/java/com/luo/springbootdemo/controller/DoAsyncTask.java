package com.luo.springbootdemo.controller;

import com.luo.springbootdemo.task.AsynTask;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.concurrent.Future;

//@RestController
//@RequestMapping("async")
public class DoAsyncTask {

    @Autowired
    private AsynTask task;

    @RequestMapping("taskTest")
    public String test() throws Exception {
        long start = System.currentTimeMillis();
        Future<Boolean> doTask01 = task.doTask01();
        Future<Boolean> doTask02 = task.doTask02();
        Future<Boolean> doTask03 = task.doTask03();

        //this loop will take some time
        while (!doTask01.isDone() || !doTask02.isDone() || !doTask03.isDone()) {
            if (doTask01.isDone() && doTask02.isDone() && doTask03.isDone()) {
                break;
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("total time of three tasks is :" + (end - start) + "ms");

        return "total time of three tasks is :" + (end - start);


    }

}
