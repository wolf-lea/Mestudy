package com.zengyunhua.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @title 启动类
 * @author zengyunhua
 * @2019年2月1日-下午1:31:17
 * @version2019
 */
@SpringBootApplication
@EnableEurekaClient  //本服务启动后会自动注册eureka服务中
@EnableDiscoveryClient  //服务的注册于发现
@EnableCircuitBreaker //对hystrix熔断机制的支持
public class DeptProvider8001_Hystrix_App {
	
	public static void main(String[] args) {
		SpringApplication.run(DeptProvider8001_Hystrix_App.class, args);
	}

}
