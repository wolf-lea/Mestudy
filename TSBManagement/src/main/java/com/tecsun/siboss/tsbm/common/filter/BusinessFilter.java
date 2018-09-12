package com.tecsun.siboss.tsbm.common.filter;

import com.tecsun.siboss.tsbm.common.bean.DevBean;
import com.tecsun.siboss.tsbm.common.util.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    public void init(FilterConfig filterConfig) throws ServletException {

    }

    /**
     * 验证前端页面（TSB,selfterminal,weixin...）传入的tokenId是否合法
     * 如果有设备编码，则验证传入的设备编码是否合法（TSB）
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
        String ip = CommUtil.getIpAddr(req);
        String tokenId = req.getParameter("tokenId");
        String deviceid = req.getParameter("deviceid");
        try {
            String redisIP = StringUtils.isNotEmpty(tokenId) ? JedisUtil.getValue(tokenId) : "";
            if (StringUtils.isBlank(redisIP)) {
                String msg = "不存在此token，或超时被删除,tokenId:" + tokenId + ",IP：" + ip;
                logger.error("IFACE_ERRORCODE:IFAC-EERR-301,"+msg);
                resp.getWriter().print(GlobalResult.error_301(msg));
            } else {
                if (!redisIP.equals(ip)) {
                    logger.error("IFACE_ERRORCODE:IFAC-EERR-301,拦截非法访问,tokenId：" + tokenId + ",原IP：" + redisIP + ",拦截IP：" + ip);
                    resp.getWriter().print(GlobalResult.error_301("拦截非法访问，请重新登录"));
                }else{
                    //如果校验ip通过，则校验设备编码
                    boolean flag=checkDev(deviceid);
                    if (flag) {
                        JedisUtil.setValue(tokenId, ip);
                        chain.doFilter(request, response);
                    }else{
                        String msg="拦截非法访问,deviceid:"+deviceid+",tokenId：" + tokenId + ",拦截IP：" + ip;
                        logger.error("IFACE_ERRORCODE:IFAC-EERR-303,"+msg);
                        resp.getWriter().print(GlobalResult.error_303(msg));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("IFACE_ERRORCODE:IFAC-EERR-302,BusinessFilter 拦截出错," + e.getMessage());
            resp.getWriter().print(GlobalResult.error_302("BusinessFilter 拦截出错," + e.getMessage()));
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
                logger.error("校验设备编码失败,deviceid :"+deviceid+",result :" + result);
                logger.error(e.getMessage());
            }
        } else {
            return true;
        }
        return false;
    }

    public void destroy() {

    }
}
