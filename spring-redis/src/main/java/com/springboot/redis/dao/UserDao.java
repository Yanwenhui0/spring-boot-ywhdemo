package com.springboot.redis.dao;

import com.springboot.redis.entity.User;
import org.springframework.stereotype.Repository;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/13
 */
@Repository
public class UserDao {

    public User getUser() {
        System.out.println("userDao 执行了......");
        User user = new User();
        user.setUserId(3);
        user.setName("ywh");
        user.setSex("nan");
        user.setTel("18252552709");
        return user;
    }

}
