package com.tecsun.sisp.adapter.modules.card.service.impl;

import com.tecsun.sisp.adapter.common.util.JsonHelper;
import com.tecsun.sisp.adapter.modules.card.entity.request.CsspApplyBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.ResultBusMakeDetal;
import com.tecsun.sisp.adapter.modules.card.entity.response.CardAccomplishVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.DeviceRegistVO;
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
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"queryUploadData",map);
    }
    
  //社保卡申请-查询申领原始照片路径
    public CsspApplyBean queryPicPath4Cssp(String sfzh){
        Map map = new HashMap();
        map.put("sfzh",sfzh);
        return this.getSqlSessionTemplate().selectOne(NAMESPACE+"queryPicPath",map);
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
    	return this.getSqlSessionTemplate().update(NAMESPACE+"updateCardApplyStatus",map);

    }
    
    //社保卡申请-查询符合社保卡申请数据
    public List<CsspApplyBean> queryPersonInfo4Cssp(String applyId){
        Map map = new HashMap();
        map.put("applyId",applyId);
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"queryPersonInfo",map);
    }

  //社保卡申请-查询领卡信息
    public CardAccomplishVO cardGetMessage4Cssp(SecQueryBean vo){
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "cardGetMssage", vo);
    }

  //社保卡申请-保存临时申请数据
    public void insertTemInfo4Cssp(String sfzh, String xm, long picId, String pic_status, String message) {
        Map map = new HashMap();
        map.put("sfzh", sfzh);
        map.put("xm", xm);
        map.put("pic_id", picId);
        map.put("pic_status", pic_status);
        map.put("message", message);
        this.getSqlSessionTemplate().insert(NAMESPACE+"insertTemInfo",map);
    }
    
    /**
     * zengyunhua获取社保网点
     */
    public DeviceRegistVO querybyOrgId4Sisp(String deviceid){
    	return this.getSqlSessionTemplate().selectOne(NAMESPACE+"querybyOrgId",deviceid);
    }
    
}
