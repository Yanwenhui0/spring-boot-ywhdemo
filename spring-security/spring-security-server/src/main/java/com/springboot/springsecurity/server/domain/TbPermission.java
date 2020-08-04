package com.springboot.springsecurity.server.domain;

import java.util.Date;
import lombok.Data;

/**
@description : 
@author : yanwenhui
@date : 2020/8/4 
*/
/**
    * 权限表
    */
@Data
public class TbPermission {
    private Long id;

    /**
    * 父权限
    */
    private Long parentId;

    /**
    * 权限名称
    */
    private String name;

    /**
    * 权限英文名称
    */
    private String enname;

    /**
    * 授权路径
    */
    private String url;

    /**
    * 备注
    */
    private String description;

    private Date created;

    private Date updated;
}