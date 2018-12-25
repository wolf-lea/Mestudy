package com.fly.core.druid;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

import com.alibaba.druid.support.http.WebStatFilter;

/**
 * 
 * DruidStatFilter
 * 
 * @author 00fly
 * @version [版本号, 2018年9月20日]
 * @see https://www.jianshu.com/p/4b720ed4bb9a
 * @since [产品/模块版本]
 */
@WebFilter(filterName = "druidWebStatFilter", urlPatterns = "/*", initParams = {@WebInitParam(name = "exclusions", value = "*.js,*.gif,*.jpg,*.bmp,*.png,*.css,*.ico,/druid/*")// 忽略资源
})
public class DruidStatFilter extends WebStatFilter
{
}
