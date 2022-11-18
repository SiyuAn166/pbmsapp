package com.petrobest.pbmsapp.quartz.utils;

import com.petrobest.pbmsapp.quartz.domain.Job;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Slf4j
public class ScheduleJob extends QuartzJobBean {
    private ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        Job scheduleJob = (Job) jobExecutionContext.getMergedJobDataMap().get(Job.JOB_PARAM_KEY);

        long startTime = System.currentTimeMillis();
        try {
            // 执行任务
            log.info("任务准备执行，任务ID：{}", scheduleJob.getJobId());
            ScheduleRunnable task = new ScheduleRunnable(scheduleJob.getBeanName(), scheduleJob.getMethodName(), scheduleJob.getParams());

            Future<?> future = service.submit(task);
            future.get();
            long times = System.currentTimeMillis() - startTime;

            log.info("任务执行完毕，任务ID：{} 总共耗时：{} 毫秒", scheduleJob.getJobId(), times);
        } catch (Exception e) {
            log.error("任务执行失败，任务ID：" + scheduleJob.getJobId(), e);
        }

    }
}
