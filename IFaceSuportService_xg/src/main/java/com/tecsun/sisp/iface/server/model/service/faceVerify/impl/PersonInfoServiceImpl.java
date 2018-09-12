package com.tecsun.sisp.iface.server.model.service.faceVerify.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.iface.common.vo.Page;
import com.tecsun.sisp.iface.common.vo.faceverify.BusinessInfoPO;
import com.tecsun.sisp.iface.common.vo.faceverify.DRPersonPO;
import com.tecsun.sisp.iface.common.vo.faceverify.FafangInfo;
import com.tecsun.sisp.iface.common.vo.faceverify.HistoryMsgBean;
import com.tecsun.sisp.iface.common.vo.faceverify.IC09PO;
import com.tecsun.sisp.iface.common.vo.faceverify.PersonLoginBean;
import com.tecsun.sisp.iface.common.vo.faceverify.RegistBean;
import com.tecsun.sisp.iface.common.vo.faceverify.XGPersonPO;
import com.tecsun.sisp.iface.server.model.dao_dr.DRPersonInfoDao;
import com.tecsun.sisp.iface.server.model.dao_xg.BusinessInfoDao;
import com.tecsun.sisp.iface.server.model.dao_xg.IC09Dao;
import com.tecsun.sisp.iface.server.model.dao_xg.XGPersonInfoDao;
import com.tecsun.sisp.iface.server.model.service.faceVerify.PersonInfoService;

/**
 * Created by Sandwich on 2015/12/11.
 */
@Service("personInfoService")
public class PersonInfoServiceImpl implements PersonInfoService{

    @Autowired
    DRPersonInfoDao drPersonInfoDao;

    @Autowired
    XGPersonInfoDao xgPersonInfoDao;

    @Autowired
    BusinessInfoDao businessInfoDao;

    @Autowired
    IC09Dao ic09Dao;

    public List<DRPersonPO> getDRPersonInfo(Map map) {
        return drPersonInfoDao.getDRPersonInfo(map) ;
    }
    
    
    //根据身份证号码、姓名、性别查询需要采集人的信息
    public List<DRPersonPO> getPersonInfoByIdCard(RegistBean bean) {
        return drPersonInfoDao.getPersonInfoByIdCard(bean) ;
    }
    //根据身份证号码修改采集信息；
    public boolean upPersonInfoByIdCard(Map map) {
        return xgPersonInfoDao.upPersonInfoByIdCard(map)>0;
    }
    
    
    
    public Page<FafangInfo> getFafangInfo(Page<FafangInfo> page , PersonLoginBean bean) {
    	bean.setPage(page);
    	page.setData((List<FafangInfo>) drPersonInfoDao.getFafangInfo(bean));
        return page ;
    }

   
    public boolean insertPersonInfo(Map map) {
        return xgPersonInfoDao.insertPersonInfo(map)>0;
    }

   
    public List<XGPersonPO> getXGPersonInfo(Map map) {
        return xgPersonInfoDao.getXGPersonInfo(map);
    }

   
    public boolean updateXGPersonFPStatus(String isCheck,String idCard) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("isCheck",isCheck);//01未审核，0审核成功，1审核失败 2注册审核成功
        map.put("idCard",idCard);
        return xgPersonInfoDao.updateXGPersonFPStatus(map)>0;
    }
    
    public boolean updateXGPersonPOToPassword(String password,String idCard){
    	Map<String,String> map = new HashMap<String, String>();
        map.put("password",password);
        map.put("idCard",idCard);
        return xgPersonInfoDao.updateXGPersonPOToPassword(map)>0;
    }

   
    public boolean removeXGPersonFPStatus(String isCheck, String idCard) {//有认证时间
        Map<String,String> map = new HashMap<String, String>();
        map.put("isCheck",isCheck);
        map.put("idCard",idCard);
        return xgPersonInfoDao.removeXGPersonFPStatus(map)>0;
    }
/*
 * String personcheckstatus  人检状态
 * String  machincheckstatus 机检状态
 * String authway  认证渠道
 * String authtype 认证方式 
 */
   
    public boolean insertBusinessFaceRe(String person_id, String status, String statement, String identify_id,String personcheckstatus,String  machincheckstatus,String authway,String authtype,String times) {
        System.out.println("非实时的机检结果："+times);
    	Map<String,String> map = new HashMap<String, String>();
        map.put("person_id",person_id);
        map.put("status",status);
        map.put("statement",statement);
        map.put("identify_id",identify_id);
        map.put("personcheckstatus", personcheckstatus);
        map.put("machinecheckstatus", machincheckstatus);
        
        map.put("authway", authway);
        map.put("authtype", authtype);
        map.put("times",times);
        System.out.println("向业务表添加数据时间："+times);
        return businessInfoDao.insertBusinessFaceRe(map)>0;
    }

   
    public boolean insertView(String AAC001, String AAB001, String AAE001, String AAE047, String AAE011,
                              String AAE036, String AAE038, String AAE013, String AAE278, String OAE001,String personid,String identify_id,String AAE279,String devicetype) {
    	System.out.println("向IC09表中添加数据的时间："+AAE047);
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("AAB001",AAB001);
        map.put("AAC001",AAC001);
        map.put("AAE001",AAE001);

        map.put("AAE047",AAE047);
        map.put("AAE011",devicetype);
        map.put("AAE036",new Date());

        map.put("AAE038",AAE038);
        map.put("AAE013",AAE013);
        map.put("AAE278",AAE278);
        map.put("OAE001",OAE001);
        map.put("personid",personid);
        map.put("identify_id",identify_id);
        map.put("AAE279", AAE279);  //add by 20160528
        return ic09Dao.insertView(map)>0;
    }

    
   
    public boolean insertView(String AAC001, String AAB001, String AAE001, String AAE047, String AAE011,
                              String AAE036, String AAE038, String AAE013, String AAE278, String OAE001,String personid,String identify_id) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("AAB001",AAB001);
        map.put("AAC001",AAC001);
        map.put("AAE001",AAE001);

        map.put("AAE047",new Date());
        map.put("AAE011",AAE011);
        map.put("AAE036",new Date());

        map.put("AAE038",AAE038);
        map.put("AAE013",AAE013);
        map.put("AAE278",AAE278);
        map.put("OAE001",OAE001);
        map.put("personid",personid);
        map.put("identify_id",identify_id);
        return ic09Dao.insertView(map)>0;
    }
    
   
    public boolean updateToken(Map map) {
        return xgPersonInfoDao.updateToken(map)>0;
    }

   
    public List<IC09PO> getIC09Info(String AAB001, String AAC001 ,String AAE001 , String AAE038) {
        Map<String,String> map = new HashMap<String, String>();
        map.put("AAB001",AAB001);
        map.put("AAC001",AAC001);
        map.put("AAE001",AAE001);
        map.put("AAE038",AAE038);
        System.out.println(AAB001+"==="+AAC001+"==="+AAE001+"==="+AAE038);
        return ic09Dao.getIC09Info(map);
    }

   
    public Page<BusinessInfoPO> verifyHistory(Page<BusinessInfoPO> page , HistoryMsgBean bean) {
    	bean.setPage(page);
    	page.setData(businessInfoDao.verifyHistory(bean));
        return page;
    }
    
    
    
    //认证记录
    public List<BusinessInfoPO> verifyHistory(HistoryMsgBean bean) {
        return businessInfoDao.verifyHistory(bean) ;
    }
    
    

   
    public boolean delPersonInfo(String idCard) {
        Map<String ,String> map = new HashMap<String, String>();
        map.put("idCard",idCard);
        return xgPersonInfoDao.delPersonInfo(map)>0;
    }
    
    public BusinessInfoPO getBusinessInfoByStatus(Map map){
    	return businessInfoDao.getBusinessInfoByStatus(map);
    }

	
	public boolean updateView(String AAC001, String AAB001, String AAE001,String AAE038,String AAE047) {
		 Map<String,Object> map = new HashMap<String, Object>();
         map.put("AAB001",AAB001);
         map.put("AAC001",AAC001);
         map.put("AAE001",AAE001);
         map.put("AAE038", AAE038);
         map.put("AAE047",AAE047);
         //map.put("AAE036",new Date());
		return ic09Dao.updateView(map);
	}
}
