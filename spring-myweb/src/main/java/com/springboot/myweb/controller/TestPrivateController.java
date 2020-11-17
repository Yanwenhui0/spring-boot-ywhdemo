package com.springboot.myweb.controller;

import com.springboot.myweb.aspects.MyTime;
import com.springboot.myweb.service.TestPrivateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/11/16
 */
@RestController
@RequestMapping("/private")
public class TestPrivateController {

    @Autowired
    private TestPrivateService testPrivateService;

    @GetMapping
    public String test() {
        this.testPrivateService.sendHello();
        return "OK";
    }

    @MyTime
    @PostMapping
    public String test2() {
        return "OK";
    }

}
