package com.springboot.springsecurity.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/3
 */
@SpringBootApplication
@MapperScan(basePackages = "com.springboot.springsecurity.server.mapper")
public class SpringSecurityServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityServerApplication.class, args);
    }
}
