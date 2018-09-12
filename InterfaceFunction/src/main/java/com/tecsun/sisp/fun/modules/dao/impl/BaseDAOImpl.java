package com.tecsun.sisp.fun.modules.dao.impl;

import com.tecsun.sisp.fun.modules.dao.BaseDAO;

import java.util.List;

/**
 * Created by zhangqingjie on 15-5-8.
 */
public abstract class BaseDAOImpl<T> implements BaseDAO<T> {
    @Override
    public T get(String id) {
        return null;
    }

    @Override
    public T get(T entity) {
        return null;
    }

    @Override
    public List<T> findList(T entity) {
        return null;
    }

    @Override
    public List<T> findAllList(T entity) {
        return null;
    }

    @Override
    public List<T> findAllList() {
        return null;
    }

    @Override
    public int insert(T entity) {
        return 0;
    }

    @Override
    public int update(T entity) {
        return 0;
    }

    @Override
    public int delete(String id) {
        return 0;
    }

    @Override
    public int delete(T entity) {
        return 0;
    }
}
