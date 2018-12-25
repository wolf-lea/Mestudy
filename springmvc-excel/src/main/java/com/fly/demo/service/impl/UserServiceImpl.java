package com.fly.demo.service.impl;

import java.util.List;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fly.common.PaginationSupport;
import com.fly.demo.dao.UserDAO;
import com.fly.demo.entity.User;
import com.fly.demo.service.UserService;

/**
 * 
 * UserService 接口实现类
 * 
 * @author 00fly
 * @version [版本号, 2018-11-06]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService
{
    
    static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    
    @Autowired
    UserDAO userDAO;
    
    @Override
    public void insert(User user)
    {
        userDAO.insert(user);
    }
    
    @Override
    public void deleteById(Long id)
    {
        userDAO.deleteById(id);
    }
    
    @Override
    public long deleteById(Long[] ids)
    {
        return userDAO.deleteById(ids);
    }
    
    @Override
    public long deleteById(List<Long> ids)
    {
        return userDAO.deleteById(ids);
    }
    
    @Override
    public void update(User user)
    {
        userDAO.updateById(user);
    }
    
    @Override
    public void saveOrUpdate(User user)
    {
        if (user.getId() == null)
        {
            userDAO.insert(user);
        }
        else
        {
            userDAO.updateById(user);
        }
    }
    
    @Override
    public User queryById(Long id)
    {
        return userDAO.queryById(id);
    }
    
    @Override
    public List<User> queryAll()
    {
        return userDAO.queryAll();
    }
    
    /**
     * 根据条件分页查询
     * 
     * @param user 条件对象
     * @param pageNo 页号
     * @param pageSize 页大小
     * @return
     */
    @Override
    public PaginationSupport<User> queryForPagination(User user, int pageNo, int pageSize)
    {
        return userDAO.queryForPagination(user, pageNo, pageSize);
    }
    
    /**
     * 事务方法
     * 
     * 
     * @see [类、类#方法、类#成员]
     */
    public void testTrans()
    {
        List<User> list = queryAll();
        for (User user : list)
        {
            userDAO.insert(user);
        }
        Assert.assertTrue(false); // 抛出异常
    }
}
