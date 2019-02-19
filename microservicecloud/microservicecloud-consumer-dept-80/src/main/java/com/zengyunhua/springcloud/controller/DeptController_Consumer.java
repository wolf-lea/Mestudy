package com.zengyunhua.springcloud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.zengyunhua.springcloud.entity.Dept;

/**
 * @title 部门消费者
 * @author zengyunhua
 * @2019年2月4日-下午3:54:19
 * @version2019
 */
@RestController
@EnableEurekaClient
public class DeptController_Consumer {
	
	//private static final String REST_URL_PREFIX ="http://localhost:8001";
	private static final String REST_URL_PREFIX ="http://MICROSERVICECLOUD-DEPT";
	/**
	 * 使用restTemplate访问restful接口非常的简单粗暴无脑
	 * (url,requestMap,ResponseBean.class)这三个参数分别代表REST请求地址
	 * 请求参数，HTTP响应转换被转换的对象类型
	 */
	@Autowired
	private RestTemplate restTemplate;
	
	
	@RequestMapping(value="/consumer/dept/add")
	public boolean add(Dept dept){
		return restTemplate.postForObject(REST_URL_PREFIX+"/dept/add", dept, Boolean.class);
	}
	
	@RequestMapping(value="/consumer/dept/get/{id}")
	public Dept get(@PathVariable("id") Long id){
		return restTemplate.getForObject(REST_URL_PREFIX+"/dept/get/"+id, Dept.class);
	}
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/consumer/dept/list")
	public List<Dept> list(){
		return restTemplate.getForObject(REST_URL_PREFIX+"/dept/list", List.class);
	}
	
	@RequestMapping(value = "/consumer/dept/discovery", method = RequestMethod.GET)
	public Object discovery()
	{
		
		return restTemplate.getForObject(REST_URL_PREFIX+"/dept/discovery", Object.class);
	}

	
	
}
