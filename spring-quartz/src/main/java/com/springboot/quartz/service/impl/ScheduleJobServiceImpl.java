package com.springboot.quartz.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.quartz.entity.ScheduleJob;
import com.springboot.quartz.mapper.ScheduleJobMapper;
import com.springboot.quartz.service.ScheduleJobService;
import org.springframework.stereotype.Service;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/9/17
 */
@Service
public class ScheduleJobServiceImpl extends ServiceImpl<ScheduleJobMapper, ScheduleJob> implements ScheduleJobService {

}
