package com.springboot.springjetty.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2021/1/13
 */
@RestController
@RequestMapping("/test-jetty")
public class TestJettyController {

    @GetMapping
    public String getString() {
        return "hello jetty.";
    }
}
