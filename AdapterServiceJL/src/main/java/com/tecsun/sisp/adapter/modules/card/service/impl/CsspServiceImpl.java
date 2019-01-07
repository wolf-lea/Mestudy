package com.tecsun.sisp.adapter.modules.card.service.impl;

import com.tecsun.sisp.adapter.common.util.JsonHelper;
import com.tecsun.sisp.adapter.modules.card.entity.request.CsspApplyBean;
import com.tecsun.sisp.adapter.modules.card.entity.response.CardAccomplishVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.CardDictVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.CardInfoVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.CsspApplyInfoVo;
import com.tecsun.sisp.adapter.modules.card.entity.response.VerifyRecordVo;
import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
@Service("CsspService")
public class CsspServiceImpl  extends BaseService{
    public static final Logger logger = Logger.getLogger(CsspServiceImpl.class);

    public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.card.service.impl.CsspServiceImpl.";

    // 查询之前是否申请过社保卡
    public List<CsspApplyBean> isExistApplyPersonInfo4Cssp(String name,String sfzh) throws Exception{
        Map map = new HashMap();
        map.put("xm",name);
        map.put("sfzh",sfzh);
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"isExistApplyPersonInfo",map);

    }

    //社保卡申请-存入社保卡申请信息表
    public int insertCardApplyInfo4Cssp(CsspApplyBean bean) throws Exception {
        int status = 0;
        try {
            status = this.getSqlSessionTemplate().insert(NAMESPACE + "insertCardApplyInfo", bean);
        } catch (Exception e) {
            logger.error("存入社保卡申请信息表失败" + JsonHelper.javaBeanToJson(bean), e);
            status = -2;
        }
        return status;
    }
    //社保卡申请-存入社保卡申请领卡地址表
    public int insertCardApplyAddr4Cssp(CsspApplyBean bean) throws Exception {
        int status = 0;
        try {
            status = this.getSqlSessionTemplate().insert(NAMESPACE + "insertCardApplyAddr", bean);
        } catch (Exception e) {
            logger.error("存入社保卡申请领卡地址表失败" + JsonHelper.javaBeanToJson(bean), e);
            status = -2;
        }
        return status;
    }

    //社保卡申请-修改社保卡申请信息表
    public int updateCardApplyInfo4Cssp(long personId,String isUpload,long uploadNum){
        if(personId==0) return -1;
        Map map = new HashMap();
        map.put("isUpload",isUpload);
        map.put("personId",personId);
        map.put("uploadNum",uploadNum);
    	return this.getSqlSessionTemplate().update(NAMESPACE+"updateCardApplyInfo",map);

    }

    //社保卡申请-查询符合社保卡申请数据
    public List<CsspApplyBean> queryUploadData4Cssp(long uploadNum){
        Map map = new HashMap();
        map.put("uploadNum",uploadNum);
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "queryUploadData", map);
    }

    //社保卡申请-查询符合社保卡申请数据
    public List<CsspApplyBean> queryUploadDataList4Cssp(long uploadNum){
        Map map = new HashMap();
        map.put("uploadNum",uploadNum);
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"queryUploadDataList",map);
    }

    //社保卡申请-修改社保卡申请信息表
    public int updateCardApplyStatus4Cssp(String applyId,String isUpload,long uploadNum){
        if(applyId == null || applyId.isEmpty()) return -1;
        Map map = new HashMap();
        map.put("isUpload",isUpload);
        map.put("applyId",applyId);
        map.put("uploadNum",uploadNum);
    	return this.getSqlSessionTemplate().update(NAMESPACE + "updateCardApplyStatus", map);

    }

    //社保卡申请-查询符合社保卡申请数据
    public List<CsspApplyBean> queryPersonInfo4Cssp(String applyId){
        Map map = new HashMap();
        map.put("applyId",applyId);
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "queryPersonInfo", map);
    }

    //社保卡申请-查询领卡信息
    public CardAccomplishVO cardGetMessage4Cssp(SecQueryBean vo){
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "cardGetMssage", vo);
    }

    //社保卡申请-保存申领原始照片与处理后照片的关系
    public void insertApplyPic4Cssp(String sfzh, String xm, long picId, String pic_status, String message, long perPicId) {
        Map map = new HashMap();
        map.put("sfzh", sfzh);
        map.put("xm", xm);
        map.put("pic_id", picId);
        map.put("pic_status", pic_status);
        map.put("message", message);
        map.put("per_pic_id", perPicId);
        this.getSqlSessionTemplate().insert(NAMESPACE+"insertApplyPic",map);
    }
    //社保卡申请-查询申请详情
    public CsspApplyInfoVo getApplyCardInfoById4Cssp(long applyId){
    	Map map = new HashMap();
    	map.put("applyId", applyId);
    	return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getApplyCardInfoById", map);
    }

	/**
	 * 社保卡申请-审核记录查看
	 * @param recordId
	 * @return
	 */
	public VerifyRecordVo getVerifyDetail4Other(long recordId) {
		Map map = new HashMap();
    	map.put("recordId", recordId);
    	return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getVerifyDetail", map);
	}
	
	/**
	 * 查询吉林卡管数据字典
	 * @param bean
	 * @return
	 */
	public List<CardDictVO> getDicList4skcj(SecQueryBean bean) {
    	return this.getSqlSessionTemplate().selectList(NAMESPACE + "getDicList", bean);
	}
	
	/**
	 * 查询是否在荣科进行社保卡数据采集
	 * @param bean
	 * @return
	 */
	public List<CardInfoVO> isExitRK4skcj(SecQueryBean bean) throws Exception{
    	return this.getSqlSessionTemplate().selectList(NAMESPACE + "isExitRK", bean);
	}
	
	/**
	 * 查询是否在省卡管存在社保卡采集数据
	 * @param bean
	 * @return
	 */
	public List<CardInfoVO> isExitSKG4skcj(SecQueryBean bean) throws Exception{
    	return this.getSqlSessionTemplate().selectList(NAMESPACE + "isExitSKG", bean);
	}
	
	
	/**
	 * 查询是否在德生存在社保卡采集数据
	 * @param bean
	 * @return
	 */
	public List<CardInfoVO> isExitDS4skcj(SecQueryBean bean) throws Exception{
    	return this.getSqlSessionTemplate().selectList(NAMESPACE + "isExitDS", bean);
	}
	

	//社保卡申请-存入社保卡申请信息到DS数据库数据表DS_COLLECTIONS
    public int insertCardApplyInfoToCollect4skcj(CsspApplyBean bean) throws Exception {
        int status = 0;
        try {
            status = this.getSqlSessionTemplate().insert(NAMESPACE + "insertCardApplyInfoToCollect", bean);
        } catch (Exception e) {
            logger.error("存入社保卡申请信息表DS数据库数据表DS_COLLECTIONS失败" + JsonHelper.javaBeanToJson(bean), e);
            status = -2;
        }
        return status;
    }
    
}
