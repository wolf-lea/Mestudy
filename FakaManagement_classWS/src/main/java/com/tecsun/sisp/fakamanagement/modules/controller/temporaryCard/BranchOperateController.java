package com.tecsun.sisp.fakamanagement.modules.controller.temporaryCard;

import com.tecsun.sisp.fakamanagement.common.*;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.receive.QuerySpeedOfCardBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.store.BatchManageBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard.FakaFailParamBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard.FakaParamBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard.TemporaryCardInfoBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.receive.SpeedOfCardVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.ORGVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.store.BatchManageVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.store.CardStoreCountVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.store.CardStoreTotalVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard.TempCardPersonVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard.TemporaryCardInfoVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard.TemporaryFakaVO;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.temporaryCard.BranchOperateServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.util.PublicMethod;
import com.tecsun.sisp.fakamanagement.outerface.cardservice.CardServiceUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import java.util.List;
import java.util.Map;

/**
 * 网点临时卡相关接口
 * Created by chenlicong on 2018/3/21.
 */
@Controller
@RequestMapping(value = "/faka/temporary")
public class BranchOperateController extends BaseController{

    public final static Logger logger = Logger.getLogger(BranchOperateController.class);

    @Autowired
    private BranchOperateServiceImpl branchOperateService;
    @Autowired
    private LoginUserServiceImpl loginUserService;

    //查询网点临时卡列表
    @RequestMapping(value = "/getTemporaryCardList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getTemporaryCardList(@RequestBody TemporaryCardInfoBean req,HttpServletRequest request) {
        String pagesize = request.getParameter("pagesize");
        if ("".equals(pagesize)){
            pagesize = "10";
        }
        String pageno = request.getParameter("pageno");
        if ("".equals(pageno)){
            pageno = "1";
        }

        //去除查询条件两端空格
        if (null!=req.getIdcard() && !"".equals(req.getIdcard())){
            req.setIdcard(req.getIdcard().trim());
        }
        if (null!=req.getCardid() && !"".equals(req.getCardid())){
            req.setCardid(req.getCardid().trim());
        }
        if (null!=req.getTempcardNo() && !"".equals(req.getTempcardNo())){
            req.setTempcardNo(req.getTempcardNo().trim());
        }

        Page<TemporaryCardInfoVO> result;
        try {
            Page<TemporaryCardInfoVO> page = new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
            //通过userid获取入库网点ID
            ORGVO orgvo = loginUserService.getRkwd(Integer.valueOf(req.getUserid()));
            if(orgvo == null){
                return error("获取入库网点ID出错");
            }
            req.setRkwd(orgvo.getOrgid());
            logger.info("开始查询网点临时卡列表===");
            result = branchOperateService.getTemporaryCardList(page,req);
            if (result.getData().size()>0) {
                logger.info("查询网点临时卡列表成功===!");
                return ok("查询成功！", result);
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //查询网点临时卡详细
    @RequestMapping(value = "/getTemporaryCardDetail", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getTemporaryCardDetail(@RequestBody TemporaryCardInfoBean request) {
        try {
            logger.info("开始查询网点临时卡详细===");
            TemporaryCardInfoVO vo = branchOperateService.getTemporaryCardDetail(request);
            if (vo != null) {
                logger.info("查询网点临时卡详细成功===!");
                return ok("查询成功！", vo);
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //制卡进度查询--判断是否符合临时卡发放
    @RequestMapping(value = "/checkSpeedOfCard", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result querySpeedOfCard(@RequestBody QuerySpeedOfCardBean vo, @Context HttpServletRequest request,
                                   @Context HttpServletResponse response) {
        SpeedOfCardVO re = new SpeedOfCardVO();
        try {
            if (null==vo.getName()||vo.getName().equals("")) {
                return error("姓名为空");
            }
            if (null==vo.getIdcard()||vo.getIdcard().equals("")) {
                return error("身份证号码为空");
            }
            if (null==vo.getReginal()||vo.getReginal().equals("")) {
                vo.setReginal(vo.getIdcard().substring(0,4)+"00");
            }
            System.out.println("调用卡管制卡进度接口开始"+vo.getIdcard());
            String xml= CardServiceUtils.invoke("getAZ03", vo.getIdcard(), vo.getName(), null, vo.getReginal());
            System.out.println("调用结果："+xml);
            Element rootElement = DocumentHelper.parseText(xml).getRootElement();
            Element err = rootElement.element("ERR");
            if (err != null) {
                if (err.getText().equals("OK")) {
                    logger.info("调用卡管制卡进度接口成功===");
                    re = (SpeedOfCardVO) PublicMethod.ElementToBean(rootElement, SpeedOfCardVO.class);
                    if ("".equals(re.getINSUREFINISHTIME())){
                        logger.info("已申领未发卡，符合临时卡发放！");
                        return ok("已申领未发卡，符合临时卡发放！");
                    }else {

                        System.out.println("调用卡管卡状态接口开始===" + vo.getIdcard());
                        String xml2 = CardServiceUtils.invoke("getBank", vo.getIdcard(), vo.getName(), null, vo.getReginal());
                        System.out.println("调用结果：" + xml2);
                        Element rootElement2 = DocumentHelper.parseText(xml2).getRootElement();
                        Element err2 = rootElement2.element("ERR");
                        Element AAZ502 = rootElement2.element("AAZ502");
                        if (err2 != null) {
                            if (err2.getText().equals("OK")) {
                                logger.info("调用卡管卡状态接口成功===");

                                if ("2".equals(AAZ502.getText()) || "9".equals(AAZ502.getText())) {
                                    logger.info("已有社保卡，且状态符合临时卡发放！");
                                    return ok("已有社保卡，且状态符合临时卡发放！");
                                } else {
                                    logger.info("已有社保卡，且状态不符合临时卡发放！");
                                    return error("已有社保卡，且状态不符合临时卡发放！");
                                }

                            } else {
                                logger.error("调用卡状态接口失败===" + err2.getText());
                                return error(err2.getText());
                            }

                        } else {
                            logger.info("查无此人数据，不符合临时卡发放！");
                            return error("查无此人数据，不符合临时卡发放！");
                        }

                    }
                } else {
                    logger.error("调用制卡进度接口失败==="+err.getText());
                    return error(err.getText());
                }
            } else {
                logger.info("查无此人数据，不符合临时卡发放！");
                return error("查无此人数据，不符合临时卡发放！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return error(GlobalVariable.STRING_CARD_WEBSERVICE_ERROR);
        }
    }

    //省卡管卡状态查询--判断是否符合临时卡发放
    @RequestMapping(value = "/queryCardStatus", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result queryCardStatus(@RequestBody QuerySpeedOfCardBean vo, @Context HttpServletRequest request,
                                   @Context HttpServletResponse response) {
        try {
            if (null==vo.getName()||vo.getName().equals("")) {
                return error("姓名为空");
            }
            if (null==vo.getIdcard()||vo.getIdcard().equals("")) {
                return error("身份证号码为空");
            }
            if (null==vo.getReginal()||vo.getReginal().equals("")) {
                vo.setReginal(vo.getIdcard().substring(0,4)+"00");
            }
            System.out.println("调用卡状态接口开始==="+vo.getIdcard());
            String xml2= CardServiceUtils.invoke("getBank", vo.getIdcard(), vo.getName(), null, vo.getReginal());
            System.out.println("调用结果："+xml2);
            Element rootElement2 = DocumentHelper.parseText(xml2).getRootElement();
            Element err2 = rootElement2.element("ERR");
            Element AAZ502 = rootElement2.element("AAZ502");
            if (err2 != null) {
                if (err2.getText().equals("OK")) {

                    if ("2".equals(AAZ502.getText()) || "9".equals(AAZ502.getText())) {
                        logger.info("符合临时卡发放！");
                        return ok("符合临时卡发放！");
                    } else {
                        logger.info("不符合临时卡发放！");
                        return error("不符合临时卡发放！");
                    }

                } else {
                    logger.error("调用卡状态接口失败===" + err2.getText());
                    return error(err2.getText());
                }

            } else {
                return error("查无数据！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return error(GlobalVariable.STRING_CARD_WEBSERVICE_ERROR);
        }
    }

    //省卡管卡数据查询
    @RequestMapping(value = "/getAC01", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getAC01(@RequestBody QuerySpeedOfCardBean vo, @Context HttpServletRequest request,
                                  @Context HttpServletResponse response) {
        try {
            if (null==vo.getName()||vo.getName().equals("")) {
                return error("姓名为空");
            }
            if (null==vo.getIdcard()||vo.getIdcard().equals("")) {
                return error("身份证号码为空");
            }
            if (null==vo.getReginal()||vo.getReginal().equals("")) {
                vo.setReginal(vo.getIdcard().substring(0,4)+"00");
            }
            System.out.println("调用卡数据查询接口开始==="+vo.getIdcard());
            String xml2= CardServiceUtils.invoke("getAC01", vo.getIdcard(), vo.getName(), null, vo.getReginal());
            System.out.println("调用结果："+xml2);
            Element rootElement2 = DocumentHelper.parseText(xml2).getRootElement();
            Element err2 = rootElement2.element("ERR");
            if (err2 != null) {
                if (err2.getText().equals("OK")) {
                    logger.info("卡数据查询成功！");
                    return ok("卡数据查询成功！");
                } else {
                    logger.error("调用卡数据接口失败===" + err2.getText());
                    return error(err2.getText());
                }
            } else {
                return error("查无数据！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            return error(GlobalVariable.STRING_CARD_WEBSERVICE_ERROR);
        }
    }

    /**
     * 临时卡发放--返回省厅数据写卡
     * @param bean
     * @return
     */
    @RequestMapping(value = "/grantTemporaryCard",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result grantTemporaryCard(@RequestBody FakaParamBean bean){
        try{
            //通过userid获取入库网点编码
            ORGVO orgvo = loginUserService.getRkwd(Integer.valueOf(bean.getUserid()));
            if(orgvo == null){
                return error("获取入库网点ID出错");
            }
            bean.setOrgCode(orgvo.getOrgcode());

            String user= Config.getInstance().get("card_user");
            String pwd=Config.getInstance().get("card_pwd");
            String operation="临时卡发放";
            logger.info("开始调用省厅发放接口===");
            String param=branchOperateService.grantAllDsjkParam(operation, user, pwd, bean);//组装参数
            String res= CardServiceUtils.allDsjk(param);//调用省厅临时卡发放接口
            logger.info("调用结果："+res);
            res="<root>"+res+"</root>";
            Map<String,String> map= Dom4JUtil.readXmlToMap(res);
            String status=map.get("ERR");
            if("OK".equals(status)){
                TemporaryFakaVO bvo=new TemporaryFakaVO();
                bvo.setCardNo(map.get("临时卡编号"));
                bvo.setIdCard(map.get("社会保障号码"));
                bvo.setCardId(map.get("社保卡号"));
                bvo.setFkrq(map.get("发卡日期"));
                bvo.setKyxq(map.get("有效期至"));
                bvo.setAZ01LID(map.get("AZ01LID"));
                bvo.setAZ01ID(map.get("AZ01ID"));
                bvo.setAZ02ID(map.get("AZ02ID"));
                logger.info("调用卡管发放接口成功===");
                return ok("获取成功！",bvo);
            }else {
                logger.error("临时卡发放-调用接口错误！"+status);
                return error("临时卡发放-调用接口错误！"+status);
            }
        }catch (Exception e){
            logger.error("临时卡发放-出现异常！",e);
            return error("临时卡发放-出现异常！");
        }
    }

    /**
     * 临时卡发放失败
     * @param
     * @return
     */
    @RequestMapping(value = "/failTemporaryCard",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result failTemporaryCard(@RequestBody TempCardPersonVO bean){
        try{
            String user= Config.getInstance().get("card_user");
            String pwd=Config.getInstance().get("card_pwd");
            String operation="临时卡发放失败";
            logger.info("开始调用省厅发放失败接口===");
            String param=branchOperateService.failAllDsjkParam(operation, user, pwd, bean);//组装参数
            String res= CardServiceUtils.allDsjk(param);//调用省厅临时卡发放接口
            logger.info("调用结果："+res);
            res="<root>"+res+"</root>";
            Map<String,String> map= Dom4JUtil.readXmlToMap(res);
            String status=map.get("ERR");
            if("OK".equals(status)){
                logger.info("调用临时卡发放失败接口成功===");
                return ok("调用临时卡发放失败接口成功！");
            }else {
                logger.error("临时卡发放失败-调用接口错误！"+status);
                return error("临时卡发放失败-调用接口错误！");
            }
        }catch (Exception e){
            logger.error("临时卡发放失败-出现异常！",e);
            return error("临时卡发放失败-出现异常！");
        }
    }

    //临时卡发放--插入人员信息
    @RequestMapping(value = "/addTempPersonInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result addTempPersonInfo(@RequestBody TempCardPersonVO request) {
        try {
            //通过userid获取入库网点ID
            ORGVO orgvo = loginUserService.getRkwd(Integer.valueOf(request.getUserid()));
            if(orgvo == null){
                return error("获取入库网点ID出错");
            }
            request.setRkwd(orgvo.getOrgid());

            logger.info("人员插入开始===!");
            int i = branchOperateService.addTempPersonInfo(request);
            if (i>0) {
                logger.info("人员插入成功===!");

                //发放失败调用省厅接口===
                if ("01".equals(request.getPersonStatus())) {//发放失败
                    String status = branchOperateService.failTemporaryCard(request);
                    if ("OK".equals(status)) {
                        logger.info("临时卡发放失败,调用省厅接口成功===");
                        return ok("临时卡发放失败,调用省厅接口成功！");
                    } else {
                        logger.error("临时卡发放失败-调用接口错误！" + status);
                        return error("临时卡发放失败-调用接口错误！");
                    }
                }else {
                    return ok("插入成功！");
                }
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("插入信息发生了异常：" + e);
            return error("插入信息失败！");
        }
    }

    /**
     * 临时卡注销
     * @param bean
     * @return
     */
    @RequestMapping(value = "/cancelTemporaryCard",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result cancelTemporaryCard(@RequestBody FakaParamBean bean){
        try{
            //通过userid获取入库网点ID
            ORGVO orgvo = loginUserService.getRkwd(Integer.valueOf(bean.getUserid()));
            if(orgvo == null){
                return error("获取入库网点ID出错");
            }
            bean.setRkwd(orgvo.getOrgid());
            bean.setOrgCode(orgvo.getOrgcode());

            String user= Config.getInstance().get("card_user");
            String pwd=Config.getInstance().get("card_pwd");
            String operation="临时卡注销";
            logger.info("开始调用省厅注销接口===");
            String param=branchOperateService.cancelAllDsjkParam(operation, user, pwd, bean);//组装参数
            String res= CardServiceUtils.allDsjk(param);//调用省厅临时卡发放接口
            logger.info("调用结果："+res);
            res="<root>"+res+"</root>";
            Map<String,String> map= Dom4JUtil.readXmlToMap(res);
            String status=map.get("ERR");
            if("OK".equals(status)){
                logger.info("调用卡管注销接口成功===");
                int i = branchOperateService.cancelTempCard(bean);
                if (i>0) {
                    logger.info("注销成功！");
                    return ok("注销成功！");
                }else {
                    logger.info("注销失败！");
                    return error("注销失败！");
                }
            }else {
                logger.error("临时卡注销-调用接口错误！"+status);
                return error("临时卡注销-调用接口错误！"+status);
            }
        }catch (Exception e){
            logger.error("临时卡注销-出现异常！",e);
            return error("临时卡注销-出现异常！");
        }
    }

    /**
     * 临时卡收回
     * @param bean
     * @return
     */
    @RequestMapping(value = "/recycleTemporaryCard",method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result recycleTemporaryCard(@RequestBody FakaParamBean bean){
        try{
            //通过userid获取入库网点ID
            ORGVO orgvo = loginUserService.getRkwd(Integer.valueOf(bean.getUserid()));
            if(orgvo == null){
                return error("获取入库网点ID出错");
            }
            bean.setRkwd(orgvo.getOrgid());
            bean.setOrgCode(orgvo.getOrgcode());

            String user= Config.getInstance().get("card_user");
            String pwd=Config.getInstance().get("card_pwd");
            String operation="临时卡收回";
            logger.info("开始调用省厅回收接口===");
            String param=branchOperateService.grantAllDsjkParam(operation, user, pwd, bean);//组装参数
            String res= CardServiceUtils.allDsjk(param);//调用省厅临时卡发放接口
            logger.info("调用结果："+res);
            res="<root>"+res+"</root>";
            Map<String,String> map= Dom4JUtil.readXmlToMap(res);
            String status=map.get("ERR");
            if("OK".equals(status)){
                logger.info("调用卡管回收接口成功===");
                int i = branchOperateService.recycleTempCard(bean);
                if (i>0) {
                    logger.info("回收成功！");
                    return ok("回收成功！");
                }else {
                    logger.info("回收失败！");
                    return error("回收失败！");
                }
            }else {
                logger.error("临时卡回收-调用接口错误！"+status);
                return error("临时卡回收-调用接口错误！"+status);
            }
        }catch (Exception e){
            logger.error("临时卡回收-出现异常！",e);
            return error("临时卡回收-出现异常！");
        }
    }




}
