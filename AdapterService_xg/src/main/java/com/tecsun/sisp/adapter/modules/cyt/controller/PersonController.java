package com.tecsun.sisp.adapter.modules.cyt.controller;

import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.PersonBean;
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
public class PersonController extends BaseController {

    Logger logger = Logger.getLogger(PersonController.class);

    @Autowired
    PersonServiceImpl personService;

    /**
     * 参保信息查询
     * @param bean
     * @return
     */
    @RequestMapping("/personSelect")
    public Result personSelect(@RequestBody Map<String,Object> bean){
        String check = checkSelect(bean);
        if(StringUtils.isNotEmpty(check)) {
            return error(check);
        }
        try {
            PersonBean person = personService.PersonSelect4other(bean);
            if (person == null) {
                return error("查无参保信息");
            }
            if (person != null) {
                return ok("查询成功", person);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("查询参保信息失败：" + e);
        }
        return error("查询参保信息失败");

    }

    //参保信息查询入参校验
    private String checkSelect(Map<String, Object> bean) {
        if(bean == null || bean.size() == 0)
            return "入参不能为空";

        if(bean.get("sfzh") == null || bean.get("sfzh") =="")
            return "身份证号sfzh不能为空";
        if(bean.get("xm") == null || bean.get("xm") =="")
            return "姓名xm不能为空";

        return "";
    }


}
