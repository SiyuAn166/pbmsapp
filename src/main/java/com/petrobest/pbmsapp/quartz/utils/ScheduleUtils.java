package com.petrobest.pbmsapp.quartz.utils;

import org.quartz.*;
import com.petrobest.pbmsapp.quartz.domain.Job;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 定时任务工具类
 *
 * @author MrBird
 */
public class ScheduleUtils {

    private static Logger log = LoggerFactory.getLogger(ScheduleUtils.class);

    protected ScheduleUtils() {

    }

    private static final String JOB_NAME = "TASK_";

    /**
     * 获取触发器key
     */
    private static TriggerKey getTriggerKey(String jobId) {
        return TriggerKey.triggerKey(JOB_NAME + jobId);
    }

    /**
     * 获取jobKey
     */
    private static JobKey getJobKey(String jobId) {
        return JobKey.jobKey(JOB_NAME + jobId);
    }

    /**
     * 获取表达式触发器
     */
    public static CronTrigger getCronTrigger(Scheduler scheduler, String jobId) {
        try {
            return (CronTrigger) scheduler.getTrigger(getTriggerKey(jobId));
        } catch (SchedulerException e) {
            log.error("获取Cron表达式失败", e);
        }
        return null;
    }

    /**
     * 创建定时任务
     */
    public static void createScheduleJob(Scheduler scheduler, Job scheduleJob) throws SchedulerException {
        // 构建job信息
        JobDetail jobDetail = JobBuilder.newJob(ScheduleJob.class).withIdentity(getJobKey(scheduleJob.getJobId()))
                .build();
        log.info("构建job信息");
        // 表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                .withMisfireHandlingInstructionDoNothing();
        log.info("表达式调度构建器");
        // 按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().withIdentity(getTriggerKey(scheduleJob.getJobId()))
                .withSchedule(scheduleBuilder).build();
        log.info("按新的cronExpression表达式构建一个新的trigger");
        // 放入参数，运行时的方法可以获取
        jobDetail.getJobDataMap().put(Job.JOB_PARAM_KEY, scheduleJob);
        log.info("放入参数，运行时的方法可以获取");
        scheduler.scheduleJob(jobDetail, trigger);

        // 暂停任务
        if (scheduleJob.getStatus().equals(Job.ScheduleStatus.PAUSE.getValue())) {
            pauseJob(scheduler, scheduleJob.getJobId());
        }
    }

    /**
     * 更新定时任务
     */
    public static void updateScheduleJob(Scheduler scheduler, Job scheduleJob) throws SchedulerException {
        TriggerKey triggerKey = getTriggerKey(scheduleJob.getJobId());

        // 表达式调度构建器
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(scheduleJob.getCronExpression())
                .withMisfireHandlingInstructionDoNothing();

        CronTrigger trigger = getCronTrigger(scheduler, scheduleJob.getJobId());

        if (trigger == null) {
            throw new SchedulerException("获取Cron表达式失败");
        } else {
            // 按新的cronExpression表达式重新构建trigger
            trigger = trigger.getTriggerBuilder().withIdentity(triggerKey).withSchedule(scheduleBuilder).build();
            // 参数
            trigger.getJobDataMap().put(Job.JOB_PARAM_KEY, scheduleJob);
        }

        scheduler.rescheduleJob(triggerKey, trigger);

        // 暂停任务
        if (scheduleJob.getStatus().equals(Job.ScheduleStatus.PAUSE.getValue())) {
            pauseJob(scheduler, scheduleJob.getJobId());
        }


    }

    /**
     * 立即执行任务
     */
    public static void run(Scheduler scheduler, Job scheduleJob) throws SchedulerException {
        // 参数
        JobDataMap dataMap = new JobDataMap();
        dataMap.put(Job.JOB_PARAM_KEY, scheduleJob);

        scheduler.triggerJob(getJobKey(scheduleJob.getJobId()), dataMap);
    }

    /**
     * 暂停任务
     */
    public static void pauseJob(Scheduler scheduler, String jobId) throws SchedulerException {
        scheduler.pauseJob(getJobKey(jobId));
    }

    /**
     * 恢复任务
     */
    public static void resumeJob(Scheduler scheduler, String jobId) throws SchedulerException {
        scheduler.resumeJob(getJobKey(jobId));
    }

    /**
     * 删除定时任务
     */
    public static void deleteScheduleJob(Scheduler scheduler, String jobId) {
        try {
            scheduler.deleteJob(getJobKey(jobId));
        } catch (SchedulerException e) {
            log.error("删除定时任务失败", e);
        }
    }
}
