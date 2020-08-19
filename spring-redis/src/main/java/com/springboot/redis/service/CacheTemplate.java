package com.springboot.redis.service;

import com.springboot.redis.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/19
 */
@Component
@Slf4j
public class CacheTemplate {

    @Resource
    private RedisTemplate redisTemplate;

    public <T> T redisFindValue(String key, long time, TimeUnit timeUnit, CacheLoadble<? extends T> cacheLoadble) {
        ValueOperations ops = redisTemplate.opsForValue();
        T t = (T) ops.get(key);
        if (null != t) {
            return t;
        }
        Lock lock = new ReentrantLock();
        if (lock.tryLock()) {
            try {
                if (null == t) {
                    t = cacheLoadble.load();
                    Random random = new Random();
                    int outTime = random.nextInt(30);
                    ops.set(key, Optional.ofNullable(t).orElse((T) (t.getClass().newInstance())), time + outTime, timeUnit);
                    log.info("key => {}, time => {}", key, time + outTime);
                }
            } catch (Exception e) {

            } finally {
                lock.unlock();
            }
        }
        return t;
    }

}
