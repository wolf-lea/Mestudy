package com.fly.demo.service;

import java.util.List;

import com.fly.common.PaginationSupport;
import com.fly.demo.entity.Measure;

/**
 * 
 * MeasureService 接口
 * 
 * @author 00fly
 * @version [版本号, 2018-11-06]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface MeasureService
{
    /**
     * 新增
     * 
     * @param measure
     * @see [类、类#方法、类#成员]
     */
    void insert(Measure measure);
    
    /**
     * 根据id删除
     * 
     * @param id
     * @see [类、类#方法、类#成员]
     */
    void deleteById(Long id);
    
    /**
     * 根据主键id列表删除数据
     * 
     * @param ids 主键列表
     * @return
     */
    long deleteById(Long[] ids);
    
    /**
     * 根据主键id列表删除数据
     * 
     * @param ids 主键列表
     * @return
     */
    long deleteById(List<Long> ids);
    
    /**
     * 根据id更新
     * 
     * @param measure
     * @see [类、类#方法、类#成员]
     */
    void update(Measure measure);
    
    /**
     * 新增/根据id更新
     * 
     * @param measure
     * @see [类、类#方法、类#成员]
     */
    void saveOrUpdate(Measure measure);
    
    /**
     * 根据id查询
     * 
     * @param id
     * @return
     * @see [类、类#方法、类#成员]
     */
    Measure queryById(Long id);
    
    /**
     * 查询全部
     * 
     * @return
     */
    List<Measure> queryAll();
    
    /**
     * 根据条件分页查询
     * 
     * @param criteria 条件对象
     * @param pageNo 页号
     * @param pageSize 页大小
     * @return
     */
    PaginationSupport<Measure> queryForPagination(Measure criteria, int pageNo, int pageSize);
    
    /**
     * 事务方法
     * 
     * @see [类、类#方法、类#成员]
     */
    void testTrans();
}
