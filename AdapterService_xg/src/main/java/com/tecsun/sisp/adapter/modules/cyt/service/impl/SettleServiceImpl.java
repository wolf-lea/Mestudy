package com.tecsun.sisp.adapter.modules.cyt.service.impl;

import com.tecsun.sisp.adapter.common.datasource.CustomerContextHolder;
import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.common.util.UID;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.cyt.entity.request.AccountQueryBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.request.ChargeInformationBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.request.MedicineBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.AccountQueryOutBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.ClaimBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.DoctorBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.PersonBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xumaohao on 2017/7/24.
 */
@Service("SettleServiceImpl")
public class SettleServiceImpl extends BaseService {

    private static String NAMESPACE = "com.tecsun.sisp.adapter.modules.cyt.service.impl.SettleServiceImpl.";

    @Autowired
    TestSettleServiceImpl testSettleService;
    @Autowired
    PersonServiceImpl personService;

    /**
     * 插入医疗结算信息
     * @param bean
     * @return
     * @throws Exception
     */
    public ClaimBean insertSettleAccounts4cssp(ChargeInformationBean bean) throws Exception{
        //获取就诊流水号
        String jzlsh = UID.dateTimes();
        //获取时间
        Date date=new Date();

        bean.setJzlsh(jzlsh);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        bean.setJssj(dateFormat.format(date));

        //技术金额 并将药物数据插到数据库
        int i = 1;
        float num = 0;
        for (MedicineBean medic : bean.getMedicine()) {
            medic.setFyxh(String.valueOf(i));
            i++;
            medic.setJzlsh(jzlsh);
            medic.setJe(medic.getDj() * medic.getSl());
            insertMedicine4cssp(medic);
            num += medic.getJe();
        }

        //药物数量与总金额
        bean.setNum(String.valueOf(bean.getMedicine().length));
        bean.setZje(String.valueOf(num));

        //调第三方接口并接受返回的数据
        ClaimBean claim = testSettleService.insertSettleAccounts4other(bean);
        if(claim != null) {
            bean.setBxje(claim.getBxje());
            bean.setZfje(claim.getZfje());
            bean.setTcye(claim.getTcye());
            bean.setJslsh(claim.getJslsh());
            bean.setJssj(claim.getJssj());

            Map<String, Object> map = new HashMap<>();
            map.put("xm", bean.getXm());
            map.put("sfzh", bean.getSfzh());
            PersonBean person = personService.PersonSelect4other(map);
            if(person != null){
                bean.setSbkh(person.getSbkh());
            }

            //数据源切换
            CustomerContextHolder.setCustomerType(CustomerContextHolder.CSSP_DATASOURCE);
            //保存数据到本地数据库
            int count = getSqlSessionTemplate().insert(NAMESPACE + "insertSettleAccounts", bean);
            return count > 0 ? claim:null;
        }
        return null;
    }

    /**
     * 插入医疗药物信息
     * @param bean
     * @return
     * @throws Exception
     */
    public boolean insertMedicine4cssp(MedicineBean bean) throws Exception{

        int count = getSqlSessionTemplate().insert(NAMESPACE + "insertMedicine", bean);

        return count > 0 ? true : false;

    }


    /**
     * 结算查询
     * @param bean
     * @return
     * @throws Exception
     */
    public Page<AccountQueryOutBean> selectSettleAccounts4cssp(AccountQueryBean bean) throws Exception{
        Page<AccountQueryOutBean> page =  new Page<>(bean.getPageno(),bean.getPagesize());
        bean.setPage(page);

        List<AccountQueryOutBean> accountQueryOut = null;
        if(bean.getFlag() == 0){
            //已结算
            accountQueryOut= getSqlSessionTemplate().selectList(NAMESPACE + "selectSettleAccounts", bean);
        }else if(bean.getFlag() == 1){
            //未汇总
            accountQueryOut= getSqlSessionTemplate().selectList(NAMESPACE + "selectCollect", bean);
        }else {
            //已取消 审核
            accountQueryOut= getSqlSessionTemplate().selectList(NAMESPACE + "selectAllSettleAccounts", bean);
        }

        if(accountQueryOut.size() > 0 ){
            page.setData(accountQueryOut);
        }
        return page;
    }

    /**
     * 结算取消
     * @param bean
     * @return
     * @throws Exception
     */
    public boolean cancelSettleAccounts4cssp(Map<String,Object> bean) throws Exception {
        Date date=new Date();

        bean.put("jsqxsj", date);
        bean.put("jszt", "1");
        int count = getSqlSessionTemplate().update(NAMESPACE + "updateSettleAccounts", bean);

        //调第三方接口取消结算
        testSettleService.cancelSettleAccounts4other(bean);

        DoctorBean doctor = personService.DoctorSelect4other(bean);

        return count > 0 ? true : false;
    }

}
