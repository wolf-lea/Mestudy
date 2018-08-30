package com.tecsun.sisp.adapter.modules.cyt.controller;

import com.tecsun.sisp.adapter.common.util.ParamUtil;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.DoctorBean;
import com.tecsun.sisp.adapter.modules.cyt.service.impl.PersonServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by xumaohao on 2017/7/28.
 */
@RestController
@RequestMapping("/adapter/cyt")
public class DoctorController extends BaseController {

    Logger logger = Logger.getLogger(DoctorController.class);

    @Autowired
    PersonServiceImpl personService;

    /**
     * 登录
     * @param bean
     * @return
     */
    @RequestMapping("/doctorLogin")
    public Result doctorLogin(@RequestBody Map<String,Object> bean){
        String check = ParamUtil.checkNullOrEmplty(bean, "zh", "mm");
        if(StringUtils.isNotEmpty(check)) {
            return error(check);
        }
        try {
            DoctorBean doctor = personService.DoctorSelect4other(bean);
            if (doctor == null) {
                return error("登录失败：查无此账户信息");
            }
            if (doctor != null) {
                if(bean.get("mm").equals(doctor.getMm())){
                    return ok("登录成功", doctor);
                }else {
                    return error("登录失败：密码错误");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("登录失败：" + e);
        }
        return error("登录失败");
    }

    /**
     * 查询医生信息
     * @param bean
     * @return
     */
    @RequestMapping("/doctorSelect")
    public Result doctorSelect(@RequestBody Map<String,Object> bean){
        String check = ParamUtil.checkNullOrEmplty(bean, "zh");
        if(StringUtils.isNotEmpty(check)) {
            return error(check);
        }
        try {
            DoctorBean doctor = personService.DoctorSelect4other(bean);
            if (doctor == null) {
                return error("查询失败：查无此账户信息");
            }
            if (doctor != null) {
                return ok("查询成功", doctor);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("登录失败：" + e);
        }
        return error("登录失败");
    }

    //登录查询入参校验
    private String checkSelect(Map<String, Object> bean) {
        if(bean == null || bean.size() == 0)
            return "入参不能为空";

        if(bean.get("zh") == null || bean.get("zh") =="")
            return "账号zh不能为空";
        if(bean.get("mm") == null || bean.get("mm") =="")
            return "密码mm不能为空";

        return "";
    }


}
