package com.springboot.quartz.service;

import com.springboot.quartz.entity.ScheduleJob;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/9/17
 */
public interface QuartzService {

    boolean addJob(ScheduleJob job);

    boolean initAllTasks();

    void start(String jobId);

    void pause(String jobId);

    void delete(String jobId);

    void startAllJob();

    void pauseAllJob();

}
