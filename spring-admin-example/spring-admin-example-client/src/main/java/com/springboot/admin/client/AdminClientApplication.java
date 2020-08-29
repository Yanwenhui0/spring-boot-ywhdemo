package com.springboot.admin.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/28
 */
@SpringBootApplication
@EnableDiscoveryClient
public class AdminClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminClientApplication.class, args);
    }
}
