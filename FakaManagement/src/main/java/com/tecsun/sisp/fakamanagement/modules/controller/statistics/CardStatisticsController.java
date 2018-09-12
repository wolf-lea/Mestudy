package com.tecsun.sisp.fakamanagement.modules.controller.statistics;

import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.statistics.CardStatisticsBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.supervise.ChannelStatisticsBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.statistics.SettleStatisticsBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.common.DistinctAndBankVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.CardStatisticsReturnVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.CardStatisticsVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.supervise.UserFunctionVO;
import com.tecsun.sisp.fakamanagement.modules.service.impl.channel.ChannelStatisticsServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.settle.SettleStatisticsServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.statistics.CardStatisticsServiceImpl;
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
 * Created by chenlicong on 2017/12/22.
 */
@Controller
@RequestMapping(value = "/faka/cardStatistics")
public class CardStatisticsController extends BaseController{

    public final static Logger logger = Logger.getLogger(CardStatisticsController.class);

    @Autowired
    private CardStatisticsServiceImpl cardStatisticsService;
    @Autowired
    private ChannelStatisticsServiceImpl channelStatisticsService;
    @Autowired
    private SettleStatisticsServiceImpl settleStatisticsService;
    @Autowired
    private LoginUserServiceImpl loginUserService;

    //获取分配给用户的统计功能
    @RequestMapping(value = "/getFunctionByUserId", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getFunctionByUserId(@RequestBody UserFunctionVO request) {
        try {
            List<UserFunctionVO> list = cardStatisticsService.getFunctionByUserId(request);
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
            int i = cardStatisticsService.updateFuncSortById(list);
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

    //各渠道应用功能使用数量统计
    @RequestMapping(value = "/getCountByChannel", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getChannelDataset(@RequestBody ChannelStatisticsBean request){
        try {
            List<ChannelStatisticsBean> list = channelStatisticsService.getCountByChannel(request);
            CardStatisticsReturnVO returnVO = new CardStatisticsReturnVO();
            List<Object> listVO = new ArrayList<>();
            if (list.size() > 0) {
                List<ChannelStatisticsBean> list1 = new ArrayList<>();
                List<ChannelStatisticsBean> list2 = new ArrayList<>();
                List<ChannelStatisticsBean> list3 = new ArrayList<>();
                for (ChannelStatisticsBean bean : list){
                    DistinctAndBankVO vo = new DistinctAndBankVO();
                    vo.setCode(bean.getChannelcode());
                    List<DistinctAndBankVO> channelList = loginUserService.queryChannelByCode(vo);//翻译渠道名称
                    if ("WeChat".equals(bean.getChannelcode())){
                        if (channelList.size()>0) {
                            bean.setChannelcode(channelList.get(0).getName());
                        }
                        list1.add(bean);
                    }else if ("Alipay".equals(bean.getChannelcode())){
                        if (channelList.size()>0) {
                            bean.setChannelcode(channelList.get(0).getName());
                        }
                        list2.add(bean);
                    }else if ("TSB".equals(bean.getChannelcode())){
                        if (channelList.size()>0) {
                            bean.setChannelcode(channelList.get(0).getName());
                        }
                        list3.add(bean);
                    }

                }
                listVO.add(list1);
                listVO.add(list2);
                listVO.add(list3);
                returnVO.setIndex(1);
                returnVO.setList(listVO);
                logger.info("查询成功1!");
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

    //全市卡量统计
    @RequestMapping(value = "/getCardCountByCity", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardCountByCity(@RequestBody CardStatisticsBean request) {
        try {
            /*if ("".equals(request.getAccountId())){
                return error("缺少必要的参数！");
            }
            logger.info("传入的账号是：" + request.getAccountId());*/
            CardStatisticsVO res = cardStatisticsService.getCardCountByCity(request);
            if (res != null) {
                List<Object> list = new ArrayList<>();
                list.add(res);
                CardStatisticsReturnVO returnVO = new CardStatisticsReturnVO();
                returnVO.setIndex(2);
                returnVO.setList(list);
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

    //全市每年发卡量统计
    @RequestMapping(value = "/getCardCountByYear", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardCountByYear(@RequestBody CardStatisticsBean request) {
        try {
            /*if ("".equals(request.getAccountId())){
                return error("缺少必要的参数！");
            }
            logger.info("传入的账号是：" + request.getAccountId());*/
            List<CardStatisticsVO> list = cardStatisticsService.getCardCountByYear(request);
            if (list.size()>0) {
                CardStatisticsReturnVO returnVO = new CardStatisticsReturnVO();
                returnVO.setIndex(3);
                returnVO.setList(list);
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

    //各区县已、未发量统计
    @RequestMapping(value = "/getCardCountByDistrict", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardCountByDistrict(@RequestBody CardStatisticsBean request) {
        try {
            /*if ("".equals(request.getAccountId())){
                return error("缺少必要的参数！");
            }
            logger.info("传入的账号是：" + request.getAccountId());*/
            List<CardStatisticsVO> list = cardStatisticsService.getCardCountByDistrict(request);
            if (list.size()>0) {
                for (CardStatisticsVO vo : list) {
                    DistinctAndBankVO distinctVO = new DistinctAndBankVO();
                    distinctVO.setCode(vo.getReginalcode());
                    List<DistinctAndBankVO> distinctList = loginUserService.queryDistinctByCode(distinctVO);//翻译区域名称
                    if (distinctList.size()>0) {
                        vo.setReginalcode(distinctList.get(0).getName());
                    }
                }
                CardStatisticsReturnVO returnVO = new CardStatisticsReturnVO();
                returnVO.setIndex(4);
                returnVO.setList(list);
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
    @RequestMapping(value = "/getCardCountByBank", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardCountByBank(@RequestBody CardStatisticsBean request) {
        try {
            /*if ("".equals(request.getAccountId())){
                return error("缺少必要的参数！");
            }
            logger.info("传入的账号是：" + request.getAccountId());*/
            List<CardStatisticsVO> list = cardStatisticsService.getCardCountByBank(request);
            if (list.size()>0) {
                int fakaCount = 0;
                int notfakaCount = 0;
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
                        fakaCount = fakaCount + Integer.parseInt(vo.getFakaCount());
                        notfakaCount = notfakaCount + Integer.parseInt(vo.getNotfakaCount());
                        num = 1;
                    }else {
                        list2.add(vo);
                    }
                }
                if (num == 1) {
                    vo2.setFakaCount(fakaCount + "");
                    vo2.setNotfakaCount(notfakaCount + "");
                    vo2.setBank("农商行");
                    list2.add(vo2);
                }
                CardStatisticsReturnVO returnVO = new CardStatisticsReturnVO();
                returnVO.setIndex(5);
                returnVO.setList(list2);
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

    //全市医保结算刷卡量统计
    @RequestMapping(value = "/insertYbjsInfoByTime", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Result insertYbjsInfoByTime() {
        try {
            logger.info("开始查询===");
            List<SettleStatisticsBean> list = settleStatisticsService.getSettleCountByMonth();
            logger.info("查询数据成功===");
            if (list.size()>0) {
                logger.info("开始插入数据===");
                int i = cardStatisticsService.insertYbjsInfoByTime(list);
                if (i > 0) {
                    logger.info("临时表数据插入成功！");
                    return ok("操作成功！");
                } else {
                    logger.error("操作失败！");
                    return error("操作失败！");
                }
            }else {
                logger.error("查询失败！");
                return error("查询失败！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //各区县医保结算刷卡量统计
    @RequestMapping(value = "/insertYbjsInfoByDistrict", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Result insertYbjsInfoByDistrict() {
        try {
            logger.info("开始查询===");
            List<SettleStatisticsBean> list = settleStatisticsService.getSettleCountByDistrict();
            logger.info("查询数据成功===");
            if (list.size()>0) {
                logger.info("开始插入数据===");
                int i = cardStatisticsService.insertYbjsInfoByDistrict(list);
                if (i > 0) {
                    logger.info("临时表数据插入成功！");
                    return ok("操作成功！");
                } else {
                    logger.error("操作失败！");
                    return error("操作失败！");
                }
            }else {
                logger.error("查询失败！");
                return error("查询失败！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //全市医保结算刷卡量统计
    @RequestMapping(value = "/getSettleCountByMonth", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getSettleCountByMonth(@RequestBody SettleStatisticsBean request) {
        try {
            logger.info("开始查询===");
            List<SettleStatisticsBean> list = cardStatisticsService.getTempSettleCountByMonth(request);
            logger.info("查询数据成功===");
            if (list.size()>0) {
                CardStatisticsReturnVO returnVO = new CardStatisticsReturnVO();
                returnVO.setIndex(6);
                returnVO.setList(list);
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

    //各区县医保结算刷卡量统计
    @RequestMapping(value = "/getSettleCountByDistrict", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getSettleCountByDistrict(@RequestBody SettleStatisticsBean request) {
        try {
            logger.info("开始查询===");
            List<SettleStatisticsBean> list = cardStatisticsService.getTempSettleCountByDistrict(request);
            logger.info("查询数据成功===");
            if (list.size()>0) {
                CardStatisticsReturnVO returnVO = new CardStatisticsReturnVO();
                returnVO.setIndex(7);
                returnVO.setList(list);
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


   /* public static void  main(String[] args){
        try {

            logger.info("正在准备同步医保结算数据===");
            String baseUrl = "http://" + Config.getInstance().get("inner_proxy_ip") + ":" + Config.getInstance().get("inner_proxy_port") + Config.getInstance().get("platform_context_path");
            String url = baseUrl + Config.getInstance().get("ybjs_time_url");
            String jsonResult = (String) BaseController.getWebClient(url, String.class);
            logger.info("调用同步医保数据接口：" + jsonResult);
            Result result = JsonMapper.buildNormalMapper().fromJson(jsonResult, Result.class);
            if (Integer.parseInt(result.getStatusCode()) == 200) {
                logger.info("同步成功！");
            } else {
                logger.info("同步失败！");
            }
        }catch (Exception e){
            logger.error("===="+e);
        }
    }*/

}
