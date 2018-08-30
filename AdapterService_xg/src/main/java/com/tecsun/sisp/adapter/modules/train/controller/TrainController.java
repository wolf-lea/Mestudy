package com.tecsun.sisp.adapter.modules.train.controller;

import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.train.entity.request.ApplyBean;
import com.tecsun.sisp.adapter.modules.train.entity.request.MessageSelectBean;
import com.tecsun.sisp.adapter.modules.train.entity.response.TrainDetailBean;
import com.tecsun.sisp.adapter.modules.train.entity.response.TrainMessageBean;
import com.tecsun.sisp.adapter.modules.train.service.impl.TestTrainServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xumaohao on 2017/8/29.
 */
@RestController
@RequestMapping("/adapter/train")
public class TrainController extends BaseController {

    @Autowired
    TestTrainServiceImpl testTrainService;

    Logger logger = Logger.getLogger(TrainController.class);

    /**
     * 获取培训信息
     * @param bean
     * @return
     */
    @RequestMapping("selectMessageList")
    public Result selectMessageList(@RequestBody MessageSelectBean bean){
        try {
            //入参校验
            String err = checkSelect(bean);
            if(StringUtils.isNotEmpty(err))
                return error(err);
            //获取培训信息
            Page<TrainMessageBean> trainMessage = testTrainService.selectMessageList4Other(bean);
            return ok("获取培训信息成功", trainMessage);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取培训信息失败",e);
        }

        return error("获取培训信息失败");
    }

    /**
     * 获取培训信息详情
     * @param bean
     * @return
     */
    @RequestMapping("selectMessageDetail")
    public Result selectMessageDetail(@RequestBody ApplyBean bean){
        try {
            //入参校验
            String err = checkSelectDetail(bean);
            if(StringUtils.isNotEmpty(err))
                return error(err);
            //获取培训信息详情
            TrainDetailBean train = testTrainService.selectMessageDetail4Other(bean);
            return ok("查询成功", train);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取培训信息详情失败:",e);
        }
        return error("查询失败");
    }

    /**
     * 培训信息入参校验
     * @param bean
     * @return
     * @throws Exception
     */
    private String checkSelect(MessageSelectBean bean) throws Exception{
        if(bean == null)
            return "入参不能为空";
        if(StringUtils.isEmpty(bean.getChannelcode()))
            return "渠道编码不能为空";
//        if(StringUtils.isEmpty(bean.getXm()))
//            return "姓名不能为空";
//        if(StringUtils.isEmpty(bean.getSfzh()))
//            return "身份证号码不能为空";
        return "";
    }

    /**
     * 培训详情入参校验
     * @param bean
     * @return
     * @throws Exception
     */
    private String checkSelectDetail(ApplyBean bean) throws Exception{
        if(bean == null)
            return "入参不能为空";
        if(StringUtils.isEmpty(bean.getChannelcode()))
            return "渠道编码不能为空";
//        if(StringUtils.isEmpty(bean.getXm()))
//            return "姓名不能为空";
//        if(StringUtils.isEmpty(bean.getSfzh()))
//            return "身份证号码不能为空";
        return "";
    }
}
