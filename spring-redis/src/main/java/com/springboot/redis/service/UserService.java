package com.springboot.redis.service;

import com.springboot.redis.dao.UserDao;
import com.springboot.redis.entity.User;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/13
 */
@Service
public class UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private RedisTemplate redisTemplate;

    @Cacheable(cacheNames = "user", keyGenerator = "keyGenerator")
    public User getUser() {
//        ValueOperations ops = redisTemplate.opsForValue();
//        User user = (User) ops.get("test:user:get");
//        if(null == user) {
//            user = userDao.getUser();
//            ops.set("test:user:get", user, 30, TimeUnit.SECONDS);
//        }
//        return user;
        return userDao.getUser();
    }

}
