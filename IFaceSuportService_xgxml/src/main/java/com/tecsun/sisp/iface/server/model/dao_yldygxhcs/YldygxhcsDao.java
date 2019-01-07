package com.tecsun.sisp.iface.server.model.dao_yldygxhcs;

import java.util.Map;


import com.tecsun.sisp.iface.server.util.MyBatisDao;

@MyBatisDao
public interface YldygxhcsDao {

	/**
	 * 通过存储过程得到个性化测算
	 * @param string
	 * @param aic161
	 * @param aic162
	 * @param jfdc
	 * @return
	 */
	Map<String, Object> getGxhcs(Map<String,Object> map);

}
