package com.springboot.springsecurity.resource.service.impl;

import com.springboot.springsecurity.resource.domain.TbContent;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.springboot.springsecurity.resource.mapper.TbContentMapper;
import com.springboot.springsecurity.resource.service.TbContentService;

import java.util.List;

/**
@description : 
@author : yanwenhui
@date : 2020/8/6 
*/
@Service
public class TbContentServiceImpl implements TbContentService{

    @Resource
    private TbContentMapper tbContentMapper;

    @Override
    public TbContent getById(Long id) {
        return tbContentMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<TbContent> selectAll() {
        return tbContentMapper.selectAll();
    }

    @Override
    public int insert(TbContent tbContent) {
        return tbContentMapper.insert(tbContent);
    }

    @Override
    public int update(TbContent tbContent) {
        return tbContentMapper.updateByPrimaryKey(tbContent);
    }

    @Override
    public int delete(Long id) {
        return tbContentMapper.deleteByPrimaryKey(id);
    }

}
