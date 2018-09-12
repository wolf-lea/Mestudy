package com.tecsun.sisp.fakamanagement.modules.task;

import com.tecsun.sisp.fakamanagement.common.Config;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardInfoVO;
import com.tecsun.sisp.fakamanagement.modules.service.impl.card.CardInfoServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.util.ThreadPoolUtil;
import com.tecsun.sisp.fakamanagement.outerface.cardservice.CardServiceUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Fangyugui on 2018/4/12.
 */

@Controller
@RequestMapping(value = "/faka/synchrTask")
public class SynchrFakaToCardTask extends BaseController{

    public final static Logger logger = Logger.getLogger(SynchrFakaToCardTask.class);
    public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

    @Autowired
    private CardInfoServiceImpl cardInfoService;

    @RequestMapping(value = "/synchrFakaToCard", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public void synchrFakaToCard(){
        logger.info("============="+ dateFormat.format(new Date())+"（定时器）发卡同步 开启 ====================");
        List<CardInfoVO> cardInfoList = new ArrayList<>();
        try {
            logger.info("开始查询未发卡数据===");
            cardInfoList =  cardInfoService.getNoFakaCardInfo();
            logger.info("查询未发卡数据成功===");
        } catch (Exception e) {
            e.printStackTrace();
            String msg="";
            if (null != e.getCause()) {
                msg = e.getCause().getMessage();
            } else {
                msg = e.getMessage();
            }
            logger.error(dateFormat.format(new Date()) + " (定时接口)获取未发卡数据 异常:" + msg);
            //return error("获取未发卡数据 异常:"+msg);
            return ;
        }
        logger.info(dateFormat.format(new Date()) + " (定时接口)本次需同步发卡数量===" + cardInfoList.size());

        if (cardInfoList.isEmpty()){
            logger.info(dateFormat.format(new Date()) + " (定时接口)本次同步发卡数量为===" +cardInfoList.size()+ ",已结束");
            //return ok("本次同步发卡数量为;" +cardInfoList.size()+ ",已结束");
            return;
        }

        int count = Integer.valueOf(Config.getInstance().get("maxThreadNum")); //单线程最大处理量
        int runSize = cardInfoList.size()/count ;

        if(count*runSize < cardInfoList.size()){
            runSize += 1;
        }
        for(int i = 0 ; i < runSize ;i++) {
            List<CardInfoVO> newList = new ArrayList<CardInfoVO>();
            for (int j = i * count; j < cardInfoList.size(); j++) {
                if (i == 0) {
                    if (j >= count) {
                        break;
                    }
                } else {
                    if (j >= (i + 1) * count) {
                        break;
                    }
                }
                newList.add(cardInfoList.get(j));
            }
            ThreadPoolUtil.getThreadPool().execute(new SynchrFakaThread(newList,cardInfoService));
        }
        logger.info(">>>>>>>>>>>>>>>>同步线程 已全部启动...正常同步中<<<<<<<<<<<<< ");

    }
}

    class SynchrFakaThread implements Runnable{
        private static Logger logger = Logger.getLogger(SynchrFakaThread.class);
        private List<CardInfoVO> list;
        private CardInfoServiceImpl cardInfoService;
        public static final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss SSS");

        public SynchrFakaThread(List<CardInfoVO> list,CardInfoServiceImpl cardInfoService){
            this.list = list;
            this.cardInfoService=cardInfoService;
        }

        public void run() {
            //logger.info("开始匹配数据===");
            int num = 0;
            try {
                for (CardInfoVO vo : list){
                    num++;
                    logger.info("已匹配数据"+num+"条========");

                    //不同步公共网点的数据
                    //if (!"123456".equals(vo.getFkwd())) {
                        //logger.info("开始同步非公共网点的数据===");
                        if (StringUtils.isBlank(vo.getReginalcode())) {
                            vo.setReginalcode(vo.getIdcard().substring(0, 4) + "00");
                        }
                        //调取省厅 制卡进度查询
                        //logger.info("开始调取省厅制卡进度查询接口===");
                        String xml = CardServiceUtils.invoke("getAZ03", vo.getIdcard(), vo.getName(), null, vo.getReginalcode());
                        //logger.info("调用结果："+xml);
                        Element rootElement = DocumentHelper.parseText(xml).getRootElement();
                        Element err = rootElement.element("ERR");
                        if (err != null) {
                            if (err.getText().equals("OK")) {
                                //省厅返回成功 获取 字段GetTime 若不为空 则已发卡
                                //省厅已发卡 需修改发卡系统状态 同步发卡状态
                                Element getTime = rootElement.element("GETTIME");
                                if (!StringUtils.isBlank(getTime.getText())) {
                                    cardInfoService.updataFakaStatus(vo);
                                    logger.info("身份证:" + vo.getIdcard() + ",姓名:" + vo.getName() + " 已同步发卡状态");
                                }
                            } else {
                                logger.info("身份证:" + vo.getIdcard() + ",姓名:" + vo.getName() + ",调用省厅接口失败===" + err.getText());
                            }
                        } else {
                            logger.info("身份证:" + vo.getIdcard() + ",姓名:" + vo.getName() + ",查无此人数据");
                        }

                    //}

                }
            } catch (Exception e) {
                e.printStackTrace();
                String msg = "";
                if (null != e.getCause()) {
                    msg = e.getCause().getMessage();
                } else {
                    msg = e.getMessage();
                }
                logger.error(dateFormat.format(new Date()) + " (定时线程接口)轮询同步发生异常,已中止:" + msg);
                return;
            }
            logger.info(dateFormat.format(new Date()) + " (定时线程接口)本线程执行结束，已处理了:"+list.size());
            return ;
        }
    }

