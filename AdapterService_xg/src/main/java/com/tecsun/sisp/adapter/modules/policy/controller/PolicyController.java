package com.tecsun.sisp.adapter.modules.policy.controller;

import com.tecsun.sisp.adapter.common.util.*;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
import com.tecsun.sisp.adapter.modules.policy.entity.request.PolicyTitleBean;
import com.tecsun.sisp.adapter.modules.policy.entity.response.PolicyTitleVO;
import com.tecsun.sisp.adapter.modules.policy.entity.response.PolicyTypeVO;
import com.tecsun.sisp.adapter.modules.policy.entity.response.PolicyVO;
import com.tecsun.sisp.adapter.modules.policy.service.impl.TestPolicyServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**政策法规
 *
 * Created by danmeng on 2017/7/10.
 */
@Controller
@RequestMapping(value = "/adapter/policy")
public class PolicyController extends BaseController {

    public final static Logger logger = Logger.getLogger(PolicyController.class);

    @Autowired
    private TestPolicyServiceImpl policyService;
    /**
     * 获取政策法规类型
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getPolicyType", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getPolicyType(@RequestBody SecQueryBean bean) {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            List<PolicyTypeVO> voList = policyService.getPolicyType4Other();
            statusCode = Constants.RESULT_MESSAGE_SUCCESS;
            if (voList != null && voList.size() > 0) {
                message = "查询成功";
                //不分页
                Page<PolicyTypeVO> page = new Page(1, bean.getPagesize());
                page.setCount(voList.size());
                page.setPagesize(voList.size());
                page.setData(voList);
                return result(statusCode, message, page);
            }else message="暂无政策信息";
        } catch (Exception e) {
            logger.error("获取政策法规类型失败：", e);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
        }
        return this.result(statusCode, message);
    }
    
    /**
     * 获取政策法规列表
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getPolicyList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getPolicyList(@RequestBody PolicyTitleBean bean) {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        if (StringUtils.isBlank(bean.getPolicyType())) return result(Constants.RESULT_MESSAGE_PARAM, "请输入类型");
        try {
            List<PolicyTitleVO> list = policyService.getPolicyList4Other(bean);
            statusCode = Constants.RESULT_MESSAGE_SUCCESS;
            if (list != null && list.size() > 0) {
                message = "查询成功";
                //不分页
                Page<PolicyTitleVO> page = new Page(1, bean.getPagesize());
                page.setCount(list.size());
                page.setPagesize(list.size());
                page.setData(list);
                return result(statusCode, message,page);
            } else message = "暂无政策信息";
        } catch (Exception e) {
            logger.error("获取政策法规列表失败：", e);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
        }
        return result(statusCode, message);
    }

    /**
     * 获取政策内容
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getPolicyContext", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getPolicyContext(@RequestBody PolicyTitleBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getPolicyID())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入政策信息");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            PolicyVO vo = policyService.getPolicyContext4Other(bean);
            statusCode = Constants.RESULT_MESSAGE_SUCCESS;
            if (vo != null) {
                message = "查询成功";
                return result(statusCode, message, vo);
            } else message = "暂无政策信息";
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("获取政策内容异常：", e);
        }
        return result(statusCode, message);
    }
}
