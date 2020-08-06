package com.springboot.springsecurity.resource.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import com.springboot.springsecurity.resource.mapper.TbContentCategoryMapper;
import com.springboot.springsecurity.resource.service.TbContentCategoryService;
/**
@description : 
@author : yanwenhui
@date : 2020/8/6 
*/
@Service
public class TbContentCategoryServiceImpl implements TbContentCategoryService{

    @Resource
    private TbContentCategoryMapper tbContentCategoryMapper;

}
