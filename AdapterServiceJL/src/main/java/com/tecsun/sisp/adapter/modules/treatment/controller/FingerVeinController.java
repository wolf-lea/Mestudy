package com.tecsun.sisp.adapter.modules.treatment.controller;

import com.tecsun.sisp.adapter.common.util.*;
import com.tecsun.sisp.adapter.common.util.treatment.FingerVeinIface;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.common.entity.request.PicBusBean;
import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
import com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl;
import com.tecsun.sisp.adapter.modules.treatment.entity.request.*;
import com.tecsun.sisp.adapter.modules.treatment.entity.response.TreatPersonVO;
import com.tecsun.sisp.adapter.modules.treatment.entity.response.VerifyResult;
import com.tecsun.sisp.adapter.modules.treatment.service.impl.TreatServiceImpl;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * 指静脉采集认证
 * Created by danmeng on 2017/3/17.
 */

@Controller
@RequestMapping(value = "/adapter/fingervein")
public class FingerVeinController  extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(FaceServiceController.class);
    private static String RZY_URL = Config.getInstance().get("rzy.url");
    @Autowired
    private TreatServiceImpl treatService;
    @Autowired
    private CommServiceImpl commService;

    //系统内部调用 根据身份证获取PERSON_ID,TREAT_ID,判断个人数据是否存在平台
    public TreatPersonBean isPersonTreat(String sfzh,String xm) throws Exception {
        return treatService.isPersonTreat4Cssp(sfzh,xm);
    }    //   ----------------- 采集--------------------------------

    /**
     * 待遇资格认证-指静脉是否已采集
     * @param bean
     * @return
     */
    @RequestMapping(value = "/isCollectFingerVein", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result isCollectFingerVein(@RequestBody SecQueryBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh()) || StringUtils.isBlank(bean.getXm())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            Map map = FingerVeinIface.isCollectFingervein(bean.getSfzh(), bean.getXm());
            statusCode = String.valueOf(map.get("statusCode"));
            message = String.valueOf(map.get("message"));
        } catch (Exception e) {
            logger.error("待遇资格认证-指静脉是否已采集异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 待遇资格认证-指静脉采集
     * 并将数据存入平台数据库采集表
     * 如果 colData（采集图片）id不为0，则查询图片是否存在，若不存在返回 RESULT_MESSAGE_PARAM
     * 若colData为0，则根据认证云结果返回
     * 某些项目指静纹采集需传头像，待完善从第三方获取头像方法
     * 个人信息头像来源： 查看认证云采集数据接口（将建模照片充当头像）,如果认证云未采集，则头像为第三方来源基本信息中的头像
     **@param bean
     * @return
     */
    @RequestMapping(value = "/fingerVeinCollection", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result fingerVeinCollection(@RequestBody CollectBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh()) || StringUtils.isBlank(bean.getXm())
                || StringUtils.isBlank(bean.getColHand()) || StringUtils.isBlank(bean.getColTime())
                || StringUtils.isBlank(bean.getColType()) || StringUtils.isBlank(bean.getDeviceid())
                || StringUtils.isBlank(bean.getColChannel()) || StringUtils.isBlank(bean.getColBus())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        if (bean.getColData() == 0 && StringUtils.isBlank(bean.getColBase64())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "指静脉采集数据不全");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        bean.setColResult(Constants.PIXEL_BUSINESS_STATUS_02);
        try {
            // 渠道类型：德生宝  地址为空则根据设备号查设备所在地
            if ((Constants.TSB).equals(bean.getChannelcode())&&StringUtils.isEmpty(bean.getColAddress())) {
                bean.setColAddress(commService.getTsbAddress4Sisp(bean.getDeviceid()));
            }
            TreatPersonBean personBean = new TreatPersonBean();
            personBean.setSfzh(bean.getSfzh());
            personBean.setXm(bean.getXm());
            List<TreatPersonVO> list = treatService.getTreatPersonInfo4Cssp(personBean);
            if (list == null || list.size() == 0 || list.get(0).getPersonId() == 0 || list.get(0).getTreatId() == 0) {
                message = "暂无该用户信息";
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            String personPicPhoto=null;//个人信息头像
            //            某些项目指静脉采集需传头像 获取头像开始————————————————
            //个人信息头像来源： 查看认证云采集数据接口（将建模照片充当头像）,如果认证云未采集，则头像为第三方来源基本信息中的头像
            //查看认证云采集数据（获取头像）
/*
            Map colMap = TreatmentIface.queryCollectInfo(bean.getSfzh(), bean.getXm(), "01");//认证方式 人脸
            if (Constants.RESULT_MESSAGE_SUCCESS.equals(String.valueOf(colMap.get("statusCode"))) && colMap.get("result") != null) {
                String data = JsonMapper.buildNormalMapper().toJson(colMap.get("result"));
                List<CollIntoVO> collIntoVOs = JsonHelper.jsonToList(data, CollIntoVO.class);
                if (StringUtils.isNotBlank(collIntoVOs.get(0).getCollectdata()))
                    personPicPhoto = collIntoVOs.get(0).getCollectdata();
            }
            //如果认证云未采集，则头像为第三方获取头像(待完善)
            String personPicBase64 = null;//第三方获取头像
            if (StringUtils.isBlank(personPicPhoto)) {
                personPicPhoto = personPicBase64;
            }
            if (StringUtils.isBlank(personPicPhoto)) {
                message = "该用户缺少个人头像";
                logger.error("sfzh=" + bean.getSfzh() + message);
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            */
            //            某些项目指静脉采集需传头像 获取头像结束————————————————

            String picType = Constants.PICTURE_TYPE_208;
            if ("1".equals(bean.getColHand())) picType = Constants.PICTURE_TYPE_207;
            //如果指静脉图片不为空，则查询图片相关信息
            String picPath = null;
            if (bean.getColData() != 0) {
                picPath = commService.photoIsExist4Cssp(bean.getColData(), picType);
                if (Constants.RESULT_MESSAGE_PARAM.equals(picPath)) {
                    message = "采集图片不存在";
                    logger.error("sfzh=" + bean.getSfzh() + " picId=" + bean.getColData() + message);
                    return this.result(Constants.RESULT_MESSAGE_PARAM, message);
                }
            }
            //指静脉采集
            Map map = FingerVeinIface.collectFingervein(list.get(0), bean.getColBase64(), personPicPhoto, picPath);
            statusCode = String.valueOf(map.get("statusCode"));
            message = String.valueOf(map.get("message"));
            if (Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode)) {
                bean.setColResult(Constants.PIXEL_BUSINESS_STATUS_01);
            } else {
                bean.setFailReason(message);
            }
            //如果指静脉图片不为空，则存储图片相关信息
            if (bean.getColData() != 0) {
                commService.photoTOPersonAndBus4Cssp(bean.getColData(), bean.getPersonId(), picType, statusCode);
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "采集失败";
            bean.setColResult(Constants.PIXEL_BUSINESS_STATUS_02);
            logger.error("待遇资格认证-指静脉采集异常：", e);
        } finally {
            try {
                if (Constants.PIXEL_BUSINESS_STATUS_02.equals(bean.getColResult()) && StringUtils.isEmpty(bean.getFailReason()))
                    bean.setFailReason(message);
                treatService.insertCollect4Cssp(bean);
            } catch (Exception e) {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "采集失败";
                logger.error("待遇资格认证-指静脉采集插入数据库错误：", e);
            }
        }
        return result(statusCode, message);
    }


//   ----------------- 认证 --------------------------------


    /**
     * 待遇资格认证-指静脉认证(云端)
     * 认证方式04：指静脉（云端）
     * 如果verifyData(认证图片id)不为0，则查询图片是否存在，若不存在返回 RESULT_MESSAGE_PARAM
     * 若verifyData为0，则根据认证云结果返回
     * 并将数据存入平台数据库认证操作表
     * @param bean
     * @return
     */
    @RequestMapping(value = "/fingerVeinVerification", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result fingerVeinVerification(@RequestBody VerifyBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh()) || StringUtils.isBlank(bean.getXm()) || bean.getVerifyId() == 0
                || StringUtils.isBlank(bean.getVerifyBus()) || StringUtils.isBlank(bean.getDeviceid())
                || StringUtils.isBlank(bean.getVerifyTime()) || StringUtils.isBlank(bean.getVerifyType())
                || StringUtils.isBlank(bean.getVerifyChannel())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        if (bean.getVerifyData() == 0 && StringUtils.isBlank(bean.getVerifyBase64())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_02);
        VerifyResult vResult = new VerifyResult();
        try {
            if (treatService.isExistVerify4Cssp(bean.getVerifyId()) <= 0) {
                message = "查不到该认证id对应数据";
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            // 渠道类型：德生宝  地址为空则根据设备号查设备所在地
            if ((Constants.TSB).equals(bean.getChannelcode())&&StringUtils.isEmpty(bean.getVerifyAddress())) {
                bean.setVerifyAddress(commService.getTsbAddress4Sisp(bean.getDeviceid()));
            }
            TreatPersonBean personBean = isPersonTreat(bean.getSfzh(),bean.getXm());
            if (personBean == null || personBean.getPersonId() == 0 || personBean.getTreatId() == 0) {
                message = "暂无该用户信息";
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            bean.setPersonId(personBean.getPersonId());
            String picType = Constants.PICTURE_TYPE_209;
            //如果指静脉图片不为空，则查询图片相关信息
            if (bean.getVerifyData() != 0) {
                String picPath = commService.photoIsExist4Cssp(bean.getVerifyData(), picType);
                if (Constants.RESULT_MESSAGE_PARAM.equals(picPath)) {
                    message = "认证图片不存在";
                    logger.error("sfzh=" + bean.getSfzh() + " picId=" + bean.getVerifyData() + message);
                    return this.result(Constants.RESULT_MESSAGE_PARAM, message);
                }
            }
            //指静脉认证
            Map map = FingerVeinIface.fingerveinAuth(bean);
            statusCode = String.valueOf(map.get("statusCode"));
            message = String.valueOf(map.get("message"));
            if (Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode)) {
                bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_01);
            } else {
                bean.setFailReason(message);
            }
            //如果指静脉图片不为空，则存储图片相关信息
            if (bean.getVerifyData() != 0) {
                commService.photoTOPersonAndBus4Cssp(bean.getVerifyData(), bean.getPersonId(), picType, statusCode);
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "认证失败";
            bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_02);
            logger.error("待遇资格认证-指静脉认证(云端)异常：", e);
        } finally {
            try {
                if (Constants.PIXEL_BUSINESS_STATUS_02.equals(bean.getVerifyResult()) && StringUtils.isEmpty(bean.getFailReason()))
                    bean.setFailReason(message);
                bean.setVerifyTime(CommUtil.getNowDateLongStr());
                vResult.setVerifyTime(bean.getVerifyTime());
                treatService.insertVerifyOperation4Cssp(bean);
                vResult.setOperationId(bean.getOperationId());
                treatService.insertVerifyAndOpera4Cssp(bean);
            } catch (Exception e) {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "认证失败";
                logger.error("待遇资格认证-指静脉认证(云端)插入数据库错误：", e);
            }
        }
        return result(statusCode, message, vResult);
    }

    /**
     * 指静脉对比/1:1认证
     * 如果verifyData(认证图片id)不为0，则查询图片是否存在，若不存在返回 RESULT_MESSAGE_PARAM
     * 若verifyData为0，则根据认证云结果返回
     * 若该接口数据非采集认证数据，数据存入平台数据库认证业务记录表  T_YTH_VERIFY_RECORD（默认）
     * 若该接口为认证数据（与个人信息表关联）， 数据存入平台数据库认证操作表 T_YTH_VERIFY_OPERATION（需编写，参考认证接口）
     * @param bean
     * @return
     */
    @RequestMapping(value = "/fingerVeinOnlineAuth", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result fingerVeinOnlineAuth(@RequestBody VerifyBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh()) || StringUtils.isBlank(bean.getXm())
                || StringUtils.isBlank(bean.getVerifyBase64()) || StringUtils.isBlank(bean.getComparisonBase64())
                || StringUtils.isBlank(bean.getVerifyTime()) || StringUtils.isBlank(bean.getVerifyType())
                || StringUtils.isBlank(bean.getVerifyChannel()) || StringUtils.isBlank(bean.getDeviceid())
                || StringUtils.isBlank(bean.getVerifyBus())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        VerifyResult vResult = new VerifyResult();
        bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_02);
        try {
            // 渠道类型：德生宝  地址为空则根据设备号查设备所在地
            if ((Constants.TSB).equals(bean.getChannelcode())&&StringUtils.isEmpty(bean.getVerifyAddress())) {
                bean.setVerifyAddress(commService.getTsbAddress4Sisp(bean.getDeviceid()));
            }
            //如果指静脉图片不为空，则查询图片相关信息
            String picType = Constants.PICTURE_TYPE_209;
            if (bean.getVerifyData() != 0) {
                String picPath = commService.photoIsExist4Cssp(bean.getVerifyData(), picType);
                if (Constants.RESULT_MESSAGE_PARAM.equals(picPath)) {
                    message = "认证图片不存在";
                    logger.error("sfzh=" + bean.getSfzh() + " picId=" + bean.getVerifyData() + message);
                    return this.result(Constants.RESULT_MESSAGE_PARAM, message);
                }
            }
            if (bean.getComparisonData() != 0) {
                String comPicPath = commService.photoIsExist4Cssp(bean.getComparisonData(), Constants.PICTURE_TYPE_001);
                if (Constants.RESULT_MESSAGE_PARAM.equals(comPicPath)) {
                    message = "比对图片不存在";
                    logger.error("sfzh=" + bean.getSfzh() + " picId=" + bean.getComparisonData() + message);
                    return this.result(Constants.RESULT_MESSAGE_PARAM, message);
                }
            }
            //认证云指静脉1：1比对
            Map map = FingerVeinIface.fingerOnlineAuth(bean);
            statusCode = String.valueOf(map.get("statusCode"));
            message = String.valueOf(map.get("message"));
            if (Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode)) {
                bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_01);
            } else {
                bean.setFailReason(message);
            }
            //认证图片关联业务、人员
            if (bean.getVerifyData() != 0) {
                commService.photoTOPersonAndBus4Cssp(bean.getVerifyData(), bean.getPersonId(), picType, statusCode);
            }
            //比对图片关联业务、人员
            if (bean.getComparisonData() != 0) {
                commService.photoTOPersonAndBus4Cssp(bean.getComparisonData(), bean.getPersonId(), Constants.PICTURE_TYPE_001, statusCode);
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "比对失败";
            bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_02);
            logger.error("指静脉对比/1:1认证异常：", e);
        } finally {
            try {
                vResult.setVerifyTime(CommUtil.getNowDateLongStr());
                if (Constants.PIXEL_BUSINESS_STATUS_02.equals(bean.getVerifyResult()) && StringUtils.isEmpty(bean.getFailReason()))
                    bean.setFailReason(message);
                commService.insertVerifyRecord4Cssp(bean);
                vResult.setVerifyId(bean.getVerifyId());
            } catch (Exception e) {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "比对失败";
                logger.error("指静脉对比/1:1认证插入数据库错误：", e);
            }
        }
        return result(statusCode, message, vResult);
    }

    /**
     * 待遇资格认证-获取指静脉采集信息（终端认证）
     * 废弃
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getFingerVeinData", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getFingerVeinData(@RequestBody CollectBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh()) || StringUtils.isBlank(bean.getXm())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("idCard", bean.getSfzh());
            jsonObject.put("name", bean.getXm());
            Map map = DictionaryUtil.rzyPostClient(String.valueOf(jsonObject), RZY_URL + Constants.RZY_GETFINGERVEINDATA);
            statusCode = String.valueOf(map.get("statusCode"));
            message = String.valueOf(map.get("message"));
        } catch (Exception e) {
            logger.error("待遇资格认证-获取指静脉采集信息异常：", e);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
        }
        return result(statusCode, message, bean);
    }

    /**
     * 待遇资格认证-上传指静脉认证结果（终端）
     * 认证方式07：指静脉（终端）
     * 如果verifyData(认证图片id)不为0，则查询图片是否存在，若不存在返回 RESULT_MESSAGE_PARAM
     * 若verifyData为0，则根据认证云结果返回
     * 并将数据存入平台数据库认证表
     * @param bean
     * @return
     */
    @RequestMapping(value = "/uploadFingerVeinVerify", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result uploadFingerVeinVerify(@RequestBody VerifyBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh()) || StringUtils.isBlank(bean.getXm()) || bean.getVerifyId() == 0
                || StringUtils.isBlank(bean.getDeviceid()) || StringUtils.isBlank(bean.getVerifyBus())
                || StringUtils.isBlank(bean.getVerifyType()) || StringUtils.isBlank(bean.getVerifyTime())
                || StringUtils.isBlank(bean.getVerifyResult()) || StringUtils.isBlank(bean.getVerifyChannel())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        String verifyResult = Constants.PIXEL_BUSINESS_STATUS_02;
        VerifyResult vResult = new VerifyResult();
        try {
            if (treatService.isExistVerify4Cssp(bean.getVerifyId()) <= 0) {
                message = "查不到该认证id对应数据";
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            // 渠道类型：德生宝  地址为空则根据设备号查设备所在地
            if ((Constants.TSB).equals(bean.getChannelcode())&&StringUtils.isEmpty(bean.getVerifyAddress())) {
                bean.setVerifyAddress(commService.getTsbAddress4Sisp(bean.getDeviceid()));
            }
            TreatPersonBean personBean = isPersonTreat(bean.getSfzh(),bean.getXm());
            if (personBean == null || personBean.getPersonId() == 0 || personBean.getTreatId() == 0) {
                message = "暂无该用户信息";
                bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_02);
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            bean.setPersonId(personBean.getPersonId());
            String picType = Constants.PICTURE_TYPE_209;
            //如果指静脉图片不为空，则查询图片相关信息
            if (bean.getVerifyData() != 0) {
                String picPath = commService.photoIsExist4Cssp(bean.getVerifyData(), picType);
                if (Constants.RESULT_MESSAGE_PARAM.equals(picPath)) {
                    bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_02);
                    logger.error("sfzh=" + bean.getSfzh() + " picId=" + bean.getVerifyData() + " 图片不存在");
                    return this.result(Constants.RESULT_MESSAGE_PARAM, "认证图片不存在");
                }
            }
            //保存指静脉认证结果
            Map map = FingerVeinIface.saveFingerVeinVerifyResult(bean);
            statusCode = String.valueOf(map.get("statusCode"));
            message = String.valueOf(map.get("message"));
            String busStatus = Constants.RESULT_MESSAGE_ERROR;
            if ("1".equals(bean.getVerifyResult()) && Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode)) {
                verifyResult = Constants.PIXEL_BUSINESS_STATUS_01;
                busStatus = Constants.RESULT_MESSAGE_SUCCESS;
            } else {
                if ("0".equals(bean.getVerifyResult()))
                    bean.setFailReason(StringUtils.isEmpty(bean.getFailReason()) ? "前端指静脉认证失败" : bean.getFailReason());
                else bean.setFailReason("认证云：" + message);
            }
            //如果指静脉图片不为空，则存储图片相关信息
            if (bean.getVerifyData() != 0) {
                commService.photoTOPersonAndBus4Cssp(bean.getVerifyData(), bean.getPersonId(), picType, busStatus);
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "上传指静脉认证结果失败";
            verifyResult = Constants.PIXEL_BUSINESS_STATUS_02;
            logger.error("待遇资格认证-上传指静脉认证结果（终端）异常：", e);
        } finally {
            try {
                bean.setVerifyResult(verifyResult);
                if (Constants.PIXEL_BUSINESS_STATUS_02.equals(bean.getVerifyResult()) && StringUtils.isEmpty(bean.getFailReason()))
                    bean.setFailReason(message);
                bean.setVerifyTime(CommUtil.getNowDateLongStr());
                treatService.insertVerifyOperation4Cssp(bean);
                vResult.setOperationId(bean.getOperationId());
                vResult.setVerifyTime(bean.getVerifyTime());
                treatService.insertVerifyAndOpera4Cssp(bean);
            } catch (Exception e) {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "提交失败";
                logger.error("待遇资格认证-上传指静脉认证结果（终端）修改数据库错误：", e);
            }
        }
        return result(statusCode, message, vResult);
    }
}