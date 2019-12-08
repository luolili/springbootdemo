package com.example.springwebclientdemo.proxy;

import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.client.WebClient;

public class WebclientRestHandler implements RestHandler {
    private WebClient webClient;

    @Override
    public void init(ServerInfo serverInfo) {
        this.webClient = WebClient.create(serverInfo.getUrl());
    }

    @Override
    public Object invokeRest(MethodInfo methodInfo) {
        Object res = null;
        WebClient.ResponseSpec spec = this.webClient.method(methodInfo.getMethod())
                .uri(methodInfo.getUrl())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve();

        if (methodInfo.isReturnFlux()) {
            res = spec.bodyToFlux(methodInfo.getReturnElementType());
        } else {
            res = spec.bodyToMono(methodInfo.getReturnElementType());
        }

        return res;
    }
}
