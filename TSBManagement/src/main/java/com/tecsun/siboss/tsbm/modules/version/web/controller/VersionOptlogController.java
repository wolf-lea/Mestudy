package com.tecsun.siboss.tsbm.modules.version.web.controller;

import com.tecsun.siboss.tsbm.common.BaseController;
import com.tecsun.siboss.tsbm.common.bean.Page;
import com.tecsun.siboss.tsbm.common.bean.Result;
import com.tecsun.siboss.tsbm.common.util.CommUtil;
import com.tecsun.siboss.tsbm.common.util.Constants;
import com.tecsun.siboss.tsbm.common.util.GlobalResult;
import com.tecsun.siboss.tsbm.modules.version.entity.VersionOptlogParam;
import com.tecsun.siboss.tsbm.modules.version.entity.VersionOptlogVO;
import com.tecsun.siboss.tsbm.modules.version.entity.VersionVO;
import com.tecsun.siboss.tsbm.modules.version.service.VersionOptlogServiceImpl;
import com.tecsun.siboss.tsbm.modules.version.service.VersionServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Created by zhuxiaokai on 15-12-23.
 */
@Controller
@RequestMapping(value = "tsbm/versionOptlog")
public class VersionOptlogController extends BaseController{
    public final static Logger logger = Logger.getLogger(VersionController.class);

    @Autowired
    public VersionOptlogServiceImpl versionOptlogService;

    @Autowired
    public VersionServiceImpl versionService;

    /**
     *查询
     * @return
     */
    @RequestMapping(value = "/getListByGet", method = RequestMethod.GET , produces = "application/json")
    @ResponseBody
    public Object getDataListByGet(HttpServletRequest request) {
        logger.info("VersionOptlogController查询getDataListByGet");
        String pageSize = StringUtils.defaultIfBlank(request.getParameter("pagesize"), "10");
        String pageNo = StringUtils.defaultIfBlank(request.getParameter("pageno"), "1");
        Page<VersionOptlogVO> page = new Page<VersionOptlogVO>(Integer.parseInt(pageSize), Integer.parseInt(pageNo));

        VersionOptlogParam param = new VersionOptlogParam();
        String versionId = request.getParameter("versionId");
        if (!"".equals(versionId) && versionId != null) {
            param.setVersionId(Long.parseLong(versionId));
        }
        param.setOptType(request.getParameter("optType"));
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
            page = versionOptlogService.getList(page, param);
        } catch (Exception e) {
            logger.error("VersionOptlogController查询getDataListByGet方法异常", e);
            return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
        }
        for (VersionOptlogVO vo: page.getLists()) {
            VersionVO versionVO = null;
            try {
                versionVO = versionService.getVersion(vo.getVersionId());
            } catch (Exception e1) {
                logger.error("VersionOptlogController获取id=" + vo.getVersionId() + "的软件版本记录时出错", e1);
                return this.result(Constants.RESULT_MESSAGE_EXCEPTION, GlobalResult.OPERATE_FAILURE);
            }
            vo.setName(versionVO.getName());
            vo.setVersion(versionVO.getVersion());
        }
        Result result = new Result(Constants.RESULT_MESSAGE_SUCCESS, GlobalResult.QUERY_SUCCESS, page.getLists(), page.getRowCount());
        return result;

    }
}
