package com.springboot.quartz.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.quartz.entity.ScheduleJob;
import com.springboot.quartz.enums.JobOperateEnum;
import com.springboot.quartz.job.QuartzJobFactory;
import com.springboot.quartz.service.QuartzService;
import com.springboot.quartz.service.ScheduleJobService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/9/17
 */
@Service
public class QuartzServiceImpl implements QuartzService {

    /**
     * 调度器
     */
    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ScheduleJobService scheduleJobService;


    @Override
    public boolean addJob(ScheduleJob job) {
        try {
            //创建触发器
            Trigger trigger = TriggerBuilder.newTrigger().withIdentity(job.getJobName())
                    .withSchedule(CronScheduleBuilder.cronSchedule(job.getCronExpression()))
                    .startNow()
                    .build();

            //创建任务
            JobDetail jobDetail = JobBuilder.newJob(QuartzJobFactory.class)
                    .withIdentity(job.getJobName())
                    .build();

            //传入调度的数据，在QuartzFactory中需要使用
            jobDetail.getJobDataMap().put("scheduleJob", job);

            //调度作业
            scheduler.scheduleJob(jobDetail, trigger);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean initAllTasks() {
        //查询数据库是否存在需要定时的任务
        List<ScheduleJob> scheduleJobs = scheduleJobService.list();
        if (scheduleJobs != null) {
            scheduleJobs.forEach(scheduleJob -> {
                if (JobOperateEnum.START.getValue().equals(scheduleJob.getStatus())) {
                    addJob(scheduleJob);
                }
            });
        }
        return true;
    }

    @Override
    public void start(String jobId) {
        //此处省去数据验证
        ScheduleJob job = scheduleJobService.getById(jobId);
        job.setStatus(JobOperateEnum.START.getValue());
        scheduleJobService.updateById(job);

        //执行job
        try {
            this.operateJob(JobOperateEnum.START, job);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pause(String jobId) {
        //此处省去数据验证
        ScheduleJob job = scheduleJobService.getById(jobId);
        job.setStatus(JobOperateEnum.PAUSE.getValue());
        scheduleJobService.updateById(job);

        //停止job
        try {
            this.operateJob(JobOperateEnum.PAUSE, job);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String jobId) {
        //此处省去数据验证
        ScheduleJob job = scheduleJobService.getById(jobId);

        //删除job
        try {
            this.operateJob(JobOperateEnum.DELETE, job);
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startAllJob() {
        //此处省去数据验证
        scheduleJobService.update(new ScheduleJob().setStatus(JobOperateEnum.START.getValue()), new QueryWrapper<>());

        //执行job
        try {
            scheduler.start();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void pauseAllJob() {
        //此处省去数据验证
        scheduleJobService.update(new ScheduleJob().setStatus(JobOperateEnum.PAUSE.getValue()), new QueryWrapper<>());

        //执行job
        try {
            scheduler.standby();
        } catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private void operateJob(JobOperateEnum jobOperateEnum, ScheduleJob job) throws SchedulerException {
        JobKey jobKey = new JobKey(job.getJobName());
        JobDetail jobDetail = scheduler.getJobDetail(jobKey);
        if (jobDetail == null) {
            //抛异常
        }
        switch (jobOperateEnum) {
            case START:
                scheduler.resumeJob(jobKey);
                break;
            case PAUSE:
                scheduler.pauseJob(jobKey);
                break;
            case DELETE:
                scheduler.deleteJob(jobKey);
                break;
        }
    }
}
