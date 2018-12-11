package com.tecsun.sisp.iface.server.model.dao.version;

import java.util.List;
import java.util.Map;

import com.tecsun.sisp.iface.common.util.MyBatisDao;
import com.tecsun.sisp.iface.common.vo.version.result.DeviceRegist;
import com.tecsun.sisp.iface.common.vo.version.result.VerCheckRecordPo;
import com.tecsun.sisp.iface.common.vo.version.result.VerUpdateListParam;
import com.tecsun.sisp.iface.common.vo.version.result.VerUpdateListPo;
import com.tecsun.sisp.iface.common.vo.version.result.VersionPo;

@MyBatisDao
public interface VersionDao {

	/**
     * 添加一条更新记录到版本更新队列表
     */
    public int versionAddUpdateList(VerUpdateListPo po) ;
    /**
     * 添加一条版本校验记录
     */
    public int versionAddCheckRecord(VerCheckRecordPo po) ;

    /*根据ID查询*/
    public VerUpdateListPo versionGetByDeviceId(Map map) ;

    /**
     * 根据软件版本号查找软件版本信息
     */
    public List<VersionPo> versionGetByVersion(Map map);

    public int versionUpdateList(VerUpdateListPo po) ;
    public VerUpdateListPo versionGetByDev(VerUpdateListParam param) ;

    public List<DeviceRegist> versionGetDevice(String cpu) ;
    public VersionPo versionGetVerByAppType(String appType) ;
}
