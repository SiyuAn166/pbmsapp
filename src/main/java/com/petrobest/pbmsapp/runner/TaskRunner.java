package com.petrobest.pbmsapp.runner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

//@Component
public class TaskRunner implements ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void run(ApplicationArguments args) throws Exception {
       /* JobService2 jobService = ApplicationContextRegister.getBean(JobService2.class);
        Job job = jobService.findJob(123L);

        Scheduler scheduler = ApplicationContextRegister.getBean(Scheduler.class);
        ScheduleUtils.run(scheduler, job);
        logger.info("任务启动成功");*/

    }
}
