package com.fly.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterUtils;
import org.springframework.stereotype.Component;

/**
 * 
 * 命名SQL执行类
 * 
 * @author 00fly
 * @version [版本号, 2018-11-15]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Component
public class NamedSqlExecute
{
    private static final Logger LOGGER = LoggerFactory.getLogger(NamedSqlExecute.class);
    
    @Autowired
    DataSource dataSource;
    
    private JdbcTemplate jdbcTemplate;
    
    /**
     * 初始化 JdbcTemplate
     */
    @PostConstruct
    public void init()
    {
        jdbcTemplate = new JdbcTemplate(dataSource);
        LOGGER.info("JdbcTemplate = {}", jdbcTemplate);
    }
    
    /**
     * 执行查询
     * 
     * @param namedParamSQL 命名SQL
     * @param params 参数
     * @return
     * @see [类、类#方法、类#成员]
     */
    public List<Map<String, Object>> queryForList(String namedParamSQL, Map<String, Object> params)
    {
        String realSql = buildRealSQL(namedParamSQL, params);
        Object[] paramArr = buildValueArray(namedParamSQL, params);
        List<Map<String, Object>> list = jdbcTemplate.queryForList(realSql, paramArr);
        
        // 原生SQL相关信息
        LOGGER.info("★★★★ execute: paramArr = {}", Arrays.asList(paramArr));
        LOGGER.info("★★★★ execute: realSql  = {}", realSql);
        return list;
    }
    
    /**
     * 将namedParamSQL转换为带?的传统SQL
     * 
     * @param namedParamSQL 命名参数SQL
     * @param paramMap Map参数
     * @return
     * @see [类、类#方法、类#成员]
     */
    private static String buildRealSQL(String namedParamSQL, Map<String, Object> paramMap)
    {
        // 将多个不可见字符替换为空格
        namedParamSQL = namedParamSQL.replaceAll("(\t|\r|\n)+", " ");
        return NamedParameterUtils.substituteNamedParameters(NamedParameterUtils.parseSqlStatement(namedParamSQL), new MapSqlParameterSource(paramMap));
    }
    
    /**
     * 滤除无效参数列表后，以Object[]返回
     * 
     * @param namedParamSQL 命名参数SQL
     * @param paramMap Map参数
     * @return
     * @see [类、类#方法、类#成员]
     */
    private static Object[] buildValueArray(String namedParamSQL, Map<String, Object> paramMap)
    {
        Object[] params = NamedParameterUtils.buildValueArray(namedParamSQL, paramMap);
        List<Object> paramList = new ArrayList<>();
        for (Object obj : params)
        {
            if (List.class.isInstance(obj))
            {
                paramList.addAll((List<?>)obj);
            }
            else
            {
                paramList.add(obj);
            }
        }
        return paramList.toArray();
    }
}
