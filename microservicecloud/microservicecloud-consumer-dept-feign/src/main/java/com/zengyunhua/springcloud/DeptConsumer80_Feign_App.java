package com.zengyunhua.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.ComponentScan;


/**
 * @title
 * @author zengyunhua
 * @2019年2月4日-下午4:46:59
 * @version2019
 */
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages={"com.zengyunhua.springcloud"})
@ComponentScan("com.zengyunhua.springcloud")
public class DeptConsumer80_Feign_App {
	
	public static void main(String[] args) {
		SpringApplication.run(DeptConsumer80_Feign_App.class, args);
	}

}
