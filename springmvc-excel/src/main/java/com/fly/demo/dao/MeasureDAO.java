package com.fly.demo.dao;

import java.util.List;

import com.fly.common.PaginationSupport;
import com.fly.demo.entity.Measure;

/**
 * 
 * MeasureDAO 接口
 * 
 * @author 00fly
 * @version [版本号, 2018-11-06]
 * @see [相关类/方法]
 * @since [产品/模块版本]
 */
public interface MeasureDAO
{
    
    /**
     * 根据条件删除数据
     * 
     * @param criteria 条件对象
     * @return
     * 
     */
    int deleteByCriteria(Measure criteria);
    
    /**
     * 根据主键id删除数据
     * 
     * @param id 主键
     * @return
     * 
     */
    int deleteById(Long id);
    
    /**
     * 根据主键id列表删除数据
     * 
     * @param ids 主键列表
     * @return
     * 
     */
    int deleteById(Long[] ids);
    
    /**
     * 根据主键id列表删除数据
     * 
     * @param ids 主键列表
     * @return
     * 
     */
    int deleteById(List<Long> ids);
    
    /**
     * 增加记录(插入全字段)
     * 
     * @param bean 待插入对象
     * @return
     * 
     */
    int insert(Measure bean);
    
    /**
     * 增加记录(仅插入非空字段)
     * 
     * @param bean 待插入对象
     * @return
     * 
     */
    int insertSelective(Measure bean);
    
    /**
     * 查询全部
     * 
     * @return
     * 
     */
    List<Measure> queryAll();
    
    /**
     * 根据条件查询
     * 
     * @param criteria 条件对象
     * @return
     * 
     */
    List<Measure> queryByCriteria(Measure criteria);
    
    /**
     * 根据id查找数据
     * 
     * @param id 主键
     * @return
     * 
     */
    Measure queryById(Long id);
    
    /**
     * 根据条件分页查询
     * 
     * @param criteria 条件对象
     * @param pageNo 页号
     * @param pageSize 页大小
     * @return
     * 
     */
    PaginationSupport<Measure> queryForPagination(Measure criteria, int pageNo, int pageSize);
    
    /**
     * 根据条件查询数据条数
     * 
     * @param criteria 条件对象
     * @return
     * 
     */
    long queryTotal(Measure criteria);
    
    /**
     * 根据复杂条件更新全字段数据
     * 
     * @param bean 待更新对象
     * @param criteria 条件对象
     * @return
     * 
     */
    int updateByCriteria(Measure bean, Measure criteria);
    
    /**
     * 根据复杂条件更新非空字段数据
     * 
     * @param bean 待更新对象
     * @param criteria 条件对象
     * @return
     * 
     */
    int updateByCriteriaSelective(Measure bean, Measure criteria);
    
    /**
     * 根据id更新全部数据
     * 
     * @param bean 待更新对象
     * @return
     * 
     */
    int updateById(Measure bean);
    
    /**
     * 根据id更新非空字段数据
     * 
     * @param bean 待更新对象
     * @return
     * 
     */
    int updateByIdSelective(Measure bean);
    
}