package com.tecsun.sisp.adapter.modules.treatment.service.impl;

import com.tecsun.sisp.adapter.common.util.CommUtil;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.JsonHelper;
import com.tecsun.sisp.adapter.modules.common.entity.request.PersonBean;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.treatment.entity.request.CollectBean;
import com.tecsun.sisp.adapter.modules.common.entity.request.PicBusBean;
import com.tecsun.sisp.adapter.modules.treatment.entity.request.TreatPersonBean;
import com.tecsun.sisp.adapter.modules.treatment.entity.request.VerifyBean;
import com.tecsun.sisp.adapter.modules.treatment.entity.request.VerifyListBean;
import com.tecsun.sisp.adapter.modules.treatment.entity.response.TreatPersonVO;
import com.tecsun.sisp.adapter.modules.treatment.entity.response.VerifyListVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by danmeng on 2017/3/13.
 */
@Service("TreatServiceImpl")
public class TreatServiceImpl extends BaseService {
	private static Logger logger = LoggerFactory.getLogger(TreatServiceImpl.class);
	public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.treatment.service.impl.TreatServiceImpl.";
	public final static String Comm_NAMESPACE = "com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl.";

	/**
	 * 待遇资格认证-个人基本信息
	 * @param bean
	 * @return
	 */
	public List<TreatPersonVO> getTreatPersonInfo4Cssp(TreatPersonBean bean)
			throws Exception {
		return this.getSqlSessionTemplate().selectList(NAMESPACE + "getTreatPersonInfo", bean);
	}



	/**
	 * 待遇资格认证-根据身份证获取PERSON_ID,TREAT_ID,判断个人数据是否存在个人基本信息表和待遇资格认证信息表
	 * @param sfzh
	 * @return
	 */
	public TreatPersonBean isPersonTreat4Cssp(String sfzh,String xm) throws Exception {
        Map map = new HashMap();
        map.put("sfzh",sfzh);
        map.put("xm",xm);
		// 获取测试数据
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "isPersonTreat", map);
	}

    /**
     * 待遇资格认证-保存待遇个人基本信息
     * 个人基础数据T_YTH_BASIC_PERSON_INFO，待遇基础数据 T_YTH_TREAT_INFO
     * 个人基础数据 允许新增，不允许修改（包括民族、性别等，具体要求项目完善）
     * 待遇基础数据 可增可改
     * @param bean
     * @return
     */
	public TreatPersonVO saveTreatPersonInfo4Cssp(TreatPersonBean bean)throws Exception {
        logger.info("===================sfzh:" + bean.getSfzh() + ",xm:" + bean.getXm() + "===================");
        // 新增
        TreatPersonVO treatPersonVO = new TreatPersonVO();
        if (bean.getPersonId() == 0) {
            // 把数据存到公服个人基本信息表 T_YTH_BASIC_PERSON_INFO
            PersonBean personBean = new PersonBean();
            BeanUtils.copyProperties(bean, personBean);
            //  && StringUtils.isNotBlank(bean.getNation())&& StringUtils.isNotBlank(bean.getSex()) 具体项目民族、性别是否必填
            if (StringUtils.isNotBlank(personBean.getSfzh()) && StringUtils.isNotBlank(personBean.getXm())) {
                if(StringUtils.isNotBlank(bean.getBirthday())) {
                    try {
                        CommUtil.getFormatDateString(bean.getBirthday(), "yyyy-MM-dd");
                    } catch (Exception e) {
                        logger.error("出生日期不符合格式:yyyy-MM-dd:"+bean.getBirthday(),e);
                        bean.setBirthday("");
                    }
                }
                if (StringUtils.isBlank(bean.getBirthday()))
                    bean.setBirthday(CommUtil.getBirthdayByCertNum(bean.getSfzh()));
                this.getSqlSessionTemplate().insert(Comm_NAMESPACE + "insertBasicPersonInfo", personBean);
            }
            bean.setPersonId(personBean.getPersonId());
        }
        if (bean.getPersonId() != 0) {
            if (bean.getTreatId() == 0) {
                this.getSqlSessionTemplate().insert(NAMESPACE + "insertTreatInfo", bean);
            } else {
                // 个人信息存在则判断是否修改
                if (StringUtils.isNotEmpty(bean.getCompany())
                        || StringUtils.isNotEmpty(bean.getPhone())
                        || StringUtils.isNotEmpty(bean.getAddress())) {
                    this.getSqlSessionTemplate().update(NAMESPACE + "updateTreatInfo", bean);
                }
            }
        } else {
            logger.error("保存个人基本信息失败" + JsonHelper.javaBeanToJson(bean));
        }
        treatPersonVO.setPersonId(bean.getPersonId());
        return treatPersonVO;
    }

	/**
	 * 待遇资格认证-存入采集表
	 * @param bean
	 * @return
	 */
	public int insertCollect4Cssp(CollectBean bean) throws Exception {
		return this.getSqlSessionTemplate().insert(NAMESPACE + "insertCollect",bean);
	}

	/**
	 * 待遇资格认证-更改采集表
	 * @param bean
	 * @return
	 */
	public int updateCollect4Cssp(CollectBean bean) throws Exception {
		return this.getSqlSessionTemplate().update(NAMESPACE + "updateCollect",bean);
	}

	/**
	 * 待遇资格认证-存入认证表
	 * @param bean
	 * @return
	 */
	public int insertVerify4Cssp(VerifyBean bean) throws Exception {
		return this.getSqlSessionTemplate().insert(NAMESPACE + "insertVerify",bean);
	}

	/**
	 * 待遇资格认证-更改认证表
	 * @param bean
	 * @return
	 */
	public int updateVerify4Cssp(VerifyBean bean) throws Exception {
		return this.getSqlSessionTemplate().update(NAMESPACE + "updateVerify",bean);
	}

    /**
     * 待遇资格认证-认证数据是否存在
     * @param
     * @return
     */
    public int isExistVerify4Cssp(long verifyId) throws Exception {
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "isExistVerify",verifyId);
    }

    /**
     * 待遇资格认证-存入认证操作表
     * @param verifyId
     * @return
     */
    public int insertVerifyOperation4Cssp(VerifyBean verifyId) throws Exception {
        return this.getSqlSessionTemplate().insert(NAMESPACE + "insertVerifyOperation",verifyId);
    }
    /**
     * 待遇资格认证-存入认证操作关联表
     * @param bean
     * @return
     */
    public int insertVerifyAndOpera4Cssp(VerifyBean bean) throws Exception {
        return this.getSqlSessionTemplate().insert(NAMESPACE + "insertVerifyAndOpera",bean);
    }

    /**
     * 待遇资格认证-个人认证记录(除初始状态数据)
     * @param bean
     * @return
     */
    public List<VerifyListVO> getVerifyRecord4Cssp(VerifyListBean bean)
            throws Exception {
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getVerifyRecord", bean);
    }
}
