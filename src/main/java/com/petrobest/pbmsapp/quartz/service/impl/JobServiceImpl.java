package com.petrobest.pbmsapp.quartz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.petrobest.pbmsapp.quartz.dao.JobDao;
import com.petrobest.pbmsapp.quartz.domain.Job;
import com.petrobest.pbmsapp.quartz.service.JobService;
import com.petrobest.pbmsapp.quartz.utils.ScheduleUtils;
import lombok.extern.slf4j.Slf4j;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class JobServiceImpl extends ServiceImpl<JobDao, Job> implements JobService {

    @Autowired
    private Scheduler scheduler;

    @Autowired
    JobDao jobDao;

    /**
     * 项目启动时，初始化定时器
     */
    @PostConstruct
    public void init() {
        List<Job> scheduleJobList = list();
        // 如果不存在，则创建
        scheduleJobList.forEach(scheduleJob -> {
            CronTrigger cronTrigger = ScheduleUtils.getCronTrigger(scheduler, scheduleJob.getJobId());
            if (cronTrigger == null) {
                try {
                    ScheduleUtils.createScheduleJob(scheduler, scheduleJob);
                } catch (SchedulerException e) {
                    log.error("创建定时任务失败", e);
                }
            } else {
                try {
                    ScheduleUtils.updateScheduleJob(scheduler, scheduleJob);
                    log.info("更新任务");
                } catch (SchedulerException e) {
                    log.error("更新定时任务失败", e);
                }
            }
        });
    }

    @Override
    @Transactional
    public boolean addJob(Job job) {
        job.setCreateTime(new Date());
        job.setStatus(Job.ScheduleStatus.PAUSE.getValue());
        boolean save = this.save(job);
        if (!save) return save;
        try {
            ScheduleUtils.createScheduleJob(scheduler, job);
        } catch (SchedulerException e) {
            log.error("创建定时任务失败", e);
            return false;
        }
        return save;
    }

    @Override
    public boolean updateJob(Job job) {
        boolean update = updateById(job);
        if (!update) return update;
        try {
            ScheduleUtils.updateScheduleJob(scheduler, getById(Long.valueOf(job.getJobId())));
        } catch (SchedulerException e) {
            log.error("修改定时任务失败", e);
            return false;
        }
        return update;
    }

    @Override
    @Transactional
    public boolean run(List<String > jobIds) {
        for (String jobId : jobIds) {
            try {
                ScheduleUtils.run(scheduler, getById(Long.valueOf(jobId)));
            } catch (SchedulerException e) {
                log.error("执行定时任务失败", e);
                return false;
            }
        }
        return true;
    }

    @Override
    @Transactional
    public boolean pause(List<String > jobIds) {
        for (String jobId : jobIds) {
            try {
                ScheduleUtils.pauseJob(scheduler, jobId);
            } catch (SchedulerException e) {
                log.error("暂停定时任务失败", e);
                return false;
            }
        }
        Job job = new Job();
        job.setStatus(Job.ScheduleStatus.PAUSE.getValue());
        jobDao.updateBatchByIds(job, jobIds);
        return true;
    }


    @Override
    @Transactional
    public boolean resume(List<String > jobIds) {
        for (String jobId : jobIds) {
            try {
                ScheduleUtils.resumeJob(scheduler, jobId);
            } catch (SchedulerException e) {
                log.error("恢复定时任务失败", e);
                return false;
            }
        }
        Job job = new Job();
        job.setStatus(Job.ScheduleStatus.NORMAL.getValue());
        jobDao.updateBatchByIds(job, jobIds);
        return true;
    }
}
