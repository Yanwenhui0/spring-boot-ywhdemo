package com.springboot.mybatisplustenant.service;

import com.springboot.mybatisplustenant.domain.Car;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2021/2/1
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class CarServiceTest {

    @Autowired
    private CarService carService;

    @Test
    public void save() {
        // 没有 tenant_id 字段，也没有忽略，会抛出找不到列名的错误
        carService.save(new Car().setCarName("第一辆"));
        carService.save(new Car().setCarName("第二辆"));
        carService.save(new Car().setCarName("第三辆"));
    }

}
