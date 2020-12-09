package com.springboot.myweb.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/12/9
 */
@RestController
@RequestMapping("/test-list")
public class TestListController {

    @PostMapping({"", "/"})
    public List<Map<String, Object>> getListMap(@RequestBody List<Map<String, Object>> body) {
        return body;
    }


}
