package com.luo.netty;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.luo.netty.dao")
public class NettyApp {
    public static void main(String[] args) {
        SpringApplication.run(NettyApp.class, args);
    }
}
