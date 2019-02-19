package com.tecsun.sisp.iface.server.model.service;


import com.tecsun.sisp.iface.common.vo.SecQueryBean;
import com.tecsun.sisp.iface.common.vo.card.*;

import java.util.List;
import java.util.Map;

/**
 * 社保卡查询业务接口定义 SISP_IFACE_SO
 * Created by jerry on 2015/5/31.
 */
public interface SecQueryService {

    //.修改手机号码	SISP_IFACE_SO_001
    public void changePhoneNo(SecQueryBean bean) throws Exception;

    //.查询个人社保基本信息	SISP_IFACE_SO_002
    public List<TsgrcbxxVO> getPersonSureInfo(SecQueryBean bean) throws Exception;

    //个人缴费基数信息	SISP_IFACE_SO_003
    public List<TsgrjfjsxxVO> getPersonPay(SecQueryBean vo) throws Exception;

    //个人养老保险缴费信息	SISP_IFACE_SO_004
    public List<TsgrjfxxVO> getPersonPesionPay(SecQueryBean bean) throws Exception;

    //.失业保险基本信息	SISP_IFACE_SO_005
    public List<TslybxjbxxVO> getLoseFee(SecQueryBean bean) throws Exception;

    //.失业人员待遇支付明细表	SISP_IFACE_SO_006
    public List<TssydyffxxVO> getLosePay(SecQueryBean bean) throws Exception;

    //.医疗保险费用结算信息	SISP_IFACE_SO_007
    public List<TsmtbxfyjsxxVO> getHealthFee(SecQueryBean bean) throws Exception;

    //.医疗保险个人帐户信息	SISP_IFACE_SO_008
    public List<TsmtbxgrjbxxVO> getHealthAccount(SecQueryBean bean) throws Exception;

    //.医疗保险个人缴费信息	SISP_IFACE_SO_009
    public List<TsmtbxgrjfmxVO> getHealthPay(SecQueryBean bean) throws Exception;

    //.生育保险基本信息	SISP_IFACE_SO_010
    public List<TssybxjbxxVO> getBirthInfo(SecQueryBean bean) throws Exception;

    //.养老保险月账户	SISP_IFACE_SO_011
    public List<TsylyzhxxVO> getPesionMouthAccount(SecQueryBean bean) throws Exception;

    //.在职人员养老保险个人年帐户	SISP_IFACE_SO_012
    public List<TsylgrzhxxVO> getPesionYearAccount(SecQueryBean bean) throws Exception;

    //.养老保险个人缴费明细	SISP_IFACE_SO_013
    public List<TsylbxgrjfmxVO> getPesionTreatment(SecQueryBean bean) throws Exception;

    //.养老保险待遇支付信息	SISP_IFACE_SO_014
    public List<TsyldyffxxVO> getPesionPay(SecQueryBean bean) throws Exception;

    //.养老保险基本信息	SISP_IFACE_SO_015
    public List<TsylbxjbxxVO> getPesionInfo(SecQueryBean bean) throws Exception;

    //.离退休人员待遇信息	SISP_IFACE_SO_016
    public List<TsylbxltxrydyxxVO> getQuitPay(SecQueryBean bean) throws Exception;

    //.个人工伤保险基本信息	SISP_IFACE_SO_017
    public List<TsgsbxjbxxVO> getHurtInfo(SecQueryBean bean) throws Exception;
    
    
    public List<TsInsuranceVO> getPersonInsure(SecQueryBean bean) throws Exception ;
    
    //参保个人信息
    public List<TsInsuranceVO> getPersonInfo(SecQueryBean bean) throws Exception;
     //养老保险缴费合计
    public List<TsInsuranceVO> getPersonIncureSum(SecQueryBean bean) throws Exception ;
    
     //医疗保险缴费合计
    public List<TsInsuranceVO> getHealthIncureySum(SecQueryBean bean) throws Exception;
    
     //失业保险缴费合计
    public List<TsInsuranceVO> getLoseFeeIncureySum(SecQueryBean bean) throws Exception ;
    
     //生育保险缴费合计
    public List<TsInsuranceVO> getBirthInfoIncureySum(SecQueryBean bean) throws Exception ;
    
  //工伤保险缴费合计
    public List<TsInsuranceVO> getHurtInfoIncureySum(SecQueryBean bean) throws Exception;
    
    public List<TsInsuranceVO> getPersonCvrgInfo(SecQueryBean bean) throws Exception ;
    
    //养老保险列表查询
    public List<TsInsuranceVO> getPersonIncureList(SecQueryBean bean) throws Exception;

    //医疗保险列表查询
    public List<TsInsuranceVO> getHealthIncureyList(SecQueryBean bean) throws Exception;
    
    //失业保险列表查询
    public List<TsInsuranceVO> getLoseFeeIncureyList(SecQueryBean bean) throws Exception;
    //生育保险列表查询
    public List<TsInsuranceVO> getBirthInfoIncureyList(SecQueryBean bean) throws Exception;
  //工伤保险列表查询
    public List<TsInsuranceVO> getHurtInfoIncureyList(SecQueryBean bean) throws Exception;
    
    
    public List<TsjbxxVO> getGrbhList(TsjbxxVO vo) throws Exception ;
    
    
  //单位或个人缴费合计
    public List<TsInsuranceVO> getPaySum(SecQueryBean bean) throws Exception ;
    
  //医疗明细帐户列表
    public List<TsInsuranceVO> getHealthAcountList(SecQueryBean bean) throws Exception;
    
  //当前医疗余额
    public List<TsInsuranceVO> getylAccountYe(SecQueryBean bean) throws Exception;
    
    
  //系统内部调用 根据用户名，身份证获取用户唯一 个人编号
    public List<TsInsuranceVO> getPersonGrbh(SecQueryBean bean) throws Exception;
    
    
    //城镇职工退休列表
    public List<TsInsuranceVO> getQuitPayList(SecQueryBean bean) throws Exception ;
    
    //城镇职工退休合计
    public List<TsInsuranceVO> getQuitPaySum(SecQueryBean bean) throws Exception;

    //城镇职工退休个人编码
    public List<TsInsuranceVO> getQuitPayCode(SecQueryBean bean)throws Exception;
    
    public List<TsInsuranceVO> getCheckPersonGrbh(SecQueryBean bean) throws Exception;

    // 五险：查询所有在参保个人编号
    public List<TsInsuranceVO>  getPersonCount(SecQueryBean bean) throws Exception;

    // 五险补充：工伤认定信息查询
    public  List<TswxaddVO>  getInjuryIdenList(SecQueryBean bean) throws Exception;

    // 五险补充：工伤定期待遇信息
    public  List<TswxaddVO>  getInjuryTreatmentList(SecQueryBean bean) throws Exception;

    // 五险补充：劳动能力鉴定信息
    public  List<TswxaddVO>  getWorkAbilityCheckList(SecQueryBean bean) throws Exception;

    // 五险补充：工伤医疗费结算记录查询
    public  List<TswxaddVO>  getInjuryMedicalList(SecQueryBean bean) throws Exception;

    // 五险补充：生育保险待遇信息查询
    public  List<TswxaddVO>  getFertilityTreatList(SecQueryBean bean) throws Exception;

    // 五险补充：生育医疗费用结算信息查询
    public List<TswxaddVO> getFertilityMedicalList(SecQueryBean bean) throws Exception;
}
