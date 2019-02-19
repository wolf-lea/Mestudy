package com.tecsun.sisp.iface.server.model.service;


import javax.annotation.Resource;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import com.tecsun.sisp.iface.server.entity.requestBean.PersonParam;



@Service("authenticationService")
public class AuthenticationServiceImpl  {
	public static final Logger logger = Logger
			.getLogger(AuthenticationServiceImpl.class);

	 private SqlSessionTemplate sqlSessionTemplate;

	    public SqlSessionTemplate getSqlSessionTemplate() {
	        return sqlSessionTemplate;
	    }

	    @Resource
	    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
	        this.sqlSessionTemplate = sqlSessionTemplate;
	    }
	    
	    //保存认证数据
	    public int saveAuthenticationDatas(PersonParam vo){
			return  sqlSessionTemplate.insert("saveAuthenticationDatas",vo);
			
	    }
	    
}
