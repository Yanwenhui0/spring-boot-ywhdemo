package com.springboot.mybatisplustenant.service.impl;

import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.springboot.mybatisplustenant.mapper.UserMapper;
import com.springboot.mybatisplustenant.domain.User;
import com.springboot.mybatisplustenant.service.UserService;
/**
 * @author : yanwenhui
 * @description : 
 * @date : 2021/2/1
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService{

}
