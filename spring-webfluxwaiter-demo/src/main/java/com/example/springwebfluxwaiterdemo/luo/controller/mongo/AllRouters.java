package com.example.springwebfluxwaiterdemo.luo.controller.mongo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class AllRouters {

    @Bean
    public RouterFunction<ServerResponse> userRouter(UserHandler userHandler) {

        return RouterFunctions
                .nest(RequestPredicates.path("/user"),//类 request mapping
                        RouterFunctions
                                .route(RequestPredicates.GET("/rget"),//内部request mapping
                                        userHandler::getAllUser))
                .andRoute(RequestPredicates.POST("/")
                                .and(RequestPredicates.accept(MediaType.APPLICATION_JSON)),
                        userHandler::createUser)
                .andRoute(RequestPredicates.DELETE("/{id}"), userHandler::delUser);//方法
    }
}
