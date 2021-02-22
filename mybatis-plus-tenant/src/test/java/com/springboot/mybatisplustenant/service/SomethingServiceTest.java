package com.springboot.mybatisplustenant.service;

import com.springboot.mybatisplustenant.domain.Something;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2021/2/2
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class SomethingServiceTest {

    @Autowired
    private SomethingService somethingService;

    @Test
    public void listAll() {
        List<Something> all = somethingService.getAll();
        for (Something something : all) {
            System.out.println(something);
        }
    }

}
