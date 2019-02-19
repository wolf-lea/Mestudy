package com.zengyunhua.springcloud.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.zengyunhua.springcloud.entity.Dept;

/**
 * @title DAO
 * @author zengyunhua
 * @2019年2月1日-下午1:09:51
 * @version2019
 */
@Mapper
public interface DeptDao {
	
	
	public boolean addDept(Dept dept);

	public Dept findById(Long id);

	public List<Dept> findAll();

}
