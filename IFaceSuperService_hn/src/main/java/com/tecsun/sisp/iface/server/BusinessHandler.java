package com.tecsun.sisp.iface.server;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 业务拦截处理类，发送数据到业务分析子系统
 * Created by jerry on 2015/6/1.
 */
@Component
@Aspect
public class BusinessHandler {

    private static Logger logger = LoggerFactory.getLogger(BusinessHandler.class);
    //@Pointcut("execution(* com.tecsun.sisp.iface.server.model.service.FlexEmpQueryServiceImpl.*(..))")
    //@Pointcut("execution(* com.tecsun.sisp.iface.server.controller.*.*.*(..))")
    @Pointcut("execution(* com.tecsun.sisp.iface.server.controller..*.*(..))")
    public void pointCut() {
    }

    @Before("pointCut()")
    public void before(JoinPoint joinPoint) {
    	System.out.println("前置通知正在run============>>>>>>>>");
    	
    	/*
        System.out.println("================开始拦截方法之前处理=================");
        Object[] objs = joinPoint.getArgs();
        String busiCode = Constants.BUSINESSCODE.get(joinPoint.getSignature().getName());//根据方法名称获取业务类型
        if (StringUtils.isNotEmpty(busiCode)) {
            if (null != objs && objs.length > 0) {
                if (objs[0] instanceof SecQueryBean) {
                    SecQueryBean bean = (SecQueryBean) objs[0];
                    TransBean transBean=new TransBean();
                    transBean.setUserid(bean.getSfzhm());
                    transBean.setChannelcode(bean.getChannelcode());
                    transBean.setBusinesscode(busiCode);
                    transBean.setDeviceid(bean.getDeviceid());
                    ThreadPoolUtil.getThreadPool().execute(new ThreadPoolTask(transBean));
                }
            }
        }*/
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "e")
    public void afterThrowing(JoinPoint joinPoint, Exception e) {
        logger.error("异常类=======:" + joinPoint.getTarget().getClass() + "==" + e.getMessage());
        System.out.println("NONONONONO");
    }
}
