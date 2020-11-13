package com.spring.springredissionmoreprovider;

import org.junit.jupiter.api.Test;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundStreamOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

@SpringBootTest
class SpringRedissionMoreProviderApplicationTests {

//    @Autowired
//    private RedissonClient redission;

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
    void testMoreRedis() {
        RBucket<String> bucket = oneRedission.getBucket("yanwenhui:20201113:testdb11");
        bucket.set("hello world :: db1111");

        System.out.println(bucket.get());

        RBucket<String> bucket2 = twoRedission.getBucket("yanwenhui:20201113:testdb22");
        bucket2.set("hello world :: db2222");

        System.out.println(bucket2.get());


        ValueOperations valueOperations = oneTemplate.opsForValue();
        valueOperations.set("yanwenhui:template:testdb11", "hello world :: db1111");

        System.out.println(valueOperations.get("yanwenhui:template:testdb11").toString());

        ValueOperations valueOperations2 = oneTemplate.opsForValue();
        valueOperations2.set("yanwenhui:template:testdb22", "hello world :: db2222");
        System.out.println(valueOperations.get("yanwenhui:template:testdb22").toString());

    }

}
