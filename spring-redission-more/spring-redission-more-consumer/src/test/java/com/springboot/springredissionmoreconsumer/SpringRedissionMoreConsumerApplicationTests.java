package com.springboot.springredissionmoreconsumer;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringRedissionMoreConsumerApplicationTests {

    @Autowired
    @Qualifier("oneRedisson")
    private RedissonClient oneRedission;

    @Autowired
    @Qualifier("twoRedisson")
    private RedissonClient twoRedission;

    @Autowired
    @Qualifier("oneRedissonTemplate")
    private RedisTemplate oneTemplate;

    @Autowired
    @Qualifier("twoRedissonTemplate")
    private RedisTemplate twoTemplate;


//    @Test
//    void testRedis() {
//        RBucket<String> bucket = redission.getBucket("yanwenhui:20201109:goods");
//        bucket.set("hello world");
//
//        System.out.println(bucket.get());
//    }

    @Test
    void testLock() throws InterruptedException {

        final RLock lock = oneRedission.getLock("ywh-lock");
        lock.tryLock(600, 600, TimeUnit.MINUTES);

        Thread.sleep(600*1000);
    }

    @Test
    void testLock2() throws InterruptedException {

        final RLock lock = oneRedission.getLock("ywh-lock");
        lock.tryLock(600, 600, TimeUnit.MINUTES);

        Thread.sleep(600*1000);
    }


    @Test
    void testMoreRedis() {

        for (int i = 0; i < 10000; i++) {

            RBucket<String> bucket = oneRedission.getBucket("yanwenhui:pro20201124:prodb11 - " + i);
            bucket.set("hello world :: prodb1111 - " + i);

//            System.out.println(bucket.get());

            RBucket<String> bucket2 = twoRedission.getBucket("yanwenhui:pro20201124:prodb22 - " + i);
            bucket2.set("hello world :: prodb2222 - " + i);

//            System.out.println(bucket2.get());


            ValueOperations valueOperations = oneTemplate.opsForValue();
            valueOperations.set("yanwenhui:protemplate24:prodb11 - " + i, "hello world :: more - prodb1111 - " + i);

//            System.out.println(valueOperations.get("yanwenhui:template:testdb11").toString());

            ValueOperations valueOperations2 = twoTemplate.opsForValue();
            valueOperations2.set("yanwenhui:protemplate24:prodb22 - " + i, "hello world :: more - prodb2222 - " + i);
//            System.out.println(valueOperations2.get("yanwenhui:template:testdb22").toString());
        }

    }
}
