package com.luo.netty.webwoscket;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;

public class WsServerInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel ch) throws Exception {
        //---http 支持
        //通过 chanel 获取 pipeline
        ChannelPipeline pipeline = ch.pipeline();
        // http 请求处理,websocket 基于 http
        pipeline.addLast("httpServerCodec", new HttpServerCodec());
        // 大数据流处理
        pipeline.addLast("ChunkedWriteHandler", new ChunkedWriteHandler());
        //消息最大长度，对 HttpMessage 整合为 FullHttpRequest,常用 handler
        pipeline.addLast("HttpObjectAggregator", new HttpObjectAggregator(1024 * 64));
        //---websocket 支持
        //客户端访问的路由 /ws  ,处理 hand shaking close,ping pong
        pipeline.addLast("WebSocketClientProtocolHandler", new WebSocketServerProtocolHandler("/ws"));
        //处理用户发送的消息
        pipeline.addLast("msgHandler", new ChatHandler());
    }
}
