package com.tecsun.sisp.net.modules.dao.drafts_sisp;

import java.util.List;
import java.util.Map;


import com.tecsun.sisp.net.common.MyBatisDao;
import com.tecsun.sisp.net.modules.entity.business.BusinessHandle;

@MyBatisDao
public interface BusinessHandleDao {

	/**
	 * 
	 * @param bh
	 * @return
	 */
	List<BusinessHandle> getBusinessHandlePageByCondition(
			BusinessHandle bh
			);

	/**
	 * 
	 * @param obj
	 * @return
	 */
	List<Map<String, Object>> getBusinessHandlePageMapByCondition(BusinessHandle obj);

}
