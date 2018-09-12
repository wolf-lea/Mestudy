package com.tecsun.sisp.iface.server.model.dao;

import java.util.List;

import com.tecsun.sisp.iface.common.vo.PersonVO;
import com.tecsun.sisp.iface.server.util.MyBatisDao;



@MyBatisDao
public interface NetPersonDAO {
	
	public List<PersonVO> findAllByPersonState(String personState) throws Exception;

}
