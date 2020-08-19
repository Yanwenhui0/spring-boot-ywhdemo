import com.springboot.redis.RedisApplication;
import com.springboot.redis.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/13
 */
@SpringBootTest(classes = RedisApplication.class)
public class RedisTest {

    @Resource
    UserService userService;

    @Test
    public void getUser() {
        System.out.println(userService.getUserFinalStrategy());
    }

}
