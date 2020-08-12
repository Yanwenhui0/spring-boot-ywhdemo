package com.springboot.myweb.controller;

import com.springboot.myweb.domain.MoreUser;
import com.springboot.myweb.domain.User;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/12
 */
@RestController
@RequestMapping("/user")
public class UserController {


    /**
     * 使用 @ControllerAdvice + @ModelAttribute(name = "initData")
     * 将数据添加到所有controller中
     */
    @GetMapping
    public User getUser(Model model) {
        Map<String, Object> initData = (Map)model.getAttribute("initData");
        System.out.println(initData);
        return new User();
    }


    @PostMapping(value = {"", "/"}, consumes = {MediaType.MULTIPART_FORM_DATA_VALUE, MediaType.APPLICATION_FORM_URLENCODED_VALUE})
    public User twoUser(@ModelAttribute("user") User user,
                        @ModelAttribute("moreUser") MoreUser moreUser) {
        System.out.println(user.toString());
        System.out.println(moreUser.toString());
        return user;
    }

    @PostMapping("/update-date")
    public User twoUser(@RequestBody User user) {
        System.out.println(user.toString());
        return user;
    }

}
