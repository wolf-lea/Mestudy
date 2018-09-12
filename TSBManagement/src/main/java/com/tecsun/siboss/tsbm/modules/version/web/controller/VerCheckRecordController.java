package com.tecsun.siboss.tsbm.modules.version.web.controller;

import com.tecsun.siboss.tsbm.common.BaseController;
import com.tecsun.siboss.tsbm.common.bean.Page;
import com.tecsun.siboss.tsbm.common.bean.Result;
import com.tecsun.siboss.tsbm.common.util.CommUtil;
import com.tecsun.siboss.tsbm.common.util.Constants;
import com.tecsun.siboss.tsbm.common.util.GlobalResult;
import com.tecsun.siboss.tsbm.modules.version.entity.VerCheckRecordParam;
import com.tecsun.siboss.tsbm.modules.version.entity.VerCheckRecordVO;
import com.tecsun.siboss.tsbm.modules.version.service.VerCheckRecordServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by huanghailiang on 15-12-21.
 * 软件版本检验记录
 */
@Controller
@RequestMapping(value = "tsbm/verCheckRecord")
public class VerCheckRecordController extends BaseController {
    public static final Logger logger = Logger.getLogger(VerCheckRecordController.class);

    @Autowired
    public VerCheckRecordServiceImpl verCheckRecordService;

    /*查询*/
    @RequestMapping(value = "getListByGet", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    Result getDateListByGet(HttpServletRequest request) {
        logger.info("VerCheckRecordController查询getDateListByGet");
        String pageSize = StringUtils.defaultIfBlank(request.getParameter("pagesize"), "10");
        String pageNo = StringUtils.defaultIfBlank(request.getParameter("pageno"), "1");
        Page<VerCheckRecordVO> page = new Page<VerCheckRecordVO>(Integer.parseInt(pageSize), Integer.parseInt(pageNo));
        VerCheckRecordParam param = new VerCheckRecordParam();
        String needUpdate = request.getParameter("needUpdate");
        if (StringUtils.isNotBlank(needUpdate)) {
            param.setNeedUpdate(needUpdate);
        }
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
            page = verCheckRecordService.getList(page, param);
        } catch (Exception e) {
            logger.error("VerCheckRecordController查询getDateListByGet方法异常", e);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.QUERY_SUCCESS, page.getLists(), page.getRowCount());

    }

    /*根据ID查找校验记录*/
    @RequestMapping(value = "getRecordById/{id}", method = RequestMethod.GET, produces = "application/json")
    public
    @ResponseBody
    Result getRecordById(@PathVariable Long id) {
        logger.info("查找id=" + id + "的软件版本校验记录");
        VerCheckRecordVO vo = null;
        try {
            vo = verCheckRecordService.getRecord(id);
        } catch (Exception e) {
            logger.error("查找id=" + id + "的软件版本校验记录出错", e);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.QUERY_SUCCESS, vo);
    }

    /*根据ID删除校验记录*/
    @RequestMapping(value = "deleteRecord/{id}", method = RequestMethod.DELETE, produces = "application/json")
    public
    @ResponseBody
    Result deleteRecord(@PathVariable Long id) {
        logger.info("删除id=" + id + "的软件版本校验记录");
        try {
            verCheckRecordService.deleteRecord(id);
        } catch (Exception e) {
            logger.error("删除id=" + id + "的软件版本校验记录出错", e);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        return this.result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.OPERATE_SUCCESS);
    }
}
