package com.tecsun.sisp.fakamanagement.modules.service.impl.temporaryCard;

import com.alibaba.fastjson.JSON;
import com.tecsun.sisp.fakamanagement.common.CommUtil;
import com.tecsun.sisp.fakamanagement.common.Constants;
import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard.CardCountBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.temporaryCard.TempStatisticsBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.common.DistinctAndBankVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.ORGVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard.CardCountVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.temporaryCard.TempStatisticsVO;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by chenlicong on 2018/3/21.
 */
@Service("tempStatisticsServiceImpl")
public class TempStatisticsServiceImpl extends BaseService{

    /**
     * mapper文件namespace属性值
     */
    public static final String NAMESPACE = "com.tecsun.sisp.fakamanagement.modules.service.impl.temporaryCard.TempStatisticsServiceImpl.";
    @Autowired
    private LoginUserServiceImpl loginUserService;
    public final static Logger logger = Logger.getLogger(TempStatisticsServiceImpl.class);

    //临时卡市人社统计
    public Page<TempStatisticsVO> getTempCount(Page<TempStatisticsVO> page,TempStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        req.setPage(page);
        List<TempStatisticsVO> list = template.selectList(NAMESPACE + "getTempCount", req);
        //计算未回收数量
        for (TempStatisticsVO vo : list){
            int notRecoveryNum = Integer.valueOf(vo.getCardGrantNum())-Integer.valueOf(vo.getRecoveryNum())-Integer.valueOf(vo.getCancelNum());
            vo.setNotRecoveryNum(String.valueOf(notRecoveryNum));
        }
        page.setData(list);
        return page;
    }

    //临时卡区县人社统计
    public Page<TempStatisticsVO> getTempDistinctCount(Page<TempStatisticsVO> page,TempStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        req.setPage(page);
        List<TempStatisticsVO> list = template.selectList(NAMESPACE + "getTempDistinctCount", req);
        //计算未回收数量
        for (TempStatisticsVO vo : list){
            int notRecoveryNum = Integer.valueOf(vo.getCardGrantNum())-Integer.valueOf(vo.getRecoveryNum())-Integer.valueOf(vo.getCancelNum());
            vo.setNotRecoveryNum(String.valueOf(notRecoveryNum));
        }
        page.setData(list);
        return page;
    }

    //临时卡银行统计
    public Page<TempStatisticsVO> getTempBankCount(Page<TempStatisticsVO> page,TempStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        req.setPage(page);
        List<TempStatisticsVO> list = template.selectList(NAMESPACE + "getTempBankCount", req);
        //计算未回收数量
        for (TempStatisticsVO vo : list){
            int notRecoveryNum = Integer.valueOf(vo.getCardGrantNum())-Integer.valueOf(vo.getRecoveryNum())-Integer.valueOf(vo.getCancelNum());
            vo.setNotRecoveryNum(String.valueOf(notRecoveryNum));
        }
        page.setData(list);
        return page;
    }

    //临时卡网点统计
    public Page<TempStatisticsVO> getTempBranchCount(Page<TempStatisticsVO> page,TempStatisticsBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        req.setPage(page);
        List<TempStatisticsVO> list = template.selectList(NAMESPACE + "getTempBranchCount", req);
        //计算未回收数量
        for (TempStatisticsVO vo : list){
            int notRecoveryNum = Integer.valueOf(vo.getCardGrantNum())-Integer.valueOf(vo.getRecoveryNum())-Integer.valueOf(vo.getCancelNum());
            vo.setNotRecoveryNum(String.valueOf(notRecoveryNum));
        }
        page.setData(list);
        return page;
    }
    //市人社 区县人社 银行统计  传统方法 比较慢
   /* public Page<CardCountVO> getCardCount(Page<CardCountVO> page, CardCountBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        req.setPage(page);
        //制卡成功数量
        List<CardCountVO> makeCarList = template.selectList(NAMESPACE + "getMakeCardCount", req);
        //发卡成功数量
        List<CardCountVO> grandCardlist  = new ArrayList<>();
        //临时卡发卡数量
        List<CardCountVO> tempCardlist = new ArrayList<>();
        for(int i=0;i<makeCarList.size();i++){

            makeCarList.get(i).setNowGrantCardCount(0);
            CardCountVO cardCountVO=makeCarList.get(i);
            DistinctAndBankVO distinctAndBankVO = loginUserService.getFkwdById(cardCountVO.getId());
            if (null == distinctAndBankVO) {
                logger.error("获取该网点机构信息为空！");
                return page;
            }
            makeCarList.get(i).setOrgName(distinctAndBankVO.getName());

            CardCountBean cardCountBean=new CardCountBean();
            cardCountBean.setBranchId(cardCountVO.getId());
            grandCardlist = template.selectList(NAMESPACE + "getGrantCardCount", cardCountBean);
            if(null!=grandCardlist && grandCardlist.size()>0){
                makeCarList.get(i).setGrantCardCount(grandCardlist.get(0).getGrantCardCount());
            }else{
                makeCarList.get(i).setGrantCardCount(0);
            }
            tempCardlist = template.selectList(NAMESPACE + "getTempGrantCard", cardCountBean);
            if(null!=tempCardlist && tempCardlist.size()>0){
                makeCarList.get(i).setTempGrantCardCount(tempCardlist.get(0).getTempGrantCardCount());
            }else{
                makeCarList.get(i).setTempGrantCardCount(0);
            }
        }
        page.setData(makeCarList);
        return page;
    }*/

    /*//市人社 区县人社 银行统计  版本二
    public Page<CardCountVO> getCardCount(Page<CardCountVO> page, CardCountBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        req.setPage(page);
        //制卡成功数量
        List<CardCountVO> makeCarList = template.selectList(NAMESPACE + "getMakeCardCount", req);
        if(null!=makeCarList && makeCarList.size()>0){
            //发卡成功数量
            List<CardCountVO> grandCardlist  = template.selectList(NAMESPACE + "getGrantCardCount", req);
            //临时卡发卡数量
            List<CardCountVO> tempCardlist = template.selectList(NAMESPACE + "getTempGrantCard", req);
            Map<String,String> gradMap=new HashMap<>();
            Map<String,String> tempMap=new HashMap<>();
            if(null==grandCardlist){
                grandCardlist=new ArrayList<>();
            }
            if(null==tempCardlist){
                tempCardlist=new ArrayList<>();
            }
            CommUtil commUtil=new CommUtil();
            gradMap= commUtil.beanListToMap(grandCardlist);
            tempMap= commUtil.beanListToMap(tempCardlist);
            for(int i=0;i<makeCarList.size();i++){
                makeCarList.get(i).setNowGrantCardCount(0);
                CardCountVO cardCountVO=makeCarList.get(i);
                DistinctAndBankVO distinctAndBankVO = loginUserService.getFkwdById(cardCountVO.getId());
                if (null == distinctAndBankVO) {
                    logger.error("获取该网点机构信息为空！");
                    return page;
                }
                makeCarList.get(i).setOrgName(distinctAndBankVO.getName());
                Integer grandCount=0;
                Integer tempCount= 0;
                String grand=gradMap.get(String.valueOf(cardCountVO.getId()));
                String temp=tempMap.get(String.valueOf(cardCountVO.getId()));
                if(!StringUtils.isEmpty(grand)){
                    grandCount= Integer.valueOf(grand);
                }
                if(!StringUtils.isEmpty(temp)){
                    tempCount= Integer.valueOf(temp);
                }
                makeCarList.get(i).setGrantCardCount(grandCount);
                makeCarList.get(i).setTempGrantCardCount(tempCount);
            }
            page.setData(makeCarList);
            return page;
        }else{
            logger.error("获取该网点制卡数量为空！");
            return page;
        }

    }*/
    //市人社 区县人社 银行统计  版本三
    public Page<CardCountVO> getCardCount(CardCountBean req,Page<CardCountVO> p, List orgCodes, List orgIds)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
//        /req.setPage(page);
        CommUtil commUtil=new CommUtil();
        //制卡量map
        Map<String,String> makeMap=new HashMap<>();
        //发卡量map
        Map<String,String> gradMap=new HashMap<>();
        //临时卡发卡量map
        Map<String,String> tempMap=new HashMap<>();
        req.setOrgCodes(orgCodes);
        req.setOrgIds(orgIds);
        if(StringUtils.isNotEmpty(req.getBeginTime())){
            req.setBeginTime(req.getBeginTime()+" 00:00:00");
        }if(StringUtils.isNotEmpty(req.getEndTime())){
            req.setEndTime(req.getEndTime()+" 23:59:59");
        }
        //制卡成功数量
        List<CardCountVO> makeCarList = template.selectList(NAMESPACE + "getMakeCardCount", req);
        //发卡数量
        List<CardCountVO> grandCardlist  =new ArrayList<>();
        //临时卡发卡数量
        List<CardCountVO> tempCardlist = template.selectList(NAMESPACE + "getTempGrantCard", req);
        if(null!=tempCardlist&& tempCardlist.size()>0){
            tempMap= commUtil.beanListToMap(tempCardlist);
        }
        //制卡不为空  查询发卡量
        /*if(null!=makeCarList && makeCarList.size()>0){
            makeMap=commUtil.beanListToMap(makeCarList);
            //发卡成功数量
            grandCardlist  = template.selectList(NAMESPACE + "getGrantCardCount", req);
           if(null!=grandCardlist && grandCardlist.size()>0){  //发卡不为空 则统计两个list的结果为一个list 转map
                gradMap=commUtil.beanListToMap(grandCardlist);
            }
        }*/
        makeMap=commUtil.beanListToMap(makeCarList);
        //发卡成功数量
        grandCardlist  = template.selectList(NAMESPACE + "getGrantCardCount", req);
        if(null!=grandCardlist && grandCardlist.size()>0){  //发卡不为空 则统计两个list的结果为一个list 转map
            gradMap=commUtil.beanListToMap(grandCardlist);
        }
        List<CardCountVO> orgvos=p.getData();
        for(int j=0;j<orgvos.size();j++){
            Integer makeCount=0;
            Integer grandCount=0;
            Integer tempCount= 0;
            CardCountVO cardCountVO=orgvos.get(j);
            String key=String.valueOf(cardCountVO.getId());
            String make=makeMap.get(key);
            if(!StringUtils.isEmpty(make)){
                makeCount= Integer.valueOf(make);
            }
            String grand=gradMap.get(key);
            if(!StringUtils.isEmpty(grand)){
                grandCount= Integer.valueOf(grand);
            }
            String temp=tempMap.get(key);
            if(!StringUtils.isEmpty(temp)){
                tempCount= Integer.valueOf(temp);
            }
            cardCountVO.setMakeCardCount(makeCount);
            cardCountVO.setGrantCardCount(grandCount);
            cardCountVO.setTempGrantCardCount(tempCount);
            cardCountVO.setNowGrantCardCount(0);
            orgvos.set(j,cardCountVO);
        }
        p.setData(orgvos);
        return p;
    }

    //网点统计
    public Page<CardCountVO> getBranchCount(Page<CardCountVO> page, CardCountBean req,ORGVO orgvo)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        req.setPage(page);
        if(StringUtils.isNotEmpty(req.getBeginTime())){
            req.setBeginTime(req.getBeginTime()+" 00:00:00");
        }if(StringUtils.isNotEmpty(req.getEndTime())){
            req.setEndTime(req.getEndTime()+" 23:59:59");
        }
        //制卡成功数量
        List<CardCountVO> makeCarList = template.selectList(NAMESPACE + "getBranchMakeCardCount", req);
        CardCountVO cardCountVO=new CardCountVO();
        if(null!=makeCarList && makeCarList.size()>0){
            cardCountVO=makeCarList.get(0);
            cardCountVO.setOrgName(orgvo.getOrgname());
            cardCountVO.setNowGrantCardCount(0);
            Integer makeCount=cardCountVO.getMakeCardCount();
            cardCountVO.setMakeCardCount(makeCount);

        }else{
            makeCarList=new ArrayList<>(1);
            cardCountVO.setOrgName(orgvo.getOrgname());
            cardCountVO.setMakeCardCount(0);
            cardCountVO.setGrantCardCount(0);
            cardCountVO.setNowGrantCardCount(0);
        }
        //发卡成功数量
        List<CardCountVO> grandCardlist = template.selectList(NAMESPACE + "getBranchGrantCardCount", req);
        if(null!=grandCardlist && grandCardlist.size()>0){
            CardCountVO grand=grandCardlist.get(0);
            Integer grandCount=grand.getGrantCardCount();
            cardCountVO.setGrantCardCount(grandCount);
        }else{
            cardCountVO.setGrantCardCount(0);
        }
        //临时卡发卡成功数量
        List<CardCountVO> tempCardlist = template.selectList(NAMESPACE + "getBranchTempGrantCardCount", req);
        if(null!=tempCardlist && tempCardlist.size()>0){
            CardCountVO temp=tempCardlist.get(0);
            Integer tempCount=temp.getTempGrantCardCount();
            cardCountVO.setTempGrantCardCount(tempCount);
        }else{
            cardCountVO.setTempGrantCardCount(0);
        }
        if(makeCarList.size()==0){
            makeCarList.add(0,cardCountVO);
        }else{
            makeCarList.set(0,cardCountVO);
        }
        page.setData(makeCarList);
        return page;

    }

    //按区县统计制卡数量 发卡数量 临时卡发卡数量
    public Page<CardCountVO> getCardCountByDistinct(Page<CardCountVO> page,CardCountBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        req.setPage(page);
        List<CardCountVO> list = template.selectList(NAMESPACE + "getCardCountByDistinct", req);
        for (CardCountVO vo : list){
            if (vo.getTempGrantCardCount() == null){
                vo.setTempGrantCardCount(0);
            }
        }
        page.setData(list);
        return page;
    }

    //按银行统计制卡数量 发卡数量 临时卡发卡数量
    public Page<CardCountVO> getCardCountByBank(Page<CardCountVO> page,CardCountBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        req.setPage(page);
        List<CardCountVO> list = template.selectList(NAMESPACE + "getCardCountByBank", req);
        for (CardCountVO vo : list){
            if (vo.getTempGrantCardCount() == null){
                vo.setTempGrantCardCount(0);
            }
        }
        page.setData(list);
        return page;
    }

    //查询银行编码和区县编码
    public CardCountVO getBankOrDistinctByUserId(CardCountBean req)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        CardCountVO vo = template.selectOne(NAMESPACE + "getBankOrDistinctByUserId", req);
        return vo;
    }

}
