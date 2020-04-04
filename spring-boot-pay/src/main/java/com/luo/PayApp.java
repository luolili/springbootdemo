package com.luo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})

@SpringBootApplication
@MapperScan("com.luo.mapper")
@EnableTransactionManagement
public class PayApp {
    public static void main(String[] args) {
        SpringApplication.run(PayApp.class, args);
    }
}
