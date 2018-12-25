package com.fly.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * 
 * ComponentScanTest
 * 
 * @author 00fly
 * @version [版本号, 2018年11月12日]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/test-applicationContext.xml"})
public class ComponentScanTest
{
    private static final Logger LOGGER = LoggerFactory.getLogger(ComponentScanTest.class);
    
    @Autowired
    ApplicationContext applicationContext;
    
    @Before
    public void before()
    {
        LOGGER.info("★★★★★★★★ ApplicationContext = {}", applicationContext);
        int i = 1;
        for (String beanName : applicationContext.getBeanDefinitionNames())
        {
            LOGGER.info("{}.\t{}", i, beanName);
            i++;
        }
    }
    
    @Test
    public void test()
    {
    }
}
