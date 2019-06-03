package com.luo.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
// open the task
@EnableScheduling
//open asyn task
@EnableAsync
/**
 * spring -> springboot ->springcloud
 * new feature:
 * 1. functional programming+ kotlin
 * 2. new programming model : wenflux
 * reactive stream:flow api(jdk9)
 * reactor(spring)
 * asyc nio/servlet3.1
 */
public class SpringbootdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootdemoApplication.class, args);
	}

}
