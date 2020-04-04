package com.luo.netty;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 客户端发送一个请求，服务器返回信息
 * 主从线程模型
 * 启动后 页面访问localhost:8088
 */
public class HelloServer {
    public static void main(String[] args) {
        //定义一个主线程组，只接受请求
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        //定义一个从线程组，处理请求
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        //启动人
        ServerBootstrap serverBootstrap = new ServerBootstrap();
        //netty 服务器
        serverBootstrap.group(bossGroup, workerGroup)
                //双向通道
                .channel(NioServerSocketChannel.class)
                //子处理人，处理 workerGroup
                .childHandler(new HelloServerInitializer());
        try {
            ChannelFuture channelFuture = serverBootstrap.bind(8088).sync();
            // 关闭客户端对应的通道
            channelFuture.channel().closeFuture().sync();
        } catch (Exception e) {

        } finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }

    }
}
