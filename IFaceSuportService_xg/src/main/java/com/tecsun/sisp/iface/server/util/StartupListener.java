package com.tecsun.sisp.iface.server.util;

import org.springframework.web.context.ContextLoader;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ClassName: StartupListener
 * Description: 初始化应用程序
 * 1.初始化redis连接池
 * Author： 张清洁
 * CreateTime： 2015年06月07日 13时:50分
 */
public class StartupListener extends ContextLoader implements ServletContextListener {

    /**
     * 初始化redis,初始化线程池
     * @param servletContextEvent
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        DataBase.init();
        ThreadPoolUtil.init();
//        try {
//            DBConnection.initConn();
//        } catch (SQLException e) {
//            logger.error("初始化程序失败：无法初始化数据库连接:database sisp");
//        }
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {

    }

}
