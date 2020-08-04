package com.springboot.springsecurity.server.service;

import com.springboot.springsecurity.server.domain.TbRole;
    /**
@description : 
@author : yanwenhui
@date : 2020/8/4 
*/
public interface TbRoleService{


    int deleteByPrimaryKey(Long id);

    int insert(TbRole record);

    int insertSelective(TbRole record);

    TbRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbRole record);

    int updateByPrimaryKey(TbRole record);

}
