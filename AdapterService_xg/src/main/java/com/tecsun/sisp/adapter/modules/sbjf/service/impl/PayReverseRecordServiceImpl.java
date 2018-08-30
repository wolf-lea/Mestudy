package com.tecsun.sisp.adapter.modules.sbjf.service.impl;

import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by linzetian on 2017/6/6.
 */
@Service("PayReverseRecordServiceImpl")
public class PayReverseRecordServiceImpl extends BaseService {

    public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.sbjf.service.impl.PayReverseRecordServiceImpl.";

    /**
     * 插入冲正记录
     * @param flowNum 交易流水号
     * @param status 状态
     * @return true成功  false 失败
     * @throws Exception
     */
    public boolean insertReverseRecord4cssp(String flowNum,String status) throws Exception{
        Map<String,String> bean = new HashMap<>();
        bean.put("flowNum", flowNum);
        bean.put("status", status);

        int count =  this.getSqlSessionTemplate().insert(NAMESPACE + "insertReverseRecord", bean);
        return count > 0 ? true : false;
    }


}
