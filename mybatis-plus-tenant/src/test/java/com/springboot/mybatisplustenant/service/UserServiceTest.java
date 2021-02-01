package com.springboot.mybatisplustenant.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.springboot.mybatisplustenant.domain.Book;
import com.springboot.mybatisplustenant.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2021/2/1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    public void saveBook() {
        userService.save(new User().setUsername("第一人"));
        userService.save(new User().setUsername("第二人"));
        userService.save(new User().setUsername("第三人"));
    }

    @Test
    public void getBook() {
        System.out.println(userService.getOne(new QueryWrapper<>(new User().setUsername("第一人"))));
    }

    @Test
    public void listBook() {
        for (User user : userService.list(new QueryWrapper<>(new User().setUsername("第一人")))) {
            System.out.println(user);
        }
    }
}
