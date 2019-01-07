package com.tecsun.sisp.fakamanagement.modules.controller.store;

import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.store.DispatchBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.store.DispatchVo;
import com.tecsun.sisp.fakamanagement.modules.service.impl.store.DispatchServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

/**
 * 社保卡发行服务-分拨
 * Created by xumaohao on 2018/1/23.
 */
@Controller
@RequestMapping(value = "/faka/cardDispatch")
public class DispatchController extends BaseController {
    public final static Logger logger = Logger.getLogger(DispatchController.class);

    @Autowired
    private DispatchServiceImpl dispatchService;


    /**
     * 查询分拨信息
     * @param bean
     * @param request 页数页码在路径中获取
     * @param response
     * @return
     */
    @RequestMapping(value = "/queryDispatch", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result queryDispatch(@RequestBody DispatchBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        //获取路径的页数页码 默认一页十条
        String pageno = StringUtils.defaultIfBlank(request.getParameter("pageno"), "1");
        String pagesize = StringUtils.defaultIfBlank(request.getParameter("pagesize"), "10");
        Page<DispatchVo> page = null;
        try {
            //去除查询条件两端空格
            if (null!=bean.getBatchNo() && !"".equals(bean.getBatchNo())){
                bean.setBatchNo(bean.getBatchNo().trim());
            }

            //配置页数页码
            page = new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
            //查询数据
            page= dispatchService.queryDispatch(page, bean);
            return ok("查询成功！",page);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("DispatchController中queryDispatch方法出现异常");
        }
        return error("查询失败！");
    }

    /**
     * 对社保卡进行分拨
     * @param bean
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/selectDispatch", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result selectDispatch(@RequestBody DispatchBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        //入参校验
        if(StringUtils.isBlank(bean.getUserid())) return result("300", "用户id不能为空");
        if(bean.getId() == null || bean.getId().size() == 0) return result("300", "批次id不能为空");
        try {
            int res = dispatchService.selectDispatch(bean);
            if(res > 0) return ok("分拨成功！");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("DispatchController中selectDispatch方法出现异常");
        }
        return error("分拨失败！");
    }


}

