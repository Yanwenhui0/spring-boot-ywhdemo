package com.springboot.quartz.job;

import com.springboot.quartz.util.SpringUtils;
import com.springboot.quartz.entity.ScheduleJob;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.lang.reflect.Method;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/9/17
 */
public class QuartzJobFactory implements Job {

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        //获取调度数据
        ScheduleJob scheduleJob = (ScheduleJob) jobExecutionContext.getMergedJobDataMap().get("scheduleJob");

        //获取对应的Bean
        Object object = SpringUtils.getBean(scheduleJob.getBeanName());
        try {
            //利用反射执行对应方法
            Method method = object.getClass().getMethod(scheduleJob.getMethodName());
            method.invoke(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
