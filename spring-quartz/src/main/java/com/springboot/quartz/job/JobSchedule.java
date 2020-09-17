package com.springboot.quartz.job;

import com.springboot.quartz.service.QuartzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/9/17
 */
@Slf4j
@Component
public class JobSchedule implements CommandLineRunner {

    @Autowired
    private QuartzService quartzService;

    /**
     * 任务调度开始
     *
     * @param strings
     * @throws Exception
     */
    @Override
    public void run(String... strings) {
        log.info("任务调度开始==============任务调度开始");
        boolean flag = quartzService.initAllTasks();
        if (flag) {
            quartzService.startAllJob();
        }
        log.info("任务调度结束==============任务调度结束");
    }
}