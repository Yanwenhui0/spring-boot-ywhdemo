package com.springboot.myweb.aspects;

import java.lang.annotation.*;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/11/16
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyTime {
}
