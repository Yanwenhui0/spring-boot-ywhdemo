package com.springboot.quartz.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/9/17
 */
@Data
@Accessors(chain = true)
public class ScheduleJob implements Serializable {

    private static final long serialVersionUID = -4817207967542181721L;

    @TableId(type = IdType.UUID)
    private String jobId;

    /**
     * 任务名称
     */
    private String jobName;

    /**
     * cron表达式
     */
    private String cronExpression;

    /**
     * 服务名称
     */
    private String beanName;

    /**
     * 方法名称
     */
    private String methodName;

    /**
     * 状态 1.启动 2.暂停
     */
    private int status;

    /**
     * 是否删除 0.否 1.是
     */
    @TableField(fill = FieldFill.INSERT)
    @TableLogic
    private int deleteFlag;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;
}