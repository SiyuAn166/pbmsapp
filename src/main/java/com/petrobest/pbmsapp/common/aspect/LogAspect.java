package com.petrobest.pbmsapp.common.aspect;

import com.alibaba.fastjson.JSON;
import com.petrobest.pbmsapp.common.annotation.Log;
import com.petrobest.pbmsapp.common.utils.HttpContextUtils;
import com.petrobest.pbmsapp.common.utils.IPUtils;
import com.petrobest.pbmsapp.common.utils.JSONUtils;
import com.petrobest.pbmsapp.common.utils.ShiroUtils;
import com.petrobest.pbmsapp.system.domain.LogDO;
import com.petrobest.pbmsapp.system.domain.UserDO;
import com.petrobest.pbmsapp.system.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

@Aspect
@Component
@Slf4j
public class LogAspect {
    @Autowired
    private LogService logService;

    @Pointcut("@annotation(com.petrobest.pbmsapp.common.annotation.Log)")
    public void logPointCut() {
    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        // 执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;
        //异步保存日志
        saveLog(point, time);
        return result;
    }

    void saveLog(ProceedingJoinPoint joinPoint, long time) throws InterruptedException {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogDO sysLog = new LogDO();
        Log alog = method.getAnnotation(Log.class);
        if (alog != null) {
            // 注解上的描述
            sysLog.setOperation(alog.value());
        }
        // 请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(className + "." + methodName + "()");
        // 请求的参数
        Object[] args = joinPoint.getArgs();
        try {
            String params = JSON.toJSONString(args);
//            log.info(String.format("params:{}"), params);
            // 获取request
            HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
            // 设置IP地址
            sysLog.setIp(IPUtils.getIpAddr(request));
            // 用户名

            sysLog.setUserId(ShiroUtils.getUserId());
            sysLog.setUsername(ShiroUtils.getUser().getUsername());
            sysLog.setTime((int) time);
            // 系统当前时间
            Date date = new Date();
            sysLog.setCreateTime(date);
            sysLog.setParams(params);
            // 保存系统日志
            logService.save(sysLog);

        } catch (Exception e) {
            log.error("log aspect error");
            e.printStackTrace();
        }

    }
}
