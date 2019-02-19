package com.tecsun.sisp.net.modules.service.business.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.net.common.Page;
import com.tecsun.sisp.net.export.ExportService;
import com.tecsun.sisp.net.modules.dao.drafts_sisp.BusinessHandleDao;
import com.tecsun.sisp.net.modules.entity.business.BusinessHandle;

@Service
public class BusinessHandleExportService implements ExportService<BusinessHandle>{

	@Autowired
	private BusinessHandleDao businessHandleDao;
	
	@Override
	public Page<BusinessHandle> getPageList(BusinessHandle obj, Page<BusinessHandle> pages) {
		List<BusinessHandle> list = businessHandleDao.getBusinessHandlePageByCondition(obj);
		pages.setData(list);
		return pages;
	}

}
