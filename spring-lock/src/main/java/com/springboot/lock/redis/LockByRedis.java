package com.springboot.lock.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/21
 */
@Component
public class LockByRedis {

    private static final long LOCK_TIME_OUT = 60;

    @Autowired
    private RedisTemplate redisTemplate;

    public void getRedisLock() throws Exception {

        // 设置获取锁的超时时间
        long getLockTime = System.currentTimeMillis() + 3 * 1000;

        ValueOperations valueOperations = redisTemplate.opsForValue();

        Boolean isLock = false;

        while (System.currentTimeMillis() < getLockTime) {
            isLock = valueOperations.setIfAbsent("lock:redis:key", "value", LOCK_TIME_OUT, TimeUnit.SECONDS);
            // 获取锁成功
            if (isLock) {
                System.out.println("获取锁成功");
                break;
            }
        }

        if(!isLock) {
            throw new RuntimeException("获取锁失败！请重试");
        }

        // 业务逻辑
        Thread.sleep(30 * 1000);

        redisTemplate.delete("lock:redis:key");
        System.out.println("释放锁成功");
    }

}
