package com.tecsun.sisp.fakamanagement.modules.controller.replacement;

import com.tecsun.sisp.fakamanagement.common.Constants;
import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.replacement.ReplaceCardBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.replacement.ReplaceCardVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.ORGVO;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.replacement.ReplacementCardServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * (预制卡)补换卡管理：对内/对外接口
 * Created by chenlicong on 2018/9/4.
 */
@Controller
@RequestMapping(value = "/faka/replace")
public class ReplacementCardController extends BaseController{

    public final static Logger logger = Logger.getLogger(ReplacementCardController.class);

    @Autowired
    private ReplacementCardServiceImpl replacementCardService;
    @Autowired
    private LoginUserServiceImpl loginUserService;

    /**
     * 查询补换卡人员信息
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getReplaceCardInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getReplaceCardInfo(@RequestBody ReplaceCardBean bean) {
        ReplaceCardVO vo = null;
        try {
            //去除查询条件两端空格
            if (null!=bean.getIdcard() && !"".equals(bean.getIdcard())){
                bean.setIdcard(bean.getIdcard().trim());
            }
            if (null!=bean.getName() && !"".equals(bean.getName())){
                bean.setName(bean.getName().trim());
            }

            if (null==bean.getName()||bean.getName().equals("")) {
                return error("姓名不能为空");
            }
            if (null==bean.getIdcard()||bean.getIdcard().equals("")) {
                return error("身份证号码不能为空");
            }
            if (null==bean.getReginal()||bean.getReginal().equals("")) {
                bean.setReginal(bean.getIdcard().substring(0,4)+"00");
            }

            logger.info("开始查询===");
            vo = replacementCardService.getReplaceCardInfo(bean);
            logger.info("查询完成===");
        } catch (Exception e) {
            logger.error("查询补换卡人员信息发生异常："+e);
            return error(e.getMessage());
        }
        if (vo == null) {
            return error("查无数据！");
        }else{
            if ("".equals(vo.getErrMsg()) || vo.getErrMsg()==null){
                return ok("查询成功！", vo);
            }else {
                return error(vo.getErrMsg(),vo);
            }

        }
    }

    /**
     * 临时挂失
     * @param bean
     * @return
     */
    @RequestMapping(value = "/lsgs", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result lsgs(@RequestBody ReplaceCardBean bean) {
        String result = "";
        try {
            //去除查询条件两端空格
            if (null!=bean.getIdcard() && !"".equals(bean.getIdcard())){
                bean.setIdcard(bean.getIdcard().trim());
            }
            if (null!=bean.getName() && !"".equals(bean.getName())){
                bean.setName(bean.getName().trim());
            }
            if (null==bean.getName()||bean.getName().equals("")) {
                return error("姓名不能为空");
            }
            if (null==bean.getIdcard()||bean.getIdcard().equals("")) {
                return error("身份证号码不能为空");
            }
            if (null==bean.getCardid()||bean.getCardid().equals("")) {
                return error("社保卡号不能为空");
            }
            if (null==bean.getStatus()||bean.getStatus().equals("")) {
                return error("卡状态不能为空");
            }
            if (null==bean.getReginal()||bean.getReginal().equals("")) {
                bean.setReginal(bean.getIdcard().substring(0,4)+"00");
            }
            //正常状态的卡才可以临时挂失
            if ("1".equals(bean.getStatus())) {
                logger.info("开始临时挂失===");
                result = replacementCardService.lsgs(bean);
                logger.info("临时挂失完成===");
            }else {
                return error("只有处于正常状态的卡才可以临时挂失");
            }
        } catch (Exception e) {
            logger.error("临时挂失发生异常："+e);
            return error(e.getMessage());
        }
        if (result == "") {
            return ok("临时挂失成功！");
        }else{
            return error(result);
        }
    }

    /**
     * 正式挂失
     * @param bean
     * @return
     */
    @RequestMapping(value = "/zsgs", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result zsgs(@RequestBody ReplaceCardBean bean) {
        String result = "";
        try {
            //去除查询条件两端空格
            if (null!=bean.getIdcard() && !"".equals(bean.getIdcard())){
                bean.setIdcard(bean.getIdcard().trim());
            }
            if (null!=bean.getName() && !"".equals(bean.getName())){
                bean.setName(bean.getName().trim());
            }
            if (null==bean.getName()||bean.getName().equals("")) {
                return error("姓名不能为空");
            }
            if (null==bean.getIdcard()||bean.getIdcard().equals("")) {
                return error("身份证号码不能为空");
            }
            if (null==bean.getCardid()||bean.getCardid().equals("")) {
                return error("社保卡号不能为空");
            }
            if (null==bean.getStatus()||bean.getStatus().equals("")) {
                return error("卡状态不能为空");
            }
            if (null==bean.getReginal()||bean.getReginal().equals("")) {
                bean.setReginal(bean.getIdcard().substring(0,4)+"00");
            }
            //正常状态或临时挂失状态的卡才可以正式挂失
            if ("1".equals(bean.getStatus()) || "A".equals(bean.getStatus())) {
                logger.info("开始正式挂失===");
                result = replacementCardService.zsgs(bean);
                logger.info("正式挂失完成===");
            }else {
                return error("只有处于正常状态或临时挂失状态的卡才可以正式挂失");
            }
        } catch (Exception e) {
            logger.error("正式挂失发生异常："+e);
            return error(e.getMessage());
        }
        if (result == "") {
            return ok("正式挂失成功！");
        }else{
            return error(result);
        }
    }

    /**
     * 解除挂失
     * @param bean
     * @return
     */
    @RequestMapping(value = "/jcgs", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result jcgs(@RequestBody ReplaceCardBean bean) {
        String result = "";
        try {
            //去除查询条件两端空格
            if (null!=bean.getIdcard() && !"".equals(bean.getIdcard())){
                bean.setIdcard(bean.getIdcard().trim());
            }
            if (null!=bean.getName() && !"".equals(bean.getName())){
                bean.setName(bean.getName().trim());
            }
            if (null==bean.getName()||bean.getName().equals("")) {
                return error("姓名不能为空");
            }
            if (null==bean.getIdcard()||bean.getIdcard().equals("")) {
                return error("身份证号码不能为空");
            }
            if (null==bean.getCardid()||bean.getCardid().equals("")) {
                return error("社保卡号不能为空");
            }
            if (null==bean.getStatus()||bean.getStatus().equals("")) {
                return error("卡状态不能为空");
            }
            if (null==bean.getReginal()||bean.getReginal().equals("")) {
                bean.setReginal(bean.getIdcard().substring(0,4)+"00");
            }
            //临时挂失状态或正式挂失状态的卡可以解除挂失
            if ("A".equals(bean.getStatus()) || "2".equals(bean.getStatus())) {
                logger.info("开始解除挂失===");
                result = replacementCardService.jcgs(bean);
                logger.info("解除挂失完成===");
            }else {
                return error("只有处于临时挂失状态或正式挂失状态的卡可以解除挂失");
            }
        } catch (Exception e) {
            logger.error("解除挂失发生异常："+e);
            return error(e.getMessage());
        }
        if (result == "") {
            return ok("解除挂失成功！");
        }else{
            return error(result);
        }
    }

    /**
     * (即时制卡标注)补换卡标注
     * @param bean
     * @return
     */
    @RequestMapping(value = "/replaceMark", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result replaceMark(@RequestBody ReplaceCardBean bean) {
        String result = "";
        try {
            //去除查询条件两端空格
            if (null!=bean.getIdcard() && !"".equals(bean.getIdcard())){
                bean.setIdcard(bean.getIdcard().trim());
            }
            if (null!=bean.getName() && !"".equals(bean.getName())){
                bean.setName(bean.getName().trim());
            }
            if (null==bean.getName()||bean.getName().equals("")) {
                return error("姓名不能为空");
            }
            if (null==bean.getIdcard()||bean.getIdcard().equals("")) {
                return error("身份证号码不能为空");
            }
            if (null==bean.getReginal()||bean.getReginal().equals("")) {
                bean.setReginal(bean.getIdcard().substring(0,4)+"00");
            }

            logger.info("补换卡标注开始===");
            result = replacementCardService.replaceMark(bean);
            logger.info("补换卡标注完成===");
        } catch (Exception e) {
            logger.error("补换卡标注发生异常："+e);
            return error(e.getMessage());
        }
        if (result == "") {
            return ok("标注成功！");
        }else{
            return error(result);
        }
    }

    /**
     * 申请(补换)社保卡
     * @param bean
     * @return
     */
    @RequestMapping(value = "/applyReplaceCard", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result applyReplaceCard(@RequestBody ReplaceCardBean bean) {
        String result = "";
        try {
            //去除查询条件两端空格
            if (null!=bean.getIdcard() && !"".equals(bean.getIdcard())){
                bean.setIdcard(bean.getIdcard().trim());
            }
            if (null!=bean.getName() && !"".equals(bean.getName())){
                bean.setName(bean.getName().trim());
            }
            if (null==bean.getName()||bean.getName().equals("")) {
                return error("姓名不能为空");
            }
            if (null==bean.getIdcard()||bean.getIdcard().equals("")) {
                return error("身份证号码不能为空");
            }
            if (null==bean.getReplaceType()||bean.getReplaceType().equals("")) {
                return error("补卡类别不能为空");
            }
            if (null==bean.getReplaceReason()||bean.getReplaceReason().equals("")) {
                return error("补卡原因不能为空");
            }
            if (null==bean.getBankCode()||bean.getBankCode().equals("")) {
                return error("制卡银行不能为空");
            }
            if (null==bean.getReginal()||bean.getReginal().equals("")) {
                bean.setReginal(bean.getIdcard().substring(0,4)+"00");
            }

            logger.info("申请社保卡开始===");
            result = replacementCardService.applyReplaceCard(bean);
            logger.info("申请社保卡完成===");
        } catch (Exception e) {
            logger.error("申请社保卡发生异常："+e);
            return error(e.getMessage());
        }
        if (result == "") {
            return ok("申请社保卡成功！");
        }else{
            return error(result);
        }
    }

    /**
     * 验证预制卡是否入库
     * @param bean
     * @return
     */
    @RequestMapping(value = "/checkReplaceCard", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result checkReplaceCard(@RequestBody ReplaceCardBean bean) {
        try {
            if (null==bean.getReplacecardNo()||bean.getReplacecardNo().equals("")) {
                return error("预制卡编号不能为空");
            }
            if (null==bean.getAtr()||bean.getAtr().equals("")) {
                return error("卡复位码不能为空");
            }
            logger.info("验证预制卡是否入库===");
            List<ReplaceCardVO> list = replacementCardService.checkReplaceCard(bean);
            if (list.size()>0) {
                logger.info("该张预制卡已入库");
                return ok("该张预制卡已入库");
            }else{
                logger.info("该张预制卡未入库，不能制卡");
                return error("该张预制卡未入库，不能制卡");
            }
        } catch (Exception e) {
            logger.error("验证预制卡是否入库发生异常："+e);
            return error(e.getMessage());
        }
    }

    /**
     * 制卡-获取制卡数据
     * @param bean
     * @return
     */
    @RequestMapping(value = "/makeReplaceCard", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result makeReplaceCard(@RequestBody ReplaceCardBean bean) {
        ReplaceCardVO vo = null;
        try {
            //去除查询条件两端空格
            if (null!=bean.getIdcard() && !"".equals(bean.getIdcard())){
                bean.setIdcard(bean.getIdcard().trim());
            }
            if (null!=bean.getName() && !"".equals(bean.getName())){
                bean.setName(bean.getName().trim());
            }
            if (null==bean.getName()||bean.getName().equals("")) {
                return error("姓名不能为空");
            }
            if (null==bean.getIdcard()||bean.getIdcard().equals("")) {
                return error("身份证号码不能为空");
            }
            if (null==bean.getReginal()||bean.getReginal().equals("")) {
                bean.setReginal(bean.getIdcard().substring(0,4)+"00");
            }
            logger.info("获取制卡数据开始===");
            vo = replacementCardService.makeReplaceCard(bean);
            logger.info("获取制卡数据完成===");
        } catch (Exception e) {
            logger.error("获取制卡数据发生异常："+e);
            return error(e.getMessage());
        }
        if (vo == null) {
            return error("获取制卡数据失败！");
        }else{
            if ("".equals(vo.getErrMsg()) || vo.getErrMsg()==null){
                return ok("获取制卡数据成功",vo);
            }else {
                return error(vo.getErrMsg());
            }
        }
    }

    /**
     * PC端制卡-获取制卡数据
     * @param bean
     * @return
     */
    @RequestMapping(value = "/makeReplaceCardByPC", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result makeReplaceCardByPC(@RequestBody ReplaceCardBean bean) {
        ReplaceCardVO vo = null;
        try {
            //去除查询条件两端空格
            if (null!=bean.getIdcard() && !"".equals(bean.getIdcard())){
                bean.setIdcard(bean.getIdcard().trim());
            }
            if (null!=bean.getName() && !"".equals(bean.getName())){
                bean.setName(bean.getName().trim());
            }
            if (null==bean.getName()||bean.getName().equals("")) {
                return error("姓名不能为空");
            }
            if (null==bean.getIdcard()||bean.getIdcard().equals("")) {
                return error("身份证号码不能为空");
            }
            if (null==bean.getReginal()||bean.getReginal().equals("")) {
                bean.setReginal(bean.getIdcard().substring(0,4)+"00");
            }
            //通过用户id查询银行编码
            if (null==bean.getUserid()||bean.getUserid().equals("")) {
                return error("用户id不能为空");
            }else {
                logger.info("通过用户id查询银行编码==="+bean.getUserid());
                String fkwd = loginUserService.getFkwd(bean.getUserid());
                String bank = loginUserService.getBankCode(fkwd);
                logger.info("银行编码为==="+bank);
                if (!"".equals(bank)){
                    bean.setBankCode(bank);
                }
            }

            logger.info("PC端获取制卡数据开始===");
            vo = replacementCardService.makeReplaceCardByPC(bean);
            logger.info("PC端获取制卡数据完成===");
        } catch (Exception e) {
            logger.error("PC端获取制卡数据发生异常："+e);
            return error(e.getMessage());
        }
        if (vo == null) {
            return error("获取制卡数据失败！");
        }else{
            if ("".equals(vo.getErrMsg()) || vo.getErrMsg()==null){
                return ok("获取制卡数据成功",vo);
            }else {
                return error(vo.getErrMsg());
            }
        }
    }

    /**
     * 即制卡回盘
     * @param bean
     * @return
     */
    @RequestMapping(value = "/replyCardInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result replyCardInfo(@RequestBody ReplaceCardBean bean) {
        String res = "";
        try {
            if (null==bean.getName()||bean.getName().equals("")) {
                return error("姓名不能为空");
            }
            if (null==bean.getIdcard()||bean.getIdcard().equals("")) {
                return error("身份证号码不能为空");
            }
            if (null==bean.getReginal()||bean.getReginal().equals("")) {
                bean.setReginal(bean.getIdcard().substring(0,4)+"00");
            }
            logger.info("即制卡回盘开始===");
            res = replacementCardService.replyCardInfo(bean);
            logger.info("即制卡回盘完成===");
        } catch (Exception e) {
            logger.error("即制卡回盘发生异常："+e);
            return error(e.getMessage());
        }
        if (res == "") {
            return ok("即制卡回盘成功");
        }else{
            return error(res);
        }
    }

    /**
     * 制卡失败修改卡信息
     * @param bean
     * @return
     */
    @RequestMapping(value = "/updateReplaceCardInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result updateReplaceCardInfo(@RequestBody ReplaceCardBean bean) {
        boolean i = false;
        if (null==bean.getReplacecardNo()||bean.getReplacecardNo().equals("")) {
            return error("预制卡编号不能为空");
        }
        if (null==bean.getStatus()||bean.getStatus().equals("")) {
            return error("卡状态不能为空");
        }
        if (null==bean.getRkwd()||bean.getRkwd().equals("")) {
            return error("入库网点不能为空");
        }
        /*String message="操作成功";
        String statusCode=Constants.RESULT_MESSAGE_SUCCESS;
        if ( null==bean.getRkwd()|| bean.getRkwd() == "") {
            message = " 用户id  不能为空!";
            logger.error(message);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            return new Result(statusCode, message, bean);
        }*/
        try {
            /*//通过userId获取网点编码
            ORGVO orgvo = loginUserService.getRkwd(Integer.valueOf(bean.getRkwd()));
            if (null == orgvo) {
                message = "获取该用户所在的网点机构信息为空！";
                logger.error(message);
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                return new Result(statusCode, message, null);
            }
            bean.setRkwd(orgvo.getOrgcode());//网点编码*/
            logger.info("制卡失败修改卡信息开始===");
            i = replacementCardService.updateReplaceCardInfo(bean);
            logger.info("制卡失败修改卡信息完成===");
        } catch (Exception e) {
            logger.error("制卡失败修改卡信息发生异常："+e);
            return error(e.getMessage());
        }
        if (i == true) {
            return ok("修改成功");
        }else{
            return error("修改失败");
        }
    }

    /**
     * 记录补换卡人员和卡数据
     * @param bean
     * @return
     */
    @RequestMapping(value = "/saveReplaceCardInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result saveReplaceCardInfo(@RequestBody ReplaceCardBean bean) {
        boolean i = false;
        try {
            if (null==bean.getName()||bean.getName().equals("")) {
                return error("姓名不能为空");
            }
            if (null==bean.getIdcard()||bean.getIdcard().equals("")) {
                return error("身份证号码不能为空");
            }
            if (null==bean.getReginal()||bean.getReginal().equals("")) {
                bean.setReginal(bean.getIdcard().substring(0,4)+"00");
            }
            logger.info("记录补换卡人员和卡数据开始===");
            i = replacementCardService.saveReplaceCardInfo(bean);
            logger.info("记录补换卡人员和卡数据完成===");
        } catch (Exception e) {
            logger.error("记录补换卡人员和卡数据发生异常："+e);
            return error(e.getMessage());
        }
        if (i == true) {
            return ok("记录成功");
        }else{
            return error("记录失败");
        }
    }


}
