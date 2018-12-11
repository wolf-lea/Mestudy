package com.tecsun.sisp.iface.server.model.service.version;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.tecsun.sisp.iface.common.vo.version.result.DeviceRegist;
import com.tecsun.sisp.iface.common.vo.version.result.VerCheckRecordPo;
import com.tecsun.sisp.iface.common.vo.version.result.VerUpdateListParam;
import com.tecsun.sisp.iface.common.vo.version.result.VerUpdateListPo;
import com.tecsun.sisp.iface.common.vo.version.result.VersionPo;

public interface VersionService {

	 /**
     * 添加一条更新记录到版本更新队列表
     */
    public int versionAddUpdateList(VerUpdateListPo po) ;
    /**
     * 添加一条版本校验记录
     */
    public int versionAddCheckRecord(VerCheckRecordPo po) ;

    /*根据ID查询*/
    public VerUpdateListPo versionGetByDeviceId(Long deviceNo,String appType) ;

    /**
     * 根据软件版本号查找软件版本信息
     */
    public List<VersionPo> versionGetByVersion(String version, String appType);

    public int versionUpdateList(VerUpdateListPo po) ;
    public VerUpdateListPo versionGetByDev(VerUpdateListParam param) ;

    public List<DeviceRegist> versionGetDevice(String cpu) ;
    public VersionPo versionGetVerByAppType(String appType) ;
}
