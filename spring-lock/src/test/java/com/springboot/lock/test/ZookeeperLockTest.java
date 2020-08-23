package com.springboot.lock.test;

import com.springboot.lock.zookeeper.LockByZookeeper;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/21
 */
@SpringBootTest
public class ZookeeperLockTest {

    @Autowired
    private CuratorFramework curatorTemplate;

    @Autowired
    private LockByZookeeper lockByZookeeper;

    @Test
    public void getLock() throws Exception {
        lockByZookeeper.getZookeeperLock();
    }

    @Test
    public void createNode() throws Exception {
        System.out.println(curatorTemplate.create().withMode(CreateMode.PERSISTENT_SEQUENTIAL).forPath("/key"));
        List<String> forPath = curatorTemplate.getChildren().forPath("/");
        Collections.sort(forPath);
        for (String s : forPath) {
            System.out.println(s);
        }
    }

}
