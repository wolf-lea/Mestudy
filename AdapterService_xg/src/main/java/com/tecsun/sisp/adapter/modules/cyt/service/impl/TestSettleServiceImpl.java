package com.tecsun.sisp.adapter.modules.cyt.service.impl;

import com.tecsun.sisp.adapter.common.util.UID;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.cyt.entity.request.AccountQueryBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.request.ChargeInformationBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.ClaimBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.CollectBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.PersonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xumaohao on 2017/7/24.
 */
@Service("TestSettleServiceImpl")
public class TestSettleServiceImpl extends BaseService {

    private static String NAMESPACE = "com.tecsun.sisp.adapter.modules.cyt.service.impl.TestSettleServiceImpl.";

    @Autowired
    PersonServiceImpl personService;
    /**
     * 插入医疗结算信息
     * @param bean
     * @return
     * @throws Exception
     */
    public ClaimBean insertSettleAccounts4other(ChargeInformationBean bean) throws Exception{
        Map<String, Object> map = new HashMap<>();
        map.put("sfzh", bean.getSfzh());
        PersonBean person= personService.PersonSelect4other(map);
        //报销金额
        float bxje = Float.parseFloat(bean.getZje()) * 0.8f;
        //统筹余额
        float tcye = 0;

        boolean res = true;
        if(person == null){
            bxje = 0;
        }else {
            //统筹余额小于报销金额
            if(Float.valueOf(person.getTcye()) < Float.valueOf(bxje)){
                bxje = Float.valueOf(person.getTcye());
            }else {
                tcye = Float.valueOf(person.getTcye()) - Float.valueOf(bxje);
            }
            map.put("tcye",tcye);


            //更新统筹余额
            res = personService.PerSonUpdate4other(map);
        }

        float zfje = Float.parseFloat(bean.getZje()) - bxje;


        if(res){
            //获取结算流水号
            String jslsh = UID.dateTimes();
            jslsh=jslsh.substring(jslsh.length() - 12, jslsh.length()-6);
            bean.setJslsh(jslsh);

            bean.setBxje(String.valueOf(bxje));
            bean.setZfje(String.valueOf(tcye));
            bean.setTcye(String.valueOf(zfje));

            //记录结算信息
            int count = getSqlSessionTemplate().insert(NAMESPACE + "testInsertSettleAccounts", bean);
            if(count > 0){
                //返回结算后信息
                ClaimBean claim = new ClaimBean();
                claim.setSfzh(bean.getSfzh());
                claim.setXm(bean.getXm());
                claim.setJslsh(bean.getJslsh());
                claim.setBxje(String.valueOf(bxje));
                claim.setTcye(String.valueOf(tcye));
                claim.setZfje(String.valueOf(zfje));
                claim.setZje(bean.getZje());
                claim.setJssj(bean.getJssj());

                return claim;
            }
        }

        return null;
    }

    /**
     * 结算取消
     * @param bean
     * @return
     * @throws Exception
     */
    public boolean cancelSettleAccounts4other(Map<String,Object> bean) throws Exception {
        bean.put("jszt", "1");
        int count = getSqlSessionTemplate().update(NAMESPACE + "testUpdateSettleAccounts", bean);
        return count > 0 ? true : false;
    }

    /**
     * 结算汇总
     * @param bean
     * @return
     * @throws Exception
     */
    public boolean collectSummarizing4other(Map<String,Object> bean) throws Exception {
        bean.put("hzzt", "1");
        int count = getSqlSessionTemplate().update(NAMESPACE + "testUpdateSettleAccounts", bean);
        return count > 0 ? true : false;
    }

    /**
     * 记录结算汇总
     * @param bean
     * @return
     * @throws Exception
     */
    public boolean insertSummarizing4other(Map<String,Object> bean) throws Exception {
        int count = getSqlSessionTemplate().insert(NAMESPACE + "testInsertCollect", bean);
        return count > 0 ? true : false;
    }

    /**
     * 查询结算汇总
     * @param bean
     * @return
     * @throws Exception
     */
    public List<CollectBean> selectSummarizing4other(AccountQueryBean bean) throws Exception {
        List<CollectBean> collect = getSqlSessionTemplate().selectList(NAMESPACE + "testSelectCollect", bean);
        return collect.size() > 0 ? collect : null;
    }

    /**
     * 结算汇总取消
     * @param bean
     * @return
     * @throws Exception
     */
    public boolean cancelCollect4other(Map<String,Object> bean) throws Exception {
        int count = getSqlSessionTemplate().update(NAMESPACE + "updateCollect", bean);
        return count > 0 ? true : null;
    }
}
