package com.tecsun.sisp.iface.server.model.service.so.impl;

import com.tecsun.sisp.iface.common.vo.common.query.SecQueryBean;
import com.tecsun.sisp.iface.common.vo.so.po.medical.*;
import com.tecsun.sisp.iface.server.model.dao.so.MedicalDao;
import com.tecsun.sisp.iface.server.model.service.so.MedicalService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by DG on 2015/10/9.
 */
@Service("medicalServiceImpl")
public class MedicalServiceImpl implements MedicalService {

    @Autowired
    public MedicalDao medicalDao;

    @Override
    public Boolean smsCheckoutPersonNo(SecQueryBean bean) {
        return medicalDao.smsCheckoutPersonNo(bean.getAac002()) != 0;
    }

    @Override
    public YlgrjbxxPO smsYlgrjbxx(SecQueryBean bean) {
        return medicalDao.smsYlgrjbxx(bean.getAac002());
    }
    
    //个人医疗参保信息
  @Override
    public YlgrjbxxPO smsYlgrjbxxByperonsId(SecQueryBean bean) {
        return medicalDao.smsYlgrjbxxByperonsId(bean.getAac002());
    }
    
    //2018-9-18修改zengyunhua
 /*   @Override
	public List<YlgrjbxxPO> smsYlgrjbxxByperonsId(SecQueryBean bean) {
    	return medicalDao.smsYlgrjbxxByperonsId(bean.getAac002());
	}*/
  
 

  


    @Override
    public int smsCountYljfxxcx(SecQueryBean bean) {
        return medicalDao.smsCountYljfxxcx(medicalDao.smsylGetPersonIdByPersonNo(bean.getAac002()));
    }

    @Override//医疗个人缴费
    public List<YljfxxcxPO> smsYljfxxcx(SecQueryBean bean) {
    	bean.setAac001(medicalDao.smsylGetPersonIdByPersonNo(bean.getAac002()));
        return medicalDao.smsYljfxxcx(bean);
    }

    @Override
    public int smsCountYlgrzhxxcx(SecQueryBean bean) {
        return medicalDao.smsCountYlgrzhxxcx(bean.getAac002());
    }

    @Override
    public List<YlgrzhxxcxPO> smsYlgrzhxxcx(SecQueryBean bean) {
        return medicalDao.smsYlgrzhxxcx(bean);
    }

    @Override
    public int smsCountYlgrjsxxcx(SecQueryBean bean) {
        return medicalDao.smsCountYlgrjsxxcx(bean.getAac002());
    }

    @Override//个人结算
    public List<YlgrjsxxcxPO> smsYlgrjsxxcx(SecQueryBean bean) {
    	bean.setAac001(medicalDao.smsylGetPersonIdByPersonNo(bean.getAac002()));
        return medicalDao.smsYlgrjsxxcx(bean);
    }

    @Override
    public int smsCountMxbxxcx(SecQueryBean bean) {
        return medicalDao.smsCountMxbxxcx(bean.getAac002());
    }

    @Override
    public List<MxbxxcxPO> smsMxbxxcx(SecQueryBean bean) {
        return medicalDao.smsMxbxxcx(bean);
    }

    @Override
    public int smsCountYdanzxxcx(SecQueryBean bean) {
        return medicalDao.smsCountYdanzxxcx(bean.getAac002());
    }

    @Override
    public List<YdanzxxcxPO> smsYdanzxxcx(SecQueryBean bean) {
        return medicalDao.smsYdanzxxcx(bean);
    }
    @Override//账户余额
    public YlgrzhxxcxPO smsgrbhzhye(SecQueryBean bean){
    bean.setAac001(medicalDao.smsylGetPersonIdByPersonNo(bean.getAac002()));
    	return medicalDao.smsgrbhzhye(bean);
    }

	
    	
}
