package com.tecsun.sisp.iface.common.util.filter;

import java.io.IOException;
import java.util.Iterator;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tecsun.sisp.iface.common.util.*;
import org.apache.log4j.Logger;
import redis.clients.jedis.exceptions.JedisDataException;

public class RequestVerificationFilter implements Filter{

	public final static Logger logger = Logger.getLogger(RequestVerificationFilter.class);

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse,
		FilterChain filterChain) throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) servletRequest;
		HttpServletResponse response = (HttpServletResponse) servletResponse;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/json;charset=utf-8");

		String url = request.getServletPath();
		if(isNotFilter(url)) {
			filterChain.doFilter(servletRequest,servletResponse);
			return;
		}

		String token = request.getParameter("token");
		String devId = request.getParameter("devId");

		try {
//			if(token.equals(JedisUtil.getValue(devId)))
			System.out.println(111);
				filterChain.doFilter(servletRequest,servletResponse);
//			else{
//				ErrorResult result = new ErrorResult();
//				result.setStatusCode(GlobalVariable.RESULT_QUERY_NOT_EXIST);
//				result.setMessage("身份验证错误，请重新登陆");
//				response.getWriter().print(JsonHelper.javaBeanToJson(result));
//			}
		}catch (JedisDataException e){
			ErrorResult result = new ErrorResult();
			result.setStatusCode(GlobalVariable.RESULT_VERIFY_WRONG);
			result.setMessage(GlobalVariable.STRING_VERIFY_WRONG);
			response.getWriter().print(JsonHelper.javaBeanToJson(result));
		}catch (Exception e){
			e.printStackTrace();
			logger.equals("operator redis:" + e);
			ErrorResult result = new ErrorResult();
			result.setStatusCode(GlobalVariable.RESULT_SERVER_WRONG);
			result.setMessage(GlobalVariable.STRING_SERVER_WRONG);
			response.getWriter().print(JsonHelper.javaBeanToJson(result));
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		
	}

	public boolean isNotFilter(String url){
		boolean result = false;
		Iterator<String> ite = Constants.NOT_FILTER_URL.iterator();
		while(ite.hasNext()){
			if(ite.next().equals(url)) {
				result = true;
				return result;
			}
		}
		return result;
	}
}
