package com.petrobest.pbmsapp.rabbitmq.service;

import com.petrobest.pbmsapp.fbox.config.FboxPropertiesConfig;
import com.petrobest.pbmsapp.fbox.service.MonitorPointService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;


//@Component
@Slf4j
public class RabbitPersistenceConsumer {

    @Autowired
    private FboxPropertiesConfig config;

    @Autowired
    private MonitorPointService service;

//    @RabbitListener(queues = "persistence-queue")
    public void consume(Map msg) {

//        log.info("收到消息：" + msg);
//        //头信息
//        Map headers = (LinkedHashMap) msg.get("headers");
//        //获取盒子序号
//        String receivedTopic = headers.get("mqtt_receivedTopic").toString();
//        String prefix = config.getTopicPrefix();
//        String suffix = config.getTopicSuffix();
//        int p = receivedTopic.indexOf(prefix) + prefix.length();
//        int s = receivedTopic.indexOf(suffix);
//        String boxid = receivedTopic.substring(p, s);
//
//        JSONObject payloadJson = JSONObject.parseObject((String) msg.get("payload"));
//        //补全信息 ，转为MonitorPoint
//        payloadJson.put("deviceId", boxid);//添加设备序号
//        payloadJson.put("id", snowFlake.nextId());//生成随机id
//
//
//        //内容信息
//        MonitorPointDO monitorPointDO = JSON.parseObject(payloadJson.toJSONString(), MonitorPointDO.class); //JSON转Map
//        log.info("consumer1 h1 接到payload:" + monitorPointDO);
//        long insertID = service.save(monitorPointDO);
//        log.info("插入成功，行id："+insertID);

    }


}
