package com.springboot.mybatisplusmore;

import com.springboot.mybatisplusmore.entity.second.MoreUser;
import com.springboot.mybatisplusmore.mapper.primary.UserMapper;
import com.springboot.mybatisplusmore.mapper.second.MoreUserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MybatisPlusMoreApplicationTests {

    @Autowired
    UserMapper userMapper;

    @Autowired
    MoreUserMapper moreUserMapper;

    @Test
    void contextLoads() {
        System.out.println(userMapper.selectByName("严文慧1").toString());
        System.out.println(moreUserMapper.selectByName("严文慧3").toString());
    }

}
