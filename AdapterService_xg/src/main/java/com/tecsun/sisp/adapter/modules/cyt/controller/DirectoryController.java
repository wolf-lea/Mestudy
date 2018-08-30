package com.tecsun.sisp.adapter.modules.cyt.controller;

import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.cyt.entity.request.DiseaseBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.request.PaidBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.DiseaseDirectoryBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.PaidDirectoryBean;
import com.tecsun.sisp.adapter.modules.cyt.service.impl.DiseaseServiceImpl;
import com.tecsun.sisp.adapter.modules.cyt.service.impl.PaidServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by xumaohao on 2017/8/3.
 */

@RestController
@RequestMapping("/adapter/cyt")
public class DirectoryController extends BaseController {

    Logger logger = Logger.getLogger(DoctorController.class);

    @Autowired
    DiseaseServiceImpl diseaseService;
    @Autowired
    PaidServiceImpl paidService;

    /**
     * 门诊疾病目录查询
     * @param bean
     * @return
     */
    @RequestMapping("/diseaseDirectory")
    public Result diseaseDirectory(@RequestBody DiseaseBean bean) {

        String check = checkdiseaseDirectory(bean);
        if(StringUtils.isNotEmpty(check)){
            return error(check);
        }
        try {
            List<DiseaseDirectoryBean> diseaseDirectory = diseaseService.DiseaseSelect4other(bean);
            return ok("查询成功",diseaseDirectory);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询失败：",e);
        }
        return error("查询失败");
    }

    /**
     * 门诊收费目录查询
     * @param bean
     * @return
     */
    @RequestMapping("/paidDirectory")
    public Result paidDirectory(@RequestBody PaidBean bean) {

        String check = checkPaidDirectory(bean);
        if(StringUtils.isNotEmpty(check)){
            return error(check);
        }
        try {
            Page<PaidDirectoryBean> PaidDirectory = paidService.PaidSelect4other(bean);
            return ok(PaidDirectory.getPagesize(),"查询成功",PaidDirectory);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询失败：",e);
        }
        return error("查询失败");
    }

    //检查疾病目录查询入参
    private String checkdiseaseDirectory(DiseaseBean bean) {
        if(bean == null)
            return "入参不能为空";
        if(StringUtils.isEmpty(bean.getChannelcode()))
            return "渠道编码channelcode不能为空";
//        if(StringUtils.isEmpty(bean.getDeviceid()))
//            return  "标识Id deviceid不能为空";
//        if(StringUtils.isEmpty(bean.getTokenid()))
//            return "权限校验码tokenid不能为空";

        return "";
    }

    //检查收费目录查询入参
    private String checkPaidDirectory(PaidBean bean) {
        if(bean == null)
            return "入参不能为空";
        if(StringUtils.isEmpty(bean.getChannelcode()))
            return "渠道编码channelcode不能为空";
//        if(StringUtils.isEmpty(bean.getDeviceid()))
//            return  "标识Id deviceid不能为空";
//        if(StringUtils.isEmpty(bean.getTokenid()))
//            return "权限校验码tokenid不能为空";

        return "";
    }
}
