package com.springboot.lock.test;

import com.springboot.lock.LockApplication;
import com.springboot.lock.redis.LockByRedis;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/21
 */
@SpringBootTest
public class RedisLockTest {

    @Autowired
    private LockByRedis lockByRedis;

    @Test
    public void redisLock() {
        try {
            lockByRedis.getRedisLock();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
