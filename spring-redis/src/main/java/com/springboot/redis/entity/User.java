package com.springboot.redis.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/13
 */
@Data
@ToString
public class User implements Serializable {

    private static final long serialVersionUID = -3192501198075525137L;

    private Integer userId;

    private String name;

    private String sex;

    private String tel;
}