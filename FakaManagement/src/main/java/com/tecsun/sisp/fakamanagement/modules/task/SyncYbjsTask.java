package com.tecsun.sisp.fakamanagement.modules.task;

import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.statistics.SettleStatisticsBean;
import com.tecsun.sisp.fakamanagement.modules.service.impl.settle.SettleStatisticsServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.statistics.CardStatisticsServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by chenlicong on 2018/5/25.
 */

@Controller
@RequestMapping(value = "/faka/syncYbjs")
public class SyncYbjsTask extends BaseController {

    public final static Logger logger = Logger.getLogger(SyncYbjsTask.class);
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    @Autowired
    private SettleStatisticsServiceImpl settleStatisticsService;
    @Autowired
    private CardStatisticsServiceImpl cardStatisticsService;

    @RequestMapping(value = "/SynchrYbjsDate", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Result SynchrYbjsDate(){
        logger.info("============="+ dateFormat.format(new Date())+"（定时器）医保刷卡数据同步 开启 ====================");
        List<SettleStatisticsBean> list;
        int i = 0;
        try {
            /*logger.info("正在准备同步医保刷卡数据===");
            String url = Config.getInstance().get("ybjs_time_url");
            url = HttpClientUtil.getHost(url);
            //String jsonResult = (String) BaseController.getWebClient(url, String.class);
            String jsonResult = HttpClientUtil.getHttpData(url, null, false);
            logger.info("同步医保刷卡数据接口调用结果：" + jsonResult);
            Result result = JsonMapper.buildNormalMapper().fromJson(jsonResult, Result.class);*/

            logger.info("开始查询医保刷卡数据===");
            list = settleStatisticsService.getSettleCountByMonth();
            if (list.size()>0) {
                logger.info("开始插入医保刷卡数据===");
                i = cardStatisticsService.insertYbjsInfoByTime(list);
                if (i>0) {
                    logger.info("插入医保刷卡数据成功！");
                    return ok("同步医保刷卡数据成功===");
                }else {
                    logger.info("插入医保刷卡数据失败！");
                    return error("同步医保刷卡数据失败===");
                }
            } else {
                logger.info("查询医保刷卡数据失败！");
                return error("查询医保刷卡数据失败===");
            }
        } catch (Exception e) {
            logger.error(dateFormat.format(new Date()) + " (定时接口)同步医保刷卡数据，发生异常:" + e);
            return error("同步医保刷卡数据，发生异常:"+e);
        }

    }



}
