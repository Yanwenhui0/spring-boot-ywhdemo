package com.springboot.mybatisplusmore.mapper.second;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.mybatisplusmore.entity.second.MoreUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/7/30
 */
@Mapper
public interface MoreUserMapper extends BaseMapper<MoreUser> {

    MoreUser selectByName(String name);
}
