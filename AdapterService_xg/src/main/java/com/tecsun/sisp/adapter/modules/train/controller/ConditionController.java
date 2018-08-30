package com.tecsun.sisp.adapter.modules.train.controller;

import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.train.entity.request.ApplyBean;
import com.tecsun.sisp.adapter.modules.train.service.impl.ConditionServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by xumaohao on 2017/9/6.
 */
@RestController
@RequestMapping("/adapter/train")
public class ConditionController extends BaseController {

    Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    ConditionServiceImpl conditionService;

    /**
     * 就业培训报名筛选条件
     * @param bean
     * @return
     */
    @RequestMapping("/selectCondition")
    public Result selectCondition(@RequestBody ApplyBean bean) {
        try {
            //入参校验
//            String err = checkApply(bean);
//            if(StringUtils.isNotEmpty(err))
//                return error(err);

            //新增报名记录
            Object obj = conditionService.seleceCondition4cssp();
                return ok("获取筛选条件成功",obj);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取筛选条件失败"+e);
        }
        return error("获取筛选条件失败");
    }

}
