package com.fly.test;

import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.fly.demo.entity.User;
import com.fly.demo.service.UserService;

/**
 * Junit 单元测试
 * 
 * @author 00fly
 * @version [版本号, 2018-11-06]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Transactional
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class UserServiceTest
{
    @Autowired
    UserService userService;
    
    @Test
    public void test()
    {
        userService.queryAll();
    }
    
    /**
     * 事务测试(去掉@Transactional数据被插入)
     * 
     * @see [类、类#方法、类#成员]
     */
    @Test
    public void testInsert()
    {
        List<User> list = userService.queryAll();
        for (User user : list)
        {
            user.setName("username_" + RandomStringUtils.randomNumeric(2, 10));
            user.setAge(RandomUtils.nextInt(10, 60));
            userService.saveOrUpdate(user);
        }
    }
}
