package com.chat.imchat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.socket.config.annotation.EnableWebSocket;

@SpringBootApplication
@EnableWebSocket
public class ImchatApplication {

    public static void main(String[] args) {
        SpringApplication.run(ImchatApplication.class, args);
    }

}
