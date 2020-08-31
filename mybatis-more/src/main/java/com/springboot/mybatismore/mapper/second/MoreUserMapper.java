package com.springboot.mybatismore.mapper.second;

import com.springboot.mybatismore.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @author : yanwenhui
 * @description : moreUser mybatis-xml 映射接口
 * @date : 2020/7/28
 */
@Mapper
public interface MoreUserMapper {

    @Select("select * from more_user where user_id = #{id} ")
    User selectUser(@Param("id") int id);
}
