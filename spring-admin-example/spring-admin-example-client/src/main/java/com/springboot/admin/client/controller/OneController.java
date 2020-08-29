package com.springboot.admin.client.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yanwenhui
 * @date 2020/8/28 22:05
 */
@RestController
public class OneController {

    @GetMapping({"", "/"})
    public String getOne() {
        return "hello spring boot admin.";
    }

}
