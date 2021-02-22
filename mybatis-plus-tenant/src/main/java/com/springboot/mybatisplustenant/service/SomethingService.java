package com.springboot.mybatisplustenant.service;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.mybatisplustenant.domain.Something;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2021/2/2
 */
public interface SomethingService extends IService<Something>{

    List<Something> getAll();

}
