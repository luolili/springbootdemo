package com.luo.netty.webwoscket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class WsServer {

    private static class SingletonWsServer {
        public static WsServer instace = new WsServer();
    }

    public static WsServer getInstance() {
        return SingletonWsServer.instace;
    }

    //定义一个主线程组，只接受请求
    EventLoopGroup mainGroup;
    //定义一个从线程组，处理请求
    EventLoopGroup subGroup;
    //启动人
    ServerBootstrap serverBootstrap;
    ChannelFuture channelFuture;

    public WsServer() {
        mainGroup = new NioEventLoopGroup();
        subGroup = new NioEventLoopGroup();
        serverBootstrap = new ServerBootstrap();
        serverBootstrap.group(mainGroup, subGroup)
                //双向通道
                .channel(NioServerSocketChannel.class)
                //子处理人，处理 workerGroup
                .childHandler(new WsServerInitializer());
    }

    public void start() {
        channelFuture = serverBootstrap.bind(8080);
        log.info("start");
    }
}
