package com.tecsun.sisp.iface.server.model.service.version.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.iface.common.vo.version.result.DeviceRegist;
import com.tecsun.sisp.iface.common.vo.version.result.VerCheckRecordPo;
import com.tecsun.sisp.iface.common.vo.version.result.VerUpdateListParam;
import com.tecsun.sisp.iface.common.vo.version.result.VerUpdateListPo;
import com.tecsun.sisp.iface.common.vo.version.result.VersionPo;
import com.tecsun.sisp.iface.server.model.dao.version.VersionDao;
import com.tecsun.sisp.iface.server.model.service.version.VersionService;

/**
 * Created by zhuxiaokai on 15-12-24.
 */
@Service("versionService")
public class VersionServiceImpl implements VersionService{

	@Autowired
    private VersionDao versionDao;
    
    /**
     * 添加一条更新记录到版本更新队列表
     */
    public int versionAddUpdateList(VerUpdateListPo po) {
        return versionDao.versionAddUpdateList(po);
    }

    /**
     * 添加一条版本校验记录
     */
    public int versionAddCheckRecord(VerCheckRecordPo po) {
        return versionDao.versionAddCheckRecord(po);
    }

    /*根据ID查询*/
    public VerUpdateListPo versionGetByDeviceId(Long deviceNo,String appType) {
        Map map = new HashMap();
        map.put("deviceNo",deviceNo);
        map.put("appType",appType);
        return versionDao.versionGetByDeviceId(map);
    }

    /**
     * 根据软件版本号查找软件版本信息
     */
    public List<VersionPo> versionGetByVersion(String version, String appType) {
        Map map = new HashMap<String, String>();
        map.put("version", version);
        map.put("appType", appType);
        return versionDao.versionGetByVersion(map);
    }

    public int versionUpdateList(VerUpdateListPo po) {
        return versionDao.versionUpdateList(po);
    }

    public VerUpdateListPo versionGetByDev(VerUpdateListParam param) {
        return versionDao.versionGetByDev(param);
    }

    public List<DeviceRegist> versionGetDevice(String cpu) {
        return versionDao.versionGetDevice(cpu);
    }

    public VersionPo versionGetVerByAppType(String appType)  {
        return versionDao.versionGetVerByAppType(appType);
    }
}
