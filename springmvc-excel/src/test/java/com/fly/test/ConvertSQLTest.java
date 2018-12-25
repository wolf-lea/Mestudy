package com.fly.test;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fly.common.DataBaseInit;
import com.fly.core.NamedSqlExecute;

/**
 * 
 * 将namedParamSQL转换为带参数列表的传统SQL执行
 * 
 * @author 00fly
 * @version [版本号, 2018年11月10日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class ConvertSQLTest
{
    private static final Logger logger = LoggerFactory.getLogger(ConvertSQLTest.class);
    
    @Autowired
    NamedSqlExecute namedSqlExecute;
    
    /**
     * 执行数据库表初始化
     */
    @BeforeClass
    public static void initDB()
    {
        try
        {
            DataBaseInit.initUseSQL("/sql/init.sql");
        }
        catch (IOException | SQLException e)
        {
            logger.error(e.getMessage(), e);
        }
    }
    
    @Test
    public void test1()
        throws SQLException
    {
        // 将namedParamSQL转换为带参数列表的传统SQL
        String namedParamSQL = "select id, name from student where id<>:id and (id=:id1 or id=:id2)";
        Map<String, Object> params = new HashMap<>();
        params.put("id", 1);
        params.put("id1", 2);
        params.put("id2", 3);
        params.put("name", "001");
        params.put("name1", "002");
        params.put("name2", "003");
        
        List<Map<String, Object>> list = namedSqlExecute.queryForList(namedParamSQL, params);
        logger.info("★★★★ execute: result = {}", list);
    }
    
    @Test
    public void test2()
        throws SQLException
    {
        // IN条件
        String namedParamSQL = "select id, name from student where id in (:ids)";
        Map<String, Object> params = Collections.singletonMap("ids", Arrays.asList(1, 2, 3, 4, 5));
        
        List<Map<String, Object>> list = namedSqlExecute.queryForList(namedParamSQL, params);
        logger.info("★★★★ execute: result = {}", list);
    }
    
    @Test
    public void test3()
        throws SQLException
    {
        // IN条件&&其他条件
        String namedParamSQL = "select id, name from student where id=:id or id in (:ids)";
        Map<String, Object> params = new HashMap<>();
        params.put("id", 1);
        params.put("ids", Arrays.asList(1, 2, 3, 4, 5));
        
        List<Map<String, Object>> list = namedSqlExecute.queryForList(namedParamSQL, params);
        logger.info("★★★★ execute: result = {}", list);
    }
}
