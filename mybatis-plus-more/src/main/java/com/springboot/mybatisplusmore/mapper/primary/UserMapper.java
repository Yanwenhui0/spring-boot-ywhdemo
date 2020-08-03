package com.springboot.mybatisplusmore.mapper.primary;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.springboot.mybatisplusmore.entity.primary.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/7/30
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    User selectByName(String name);

}
