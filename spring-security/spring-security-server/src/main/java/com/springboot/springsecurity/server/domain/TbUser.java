package com.springboot.springsecurity.server.domain;

import java.util.Date;
import lombok.Data;

/**
@description : 
@author : yanwenhui
@date : 2020/8/4 
*/
/**
    * 用户表
    */
@Data
public class TbUser {

    private Long id;

    /**
    * 用户名
    */
    private String username;

    /**
    * 密码，加密存储
    */
    private String password;

    /**
    * 注册手机号
    */
    private String phone;

    /**
    * 注册邮箱
    */
    private String email;

    private Date created;

    private Date updated;
}