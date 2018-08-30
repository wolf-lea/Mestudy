package com.tecsun.sisp.adapter.modules.common.controller;

import com.tecsun.sisp.adapter.common.util.*;
import com.tecsun.sisp.adapter.common.util.treatment.FaceIface;
import com.tecsun.sisp.adapter.modules.account.entity.request.CaptchaBean;
import com.tecsun.sisp.adapter.modules.account.entity.response.CaptchaVO;
import com.tecsun.sisp.adapter.modules.common.entity.request.*;
import com.tecsun.sisp.adapter.modules.common.entity.response.AreaInfoVo;
import com.tecsun.sisp.adapter.modules.common.entity.response.Branch;
import com.tecsun.sisp.adapter.modules.common.entity.response.BusConfigVO;
import com.tecsun.sisp.adapter.modules.common.entity.response.DictionaryVO;
import com.tecsun.sisp.adapter.modules.common.entity.response.PicVO;
import com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl;
import com.tecsun.sisp.adapter.modules.treatment.entity.request.VerifyBean;
import com.tecsun.sisp.adapter.modules.treatment.entity.response.VerifyResult;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**通用接口
 * 上传、查看图片
 * Created by danmeng on 2017/3/13.
 */
@Controller
@RequestMapping(value = "/adapter/comm")
public class CommController extends BaseController {

    public final static Logger logger = Logger.getLogger(CommController.class);

    @Autowired
    private CommServiceImpl commService;
    /**
     * 上传图片
     * @param bean
     * @return
     */
    @RequestMapping(value = "/uploadPicture", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result uploadPicture(@RequestBody PicBean bean) {
        if (StringUtils.isBlank(bean.getPicBase64())||StringUtils.isBlank(bean.getPicType()))
            return this.result(Constants.RESULT_MESSAGE_PARAM, "图片base64编码、类型不能为空");
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        PicVO picVO = new PicVO();
        try {
            // 把图片存到公服T_YTH_PICTURE_INFO表
            long picId = commService.uploadPicture4Cssp(bean);
            if(picId==-1) return this.result(Constants.RESULT_MESSAGE_PARAM, "入参不正确，传入的图像信息有误");
            else if(picId>0){
                //图片关联业务
                PicBusBean picBusBean = new PicBusBean();
                picBusBean.setPicId(picId);
                picBusBean.setPicStatus(Constants.PIXEL_BUSINESS_STATUS_00);
                picBusBean.setPicType(bean.getPicType());
                if( commService.insertPictureBus4Cssp(picBusBean)>0){
                    picVO.setPicId(bean.getPicId());
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                    message = "上传成功";
                }
            }
        } catch (Exception e) {
            logger.error("上传图片失败：", e);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
        }
        return this.result(statusCode, message, picVO);
    }
    
    /**
     * 查询图片
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getPicture", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getPicture(@RequestBody PicBean bean) {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        if (bean.getPicId() == 0) return result(Constants.RESULT_MESSAGE_PARAM, "请输入图片ID");
        try {

            String picPath = commService.photoIsExist4Cssp(bean.getPicId(), bean.getPicType());
            if (Constants.RESULT_MESSAGE_PARAM.equals(picPath)) {
                message = "查无此图片或图片类型错误";
                return result(statusCode, message);
            } else {
                bean.setPicBase64(ImageChangeUtil.getImageStr(picPath));
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "查询成功";
            }
        } catch (Exception e) {
            logger.error("查询图片失败：", e);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
        }
        return result(statusCode, message, bean);
    }

    /**
     * 人脸对比(两张照片)认证业务：001-实名支付认证；003-社保卡申请；004-补换卡申请；010-国政通临时
     * 将认证图片关联业务、人员
     * 将比对图片图片关联业务、人员
     * 若该接口数据非采集认证数据，数据存入平台数据库认证业务记录表  T_YTH_VERIFY_RECORD
     *若该接口为认证数据（与个人信息表关联）， 数据存入平台数据库认证操作表 T_YTH_VERIFY_OPERATION（需编写，参考认证接口）
     * @param bean
     * @return
     */
    @RequestMapping(value = "/comparePhoto", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result comparePhoto(@RequestBody VerifyBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh()) || StringUtils.isBlank(bean.getXm())
                || bean.getVerifyData() == 0 || StringUtils.isBlank(bean.getVerifyType())
                || StringUtils.isBlank(bean.getPicType()) || StringUtils.isBlank(bean.getDeviceid())
                || StringUtils.isBlank(bean.getVerifyChannel()) || StringUtils.isBlank(bean.getVerifyBus())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        if (!"010".equals(bean.getVerifyBus()) && bean.getComparisonData() == 0) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        VerifyResult vResult = new VerifyResult();
        bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_02);
        try {
            // 渠道类型：德生宝  地址为空则根据设备号查设备所在地
            if ((Constants.TSB).equals(bean.getChannelcode()) && StringUtils.isEmpty(bean.getVerifyAddress())) {
                bean.setVerifyAddress(commService.getTsbAddress4Sisp(bean.getDeviceid()));
            }
            //认证图片信息
            String picPath = commService.photoIsExist4Cssp(bean.getVerifyData(), bean.getPicType());
            if (Constants.RESULT_MESSAGE_PARAM.equals(picPath)) {
                message = "认证图片不存在";
                logger.error("sfzh=" + bean.getSfzh() + " picId=" + bean.getVerifyData() + message);
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            Map<String, Object> map = new HashMap<String, Object>();
            if (!"010".equals(bean.getVerifyBus())) {
                //比对图片信息
                String comPicPath = commService.photoIsExist4Cssp(bean.getComparisonData(), Constants.PICTURE_TYPE_001);
                if (Constants.RESULT_MESSAGE_PARAM.equals(comPicPath)) {
                    message = "比对图片不存在";
                    logger.error("sfzh=" + bean.getSfzh() + " picId=" + bean.getComparisonData() + message);
                    return this.result(Constants.RESULT_MESSAGE_PARAM, message);
                }
                //认证云1：1比对
                map = FaceIface.comparePhoto(picPath, comPicPath);
            } else {
                // 国政通人像比对
                map = FaceIface.gztFaceCompare(bean.getSfzh(), bean.getXm(), picPath, bean.getVerifyChannel());
            }
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
            if (bean.getComparisonData() == 0) {
                //比对图片关联业务、人员
                commService.photoTOPersonAndBus4Cssp(bean.getComparisonData(), bean.getPersonId(), Constants.PICTURE_TYPE_001, statusCode);
            }
            //认证图片关联业务、人员
            commService.photoTOPersonAndBus4Cssp(bean.getVerifyData(), bean.getPersonId(), bean.getPicType(), statusCode);
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "对比失败";
            bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_02);
            logger.error("人脸对比异常：", e);
        } finally {
            try {
                if (Constants.PIXEL_BUSINESS_STATUS_02.equals(bean.getVerifyResult()) && StringUtils.isEmpty(bean.getFailReason()))
                    bean.setFailReason(message);
                bean.setFailReason(bean.getFailReason() + " 认证分数：" + vResult.getScore());
                bean.setVerifyTime(CommUtil.getNowDateLongStr());
                commService.insertVerifyRecord4Cssp(bean);
                vResult.setVerifyId(bean.getVerifyId());
                vResult.setVerifyTime(bean.getVerifyTime());
            } catch (Exception e) {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "对比失败";
                logger.error("待遇资格认证-人脸对比插入数据库错误：", e);
            }
        }
        return result(statusCode, message, vResult);
    }
    //查询数据字典
    @RequestMapping(value = "/getAllDictionary", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result getAllDictionary(@RequestBody DictBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getGroupId())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入组名");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            List<DictionaryVO> list = commService.getDictionaryById4Sisp(bean.getGroupId());
            if (list != null && list.size() > 0) {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "查询成功";
                return result(statusCode, message, list);
            } else message = "暂无该数据字典";
        } catch (Exception e) {
            logger.error("获取字典组数据出错：" + e.getMessage());
        }
        return result(statusCode, message);
    }

    /**
     * OCR身份证识别接口
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/ocrIdCard", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result ocrIdCard(@RequestBody PicBean bean) {
        //入参校验
        String check = checOcr(bean);
        if(StringUtils.isNotEmpty(check)){
            return error(check);
        }

        Result result = getPicture(bean);
        if (Constants.RESULT_MESSAGE_ERROR.equals(result.getStatusCode())) {
            return result;
        }
        JSONObject jsonObject = JSONObject.fromObject(result.getData());
        bean.setPicBase64(jsonObject.getString("picBase64"));
        String urlStr = Config.getInstance().get("ocr.url");

        String res = "";
        HttpURLConnection conn = null;
        try {
            URL url = new URL(urlStr);
            conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(5000);
            conn.setReadTimeout(30000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setUseCaches(false);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("Accept-Charset", "utf-8");
            conn.setRequestProperty("contentType", "utf-8");
            conn.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");
            DataOutputStream out = new DataOutputStream(conn.getOutputStream());
            StringBuffer strBuf = new StringBuffer();

            strBuf.append("b64=1&recotype=IdCard&username=test&encoding=utf-8&password=test&head_portrait=0&crop_image=0&");
            strBuf.append("image=");
            strBuf.append(URLEncoder.encode(bean.getPicBase64(), "utf-8"));
            out.writeBytes(strBuf.toString());
            out.flush();
            out.close();

			/* Read Response */
            StringBuffer strBufRes = new StringBuffer();
            BufferedReader reader = new BufferedReader(new InputStreamReader(
                    conn.getInputStream(), "UTF-8"));
            String line = null;
            while ((line = reader.readLine()) != null) {
                strBufRes.append(line);
            }
            res = strBufRes.toString();
            reader.close();
            reader = null;
//            log.info("---------识别结果:" + res);
            JSONObject jsonResult = JSONObject.fromObject(res);
            if (!jsonResult.isNullObject()) {
                String statusCode = jsonResult.getString("Error");
                if (statusCode != null && statusCode.equals("0")) {
                    return new Result(Constants.RESULT_MESSAGE_SUCCESS, "识别成功",
                            jsonResult.get("Result"));
                } else {
                    return result(statusCode, jsonResult.getString("Details"));
                }
            } else {
                return error("接口请求错误", null);
            }

        } catch (Exception e) {
            e.printStackTrace();
            logger.error("OCR读取身份证信息失败" + e);
        } finally {
            if (conn != null) {
                conn.disconnect();
                conn = null;
            }
        }
        return error("接口请求错误", null);
    }

    /**
     * 获取网点列表
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getBranch", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getBranch(@RequestBody BranchBean bean) {
        //入参校验
        String check = checGetBranch(bean);
        if(StringUtils.isNotEmpty(check)){
            return error(check);
        }
        if(StringUtils.isEmpty(bean.getServicesCode())){
            bean.setServicesCode("apply");
        }
        try {
            Page<Branch> list = commService.getBranchList4cssp(bean);
            return ok("查询完成", list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取网点失败：" + e);
        }
        return error("获取网点失败");
    }

    /**
     * 德生宝获取经纬度
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getCoordinate", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCoordinate(@RequestBody BranchBean bean) {
        //入参校验
        String check = checgetCoordinate(bean);
        if(StringUtils.isNotEmpty(check)){
            return error(check);
        }
        try {
            Map<String,String> map = commService.getCoord4Sisp(bean.getDeviceid());
//            if(StringUtils.isNotEmpty(map.get("LONGITUDE"))  && StringUtils.isNotEmpty(map.get("LATITUDE"))) {
            if(map != null) {
                Map<String, String> coord = new HashMap<>();
                coord.put("longitude",map.get("LONGITUDE"));
                coord.put("latitude",map.get("LONGITUDE"));
                return ok("获取经纬度成功", coord);
            }else
                return error("请检查此设备是否已配置坐标值");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("获取经纬度失败：" + e);
        }
        return error("获取经纬度失败");
    }

    /**
     * 获取网点入参检验
     * @param bean
     * @return
     */
    private String checGetBranch(BranchBean bean) {
        if(bean == null)
            return "入参不能为空";
        if(StringUtils.isEmpty(bean.getChannelcode()))
            return "渠道编码channelcode不能为空";
        if(StringUtils.isEmpty(bean.getDeviceid()))
            return  "标识Id deviceid不能为空";
        if(StringUtils.isEmpty(bean.getTokenid()))
            return "权限校验码tokenid不能为空";
        if(bean.getLongitude() == 0)
            return "经度longitude不能为空";
        if(bean.getLatitude() == 0)
            return "纬度latitude不能为空";
        return "";
    }

    /**
     * 身份证识别ocr入参校验
     * @param bean
     * @return
     */
    private String checgetCoordinate(BranchBean bean) {
        if(bean == null)
            return "入参不能为空";
        if(StringUtils.isEmpty(bean.getChannelcode()))
            return "渠道编码channelcode不能为空";
        if(StringUtils.isEmpty(bean.getDeviceid()))
            return  "标识Id deviceid不能为空";
        return "";
    }

    /**
     * 德生宝获取经纬度入参校验
     * @param bean
     * @return
     */
    private String checOcr(PicBean bean) {
        if(bean == null)
            return "入参不能为空";
        if(StringUtils.isEmpty(bean.getChannelcode()))
            return "渠道编码channelcode不能为空";
        if(bean.getPicId() == 0)
            return "图片id picId不能为空";
        if(StringUtils.isEmpty(bean.getPicType()))
            return "图片类型picType不能为空";
        return "";
    }

    //查询业务配置信息
    @RequestMapping(value = "/getBusConfigInfo", method = RequestMethod.POST,produces = "application/json")
    @ResponseBody
    public Result getBusConfigInfo(@RequestBody BusConfigBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getConfigCode())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入所属编码");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            List<BusConfigVO> list = commService.getBusConfigInfo4Cssp(bean.getConfigCode());
            statusCode = Constants.RESULT_MESSAGE_SUCCESS;
            message = "查询成功";
            return result(statusCode, message, list);
        } catch (Exception e) {
            logger.error("获取信息出错：" + e.getMessage());
        }
        return result(statusCode, message);
    }

    /**
     * 发送短信验证码
     * 无其他注册条件，代码从提取AccountController中提取
     * @param bean
     * @return
     */
    @RequestMapping(value = "/sendCaptcha", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result sendCaptcha(@RequestBody CaptchaBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getPhone())) {
            return this.error("请输入手机号");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "发送错误";
        try {
            if (StringUtils.isNotEmpty(bean.getPhone())) {
                bean.setSmsType("4");
                CaptchaVO captchaVO = SmsUtil.sendVerify4Sms(bean.getPhone(), bean.getSmsType());
                if (captchaVO != null && captchaVO.getValidity() == true) {
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                    message = "发送成功";
                    return result(statusCode, message, captchaVO);
                }
            } else {
                message = "该用户未绑定手机号码";
            }
        } catch (Exception e) {
            logger.error("CommController中获取短信验证码失败：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 校验验证码
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/checkCaptcha", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result checkCaptcha(@RequestBody CaptchaBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getCaptcha())) {
            return error("请输入验证码");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "校验失败";
        try {
            if (StringUtils.isNotEmpty(bean.getPhone())) {
                bean.setSmsType("4");
                int status = SmsUtil.verifySMS(bean.getPhone(), bean.getCaptcha(), bean.getSmsType());
                if (status == -1)
                    message = "输入验证码超时，请重新获取";
                else if (status == -2)
                    message = "输入验证码错误，请重新输入";
                else if (status == 1) {
                    message = "验证码正确";
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                } else {
                    message = "校验失败";
                }
            } else {
                message = "未找到有效手机号码";
            }
        } catch (Exception e) {
            logger.error("CommController中校验验证码失败：", e);
        }
        return result(statusCode, message);
    }
	
	@RequestMapping(value = "/getAreaList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getAreaList(@RequestBody AreaQueryBean bean){
        //入参校验
        if(StringUtils.isEmpty(bean.getAreaCode())){
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入区域编码");
        }
        try {
        	List<AreaInfoVo> list = commService.getPCAreaList4Sisp(bean);
            return ok("查询成功", list);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("common-getAreaList报错：" + e);
        }
        return error("获取信息失败");
    }
}
