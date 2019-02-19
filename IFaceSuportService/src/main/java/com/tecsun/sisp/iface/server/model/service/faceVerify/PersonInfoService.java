package com.tecsun.sisp.iface.server.model.service.faceVerify;

import com.tecsun.sisp.iface.common.vo.Page;
import com.tecsun.sisp.iface.common.vo.faceverify.BusinessInfoPO;
import com.tecsun.sisp.iface.common.vo.faceverify.DRPersonPO;
import com.tecsun.sisp.iface.common.vo.faceverify.HistoryMsgBean;
import com.tecsun.sisp.iface.common.vo.faceverify.HistoryResult;
import com.tecsun.sisp.iface.common.vo.faceverify.IC09PO;
import com.tecsun.sisp.iface.common.vo.faceverify.RegistBean;
import com.tecsun.sisp.iface.common.vo.faceverify.XGPersonPO;

import java.util.List;
import java.util.Map;

/**
 * Created by Sandwich on 2015/12/11.
 */
public interface PersonInfoService {
    List<DRPersonPO> getDRPersonInfo(Map map);//从东软获取数据

    boolean insertPersonInfo(java.util.Map map);//新增数据到孝感数据库

    List<XGPersonPO> getXGPersonInfo(Map map);//从孝感获取数据

    boolean updateXGPersonFPStatus(String isCheck,String idCard);//更新人员表认证状态为未认证

    boolean removeXGPersonFPStatus(String isCheck,String idCard );//更新人员表认证状态

    boolean insertBusinessFaceRe(String person_id,String status,String statement,String identify_id,String personcheckstatus,String  machincheckstatus,String authway,String authtype,String times);//新增认证业务记录

    boolean insertView(String AAC001, String AAB001,String AAE001,String AAE047,String AAE011,
            String AAE036,String AAE038,String AAE013,String AAE278,String OAE001,String personId,String identify);
    
    boolean insertView(String AAC001, String AAB001,String AAE001,String AAE047,String AAE011,
                       String AAE036,String AAE038,String AAE013,String AAE278,String OAE001,String personId,String identify,String AAE279,String devicetype);

    boolean updateToken(Map map);//更新token

    boolean delPersonInfo(String idCard);

	List<IC09PO> getIC09Info(String AAB001, String AAC001, String AAE001, String AAE038);//获取认证信息

	Page<BusinessInfoPO> verifyHistory(Page<BusinessInfoPO> page,HistoryMsgBean bean);//获取认证历史纪录
	
	boolean updateView(String AAC001, String AAB001,String AAE001,String AAE038,String AAE047);
	
	//根据身份证号码、姓名、性别查询需要采集人的信息
	 List<DRPersonPO> getPersonInfoByIdCard(RegistBean bean) ;
	 
	 boolean upPersonInfoByIdCard(Map map);// //根据身份证号码修改采集信息；
	 
	 
	 
}
