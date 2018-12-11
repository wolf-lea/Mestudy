package com.tecsun.sisp.iface.common.util.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.ContextLoader;

import com.tecsun.sisp.iface.common.util.DataBase;

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