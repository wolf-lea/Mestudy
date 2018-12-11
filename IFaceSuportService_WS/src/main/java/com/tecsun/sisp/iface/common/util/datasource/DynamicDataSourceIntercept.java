package com.tecsun.sisp.iface.common.util.datasource;

import org.apache.commons.lang.ClassUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.lang.reflect.Proxy;

/**
 * Created by DG on 2015/10/9.
 */
@Aspect
@Component
public class DynamicDataSourceIntercept {

    @Before("execution(* com.tecsun.sisp.iface.server.model.service..*.*(..))")
    public void dynamicDataSourceAspect(JoinPoint joinPoint){
        System.out.println("================数据源切换开始处理=================");
        Class<?> clazz = joinPoint.getTarget().getClass();

        String className = clazz.getName();
        if(ClassUtils.isAssignable(clazz,Proxy.class)){
            className = joinPoint.getSignature().getDeclaringTypeName();
        }
        String methodName = joinPoint.getSignature().getName();

        if (className.contains("sms")) {
            CustomerContextHolder.setCustomerType(CustomerContextHolder.SMS_DATASOURCE);
        } else if (className.contains("sms")) {
            CustomerContextHolder.setCustomerType(CustomerContextHolder.SMS_DATASOURCE);
        } else if (methodName.contains("simis")) {
            CustomerContextHolder.setCustomerType(CustomerContextHolder.SIMIS_DATASOURCE);
        } else if (methodName.contains("simis")) {
            CustomerContextHolder.setCustomerType(CustomerContextHolder.SIMIS_DATASOURCE);
        } else if (className.contains("version")) {
            CustomerContextHolder.setCustomerType(CustomerContextHolder.VERSION_DATASOURCE);
        } else if (methodName.contains("version")) {
            CustomerContextHolder.setCustomerType(CustomerContextHolder.VERSION_DATASOURCE);
        } else {
            CustomerContextHolder.removeCustomerType();
        }


    }
}
