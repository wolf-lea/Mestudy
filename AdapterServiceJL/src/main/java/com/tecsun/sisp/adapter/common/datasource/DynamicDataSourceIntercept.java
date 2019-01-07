package com.tecsun.sisp.adapter.common.datasource;

import java.lang.reflect.Proxy;

import org.apache.commons.lang.ClassUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.tecsun.sisp.adapter.common.datasource.CustomerContextHolder;

/**
 * 拦截切换数据源
 * 
 * @author tan
 *
 */
@Component
@Aspect
public class DynamicDataSourceIntercept {

	public static final Logger logger = Logger
			.getLogger(DynamicDataSourceIntercept.class);

	@Pointcut("execution(* com.tecsun.sisp.adapter.modules..service.impl..*(..))")
	public void pointCut() {
	}

	@Before("pointCut()")
	public void before(JoinPoint joinPoint) {
		logger.info("================数据源切换开始处理=================");
		Class<?> clazz = joinPoint.getTarget().getClass();

		String className = clazz.getName();
		if (ClassUtils.isAssignable(clazz, Proxy.class)) {
			className = joinPoint.getSignature().getDeclaringTypeName();
		}
		String methodName = joinPoint.getSignature().getName();

		logger.info("类名：" + className + " , 方法名：" + methodName);

		if (methodName.toLowerCase().contains("4sisp")) {
			CustomerContextHolder
					.setCustomerType(CustomerContextHolder.SISP_DATASOURCE);
		} else if (methodName.toLowerCase().contains("4cssp")) {
			CustomerContextHolder
					.setCustomerType(CustomerContextHolder.CSSP_DATASOURCE);
		}else if (methodName.toLowerCase().contains("4sssm")) {
			CustomerContextHolder
					.setCustomerType(CustomerContextHolder.SSSM_DATASOURCE);
		} else if (methodName.toLowerCase().contains("4other")) {
			CustomerContextHolder
			.setCustomerType(CustomerContextHolder.OTHER_DATASOURCE);
		} else if (methodName.toLowerCase().contains("4skcj")) {
			CustomerContextHolder
			.setCustomerType(CustomerContextHolder.SKCJ_DATASOURCE);
		} else if (methodName.toLowerCase().contains("4faka")) {
			CustomerContextHolder
			.setCustomerType(CustomerContextHolder.SISP_FAKA_DATASOURCE);
		}else {
			CustomerContextHolder.removeCustomerType();
		}
	}

	@AfterThrowing(pointcut = "pointCut()", throwing = "e")
	public void afterThrowing(JoinPoint joinPoint, Exception e) {
		logger.error("异常类=======:" + joinPoint.getTarget().getClass() + "=="
				+ e.getMessage(), e);
	}
}
