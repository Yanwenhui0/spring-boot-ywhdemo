package com.springboot.springsecurity.server.mapper;

import com.springboot.springsecurity.server.domain.TbPermission;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.MyMapper;

import java.util.List;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/4
 */
public interface TbPermissionMapper extends MyMapper<TbPermission> {

    List<TbPermission> selectByUserId(@Param("userId") Long userId);
}