package com.fly.demo.service.impl;

import java.util.List;

import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fly.common.PaginationSupport;
import com.fly.demo.dao.MeasureDAO;
import com.fly.demo.entity.Measure;
import com.fly.demo.service.MeasureService;

/**
 * 
 * MeasureService 接口实现类
 * 
 * @author 00fly
 * @version [版本号, 2018-11-06]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MeasureServiceImpl implements MeasureService
{
    
    static final Logger LOGGER = LoggerFactory.getLogger(MeasureServiceImpl.class);
    
    @Autowired
    MeasureDAO measureDAO;
    
    @Override
    public void insert(Measure measure)
    {
        measureDAO.insert(measure);
    }
    
    @Override
    public void deleteById(Long id)
    {
        measureDAO.deleteById(id);
    }
    
    @Override
    public long deleteById(Long[] ids)
    {
        return measureDAO.deleteById(ids);
    }
    
    @Override
    public long deleteById(List<Long> ids)
    {
        return measureDAO.deleteById(ids);
    }
    
    @Override
    public void update(Measure measure)
    {
        measureDAO.updateById(measure);
    }
    
    @Override
    public void saveOrUpdate(Measure measure)
    {
        if (measure.getId() == null)
        {
            measureDAO.insert(measure);
        }
        else
        {
            measureDAO.updateById(measure);
        }
    }
    
    @Override
    public Measure queryById(Long id)
    {
        return measureDAO.queryById(id);
    }
    
    @Override
    public List<Measure> queryAll()
    {
        return measureDAO.queryAll();
    }
    
    /**
     * 根据条件分页查询
     * 
     * @param measure 条件对象
     * @param pageNo 页号
     * @param pageSize 页大小
     * @return
     */
    @Override
    public PaginationSupport<Measure> queryForPagination(Measure measure, int pageNo, int pageSize)
    {
        return measureDAO.queryForPagination(measure, pageNo, pageSize);
    }
    
    /**
     * 事务方法
     * 
     * 
     * @see [类、类#方法、类#成员]
     */
    public void testTrans()
    {
        List<Measure> list = queryAll();
        for (Measure measure : list)
        {
            measureDAO.insert(measure);
        }
        Assert.assertTrue(false); // 抛出异常
    }
}
