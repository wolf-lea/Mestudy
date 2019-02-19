package com.zengyunhua.myrule;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;

/**
 * @title 自定义负载均衡Ribbon配置类
 * @author zengyunhua
 * @2019年2月10日-下午1:01:51
 * @version2019
 */
@Configuration
public class MySelfRule {
	
	@Bean
	public IRule myRule(){
		//return new RandomRule();//Ribbon默认轮询自定义为随机
		return new RandomRule_ZYH();
	}

}
