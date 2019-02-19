package com.zengyunhua.springcloud.service;

import java.util.List;

import com.zengyunhua.springcloud.entity.Dept;

/**
 * @title service接口
 * @author zengyunhua
 * @2019年2月1日-下午1:16:57
 * @version2019
 */
public interface DeptService {
	
	public boolean add(Dept dept);

	public Dept get(Long id);

	public List<Dept> list();

}
