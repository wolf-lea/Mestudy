package com.tecsun.sisp.fakamanagement.common.filter.business;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import com.tecsun.sisp.fakamanagement.common.Config;
import com.tecsun.sisp.fakamanagement.common.DataBase;
import com.tecsun.sisp.fakamanagement.modules.util.MD5Generator;
import com.tecsun.sisp.fakamanagement.modules.util.PublicMethod;
import com.xx.util.exception.ExceptionUtil;

/**
 * 拦截请求
 * 1.判断是否用户session超时
 * 2.设置session redis
 * @author po_tan
 *
 */
public class BusinessFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(BusinessFilter.class);

    public static int ticketTimeOut  = Integer.parseInt(Config.getInstance().get("ticket_time_out"));
    
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 验证前端页面
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setCharacterEncoding("UTF-8");
        String ip = PublicMethod.getIpAddr(req);
        String usertype = req.getParameter("userType");
        Jedis publicJedis = null;
        JedisPool pool = null;
        try{
        	pool = DataBase.getJedisPublicPool();
            publicJedis = pool.getResource();
            String ticket = PublicMethod.getCookie("ticket" , req);
            String userId = publicJedis.hget(ticket, "userId");
            String loginName = publicJedis.hget(ticket, "loginName");
            if (ticket == null || ticket.equals("") || userId == null || userId.equals("")){
        		resp.setHeader("sessionstatus", "301");
        		resp.setHeader("usertype", usertype);
        		return ;
            }
            
            String browserInfo = ip + req.getHeader("User-Agent");// 验证浏览器信息（ip+端口+浏览器信息）
            HttpSession session = req.getSession();
            String jsessionId = session.getId();
            String _ticket = MD5Generator.generateValue(browserInfo + jsessionId + userId);
            publicJedis.hset(ticket, "browserInfo", browserInfo);
            publicJedis.hset(ticket, "jsessionId", jsessionId);
            publicJedis.hset(ticket, "userId", userId);

            String key = "ssologinkey:userid:" + userId;
            publicJedis.del(key);
            publicJedis.set(key, _ticket);
            publicJedis.expire(key, 60 * ticketTimeOut);

            publicJedis.hset(ticket, "loginName", loginName);
            publicJedis.expire(ticket, 60 * ticketTimeOut);
            Cookie cookie = new Cookie("ticket", ticket);// 保存ticket到客户端
            cookie.setPath("/");
            resp.addCookie(cookie);
            
            chain.doFilter(request, response);
            
        }catch(Exception e){
        	if (pool != null && publicJedis != null){
                pool.returnBrokenResource(publicJedis);
                publicJedis = null;
                pool = null;
            }
            logger.error(ExceptionUtil.getError(e));
            resp.setHeader("sessionstatus", "302");
            return;
        }finally {
            if (pool != null && publicJedis != null)
                pool.returnResource(publicJedis);
        }
        
    }

    public void destroy() {

    }
}
