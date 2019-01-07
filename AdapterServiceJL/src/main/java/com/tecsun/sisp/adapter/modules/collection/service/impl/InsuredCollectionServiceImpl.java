package com.tecsun.sisp.adapter.modules.collection.service.impl;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.adapter.modules.common.service.BaseService;

/**
 *@author sipeng
 *@email zsp_banyue@163.com
 *@date 2017年12月11日
 * 
 */
@Service("InsuredCollectionService")
public class InsuredCollectionServiceImpl extends BaseService{
	
	 public static final Logger logger = Logger.getLogger(InsuredCollectionServiceImpl.class);

	 public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.collection.service.impl.InsuredCollectionServiceImpl.";

	/**全民参保  信息收集
	 * @param map
	 * @return
	 */
	public long insertInsuredInfo4Cssp(Map<String, Object> map) {
		long id = 0;
		try {
			id = this.getSqlSessionTemplate().insert(NAMESPACE+"insertInsuredInfo", map);
		} catch (Exception e) {
			 logger.error("存入信息失败", e);
			e.printStackTrace();
		}
		return id;
	}
	
	
}
