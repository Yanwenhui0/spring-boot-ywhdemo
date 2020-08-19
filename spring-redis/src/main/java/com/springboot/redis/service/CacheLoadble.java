package com.springboot.redis.service;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/8/19
 */
public class CacheLoadble<T> {

    /**
     * 通过重写此方法，将数据库获取到的对象 T 返回，传给 CacheTemplate
     * 此方法中写获取对象 T 的业务逻辑
     * @return
     */
    public T load(){
        return null;
    }

}
