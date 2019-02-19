package com.zengyunhua.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @title 配置中心
 * @author zengyunhua
 * @2019年2月11日-下午3:48:34
 * @version2019
 */
@SpringBootApplication
@EnableConfigServer
public class Config_3344_StartSpringCloudApp {
	
	public static void main(String[] args) {
		SpringApplication.run(Config_3344_StartSpringCloudApp.class, args);
	}

}
