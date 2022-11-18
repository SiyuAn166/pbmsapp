package com.petrobest.pbmsapp.quartz.utils;

import com.petrobest.pbmsapp.common.config.ApplicationContextRegister;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

@Slf4j
public class ScheduleRunnable implements Runnable {

    private Object target;
    private Method method;
    private String params;

    public ScheduleRunnable(Object target, String methodName, String params) throws NoSuchMethodException {
        this.target = ApplicationContextRegister.getBean(target.toString());
        this.params = params;
        if (StringUtils.isNotBlank(params)) {
            this.method = this.target.getClass().getDeclaredMethod(methodName, String.class);
        } else {
            this.method = this.target.getClass().getDeclaredMethod(methodName);
        }
    }

    @Override
    public void run() {
        try {
            ReflectionUtils.makeAccessible(method);
            if (StringUtils.isNotBlank(params)) {
                method.invoke(target, params);
            } else {
                method.invoke(target);
            }
        } catch (Exception e) {
            log.error("执行定时任务失败", e);
        }
    }
}
