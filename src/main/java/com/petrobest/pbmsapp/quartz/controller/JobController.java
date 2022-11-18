package com.petrobest.pbmsapp.quartz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.petrobest.pbmsapp.common.annotation.Log;
import com.petrobest.pbmsapp.common.domain.ResponseBo;
import com.petrobest.pbmsapp.quartz.domain.Job;
import com.petrobest.pbmsapp.quartz.domain.JobVO;
import com.petrobest.pbmsapp.quartz.service.JobService;
import com.petrobest.pbmsapp.system.domain.ResourceDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.ListUtils;
import org.quartz.CronExpression;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static javax.swing.UIManager.get;

@RestController
@RequestMapping("/job")
@Slf4j
public class JobController {

    @Autowired
    JobService service;

    @GetMapping("/checkCron")
    public boolean checkCron(@RequestBody Map<String, String> param) {
        String cron = param.get("cron");
        try {
            return CronExpression.isValidExpression(cron);
        } catch (Exception e) {
            return false;
        }
    }


    @PostMapping("/list")
    public ResponseBo list(@RequestBody JobVO jobVO) {
        //分页构造器
        Page<Job> page = new Page<>(jobVO.getCurrPage(), jobVO.getPageSize());
        //条件构造器
        QueryWrapper<Job> queryWrapper = new QueryWrapper<>(jobVO.getEntity());
        //执行分页
        IPage<Job> pageList = service.page(page, queryWrapper);
        //返回结果
        return ResponseBo.ok(pageList);
        /*List<Job> list = service.list();
        if (list != null && list.size() > 0) {
            return ResponseBo.ok(list);
        }
        log.error("获取任务信息失败");
        return ResponseBo.error("获取任务信息失败，请联系网站管理员！");*/
    }

    @Log("新增任务")
    @PostMapping("/save")
    public ResponseBo addJob(@RequestBody Job job) {
        boolean addJob = service.addJob(job);
        if (addJob) {
            return ResponseBo.ok("新增任务成功！");
        } else {
            log.error("新增任务失败");
            return ResponseBo.error("新增任务失败，请联系网站管理员！");
        }
    }

    @Log("删除任务")
    @PostMapping("/delete")
    public ResponseBo deleteJob(String ids) {
        boolean remove = service.removeByIds(Arrays.asList(ids.split(",")));
        if (remove) {
            return ResponseBo.ok("删除任务成功！");
        } else {
            log.error("删除任务失败");
            return ResponseBo.error("删除任务失败，请联系网站管理员！");
        }
    }

    @PostMapping("/find")
    public ResponseBo find(@RequestBody Map<String, String> param) {
        String jobId = param.get("jobId");
        Job job = service.getById(jobId);
        if (job != null) {
            return ResponseBo.ok(job);
        }
        log.error("获取任务信息失败");
        return ResponseBo.error("获取任务信息失败，请联系网站管理员！");
    }

    @Log("修改任务")
    @PostMapping("/update")
    public ResponseBo updateJob(@RequestBody Job job) {
//        boolean update = service.saveOrUpdate(job);
        boolean update = service.updateJob(job);
        if (update) {
            return ResponseBo.ok("修改任务成功！");
        } else {
            log.error("修改任务失败");
            return ResponseBo.error("修改任务失败，请联系网站管理员！");
        }
    }

    @Log("执行任务")
    @PostMapping("/run")
    public ResponseBo runJob(@RequestBody Map<String, List<String>> param) {
        List<String> jobIds = param.get("jobIds");
        boolean run = service.run(jobIds);
        if (run) {
            return ResponseBo.ok("执行任务成功！");
        } else {
            log.error("执行任务失败");
            return ResponseBo.error("执行任务失败，请联系网站管理员！");
        }

    }


    @Log("暂停任务")
    @PostMapping("/pause")
    public ResponseBo pauseJob(@RequestBody Map<String, List<String>> param) {
        List<String> jobIds = param.get("jobIds");
        boolean pause = service.pause(jobIds);
        if (pause) {
            return ResponseBo.ok("暂停任务成功！");
        } else {
            log.error("暂停任务失败");
            return ResponseBo.error("暂停任务失败，请联系网站管理员！");
        }
    }


    @Log("恢复任务")
    @PostMapping("/resume")
    public ResponseBo resumeJob(@RequestBody Map<String, List<String>> param) {
        List<String> jobIds = param.get("jobIds");
        boolean resume = service.resume(jobIds);
        if (resume) {
            return ResponseBo.ok("恢复任务成功！");
        } else {
            log.error("恢复任务失败");
            return ResponseBo.error("恢复任务失败，请联系网站管理员！");
        }
    }
}
