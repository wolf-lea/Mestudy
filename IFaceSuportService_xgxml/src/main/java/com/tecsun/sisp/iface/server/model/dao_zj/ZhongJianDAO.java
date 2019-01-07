package com.tecsun.sisp.iface.server.model.dao_zj;

import java.util.List;

import com.tecsun.sisp.iface.common.vo.Page;
import com.tecsun.sisp.iface.common.vo.PostData;
import com.tecsun.sisp.iface.common.vo.postDataRequest;
import com.tecsun.sisp.iface.server.util.MyBatisDao;


@MyBatisDao
public interface ZhongJianDAO {
	
	  //2018/0512核定申请
    public int addBarcode(PostData bean) throws Exception;
    
    
    //2018/0513查询
    
    public List<PostData> querybyAuditTable(postDataRequest bean) throws Exception;
    
    
    public int updateBjztAndBz(PostData bean);

}
