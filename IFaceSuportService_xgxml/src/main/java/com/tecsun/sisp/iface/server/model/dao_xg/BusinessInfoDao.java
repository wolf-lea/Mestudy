package com.tecsun.sisp.iface.server.model.dao_xg;

import com.tecsun.sisp.iface.common.vo.Page;
import com.tecsun.sisp.iface.common.vo.faceverify.BusinessInfoPO;
import com.tecsun.sisp.iface.common.vo.faceverify.HistoryMsgBean;
import com.tecsun.sisp.iface.common.vo.faceverify.HistoryResult;
import com.tecsun.sisp.iface.server.util.MyBatisDao;

import java.util.List;

/**
 * Created by Sandwich on 2015/12/14.
 */
@MyBatisDao
public interface BusinessInfoDao {
    int insertBusinessFaceRe(java.util.Map map);//新增认证记录

    List<BusinessInfoPO> verifyHistory(HistoryMsgBean bean);//认证记录
    
    BusinessInfoPO getBusinessInfoByStatus(java.util.Map map);
}
