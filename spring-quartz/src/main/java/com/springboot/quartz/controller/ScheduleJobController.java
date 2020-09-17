package com.springboot.quartz.controller;

import com.springboot.quartz.enums.JobOperateEnum;
import com.springboot.quartz.service.QuartzService;
import com.springboot.quartz.entity.ScheduleJob;
import com.springboot.quartz.service.ScheduleJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/9/17
 */
@RestController
@RequestMapping("/task")
public class ScheduleJobController {

    @Autowired
    private ScheduleJobService scheduleJobService;

    @Autowired
    private QuartzService quartzService;


    @PostMapping({"", "/"})
    public String add(@RequestBody ScheduleJob job) {
        scheduleJobService.save(job);
        if(JobOperateEnum.START.getValue().equals(job.getStatus())) {
            quartzService.addJob(job);
        }
        return "新增定时任务成功";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        quartzService.delete(id);
        scheduleJobService.removeById(id);
        return "新增定时任务成功";
    }

    @PutMapping({"", "/"})
    public String update(@RequestBody ScheduleJob job) {
        quartzService.delete(job.getJobId());
        scheduleJobService.updateById(job);
        if(JobOperateEnum.START.getValue().equals(job.getStatus())) {
            quartzService.addJob(job);
            quartzService.start(job.getJobId());
        }
        return "新增定时任务成功";
    }

    @GetMapping("/{id}")
    public ScheduleJob getOne(@PathVariable String id) {
        return scheduleJobService.getById(id);
    }

    @GetMapping("/list")
    public List<ScheduleJob> getList() {
        return scheduleJobService.list();
    }


    @GetMapping("/quartz/start/{id}")
    public String startQuartz(@PathVariable("id") String id) {
        quartzService.start(id);
        return "启动定时任务成功";
    }

    @GetMapping("/quartz/pause/{id}")
    public String pauseQuartz(@PathVariable("id") String id) {
        quartzService.pause(id);
        return "暂停定时任务成功";
    }

    @DeleteMapping("/quartz/delete/{id}")
    public String deleteQuartz(@PathVariable("id") String id) {
        quartzService.delete(id);
        return "删除定时任务成功";
    }

    @GetMapping("/quartz/startAllJob")
    public String startAllJob() {
        quartzService.startAllJob();
        return "启动所有定时任务成功";
    }

    @GetMapping("/quartz/pauseAllJob")
    public String pauseAllJob() {
        quartzService.pauseAllJob();
        return "暂停所有定时任务成功";
    }

}
