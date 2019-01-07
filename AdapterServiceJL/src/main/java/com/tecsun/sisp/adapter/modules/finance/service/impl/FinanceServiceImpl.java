package com.tecsun.sisp.adapter.modules.finance.service.impl;

import com.tecsun.sisp.adapter.common.util.JsonHelper;
import com.tecsun.sisp.adapter.modules.finance.entity.request.DeviceRelationBean;
import com.tecsun.sisp.adapter.modules.finance.entity.request.FinanceTradeBean;
import com.tecsun.sisp.adapter.modules.finance.entity.response.DeviceRelationVO;
import com.tecsun.sisp.adapter.modules.finance.entity.response.PhoneChargeVO;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 */
@Service("FinanceService")
public class FinanceServiceImpl extends BaseService{
    public static final Logger logger = Logger.getLogger(FinanceServiceImpl.class);

    public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.finance.service.impl.FinanceServiceImpl.";


    //新增金融交易信息
    public int insertFinanceTradeInfo4Cssp(FinanceTradeBean bean) throws Exception {
        int status = 0;
        try {
            status = getSqlSessionTemplate().insert(NAMESPACE + "insertFinanceTradeInfo", bean);
        } catch (Exception e) {
            logger.error("新增金融交易信息失败" + JsonHelper.javaBeanToJson(bean), e);
            status = -2;
        }
        return status;
    }
    //存入手机充值表
    public int insertPhoneCharge4Cssp(PhoneChargeVO bean) throws Exception {
        int status = 0;
        try {
            status = this.getSqlSessionTemplate().insert(NAMESPACE + "insertPhoneCharge", bean);
        } catch (Exception e) {
            logger.error("存入手机充值表失败" + JsonHelper.javaBeanToJson(bean), e);
            status = -2;
        }
        return status;
    }

    //修改金融交易信息
    public int updateFinanceTradeInfo4Cssp(FinanceTradeBean bean)throws Exception {
    	return  this.getSqlSessionTemplate().update(NAMESPACE + "updateFinanceTradeInfo", bean);

    }
    /**
     * 查询金融交易信息
     * @param
     * @return
     */
    public List<FinanceTradeBean>selectFinaceTradeInfo4Cssp(FinanceTradeBean bean) throws  Exception{
        List<FinanceTradeBean> list=getSqlSessionTemplate().selectList(NAMESPACE + "selectFinaceTradeInfo", bean);
        return list;
    }

    /**
     * 查询设备多终端多商户关系
     * @param
     * @return
     */
    public List<DeviceRelationVO>selectDeviceRelation4Sisp(DeviceRelationBean bean) throws  Exception{
        List<DeviceRelationVO> list=getSqlSessionTemplate().selectList(NAMESPACE + "selectDeviceRelation", bean);
        return list;
    }


}
