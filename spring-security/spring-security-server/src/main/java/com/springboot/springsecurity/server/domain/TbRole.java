package com.springboot.springsecurity.server.domain;

import java.util.Date;
import lombok.Data;

/**
@description : 
@author : yanwenhui
@date : 2020/8/4 
*/
/**
    * 角色表
    */
@Data
public class TbRole {
    private Long id;

    /**
    * 父角色
    */
    private Long parentId;

    /**
    * 角色名称
    */
    private String name;

    /**
    * 角色英文名称
    */
    private String enname;

    /**
    * 备注
    */
    private String description;

    private Date created;

    private Date updated;
}