package com.springboot.quartz.handle;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author : yanwenhui
 * @description : mybatis-plus 自动填充
 * @date : 2020/8/11
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    private static final Logger LOG = LoggerFactory.getLogger(MyMetaObjectHandler.class);

    /**
     * 创建时间
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        LOG.info(" -------------------- start insert fill ...  --------------------");
        if (null == getFieldValByName("createTime", metaObject)) {
            this.setInsertFieldValByName("createTime", LocalDateTime.now(), metaObject);
        }
        if (null == getFieldValByName("updateTime", metaObject)) {
            this.setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
        }
        if (null == getFieldValByName("deleteFlag", metaObject)) {
            this.setUpdateFieldValByName("deleteFlag", 0, metaObject);
        }
    }

    /**
     * 最后一次更新时间
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        LOG.info(" -------------------- start update fill ...  --------------------");
        this.setUpdateFieldValByName("updateTime", LocalDateTime.now(), metaObject);
    }

}
