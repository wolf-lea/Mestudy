package com.tecsun.sisp.iface.server.model.dao_xg;

import com.tecsun.sisp.iface.common.vo.faceverify.IC09PO;
import com.tecsun.sisp.iface.common.vo.faceverify.XGPersonPO;
import com.tecsun.sisp.iface.server.util.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * Created by Sandwich on 2015/12/14.
 */
@MyBatisDao
public interface IC09Dao {
    List<IC09PO> getIC09Info(java.util.Map map);//从孝感数据库获取参保人员信息

    int insertView(Map map);//向视图增加个人认证信息
    
	boolean updateView(Map map);
    
}
