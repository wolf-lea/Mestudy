package com.tecsun.sisp.fakamanagement.modules.service;

import org.mybatis.spring.SqlSessionTemplate;

import javax.annotation.Resource;

/**
 * 
 * @author po_tan
 *
 */
public class BaseService {
    /*private SqlSessionTemplate sqlSessionTemplate;

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Resource
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }*/
	
	private SqlSessionTemplate userSqlSessionTemplate;
    private SqlSessionTemplate fakaSqlSessionTemplate;
    private SqlSessionTemplate channelSqlSessionTemplate;
    private SqlSessionTemplate settleSqlSessionTemplate;

    public SqlSessionTemplate getUserSqlSessionTemplate() {
        return userSqlSessionTemplate;
    }

    @Resource(name="userSqlSessionTemplate")
    public void setUserSqlSessionTemplate(SqlSessionTemplate userSqlSessionTemplate) {
        this.userSqlSessionTemplate = userSqlSessionTemplate;
    }

    public SqlSessionTemplate getFakaSqlSessionTemplate() {
        return fakaSqlSessionTemplate;
    }

    @Resource(name="fakaSqlSessionTemplate")
    public void setFakaSqlSessionTemplate(SqlSessionTemplate fakaSqlSessionTemplate) {
        this.fakaSqlSessionTemplate = fakaSqlSessionTemplate;
    }

    public SqlSessionTemplate getChannelSqlSessionTemplate() {
        return channelSqlSessionTemplate;
    }

    @Resource(name="channelSqlSessionTemplate")
    public void setChannelSqlSessionTemplate(SqlSessionTemplate channelSqlSessionTemplate) {
        this.channelSqlSessionTemplate = channelSqlSessionTemplate;
    }

    public SqlSessionTemplate getSettleSqlSessionTemplate() {
        return settleSqlSessionTemplate;
    }

    @Resource(name="settleSqlSessionTemplate")
    public void setSettleSqlSessionTemplate(SqlSessionTemplate settleSqlSessionTemplate) {
        this.settleSqlSessionTemplate = settleSqlSessionTemplate;
    }
}
