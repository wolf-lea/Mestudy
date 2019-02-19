package com.zengyunhua.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @title
 * @author zengyunhua
 * @2019年2月11日-下午5:45:31
 * @version2019
 */
@SpringBootApplication
@EnableEurekaServer
public class Config_Git_EurekaServerApplication {

	
	public static void main(String[] args) {
		SpringApplication.run(Config_Git_EurekaServerApplication.class, args);
	}

}
