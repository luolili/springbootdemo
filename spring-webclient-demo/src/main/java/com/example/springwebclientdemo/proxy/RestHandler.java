package com.example.springwebclientdemo.proxy;

public interface RestHandler {
    void init(ServerInfo serverInfo);

    Object invokeRest(MethodInfo methodInfo);


}
