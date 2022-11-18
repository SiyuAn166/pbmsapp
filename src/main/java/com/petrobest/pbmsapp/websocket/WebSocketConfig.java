package com.petrobest.pbmsapp.websocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
@EnableConfigurationProperties(WebSocketProperties.class)
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Autowired
    private WebSocketProperties webSocketProperties;

    //注册STOMP协议的节点(endpoint),并映射指定的url
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) { //endPoint 注册协议节点,并映射指定的URl
        //注册一个名字为"ws" ws,并指定 SockJS协议。 允许跨域
        registry.addEndpoint(webSocketProperties.getEndpoints()).setAllowedOrigins("*").withSockJS();
    }


    //配置消息代理(Message Broker)
    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {//配置消息代理(message broker)
        //点对点式增加一个/queue 消息代理
        registry.enableSimpleBroker(webSocketProperties.getDestinationPrefixes());  //参数为 发布、订阅主题时的前缀, 发布、订阅时需要加上,, ""为无前缀主题,此前缀作为主题的一部分，不可省略
        //全局使用的订阅前缀（客户端订阅路径上会体现出来）
        registry.setApplicationDestinationPrefixes(webSocketProperties.getAppDestinationPrefix());
        //点对点使用的订阅前缀（客户端订阅路径上会体现出来），不设置的话，默认也是/user/
        registry.setUserDestinationPrefix(webSocketProperties.getUserDestinationPrefix());   //上边主题前缀必须添加此处的前缀，否则一对一通信不起作用

    }

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration webSocketTransportRegistration) {

    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration channelRegistration) {

    }

    @Override
    public void configureClientOutboundChannel(ChannelRegistration channelRegistration) {

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> list) {

    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> list) {

    }

    @Override
    public boolean configureMessageConverters(List<MessageConverter> list) {
        return false;
    }

}
