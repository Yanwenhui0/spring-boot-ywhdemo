package com.springboot.springelk.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2021/4/10
 */
@RequestMapping("/test")
@RestController
@Slf4j
public class TestController {

    @GetMapping
    public String sendLog() {
        log.info("hello logstash");
        return "hello logstash";
    }

}
