package com.tecsun.sisp.fun.modules.exception;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

/**
 * Created by zhangqingjie on 15-5-11.
 */
@Component
@Aspect
public class ExceptionHandler{

    private static Logger logger = LoggerFactory.getLogger(ExceptionHandler.class);

    @Pointcut("execution(* com.tecsun.sisp.fun.modules.service.impl..*(..))")
    public void pointCut(){}

    @Before("pointCut()")
    public void before(JoinPoint joinPoint){
        System.out.println("================开始拦截方法之前处理=================");
    }

    @AfterThrowing(pointcut = "pointCut()",throwing = "e")
    public void afterThrowing(JoinPoint joinPoint,Exception e){
        logger.error("异常类=======:" + joinPoint.getTarget().getClass() + "==" + e.getMessage());
    }
}
