package com.springboot.springsecurity.server.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.springboot.springsecurity.server.mapper.TbPermissionMapper;
import com.springboot.springsecurity.server.domain.TbPermission;
import com.springboot.springsecurity.server.service.TbPermissionService;

import java.util.List;

/**
@description : 
@author : yanwenhui
@date : 2020/8/4 
*/
@Service
public class TbPermissionServiceImpl implements TbPermissionService{

    @Resource
    private TbPermissionMapper tbPermissionMapper;

    @Override
    public int deleteByPrimaryKey(Long id) {
        return tbPermissionMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TbPermission record) {
        return tbPermissionMapper.insert(record);
    }

    @Override
    public int insertSelective(TbPermission record) {
        return tbPermissionMapper.insertSelective(record);
    }

    @Override
    public TbPermission selectByPrimaryKey(Long id) {
        return tbPermissionMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TbPermission record) {
        return tbPermissionMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TbPermission record) {
        return tbPermissionMapper.updateByPrimaryKey(record);
    }

    @Override
    public List<TbPermission> selectByUserId(Long userId) {
        return tbPermissionMapper.selectByUserId(userId);
    }

}
