package com.spring.springredissionmoreprovider.config;

import org.redisson.config.ConfigExt;
import org.redisson.config.SingleServerConfigExt;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/11/7
 */
public class RedissonProperties extends ConfigExt {

    private String beanName;

    @NestedConfigurationProperty
    private SingleServerConfigExt singleServerConfigExt;


    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public SingleServerConfigExt getSingleServerConfigExt() {
        return singleServerConfigExt;
    }

    public void setSingleServerConfigExt(SingleServerConfigExt singleServerConfigExt) {
        this.singleServerConfigExt = singleServerConfigExt;
        super.setSingleServerConfig(singleServerConfigExt);
    }
}
