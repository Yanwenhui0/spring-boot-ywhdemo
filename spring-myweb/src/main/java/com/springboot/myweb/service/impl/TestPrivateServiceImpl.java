package com.springboot.myweb.service.impl;

import com.springboot.myweb.service.TestPrivateService;
import org.springframework.stereotype.Service;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/11/16
 */
@Service
public class TestPrivateServiceImpl implements TestPrivateService {

    @Override
    public void sendHello() {
        System.out.println("private 方法不能被代理");
    }
}
