package com.springboot.lock.zookeeper;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.ListUtils;
import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.CreateMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/21
 */
@Component
public class LockByZookeeper {

    @Autowired
    private CuratorFramework curatorTemplate;

    public void getZookeeperLock() throws Exception {

        // 设置获取锁的超时时间
        long getLockTime = System.currentTimeMillis() + 3 * 1000;

        String key = curatorTemplate.create().withMode(CreateMode.EPHEMERAL).forPath("key");

        boolean isLock = false;
        while (System.currentTimeMillis() < getLockTime) {
            if(tryLock(key)) {
                isLock = true;
                break;
            }
            waitForLock();
        }

    }



    private boolean tryLock(String self) throws Exception {
        List<String> childList = curatorTemplate.getChildren().forPath("/");
        Collections.sort(childList);
        String firstNode;
        if(CollectionUtils.isEmpty(childList) || StringUtils.isBlank(firstNode = childList.get(0))) {
            throw new RuntimeException("zookeeper中不存在node!");
        }

        // 以获取到锁
        if(StringUtils.equals(self, firstNode)) {

        }
        return true;
    }

    private void waitForLock() {

    }

}
