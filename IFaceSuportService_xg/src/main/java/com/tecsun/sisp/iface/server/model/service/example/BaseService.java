package com.tecsun.sisp.iface.server.model.service.example;

import java.util.List;

public interface BaseService<T> {

	List<T> findAll(T t);

	List<T> findListByPage(T t);
}
