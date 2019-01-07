package com.tecsun.sisp.adapter.modules.evaluate.controller;

import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.evaluate.entity.request.ContentBean;
import com.tecsun.sisp.adapter.modules.evaluate.entity.request.EvaluateBean;
import com.tecsun.sisp.adapter.modules.evaluate.entity.response.ContentVo;
import com.tecsun.sisp.adapter.modules.evaluate.entity.response.EvaluateVo;
import com.tecsun.sisp.adapter.modules.evaluate.service.impl.ContentServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xumaohao on 2017/10/18.
 */
@RestController
@RequestMapping("/adapter/evaluate")
public class EvaluateController extends BaseController{

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private ContentServiceImpl contentService;

    /**
     * 获取评价内容
     * @param bean
     * @return
     */
    @RequestMapping(value = "/selectContent",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result selectContent(@RequestBody ContentBean bean) {
        //入参校验
        if(StringUtils.isEmpty(bean.getServiceCode())) return error("业务编码serviceCode不能为空");
        try {
            List<ContentVo> content = contentService.selectContent4cssp(bean);
            if(content == null || content.size() == 0){
                return ok("获取评价内容成功",null);
            }
            return ok("获取评价内容成功", content);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取评价内容：" + e);
        }
        return error("获取评价内容失败");
    }

    /**
     * 插入评价结果
     * @param bean
     * @return
     */
    @RequestMapping(value = "/insertEvaluate",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result insertEvaluate(@RequestBody EvaluateBean bean) {
        //入参校验
        if (StringUtils.isEmpty(bean.getEvaluateService())) return this.result(Constants.RESULT_MESSAGE_PARAM, "业务类型evaluateService不能为空");
        if (StringUtils.isEmpty(bean.getEvaluateXm())) return this.result(Constants.RESULT_MESSAGE_PARAM, "姓名不能为空");
        if (StringUtils.isEmpty(bean.getEvaluateSfzh())) return this.result(Constants.RESULT_MESSAGE_PARAM, "身份证号码不能为空");
        try {
            String count = contentService.insertEvaluate4cssp(bean);
            if (count != null && count != "") {
                Map<String, String> map = new HashMap<>();
                map.put("serviceNumber", count);
                return ok("记录成功！",map);
            }
            return ok("评价成功！");

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("服务评价：" + e);
        }
        return error("评价失败！");
    }

    /**
     * 查询评价结果
     * @param bean
     * @return
     */
    @RequestMapping(value = "/selectEvaluate",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result selectEvaluate(@RequestBody EvaluateBean bean) {
        //入参校验
        if (StringUtils.isEmpty(bean.getServiceNumber())) return error("业务编号serviceNumber不能为空");
        if (StringUtils.isEmpty(bean.getEvaluateService())) return error("业务类型evaluateService不能为空");
        try {
            EvaluateVo evaluateVo = contentService.selectEvaluate4cssp(bean);
            return ok("查询成功！", evaluateVo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询评价结果：" + e);
        }
        return error("查询失败！");
    }
}
