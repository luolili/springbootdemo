package com.luo.netty;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;

public class HelloServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //通过 chanel 获取 pipeline
        ChannelPipeline pipeline = ch.pipeline();
        // http 请求处理
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        pipeline.addLast("myHandler", new CustomHandler());

    }
}
