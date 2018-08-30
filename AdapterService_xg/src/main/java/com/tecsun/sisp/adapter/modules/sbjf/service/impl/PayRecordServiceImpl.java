package com.tecsun.sisp.adapter.modules.sbjf.service.impl;

import com.tecsun.sisp.adapter.common.util.*;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.sbjf.entity.PayRecordDetailEntity;
import com.tecsun.sisp.adapter.modules.sbjf.entity.PayRecordEntity;
import com.tecsun.sisp.adapter.modules.sbjf.entity.inputBean.CreateFlowNumBean;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by linzetian on 2017/6/6.
 */
@Service("PayRecordServiceImpl")
public class PayRecordServiceImpl extends BaseService {

    @Autowired
    PayServiceImpl payService;

    @Autowired
    GradeServiceImpl gradeService;

    public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.sbjf.service.impl.PayRecordServiceImpl.";

    public final static String KEY_OF_PRINTCOUNT = "sbjf:print:%s";
    public final static String PRINTCOUNT_TIMEOUT = Config.getInstance().get(Constants.SBJF_PRINT_TIMEOUT);
    public final static String PRINTCOUNT = Config.getInstance().get(Constants.SBJF_PRINT_COUNT);

    /**
     * 插入缴费记录
     * @param bean
     * @return
     */
    public boolean insertPayRecord4cssp(CreateFlowNumBean bean) throws Exception{
        int count =  this.getSqlSessionTemplate().insert(NAMESPACE + "insertPayRecord", bean);
        return count > 0 ? true : false;
    }

    /**
     * 插入缴费详细信息
     * @param prd
     * @return
     * @throws Exception
     */
    public boolean insertPayRecordDetail4cssp(PayRecordDetailEntity prd) throws Exception{
        System.out.println(prd.getPayId());
        int count = this.getSqlSessionTemplate().insert(NAMESPACE + "insertPayRecordDetail", prd);
        return  count > 0 ? true : false;
    }

    /**
     * 根据缴费订单号获取详情清单
     * @param flowNum
     * @return
     * @throws Exception
     */
    public List<PayRecordDetailEntity> payRecordDetailList4cssp(String flowNum) throws Exception{
        List<PayRecordDetailEntity>  resList = this.getSqlSessionTemplate().selectList(NAMESPACE + "payRecordDetailList", flowNum);
        return resList;
    }


    /**
     * 获取已缴费记录
     */
    public Page<PayRecordEntity> payRecordList4cssp(Map<String,Object> bean) throws Exception{
        bean = ParamUtil.addPage(bean);
        List<PayRecordEntity> res = this.getSqlSessionTemplate().selectList(NAMESPACE + "payRecordList", bean);

        Page<PayRecordEntity> page = ( Page<PayRecordEntity>)bean.get(Constants.PAGE);
        page.setData(res);

        return page;
    }

    /**
     * 获取打印次数
     * @return
     * @throws Exception
     */
    public Map<String,Object> getPrintCount4cssp(String sfzh,String payNum) throws Exception{
        Map<String,Object> map = new HashMap<>();

        int count = 0;
        String key = "";
        if(StringUtils.isEmpty(sfzh)){
            PayRecordEntity prInfo = payRecordInfo4cssp(payNum);
            if(prInfo==null) {
                prInfo = new PayRecordEntity();
            }
            key = String.format(KEY_OF_PRINTCOUNT, prInfo.getSfzh());
        }else{
            key = String.format(KEY_OF_PRINTCOUNT, sfzh);
        }

        String strPrintCount = JedisUtil.getValue(key);
        if(StringUtils.isNotBlank(strPrintCount)){
            count = Integer.parseInt(strPrintCount);
            map.put("printCount", count);
            map.put("printId",key);
            return map;
        }else {

            int timeout = Integer.parseInt(PRINTCOUNT_TIMEOUT);
            count =  Integer.parseInt(PRINTCOUNT);
            JedisUtil.setValue(key, PRINTCOUNT,timeout);
            map.put("printCount", count);
            map.put("printId", key);
            return map;
        }
    }

    /**
     * 减少打印次数
     * @param printId 唯一标识符
     * @return
     */
    public boolean subtractPrintCount(String printId){
        int count = 0;
        try {
            String strPrintCount = JedisUtil.getValue(printId);
            if(StringUtils.isNotBlank(strPrintCount)){
                count = Integer.parseInt(strPrintCount);
                if(count > 0){
                    count --;
                }
                int timeout = Integer.parseInt(PRINTCOUNT_TIMEOUT);
                JedisUtil.setValue(printId, count+"", timeout);
                return true;
            }
        } catch (Exception e) {
            System.out.println("减少打印次数异常"+e.getMessage());
        }
        return false;
    }

    /**
     * 修改缴费记录状态
     * @param bean
     * @return
     */
    public boolean updateRecordStatus4cssp(Map<String,Object> bean)throws Exception{
        int count = this.getSqlSessionTemplate().update(NAMESPACE + "updateRecordStatus",bean);
        return count > 0 ? true : false;
    }

    /**
     * 根据缴费单号获取缴费记录信息
     * @param payNum
     * @return
     * @throws Exception
     */
    public  PayRecordEntity payRecordInfo4cssp(String payNum) throws Exception{
        PayRecordEntity prInfo = this.getSqlSessionTemplate().selectOne(NAMESPACE + "payRecordInfo",payNum);
        return prInfo;
    }
}
