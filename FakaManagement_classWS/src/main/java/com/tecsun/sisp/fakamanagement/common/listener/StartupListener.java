package com.tecsun.sisp.fakamanagement.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tecsun.sisp.fakamanagement.modules.util.ThreadPoolUtil;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.ContextLoader;

import com.tecsun.sisp.fakamanagement.common.DataBase;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * 初始化redis连接池
 */
public class StartupListener extends ContextLoader implements ServletContextListener {

    private static ApplicationContext ac = null;
    /*
    * 初始化redis，初始化线程池
    * */
    public void contextInitialized(ServletContextEvent servletContextEvent)
    {
        DataBase.init();
        ThreadPoolUtil.init();
        ac = WebApplicationContextUtils.getWebApplicationContext(servletContextEvent.getServletContext());
        /*SyncYbjsData syncThread = new SyncYbjsData();
        syncThread.start();*/
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent)
    {
    }

    public static ApplicationContext getApplicationContext() {
        return ac;
    }

}