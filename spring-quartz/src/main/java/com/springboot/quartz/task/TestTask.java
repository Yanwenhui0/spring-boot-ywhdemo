package com.springboot.quartz.task;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/9/17
 */
@Slf4j
@Component("com.springboot.quartz.task.TestTask")
public class TestTask {

    public void oneTask(){
        log.info("-------------------TestTask::oneTask任务执行开始-------------------");
        log.info("========>>>>>>>>>>>" + new Date());
        System.out.println("========>>>>>>>>>>>" + new Date());
        log.info("-------------------TestTask::oneTask任务执行结束-------------------");
    }

    public void twoTask(){
        log.info("-------------------TestTask::twoTask任务执行开始-------------------");
        log.info("========>>>>>>>>>>>" + new Date());
        System.out.println("========>>>>>>>>>>>" + new Date());
        log.info("-------------------TestTask::twoTask任务执行结束-------------------");
    }

    public void threeTask(){
        log.info("-------------------TestTask::threeTask任务执行开始-------------------");
        log.info("========>>>>>>>>>>>" + new Date());
        System.out.println("========>>>>>>>>>>>" + new Date());
        log.info("-------------------TestTask::threeTask任务执行结束-------------------");
    }

    public void fourTask(){
        log.info("-------------------TestTask::fourTask任务执行开始-------------------");
        log.info("========>>>>>>>>>>>" + new Date());
        System.out.println("========>>>>>>>>>>>" + new Date());
        log.info("-------------------TestTask::fourTask任务执行结束-------------------");
    }

    public void fiveTask(){
        log.info("-------------------TestTask::fiveTask任务执行开始-------------------");
        log.info("========>>>>>>>>>>>" + new Date());
        System.out.println("========>>>>>>>>>>>" + new Date());
        log.info("-------------------TestTask::fiveTask任务执行结束-------------------");
    }

}
