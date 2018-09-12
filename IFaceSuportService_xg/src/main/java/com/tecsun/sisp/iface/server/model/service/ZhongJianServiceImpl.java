package com.tecsun.sisp.iface.server.model.service;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.pdfbox.pdmodel.documentinterchange.logicalstructure.PDObjectReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.iface.common.vo.Page;
import com.tecsun.sisp.iface.common.vo.PostData;
import com.tecsun.sisp.iface.common.vo.postDataRequest;
import com.tecsun.sisp.iface.server.model.dao_zj.ZhongJianDAO;



@Service("zhongJianService")
public class ZhongJianServiceImpl  implements ZhongJianService{
	
	 public static final Logger logger = Logger.getLogger(ZhongJianServiceImpl.class);
	 
	 
	 @Autowired
	 private ZhongJianDAO zhongJianDAO;

	
	public int addBarcode(PostData bean) throws Exception {
		// TODO Auto-generated method stub
		return zhongJianDAO.addBarcode(bean);
	}
	
	 
	public Page <PostData> querybyAuditTable(postDataRequest bean) throws Exception{
		Page<PostData> page = new Page<>(bean.getPageno(),bean.getPagesize());
		
		bean.setPage(page);
		@SuppressWarnings("unchecked")
		List<PostData> podList = zhongJianDAO.querybyAuditTable(bean);
		if(podList!= null && podList.size()>0){
			page.setData(podList);
		}
		return page;
	}
	
	
	  public int updateBjztAndBz(PostData bean) {
	        return zhongJianDAO.updateBjztAndBz(bean);
	    }
	
}
