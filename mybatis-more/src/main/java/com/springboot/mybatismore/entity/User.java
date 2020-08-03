package com.springboot.mybatismore.entity;

import lombok.Data;
import lombok.ToString;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/7/28
 */
@Data
@ToString
public class User {

    private Integer userId;

    private String name;

    private String sex;

    private String tel;
}
