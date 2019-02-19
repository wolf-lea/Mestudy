package com.tecsun.sisp.fun.modules.controller.common;

import com.tecsun.sisp.fun.common.DefualtUseData;
import com.tecsun.sisp.fun.common.InitInstainceConfig;
import com.tecsun.sisp.fun.modules.controller.BaseController;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhangqingjie on 15-5-19.
 */

@Controller
@RequestMapping(value = "/ans/common")
public class CommonController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(CommonController.class);

    /**
     * 获取字典组数据
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getAllDictionary", method = RequestMethod.GET)
    @ResponseBody
    public String getAllDictionary(HttpServletRequest request) throws Exception {
        String groupId = request.getParameter("groupId");
        String data = "";
        try {
            data = DefualtUseData.getDictionaryById(groupId);
        } catch (Exception e) {
            logger.error("获取字典组数据出错：" + e.getMessage());
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 获取所有设备管理员
     *
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getAllManger", method = RequestMethod.GET)
    @ResponseBody
    public String getAllManger() throws Exception {
        String data = "";
        try {
            data = DefualtUseData.getAllManger();
        } catch (Exception e) {
            logger.error("获取所有设备管理员出错：" + e.getMessage());
            e.printStackTrace();
        }
        return data;
    }
}
