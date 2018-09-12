package com.tecsun.sisp.iface.server.controller.version;

import com.tecsun.sisp.iface.common.util.Config;
import com.tecsun.sisp.iface.common.util.OutputMsg;
import com.tecsun.sisp.iface.common.util.OutputStatusCode;
import com.tecsun.sisp.iface.common.util.VersionUpdateTools;
import com.tecsun.sisp.iface.common.vo.version.param.DeviceParam;
import com.tecsun.sisp.iface.common.vo.version.result.*;
import com.tecsun.sisp.iface.server.controller.version.BaseController;
import com.tecsun.sisp.iface.server.model.service.version.impl.VersionServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by huanghailiang on 15-12-24.
 */
@Controller
@RequestMapping(value = "/iface/tsbm/version")
public class SoftwareVersionController extends BaseController {

    @Autowired
    private VersionServiceImpl versionService;


    public static final Logger logger = Logger.getLogger(SoftwareVersionController.class);

    protected VerUpdateListPo getUpdateListPo(VerUpdateListPo po) {
        VerUpdateListPo updatePo = new VerUpdateListPo();
        updatePo.setId(po.getId());
        updatePo.setStatus("2");
        updatePo.setPushNum(po.getPushNum() + 1);
        updatePo.setModTime(new Date());
        updatePo.setModUser("system");
        return updatePo;
    }

    @RequestMapping(value = "/checkUpdate", method = RequestMethod.POST)
    @ResponseBody
    public Result checkVersion(@RequestBody DeviceParam param) {
        String cpu = param.getCpu();
        String appType = param.getAppType();
        String devSoftwareVer = param.getSoftwareVersion();

        logger.info("检测更新：设备唯一号=" + cpu + "，APP类型=" + appType + "，当前版本号=" + devSoftwareVer);

        if (StringUtils.isBlank(appType)) {
            return error("缺少APP类型信息");
        }
        if (StringUtils.isBlank(devSoftwareVer)) {
            return error("缺少软件版本号信息");
        }
        if (StringUtils.isBlank(cpu)) {
            return error("缺少设备唯一号信息");
        }

        List<DeviceRegist> list = new ArrayList<DeviceRegist>();
        try {
            list = versionService.versionGetDevice(cpu);
        } catch (Exception e) {
            logger.error("根据设备唯一号获取设备信息出错，设备唯一号=" + cpu, e);
            return this.fail(OutputStatusCode.SERVER_FAILURE, OutputMsg.SERVER_EXCEPTION);
        }
        if (list.size() == 1) {//设备表存在该设备并且该设备处于启用状态
            List<VersionPo> versionPos = new ArrayList<VersionPo>();
            try {
                versionPos = versionService.versionGetByVersion(devSoftwareVer, appType);
            } catch (Exception e2) {
                logger.error("查找设备当前正在使用的软件版本信息出错", e2);
                return this.fail(OutputStatusCode.SERVER_FAILURE, OutputMsg.SERVER_EXCEPTION);
            }

            if (versionPos.size() <= 0) {
                logger.error("软件版本表内没有找到设备正在使用的软件版本记录，版本号=" + devSoftwareVer);
                return error("软件版本表内没有找到设备正在使用的软件版本记录");
            } else if (versionPos.size() > 1) {
                logger.error("软件版本表内同一APP类型与版本号的软件存在重复，版本号=" + devSoftwareVer + "，APP类型=" + appType);
                return error("系统异常，请联系技术人员处理");
            }

            //检查更新队列表是否存在未完成的记录，有则更改更新状态为已完成，添加完成时间
            VerUpdateListParam update = new VerUpdateListParam();
            update.setVersionId(versionPos.get(0).getId());
            update.setDeviceNo(list.get(0).getDeviceId().toString());
            VerUpdateListPo updateListPo = null;
            try {
                updateListPo = versionService.versionGetByDev(update);
            } catch (Exception e3) {
                logger.error("查找设备当前正在使用的软件版本更新队列信息出错，软件版本id=" + versionPos.get(0).getId() + "，设备id=" + list.get(0).getDeviceId(), e3);
                return this.fail(OutputStatusCode.SERVER_FAILURE, OutputMsg.SERVER_EXCEPTION);
            }

            if (updateListPo != null) {//如果更新队列中存在当前使用版本的信息，则把状态改成已完成
                if (!"3".equals(updateListPo.getStatus())) {
                    updateListPo.setStatus("3");
                    updateListPo.setFinishTime(new Date());
                    try {
                        versionService.versionUpdateList(updateListPo);
                    } catch (Exception e5) {
                        logger.error("更新队列信息出错，队列id=" + updateListPo.getId(), e5);
                        return this.fail(OutputStatusCode.SERVER_FAILURE, OutputMsg.SERVER_EXCEPTION);
                    }
                }
            }

            VersionPo versionPo = null;
            try {
                versionPo = versionService.versionGetVerByAppType(appType);//获取某一APP类型最新的软件版本
            } catch (Exception e1) {
                logger.error("根据设备APP类型获取设备软件版本信息出错，设备APP类型=" + appType, e1);
                return this.fail(OutputStatusCode.SERVER_FAILURE, OutputMsg.SERVER_EXCEPTION);
            }

            String needUpdate = "";
            VerUpdateListParam verUpdateListParam = new VerUpdateListParam();
            verUpdateListParam.setVersionId(versionPo.getId());
            verUpdateListParam.setDeviceNo(String.valueOf(list.get(0).getDeviceId().toString()));

            if (Integer.parseInt(versionPo.getVersion()) - Integer.parseInt(devSoftwareVer) > 0) {//需要更新
                VerUpdateListPo versionUpdateListPo = null;
                try {
                    versionUpdateListPo = versionService.versionGetByDev(verUpdateListParam);
                } catch (Exception e4) {
                    logger.error("根据设备APP类型获取设备软件版本信息出错，设备APP类型=" + appType, e4);
                    return this.fail(OutputStatusCode.SERVER_FAILURE, OutputMsg.SERVER_EXCEPTION);
                }
                if (versionUpdateListPo == null) {
                    //在版本更新队列表增加记录
                    VerUpdateListPo verUpdateListPo = new VerUpdateListPo();
                    verUpdateListPo.setDeviceNo(list.get(0).getDeviceId().toString());
                    verUpdateListPo.setVersionId(versionPo.getId());
                    verUpdateListPo.setStatus("1");
                    verUpdateListPo.setUpdateType("1");
                    verUpdateListPo.setCreateUser("system");
                    verUpdateListPo.setPushNum(0);
                    verUpdateListPo.setRemark("外部接口校验推送");
                    try {
                        versionService.versionAddUpdateList(verUpdateListPo);
                    } catch (Exception e7) {
                        logger.error("新增软件版本队列信息出错", e7);
                        return this.fail(OutputStatusCode.SERVER_FAILURE, OutputMsg.SERVER_EXCEPTION);
                    }
                } else {
                    if ("3".equals(versionUpdateListPo.getStatus())) {
                        versionUpdateListPo.setStatus("1");
                        versionUpdateListPo.setModTime(new Date());
                        versionUpdateListPo.setModUser("system");
                        try {
                            versionService.versionUpdateList(versionUpdateListPo);
                        } catch (Exception e8) {
                            logger.error("修改软件版本队列信息出错，队列id=" + versionUpdateListPo.getId(), e8);
                            return this.fail(OutputStatusCode.SERVER_FAILURE, OutputMsg.SERVER_EXCEPTION);
                        }
                    }
                }
                Date downloadTime = VersionUpdateTools.getDownloadTime();
                //新增校验记录
                needUpdate = "Y";
                try {
                    addVerCheckRec(list.get(0), needUpdate, devSoftwareVer, versionPo);
                } catch (Exception e9) {
                    logger.error("添加软件版本校验记录出错", e9);
                    return this.fail(OutputStatusCode.SERVER_FAILURE, OutputMsg.SERVER_EXCEPTION);
                }
                String path = versionPo.getUrl();
                String tsbm = Config.getInstance().get("upload_file_path");
                path = tsbm + File.separator + path;
                File file = new File(path);
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", versionPo.getId());
                map.put("name", versionPo.getName());
                map.put("version", versionPo.getVersion());
                map.put("size", file.length());
                map.put("downloadTime", downloadTime);
                return ok("需要更新", map);
            } else {
                needUpdate = "N";
                try {
                    addVerCheckRec(list.get(0), needUpdate, devSoftwareVer, versionPo);
                } catch (Exception e10) {
                    logger.error("添加软件版本校验记录出错", e10);
                    return this.fail(OutputStatusCode.SERVER_FAILURE, OutputMsg.SERVER_EXCEPTION);
                }
                return error("无需更新");
            }
        } else if (list.size() > 1) {
            logger.error("设备表里同一设备唯一号存在多条记录，设备唯一号=" + cpu);
            return error("系统异常，请联系技术人员处理");
        } else {
            return error("该设备未启用或找不到该设备的注册信息");
        }
    }

    private void addVerCheckRec(DeviceRegist po, String needUpdate, String devSoftwareVer, VersionPo newestVer) throws Exception {
        VerCheckRecordPo verCheckRecordPo = new VerCheckRecordPo();
        verCheckRecordPo.setDeviceId(Long.parseLong(String.valueOf(po.getDeviceId())));
        verCheckRecordPo.setDeviceType("1");
        verCheckRecordPo.setAreaId(po.getAreaId());
        verCheckRecordPo.setCurrVersion(devSoftwareVer);
        verCheckRecordPo.setLatestVersionId(newestVer.getId());
        verCheckRecordPo.setLatestVersion(newestVer.getVersion());
        verCheckRecordPo.setNeedUpdate(needUpdate);
        verCheckRecordPo.setOptUser("system");
        versionService.versionAddCheckRecord(verCheckRecordPo);
    }

    @RequestMapping(value = "/downloadVersion", method = RequestMethod.POST)
    @ResponseBody
    public Result downloadVersion(@RequestBody DeviceParam param, HttpServletResponse response) {
        String cpu = param.getCpu();
        String appType = param.getAppType();

        logger.info("下载软件版本文件，设备唯一号=" + cpu + "，APP类型=" + appType);

        if (StringUtils.isBlank(cpu) || StringUtils.isBlank(appType)) {
            return error("缺少必要的信息");
        }
        OutputStream os = null;
        BufferedInputStream bis = null;
        InputStream is = null;
        List<DeviceRegist> list = new ArrayList<DeviceRegist>();
        try {
            list = versionService.versionGetDevice(cpu);
        } catch (Exception e) {
            logger.error("根据设备唯一号获取设备信息出错，设备唯一号=" + cpu, e);
            return this.fail(OutputStatusCode.SERVER_FAILURE, OutputMsg.SERVER_EXCEPTION);
        }
        if (list.size() == 1) {//设备表存在该设备并且该设备处于启用状态
            VerUpdateListPo updateListPo = null;
            try {
                updateListPo = versionService.versionGetByDeviceId(Long.parseLong(String.valueOf(list.get(0).getDeviceId())), appType);
            } catch (Exception e1) {
                logger.error("获取设备与版本相关更新队列信息出错", e1);
                return this.fail(OutputStatusCode.SERVER_FAILURE, OutputMsg.SERVER_EXCEPTION);
            }
            if (updateListPo == null) {
                return error("更新队列中找不到相关更新记录，请先检测是否需要更新");
            }
            VersionDownloadInfo downloadInfo = new VersionDownloadInfo();

            String path = "";
            if (updateListPo != null && !"3".equals(updateListPo.getStatus())) {
                path = updateListPo.getUrl();
                String tsbm = Config.getInstance().get("upload_file_path");
                path = tsbm + path;
                File file = new File(path);
                String fileName = file.getName();
                if (!file.exists() || !file.isFile()) {
                    logger.error("用于更新的软件版本文件丢失，版本表id=" + updateListPo.getVersionId() +
                            "，版本号=" + updateListPo.getVersion() + "，路径=" + path);
                    return error("需要下载的版本更新文件不存在，请联系技术人员处理");
                }
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition", "attachment;fileName=" + URLEncoder.encode(fileName));
                try {
                    is = new FileInputStream(file);
                    bis = new BufferedInputStream(is);
                    os = new BufferedOutputStream(response.getOutputStream());
                    int len;
                    byte[] bytes = new byte[10240];
                    while (-1 != (len = bis.read(bytes))) {
                        os.write(bytes, 0, len);
                    }
                    os.flush();
                } catch (Exception e2) {
                    logger.error("下载版本文件出错，文件路径=" + path);
                    return this.fail(OutputStatusCode.SERVER_FAILURE, OutputMsg.SERVER_EXCEPTION);
                } finally {
                    try {
                        if (os != null) {
                            os.close();
                        }
                        if (bis != null) {
                            bis.close();
                        }
                    } catch (Exception e4) {
                        logger.error(OutputMsg.SERVER_EXCEPTION, e4);
                        return this.fail(OutputStatusCode.SERVER_FAILURE, OutputMsg.SERVER_EXCEPTION);
                    }
                }
                VerUpdateListPo updatePo = getUpdateListPo(updateListPo);
                try {
                    versionService.versionUpdateList(updatePo);
                } catch (Exception e3) {
                    logger.error("修改软件版本队列信息出错，队列id=" + updatePo.getId(), e3);
                    return this.fail(OutputStatusCode.SERVER_FAILURE, OutputMsg.SERVER_EXCEPTION);
                }
                downloadInfo.setUrl(path);
                downloadInfo.setVersionCode(updateListPo.getVersion());
                downloadInfo.setVersionName(updateListPo.getName());
                downloadInfo.setUpdateMessage(updateListPo.getDescription());
                VersionUpdateTools.minusTotal();
                return ok("下载成功", downloadInfo);
            }
            return this.fail(OutputStatusCode.SERVER_FAILURE, OutputMsg.NO_VERSION);
        } else if (list.size() > 1) {
            logger.error("设备表里同一设备唯一号存在多条记录，设备唯一号=" + cpu);
            return error("系统异常，请联系技术人员处理");
        } else {
            return error("该设备未启用或找不到该设备的注册信息");
        }
    }
}