package com.springboot.springsecurity.server.service;

import com.springboot.springsecurity.server.domain.TbUser;
    /**
@description : 
@author : yanwenhui
@date : 2020/8/4 
*/
public interface TbUserService{

    int deleteByPrimaryKey(Long id);

    int insert(TbUser record);

    int insertSelective(TbUser record);

    TbUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(TbUser record);

    int updateByPrimaryKey(TbUser record);

    TbUser selectByUserName(String username);

}
