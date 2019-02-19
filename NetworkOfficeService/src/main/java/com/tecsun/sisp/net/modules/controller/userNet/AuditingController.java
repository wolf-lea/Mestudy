package com.tecsun.sisp.net.modules.controller.userNet;

import com.tecsun.sisp.net.common.BaseController2;
import com.tecsun.sisp.net.common.Constants;
import com.tecsun.sisp.net.common.Page;
import com.tecsun.sisp.net.common.Result;
import com.tecsun.sisp.net.modules.entity.request.DraftsBean;
import com.tecsun.sisp.net.modules.entity.request.MatterRoleBean;
import com.tecsun.sisp.net.modules.entity.response.*;
import com.tecsun.sisp.net.modules.service.impl.AreaServiceImpl;
import com.tecsun.sisp.net.modules.service.impl.DraftsServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;


//审核相关

@Controller
@RequestMapping("/userNet/audit")
public class AuditingController extends BaseController2 {


    private static Logger logger = LoggerFactory
            .getLogger(AuditingController.class);

    @Autowired
    private DraftsServiceImpl draftsService;

    @Autowired
    private AreaServiceImpl areaService;


    //业务审核待办事项  申请记录查询
    @RequestMapping(value = "/queryByApplyRecord", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Result queryByApplyRecord(HttpServletRequest request) throws Exception {

        String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "操作成功";
        DraftsBean bean = new DraftsBean();
//        String pagesize = request.getParameter("pagesize");
//        String pageno = request.getParameter("pageno");
        bean.setPageNo(Integer.valueOf(request.getParameter("pageno")));
        bean.setPageSize(Integer.valueOf(request.getParameter("pagesize")));
        if(request.getParameter("state") != null){
            bean.setState(request.getParameter("state"));
        }
        if(request.getParameter("sfzh") != null){
            bean.setSfzh(request.getParameter("sfzh"));
        }
        if(request.getParameter("tId") != null){
            bean.settId(request.getParameter("tId"));
        }
        if(request.getParameter("businessId") != null){
            bean.setBusinessId(request.getParameter("businessId"));
        }
        if(request.getParameter("sxmc") != null){
            bean.setSxmc(request.getParameter("sxmc"));
        }
        if(request.getParameter("userId") != null){
            bean.setUserId(request.getParameter("userId"));
        }
        bean.setOfficeId("aaa");

//        String sfzh = request.getParameter("sfzh");
//        String tId = request.getParameter("tId");
//        String businessId = request.getParameter("businessId");
//        String sxmc = request.getParameter("sxmc");
//        bean.setUserId(request.getParameter("userId"));
////        if (StringUtils.isNotBlank(state)) {
////                   /*bean.setStatus(new String(status.getBytes("iso8859-1"), "utf-8"));*/
////            bean.setState(state);
////        }
//        if (StringUtils.isNotBlank(sfzh)) {
//            bean.setSfzh(sfzh);
//        }
//        if (StringUtils.isNotBlank(tId)) {
//            bean.settId(tId);
//        }
//        if (StringUtils.isNotBlank(businessId)) {
//            bean.setBusinessId(businessId);
//        }
//        if (StringUtils.isNotBlank(sxmc)) {
//            bean.setSxmc(sxmc);
//        }
        Page<DraftsVo> page = new Page<>(bean.getPageno(),bean.getPageSize());
        try {
            //Page<DraftsVo> page = new Page<>(Integer.valueOf(pageno),Integer.valueOf(pagesize));
//            bean.setPage(page);
            List<DraftsVo> dlist = new ArrayList<DraftsVo>();
            dlist=draftsService.queryByApplyRecord(bean);
            if (dlist != null && dlist.size() > 0) {
                page.setData(dlist);
                message = "查询相关信息成功";
                //Tecsun.rint( "打印出参数"+JsonHelper.javaBeanToJson(dlist));
                return result(statusCode, page.getCount(), message, page.getData());
            }
        } catch (Exception e) {
            logger.error("查询出错:" + e);
        }

        return result(statusCode, message, page.getData());

    }

    //获取所有机构
    @RequestMapping(value = "/queryByOrg", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Result queryByOrg(HttpServletRequest request) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "操作成功!";
        String parentOrgId = request.getParameter("parentOrgId");
        OrgVo bean = new OrgVo();
        bean.setParentOrgId(Integer.valueOf(parentOrgId));
        List<OrgVo> list = areaService.queryByOrg(bean);
        try {
            //	List<OrgVo> list = areaService.queryByOrg();
            if (list != null && list.size() > 0) {
                message = "查询相关信息成功";
                //Tecsun.rint( "打印出参数"+JsonHelper.javaBeanToJson(list));
                return result(statusCode, message, list);
            }
        } catch (Exception e) {
            logger.error("查询出错:" + e);
        }
        return result(statusCode, message, list);
    }

    //获取事项类型
    @RequestMapping(value = "/queryByTmatter", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Result queryByTmatter() throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "操作成功";
        List<MatterVo> list = draftsService.queryByTmatter();
        try {
            if (list != null && list.size() > 0) {
                //Tecsun.rint( "打印出参数"+JsonHelper.javaBeanToJson(list));
                return result(statusCode, message, list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "获取失败";
            logger.error("数据出现异常", e);
        }
        return result(statusCode, message, list);
    }

    //用户事项关联列表
    @RequestMapping(value = "/queryByCorrelation", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result queryByCorrelation(@RequestBody MatterRoleBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "操作成功";

//        String userId = request.getParameter("userId");
//        MatterRoleBean bean = new MatterRoleBean();
//        bean.setUserId(Integer.valueOf(userId));
        try {
            List<MatterRoleBean> list = areaService.queryByCorrelation(bean);
            if (list != null && list.size() > 0) {
                //Tecsun.rint( "打印出参数"+JsonHelper.javaBeanToJson(list));
                return result(statusCode, message, list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "获取失败";
            logger.error("数据出现异常", e);
        }
        return result(statusCode, message);
    }

    //查询待办事项详细记录
    @RequestMapping(value = "/queryByApplyRecord2", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Result queryByApplyRecord2(HttpServletRequest request) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "操作成功";
        DraftsBean2 bean = new DraftsBean2();
        bean.settId(request.getParameter("tId"));
        //String businessId = request.getParameter("businessId");


        List<DraftsVo> list = draftsService.queryByApplyRecord2(bean);
        try {
            if (list != null && list.size() > 0) {
                //Tecsun.rint( "打印出参数"+JsonHelper.javaBeanToJson(list));
                return result(statusCode, message, list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "获取失败";
            logger.error("数据出现异常", e);
        }
        return result(statusCode, message, list);
    }


    //查询机构下的用户
    @RequestMapping(value = "/queryByOrgUser", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Result queryByOrgUser(HttpServletRequest request) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "操作成功";
        OrgUserVo bean = new OrgUserVo();
        String orgId = request.getParameter("orgId");
        bean.setOrgId(Integer.valueOf(orgId));
        List<OrgUserVo> list = areaService.queryByOrgUser(bean);
        try {
            if (list != null && list.size() > 0) {
                //Tecsun.rint( "打印出参数"+JsonHelper.javaBeanToJson(list));
                return result(statusCode, message, list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "获取失败";
            logger.error("数据出现异常", e);
        }
        return result(statusCode, message, list);
    }

    //查询用户列表
    @RequestMapping(value = "/queryUserList", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Result queryUserList(HttpServletRequest request) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "操作成功";
        List<OrgUserVo> list = areaService.queryUserList();
        try {
            //List<OrgUserVo> list = areaService.queryUserList();
            if (list != null && list.size() > 0) {
                //Tecsun.rint( "打印出参数"+JsonHelper.javaBeanToJson(list));
                return result(statusCode, message, list);
            }
        } catch (Exception e) {
            e.printStackTrace();
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "获取失败";
            logger.error("数据出现异常", e);
        }
        return result(statusCode, message, list);
    }


}
