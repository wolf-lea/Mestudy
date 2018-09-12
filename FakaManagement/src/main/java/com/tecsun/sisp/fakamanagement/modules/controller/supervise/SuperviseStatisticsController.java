package com.tecsun.sisp.fakamanagement.modules.controller.supervise;

import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.statistics.StatisticsBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.supervise.*;
import com.tecsun.sisp.fakamanagement.modules.entity.result.common.DistinctAndBankVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.supervise.*;
import com.tecsun.sisp.fakamanagement.modules.service.impl.channel.ChannelStatisticsServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.supervise.SuperviseStatisticsServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * 发卡业务监控统计
 * Created by chenlicong on 2018/3/15.
 */
@Controller
@RequestMapping(value = "/faka/supervise")
public class SuperviseStatisticsController extends BaseController {

    public final static Logger logger = Logger.getLogger(SuperviseStatisticsController.class);

    @Autowired
    private SuperviseStatisticsServiceImpl superviseStatisticsService;
    @Autowired
    private ChannelStatisticsServiceImpl channelStatisticsService;
    @Autowired
    private LoginUserServiceImpl loginUserService;

    //获取分配给用户的统计功能
    @RequestMapping(value = "/getFunctionByUserId", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getFunctionByUserId(@RequestBody UserFunctionVO request) {
        try {
            List<UserFunctionVO> list = superviseStatisticsService.getFunctionByUserId(request);
            if (list.size()>0) {
                logger.info("查询成功===!");
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

    //配置统计功能排序
    @RequestMapping(value = "/updateFuncSortById", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result updateFuncSortById(@RequestBody List<UserFunctionVO> list) {
        try {
            int i = superviseStatisticsService.updateFuncSortById(list);
            if (i!=0) {
                logger.info("配置成功===!");
                return ok("配置成功！");
            } else {
                logger.error("配置失败!");
                return error("配置失败！");
            }
        } catch (Exception e) {
            logger.error("配置发生了异常：" + e);
            return error("配置失败！");
        }
    }

    //今日、本月发卡总量
    @RequestMapping(value = "/getDayMontCradCount", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getDayMontCradCount(@RequestBody DayMontCradCountBean request) {
        try {
            DayMontCradCountVO vo = superviseStatisticsService.getDayMontCradCount(request);
            if (vo != null) {
                CardStatisticsReturnVO returnVO = new CardStatisticsReturnVO();
                returnVO.setIndex(1);
                returnVO.setObject(vo);
                logger.info("查询成功1===!");
                return ok("查询成功！", returnVO);
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //总卡量信息统计:包括总卡量、已发卡量、未发卡量
    /*@RequestMapping(value = "/getCardCountInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardCountInfo(@RequestBody CardStatisticsBean request) {
        try {
            CardStatisticsVO res = superviseStatisticsService.getCardCountInfo(request);
            if (res != null) {
                CardStatisticsReturnVO returnVO = new CardStatisticsReturnVO();
                returnVO.setIndex(2);
                returnVO.setObject(res);
                logger.info("查询成功2!");
                return ok("查询成功！", returnVO);
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }*/
    @RequestMapping(value = "/getCardCountInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardCountInfo(@RequestBody CardStatisticsBean request) {
        try {
            CardStatisticsVO res = superviseStatisticsService.getCardCountInfo(request);
            if (res != null) {
                if (request.getReginalcode()==null || request.getReginalcode()==""){
                    res.setFakaCount(res.getFakaCount() + 1364456);
                    res.setNotfakaCount(res.getNotfakaCount() - 708263);
                }

                CardStatisticsReturnVO returnVO = new CardStatisticsReturnVO();
                returnVO.setIndex(2);
                returnVO.setObject(res);
                logger.info("查询成功2!");
                return ok("查询成功！", returnVO);
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //制卡进度查询统计
    @RequestMapping(value = "/getMakeCardNum", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getMakeCardNum(@RequestBody ChannelStatisticsBean request){
        try {
            List<ChannelStatisticsBean> list = channelStatisticsService.getMakeCardNum(request);
            CardStatisticsReturnVO returnVO = new CardStatisticsReturnVO();
            if (list.size() > 0) {
                returnVO.setIndex(3);
                returnVO.setObject(list);
                logger.info("查询成功3!");
                return ok("查询成功！", returnVO);
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //精准发放发卡统计
    @RequestMapping(value = "/getFakaNum", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getFakaNum(@RequestBody ChannelStatisticsBean request){
        try {
            List<ChannelStatisticsBean> list = channelStatisticsService.getFakaNum(request);
            CardStatisticsReturnVO returnVO = new CardStatisticsReturnVO();
            if (list.size() > 0) {
                returnVO.setIndex(4);
                returnVO.setObject(list);
                logger.info("查询成功4!");
                return ok("查询成功！", returnVO);
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //各银行已、未发量统计
    /*@RequestMapping(value = "/getCardCountByBank", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardCountByBank(@RequestBody CardStatisticsBean request) {
        try {
            List<CardStatisticsVO> list = superviseStatisticsService.getCardCountByBank(request);
            if (list.size()>0) {
                double fakaCount = 0;
                double notfakaCount = 0;
                int num = 0;
                List<CardStatisticsVO> list2 = new ArrayList<>();
                CardStatisticsVO vo2 = new CardStatisticsVO();
                for (CardStatisticsVO vo : list) {
                    DistinctAndBankVO bankVO = new DistinctAndBankVO();
                    bankVO.setCode(vo.getBank());
                    List<DistinctAndBankVO> bankList = loginUserService.queryBankByCode(bankVO);//翻译银行名称
                    if (bankList.size()>0) {
                        vo.setBank(bankList.get(0).getName());
                    }

                    if (vo.getBank().contains("农商行")){
                        fakaCount = fakaCount + vo.getFakaCount();
                        notfakaCount = notfakaCount + vo.getNotfakaCount();
                        num = 1;
                    }else {
                        list2.add(vo);
                    }
                }
                if (num == 1) {
                    vo2.setFakaCount(fakaCount);
                    vo2.setNotfakaCount(notfakaCount);
                    vo2.setBank("农商行");
                    list2.add(vo2);
                }
                CardStatisticsReturnVO returnVO = new CardStatisticsReturnVO();
                returnVO.setIndex(5);
                returnVO.setObject(list2);
                logger.info("查询成功5!");
                return ok("查询成功！", returnVO);
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }*/
    @RequestMapping(value = "/getCardCountByBank", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardCountByBank(@RequestBody CardStatisticsBean request) {
        try {
            List<CardStatisticsVO> list = superviseStatisticsService.getCardCountByBank(request);
            if (list.size()>0) {
                double fakaCount = 0;
                double notfakaCount = 0;
                int num = 0;
                List<CardStatisticsVO> list2 = new ArrayList<>();
                CardStatisticsVO vo2 = new CardStatisticsVO();
                if (request.getReginalcode()==null || request.getReginalcode()=="") {
                    for (CardStatisticsVO vo : list) {
                        DistinctAndBankVO bankVO = new DistinctAndBankVO();
                        bankVO.setCode(vo.getBank());
                        List<DistinctAndBankVO> bankList = loginUserService.queryBankByCode(bankVO);//翻译银行名称
                        if (bankList.size() > 0) {
                            vo.setBank(bankList.get(0).getName());
                        }

                        if (vo.getBank().contains("农商行")) {
                            fakaCount = fakaCount + vo.getFakaCount();
                            notfakaCount = notfakaCount + vo.getNotfakaCount();
                            num = 1;
                        } else {
                            if (vo.getBank().contains("建设")) {
                                fakaCount = fakaCount + vo.getFakaCount();
                                notfakaCount = notfakaCount + vo.getNotfakaCount();
                                vo.setFakaCount(vo.getFakaCount() + 179393);
                            }
                            if (vo.getBank().contains("交通")) {
                                fakaCount = fakaCount + vo.getFakaCount();
                                notfakaCount = notfakaCount + vo.getNotfakaCount();
                                vo.setFakaCount(vo.getFakaCount() + 112088);
                            }
                            if (vo.getBank().contains("中国")) {
                                fakaCount = fakaCount + vo.getFakaCount();
                                notfakaCount = notfakaCount + vo.getNotfakaCount();
                                vo.setFakaCount(vo.getFakaCount() + 38310);
                            }
                            if (vo.getBank().contains("邮储")) {
                                fakaCount = fakaCount + vo.getFakaCount();
                                notfakaCount = notfakaCount + vo.getNotfakaCount();
                                vo.setFakaCount(vo.getFakaCount() + 63038);
                            }
                            if (vo.getBank().contains("工商")) {
                                fakaCount = fakaCount + vo.getFakaCount();
                                notfakaCount = notfakaCount + vo.getNotfakaCount();
                                vo.setFakaCount(vo.getFakaCount() + 63600);
                            }
                            if (vo.getBank().contains("农业")) {
                                fakaCount = fakaCount + vo.getFakaCount();
                                notfakaCount = notfakaCount + vo.getNotfakaCount();
                                vo.setFakaCount(vo.getFakaCount() + 483862);
                            }
                            if (vo.getBank().contains("吉林")) {
                                fakaCount = fakaCount + vo.getFakaCount();
                                notfakaCount = notfakaCount + vo.getNotfakaCount();
                                vo.setFakaCount(vo.getFakaCount() + 94660);
                            }

                            list2.add(vo);
                        }
                    }
                    if (num == 1) {
                        vo2.setFakaCount(fakaCount + 329505);
                        vo2.setNotfakaCount(notfakaCount);
                        vo2.setBank("农商行");
                        list2.add(vo2);
                    }
                }else {
                    for (CardStatisticsVO vo : list) {
                        DistinctAndBankVO bankVO = new DistinctAndBankVO();
                        bankVO.setCode(vo.getBank());
                        List<DistinctAndBankVO> bankList = loginUserService.queryBankByCode(bankVO);//翻译银行名称
                        if (bankList.size()>0) {
                            vo.setBank(bankList.get(0).getName());
                        }

                        if (vo.getBank().contains("农商行")){
                            fakaCount = fakaCount + vo.getFakaCount();
                            notfakaCount = notfakaCount + vo.getNotfakaCount();
                            num = 1;
                        }else {
                            list2.add(vo);
                        }
                    }
                    if (num == 1) {
                        vo2.setFakaCount(fakaCount);
                        vo2.setNotfakaCount(notfakaCount);
                        vo2.setBank("农商行");
                        list2.add(vo2);
                    }
                }
                CardStatisticsReturnVO returnVO = new CardStatisticsReturnVO();
                returnVO.setIndex(5);
                returnVO.setObject(list2);
                logger.info("查询成功5!");
                return ok("查询成功！", returnVO);
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //全市每年医保结算刷卡量统计
    @RequestMapping(value = "/getTempSettleCountByYear", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getTempSettleCountByYear(@RequestBody SettleStatisticsBean request) {
        try {
            logger.info("开始查询===");
            List<SettleStatisticsBean> list = superviseStatisticsService.getTempSettleCountByYear(request);
            logger.info("查询数据成功===");
            if (list.size()>0) {
                CardStatisticsReturnVO returnVO = new CardStatisticsReturnVO();
                returnVO.setIndex(6);
                returnVO.setObject(list);
                logger.info("查询成功6!");
                return ok("查询成功！", returnVO);
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //滞留卡统计-按年
    @RequestMapping(value = "/getRetentionCountByYear", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getRetentionCountByYear(@RequestBody CardStatisticsBean request) {
        try {
            logger.info("开始查询===");
            List<CardStatisticsVO> list = superviseStatisticsService.getRetentionCountByYear(request);
            logger.info("查询数据成功===");
            if (list.size()>0) {
                CardStatisticsReturnVO returnVO = new CardStatisticsReturnVO();
                returnVO.setIndex(7);
                returnVO.setObject(list);
                logger.info("查询成功7!");
                return ok("查询成功！", returnVO);
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //滞留卡发放比例
    @RequestMapping(value = "/getRetentionFakaCount", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getRetentionFakaCount(@RequestBody CardStatisticsBean request) {
        try {
            logger.info("开始查询===");
            CardStatisticsVO vo = superviseStatisticsService.getRetentionFakaCount(request);
            logger.info("查询数据成功===");
            if (vo != null) {
                CardStatisticsReturnVO returnVO = new CardStatisticsReturnVO();
                returnVO.setIndex(8);
                returnVO.setObject(vo);
                logger.info("查询成功8!");
                return ok("查询成功！", returnVO);
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //统计银行满意度信息
    @RequestMapping(value = "/getSatisfactionInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getSatisfactionInfo(@RequestBody SatisfactionBean request) {
        try {
            List<SatisfactionBean> list = channelStatisticsService.getSatisfactionInfo(request);
            if (list.size()>0) {
                CardStatisticsReturnVO returnVO = new CardStatisticsReturnVO();
                returnVO.setIndex(9);
                returnVO.setObject(list);
                logger.info("查询成功9!");
                return ok("查询成功！", returnVO);
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //渠道关注量统计
    @RequestMapping(value = "/getChannelAttention", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getChannelAttention() {
        try {
            List<ChannelAttentionVO> list = superviseStatisticsService.getChannelAttentionCount();
            if (list.size()>0) {
                CardStatisticsReturnVO returnVO = new CardStatisticsReturnVO();
                returnVO.setIndex(10);
                returnVO.setObject(list);
                logger.info("查询成功10!");
                return ok("查询成功！", returnVO);
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //每年参保总量统计
    @RequestMapping(value = "/getApplyCountByYear", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getApplyCountByYear(@RequestBody CardStatisticsBean request) {
        try {
            List<CardStatisticsVO> list = superviseStatisticsService.getApplyCountByYear(request);
            if (list.size()>0) {
                CardStatisticsReturnVO returnVO = new CardStatisticsReturnVO();
                returnVO.setIndex(11);
                returnVO.setObject(list);
                logger.info("查询成功11!");
                return ok("查询成功！", returnVO);
            } else {
                logger.error("暂无数据!");
                return error("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }


}
