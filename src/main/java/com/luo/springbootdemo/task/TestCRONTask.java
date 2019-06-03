package com.luo.springbootdemo.task;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component//for scanning it
public class TestCRONTask {
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    //cron expression
    @Scheduled(cron = "4-40 * * * * ?")//execute it in range of 4-40 seconds
    public void reportCurrentTime() {
        System.out.println("current time is:" + format.format(new Date()));
    }
}
