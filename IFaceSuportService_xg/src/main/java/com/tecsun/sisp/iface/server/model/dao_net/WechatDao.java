package com.tecsun.sisp.iface.server.model.dao_net;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.tecsun.sisp.iface.server.util.MyBatisDao;

@MyBatisDao
public interface WechatDao {

	List<Map<String, Object>> getProfessionalTitle(
			@Param("xm")String xm,
			@Param("sfzh")String sfzh,
			@Param("zsbh")String zsbh);

}
