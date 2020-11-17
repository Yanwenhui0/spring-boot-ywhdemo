package com.springboot.myweb.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author : yanwenhui
 * @description :
 * @date : 2020/11/16
 */
@Aspect
@Component
public class MyTimeAspect {

    @Pointcut("@annotation(com.springboot.myweb.aspects.MyTime)")
    public void pointCut() {
    }


    @Around("pointCut()")
    public Object aroundTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println(System.currentTimeMillis());
        Object proceed = proceedingJoinPoint.proceed();
        System.out.println(System.currentTimeMillis());
        return proceed;
    }

}
