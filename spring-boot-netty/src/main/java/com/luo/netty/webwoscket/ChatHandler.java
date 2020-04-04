package com.luo.netty.webwoscket;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.time.LocalDateTime;

/**
 * TextWebSocketFrame:处理文本对象
 * frame 是ws的消息载体，浏览器ws 里面的Header 右边的Message
 */
public class ChatHandler extends SimpleChannelInboundHandler<TextWebSocketFrame> {
    //所有的客户端 channel
    private static final ChannelGroup clients = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, TextWebSocketFrame msg) throws Exception {
        //客户端传输过来的消息的内容
        String text = msg.text();
        System.out.println("msg:" + text);
        //把消息发送到 channel
//        for (Channel cg : clients) {
//            //不能是 string 作为参数
//            cg.writeAndFlush(new TextWebSocketFrame("服务器在："+ LocalDateTime.now()+", 消息："+text));
//        }
        //or
        clients.writeAndFlush(new TextWebSocketFrame("服务器在：" + LocalDateTime.now() + ", 消息：" + text));
    }

    @Override
    public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
        Channel ch = ctx.channel();
        //把所有客户端放入组里
        clients.add(ch);
    }

    @Override
    public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
        //浏览器关闭/刷新,可以不写（自动删除）
        clients.remove(ctx.channel());
        System.out.print("remove long user id:" + ctx.channel().id().asLongText() + ", ");
        System.out.println("remove short user id:" + ctx.channel().id().asShortText());
    }
}