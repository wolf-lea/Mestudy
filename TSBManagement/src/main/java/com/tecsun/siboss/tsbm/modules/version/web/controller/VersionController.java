package com.tecsun.siboss.tsbm.modules.version.web.controller;

import com.tecsun.siboss.tsbm.common.BaseController;
import com.tecsun.siboss.tsbm.common.bean.Page;
import com.tecsun.siboss.tsbm.common.bean.Result;
import com.tecsun.siboss.tsbm.common.util.*;
import com.tecsun.siboss.tsbm.modules.version.entity.*;
import com.tecsun.siboss.tsbm.modules.version.service.VersionOptlogServiceImpl;
import com.tecsun.siboss.tsbm.modules.version.service.VersionServiceImpl;
import com.tecsun.siboss.tsbm.modules.version.service.VersionUpdateListServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by zhuxiaokai on 15-12-18.
 */
@Controller
@RequestMapping(value = "tsbm/version")
public class VersionController extends BaseController {
    public final static Logger logger = Logger.getLogger(VersionController.class);

    @Autowired
    public VersionServiceImpl versionService;

    @Autowired
    public VersionOptlogServiceImpl versionOptlogService;

    @Autowired
    public VersionUpdateListServiceImpl versionUpdateListService;

    /**
     * 查询
     *
     * @param param
     * @return
     */
    @RequestMapping(value = "/getListByPost", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object getDataListByPost(@RequestBody VersionParam param) {
        logger.info("VersionController查询getDataListByPost");
        List<VersionVO> list = null;
        try {
            list = versionService.getList(param);
        } catch (Exception e) {
            logger.error("VersionController查询getDataListByPost方法异常", e);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.QUERY_SUCCESS, list);
    }

    /**
     * 查询全部软件版本
     *
     * @return
     */
    @RequestMapping(value = "/getAllList", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object getAllList(HttpServletRequest request) {
        logger.info("VersionController查询全部软件版本getAllList");
        String pageSize = StringUtils.defaultIfBlank(request.getParameter("pagesize"), "10");
        String pageNo = StringUtils.defaultIfBlank(request.getParameter("pageno"), "1");
        Page<VersionVO> page = new Page<VersionVO>(Integer.parseInt(pageSize), Integer.parseInt(pageNo));

        VersionParam param = new VersionParam();
        param.setName(request.getParameter("name"));
        param.setVersion(request.getParameter("version"));
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        try {
            if (StringUtils.isNotBlank(beginTime)) {
                Date beginTimeDate = CommUtil.parseDate(beginTime);
                param.setBeginTime(beginTimeDate);
            }
            if (StringUtils.isNotBlank(endTime)) {
                Date endTimeDate = CommUtil.parseDate(endTime);
                param.setEndTime(endTimeDate);
            }
            page = versionService.getAllDataList(page, param);
        } catch (Exception e) {
            logger.error("VersionController查询全部软件版本getAllList方法异常", e);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        Result result = new Result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.QUERY_SUCCESS, page.getLists(), page.getRowCount());
        return result;
    }

    /**
     * 查询
     *
     * @return
     */
    @RequestMapping(value = "/getListByGet", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object getDataListByGet(HttpServletRequest request) {
        logger.info("VersionController查询getDataListByGet");
        String pageSize = StringUtils.defaultIfBlank(request.getParameter("pagesize"), "10");
        String pageNo = StringUtils.defaultIfBlank(request.getParameter("pageno"), "1");
        Page<VersionVO> page = new Page<VersionVO>(Integer.parseInt(pageSize), Integer.parseInt(pageNo));

        VersionParam param = new VersionParam();
        param.setName(request.getParameter("name"));
        param.setVersion(request.getParameter("version"));
        param.setAreaIds(request.getParameter("areaIds"));
        param.setDeviceType(request.getParameter("deviceType"));
        param.setAppType(request.getParameter("appType"));
        param.setDeviceIds(request.getParameter("deviceIds"));
        param.setStatus(request.getParameter("status"));
        param.setCreateUser(request.getParameter("createUser"));
        param.setAuditUser(request.getParameter("auditUser"));
        param.setModUser(request.getParameter("modUser"));
        param.setIsDelete(request.getParameter("isDelete"));
        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        try {
            if (StringUtils.isNotBlank(beginTime)) {
                Date beginTimeDate = CommUtil.parseDate(beginTime);
                param.setBeginTime(beginTimeDate);
            }
            if (StringUtils.isNotBlank(endTime)) {
                Date endTimeDate = CommUtil.parseDate(endTime);
                param.setEndTime(endTimeDate);
            }
            page = versionService.getList(page, param);
        } catch (Exception e) {
            logger.error("VersionController查询getDataListByGet方法异常", e);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        Result result = new Result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.QUERY_SUCCESS, page.getLists(), page.getRowCount());
        return result;
    }

    /**
     * 获取单条记录
     * getVersionDetail
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getVersionDetail/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object getVersionDetail(@PathVariable Long id) {
        logger.info("获取id=" + id + "的软件版本详细信息");
        VersionVO versionVO = null;
        try {
            versionVO = versionService.getVersion(id);
        } catch (Exception e) {
            logger.error("获取id=" + id + "的软件版本详细信息异常", e);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.QUERY_SUCCESS, versionVO);
    }

    /**
     * 查询软件版本更新状态以判断是否允许进行修改版本操作
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "getUpdate/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Object getUpdate(@PathVariable Long id) {
        logger.info("查询id=" + id + "的软件版本更新状态以判断是否允许进行修改版本操作");
        try {
            VersionVO versionVO = versionService.getVersion(id);
            //比较当前时间与计划更新时间
            if (versionVO.getProcessTime() != null) {
                Date now = new Date();
                int c = now.compareTo(versionVO.getProcessTime());
                if (c >= 1) {
                    return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.QUERY_SUCCESS, "error");
                }
            }
            //查找更新队列表内是否有该软件版本的更新记录以确定是否允许修改软件版本内容
            List<VersionUpdateListVO> list = versionUpdateListService.getByVerId(versionVO.getId());
            if (list.size() == 0) {
                return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.QUERY_SUCCESS, versionVO);
            } else {
                return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.QUERY_SUCCESS, "error");
            }
        } catch (Exception e) {
            logger.error("查询id=" + id + "的软件版本更新状态异常", e);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
    }

    /**
     * 新增
     *
     * @param
     * @return
     */
    @RequestMapping(value = "addVersion", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object addVersion(@RequestBody VersionVO vo) {
        logger.info("新增一个软件版本");
        long id = 0;
        try {
            id = versionService.getSequence();
        } catch (Exception e) {
            logger.error("获取软件版本表序列错误", e);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        Map map = new HashMap();
        map.put("id", id);
        map.put("name", vo.getName());
        map.put("preVersion", vo.getPreVersion());
        map.put("version", vo.getVersion());
        map.put("url", vo.getUrl());
        map.put("description", vo.getDescription());
        map.put("areaIds", vo.getAreaIds());
        map.put("deviceType", vo.getDeviceType());
        map.put("appType", vo.getAppType());
        map.put("deviceIds", vo.getDeviceIds());
        map.put("status", vo.getStatus());
        map.put("createUser", vo.getCreateUser());
        map.put("processTime", vo.getProcessTime());
        VersionVO checkVer = versionService.getByVersion(vo.getVersion(), vo.getAppType());
        if (checkVer != null) {
            return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.OPERATE_FAILURE, "当前APP类型的版本号已存在，请修改");
        }
        int result;
        try {
            result = versionService.addVersion(map);
        } catch (Exception e1) {
            logger.error("新增软件版本错误", e1);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        if (result == 1) {
            //记录操作日志
            Map optMap = new HashMap();
            optMap.put("versionId", id);
            optMap.put("optType", "ADD");
            optMap.put("optUser", vo.getCreateUser());
            optMap.put("optionDescription", "新增了一个软件版本");
            try {
                versionOptlogService.addVersionOptlog(optMap);
            } catch (Exception e2) {
                logger.error("新增软件版本日志错误", e2);
                return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
            }
            return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.QUERY_SUCCESS, "success");
        }
        return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.OPERATE_FAILURE, "error");
    }

    /**
     * 修改
     *
     * @return
     */
    @RequestMapping(value = "updateVersion", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Object updateVersion(@RequestBody VersionVO vo) {
        logger.info("修改id=" + vo.getId() + "的软件版本");
        try {
            List<VersionUpdateListVO> list = versionUpdateListService.getByVerId(vo.getId());
            if (list.size() > 0) {
                return this.result(Constants.RESULT_MESSAGE_SUCCESS, "wrong", "error");
            }
        } catch (Exception e) {
            logger.error("查找id=" + vo.getId() + "的软件版本更新记录出错", e);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        VersionVO versionVO = null;
        try {
            versionVO = versionService.getVersion(vo.getId());
        } catch (Exception e1) {
            logger.error("查找id=" + vo.getId() + "的软件版本记录出错", e1);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        Map map = new HashMap();
        map.put("name", vo.getName());
        map.put("preVersion", vo.getPreVersion());
        map.put("version", vo.getVersion());
        map.put("url", vo.getUrl());
        map.put("description", vo.getDescription());
        map.put("areaIds", vo.getAreaIds());
        map.put("deviceIds", vo.getDeviceIds());
        map.put("processTime", vo.getProcessTime());
        map.put("id", vo.getId());
        map.put("modUser", vo.getModUser());
        if (!versionVO.getVersion().equals(vo.getVersion())) {
            VersionVO checkVer = versionService.getByVersion(vo.getVersion(), versionVO.getAppType());
            if (checkVer != null) {
                return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.OPERATE_FAILURE, "当前APP类型的版本号已存在，请修改");
            }
        }
        int result;
        try {
            result = versionService.updateVersion(map);
        } catch (Exception e2) {
            logger.error("修改id=" + vo.getId() + "的软件版本记录出错", e2);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        if (result == 1) {
            //记录操作日志
            StringBuffer log = new StringBuffer("");
            if (hasChange(versionVO.getName(), vo.getName())) {
                log.append("版本名称由：“" + versionVO.getName() + "” 修改为：“" + vo.getName() + "”；");
            }
            if (hasChange(versionVO.getPreVersion(), vo.getPreVersion())) {
                log.append("上一版本编号由：“" + versionVO.getPreVersion() + "” 修改为：“" + vo.getPreVersion() + "”；");
            }
            if (hasChange(versionVO.getVersion(), vo.getVersion())) {
                log.append("当前版本编号由：“" + versionVO.getVersion() + "” 修改为：“" + vo.getVersion() + "”；");
            }
            if (hasChange(versionVO.getUrl(), vo.getUrl())) {
                log.append("版本文件url由：“" + versionVO.getUrl() + "” 修改为：“" + vo.getUrl() + "”；");
            }
            if (hasChange(versionVO.getDescription(), vo.getDescription())) {
                log.append("版本说明由：“" + versionVO.getDescription() + "” 修改为：“" + vo.getDescription() + "”；");
            }
            if (hasChange(versionVO.getAreaIds(), vo.getAreaIds())) {
                log.append("目标更新区域由：“" + versionVO.getAreaIds() + "” 修改为：“" + vo.getAreaIds() + "”；");
            }
//            if (hasChange(versionVO.getDeviceType(), vo.getDeviceType())) {
//                log.append("终端类型由：“" + versionVO.getDeviceType() + "” 修改为：“" + vo.getDeviceType() + "”；");
//            }
            if (hasChange(versionVO.getDeviceIds(), vo.getDeviceIds())) {
                log.append("设备终端编号由：“" + versionVO.getDeviceIds() + "” 修改为：“" + vo.getDeviceIds() + "”；");
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            if (versionVO.getProcessTime() == null) {
                if (vo.getProcessTime() != null) {
                    log.append("计划更新时间由：“”修改为：“" + sdf.format(vo.getProcessTime()) + "”；");
                }
            } else {
                if (vo.getProcessTime() == null) {
                    log.append("计划更新时间由：“" + sdf.format(versionVO.getProcessTime()) + "” 修改为：“”；");
                } else {
                    if (hasChange(sdf.format(versionVO.getProcessTime()), sdf.format(vo.getProcessTime()))) {
                        log.append("计划更新时间由：“" + sdf.format(versionVO.getProcessTime()) + "” 修改为：“" + sdf.format(vo.getProcessTime()) + "”；");
                    }
                }
            }
            if (StringUtils.isNotEmpty(log.toString())) {
                Map optMap = new HashMap();
                optMap.put("versionId", versionVO.getId());
                optMap.put("optType", "MOD");
                optMap.put("optUser", vo.getModUser());
                optMap.put("optionDescription", log.toString());
                try {
                    versionOptlogService.addVersionOptlog(optMap);
                } catch (Exception e3) {
                    logger.error("修改软件版本日志错误", e3);
                    return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
                }
            }
            return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.QUERY_SUCCESS, "success");
        }
        return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.OPERATE_FAILURE, "error");
    }

    private boolean hasChange(String a, String b) {
        if (StringUtils.isEmpty(a)) {
            if (StringUtils.isEmpty(b)) {
                return false;
            } else {
                return true;
            }
        } else {
            if (StringUtils.isEmpty(b)) {
                return true;
            } else {
                if (a.equals(b)) {
                    return false;
                } else {
                    return true;
                }
            }
        }
    }

    /**
     * 删除
     */
    @RequestMapping(value = "deleteVersion/{id}/{user}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public Object deleteVersion(@PathVariable Long id, @PathVariable String user) {
        logger.info("删除id=" + id + "的软件");
        try {
            versionService.deleteVersion(id);
        } catch (Exception e) {
            logger.error("删除id=" + id + "的软件版本出错", e);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        //记录操作日志
        Map map = new HashMap();
        map.put("versionId", id);
        map.put("optType", "DEL");
        map.put("optUser", user);
        map.put("optionDescription", "更改状态为已删除");
        try {
            versionOptlogService.addVersionOptlog(map);
        } catch (Exception e1) {
            logger.error("删除软件版本日志错误", e1);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.OPERATE_SUCCESS);
    }

    /**
     * 版本文件上传
     */
    @RequestMapping(value = "upload")
    @ResponseBody
    public Object uploadApk(@RequestParam(value = "file") MultipartFile file, HttpServletRequest request) {
        logger.info("保存上传的软件版本文件");
        String fileName = file.getOriginalFilename();
        //文件名
        String name = fileName.substring(0, fileName.lastIndexOf("."));
        //文件后缀
        String fileExt = fileName.substring((fileName.lastIndexOf(".") + 1)).toLowerCase();
        //增加上传时间区分相同的文件名
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        String newFileName = name + "_" + df.format(new Date()) + "." + fileExt;

        //读取配置文件
        Config config = Config.getInstance();
        String tsbm = config.get("upload_file_path");

        //上传文件保存路径
        StringBuffer savePath = new StringBuffer();
        savePath.append(tsbm + File.separator + "version");
        File dirFile = new File(savePath.toString());
        //文件夹不存在则创建
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }
        try {
            //复制文件到目标地址
            File uploadedFile = new File(savePath.toString(), newFileName);
            FileCopyUtils.copy(file.getBytes(), uploadedFile);
            String url = config.get("upload_file_version") + newFileName;
            return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.OPERATE_SUCCESS, url);
        } catch (Exception e) {
            logger.error("保存上传的软件版本文件出错", e);
            return this.result(Constants.RESULT_MESSAGE_ERROR, GlobalResult.OPERATE_FAILURE, "error");
        }
//        BufferedReader bufferedReader = null;
//        try {
//            CommonsMultipartFile cf = (CommonsMultipartFile)file;
//            DiskFileItem di = (DiskFileItem)cf.getFileItem();
//            File f = di.getStoreLocation();
//            bufferedReader = new BufferedReader(new FileReader(f));
//            StringBuffer sb = new StringBuffer();
//            while (bufferedReader.readLine() != null) {
//                sb.append(bufferedReader.readLine());
//            }
//            JedisUtil.setValue(newFileName, sb.toString());
//            return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.OPERATE_SUCCESS, newFileName);
//        } catch (Exception e) {
//            e.printStackTrace();
//            logger.error(e);
//            return this.result(Constants.RESULT_MESSAGE_ERROR, "文件上传失败");
//        } finally {
//            try {
//                if (bufferedReader != null) {
//                    bufferedReader.close();
//                }
//            } catch (Exception e1) {
//                e1.printStackTrace();
//            }
//        }
    }
}
