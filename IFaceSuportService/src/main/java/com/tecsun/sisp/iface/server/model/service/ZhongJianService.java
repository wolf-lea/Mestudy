package com.tecsun.sisp.iface.server.model.service;


import com.tecsun.sisp.iface.common.vo.Page;
import com.tecsun.sisp.iface.common.vo.PostData;
import com.tecsun.sisp.iface.common.vo.postDataRequest;

public interface ZhongJianService {
	
	
	int addBarcode(PostData bean) throws Exception;
	
	public Page <PostData> querybyAuditTable(postDataRequest bean) throws Exception;//获取所有或者办件状态
	
	
	public int updateBjztAndBz(PostData bean);

}
