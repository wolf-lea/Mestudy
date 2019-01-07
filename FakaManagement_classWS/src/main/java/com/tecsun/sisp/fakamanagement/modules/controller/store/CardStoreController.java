package com.tecsun.sisp.fakamanagement.modules.controller.store;

import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.store.CardStoreCountBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.store.CardStoreMergeBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.common.DistinctAndBankVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.store.*;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.store.CardStoreServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import java.util.ArrayList;
import java.util.List;

/**
 * 库存管理：统计、明细、详情、合并
 * Created by chenlicong on 2017/12/22.
 */
@Controller
@RequestMapping(value = "/faka/cardStore")
public class CardStoreController extends BaseController{

    public final static Logger logger = Logger.getLogger(CardStoreController.class);

    @Autowired
    private CardStoreServiceImpl cardStoreService;
    @Autowired
    private LoginUserServiceImpl loginUserService;

    //库存统计
    @RequestMapping(value = "/getCardStoreCount", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardStoreCount(@RequestBody CardStoreCountBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        String pagesize = request.getParameter("pagesize");
        if ("".equals(pagesize)){
            pagesize = "10";
        }
        String pageno = request.getParameter("pageno");
        if ("".equals(pageno)){
            pageno = "1";
        }
        //CardStoreTotalVO result = null;
        Page<CardStoreTotalVO> result;
        try {
            Page<CardStoreCountVO> page = new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
            String fkwd=loginUserService.getFkwd(bean.getFkwd());
            if(null==fkwd||fkwd.equals("")){
                return error("发卡机构获取编码为空");
            }
            bean.setFkwd(fkwd);//通过userid获取发卡机构编码
            logger.info("开始查询库存统计===");
            result = cardStoreService.getCardStoreCount(page,bean);
            if (result.getData().size()>0) {
                logger.info("查询成功!");
                return ok("查询成功！", result);
            } else {
                logger.info("暂无数据!");
                return ok("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //库存明细
    @RequestMapping(value = "/getCardStoreInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardStoreInfo(@RequestBody CardStoreCountBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        String pagesize = request.getParameter("pagesize");
        if ("".equals(pagesize)){
            pagesize = "10";
        }
        String pageno = request.getParameter("pageno");
        if ("".equals(pageno)){
            pageno = "1";
        }
        if (bean.getCardNum()==null){
            bean.setCardNum("");
        }

        //去除查询条件两端空格
        if (null!=bean.getBatchNo() && !"".equals(bean.getBatchNo())){
            bean.setBatchNo(bean.getBatchNo().trim());
        }
        if (null!=bean.getCardNum() && !"".equals(bean.getCardNum())){
            bean.setCardNum(bean.getCardNum().trim());
        }

        Page<CardStoreInfoVO> result = null;
        List<CardStoreInfoVO> list = new ArrayList<>();
        try {
            Page<CardStoreInfoVO> page = new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
            String fkwd=loginUserService.getFkwd(bean.getFkwd());
            if(null==fkwd||fkwd.equals("")){
                return error("发卡机构获取编码为空");
            }
            bean.setFkwd(fkwd);//通过userid获取发卡机构编码
            logger.info("开始查询库存明细===");
            result = cardStoreService.getCardStoreInfo(page, bean);
            logger.info("查询库存明细成功===");

            //合并查询过滤空盒子
            /*if ("1".equals(bean.getQueryType())){
                for (CardStoreInfoVO vo :result.getData()){
                    if (vo.getNotfakaCount()!=0){
                        list.add(vo);
                    }
                    *//*if (vo.getNotfakaCount()!=0 && "".equals(bean.getCardNum())){
                        list.add(vo);
                    }else {//合并查询时筛选“小于/等于”输入卡数量的数据
                        if (vo.getNotfakaCount()!=0 && vo.getNotfakaCount()<=Integer.valueOf(bean.getCardNum())){
                            list.add(vo);
                        }
                    }*//*
                }
                //result.setCount(list.size());
                result.setData(list);
            }*/

            if (result.getData().size()>0) {
                logger.info("查询成功===!");
                return ok("查询成功！", result);
            } else {
                logger.info("暂无数据!");
                return ok("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }
    //通过批次号查询箱号盒号
    @RequestMapping(value = "/getBinBoxByBatch", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getBinBoxByBatch(@RequestBody CardStoreCountBean bean) {
        try {
            List<CardStoreInfoVO> list = cardStoreService.getBinBoxByBatch(bean);
            if (list.size()>0) {
                logger.info("查询成功===!");
                return ok("查询成功！", list);
            } else {
                logger.info("暂无数据!");
                return ok("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //库存详情
    @RequestMapping(value = "/getCardStoreDetail", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardStoreDetail(@RequestBody CardStoreCountBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
        String pagesize = request.getParameter("pagesize");
        if ("".equals(pagesize)){
            pagesize = "10";
        }
        String pageno = request.getParameter("pageno");
        if ("".equals(pageno)){
            pageno = "1";
        }

        //去除查询条件两端空格
        if (null!=bean.getIdcard() && !"".equals(bean.getIdcard())){
            bean.setIdcard(bean.getIdcard().trim());
        }

        Page<CardStoreDetailVO> result = null;
        try {
            Page<CardStoreDetailVO> page = new Page<>(Integer.parseInt(pageno),Integer.parseInt(pagesize));
            result = cardStoreService.getCardStoreDetail(page, bean);
            if (result.getData().size()>0) {
                for (CardStoreDetailVO vo : result.getData()){
                    DistinctAndBankVO distinctVO = new DistinctAndBankVO();
                    distinctVO.setCode(vo.getFkwd());
                    List<DistinctAndBankVO> distinctList = loginUserService.queryDistinctByCode(distinctVO);//翻译网点名称
                    if (distinctList.size()>0) {
                        vo.setFkwd(distinctList.get(0).getName());
                    }
                }
                logger.info("查询成功===!");
                return ok("查询成功！", result);
            } else {
                logger.info("暂无数据!");
                return ok("暂无数据！");
            }
        } catch (Exception e) {
            logger.error("获取信息发生了异常：" + e);
            return error("获取信息失败！");
        }
    }

    //库存合并
    @RequestMapping(value = "/makeCardStoreMerge", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result makeCardStoreMerge(@RequestBody List<CardStoreMergeBean> list) {
        try {
            //判断选择合并的卡数量是否超出盒子最大容量
            int cardTotal=0;
            CardStoreMergeBean request = new CardStoreMergeBean();
            for (CardStoreMergeBean bean : list){
                cardTotal = cardTotal + bean.getNotfakaCount();//计算合并的卡数量
                if ("1".equals(bean.getType())) {
                    BeanUtils.copyProperties(bean,request);
                }
            }
            if (cardTotal > 250){
                logger.info("合并卡数量已超出盒子容量！");
                return error("合并卡数量已超出盒子容量！");
            }
            //查询选择合并到的对应盒子的最大卡序号
            int maxCsid = 0;
            CardStoreMergeVO vo = cardStoreService.getMaxCsid(request);
            if (vo.getMaxCsid() != 0) {
                maxCsid = vo.getMaxCsid();
            }
            maxCsid = maxCsid+1;//合并起始卡序号
            //遍历查询需要合并的盒子里的卡ID
            for (CardStoreMergeBean bean : list){
                if (!"1".equals(bean.getType())) {
                    List<CardStoreMergeVO> listVO = cardStoreService.getCiidFromCardStore(bean);
                    for (CardStoreMergeVO bean1 : listVO){
                        request.setCsid(maxCsid);
                        request.setCiid(bean1.getCiid());
                        //修改合并卡对应的卡序号、柜号、箱号、盒号
                        cardStoreService.updateCardStoreInfoByCiid(request);
                        //修改卡数据的批次和系统位置
                        String newZxwz =request.getBatchNo()+"-"+request.getBin()+"-"+request.getBox()+"-"+request.getCsid();
                        request.setNewZxwz(newZxwz);
                        cardStoreService.updateCardInfoByCiid(request);
                        maxCsid++;
                    }
                }
            }
            logger.info("合并成功!");
            return ok("合并成功！");
        } catch (Exception e) {
            logger.error("合并发生了异常：" + e);
            return error("合并失败！");
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
