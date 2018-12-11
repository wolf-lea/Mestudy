package com.tecsun.sisp.iface.server.model.dao.example;

import java.util.List;

public interface BaseDao<T> {

	List<T> findAll(T t);

	List<T> findListByPage(T t);

}
