package com.springboot.springsecurity.resource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/4
 */
@SpringBootApplication
@MapperScan(basePackages = "com.springboot.springsecurity.resource.mapper")
public class SpringSecurityResourceApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityResourceApplication.class, args);
    }
}
