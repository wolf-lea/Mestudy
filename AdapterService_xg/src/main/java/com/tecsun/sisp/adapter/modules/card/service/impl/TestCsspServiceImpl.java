package com.tecsun.sisp.adapter.modules.card.service.impl;

import com.tecsun.sisp.adapter.common.util.JsonHelper;
import com.tecsun.sisp.adapter.modules.card.entity.response.*;
import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by danmeng on 2016/10/21.
 */

@Service("TestCsspService")
public class TestCsspServiceImpl  extends BaseService {
    public static final Logger logger = Logger.getLogger(CsspServiceImpl.class);

    public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.card.service.impl.TestCsspServiceImpl.";


    public List<BankDataVO> getBankData4Other( BankDataVO vo) {
        //获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getBankData", vo);
    }

    public List< CsspPolicePhotoVO> getPolicePhotos4Other( SecQueryBean bean) {
        //获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"getPolicePhotos",bean);
    }

    //社保卡申请-存入制卡进度
    public int insertCardProgress4Other(CardProgressVO bean) throws Exception {
        int status = 0;
        try {
            status = this.getSqlSessionTemplate().insert(NAMESPACE + "insertCardProgress", bean);
        } catch (Exception e) {
            logger.error("存入制卡进度表失败" + JsonHelper.javaBeanToJson(bean), e);
            status = -2;
        }
        return status;
    }

    //社保卡申请-修改制卡进度
    public int updateCardProgress4Other(CardProgressVO vo){
        return this.getSqlSessionTemplate().update(NAMESPACE+"updateCardProgress",vo);

    }//社保卡申请-修改制卡进度
    public int updateCardProgressOfSfzh4Other(CardProgressVO vo){
        return this.getSqlSessionTemplate().update(NAMESPACE+"updateCardProgressOfSfzh",vo);

    }
}
