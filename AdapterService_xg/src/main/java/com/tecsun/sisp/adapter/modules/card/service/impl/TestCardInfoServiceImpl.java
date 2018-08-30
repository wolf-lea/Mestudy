package com.tecsun.sisp.adapter.modules.card.service.impl;

import com.tecsun.sisp.adapter.modules.card.entity.request.CardInfoBean;
import com.tecsun.sisp.adapter.modules.card.entity.response.BankDataVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.CardInfoVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.CardProgressVO;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by danmeng on 2016/10/18.
 */
@Service("TestCardInfoServiceImpl")
public class TestCardInfoServiceImpl  extends BaseService {
    public static final Logger logger = Logger.getLogger(TestCardInfoServiceImpl.class);

    public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.card.service.impl.TestCardInfoServiceImpl.";

    public CardProgressVO getCardProgress4Other(CardInfoBean bean) {
        logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+"===================");
        List<CardProgressVO> list=this.getSqlSessionTemplate().selectList(NAMESPACE+"getCardProgress",bean);
        CardProgressVO vo=null;
        if(list!=null&&list.size()>0) vo=list.get(0);
//        获取测试数据
        return vo;
    }
    public CardInfoVO setLoss4Other(CardInfoBean bean) {
        logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+"===================");

        //获取测试数据
        return this.getSqlSessionTemplate().selectOne(NAMESPACE+"setLoss",bean);
    }
    public CardInfoVO setHanging4Other(CardInfoBean bean) {
        logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+"===================");

        //获取测试数据
        return this.getSqlSessionTemplate().selectOne(NAMESPACE+"setHanging",bean);
    }
    public CardInfoVO setStart4Other(CardInfoBean bean) {
        logger.info("===================sfzh:"+bean.getSfzh() + ",xm:"+bean.getXm()+"===================");

        //获取测试数据
        return this.getSqlSessionTemplate().selectOne(NAMESPACE+"setStart",bean);
    }



}
