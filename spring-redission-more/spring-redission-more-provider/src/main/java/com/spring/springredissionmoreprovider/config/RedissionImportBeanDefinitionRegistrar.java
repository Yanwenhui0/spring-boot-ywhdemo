package com.spring.springredissionmoreprovider.config;

import com.alibaba.spring.util.PropertySourcesUtils;
import org.redisson.Redisson;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.boot.context.properties.bind.Binder;
import org.springframework.boot.context.properties.source.ConfigurationPropertySources;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/11/7
 */
public class RedissionImportBeanDefinitionRegistrar implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    public static final String REDISSON_DYNAMIC_AUTO_CONFIG_PREFIX = "configs.com.yanwenhui.redis.redisson.dynamic";

    private static final String PREFIX = "configs.com.yanwenhui.redis.redisson";

    private ConfigurableEnvironment environment;


    @Override
    public void setEnvironment(Environment environment) {
        Assert.isInstanceOf(ConfigurableEnvironment.class, environment, "Redisson dynamic auto configuration requires a configurable environment");
        this.environment = (ConfigurableEnvironment)environment;
    }

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {

        Map<String, Object> subProperties = PropertySourcesUtils.getSubProperties(this.environment, PREFIX);

        List<String> beanNameList = new ArrayList(subProperties.keySet().stream().map(name -> name.substring(0, name.indexOf(46))).distinct().filter(name -> !"dynamic".equals(name)).collect(Collectors.toSet()));

        Binder binder = new Binder(ConfigurationPropertySources.get(this.environment));

        BeanDefinitionBuilder beanDefinitionBuilder;
        RedissonProperties redissonProperties;
        BindResult<RedissonProperties> bind;

        for (int i = 0, j = beanNameList.size(); i < j; i++) {
            String beanName = beanNameList.get(i);
            bind = binder.bind(PREFIX + "." + beanName.toLowerCase(), RedissonProperties.class);
            redissonProperties = bind.get();

            // redissonClient BeanDefinitionBuilder
            beanDefinitionBuilder = BeanDefinitionBuilder.rootBeanDefinition(Redisson.class, "create");
            beanDefinitionBuilder.addConstructorArgValue(redissonProperties);

            registry.registerBeanDefinition(beanName, beanDefinitionBuilder.getBeanDefinition());

            // redissonConnectionFactory BeanDefinitionBuilder
            beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(RedissonConnectionFactory.class);
            beanDefinitionBuilder.addConstructorArgReference(beanName);
            if(i == 1) {
                beanDefinitionBuilder.setPrimary(true);
            }
            registry.registerBeanDefinition(beanName + "ConnectionFactory", beanDefinitionBuilder.getBeanDefinition());

            // stringRedisTemplate BeanDefinitionBuilder
            beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(StringRedisTemplate.class);
            beanDefinitionBuilder.addConstructorArgReference(beanName + "ConnectionFactory");
            registry.registerBeanDefinition(beanName + "StringTemplate", beanDefinitionBuilder.getBeanDefinition());

            // redisTemplate BeanDefinitionBuilder
            beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(StringRedisTemplate.class);
            beanDefinitionBuilder.addConstructorArgReference(beanName + "ConnectionFactory");
            registry.registerBeanDefinition(beanName + "Template", beanDefinitionBuilder.getBeanDefinition());
        }

    }

}
