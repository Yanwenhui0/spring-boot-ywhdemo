package com.springboot.springbootmonitor.controller;

import com.springboot.springbootmonitor.service.MonitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2021/2/22
 */
@RestController
@RequestMapping
public class MonitorController {

    @Autowired
    private MonitorService monitorService;

    @GetMapping("/all")
    public Map<String, Object> getStatus() {
        return monitorService.getServers();
    }

}
