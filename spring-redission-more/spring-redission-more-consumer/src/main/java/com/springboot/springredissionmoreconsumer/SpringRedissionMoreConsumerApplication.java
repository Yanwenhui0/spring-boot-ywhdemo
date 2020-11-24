package com.springboot.springredissionmoreconsumer;

import com.spring.springredissionmoreprovider.config.EnableDynamicRedisson;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDynamicRedisson
public class SpringRedissionMoreConsumerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringRedissionMoreConsumerApplication.class, args);
    }

}
