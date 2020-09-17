package com.springboot.quartz;

import com.springboot.quartz.entity.ScheduleJob;
import com.springboot.quartz.enums.JobOperateEnum;
import com.springboot.quartz.service.ScheduleJobService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringQuartzApplicationTests {

    @Autowired
    private ScheduleJobService scheduleJobService;

    @Test
    public void save() {
        ScheduleJob scheduleJob = new ScheduleJob();
        scheduleJob.setJobName("test")
                .setCronExpression("/10 * * * * ?")
                .setBeanName("123")
                .setMethodName("3435")
                .setStatus(JobOperateEnum.START.getValue());
        scheduleJobService.save(scheduleJob);
    }

}
