package com.tecsun.sisp.net.modules.service.business;

import com.tecsun.sisp.net.common.Page;
import com.tecsun.sisp.net.modules.entity.business.BusinessHandle;

public interface BusinessHandleService {

	/**
	 * 分页查询
	 * @param sxmc
	 * @param sxbm
	 * @param bjbh
	 * @param sfzh
	 * @param pageSize 
	 * @param pageNo 
	 * @return
	 */
	Page<BusinessHandle> getBusinessHandlePageByCondition(String sxmc, String sxbm, String bjbh, String sfzh, Integer pageNo, Integer pageSize);

}
