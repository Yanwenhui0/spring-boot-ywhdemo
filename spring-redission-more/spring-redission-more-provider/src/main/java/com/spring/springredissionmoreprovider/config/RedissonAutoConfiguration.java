package com.spring.springredissionmoreprovider.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.ConfigExt;
import org.redisson.config.SingleServerConfigExt;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/11/7
 */
@Configuration
@ConditionalOnClass({Redisson.class, ConfigExt.class, SingleServerConfigExt.class})
@ConditionalOnProperty(
        prefix = "configs.com.yanwenhui.redis.redisson.dynamic",
        name = {"enable"},
        havingValue = "false",
        matchIfMissing = true
)
@EnableConfigurationProperties
public class RedissonAutoConfiguration {


    @Bean
    @Primary
    public RedissonProperties primaryRedissonproperties() {
        return new RedissonProperties();
    }


    @Bean
    @Primary
    public RedissonClient primaryRedissonClient(@Qualifier("primaryRedissonproperties") RedissonProperties redissonProperties) {
        if(null == redissonProperties.getCodec()) {
            redissonProperties.setCodec(new JsonJacksonCodec());
        }
        return Redisson.create(redissonProperties);
    }

    @Bean
    @ConfigurationPropertiesBinding
    public CodecConverter codecConverter() {
        return new CodecConverter();
    }

}
