package com.luo.springbootdemo.task;

import java.text.SimpleDateFormat;
import java.util.Date;

//@Component//for scanning it
public class TestTask {
    private SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");

    //@Scheduled(fixedRate = 3000)//execute it every 3 second
    public void reportCurrentTime() {
        System.out.println("current time is:" + format.format(new Date()));
    }
}
