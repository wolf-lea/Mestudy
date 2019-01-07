package com.tecsun.sisp.iface.server.model.dao_xg;

import com.tecsun.sisp.iface.common.vo.faceverify.XGPersonPO;
import com.tecsun.sisp.iface.server.util.MyBatisDao;

import java.util.List;
import java.util.Map;

/**
 * Created by Sandwich on 2015/12/11.
 */
@MyBatisDao
public interface XGPersonInfoDao {
    int insertPersonInfo(java.util.Map map);//新增数据到孝感数据库

    List<XGPersonPO> getXGPersonInfo(java.util.Map map);//从孝感数据库获取参保人员信息

    int updateXGPersonFPStatus(java.util.Map map);//更新人员表认证状态未认证

    int removeXGPersonFPStatus(java.util.Map map);//更新人员表认证状态

    int updateToken(java.util.Map map);//更新token

    int delPersonInfo(java.util.Map map);//删除人员信息记录
    
    int updateXGPersonPOToPassword(java.util.Map map);//更新密码
    
    int upPersonInfoByIdCard(Map map);// //根据身份证号码修改采集信息；
}
