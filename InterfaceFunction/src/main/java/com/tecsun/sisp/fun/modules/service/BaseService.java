package com.tecsun.sisp.fun.modules.service;

import java.util.List;

/**
 * Created by zhangqingjie on 15-5-8.
 */
public interface BaseService<T> {
    /**
     * 获取单条数据
     *
     * @param id
     * @return
     */
    public T get(String id)throws Exception;

    /**
     * 获取单条数据
     *
     * @param entity
     * @return
     */
    public T get(T entity)throws Exception;

    /**
     * 查询数据列表，如果需要分页，请设置分页对象，如：entity.setPage(new Page<T>());
     *
     * @param entity
     * @return
     */
    public List<T> findList(T entity)throws Exception;

    /**
     * 查询所有数据列表
     *
     * @param entity
     * @return
     */
    public List<T> findAllList(T entity)throws Exception;

    /**
     * 查询所有数据列表
     *
     * @return
     * @see public List<T> findAllList(T entity)
     */
    @Deprecated
    public List<T> findAllList()throws Exception;

    /**
     * 插入数据
     *
     * @param entity
     * @return
     */
    public int insert(T entity)throws Exception;

    /**
     * 更新数据
     *
     * @param entity
     * @return
     */
    public int update(T entity)throws Exception;

    /**
     * 删除数据（一般为逻辑删除，更新del_flag字段为1）
     *
     * @param id
     * @return
     * @see public int delete(T entity)
     */
    @Deprecated
    public int delete(String id)throws Exception;

    /**
     * 删除数据（一般为逻辑删除，更新del_flag字段为1）
     *
     * @param entity
     * @return
     */
    public int delete(T entity)throws Exception;
}
