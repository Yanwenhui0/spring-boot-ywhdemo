package com.springboot.springsecurity.server.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.springboot.springsecurity.server.mapper.TbRoleMapper;
import com.springboot.springsecurity.server.domain.TbRole;
import com.springboot.springsecurity.server.service.TbRoleService;
/**
@description : 
@author : yanwenhui
@date : 2020/8/4 
*/
@Service
public class TbRoleServiceImpl implements TbRoleService{

    @Resource
    private TbRoleMapper tbRoleMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tbRoleMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TbRole record) {
        return tbRoleMapper.insert(record);
    }

    @Override
    public int insertSelective(TbRole record) {
        return tbRoleMapper.insertSelective(record);
    }

    @Override
    public TbRole selectByPrimaryKey(Long id) {
        return tbRoleMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TbRole record) {
        return tbRoleMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TbRole record) {
        return tbRoleMapper.updateByPrimaryKey(record);
    }

}
