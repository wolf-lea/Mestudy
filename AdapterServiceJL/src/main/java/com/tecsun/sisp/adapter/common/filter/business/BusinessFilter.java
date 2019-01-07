package com.tecsun.sisp.adapter.common.filter.business;

import java.io.IOException;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.tecsun.sisp.adapter.common.util.CommUtil;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.DictionaryUtil;
import com.tecsun.sisp.adapter.common.util.GlobalResult;
import com.tecsun.sisp.adapter.common.util.JedisUtil;
import com.tecsun.sisp.adapter.common.util.JsonMapper;
import com.tecsun.sisp.adapter.modules.common.entity.request.DevBean;

/**
 * ClassName: BusinessFilter
 * Description:  拦截器：
 * 1.判断是否合法用户;
 * <p/>
 * Author： 张清洁
 * CreateTime： 2015年06月07日 13时:44分
 */
public class BusinessFilter implements Filter {

    private static Logger logger = LoggerFactory.getLogger(BusinessFilter.class);
    /**
     * 需要排除的页面
     */
    private String escape;
    private String[] escapeArray;
    public void init(FilterConfig filterConfig) throws ServletException {
        escape = filterConfig.getInitParameter("Escape");
        if (StringUtils.isNotEmpty(escape)) {
            escapeArray = escape.split(",");
        }
        return;
    }

    /**
     * 验证前端页面（TSB,App,Wechat...）传入的tokenId是否合法
     * 如果有设备编码，则验证传入的设备编码是否合法（TSB）
     *
     * @param request
     * @param response
     * @param chain
     * @throws IOException
     * @throws ServletException
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean isExcluded = false;
        if (escapeArray != null && escapeArray.length > 0) {
            for (String escapeUrl : escapeArray) {//判断是否属于例外url
                if (((HttpServletRequest) request).getServletPath().contains(escapeUrl)) {
                    isExcluded = true;
                    break;
                }
            }
        }
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        resp.setCharacterEncoding("UTF-8");
        String ip = CommUtil.getIpAddr(req);
        String tokenId = req.getParameter("adaptertokenId");
        String deviceid = req.getParameter("deviceid");
        //      ------------------------检验权限---------------
        if (!isExcluded) {
            try {
                String redisIP = StringUtils.isNotEmpty(tokenId) ? JedisUtil.getValue(tokenId) : "";
                if (StringUtils.isBlank(redisIP)) {
                    String msg = "不存在此token，或超时被删除,adaptertokenId:" + tokenId + ",IP：" + ip;
                    logger.error("ADAPTER_ERRORCODE:ADAPTER-EERR-301," + msg);
                    resp.getWriter().print(GlobalResult.error_301("超时，请重新登陆"));
                } else {
                   /* if (!redisIP.equals(ip)) {
                        logger.error("ADAPTER_ERRORCODE:ADAPTER-EERR-301,拦截非法访问,adaptertokenId：" + tokenId + ",原IP：" + redisIP + ",拦截IP：" + ip);
                        resp.getWriter().print(GlobalResult.error_301("拦截非法访问，请重新登录"));
                    }*/
                    JedisUtil.setValue(tokenId, ip);
                    chain.doFilter(request, response);
                }
            } catch (Exception e) {
                logger.error("ADAPTER_ERRORCODE:ADAPTER-EERR-302,BusinessFilter 拦截出错,", e);
                resp.getWriter().print(GlobalResult.error_302("BusinessFilter 拦截出错"));
            }
        } else {//例外url
            chain.doFilter(request, response);
        }
    }

    private boolean checkDev(String deviceid) {
        if (StringUtils.isNotEmpty(deviceid)) {
            String result="";
            try {
                String dev_uri = DictionaryUtil.getHost(Constants.DEV_URI) + "/" + deviceid;
                result = DictionaryUtil.getClientRequest(dev_uri);
                if (!"".equals(result)) {
                    DevBean devBean = JsonMapper.buildNormalMapper().fromJson(result, DevBean.class);
                    if (devBean != null) {
                        if (StringUtils.equals(devBean.getStatus(), "0")) {
                            return true;
                        }
                    }
                }
            } catch (Exception e) {
                logger.error("===checkDev:"+deviceid+",result:===" + result,e);
            }
        } else {
            return true;
        }
        return false;
    }
    
    private boolean checkUser(String userid , String method) throws IOException{
    	try{
    		String url = DictionaryUtil.getHost(Constants.INTERFACE_CHECKUSER)+"?userid="+userid+"&method="+method;
        	String result = DictionaryUtil.getClientRequest( url);
        	Map map = JsonMapper.buildNormalMapper().fromJson(result, Map.class);
        	Map data = (Map) map.get("result");
        	if(data == null) 
        		return false;
        	else
        		return true;
    	}catch(Exception e){
    		logger.error("===userid:"+userid+",method:===" + method);
            logger.error(e.getMessage(),e);
    		return false;
    	}
    	
    }


    public void destroy() {

    }
}
