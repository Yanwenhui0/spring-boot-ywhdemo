package com.spring.springredissionmoreprovider.config;

import org.redisson.Redisson;
import org.redisson.config.ConfigExt;
import org.redisson.config.SingleServerConfigExt;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/11/9
 */
@Configuration
@ConditionalOnClass({Redisson.class, ConfigExt.class, SingleServerConfigExt.class})
@ConditionalOnProperty(
        prefix = "configs.com.yanwenhui.redis.redisson.dynamic",
        name = {"enable"},
        havingValue = "true"
)
@Import({RedissionImportBeanDefinitionRegistrar.class})
public class RedissonDynamicAutoConfiguration {
}
