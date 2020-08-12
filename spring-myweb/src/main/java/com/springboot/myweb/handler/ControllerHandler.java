package com.springboot.myweb.handler;

import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.HashMap;
import java.util.Map;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/12
 */
@ControllerAdvice
public class ControllerHandler {

    @ModelAttribute(name = "initData")
    public Map<String,Object> myData() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("age", 99);
        map.put("sex", "男");
        return map;
    }

    @InitBinder("user")
    public void user(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("user.");
    }
    @InitBinder("moreUser")
    public void moreUser(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("moreUser.");
    }

}
