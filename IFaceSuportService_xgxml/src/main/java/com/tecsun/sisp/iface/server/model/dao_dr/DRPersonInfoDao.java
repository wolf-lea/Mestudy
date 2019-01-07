package com.tecsun.sisp.iface.server.model.dao_dr;

import java.util.List;
import java.util.Map;

import com.tecsun.sisp.iface.common.vo.Page;
import com.tecsun.sisp.iface.common.vo.faceverify.DRPersonPO;
import com.tecsun.sisp.iface.common.vo.faceverify.FafangInfo;
import com.tecsun.sisp.iface.common.vo.faceverify.PersonLoginBean;
import com.tecsun.sisp.iface.common.vo.faceverify.RegistBean;
import com.tecsun.sisp.iface.server.util.MyBatisDao;

/**
 * Created by Sandwich on 2015/12/11.
 */
//操作东软数据库
@MyBatisDao
public interface DRPersonInfoDao {

    List<DRPersonPO> getDRPersonInfo(Map map);//查找参保人员信息
    
    
    List<DRPersonPO> getPersonInfoByIdCard(RegistBean bean);//根据身份证号码、姓名、性别查询需要采集人的信息

//    List<IC09PO> getView (Map map);//从视图获取个人认证信息

    int insertView(Map map);//向视图增加个人认证信息
    
    Page<FafangInfo> getFafangInfo(PersonLoginBean bean);
}
