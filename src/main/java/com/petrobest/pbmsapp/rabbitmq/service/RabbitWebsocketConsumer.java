package com.petrobest.pbmsapp.rabbitmq.service;

import com.petrobest.pbmsapp.fbox.utils.FboxConstant;
import com.petrobest.pbmsapp.redis.utils.RedisUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class RabbitWebsocketConsumer {

    private final StringBuilder stringBuilder = new StringBuilder();
    private String instanceName;

    public RabbitWebsocketConsumer() {
        this.instanceName = "cons0";
    }

    public RabbitWebsocketConsumer(String instanceName) {
        this.instanceName = instanceName;
    }


    @Autowired
    private SimpMessagingTemplate template;  //websocket推送客户端

    @Autowired
    private RedisUtils redisUtils;

    @RabbitListener(queues = "websocket-queue", containerFactory = "rabbitListenerContainerFactory")
    public void consume(Map msg) {
//        log.info(String.format("websocket consumer {} received: {}"), this.instanceName, msg);
        Map headers = (Map) msg.get("headers");
        String topic = (String) headers.get("mqtt_receivedTopic");
        String payload = (String) msg.get("payload");
        log.info(String.format("[{}].Send to stomp client. Topic: {}  payload: {}"), this.instanceName, topic, payload);

        template.convertAndSend(topic, payload);
//        log.info("Payload send to Websocket success.");

//        String redisKey = stringBuilder.append(FBOX_REDIS_NAMESPACE).append(topic).toString();
//        redisUtils.set(redisKey, payload, 300);
//        stringBuilder.setLength(0);
        redisUtils.hset(FboxConstant.FBOX_REDIS_NAMESPACE, topic, payload, 60);
//        log.info(String.format("Payload set into redis success with key: {}"), topic);
//        Object hget = redisUtils.hget(FBOX_REDIS_NAMESPACE, topic);
//        MonitorPointDO pointDO = JSON.parseObject(hget.toString(), MonitorPointDO.class);
//        log.info(pointDO.toString());
    }
}
