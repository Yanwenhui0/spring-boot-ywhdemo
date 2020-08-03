package com.springboot.swagger.controller;

import com.springboot.swagger.entity.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/7/24
 */
@Api(value = "swagger-test", tags = "user-test")
@RestController
@RequestMapping("/user")
public class UserController {


    @ApiOperation(value = "this is value", notes = "this is notes", response = User.class)
    @PostMapping
    public User save(@RequestParam String name,
                     @RequestParam String password){

        return new User(name, password);
    }

}
