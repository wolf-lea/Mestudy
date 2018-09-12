package com.tecsun.siboss.tsbm.modules.version.web.controller;

import com.tecsun.siboss.tsbm.common.BaseController;
import com.tecsun.siboss.tsbm.common.bean.Page;
import com.tecsun.siboss.tsbm.common.bean.Result;
import com.tecsun.siboss.tsbm.common.util.CommUtil;
import com.tecsun.siboss.tsbm.common.util.Constants;
import com.tecsun.siboss.tsbm.common.util.GlobalResult;
import com.tecsun.siboss.tsbm.modules.version.entity.VersionUpdateListParam;
import com.tecsun.siboss.tsbm.modules.version.entity.VersionUpdateListVO;
import com.tecsun.siboss.tsbm.modules.version.entity.VersionVO;
import com.tecsun.siboss.tsbm.modules.version.service.VersionServiceImpl;
import com.tecsun.siboss.tsbm.modules.version.service.VersionUpdateListServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by huanghailiang on 15-12-17.
 * 软件版本更新队列
 */
@Controller
@RequestMapping(value = "tsbm/versionUpdateList")
public class VersionUpdateListController extends BaseController {
    public final static Logger logger = Logger.getLogger(VersionUpdateListController.class);

    @Autowired
    public VersionUpdateListServiceImpl updateListService;

    @Autowired
    public VersionServiceImpl versionService;


    /**
     *查询
     * @return
     */
    @RequestMapping(value = "/getListByGet", method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public Result getDateListByGet(HttpServletRequest request) {
        logger.info("VersionUpdateListController查询getDateListByGet");
        String pageSize = StringUtils.defaultIfBlank(request.getParameter("pagesize"), "10");
        String pageNo = StringUtils.defaultIfBlank(request.getParameter("pageno"), "1");
        Page<VersionUpdateListVO> page = new Page<VersionUpdateListVO>(Integer.parseInt(pageSize), Integer.parseInt(pageNo));
        VersionUpdateListParam param = new VersionUpdateListParam();
        String status = request.getParameter("status");
        String areaId = request.getParameter("query_areaId_hidden");
        String version = request.getParameter("version");
        String name = request.getParameter("name");

        if(StringUtils.isNotBlank(version)){
            param.setVersion(version);
        }

        String beginTime = request.getParameter("beginTime");
        String endTime = request.getParameter("endTime");
        if(StringUtils.isNotBlank(status)){
           param.setStatus(status);
        }
        if(StringUtils.isNotBlank(name)){
            param.setName(name);
        }
        if(StringUtils.isNotBlank(areaId) && StringUtils.isNumeric(areaId)){
            param.setAreaId(Long.parseLong(areaId));
        }
        try {
            if (StringUtils.isNotBlank(beginTime) ) {
                Date beginTimeDate = CommUtil.parseDate(beginTime);
                param.setBeginTime(beginTimeDate);
            }
            if (StringUtils.isNotBlank(endTime)) {
                Date endTimeDate = CommUtil.parseDate(endTime);
                param.setEndTime(endTimeDate);
            }
            page = updateListService.getList(page, param);
        } catch (Exception e) {
            logger.error("VersionUpdateListController查询getDateListByGet方法异常", e);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.QUERY_SUCCESS, page.getLists(), page.getRowCount());

    }

    @RequestMapping(value = "/queryVersion", method = RequestMethod.GET , produces = "application/json")
    public @ResponseBody Result queryVersion(HttpServletRequest request){
        logger.info("VersionUpdateListController查询queryVersion");
        String pageSize = StringUtils.defaultIfBlank(request.getParameter("pagesize"), "10");
        String pageNo = StringUtils.defaultIfBlank(request.getParameter("pageno"), "1");
        Page<VersionVO> page = new Page<VersionVO>(Integer.parseInt(pageSize), Integer.parseInt(pageNo));
        String deviceType = request.getParameter("deviceType");
        String name = request.getParameter("version_name");
        String version = request.getParameter("version_no");
        try {
            page = versionService.queryAllVersion(page, deviceType, name, version);
        } catch (Exception e) {
            logger.error("VersionUpdateListController查询queryVersion方法异常", e);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.QUERY_SUCCESS, page.getLists(), page.getRowCount());
    }

    /**获取单条记录
     * getDemoDetail
     * @return
     */
    @RequestMapping(value = "getUpdateListDetail/{id}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Result getUpdateListDetail(@PathVariable Long id) {
        logger.info("获取id=" + id + "的更新队列记录");
        VersionUpdateListVO updateListVO;
        try {
            updateListVO = updateListService.getUpdateListVO(id);
        } catch (Exception e) {
            logger.error("获取id=" + id + "的更新队列记录异常", e);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.QUERY_SUCCESS, updateListVO);
    }

    /**
     * 新增
     * @param
     * @return
     */
    @RequestMapping(value = "addVerUpdateList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result addVerUpdateList(@RequestBody VersionUpdateListVO vo) {
        logger.info("手动新增软件版本更新队列记录");
        Map map = new HashMap();
        map.put("deviceId", vo.getDeviceNo());
        map.put("versionId", vo.getVersionId());
        map.put("status", vo.getStatus());
        map.put("createTime", new Date());
        map.put("createUser", vo.getCreateUser());
        map.put("updateType", vo.getUpdateType());
        map.put("pushNum", 0);
        map.put("remark", vo.getRemark());
        VersionUpdateListVO updateListVO = null;
        try {
            updateListVO = updateListService.check(vo.getDeviceNo(), String.valueOf(vo.getVersionId()));
        } catch (Exception e) {
            logger.error("检测更新队列是否存在相同记录时异常", e);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        int result = -1;
        if(updateListVO == null){
            try {
                result = updateListService.addVerUpdateList(map);
            } catch (Exception e1) {
                logger.error("手动新增软件版本更新队列记录时异常", e1);
                return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
            }
        }
        if (result == 1) {
            return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.OPERATE_SUCCESS, "success");
        }
        return this.result(Constants.RESULT_MESSAGE_ERROR, GlobalResult.IS_HAS, "error");
    }

    /**
     * 修改
     * @param
     * @return
     */
    @RequestMapping(value = "modifyVerUpdateList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result modifyVerUpdateList(@RequestBody VersionUpdateListVO vo) {
        logger.info("修改id=" + vo.getId() + "的软件版本更新队列记录");
        VersionUpdateListVO originalVo = null;
        try {
            originalVo = updateListService.getUpdateListVO(vo.getId());
        } catch (Exception e) {
            logger.error("获取id=" + vo.getId() + "的软件版本更新队列记录出错", e);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        VersionVO versionVO = null;
        try {
            versionVO = versionService.getVersion(originalVo.getVersionId());
        } catch (Exception e1) {
            logger.error("获取id=" + originalVo.getVersionId() + "的软件版本记录出错", e1);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        String originalDeviceNo = originalVo.getDeviceNo();
        String originalVersion = versionVO.getVersion();
        VersionUpdateListVO updateListVO = null;
        if(!vo.getDeviceNo().equals(originalDeviceNo) || !vo.getVersion().equals(originalVersion)){
            try {
                updateListVO = updateListService.check(vo.getDeviceNo(),String.valueOf(vo.getVersionId()));
            } catch (Exception e2) {
                logger.error("获取软件版本id=" + vo.getVersionId() + "，设备id=" + vo.getDeviceNo() + "的更新队列记录出错", e2);
                return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
            }
        }
        int result = -1;
        if(updateListVO == null){
            try {
                result = updateListService.modifyVerUpdateList(vo);
            } catch (Exception e3) {
                logger.error("获取id=" + vo.getId() + "的软件版本更新队列记录出错", e3);
                return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
            }
        }else {
            return this.result(Constants.RESULT_MESSAGE_ERROR, GlobalResult.IS_HAS, "error");
        }
        if (result == 1) {
            return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.OPERATE_SUCCESS, "success");
        }
        return this.result(Constants.RESULT_MESSAGE_ERROR, GlobalResult.OPERATE_FAILURE, "error");
    }


    /**
     * 删除
     */
    @RequestMapping(value = "deleteUpdateList/{id}", method = RequestMethod.DELETE, produces = "application/json")
    @ResponseBody
    public Result deleteUpdateList(@PathVariable int id) {
        logger.info("删除id=" + id + "的软件版本更新队列记录");
        try {
            updateListService.deleteVerUpdateList(id);
        } catch (Exception e) {
            logger.error("删除id=" + id + "的软件版本更新队列记录出错", e);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.OPERATE_SUCCESS);
    }

}
