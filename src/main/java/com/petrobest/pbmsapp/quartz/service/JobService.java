package com.petrobest.pbmsapp.quartz.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.petrobest.pbmsapp.quartz.domain.Job;

import java.util.List;

public interface JobService extends IService<Job> {
    boolean addJob(Job job);

    boolean updateJob(Job job);

    boolean run(List<String> jobIds);

    boolean pause(List<String> jobIds);

    boolean resume(List<String> jobIds);
}
