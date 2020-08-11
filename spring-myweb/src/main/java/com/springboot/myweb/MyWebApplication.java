package com.springboot.myweb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/11
 */
@SpringBootApplication
@MapperScan(basePackages = "com.springboot.myweb.mapper")
public class MyWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(MyWebApplication.class, args);
    }

}
