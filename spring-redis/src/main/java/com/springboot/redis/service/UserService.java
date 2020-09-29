package com.springboot.redis.service;

import com.springboot.redis.dao.UserDao;
import com.springboot.redis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/13
 */
@Service
@Slf4j
public class UserService {

    @Resource
    private UserDao userDao;

    @Resource
    private RedisTemplate redisTemplate;

    @Resource
    private CacheTemplate cacheTemplate;

    /**
     * 最普通的设置缓存方法
     *
     * @return
     */
    @Cacheable(cacheNames = "user", keyGenerator = "keyGenerator")
    public User getUser() {
//        ValueOperations ops = redisTemplate.opsForValue();
//        User user = (User) ops.get("test:user:get");
//        if(null != user) {
//            return user;
//        }
//        user = userDao.getUser();
//        if(null != user) {
//            ops.set("test:user:get", user, 30, TimeUnit.SECONDS);
//        }
//        return user;
        return userDao.getUser();
    }

    /**
     * 解决缓存雪崩问题(大量缓存数据同时失效，大流量涌入数据库，该怎么办)
     * 1.尽可能设置不同的过期时间，时间范围随机设置(推荐)
     * 2.如果缓存数据库是分布式部署，将热点数据均匀分布在不同得缓存数据库中
     * 3.设置过期时间用不失效
     *
     * @return
     */
    public User getUserCacheAvalanche() {
        Random random = new Random();
        int outTime = random.nextInt(30);
        ValueOperations ops = redisTemplate.opsForValue();
        User user = (User) ops.get("test:user:get");
        if(null != user) {
            return user;
        }
        user = userDao.getUser();
        if(null != user) {
            ops.set("test:user:get", user, 30 + outTime, TimeUnit.SECONDS);
            log.info("key => test:user:get, time => {}", 30 + outTime);
        }
        return user;
    }

    /**
     * 解决缓存穿透问题(如果此key的数据，缓存和数据库都不存在，该怎么办)
     * 1.做参数校验，明显不符合要求的key，直接打回去
     * 2.通过key在数据库获取为null，将null存入缓存，并相应减少缓存失效时间(推荐)
     * 3.布隆过滤器
     *
     * @return
     */
    public User getUserCachePenetration() {
        ValueOperations ops = redisTemplate.opsForValue();
        User user = (User) ops.get("test:user:get");
        if(null != user) {
            return user;
        }
        user = userDao.getUser();
        ops.set("test:user:get", Optional.ofNullable(user).orElse(new User()), 30, TimeUnit.SECONDS);
        return user;
    }

    /**
     * 解决缓存击穿问题(如果此缓存失效时大量流量涌入，该怎么办)
     * 1.热点数据永不失效
     * 2.加锁从数据库获取数据，使用DCL(推荐)
     *
     * @return
     */
    public User getUserCacheBreakdown() {
        ValueOperations ops = redisTemplate.opsForValue();
        User user = (User) ops.get("test:user:get");
        if (null != user) {
            return user;
        }
        Lock lock = new ReentrantLock();
        if(lock.tryLock()) {
            try {
                if(null == user) {
                    user = userDao.getUser();
                    if(null != user) {
                        ops.set("test:user:get", user, 30, TimeUnit.SECONDS);
                    }
                }
            } finally {
                lock.unlock();
            }
        }
        return user;
    }

    /**
     * 最终版本：综合缓存雪崩、缓存穿透、缓存击穿
     *
     * @return
     */
    public User getUserFinal() {
        ValueOperations ops = redisTemplate.opsForValue();
        User user = (User) ops.get("test:user:get");
        if (null != user) {
            return user;
        }
        Lock lock = new ReentrantLock();
        if(lock.tryLock()) {
            try {
                if(null == user) {
                    user = userDao.getUser();
                    Random random = new Random();
                    int outTime = random.nextInt(30);
                    ops.set("test:user:get", Optional.ofNullable(user).orElse(new User()), 30 + outTime, TimeUnit.SECONDS);
                    log.info("key => test:user:get, time => {}", 30 + outTime);
                }
            } finally {
                lock.unlock();
            }
        }
        return user;
    }

    /**
     * 最终版本：使用模板方法模式封装
     *
     * @return
     */
    public User getUserFinalStrategy() {

        return cacheTemplate.redisFindValue("test:user:get", 30, TimeUnit.SECONDS, new CacheLoadble<User>(){
            @Override
            public User load() {
                return userDao.getUser();
            }
        });

    }

}
