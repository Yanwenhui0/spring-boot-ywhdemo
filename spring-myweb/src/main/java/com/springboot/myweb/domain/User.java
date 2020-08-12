package com.springboot.myweb.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/12
 */
@Data
@ToString
public class User {

    private int id;

    private String name;

    private String sex;

    /**
     * @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")：
     * 前端 => 后端时格式化日期 (param/form-data/x-www-form-urlencoded)
     * 但不能处理 @RequestBody 中的日期格式化
     *
     * @JsonFormat(pattern="yyyy-MM-dd mm:hh:ss",timezone="GMT+8")
     * 处理json数据时生效
     * 后端 => 前端
     * 处理 @RequestBody 中的日期格式化
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern="yyyy-MM-dd mm:hh:ss",timezone="GMT+8")
    private Date updateTime;
}
