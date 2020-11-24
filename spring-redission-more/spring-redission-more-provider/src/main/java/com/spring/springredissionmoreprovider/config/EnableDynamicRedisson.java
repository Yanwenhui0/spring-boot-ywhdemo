package com.spring.springredissionmoreprovider.config;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/11/24
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({RedissionImportBeanDefinitionRegistrar.class})
public @interface EnableDynamicRedisson {
}
