package com.fly.core.druid;

import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 * 
 * 监控数据库性能
 * 
 * @author 00fly
 * @version [版本号, 2018年9月20日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/druid/*", initParams = {@WebInitParam(name = "allow", value = "192.168.1.52,127.0.0.1"), // IP白名单 (没有配置或者为空，则允许所有访问)
    @WebInitParam(name = "loginUsername", value = "test"), // 用户名
    @WebInitParam(name = "loginPassword", value = "test"), // 密码
    @WebInitParam(name = "resetEnable", value = "false")// 禁用HTML页面上的“Reset All”功能
})
public class DruidStatViewServlet extends StatViewServlet
{
    
}
