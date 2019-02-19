package com.zengyunhua.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

import com.zengyunhua.myrule.MySelfRule;

/**
 * @title
 * @author zengyunhua
 * @2019年2月4日-下午4:46:59
 * @version2019
 */
@SpringBootApplication
@EnableEurekaClient
@RibbonClient(name="MICROSERVICECLOUD-DEPT",configuration=MySelfRule.class)
public class DeptConsumer80_App {
	
	public static void main(String[] args) {
		SpringApplication.run(DeptConsumer80_App.class, args);
	}

}
