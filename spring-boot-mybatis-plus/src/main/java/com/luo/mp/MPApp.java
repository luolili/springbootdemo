package com.luo.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.luo.mp.mapper")
public class MPApp {

    public static void main(String[] args) {
        SpringApplication.run(MPApp.class);
    }
}
