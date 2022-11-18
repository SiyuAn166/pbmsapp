package com.petrobest.pbmsapp.quartz.task;

import com.alibaba.fastjson.JSON;
import com.petrobest.pbmsapp.common.config.ApplicationContextRegister;
import com.petrobest.pbmsapp.fbox.domain.MonitorPointDO;
import com.petrobest.pbmsapp.fbox.service.MonitorPointService;
import com.petrobest.pbmsapp.fbox.utils.FboxConstant;
import com.petrobest.pbmsapp.fbox.utils.FboxUtils;
import com.petrobest.pbmsapp.redis.utils.RedisUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component("mDataPersistTask")
public class MDataPersistTask {

    private Logger logger = LoggerFactory.getLogger(getClass());


    @Autowired
    private MonitorPointService service;

    public void executeTask(String topic) {
        RedisUtils redisUtils = ApplicationContextRegister.getBean(RedisUtils.class);
        Object hget = redisUtils.hget(FboxConstant.FBOX_REDIS_NAMESPACE, topic);
        if (hget != null && StringUtils.isNotBlank(hget.toString())) {
            MonitorPointDO monitorPointDO = JSON.parseObject(hget.toString(), MonitorPointDO.class);
            monitorPointDO.setDeviceId(FboxUtils.getFboxIdByTopic(topic));
            monitorPointDO.setCreateTime(new Date());
            service.save(monitorPointDO);
            logger.info("   ---定时任务mDataPersistTask：持久化成功");
        } else {
            logger.info("reids中无数据.");
        }

    }
}
