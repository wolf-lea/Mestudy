package com.tecsun.sisp.net.modules.service.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.net.common.Page;
import com.tecsun.sisp.net.modules.dao.drafts_sisp.BusinessHandleDao;
import com.tecsun.sisp.net.modules.entity.business.BusinessHandle;
import com.tecsun.sisp.net.modules.service.business.BusinessHandleService;

@Service
public class BusinessHandleServiceImpl implements BusinessHandleService{

	@Autowired
	private BusinessHandleDao businessHandleDao;
	@Override
	public Page<BusinessHandle> getBusinessHandlePageByCondition(String sxmc, String sxbm, String bjbh, String sfzh,
			Integer pageNo, Integer pageSize) {
		
		//其实可以在controlelr那里直接用BusinessHandle对象接收参数,就不用再次拼装了,
		Page page = new Page();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		BusinessHandle bh = new BusinessHandle();
//		bh.setSxmc(sxmc);
//		bh.setSxbm(sxbm);
//		bh.setBjbh(bjbh);
//		bh.setSfzh(sfzh);
		bh.setPage(page);
		List<BusinessHandle> result = businessHandleDao.getBusinessHandlePageByCondition(bh);
		page.setData(result);
		return page;
	}
}
