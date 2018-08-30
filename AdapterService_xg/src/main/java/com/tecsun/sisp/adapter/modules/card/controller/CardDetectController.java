package com.tecsun.sisp.adapter.modules.card.controller;

import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.JsonHelper;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.card.entity.request.CardBasicBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.CardDetectBean;
import com.tecsun.sisp.adapter.modules.card.entity.response.CardBasicVO;
import com.tecsun.sisp.adapter.modules.card.service.impl.DetectServiceImpl;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.common.entity.response.ExtVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**卡检测服务
 * @ClassName: CardDetectController
 * Created by danmeng on 2017/8/9.
 */

@Controller
@RequestMapping(value = "/adapter/carddetect")
public class CardDetectController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(CardDetectController.class);

    @Autowired
    private DetectServiceImpl detectService;
    /**
     * 社保卡基本信息查询
     */
    @RequestMapping(value = "/getCardBasicInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardBasicInfo(@RequestBody CardBasicBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh()) || StringUtils.isBlank(bean.getXm())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "身份证号或姓名不能为空");
        }
        //按项目需求判断社保卡号是否允许为空
        /*if (StringUtils.isBlank(bean.getSbkh())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "社保卡号不能为空");
        }*/
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "社保卡基本信息查询失败";
        try {
            //从持卡库获取数据
            //正式项目删除,并调用持卡库获取start
            CardBasicVO vo = detectService.testGetCardBasicInfo4Other(bean);
            statusCode = Constants.RESULT_MESSAGE_SUCCESS;
            if (vo == null) message = "没有此用户信息";
            else message = "社保卡基本信息查询成功";
            return this.result(statusCode, message, vo);
            //正式项目删除end
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "查询失败";
            logger.error("社保卡基本信息查询失败:" + JsonHelper.javaBeanToJson(bean), e);
        }
        return this.result(statusCode, message);
    }

    /**
     * 社保卡检测结果保存
     */
    @RequestMapping(value = "/saveCardDetectInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result saveCardDetectInfo(@RequestBody CardDetectBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh()) || StringUtils.isBlank(bean.getXm()) || StringUtils.isBlank(bean.getDetectResult())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "信息不全");
        }
        //按项目需求判断社保卡号是否允许为空
        /*if (StringUtils.isBlank(bean.getSbkh())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "社保卡号不能为空");
        }*/
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "保存失败";
        long detectId=0;
        try {
            detectId=detectService.saveCardDect4Cssp(bean);
            Map map = new HashMap();
            if (detectId > 0) {
                map.put("detectId",detectId);
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "保存成功";
                return this.result(statusCode, message,map);
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "保存失败";
            logger.error("社保卡检测结果保存失败:" + JsonHelper.javaBeanToJson(bean), e);
        }
        return this.result(statusCode, message);
    }

    /**
     * 社保卡修复结果保存
     */
    @RequestMapping(value = "/saveCardRepairInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result saveCardRepairInfo(@RequestBody CardDetectBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh()) || StringUtils.isBlank(bean.getXm())
                || StringUtils.isBlank(bean.getRepairResult()) || bean.getDetectId() == 0) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "信息不全");
        }
        //按项目需求判断社保卡号是否允许为空
        /*if (StringUtils.isBlank(bean.getSbkh())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "社保卡号不能为空");
        }*/
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "保存失败";
        try {
            long status = detectService.saveCardRepair4Cssp(bean);
            if (status == 1) {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "保存成功";
            } else if (status > 1||status==0) {
                message = "请重新检测";
                statusCode = Constants.RESULT_MESSAGE_EXCEPTION;

            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "保存失败";
            logger.error("社保卡修复结果保存失败:" + JsonHelper.javaBeanToJson(bean), e);
        }
        return this.result(statusCode, message);
    }

    /**
     * 社保卡鉴权
     */
    @RequestMapping(value = "/getCardAuth", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardAuth(@RequestBody CardBasicBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh()) || StringUtils.isBlank(bean.getXm())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "身份证号或姓名不能为空");
        }
        //按项目需求判断社保卡号是否允许为空
        /*if (StringUtils.isBlank(bean.getSbkh())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "社保卡号不能为空");
        }*/
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "鉴权失败";
        ExtVO vo = new ExtVO();
        try {
            statusCode = Constants.RESULT_MESSAGE_SUCCESS;
            message = "鉴权成功";
            return this.result(statusCode, message, vo);
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "鉴权失败";
            logger.error("社保卡鉴权失败:" + JsonHelper.javaBeanToJson(bean), e);
        }
        return this.result(statusCode, message);
    }


}