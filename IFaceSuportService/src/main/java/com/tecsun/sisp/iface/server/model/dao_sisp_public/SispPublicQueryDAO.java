package com.tecsun.sisp.iface.server.model.dao_sisp_public;


import com.tecsun.sisp.iface.common.vo.cssp.Applybean;
import com.tecsun.sisp.iface.common.vo.cssp.UseInfoBean;
import com.tecsun.sisp.iface.server.util.MyBatisDao;


/**
 * Created by jerry on 2015/5/31.
 */
@MyBatisDao
public interface SispPublicQueryDAO {

    public UseInfoBean getInfo(String deviceId) throws Exception;
}
