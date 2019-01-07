package com.tecsun.sisp.adapter.modules.common.service;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;

public class BaseService {
	private SqlSessionTemplate sqlSessionTemplate;
	
	public SqlSessionTemplate getSqlSessionTemplate(){
		return sqlSessionTemplate;
	}
	
	@Resource
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate){
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
}
