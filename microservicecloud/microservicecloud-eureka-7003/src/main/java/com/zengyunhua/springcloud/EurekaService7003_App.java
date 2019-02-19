package com.zengyunhua.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @title 服务注册中心启动类
 * @author zengyunhua
 * @2019年2月5日-上午10:13:16
 * @version2019
 */
@SpringBootApplication
@EnableEurekaServer	//EurekaService服务器端启动类 接受其他微服务注册进来
public class EurekaService7003_App {
	
	public static void main(String[] args) {
		SpringApplication.run(EurekaService7003_App.class, args);
	}

}
