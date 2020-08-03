package com.springboot.springdatamore;

import com.springboot.springdatamore.repository.primary.UserRepository;
import com.springboot.springdatamore.repository.second.MoreUserRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class SpringdataMoreApplicationTests {

    @Autowired
    UserRepository userRepository;

    @Autowired
    MoreUserRepository moreUserRepository;

    @Test
    void contextLoads() {
        System.out.println(userRepository.findById(1));
        System.out.println(moreUserRepository.findById(1));
    }

}
