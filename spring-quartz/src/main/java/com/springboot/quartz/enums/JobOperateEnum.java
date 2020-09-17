package com.springboot.quartz.enums;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/9/17
 */
@Getter
@AllArgsConstructor
public enum JobOperateEnum {
    /**
     * 启动
     */
    START(1, "启动"),
    /**
     * 暂停
     */
    PAUSE(2, "暂停"),
    /**
     * 删除
     */
    DELETE(3, "删除");

    private final Integer value;
    private final String desc;

    public String getEnumName() {
        return name();
    }
}