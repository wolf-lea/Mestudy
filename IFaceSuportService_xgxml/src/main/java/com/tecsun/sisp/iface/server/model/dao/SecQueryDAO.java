package com.tecsun.sisp.iface.server.model.dao;


import com.tecsun.sisp.iface.common.vo.SecQueryBean;
import com.tecsun.sisp.iface.common.vo.card.*;
import com.tecsun.sisp.iface.server.util.MyBatisDao;

import java.util.List;

/**
 * Created by jerry on 2015/5/31.
 */
@MyBatisDao
public interface SecQueryDAO {
    public List<TsInsuranceVO> getPersonInfo(SecQueryBean bean) throws Exception ;

    public TsjbxxVO getTsjbxxVO(TsjbxxVO vo) throws Exception;

    public void changePhoneNo(SecQueryBean bean) throws Exception;

    public List<TsgrcbxxVO> getPersonSureInfo(SecQueryBean bean) throws Exception;

    public List<TsgrjfjsxxVO> getPersonPay(SecQueryBean bean) throws Exception;

    public List<TsgrjfxxVO> getPersonPesionPay(SecQueryBean bean) throws Exception;

    public List<TsgsbxjbxxVO> getHurtInfo(SecQueryBean bean) throws Exception;

    public List<TslybxjbxxVO> getLoseFee(SecQueryBean bean) throws Exception;

    public List<TsmtbxfyjsxxVO> getHealthFee(SecQueryBean bean) throws Exception;

    public List<TsmtbxgrjbxxVO> getHealthAccount(SecQueryBean bean) throws Exception;

    public List<TsmtbxgrjfmxVO> getHealthPay(SecQueryBean bean) throws Exception;

    public List<TssybxjbxxVO> getBirthInfo(SecQueryBean bean) throws Exception;

    public List<TssydyffxxVO> getLosePay(SecQueryBean bean) throws Exception;

    public List<TsylbxgrjfmxVO> getPesionTreatment(SecQueryBean bean) throws Exception;

    public List<TsylbxjbxxVO> getPesionInfo(SecQueryBean bean) throws Exception;

    public List<TsylbxltxrydyxxVO> getQuitPay(SecQueryBean bean) throws Exception;

    public List<TsyldyffxxVO> getPesionPay(SecQueryBean bean) throws Exception;

    public List<TsylgrzhxxVO> getPesionYearAccount(SecQueryBean bean) throws Exception;

    public List<TsylyzhxxVO> getPesionMouthAccount(SecQueryBean bean) throws Exception;
    
    public List<TsInsuranceVO> getPersonInsure(SecQueryBean bean) throws Exception;
    
    public List<TsInsuranceVO> getInsureSum(SecQueryBean bean) throws Exception ;
    
    public List<TsInsuranceVO> getPersonCvrgInfo(SecQueryBean bean) throws Exception ;
    
    
    public List<TsjbxxVO> getGrbhList(TsjbxxVO vo) throws Exception ;
    
  //单位或个人缴费合计
    public List<TsInsuranceVO> getPaySum(SecQueryBean bean) throws Exception ;
    
    //医疗明细帐户列表
    public List<TsInsuranceVO> getHealthAcountList(SecQueryBean bean) throws Exception;
    
    
    //当前医疗余额
    public List<TsInsuranceVO> getylAccountYe(SecQueryBean bean) throws Exception;
    
    //城镇职工退休列表
    public List<TsInsuranceVO> getQuitPayList(SecQueryBean bean) throws Exception ;
    
  //城镇职工退休合计
    public List<TsInsuranceVO> getQuitPaySum(SecQueryBean bean) throws Exception;
    
  //系统内部调用 根据用户名，身份证获取用户唯一 个人编号
    public List<TsInsuranceVO> getPersonGrbh(SecQueryBean bean) throws Exception ;
    
    //城镇职工退休个人编码
    public List<TsInsuranceVO> getQuitPayCode(SecQueryBean bean)throws Exception;
    
    public List<TsInsuranceVO> getCheckPersonGrbh(SecQueryBean bean) throws Exception;

    // 五险：查询所有在参保个人编号
    public  List<TsInsuranceVO>  getPersonCount(SecQueryBean bean) throws Exception;

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
    public  List<TswxaddVO>  getFertilityMedicalList(SecQueryBean bean) throws Exception;

}
