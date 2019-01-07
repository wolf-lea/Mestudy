package com.tecsun.sisp.adapter.modules.card.service.impl;

import com.tecsun.sisp.adapter.common.util.JsonHelper;
import com.tecsun.sisp.adapter.modules.card.entity.request.CardBasicBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.CardDetectBean;
import com.tecsun.sisp.adapter.modules.card.entity.response.CardBasicVO;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**卡检测服务
 */
@Service("DetectService")
public class DetectServiceImpl extends BaseService{
    public static final Logger logger = Logger.getLogger(DetectServiceImpl.class);

    public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.card.service.impl.DetectServiceImpl.";

    //模拟社保卡基本信息查询
    public CardBasicVO testGetCardBasicInfo4Other(CardBasicBean bean) throws Exception {
        List<CardBasicVO> list = this.getSqlSessionTemplate().selectList(NAMESPACE + "testGetCardBasicInfo", bean);
        if (list != null && list.size() > 0)
            return list.get(0);
        else return null;
    }

    //卡检测保存
    public long saveCardDect4Cssp(CardDetectBean bean) throws Exception {
        long status = 0;
        try {
            this.getSqlSessionTemplate().insert(NAMESPACE + "saveCardDect", bean);
            status = bean.getDetectId();
        } catch (Exception e) {
            logger.error("存入社保卡申请信息表失败" + JsonHelper.javaBeanToJson(bean), e);
            status = -2;
        }
        return status;
    }


    //卡修复保存：先查询是否存在卡检测
    public long saveCardRepair4Cssp(CardDetectBean bean) throws Exception {
        long status = 0;
        try {
            if (bean.getDetectId() != 0&& StringUtils.isNotBlank(bean.getSfzh()))
                status = this.getSqlSessionTemplate().selectOne(NAMESPACE + "isExistCardDect", bean);
            if (status == 1)//卡检测数据存在且唯一
                status = this.getSqlSessionTemplate().update(NAMESPACE + "saveCardRepair", bean);
        } catch (Exception e) {
            logger.error("卡修复保存失败" + JsonHelper.javaBeanToJson(bean), e);
            status = -2;
        }
        return status;
    }



    
}
