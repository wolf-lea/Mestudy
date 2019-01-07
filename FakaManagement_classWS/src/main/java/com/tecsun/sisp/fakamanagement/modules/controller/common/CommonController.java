package com.tecsun.sisp.fakamanagement.modules.controller.common;

import com.tecsun.sisp.fakamanagement.common.AESUtil;
import com.tecsun.sisp.fakamanagement.common.CommUtil;
import com.tecsun.sisp.fakamanagement.common.JedisUtil;
import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.result.common.DistinctAndBankVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.common.LoginPassswordVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.ORGVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.supervise.UserFunctionVO;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 查询银行信息、查询区域信息
 * Created by chenlicong on 2018/1/5.
 */
@Controller
@RequestMapping(value = "/faka/common")
public class CommonController extends BaseController{

    public final static Logger logger = Logger.getLogger(CommonController.class);

    @Autowired
    private LoginUserServiceImpl loginUserService;

    //查询银行信息
    @RequestMapping(value = "/queryBankInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result queryBankInfo() {
        try {
            List<DistinctAndBankVO> list = loginUserService.queryBankInfo();
            if (list.size()>0) {
                logger.info("查询成功!");
                return ok("查询成功！", list);
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //查询区域信息
    @RequestMapping(value = "/queryDistinctInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result queryDistinctInfo(@RequestBody DistinctAndBankVO request) {
        try {
            List<DistinctAndBankVO> list = loginUserService.queryDistinctInfo(request);
            if (list.size()>0) {
                logger.info("查询成功!");
                return ok("查询成功！", list);
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }
    /**
     * 获取银行/网点信息
     * @return
     */
    @RequestMapping(value="/getBankInfo",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result getBankInfo(@RequestBody ORGVO bean){
        try{
            ORGVO orgvo=new ORGVO();

            //通过父级id获取下级银行网点
            if(StringUtils.isEmpty(bean.getParentId())){
                bean.setParentId("0");
            }if(StringUtils.isEmpty(bean.getDistinctId())){
                bean.setDistinctId("0");
            }
            orgvo.setParentId(bean.getParentId());
            orgvo.setDistinctId(bean.getDistinctId());
            //通过银行总行 和 区县  查询网点信息
            List<ORGVO> dList=loginUserService.queryAllBranch(orgvo);
            if(dList!=null && dList.size()>0){
                logger.info("查询银行/网点列表成功！");
                return ok(dList.size(),"查询银行/网点列表成功！",dList);
            }else{
                logger.error("查询银行/网点列表为空！");
                return error("查询银行/网点列表为空！");
            }
        }catch (Exception e){
            logger.error("查询银行/网点列表异常！",e);
            return error("查询银行/网点列表异常！");
        }
    }
    //查询区县信息
    @RequestMapping(value = "/queryDistinct", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result queryDistinct(@RequestBody UserFunctionVO bean) {
        try {
            ORGVO orgvo = loginUserService.getRkwd(Integer.valueOf(bean.getUserId()));
            if (null!=orgvo) {
                logger.info("查询成功!");
                return ok("查询成功！", orgvo);
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //修改发行服务登录密码
    @RequestMapping(value= "/updatePassword", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result updatePassword(@RequestBody LoginPassswordVO bean) {
        LoginPassswordVO vo;
        try {
            //查询原密码
            vo = loginUserService.getPasswordInfo(bean);
            if (vo != null) {
                //匹配原密码
                String oldPassword = bean.getOldPassword();
                if (vo.getOldPassword().equals(oldPassword)) {
                    loginUserService.updatePassword(bean);
                    logger.info("密码修改成功===");
                    return ok("密码修改成功！");
                } else {
                    logger.error("原始密码不正确，请重新输入！");
                    return error("原始密码不正确，请重新输入！");
                }
            }
        } catch (Exception e) {
            logger.error("修改密码发生了异常：" + e);
        }
        return error("修改密码失败！");
    }



}
