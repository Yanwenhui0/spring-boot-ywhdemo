package com.springboot.mybatismore.mapper.primary;

import com.springboot.mybatismore.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/7/28
 */
@Mapper
public interface UserMapper {

//    @Select("select * from user where user_id = #{id} ")
    User selectUser(@Param("id") int id);

}
