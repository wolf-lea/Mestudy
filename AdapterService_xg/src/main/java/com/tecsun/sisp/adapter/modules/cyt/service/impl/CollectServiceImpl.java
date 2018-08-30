package com.tecsun.sisp.adapter.modules.cyt.service.impl;

import com.tecsun.sisp.adapter.common.datasource.CustomerContextHolder;
import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.common.util.UID;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.cyt.entity.request.AccountQueryBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.AccountQueryOutBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.CollectBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.DoctorBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xumaohao on 2017/8/1.
 */

@Service("CollectServiceImpl")
public class CollectServiceImpl extends BaseService {

    private static String NAMESPACE1 = "com.tecsun.sisp.adapter.modules.cyt.service.impl.CollectServiceImpl.";
    private static String NAMESPACE2 = "com.tecsun.sisp.adapter.modules.cyt.service.impl.SettleServiceImpl.";

    @Autowired
    TestSettleServiceImpl testSettleService;
    @Autowired
    PersonServiceImpl personService;
    @Autowired
    SettleServiceImpl settleService;

    /**
     * 结算汇总
     * @param bean
     * @return
     * @throws Exception
     */
    public CollectBean collectSummarizing4cssp(Map<String,Object> bean) throws Exception {
        //获取汇总流水号
        String hzlsh = UID.dateTimes();
        hzlsh=hzlsh.substring(hzlsh.length() - 12, hzlsh.length()-6);
        Date date=new Date();

        bean.put("hzsj", date);
        bean.put("hzlsh",hzlsh);
        bean.put("hzzt", "1");
        int count = 0;

        int hzrc = 0;
        float jehj = 0;
        float ybkkje = 0;
        float tczfje = 0;


        String jslsh = String.valueOf(bean.get("jslsh"));
        String[] str = jslsh.split("\\|");
        hzrc = str.length;
        for (int i = 0;i<str.length;i++){
            bean.put("jslsh", str[i]);

            AccountQueryBean account = new AccountQueryBean();
            account.setJslsh(str[i]);
            Page<AccountQueryOutBean> accountOut = settleService.selectSettleAccounts4cssp(account);
            jehj += Float.valueOf(accountOut.getData().get(0).getZje());
            ybkkje += Float.valueOf(accountOut.getData().get(0).getBxje());
            tczfje += Float.valueOf(accountOut.getData().get(0).getZfje());


            //数据源切换
            CustomerContextHolder.setCustomerType(CustomerContextHolder.CSSP_DATASOURCE);
            count = getSqlSessionTemplate().update(NAMESPACE2 + "updateSettleAccounts", bean);
            testSettleService.collectSummarizing4other(bean);
        }

        //获取账号信息
        DoctorBean doctor = personService.DoctorSelect4other(bean);
        bean.clear();

        //得到汇总信息
        bean.put("byljgbm", doctor.getByljgbm());
        bean.put("byljgmc", doctor.getByljgmc());
        bean.put("sjyljgbm", doctor.getSjyljgbm());
        bean.put("sjyljgmc", doctor.getSjyljgmc());
        bean.put("hzlsh", hzlsh);
        bean.put("hzsj", date);
        bean.put("hzrc", hzrc);
        bean.put("jehj", jehj);
        bean.put("ybkkje", ybkkje);
        bean.put("tczfje", tczfje);
        bean.put("jbr", doctor.getCzymc());
        bean.put("yhzh", doctor.getYhzh());

        testSettleService.insertSummarizing4other(bean);

        bean.clear();
        AccountQueryBean accountQueryBean = new AccountQueryBean();
        accountQueryBean.setHzlsh(hzlsh);
        List<CollectBean> collect = testSettleService.selectSummarizing4other(accountQueryBean);
        return collect != null ? collect.get(0) : null;
    }

    /**
     * 结算汇总取消
     * @param bean
     * @return
     * @throws Exception
     */
    public CollectBean cancelSummarizing4cssp(Map<String,Object> bean) throws Exception {

        Date date=new Date();

        bean.put("hzqxsj", date);
        bean.put("hzzt", "2");

        //数据源切换
        CustomerContextHolder.setCustomerType(CustomerContextHolder.CSSP_DATASOURCE);
        int count = getSqlSessionTemplate().update(NAMESPACE2 + "updateSettleAccounts", bean);
        testSettleService.collectSummarizing4other(bean);

        testSettleService.cancelCollect4other(bean);

        AccountQueryBean accountQueryBean = new AccountQueryBean();
        accountQueryBean.setHzlsh((String) bean.get("hzlsh"));
        List<CollectBean> collect = testSettleService.selectSummarizing4other(accountQueryBean);
        return collect != null ? collect.get(0) : null;
    }

    /**
     * 结算汇总查询
     * @param bean
     * @return
     * @throws Exception
     */
    public Page<CollectBean> selectSummarizing4cssp(AccountQueryBean bean) throws Exception {
        Page<CollectBean> page =  new Page<>(bean.getPageno(),bean.getPagesize());
        bean.setPage(page);

        if((bean.getHzlsh() == null || bean.getHzlsh() == "") &&
                (bean.getJgbm() == null || bean.getJgbm() == "")){
            //获取账号信息
            Map<String, Object> map = new HashMap<>();
            map.put("zh", bean.getZh());
            DoctorBean doctor = personService.DoctorSelect4other(map);

            map.clear();
            bean.setJgbm(doctor.getByljgbm());
        }

        List<CollectBean> collect = testSettleService.selectSummarizing4other(bean);
        if(collect != null && collect.size() > 0){
            for (int i=0;i<collect.size();i++){
                collect.get(i).setYljgmc(collect.get(i).getByljgmc());
            }
            page.setData(collect);
        }
        return page;
    }


}
