package com.example.firstdemo.config;

import com.example.firstdemo.pojo.User;
import com.example.firstdemo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collection;

//it is a java xml file,spring3
@Configuration//RouterFunctionConfiguration is a config obj
public class RouterFunctionConfiguration {


    /**
     * servlet request
     * servlet response
     * redefine servlet req/resp: ServerRequest/ServerResponse
     * it supports self-defined req/resp ,like Netty(web server)
     * <p>
     * define a get req: find all
     */
    @Bean
    @Autowired// autowiring method
    public RouterFunction<ServerResponse> findAll(UserRepository userRepository) {

        /**
         * flux 0-n collection
         * mono 0-1 collection
         * they asyc
         */
        Collection<User> users = userRepository.findAll();
        return RouterFunctions.route(RequestPredicates.GET("/person/find/all"), request -> {
            Mono<ServerResponse> response = null;
            Flux<User> userFlux = Flux.fromIterable(users);
            Mono<ServerResponse> body = ServerResponse.ok().body(userFlux, User.class);
            return body;
        });


    }
}
