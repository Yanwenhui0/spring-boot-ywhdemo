package com.springboot.mybatisplustenant.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.mybatisplustenant.domain.Something;

import java.util.List;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2021/2/2
 */
public interface SomethingMapper extends BaseMapper<Something> {

    List<Something> getAll();

}
