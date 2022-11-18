package com.petrobest.pbmsapp.rabbitmq.config;

import com.petrobest.pbmsapp.rabbitmq.service.RabbitWebsocketConsumer;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Autowired
    ConnectionFactory connectionFactory;


    @Bean("rabbitTemplate")
    public RabbitTemplate rabbitTemplate() {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());  //消息发送者 json数据类型转换器
        return rabbitTemplate;
    }

    //消息接收者，将数组转化为json
    @Bean("rabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory() {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setPrefetchCount(0); //
        return factory;
    }


    //配置多个消费者
    @Bean
    public RabbitWebsocketConsumer websocketConsumer() {
        return new RabbitWebsocketConsumer("cons1");
    }

    @Bean
    public RabbitWebsocketConsumer websocketConsumer2() {
        return new RabbitWebsocketConsumer("cons2");
    }

    @Bean
    public RabbitWebsocketConsumer websocketConsumer3() {
        return new RabbitWebsocketConsumer("cons3");
    }
}
