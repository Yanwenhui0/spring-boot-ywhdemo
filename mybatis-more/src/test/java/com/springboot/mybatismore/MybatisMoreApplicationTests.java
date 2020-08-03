package com.springboot.mybatismore;

import com.springboot.mybatismore.entity.MoreUser;
import com.springboot.mybatismore.mapper.primary.UserMapper;
import com.springboot.mybatismore.mapper.second.MoreUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisMoreApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MoreUserMapper moreUserMapper;

    @Test
    void contextLoads() {
        System.out.println(userMapper.selectUser(2).toString());
        System.out.println(moreUserMapper.selectUser(2).toString());
    }

}
