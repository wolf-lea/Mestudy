package com.tecsun.sisp.iface.server.model.service.so.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.iface.common.Page;
import com.tecsun.sisp.iface.common.vo.common.query.SecQueryBean;
import com.tecsun.sisp.iface.common.vo.so.po.endow.GrffPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.GrjfPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.GrxxPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.GrzhPO;
import com.tecsun.sisp.iface.common.vo.so.po.endow.JfbzPO;
import com.tecsun.sisp.iface.server.model.dao.so.ResidentsInsuranceDao;
import com.tecsun.sisp.iface.server.model.service.so.ResidentsInsuranceService;

/**
 * Created by DG on 2015/10/9.
 */
@Service("residentsInsuranceServiceImpl")
public class ResidentsInsuranceServiceImpl implements ResidentsInsuranceService {

    @Autowired
    public ResidentsInsuranceDao residentsInsuranceDao;

    @Override
    public Boolean simisCheckoutIdCard(SecQueryBean query) {
    	 Map<String,String> map = new HashMap<String, String>();
         map.put("idCard",query.getAac002());
         map.put("aaz001",query.getAaz001());
        return residentsInsuranceDao.simisCheckoutIdCard(map) != 0;
    }

    @Override
    public GrxxPO simisGetEndowInfoPerson(SecQueryBean query) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("idCard",query.getAac002());
        map.put("aaz001",query.getAaz001());
        return residentsInsuranceDao.simisGetEndowInfoPerson(map);
    }

    @Override
    public Integer simisCountEndowPayPerson(SecQueryBean query) {
        return residentsInsuranceDao.simisCountEndowPayPerson(query.getAac001());
    }

    @Override
    public Page<GrjfPO> simisGetEndowPayPerson(SecQueryBean query , Page<GrjfPO> page) {
    	query.setPage(page);
    	page.setData(residentsInsuranceDao.simisGetEndowPayPerson(query));
        return page;
    }

    @Override
    public Page<GrzhPO> simisGetEndowAccountPerson(SecQueryBean query , Page<GrzhPO> page) {
    	query.setPage(page);
    	page.setData(residentsInsuranceDao.simisGetEndowAccountPerson(query));
        return page;
    }

    @Override
    public Integer simisCountEndowAccountPerson(SecQueryBean query) {
        return residentsInsuranceDao.simisCountEndowAccountPerson(query.getAac001());
    }

    @Override
    public Page<GrffPO> simisGetEndowAnnuityPerson(SecQueryBean query , Page<GrffPO> page) {
    	query.setPage(page);
    	page.setData(residentsInsuranceDao.simisGetEndowAnnuityPerson(query));
        return page;
    }

    @Override
    public Integer simisCountEndowAnnuityPerson(SecQueryBean query) {
        return residentsInsuranceDao.simisCountEndowAnnuityPerson(query.getAac001());
    }
    
    public Integer simisCountEndowPayStandardPerson(SecQueryBean query){
    	return residentsInsuranceDao.simisCountEndowPayStandardPerson(query.getAac001());
    }
    
    public Page<JfbzPO> simisEndowPayStandardPerson(SecQueryBean query , Page<JfbzPO> page){
    	query.setPage(page);
    	page.setData(residentsInsuranceDao.simisEndowPayStandardPerson(query));
        return page;
    }
}
