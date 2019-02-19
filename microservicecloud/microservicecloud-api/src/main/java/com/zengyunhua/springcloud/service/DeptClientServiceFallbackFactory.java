package com.zengyunhua.springcloud.service;

import java.util.List;

import org.springframework.stereotype.Component;

import com.zengyunhua.springcloud.entity.Dept;

import feign.hystrix.FallbackFactory;

/**
 * @title
 * @author zengyunhua
 * @2019年2月11日-上午9:15:14
 * @version2019
 */
@Component
public class DeptClientServiceFallbackFactory implements FallbackFactory<DeptClientService> {

	@Override
	public DeptClientService create(Throwable throwable)
	{
		return new DeptClientService() {
			@Override
			public Dept get(long id)
			{
				return new Dept().setDeptno(id).setDname("该ID：" + id + "没有没有对应的信息,Consumer客户端提供的降级信息,此刻服务Provider已经关闭")
						.setDb_source("no this database in MySQL");
			}

			@Override
			public List<Dept> list()
			{
				return null;
			}

			@Override
			public boolean add(Dept dept)
			{
				return false;
			}
		};
	}

}
