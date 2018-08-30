package com.tecsun.sisp.adapter.modules.treatment.controller;

import com.tecsun.sisp.adapter.common.util.*;
import com.tecsun.sisp.adapter.common.util.treatment.FaceIface;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl;
import com.tecsun.sisp.adapter.modules.treatment.entity.request.*;
import com.tecsun.sisp.adapter.modules.treatment.entity.response.TreatPersonVO;
import com.tecsun.sisp.adapter.modules.treatment.entity.response.VerifyResult;
import com.tecsun.sisp.adapter.modules.treatment.service.impl.TreatServiceImpl;


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

/**人脸采集和认证业务
 * Created by danmeng on 2017/3/16.
 */
@Controller
@RequestMapping(value = "/adapter/face")
public class FaceServiceController  extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(FaceServiceController.class);
    @Autowired
    private TreatServiceImpl treatService;
    @Autowired
    private CommServiceImpl commService;

    //系统内部调用 根据身份证获取PERSON_ID,TREAT_ID,判断个人数据是否存在平台
    public TreatPersonBean isPersonTreat(String sfzh,String xm) throws Exception {
        return treatService.isPersonTreat4Cssp(sfzh,xm);
    }

//   ----------------- 采集--------------------------------

    /**
     * 待遇资格认证-人脸采集
     * 并将数据存入平台数据库采集表
     * @param bean
     * @return
     */
    @RequestMapping(value = "/faceCollection", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result faceCollection(@RequestBody CollectBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh()) || StringUtils.isBlank(bean.getXm())
                || bean.getColData() == 0 || StringUtils.isBlank(bean.getDeviceid())
                || StringUtils.isBlank(bean.getColTime()) || StringUtils.isBlank(bean.getColType())
                || StringUtils.isBlank(bean.getColChannel()) || StringUtils.isBlank(bean.getColBus())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
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
            bean.setPersonId(list.get(0).getPersonId());
            String picPath = commService.photoIsExist4Cssp(bean.getColData(), Constants.PICTURE_TYPE_202);
            if (Constants.RESULT_MESSAGE_PARAM.equals(picPath)) {
                message = "采集图片不存在";
                logger.error("sfzh=" + bean.getSfzh() + " picId=" + bean.getColData() + message);
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
//            照片采集
            Map map = FaceIface.collectPhoto(bean.getSfzh(), bean.getXm(), picPath, list.get(0).getPhone());
            statusCode = String.valueOf(map.get("statusCode"));
            message = String.valueOf(map.get("message"));
            if (Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode)) {
                bean.setColResult(Constants.PIXEL_BUSINESS_STATUS_01);
            } else {
                bean.setFailReason(message);
            }
            commService.photoTOPersonAndBus4Cssp(bean.getColData(), bean.getPersonId(), Constants.PICTURE_TYPE_202, statusCode);
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "采集失败";
            bean.setColResult(Constants.PIXEL_BUSINESS_STATUS_02);
            logger.error("待遇资格认证-人脸采集异常：", e);
        } finally {
            try {
                if (Constants.PIXEL_BUSINESS_STATUS_02.equals(bean.getColResult()) && StringUtils.isEmpty(bean.getFailReason()))
                    bean.setFailReason(message);
                treatService.insertCollect4Cssp(bean);
            } catch (Exception e) {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "采集失败";
                logger.error("待遇资格认证-人脸采集插入数据库错误：", e);
            }
        }
        return result(statusCode, message);
    }

    /**
     * 待遇资格认证-人脸认证
     * 并将数据存入平台数据库认证操作表
     * @param bean
     * @return
     */
    @RequestMapping(value = "/faceVerification", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result faceVerification(@RequestBody VerifyBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh()) || StringUtils.isBlank(bean.getXm())
                || bean.getVerifyData() == 0 || StringUtils.isBlank(bean.getDeviceid()) || bean.getVerifyId() == 0
                || StringUtils.isBlank(bean.getVerifyType()) || StringUtils.isBlank(bean.getVerifyTime())
                || StringUtils.isBlank(bean.getVerifyChannel()) || StringUtils.isBlank(bean.getVerifyBus())) {
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
            String picPath = commService.photoIsExist4Cssp(bean.getVerifyData(), Constants.PICTURE_TYPE_203);
            if (Constants.RESULT_MESSAGE_PARAM.equals(picPath)) {
                message = "认证图片不存在";
                logger.error("sfzh=" + bean.getSfzh() + " picId=" + bean.getVerifyData() + message);
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            //认证
            Map map = FaceIface.onlineauth(bean.getSfzh(), picPath, bean.getVerifyChannel());
            statusCode = String.valueOf(map.get("statusCode"));
            message = String.valueOf(map.get("message"));
            if (map.get("result") != null) {
                String data = JsonMapper.buildNormalMapper().toJson(map.get("result"));
                vResult = JsonMapper.buildNormalMapper().fromJson(data, VerifyResult.class);
            }
            if (Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode)) {
                bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_01);
            } else {
                bean.setFailReason(message);
            }
            commService.photoTOPersonAndBus4Cssp(bean.getVerifyData(), bean.getPersonId(), Constants.PICTURE_TYPE_203, statusCode);
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "认证失败";
            bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_02);
            logger.error("待遇资格认证-人脸认证异常：", e);
        } finally {
            try {
                if (Constants.PIXEL_BUSINESS_STATUS_02.equals(bean.getVerifyResult()) && StringUtils.isEmpty(bean.getFailReason()))
                    bean.setFailReason(message);
                bean.setFailReason(bean.getFailReason()+" 认证分数："+vResult.getScore());
                bean.setVerifyTime(CommUtil.getNowDateLongStr());
                treatService.insertVerifyOperation4Cssp(bean);
                vResult.setOperationId(bean.getOperationId());
                vResult.setVerifyTime(bean.getVerifyTime());
                treatService.insertVerifyAndOpera4Cssp(bean);
            } catch (Exception e) {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "认证失败";
                logger.error("待遇资格认证-人脸认证插入数据库错误：", e);
            }
        }
        return result(statusCode, message, vResult);
    }

    /**
     * 待遇资格认证-提交活体检测结果
     * 认证成功后调用
     * 并将数据存入平台数据库认证操作表
     * @param bean
     * @return
     */
    @RequestMapping(value = "/uploadLiveDetectResult", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result uploadLiveDetectResult(@RequestBody VerifyBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh()) || StringUtils.isBlank(bean.getXm())
                || bean.getVerifyData() == 0 || bean.getVerifyId() == 0
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
            TreatPersonBean personBean = isPersonTreat(bean.getSfzh(),bean.getXm());
            if (personBean == null || personBean.getPersonId() == 0 || personBean.getTreatId() == 0) {
                message = "暂无该用户信息";
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            bean.setPersonId(personBean.getPersonId());
            String picPath = commService.photoIsExist4Cssp(bean.getVerifyData(), Constants.PICTURE_TYPE_203);
            //认证图片信息
            if (Constants.RESULT_MESSAGE_PARAM.equals(picPath)) {
                message = "认证图片不存在";
                logger.error("sfzh=" + bean.getSfzh() + " picId=" + bean.getVerifyData() + message);
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            // 渠道类型：德生宝  地址为空则根据设备号查设备所在地
            if ((Constants.TSB).equals(bean.getChannelcode())&&StringUtils.isEmpty(bean.getVerifyAddress())) {
                bean.setVerifyAddress(commService.getTsbAddress4Sisp(bean.getDeviceid()));
            }

            //提交活体检测结果
            Map map = FaceIface.uploadLiveDetectResult(bean);
            statusCode = String.valueOf(map.get("statusCode"));
            message = String.valueOf(map.get("message"));
            if ("1".equals(bean.getVerifyResult()) && Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode)) {
                verifyResult = Constants.PIXEL_BUSINESS_STATUS_01;
            } else {
                if ("0".equals(bean.getVerifyResult()))
                    bean.setFailReason(StringUtils.isEmpty(bean.getFailReason()) ? "前端活体检测失败" : bean.getFailReason());
                else bean.setFailReason("认证云：" + message);
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "活体检测失败";
            verifyResult = Constants.PIXEL_BUSINESS_STATUS_02;
            logger.error("待遇资格认证-提交活体检测结果异常：", e);
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
                logger.error("待遇资格认证-提交活体检测结果插入数据库错误：", e);
            }
        }
        return result(statusCode, message, vResult);
    }

    /**
     * 人脸检测（红外活体）
     * 认证方式08：活体检测（云端）
     * @param bean
     * @return
     */
    @RequestMapping(value = "/liveDetection", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result liveDetection(@RequestBody VerifyBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh()) || StringUtils.isBlank(bean.getXm()) || bean.getVerifyId() == 0
                || bean.getVerifyData() == 0 || StringUtils.isBlank(bean.getDeviceid()) || bean.getInfraredData() == 0
                || StringUtils.isBlank(bean.getVerifyType()) || StringUtils.isBlank(bean.getVerifyTime())
                || StringUtils.isBlank(bean.getVerifyBus()) || StringUtils.isBlank(bean.getVerifyChannel())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_02);
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "检测失败";
        VerifyResult vResult = new VerifyResult();
        try {
            if (treatService.isExistVerify4Cssp(bean.getVerifyId()) <= 0) {
                message = "查不到该认证id对应数据";
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            TreatPersonBean personBean = isPersonTreat(bean.getSfzh(),bean.getXm());
            if (personBean == null || personBean.getPersonId() == 0 || personBean.getTreatId() == 0) {
                message = "暂无该用户信息";
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            bean.setPersonId(personBean.getPersonId());
            //认证图片信息
            String picPath = commService.photoIsExist4Cssp(bean.getVerifyData(), Constants.PICTURE_TYPE_203);
            if (Constants.RESULT_MESSAGE_PARAM.equals(picPath)) {
                message = "认证图片不存在";
                logger.error("sfzh=" + bean.getSfzh() + " picId=" + bean.getVerifyData() + message);
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            //人脸检测（红外活体）图片信息
            String redpicPath = commService.photoIsExist4Cssp(bean.getInfraredData(), Constants.PICTURE_TYPE_210);
            if (Constants.RESULT_MESSAGE_PARAM.equals(redpicPath)) {
                message = "红外图片不存在";
                logger.error("红外活体图片 picId=" + bean.getInfraredData() + message);
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            // 渠道类型：德生宝  地址为空则根据设备号查设备所在地
            if ((Constants.TSB).equals(bean.getChannelcode())&&StringUtils.isEmpty(bean.getVerifyAddress())) {
                bean.setVerifyAddress(commService.getTsbAddress4Sisp(bean.getDeviceid()));
            }
            //认证云 人脸检测（红外活体）
            Map map = FaceIface.liveDetection(picPath, redpicPath);
            statusCode = String.valueOf(map.get("statusCode"));
            message = String.valueOf(map.get("message"));
            if (Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode)) {
                bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_01);
            } else {
                bean.setFailReason(message);
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "活体检测失败";
            logger.error("人脸检测（红外活体）异常：", e);
        } finally {
            try {
                if (Constants.PIXEL_BUSINESS_STATUS_02.equals(bean.getVerifyResult()) && StringUtils.isEmpty(bean.getFailReason()))
                    bean.setFailReason(message);
                bean.setVerifyTime(CommUtil.getNowDateLongStr());
                treatService.insertVerifyOperation4Cssp(bean);
                vResult.setOperationId(bean.getOperationId());
                vResult.setVerifyTime(bean.getVerifyTime());
                treatService.insertVerifyAndOpera4Cssp(bean);
            } catch (Exception e) {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "检测失败";
                logger.error("待遇资格认证-人脸检测（红外活体）插入数据库错误：", e);
            }
        }
        return result(statusCode, message, vResult);
    }
}