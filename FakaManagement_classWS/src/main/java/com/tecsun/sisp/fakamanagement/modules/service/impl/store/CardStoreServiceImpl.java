package com.tecsun.sisp.fakamanagement.modules.service.impl.store;

import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.modules.entity.param.store.CardStoreCountBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.store.CardStoreMergeBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.store.*;
import com.tecsun.sisp.fakamanagement.modules.service.BaseService;
import oracle.net.aso.s;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chenlicong on 2017/12/22.
 */
@Service("cardStoreServiceImpl")
public class CardStoreServiceImpl extends BaseService{

    /**
     * mapper文件namespace属性值
     */
    public static final String NAMESPACE = "com.tecsun.sisp.fakamanagement.modules.service.impl.store.CardStoreServiceImpl.";

    public final static Logger logger = Logger.getLogger(CardStoreServiceImpl.class);

    //库存统计
    public Page<CardStoreTotalVO> getCardStoreCount(Page<CardStoreCountVO> page, CardStoreCountBean bean)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        Page<CardStoreTotalVO> result = new Page<>();
        List<CardStoreTotalVO> resultList = new ArrayList<>();
        bean.setPage(page);
        logger.info("开始统计库存===");
        List<CardStoreCountVO> list = template.selectList(NAMESPACE + "getCardStoreCount", bean);
        logger.info("查询总量成功===");
        long count = page.getCount();
        page.setData(list);
        int cardTotal = 0;
        int fakaTotal = 0;
        int notfakaTotal = 0;
        CardStoreTotalVO resultVO = new CardStoreTotalVO();
        for (CardStoreCountVO vo : list){
            //查询盒数、卡总量、发卡量
            bean.setCcid(vo.getCcid());
            logger.info("查询盒数===");
            CardStoreCountVO boxCountVo = template.selectOne(NAMESPACE + "getBoxCount", bean);
            if (boxCountVo != null) {
                vo.setBoxCount(boxCountVo.getBoxCount());
            }
            logger.info("查询卡总量===");
            CardStoreCountVO cardCountVo = template.selectOne(NAMESPACE + "getCardCount", bean);
            if (cardCountVo != null) {
                vo.setCardCount(cardCountVo.getCardCount());
            }
            logger.info("查询发卡量===");
            CardStoreCountVO fakaCountVo = template.selectOne(NAMESPACE + "getFakaCount", bean);
            if (fakaCountVo != null) {
                vo.setFakaCount(fakaCountVo.getFakaCount());
            }

            cardTotal = cardTotal + vo.getCardCount();
            fakaTotal = fakaTotal + vo.getFakaCount();
            vo.setNotfakaCount(vo.getCardCount() - vo.getFakaCount());
            notfakaTotal = notfakaTotal + vo.getNotfakaCount();
        }
        resultVO.setCardTotal(cardTotal);
        resultVO.setFakaTotal(fakaTotal);
        resultVO.setNotfakaTotal(notfakaTotal);
        resultVO.setList(list);
        resultList.add(resultVO);
        result.setData(resultList);
        result.setPageno(page.getPageno());
        result.setPagesize(page.getPagesize());
        result.setCount(count);
        logger.info("完成统计库存===");
        return result;
    }

    //库存明细
    public Page<CardStoreInfoVO> getCardStoreInfo(Page<CardStoreInfoVO> page, CardStoreCountBean bean)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        bean.setPage(page);
        logger.info("开始统计库存明细===");
        List<CardStoreInfoVO> list = template.selectList(NAMESPACE + "getCardStoreInfo", bean);
        logger.info("查询总量成功===");
        long count = page.getCount();
        /*List<CardStoreInfoVO> list2 = new ArrayList<>();
        for (CardStoreInfoVO vo : list){
            //查询卡总量、发卡量
            if (vo.getBox() == "" || vo.getBox() == null ){
               logger.info("卡位置入参为空===");
            }
            bean.setBatchNo(vo.getBatchNo());
            bean.setCcid(vo.getCcid());
            bean.setBin(vo.getBin());
            bean.setBox(vo.getBox());
            logger.info("查询卡总量===");
            CardStoreInfoVO cardCountVo = template.selectOne(NAMESPACE + "getCardCountInfo", bean);
            if (cardCountVo != null) {
                vo.setCardCount(cardCountVo.getCardCount());
            }
            logger.info("查询发卡量===");
            CardStoreInfoVO fakaCountVo = template.selectOne(NAMESPACE + "getFakaCountInfo", bean);
            if (fakaCountVo != null) {
                vo.setFakaCount(fakaCountVo.getFakaCount());
            }

            if (vo.getCardCount()!=0) {
                vo.setNotfakaCount(vo.getCardCount() - vo.getFakaCount());
                //list2.add(vo);
            }
            if ("".equals(bean.getCardNum())){
                list2.add(vo);
            }else {//合并查询时筛选“小于/等于”输入卡数量的数据
                if (vo.getNotfakaCount()<=Integer.valueOf(bean.getCardNum())){
                    list2.add(vo);
                }
            }
        }*/
        page.setCount(count);
        page.setData(list);
        //page.setData(list2);
        logger.info("完成统计库存明细===");
        return page;
    }
    //通过批次号查询箱号盒号
    public List<CardStoreInfoVO> getBinBoxByBatch(CardStoreCountBean bean)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        List<CardStoreInfoVO> list = template.selectList(NAMESPACE + "getBinBoxByBatch", bean);
        return list;
    }

    //库存详情
    public Page<CardStoreDetailVO> getCardStoreDetail(Page<CardStoreDetailVO> page, CardStoreCountBean bean)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        bean.setPage(page);
        List<CardStoreDetailVO> list = template.selectList(NAMESPACE + "getCardStoreDetail", bean);
        page.setData(list);
        return page;
    }

    //查询选择合并到的对应盒子的最大卡序号
    public CardStoreMergeVO getMaxCsid(CardStoreMergeBean bean)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        CardStoreMergeVO vo = template.selectOne(NAMESPACE + "getMaxCsid", bean);
        return vo;
    }
    //查询需要合并的盒子里的卡ID
    public List<CardStoreMergeVO> getCiidFromCardStore(CardStoreMergeBean bean)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        List<CardStoreMergeVO> list = template.selectList(NAMESPACE + "getCiidFromCardStore", bean);
        return list;
    }
    //修改合并卡对应的卡序号、柜号、箱号、盒号
    public int updateCardStoreInfoByCiid(CardStoreMergeBean bean)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        int i = template.update(NAMESPACE + "updateCardStoreInfoByCiid", bean);
        return i;
    }
    //修改卡数据的批次和系统位置
    public int updateCardInfoByCiid(CardStoreMergeBean bean)throws Exception{
        SqlSessionTemplate template = this.getFakaSqlSessionTemplate();
        int i = template.update(NAMESPACE + "updateCardInfoByCiid", bean);
        return i;
    }




}
