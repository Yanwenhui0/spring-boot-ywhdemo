import com.springboot.redis.RedisApplication;
import com.springboot.redis.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/13
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = RedisApplication.class)
public class RedisTest {

    @Resource
    UserService userService;

    @Test
    public void getUser() {
        System.out.println(userService.getUser());
    }

}
