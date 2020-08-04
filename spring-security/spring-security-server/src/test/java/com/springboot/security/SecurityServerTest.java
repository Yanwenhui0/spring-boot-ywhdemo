package com.springboot.security;

import com.springboot.springsecurity.server.SpringSecurityServerApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/3
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringSecurityServerApplication.class)
public class SecurityServerTest {

    @Autowired
    @Qualifier("passwordEncoder")
//    @Resource
    BCryptPasswordEncoder passwordEncoder;

    @Test
    public void getEncodePassword() {
        System.out.println(passwordEncoder.encode("123456"));
    }

}
