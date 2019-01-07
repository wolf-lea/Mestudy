package com.tecsun.sisp.iface.server.model.service;

import com.tecsun.sisp.iface.common.util.Constants;
import com.tecsun.sisp.iface.common.vo.SecQueryBean;
import com.tecsun.sisp.iface.common.vo.card.*;
import com.tecsun.sisp.iface.server.model.dao.SecQueryDAO;
import com.tecsun.sisp.iface.server.outerface.his.HTHisIface;

import org.apache.axis.client.Call;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

/**
 * Created by jerry on 2015/5/31.
 */
@Service("secQueryService")
public class SecQueryServiceImpl implements SecQueryService {

	public static final Logger logger = Logger.getLogger(SecQueryServiceImpl.class);
	
    @Autowired
    private SecQueryDAO secQueryDAO;

    public TsjbxxVO getTsjbxxVO(TsjbxxVO vo) throws Exception {
        return secQueryDAO.getTsjbxxVO(vo);
    }

    public void changePhoneNo(SecQueryBean bean) throws Exception {
        secQueryDAO.changePhoneNo(bean);
    }

    public List<TsgrcbxxVO> getPersonSureInfo(SecQueryBean bean) throws Exception {
        return secQueryDAO.getPersonSureInfo(bean);
    }

    public List<TsgrjfjsxxVO> getPersonPay(SecQueryBean vo) throws Exception {
        return secQueryDAO.getPersonPay(vo);
    }

    public List<TsgrjfxxVO> getPersonPesionPay(SecQueryBean bean) throws Exception {
        return secQueryDAO.getPersonPesionPay(bean);
    }

    public List<TsgsbxjbxxVO> getHurtInfo(SecQueryBean bean) throws Exception {
        return secQueryDAO.getHurtInfo(bean);
    }

    public List<TslybxjbxxVO> getLoseFee(SecQueryBean bean) throws Exception {
        return secQueryDAO.getLoseFee(bean);
    }

    public List<TsmtbxfyjsxxVO> getHealthFee(SecQueryBean bean) throws Exception {
        return secQueryDAO.getHealthFee(bean);
    }

    public List<TsmtbxgrjbxxVO> getHealthAccount(SecQueryBean bean) throws Exception {
        return secQueryDAO.getHealthAccount(bean);
    }

    public List<TsmtbxgrjfmxVO> getHealthPay(SecQueryBean bean) throws Exception {
        return secQueryDAO.getHealthPay(bean);
    }

    public List<TssybxjbxxVO> getBirthInfo(SecQueryBean bean) throws Exception {
        return secQueryDAO.getBirthInfo(bean);
    }

    public List<TssydyffxxVO> getLosePay(SecQueryBean bean) throws Exception {
        return secQueryDAO.getLosePay(bean);
    }

    public List<TsylbxgrjfmxVO> getPesionTreatment(SecQueryBean bean) throws Exception {
        return secQueryDAO.getPesionTreatment(bean);
    }

    public List<TsylbxjbxxVO> getPesionInfo(SecQueryBean bean) throws Exception {
        return secQueryDAO.getPesionInfo(bean);
    }

    public List<TsylbxltxrydyxxVO> getQuitPay(SecQueryBean bean) throws Exception {
        return secQueryDAO.getQuitPay(bean);
    }

    public List<TsyldyffxxVO> getPesionPay(SecQueryBean bean) throws Exception {
        return secQueryDAO.getPesionPay(bean);
    }

    public List<TsylgrzhxxVO> getPesionYearAccount(SecQueryBean bean) throws Exception {
        return secQueryDAO.getPesionYearAccount(bean);
    }

    public List<TsylyzhxxVO> getPesionMouthAccount(SecQueryBean bean) throws Exception {
        return secQueryDAO.getPesionMouthAccount(bean);
    }
    
    public List<TsInsuranceVO> getPersonInsure(SecQueryBean bean) throws Exception {
        return secQueryDAO.getPersonInsure(bean);
    }
    
    
    
    //参保个人信息
    public List<TsInsuranceVO> getPersonInfo(SecQueryBean bean) throws Exception {
        return secQueryDAO.getPersonInfo(bean);
    }
    
    //养老保险缴费合计
    public List<TsInsuranceVO> getPersonIncureSum(SecQueryBean bean) throws Exception {
        return secQueryDAO.getInsureSum(bean);
    }
    
    
    //生育保险缴费合计
    public List<TsInsuranceVO> getBirthInfoIncureySum(SecQueryBean bean) throws Exception {
        return secQueryDAO.getInsureSum(bean);
    }
    
    //工伤保险缴费合计
    public List<TsInsuranceVO> getHurtInfoIncureySum(SecQueryBean bean) throws Exception {
        return secQueryDAO.getInsureSum(bean);
    }
    
    
    
    //医疗保险缴费合计
    public List<TsInsuranceVO> getHealthIncureySum(SecQueryBean bean) throws Exception {
        return secQueryDAO.getInsureSum(bean);
    }
    
    
    //失业保险缴费合计
    public List<TsInsuranceVO> getLoseFeeIncureySum(SecQueryBean bean) throws Exception {
        return secQueryDAO.getInsureSum(bean);
    }
    
    public List<TsInsuranceVO> getPersonCvrgInfo(SecQueryBean bean) throws Exception {
        return secQueryDAO.getPersonCvrgInfo(bean);
    }
    
    //养老保险列表查询
    public List<TsInsuranceVO> getPersonIncureList(SecQueryBean bean) throws Exception {
        return secQueryDAO.getPersonInsure(bean);
    }
    
    //医疗保险列表查询
    public List<TsInsuranceVO> getHealthIncureyList(SecQueryBean bean) throws Exception {
        return secQueryDAO.getPersonInsure(bean);
    }
    
    //失业保险列表查询
    public List<TsInsuranceVO> getLoseFeeIncureyList(SecQueryBean bean) throws Exception {
        return secQueryDAO.getPersonInsure(bean);
    }
    
    //生育保险列表查询
    public List<TsInsuranceVO> getBirthInfoIncureyList(SecQueryBean bean) throws Exception {
        return secQueryDAO.getPersonInsure(bean);
    }
    
  //工伤保险列表查询
    public List<TsInsuranceVO> getHurtInfoIncureyList(SecQueryBean bean) throws Exception {
        return secQueryDAO.getPersonInsure(bean);
    }
    
    public List<TsjbxxVO> getGrbhList(TsjbxxVO vo) throws Exception {
        return secQueryDAO.getGrbhList(vo);
    }
    
    //单位或个人缴费合计
    public List<TsInsuranceVO> getPaySum(SecQueryBean bean) throws Exception {
        return secQueryDAO.getPaySum(bean);
    }
    
  //医疗明细帐户列表
    public List<TsInsuranceVO> getHealthAcountList(SecQueryBean bean) throws Exception {
        return secQueryDAO.getHealthAcountList(bean);
    }
    
    
  //当前医疗余额
    public List<TsInsuranceVO> getylAccountYe(SecQueryBean bean) throws Exception {
        return secQueryDAO.getylAccountYe(bean);
    }
    
    
    //城镇职工退休列表
    public List<TsInsuranceVO> getQuitPayList(SecQueryBean bean) throws Exception {
        return secQueryDAO.getQuitPayList(bean);
    }
    
    
    //城镇职工退休个人编码
    public List<TsInsuranceVO> getQuitPayCode(SecQueryBean bean) throws Exception {
        return secQueryDAO.getQuitPayCode(bean);
    }
    
    
    //城镇职工退休合计
    public List<TsInsuranceVO> getQuitPaySum(SecQueryBean bean) throws Exception {
        return secQueryDAO.getQuitPaySum(bean);
    }
    

  //系统内部调用 根据用户名，身份证获取用户唯一 个人编号
    public List<TsInsuranceVO> getPersonGrbh(SecQueryBean bean) throws Exception {
        return secQueryDAO.getPersonGrbh(bean);
    }
    
    
  //系统内部调用 根据用户名，身份证获取用户唯一 个人编号
    public List<TsInsuranceVO> getCheckPersonGrbh(SecQueryBean bean) throws Exception {
        return secQueryDAO.getCheckPersonGrbh(bean);
    }

    // 五险：查询所有在参保个人编号
    public List<TsInsuranceVO>  getPersonCount(SecQueryBean bean) throws Exception {
        return secQueryDAO.getPersonCount(bean);
    }


    // 五险补充：工伤认定信息查询
    public List<TswxaddVO> getInjuryIdenList(SecQueryBean bean) throws Exception {
        return secQueryDAO.getInjuryIdenList(bean);
    }
    // 五险补充：工伤定期待遇信息
    public List<TswxaddVO> getInjuryTreatmentList(SecQueryBean bean) throws Exception {
        return secQueryDAO.getInjuryTreatmentList(bean);
    }

    // 五险补充：劳动能力鉴定信息
    public List<TswxaddVO> getWorkAbilityCheckList(SecQueryBean bean) throws Exception {
        return secQueryDAO.getWorkAbilityCheckList(bean);
    }

     // 五险补充：工伤医疗费结算记录查询
    public List<TswxaddVO> getInjuryMedicalList(SecQueryBean bean) throws Exception {
        return secQueryDAO.getInjuryMedicalList(bean);
    }
 // 五险补充：生育保险待遇信息查询
    public List<TswxaddVO> getFertilityTreatList(SecQueryBean bean) throws Exception {
        return secQueryDAO.getFertilityTreatList(bean);
    }

    // 五险补充：生育医疗费用结算信息查询
    public List<TswxaddVO> getFertilityMedicalList(SecQueryBean bean) throws Exception {
        return secQueryDAO.getFertilityMedicalList(bean);
    }


}
