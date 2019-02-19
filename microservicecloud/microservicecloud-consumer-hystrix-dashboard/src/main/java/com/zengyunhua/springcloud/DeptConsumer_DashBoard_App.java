package com.zengyunhua.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

/**
 * @title 启动类
 * @author zengyunhua
 * @2019年2月11日-上午10:07:34
 * @version2019
 */
@SpringBootApplication
@EnableHystrixDashboard
public class DeptConsumer_DashBoard_App {

	
	public static void main(String[] args) {
		SpringApplication.run(DeptConsumer_DashBoard_App.class, args);

	}

}
