package com.fly.main;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.fly.demo.entity.Measure;
import com.fly.demo.service.MeasureService;
import com.fly.demo.service.UserService;

/**
 * 
 * Main
 * 
 * @author 00fly
 * @version [版本号, 2018-11-06]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public class MainRun
{
    private static final Logger LOGGER = LoggerFactory.getLogger(MainRun.class);
    
    /**
     * Main
     * 
     * @param args
     * @see [类、类#方法、类#成员]
     */
    public static void main(String[] args)
    {
        GenericXmlApplicationContext context = new GenericXmlApplicationContext();
        context.setValidating(false);
        context.load("classpath*:application*.xml");
        context.refresh();
        UserService usersService = context.getBean(UserService.class);
        MeasureService measureService = context.getBean(MeasureService.class);
        LOGGER.info("data: {}", usersService.queryAll());
        LOGGER.info("--------size: {}", measureService.queryAll().size());
        try
        {
            Measure measure = new Measure();
            measure.setNo("no_" + RandomStringUtils.randomNumeric(5));
            measure.setAg(1.000);
            measureService.saveOrUpdate(measure);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        LOGGER.info("---------size: {}", measureService.queryAll().size());
        context.close();
    }
}
