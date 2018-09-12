package com.tecsun.sisp.iface.server.model.dao_dr;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.tecsun.sisp.iface.server.util.MyBatisDao;

@MyBatisDao
public interface YldyDao {

	List<String> getAAC001(@Param("idcard")String idcard);
}
