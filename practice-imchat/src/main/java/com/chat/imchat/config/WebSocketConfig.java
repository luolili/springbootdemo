package com.chat.imchat.config;

import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;
import org.springframework.web.socket.handler.WebSocketHandlerDecorator;
import org.springframework.web.socket.handler.WebSocketHandlerDecoratorFactory;
import lombok.extern.log4j.Log4j;

/**
 * websocket的配置类
 */
@Configuration
@EnableWebSocketMessageBroker//from web.socket.config
//@Log4j//lombok 日志
@Slf4j
public class WebSocketConfig extends AbstractWebSocketMessageBrokerConfigurer {


    //注册end point
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/any-socket").withSockJS();
    }

    //配置消息的缓存代理broker
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic", "/queue");
        //super.configureMessageBroker(registry);
    }

    @Override
    public void configureWebSocketTransport(final WebSocketTransportRegistration registration) {

        //-1 添加装饰工厂
        new WebSocketHandlerDecoratorFactory() {
            @Override
            public WebSocketHandler decorate(final WebSocketHandler handler) {


                return new WebSocketHandlerDecorator(handler) {
                    //在链接建立之后
                    @Override
                    public void afterConnectionEstablished(final WebSocketSession session) throws Exception {
                        //从session中获取username
                        String username = session.getPrincipal().getName();
                        log.info("online:{}", username);//打印上线的用户名

                        super.afterConnectionEstablished(session);
                    }

                    @Override
                    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
                        String username = session.getPrincipal().getName();
                        log.info("offline:{}", username);//打印下线的用户名

                        super.afterConnectionClosed(session, closeStatus);
                    }
                };
            }
        };
        super.configureWebSocketTransport(registration);
    }
}
