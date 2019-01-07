package com.tecsun.sisp.adapter.common.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.tecsun.sisp.adapter.common.util.DataBase;
import org.springframework.web.context.ContextLoader;

/**
 * 初始化redis连接池
 */
public class StartupListener extends ContextLoader implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DataBase.init();
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}