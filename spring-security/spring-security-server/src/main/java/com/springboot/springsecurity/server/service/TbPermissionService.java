package com.springboot.springsecurity.server.service;

import com.springboot.springsecurity.server.domain.TbPermission;

import java.util.List;

/**
@description : 
@author : yanwenhui
@date : 2020/8/4 
*/
public interface TbPermissionService{

    int deleteByPrimaryKey(Long id);

    int insert(TbPermission record);

    int insertSelective(TbPermission record);

    TbPermission selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbPermission record);

    int updateByPrimaryKey(TbPermission record);

    List<TbPermission> selectByUserId(Long userId);

}
