package com.luo.netty.webwoscket;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class WsServer1 {
    public static void main(String[] args) {
        //定义一个主线程组，只接受请求
        EventLoopGroup mainGroup = new NioEventLoopGroup();
        //定义一个从线程组，处理请求
        EventLoopGroup subGroup = new NioEventLoopGroup();

        //启动人
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //netty 服务器
        serverBootstrap.group(mainGroup, subGroup)
                //双向通道
                .channel(NioServerSocketChannel.class)
                //子处理人，处理 workerGroup
                .childHandler(new WsServerInitializer());

        try {
            ChannelFuture channelFuture = serverBootstrap.bind(8080).sync();
            // 关闭客户端对应的通道,同步方式
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {

        } finally {
            mainGroup.shutdownGracefully();
            subGroup.shutdownGracefully();
        }

    }
}
