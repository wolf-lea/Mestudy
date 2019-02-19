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
import com.zengyunhua.springcloud.service.DeptClientService;

/**
 * @title 部门消费者
 * @author zengyunhua
 * @2019年2月4日-下午3:54:19
 * @version2019
 */
@RestController
@EnableEurekaClient
public class DeptController_Consumer {
	
	@Autowired
	private DeptClientService service;
	
	
	@RequestMapping(value="/consumer/dept/get/{id}",method = RequestMethod.GET)
	public Dept get(@PathVariable("id") Long id){
		return this.service.get(id);
	}
	
	@RequestMapping(value = "/consumer/dept/list",method = RequestMethod.GET)
	public List<Dept> list() {
		return this.service.list();
	}
	
	@RequestMapping(value="/consumer/dept/add",method = RequestMethod.POST)
	public boolean add(Dept dept){
		return this.service.add(dept);
	}

	
	
}
