package com.tecsun.sisp.adapter.modules.card.controller;

import com.google.gson.JsonObject;
import com.tecsun.sisp.adapter.common.util.*;
import com.tecsun.sisp.adapter.modules.card.entity.request.ApplyInfoBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.CsspApplyBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.PixelBean;
import com.tecsun.sisp.adapter.modules.card.entity.response.BankDataVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.CardDictVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.CardInfoVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.CardProgressVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.CsspApplyInfoVo;
import com.tecsun.sisp.adapter.modules.card.entity.response.CsspCardPickUpVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.CsspPolicePhotoVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.FastCardVO;
import com.tecsun.sisp.adapter.modules.card.entity.response.VerifyRecordVo;
import com.tecsun.sisp.adapter.modules.card.service.impl.CsspServiceImpl;
import com.tecsun.sisp.adapter.modules.card.service.impl.TestCardInfoServiceImpl;
import com.tecsun.sisp.adapter.modules.card.service.impl.TestCsspServiceImpl;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.common.entity.request.PersonBean;
import com.tecsun.sisp.adapter.modules.common.entity.request.PicBean;
import com.tecsun.sisp.adapter.modules.common.entity.request.PicBusBean;
import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
import com.tecsun.sisp.adapter.modules.common.entity.response.PicVO;
import com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl;
import com.tecsun.sisp.adapter.modules.so.entity.request.PaymentQueryBean;
import com.tecsun.sisp.adapter.modules.so.entity.response.InsuranceVO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 社保卡业务：社保卡申领功能 文档已出，请勿随意修改入参出参
 * 
 * @ClassName: CsspServiceController
 */
@Controller
@RequestMapping(value = "/adapter/cssp")
public class CsspServiceController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(CsspServiceController.class);

    @Autowired
    private CsspServiceImpl csspService;
    @Autowired
    private TestCsspServiceImpl testCsspService;
    @Autowired
    private TestCardInfoServiceImpl testCardInfoService;
    @Autowired
    private CommServiceImpl commService;

    //像素公司接口url
    public static String PIXEL_URL = Config.getInstance().get("pixel_url");
    //社保卡申请是否允许重复申请
    public static String CARD_APPLY_IS_RE = Config.getInstance().get("card_apply_is_re");
    
    private String user = Config.getInstance().get("card.user");
    private String password = Config.getInstance().get("card.pwd");

    /**
     * canApply
     * 查询参保人是否可以申领社保卡
     * statusCode
     * 200 可以申领
     * 201 参保人姓名或身份证号不能为空
     * 301 已申领，请勿重复申领
     */
    @SuppressWarnings("unused")
	@RequestMapping(value = "/canApply", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result canApply(@RequestBody SecQueryBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "查询失败";
        String sfzh = bean.getSfzh();
        if (StringUtils.isNotEmpty(sfzh)&& StringUtils.isNotEmpty(bean.getXm())) {
            try {
            	
            	  // 调用卡管9.3接口查询制卡进度入参
                String idCard = bean.getSfzh();//身份证号
                String name = bean.getXm();//姓名
                String cityCode = idCard.substring(0, 4) + "00";//所属城市编码
                // 组装入参格式
                String[] param = {user, password, idCard, name, cityCode};
                // 调用卡管9.3接口
                Map<String, String> map = InvokeUtil.invoke("getAZ03", param);
                // 系统按aab301+aac002进行查询，然后比对aac003姓名，返回类XML串，
                // 当<ERR>域返回“OK”时信息有效，否则<ERR>域为出错信息。
                // <VALIDTAG>域为负数时表示制卡失败，<REMARKS>中是失败原因。
                if(map.isEmpty()){
                    message = "查询参保人是否可以申领社保卡信息失败";  
                    statusCode = Constants.RESULT_MESSAGE_ERROR;
                    return result(statusCode, message);
                }
                if (map.containsKey("ERR")) {
                    if (Constants.CARD_ERR_OK.equals(map.get("ERR"))
                    		&& StringUtils.isNotEmpty(map.get("AAC002"))){
                    	message = "对不起，您已采集社保卡申领数据，无需重复采集！";
	                    statusCode = "301";
	                    return result(statusCode, message);	
                    }
                }      
            	       //查询荣科采集表是否存在采集数据
		            	List<CardInfoVO> listRK = csspService.isExitRK4skcj(bean);
		            	if(listRK.size()>0){
		            		message = "对不起，您已采集社保卡申领数据，无需重复采集！";
		                    statusCode = "301";
		                    return result(statusCode, message);
		            	}else{
		            		//查询德生采集表是否存在采集数据
		            		List<CardInfoVO> listDS = csspService.isExitDS4skcj(bean);
			            		if(listDS.size()>0){
			                 		message = "对不起，您已采集社保卡申领数据，无需重复采集！";
			                        statusCode = "301";
			                        return result(statusCode, message);
			                	}else{
				                	 message = "可以申领";
				                     statusCode = Constants.RESULT_MESSAGE_SUCCESS;
			                	}
		                }
                
            } catch (Exception e) {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                logger.error("查询参保人是否可以申领社保卡信息异常bean:\t" + JsonHelper.javaBeanToJson(bean));
                logger.error("查询参保人是否可以申领社保卡信息异常：", e);

                message = "查询参保人是否可以申领社保卡信息失败";
            }
        } else {
            message = "参保人身份证号或姓名不能为空";
            statusCode = Constants.RESULT_MESSAGE_EMPTY;
        }
        return result(statusCode, message);
    }

    /**
     * checkCertValidity
     * 检测证件是否过期
     * 查询方式1、调用第三方接口获取证件有效期、2、前端传入（默认）
     */
    @RequestMapping(value = "/checkCertValidity", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result checkCertValidity(@RequestBody CsspApplyBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "检测失败";
        String sfzh = bean.getSfzh();
        String xm = bean.getXm();
        if (StringUtils.isNotEmpty(sfzh) && StringUtils.isNotEmpty(xm)) {
            try {
                //调用第三方接口获取证件有效期
                boolean status = true;
                if (status) {
                    message = "证件有效";
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                } else {
                    message = "证件有效期已过";
                }
                //前端传入（默认）
                if (StringUtils.isNotEmpty(bean.getCertValidity())) {
                    //查询证件有效期
                    long dayDiff = CommUtil.dayDiff(bean.getCertValidity(), CommUtil.getNowDateLongStr("yyyyMMdd"), "yyyyMMdd");
                    if (dayDiff < 0) {
                        message = "证件有效期已过";
                        statusCode = Constants.RESULT_MESSAGE_ERROR;
                    }
                }
            } catch (Exception e) {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                logger.error("检测证件是否过期异常bean:\t" + JsonHelper.javaBeanToJson(bean));
                logger.error("检测证件是否过期异常：", e);
                message = "检测失败";
            }
        } else {
            message = "参保人姓名或身份证号不能为空";
            statusCode = Constants.RESULT_MESSAGE_EMPTY;
        }
        return result(statusCode, message);
    }

    // 获取银行信息 各地市提供接口
    @RequestMapping(value = "/getBank", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getBank(@RequestBody SecQueryBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "查询银行信息失败";
        try {
            if ((Constants.TSB).equals(bean.getChannelcode())) {
                if (StringUtils.isNotBlank(bean.getDeviceid())) {
                    String code = commService.getCode4Sisp(bean.getDeviceid());
                    if (StringUtils.isBlank(code)) {
                        message = "设备要先与区域做绑定";
                        statusCode = "301";
                        return result(statusCode, message);
                    }
                } else {
                    message = "设备编码不能为空";
                    statusCode = Constants.RESULT_MESSAGE_EMPTY;
                    return result(statusCode, message);
                }
            }
            //根据实际情况修改获取银行信息
            BankDataVO vo = new BankDataVO();
            List<BankDataVO> list = testCsspService.getBankData4Other(vo);
            List<BankDataVO> allBanks = new ArrayList<BankDataVO>(0);
            if (list != null && !list.isEmpty()) {
                allBanks = getCsspBankTree("0", list);
            }
            message = "查询银行信息成功";
            return result(Constants.RESULT_MESSAGE_SUCCESS, message, allBanks);
        } catch (Exception e) {
            logger.error("获取银行信息失败：", e);
        }
        return result(statusCode, message);
    }

    private List<BankDataVO> getCsspBankTree(String parentCode, List<BankDataVO> banks) {
        List<BankDataVO> list = new ArrayList<BankDataVO>();
        for (BankDataVO bank : banks) {
            if (parentCode.equals(bank.getParentCode())) {
                bank.setChildBanks(getCsspBankTree(bank.getBankCode(), banks));
                list.add(bank);
            }
        }
        return list;
    }

    /**
     * getPolicePhotos获取公安库图片或省卡库图片 各地市提供接口
     */
    @RequestMapping(value = "/getPolicePhotos", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getPolicePhotos(@RequestBody SecQueryBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "获取公安库图片失败";
        try {
            if (StringUtils.isNotBlank(bean.getSfzh())) {
                List<CsspPolicePhotoVO> list  = testCsspService.getPolicePhotos4Other(bean);
                if (list != null && !list.isEmpty()) {
                    message = "获取公安库图片成功";
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                    return result(statusCode, message, list.get(0));
                } else {
                    message = "查无此人相片";
                }
            } else {
                message = "身份证号不能为空";
                statusCode = Constants.RESULT_MESSAGE_EMPTY;
            }
        } catch (Exception e) {
            logger.error("获取公安库图片失败", e);
        }
        return result(statusCode, message);
    }

    /**
     * 根据各地市要求选择：社保卡申领：相片处理需一段时间(此时参保人相片第三方处理尚未返回)，调用此方法把个人申领信息存入本地数据库,
     * 等待定时器扫描上传 根据实际情况改写此接口，入参出参不变 各地市提供接口
     * 把采集到的数据存到公服个人基本信息表 T_YTH_BASIC_PERSON_INFO
     * 把采集到的数据存到社保卡申请信息表 T_YTH_APPLYCARD_INFO
     * 性别	01男性;02女性;03未知
     * 民族存数据库为编码
     */
    @RequestMapping(value = "/applyCard", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result applyCard(@RequestBody CsspApplyBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "申领失败";
        CsspCardPickUpVO csspCardPickupVO = new CsspCardPickUpVO();
        //身份证&姓名
        if (StringUtils.isEmpty(bean.getSfzh()) && StringUtils.isEmpty(bean.getXm())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "姓名或身份证不能为空");
        }
        if (StringUtils.isEmpty(bean.getAccountProties())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "请选择户口性质");
        }
        if (StringUtils.isEmpty(bean.getSex())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "性别不能为空");
        }
        if (StringUtils.isEmpty(bean.getGuoji())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "国籍不能为空");
        }
        if (StringUtils.isEmpty(bean.getMobile())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "手机号码不能为空");
        }
        if (StringUtils.isEmpty(bean.getCertValidity())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "证件有效期不能为空");
        }
        // 民族
        String nation = bean.getNation();
        if (StringUtils.isNotEmpty(nation)) {
            nation = StringUtils.isNotBlank(nation) ? nation.replace("族", "") : "";
            bean.setNation(Constants.NATION_Code.get(nation));
        } else
            return result(Constants.RESULT_MESSAGE_EMPTY, "请选择民族");

        if (StringUtils.isEmpty(bean.getChannelcode())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "渠道类型不能为空");
        }
        //渠道类型：德生宝
        if ((Constants.TSB).equals(bean.getChannelcode())) {
            //设备号
            if (StringUtils.isEmpty(bean.getDeviceid())) {
                return result(Constants.RESULT_MESSAGE_EMPTY, "设备号不能为空");
            }
            //领卡地址
            if (StringUtils.isBlank(bean.getTsbAddress())) {
                bean.setCardAddress(commService.getTsbAddress4Sisp(bean.getDeviceid()));
            } else
                bean.setCardAddress(bean.getTsbAddress());
            bean.setAddrType("3");
        }
        if (StringUtils.isEmpty(bean.getCertType())) {
            bean.setCertType("01");//证件类型身份证（户口簿）
        }
 /*根据项目确定
//签名信息
            if (bean.getSignphotoId() == 0) {
                return result(Constants.RESULT_MESSAGE_EMPTY, "个人签名不能为空");
            }
    //领卡地址
        if (StringUtils.isBlank(bean.getTsbAddress())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "领卡地址不能为空");
        }
        if (StringUtils.isEmpty(bean.getBankCode()) || StringUtils.isEmpty(bean.getBankName())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "请选择银行信息");
        }
        if (StringUtils.isEmpty(bean.getPersonType())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "请选择人员类别");
        }
*/
        //个人相片、身份证正、反面
        if (bean.getPhotoBuzId() == 0 || bean.getPicupId() == 0 || bean.getPicdownId() == 0) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "个人相片、身份证正、反面照不能为空");
        }
        try {//出生日期
            if (StringUtils.isBlank(bean.getBirthday())&&"01".equals(bean.getCertType()))
                bean.setBirthday(CommUtil.getBirthdayByCertNum(bean.getSfzh()));
            if(StringUtils.isNotBlank(bean.getBirthday())) {
                try {
                    CommUtil.getFormatDateString(bean.getBirthday(), "yyyy-MM-dd");
                } catch (Exception e) {
                    return this.result(Constants.RESULT_MESSAGE_PARAM, "出生日期不符合格式:yyyy-MM-dd,请重新输入");
                }
            }
            //社保卡申请上传次数限制
            long uploadNum=0;
            String uploadnumber= Config.getInstance().get("card_apply_upload_num");
            if(StringUtils.isNotBlank(uploadnumber)&& ImageChangeUtil.isIntegerByString(uploadnumber))
                uploadNum= Long.parseLong(uploadnumber);
            // 查询之前是否在平台申请
            List<CsspApplyBean>  personList = csspService.isExistApplyPersonInfo4Cssp(bean.getXm(), bean.getSfzh());
            if(personList != null && !personList.isEmpty()) {
                bean.setPersonId(personList.get(0).getPersonId());
                if (personList.get(0).getApplyId() != 0) {
                    String date = personList.get(0).getCreateTime();
                    // 社保卡申请是否允许重复申请,不允许--新申领功能不允许重复
                    //人员申请记录（在本平台申领的）尚未上传成功且未达到社保卡申请上传次数限制，应暂时不允许再次申请
                    if (Constants.CONFIG_NO.equals(CARD_APPLY_IS_RE) &&
                            ("N".equals(personList.get(0).getIsUpload()) &&(personList.get(0).getUploadNum() < uploadNum)
                            )) {
                        message = "证件号" + bean.getSfzh() + "于" + date + "已申领，请勿重复申领";
                        return result(Constants.RESULT_MESSAGE_ERROR, message);
                    }
                   /* // 社保卡可重复申领 但 社保卡申请信息尚未上传成功且未达到社保卡申请上传次数限制，应不允许再次申请，可选
                    else if (Constants.CONFIG_NO.equals(CARD_APPLY_IS_RE) &&
                            ("N".equals(personList.get(0).getIsUpload()) && (personList.get(0).getUploadNum() < uploadNum))) {
                        message = "证件号" + bean.getSfzh() + "于" + date + "已申领，请勿重复申领";
                        return result(Constants.RESULT_MESSAGE_ERROR, message);
                    }*/

                }
            }
            //参保人相片信息（相片未处理）
            PicBean picBean = new PicBean();
            picBean.setPicId(bean.getPhotoBuzId());
            picBean = commService.getPicture4Cssp(picBean);
            if (picBean == null || picBean.getPicId() == 0) {
                message = "参保人相片图片不存在";
                logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPhotoBuzId());
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            //身份证正面信息
            String picUpPath = commService.photoIsExist4Cssp(bean.getPicupId(), Constants.PICTURE_TYPE_103);
            if (Constants.RESULT_MESSAGE_PARAM.equals(picUpPath)) {
                message = "身份证正面图片不存在";
                logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPicupId());
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            //身份证反面信息
            String picDownPath = commService.photoIsExist4Cssp(bean.getPicdownId(), Constants.PICTURE_TYPE_104);
            if (Constants.RESULT_MESSAGE_PARAM.equals(picDownPath)) {
                message = "身份证反面图片不存在";
                logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPicdownId());
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            //电子签名信息 德生宝端需检测
            if (bean.getSignphotoId() != 0) {
                String signPhotoPath = commService.photoIsExist4Cssp(bean.getSignphotoId(), Constants.PICTURE_TYPE_105);
                if (Constants.RESULT_MESSAGE_PARAM.equals(signPhotoPath)) {
                    message = "电子签名图片不存在";
                    logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getSignphotoId());
                    return this.result(Constants.RESULT_MESSAGE_PARAM, message);
                }
            }
            // 根据各地市要求 编写电子申领表
            bean.setScannPhoto("电子申领表");
            if (StringUtils.isEmpty(bean.getScannPhoto())) {
                return result(Constants.RESULT_MESSAGE_EMPTY, "电子申领表不能为空");
            }
            bean.setPhotoSource("02");
            bean.setIsUpload("N");
            bean.setUploadNum(0);
            int status = 0;
            PersonBean personBean = new PersonBean();
            BeanUtils.copyProperties(bean, personBean);
            personBean.setPhone(bean.getMobile());
            if (bean.getPersonId() != 0) {
                // 把个人基本信息表 T_YTH_BASIC_PERSON_INFO更新为采集到的数据
                status = commService.updatePersonInfo4Cssp(personBean);
                if (status > 0) status = 0;
                else
                    return this.result(Constants.RESULT_MESSAGE_ERROR, "保存个人信息出错");
            }
            if (bean.getPersonId() == 0) {
                // 把采集到的数据存到公服个人基本信息表 T_YTH_BASIC_PERSON_INFO
                status = commService.insertPersonInfo4Cssp(personBean);
                if (status > 0) bean.setPersonId(personBean.getPersonId());
                status = 0;
            }
            // 把采集到的数据存入社保卡申请信息表
            if (bean.getPersonId() != 0) {
                if (StringUtils.isNotBlank(bean.getAddrType()) || StringUtils.isNotBlank(bean.getCardAddress())) {
                    status = csspService.insertCardApplyAddr4Cssp(bean);// 把采集到的数据存入社保卡申请领卡地址表
                    if (status < 0) {
                        message = "保存领卡信息失败";
                        return result(statusCode, message);
                    }
                }
                bean.setRemark(bean.getChannelcode());//备注为申请渠道
                status = csspService.insertCardApplyInfo4Cssp(bean);
                if (status > 0) {
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                    message = "提交申领成功";
                    
                    //当申领提交成功的时候
                    
                    
                    //测试 社保卡申请-存入制卡进度--项目使用可删除
                    CardProgressVO vo=new CardProgressVO();
                    vo.setSfzh(bean.getSfzh());
                    vo.setXm(bean.getXm());
                    vo.setApplytime(CommUtil.getNowDateLongStr());
                    vo.setStatus("资料已成功提交，正在审核中");
                    testCsspService.insertCardProgress4Other(vo);
                    //测试
                    csspCardPickupVO.setSfzh(bean.getSfzh());
                    csspCardPickupVO.setXm(bean.getXm());
                    csspCardPickupVO.setTsbAddress(bean.getCardAddress());
                    return result(statusCode, message, csspCardPickupVO);
                }
            } else {
                logger.error("applyCard 保存个人基本信息失败" + JsonHelper.javaBeanToJson(bean));
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "申领失败";
            }
        } catch (Exception e) {
            logger.error("applyCard 保存数据错误：" + JsonHelper.javaBeanToJson(bean));
            logger.error("保存数据错误", e);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "申领失败";
        }
        return result(statusCode, message);
    }

    /**
     * 根据各地市要求选择：社保卡申领：调用此方法把个人申领信息直接上传制卡(此时个人相片已存在,即时申请)
     * 根据实际情况改写此接口，入参出参不变 各地市提供接口
     * 把采集到的数据存到公服个人基本信息表 T_YTH_BASIC_PERSON_INFO
     * 把采集到的数据存到社保卡申请信息表 T_YTH_APPLYCARD_INFO
     * 性别	01男性;02女性;03未知
     * 民族存数据库为编码
     * 如需通过定时器继续上传，则需将个人相片状态改为成功
     */
    @RequestMapping(value = "/applyCardDirect", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result applyCardDirect(@RequestBody CsspApplyBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "申领失败";
        //身份证&姓名
        if (StringUtils.isEmpty(bean.getSfzh()) && StringUtils.isEmpty(bean.getXm())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "姓名或身份证不能为空");
        }
        if (StringUtils.isEmpty(bean.getSex())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "性别不能为空");
        }
        if (StringUtils.isEmpty(bean.getAccountProties())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "请选择户口性质");
        }
        if (StringUtils.isEmpty(bean.getGuoji())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "国籍不能为空");
        }
        if (StringUtils.isEmpty(bean.getMobile())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "手机号码不能为空");
        }
        if (StringUtils.isEmpty(bean.getCertValidity())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "证件有效期不能为空");
        }
        // 民族
        if (StringUtils.isEmpty(bean.getNation())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "请选择民族");
        }    

        if (StringUtils.isEmpty(bean.getChannelcode())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "渠道类型不能为空");
        }
        
        if (StringUtils.isEmpty(bean.getCertType())) {
        	 return result(Constants.RESULT_MESSAGE_EMPTY, "请选择证件类型");
        }

        if (StringUtils.isEmpty(bean.getBankCode())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "请选择服务银行");
        }
        if (StringUtils.isEmpty(bean.getPersonType())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "请选择人员类别");
        }
        if (StringUtils.isEmpty(bean.getPersonStatus())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "请选择人员状态");
        }
        if (StringUtils.isEmpty(bean.getDomicile())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "户口地址不能为空");
        }
        if (StringUtils.isEmpty(bean.getDistinctCode())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "请选择区县");
        }
        if (StringUtils.isEmpty(bean.getOrgCode())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "请选择银行网点");
        }
        //个人相片、身份证正、反面、签名信息
        if (bean.getPhotoBuzId() == 0 || bean.getPicupId() == 0 || bean.getPicdownId() == 0) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "个人相片、身份证正、反面照不能为空");
        }
        
        try {//出生日期
            if (StringUtils.isBlank(bean.getBirthday())&&"A".equals(bean.getCertType()))
                bean.setBirthday(CommUtil.getBirthdayByCertNum(bean.getSfzh()));
            if(StringUtils.isNotBlank(bean.getBirthday())) {
                try {
                    CommUtil.getFormatDateString(bean.getBirthday(), "yyyy-MM-dd");
                } catch (Exception e) {
                    return this.result(Constants.RESULT_MESSAGE_PARAM, "出生日期不符合格式:yyyy-MM-dd,请重新输入");
                }
            }else{
            	 return result(Constants.RESULT_MESSAGE_EMPTY, "出生日期不能为空");
            }
            //未满16周岁未成人申领必填字段
            if(StringUtils.isNotBlank(bean.getBirthday())){
            	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); 
                Date birthDay = sdf.parse(bean.getBirthday());
                int age = CommUtil.getAge(birthDay);
                if(age<16){
                	if (StringUtils.isEmpty(bean.getAgentCertType())||StringUtils.isEmpty(bean.getAgentCertNo())||
                			StringUtils.isEmpty(bean.getAgentName())) {
                        return result(Constants.RESULT_MESSAGE_EMPTY, "请完善监护人信息");
                    }
                    //未成年人户口本照片
                    if (bean.getAccountphotoId()== 0) {
                        return result(Constants.RESULT_MESSAGE_EMPTY, "未成年人户口本照片不能为空");
                    }  	
                }  
            }
            
            // 查询之前是否在平台申请
            List<CsspApplyBean>  personList = csspService.isExistApplyPersonInfo4Cssp(bean.getXm(), bean.getSfzh());
            if(personList != null && !personList.isEmpty()) {
                bean.setPersonId(personList.get(0).getPersonId());
                if (personList.get(0).getApplyId() != 0) {
                        message = "对不起，您已采集社保卡申领数据，无需重复采集！";
                        return result(Constants.RESULT_MESSAGE_ERROR, message);
                    }
             }
            SecQueryBean secBean = new SecQueryBean();
            secBean.setSfzh(bean.getSfzh());
            List<CardInfoVO> listDS = csspService.isExitDS4skcj(bean);
    		if(listDS.size()>0){
         		message = "对不起，您已采集社保卡申领数据，无需重复采集！";
         		return result(Constants.RESULT_MESSAGE_ERROR, message);
    		}
            //修改参保人照片状态
            /*PicBusBean perBusBean = new PicBusBean();
            perBusBean.setPicId(bean.getPhotoBuzId());
            perBusBean.setPicStatus(Constants.PIXEL_BUSINESS_STATUS_01);
            perBusBean.setPicType(Constants.PICTURE_TYPE_102);
            commService.updatePictureBus4Cssp(perBusBean);*/
            
            //个人相片信息
            String perPhotoPath = commService.photoIsExist4Cssp(bean.getPhotoBuzId(), Constants.PICTURE_TYPE_101);
            if (Constants.RESULT_MESSAGE_PARAM.equals(perPhotoPath)) {
                message = "个人相片不存在";
                logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPhotoBuzId());
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            } else { 
            	String img = ImageChangeUtil.getImageStr(perPhotoPath);
            	byte[] bytes =  Base64.decodeBase64(img);
                bean.setPhoto64Byte(bytes);
            }
            //身份证正面信息
            String picUpPath = commService.photoIsExist4Cssp(bean.getPicupId(), Constants.PICTURE_TYPE_103);
            if (Constants.RESULT_MESSAGE_PARAM.equals(picUpPath)) {
                message = "身份证正面图片不存在";
                logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPicupId());
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            } else {
            	String img = ImageChangeUtil.getImageStr(picUpPath);
            	byte[] bytes =  Base64.decodeBase64(img);
                bean.setIdcard64ByteUp(bytes);
            }
            //身份证反面信息
            String picDownPath = commService.photoIsExist4Cssp(bean.getPicdownId(), Constants.PICTURE_TYPE_104);
            if (Constants.RESULT_MESSAGE_PARAM.equals(picDownPath)) {
                message = "身份证反面图片不存在";
                logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPicdownId());
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            } else {
            	String img = ImageChangeUtil.getImageStr(picDownPath);
            	byte[] bytes =  Base64.decodeBase64(img);
                bean.setIdcard64ByteDown(bytes);
            }
            //未成年人户口本信息
            if (bean.getAccountphotoId() != 0) {
                String accountPhotoPath = commService.photoIsExist4Cssp(bean.getAccountphotoId(), Constants.PICTURE_TYPE_106);
                if (Constants.RESULT_MESSAGE_PARAM.equals(accountPhotoPath)) {
                    message = "未成年人户口本图片不存在";
                    logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getAccountphotoId());
                    return this.result(Constants.RESULT_MESSAGE_PARAM, message);
                } else {
                	String img = ImageChangeUtil.getImageStr(accountPhotoPath);
                	byte[] bytes =  Base64.decodeBase64(img);
                    bean.setAccount64Byte(bytes);
                }
            }
                       
            bean.setPhotoSource("01");
            bean.setUploadNum(1);
            int status = 0;
            
            //把采集到的数据存到DS数据库ds_collections表--开始
            status = csspService.insertCardApplyInfoToCollect4skcj(bean);
            if (status > 0) status = 0;
            else  return this.result(Constants.RESULT_MESSAGE_ERROR, "保存采集信息到出错--ds_collections表");         
            //把采集到的数据存到DS数据库ds_collections表--结束
            		
            
            
            PersonBean personBean = new PersonBean();
            BeanUtils.copyProperties(bean, personBean);
            personBean.setPhone(bean.getMobile());
            if (bean.getPersonId() != 0) {
                // 把个人基本信息表 T_YTH_BASIC_PERSON_INFO更新为采集到的数据
                status = commService.updatePersonInfo4Cssp(personBean);
                if (status > 0) status = 0;
                else
                    return this.result(Constants.RESULT_MESSAGE_ERROR, "保存个人信息出错");
            }
            if (bean.getPersonId() == 0) {
                // 把采集到的数据存到公服个人基本信息表 T_YTH_BASIC_PERSON_INFO
                status = commService.insertPersonInfo4Cssp(personBean);
                if (status > 0){
                bean.setPersonId(personBean.getPersonId());
                status = 0;
                }else{
                	return this.result(Constants.RESULT_MESSAGE_ERROR, "保存个人信息出错");
                }
            }
            // 把采集到的数据存入社保卡申请信息表
            if (bean.getPersonId() != 0) {
                bean.setRemark(bean.getChannelcode());//备注为申请渠道
                status = csspService.insertCardApplyInfo4Cssp(bean);
                if (status > 0) {             
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                    message = "申领成功";     
                }else{
                	return this.result(Constants.RESULT_MESSAGE_ERROR, "申领失败，保存采集信息出错--t_yth_applycard_info表");
                }
            } else {
                logger.error("applyCardDirect 保存个人基本信息失败" + JsonHelper.javaBeanToJson(bean));
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "申领失败";
            }
        } catch (Exception e) {
            logger.error("applyCardDirect 保存数据错误：" + JsonHelper.javaBeanToJson(bean));
            logger.error("保存数据错误", e);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "申领失败";
        }
        return result(statusCode, message);
    }


    /**
     * 快速处理照片接口 1.只检测是否符合标准，不进行处理
     *
     *@param bean
     * @return
     */
    @RequestMapping(value = "/fastProcessPic", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result fastProcessPic(@RequestBody PixelBean bean) {
        //String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            if ( StringUtils.isEmpty(bean.getPicType())) {
                return result(Constants.RESULT_MESSAGE_EMPTY, "照片规格不可为空", null);
            }
            String photoBase64 = "";
            if (StringUtils.isNotBlank(bean.getBase64String())) {
                photoBase64 = bean.getBase64String();
            } else if (bean.getPicId() != 0) {
                String picPath = commService.photoIsExist4Cssp(bean.getPicId(), Constants.PICTURE_TYPE_101);
                if (Constants.RESULT_MESSAGE_PARAM.equals(picPath)) {
                    message = "用户图片不存在";
                    logger.error( " picId=" + bean.getPicId() + message);
                    return this.result(Constants.RESULT_MESSAGE_PARAM, message);
                }
                photoBase64 = ImageChangeUtil.getImageStr(picPath);
            } else
                return result(Constants.RESULT_MESSAGE_EMPTY, "图片信息不可为空", null);
            JsonObject json = new JsonObject();
            json.addProperty("base64String", photoBase64);
            json.addProperty("pic_type", bean.getPicType());
            String url = PIXEL_URL + "fastProcessPic";
            String result = (String) getWebClient(url, json, String.class);
            Map<String, Object> map = JsonHelper.jsonToMap(result);
            return result((String) map.get("statusCode"), (String) map.get("message"), null);
        } catch (Exception e) {
            logger.error("快速处理照片fastProcessPic-bean:\t" + JsonHelper.javaBeanToJson(bean), e);
            return error("服务器异常，请稍后再试!", null);
        }
    }

    /**
     * 上传照片接口 1、对图片进行预处理，像素公司调用callbackPicInfo返回处理图片
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/uploadPicInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result uploadPicInfo(@RequestBody PixelBean bean) {
        try {
            String callbackUrl = Config.getInstance().get("callback_pic_url");
            bean.setCallbackUrl(callbackUrl);
            if ( org.apache.commons.lang3.StringUtils.isEmpty(bean.getCallbackUrl())
                    || org.apache.commons.lang3.StringUtils.isEmpty(bean.getPicType())
                    || StringUtils.isEmpty(bean.getSfzh())||StringUtils.isEmpty(bean.getXm())) {
                return result(Constants.RESULT_MESSAGE_EMPTY, "业务参数不全", null);
            }
            String statusCode = Constants.RESULT_MESSAGE_ERROR;
            String message = "操作失败";
            String photoBase64 ="";
            long picId=0;
            long perPicId = 0;
            boolean flag = true;
            if(StringUtils.isNotBlank(bean.getBase64String())){
                //  相片（待处理）
                PicBean picBean = new PicBean();
                picBean.setPicBase64(bean.getBase64String());
                picBean.setPicType(Constants.PICTURE_TYPE_101);
                picId = commService.uploadPicture4Cssp(picBean);
                if (picId == -1) return this.result(Constants.RESULT_MESSAGE_PARAM, "入参不正确，传入的图像信息有误");
                photoBase64=bean.getBase64String();
            } else if (bean.getPicId() != 0) {
                picId=bean.getPicId();
                String picPath = commService.photoIsExist4Cssp(picId, Constants.PICTURE_TYPE_101);
                if (Constants.RESULT_MESSAGE_PARAM.equals(picPath)) {
                    message = "用户图片不存在";
                    logger.error("sfzh=" + bean.getSfzh() + " picId=" +picId + message);
                    return this.result(Constants.RESULT_MESSAGE_PARAM, message);
                }
                photoBase64 =ImageChangeUtil.getImageStr(picPath);
                picId=bean.getPicId();
            }else{
                return result(Constants.RESULT_MESSAGE_EMPTY, "图片信息不可为空", null);
            }
            if(picId>0){
                PicBean perPicBean = new PicBean();
                perPicBean.setPicType(Constants.PICTURE_TYPE_102);
                perPicId = commService.insertPicture4Cssp(perPicBean);
                if (perPicId <= 0) return this.result(Constants.RESULT_MESSAGE_PARAM, "存储图片信息错误");
                //待处理图片关联业务
                PicBusBean picBusBean = new PicBusBean();
                picBusBean.setPicId(picId);
                picBusBean.setPicStatus(Constants.PIXEL_BUSINESS_STATUS_00);
                picBusBean.setPicType(Constants.PICTURE_TYPE_101);
                long picBusId = commService.insertPictureBus4Cssp(picBusBean);
                if (picBusId > 0) {
                    // 调用像素接口预处理照片
                    JsonObject json = new JsonObject();
                    json.addProperty("base64String", photoBase64);
                    json.addProperty("pic_type", bean.getPicType());
                    json.addProperty("callbackUrl", bean.getCallbackUrl());
                    json.addProperty("Bus_id", perPicId);//返回信息修改相片id（已处理）
                    String url = PIXEL_URL + "uploadPicInfo";
                    String result = "";
                    PersonBean personBean = commService.getBasicPersonInfo4Cssp(bean.getSfzh(), bean.getXm());
                    long personId = 0;
                    if (personBean != null) personId = personBean.getPersonId();
                    try {
                        result = (String) getWebClient(url, json, String.class);
                    } catch (Exception e) {
                        //更新图片业务表中照片状态设置为预处理失败
                        PicBusBean failedBean = new PicBusBean();
                        failedBean.setPicId(picId);
                        failedBean.setPicStatus(Constants.PIXEL_BUSINESS_STATUS_04);
                        commService.updatePictureBus4Cssp(failedBean);
                        logger.error("连接像素服务器失败:"+new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()), e);
                    }
                    //添加响应信息判断
                    if("".equals(result)){
                        flag = false;//表示预处理失败
                    }
                    if(flag){
                        Map<String, Object> map = JsonHelper.jsonToMap(result);
                        message = (String) map.get("message");
                        if ((Constants.RESULT_MESSAGE_SUCCESS).equals(map.get("statusCode"))) {// 成功
                            //预存相片（已处理）信息关联业务、人员
                            PicBusBean perBusBean = new PicBusBean();
                            perBusBean.setPicId(perPicId);
                            perBusBean.setPersonId(personId);
                            perBusBean.setPicStatus(Constants.PIXEL_BUSINESS_STATUS_00);
                            perBusBean.setPicType(perPicBean.getPicType());
                            commService.insertPersonPic4Cssp(perBusBean);
                            commService.insertPictureBus4Cssp(perBusBean);
                            message = "预处理成功";
                            statusCode = (String) map.get("statusCode");
                            //预处理照片关联业务、人员
                            commService.photoTOPersonAndBus4Cssp(picId, personId,Constants.PICTURE_TYPE_101, statusCode, message);
                        }

                        PicBusBean failedBean = new PicBusBean();
                        failedBean.setPicId(picId);
                        failedBean.setPicStatus(Constants.PIXEL_BUSINESS_STATUS_03);
                        commService.updatePictureBus4Cssp(failedBean);
                    }
                }
            }
            String pic_status = Constants.PIXEL_BUSINESS_STATUS_03;
            if(!flag) {
                pic_status = Constants.PIXEL_BUSINESS_STATUS_04;
                message = "预处理失败";
            }
            //保存原始照片与处理后照片的关系
            csspService.insertApplyPic4Cssp(bean.getSfzh(),bean.getXm(), picId,pic_status,message,perPicId);//无论预处理失败或者成功，都保存上传的照片
            PicVO picVO = new PicVO();
            picVO.setPicId(perPicId);
            return new Result(statusCode, message, picVO);
        } catch (Exception e) {

            logger.info("uploadPicInfo-bean:\t" + JsonHelper.javaBeanToJson(bean),e);
            return error("服务器异常，请稍后再试!", null);
        }
    }

    /**
     * 照片处理返回接口
     *@param bean
     * @return
     */
    @RequestMapping(value = "/callbackPicInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result callbackPicInfo(@RequestBody PixelBean bean, @Context HttpServletRequest request) {
        try {
            if (org.apache.commons.lang3.StringUtils.isEmpty(bean.getBusId())
                    || org.apache.commons.lang3.StringUtils.isEmpty(bean.getStatus())) {
                return new Result("300", "入参不正确，入参不全", null);
            }
            if (!ImageChangeUtil.isIntegerByString(bean.getBusId()))
                return new Result("300", "入参不正确，入参不全", null);
            String status = bean.getStatus();
            if(!Constants.PIXEL_BUSINESS_STATUS_01.equals(status)
                    && !Constants.PIXEL_BUSINESS_STATUS_02.equals(status))
                return new Result("300", "入参不正确，入参不全", null);
            if (Constants.PIXEL_BUSINESS_STATUS_01.equals(status)) {
                if (org.apache.commons.lang3.StringUtils.isEmpty(bean.getBase64String())) {
                    return new Result("300", "入参不正确，入参不全", null);
                }
            }
            // 首先查询这个图片id是否存在
            PicBean picBean = new PicBean();
            picBean.setPicId(Long.parseLong(bean.getBusId()));
            picBean = commService.getPicture4Cssp(picBean);
            if (picBean == null || picBean.getPicId() == 0) return new Result("300", "业务ID不存在", null);
            if (Constants.PIXEL_BUSINESS_STATUS_01.equals(status)) {
                // 保存图片
                picBean.setPicBase64(bean.getBase64String());
                picBean.setPicType(Constants.PICTURE_TYPE_102);
                long picId = commService.updatePicture4Cssp(picBean);
                if(picId<0){
                    bean.setMessage(bean.getMessage()+",传入的图像信息有误,无法保存");
                }
            }
            //相片（已处理）信息修改关联业务信息
            PicBusBean perBusBean = new PicBusBean();
            perBusBean.setPicId(Long.parseLong(bean.getBusId()));
            perBusBean.setPicStatus(status);
            perBusBean.setPicType(Constants.PICTURE_TYPE_102);
            perBusBean.setPicMessage(bean.getMessage());
            commService.updatePictureBus4Cssp(perBusBean);
            return ok("回调成功", null);
        } catch (Exception e) {
            logger.info("uploadPicInfo-bean:\t" + JsonHelper.javaBeanToJson(bean),e);
            logger.error("服务器异常，请稍后再试!", e);
            return error("服务器异常，请稍后再试!", null);
        }
    }
    
    /**
     * 查询失败的审核记录
     *@param bean
     * @return
     */
    @RequestMapping(value = "/getVerifyRecord", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getVerifyRecord(@RequestBody SecQueryBean bean) {
    	String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "操作成功";
        try {
        	Page<VerifyRecordVo> page = testCsspService.getVerifyRecord4Other(bean);//查询出审核失败的记录 数据来源为测试数据  正式数据来源为第三方卡管
        	//根据查询出来的记录 更新申领表中申领记录的状态
        	List<VerifyRecordVo> data = page.getData();
        	if(data != null && data.size()>0){
        		for (VerifyRecordVo verifyRecordVo : data) {
    				List<CsspApplyBean> list = csspService.isExistApplyPersonInfo4Cssp(verifyRecordVo.getXm(), verifyRecordVo.getSfzh());//  申请数据来源 不一致   可能会有问题 。待解决
    				if(list != null && list.size()>0){
    					for (CsspApplyBean csspApplyBean : list) {
        					if(csspApplyBean.getSfzh().equals(verifyRecordVo.getSfzh()) && csspApplyBean.getXm().equals(verifyRecordVo.getXm())){
        						verifyRecordVo.setApplyId(csspApplyBean.getApplyId());
        						verifyRecordVo.setPicId(csspApplyBean.getPhotoBuzId());
        					}
        				}
    				}
    			}
        	}
        	return result(statusCode,message,page);// page 记录  存在不一样的数据
           
        } catch (Exception e) {
            logger.error("查询审核失败记录异常-bean:\t" + JsonHelper.javaBeanToJson(bean), e);
            return error("服务器异常，请稍后再试!", null);
        }
    }
    
    /**
     * 社保卡申请-获取申请记录详情
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getApplyCardInfoById", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getApplyCardInfoById(@RequestBody ApplyInfoBean bean) {
    	String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "操作成功";
        try {
        	CsspApplyInfoVo applyInfo= csspService.getApplyCardInfoById4Cssp(bean.getApplyId());
        	return result(statusCode,message,applyInfo);
        } catch (Exception e) {
            logger.error("查询记录异常-bean:\t" + JsonHelper.javaBeanToJson(bean), e);
            return error("服务器异常，请稍后再试!", null);
        }
    }
    
    /**
     * 社保卡申请-获取社保卡申请审核详情
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getVerifyDetail", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getVerifyDetail(@RequestBody ApplyInfoBean bean) {
    	String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "操作成功";
        try {
        	VerifyRecordVo verifyRecord= testCsspService.getVerifyDetail4Other(bean.getRecordId());
        	return result(statusCode,message,verifyRecord);
        } catch (Exception e) {
            logger.error("查询记录异常-bean:\t" + JsonHelper.javaBeanToJson(bean), e);
            return error("服务器异常，请稍后再试!", null);
        }
    }
    
    /**重新申领  并且将之前审核未通过记录改为已处理
     * 根据各地市要求选择：社保卡申领：相片处理需一段时间(此时参保人相片第三方处理尚未返回)，调用此方法把个人申领信息存入本地数据库,
     * 等待定时器扫描上传 根据实际情况改写此接口，入参出参不变 各地市提供接口
     * 把采集到的数据存到公服个人基本信息表 T_YTH_BASIC_PERSON_INFO
     * 把采集到的数据存到社保卡申请信息表 T_YTH_APPLYCARD_INFO
     * 性别	01男性;02女性;03未知
     * 民族存数据库为编码
     */
    @RequestMapping(value = "/reApplyCard", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result reApplyCard(@RequestBody CsspApplyBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "申领失败";
        CsspCardPickUpVO csspCardPickupVO = new CsspCardPickUpVO();
        //身份证&姓名
        if (StringUtils.isEmpty(bean.getSfzh()) && StringUtils.isEmpty(bean.getXm())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "姓名或身份证不能为空");
        }
        if (StringUtils.isEmpty(bean.getAccountProties())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "请选择户口性质");
        }
        if (StringUtils.isEmpty(bean.getSex())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "性别不能为空");
        }
        if (StringUtils.isEmpty(bean.getGuoji())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "国籍不能为空");
        }
        if (StringUtils.isEmpty(bean.getMobile())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "手机号码不能为空");
        }
        if (StringUtils.isEmpty(bean.getCertValidity())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "证件有效期不能为空");
        }
        // 民族
        String nation = bean.getNation();
        if (StringUtils.isNotEmpty(nation)) {
            nation = StringUtils.isNotBlank(nation) ? nation.replace("族", "") : "";
            bean.setNation(Constants.NATION_Code.get(nation));
        } else
            return result(Constants.RESULT_MESSAGE_EMPTY, "请选择民族");

        if (StringUtils.isEmpty(bean.getChannelcode())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "渠道类型不能为空");
        }
        //渠道类型：德生宝
        if ((Constants.TSB).equals(bean.getChannelcode())) {
            //设备号
            if (StringUtils.isEmpty(bean.getDeviceid())) {
                return result(Constants.RESULT_MESSAGE_EMPTY, "设备号不能为空");
            }
            //领卡地址
            if (StringUtils.isBlank(bean.getTsbAddress())) {
                bean.setCardAddress(commService.getTsbAddress4Sisp(bean.getDeviceid()));
            } else
                bean.setCardAddress(bean.getTsbAddress());
            bean.setAddrType("3");
        }
        if (StringUtils.isEmpty(bean.getCertType())) {
            bean.setCertType("01");//证件类型身份证（户口簿）
        }
 /*根据项目确定
//签名信息
            if (bean.getSignphotoId() == 0) {
                return result(Constants.RESULT_MESSAGE_EMPTY, "个人签名不能为空");
            }
    //领卡地址
        if (StringUtils.isBlank(bean.getTsbAddress())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "领卡地址不能为空");
        }
        if (StringUtils.isEmpty(bean.getBankCode()) || StringUtils.isEmpty(bean.getBankName())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "请选择银行信息");
        }
        if (StringUtils.isEmpty(bean.getPersonType())) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "请选择人员类别");
        }
*/
        //个人相片、身份证正、反面
        if (bean.getPhotoBuzId() == 0 || bean.getPicupId() == 0 || bean.getPicdownId() == 0) {
            return result(Constants.RESULT_MESSAGE_EMPTY, "个人相片、身份证正、反面照不能为空");
        }
        try {//出生日期
            if (StringUtils.isBlank(bean.getBirthday())&&"01".equals(bean.getCertType()))
                bean.setBirthday(CommUtil.getBirthdayByCertNum(bean.getSfzh()));
            if(StringUtils.isNotBlank(bean.getBirthday())) {
                try {
                    CommUtil.getFormatDateString(bean.getBirthday(), "yyyy-MM-dd");
                } catch (Exception e) {
                    return this.result(Constants.RESULT_MESSAGE_PARAM, "出生日期不符合格式:yyyy-MM-dd,请重新输入");
                }
            }
            // 查询之前是否在平台申请
//            CsspApplyBean personInfo = csspService.isExistApplyPersonInfo4Cssp(bean.getXm(), bean.getSfzh());
            List<CsspApplyBean>  personList = csspService.isExistApplyPersonInfo4Cssp(bean.getXm(), bean.getSfzh());
            if(personList != null && !personList.isEmpty()){
                bean.setPersonId(personList.get(0).getPersonId());
                // 社保卡申请是否允许重复申请,若不允许--新申领功能不允许重复
                if(!Constants.CONFIG_YES.equals(CARD_APPLY_IS_RE)) {
                    if (personList.get(0).getApplyId() != 0) {
                        String date = personList.get(0).getCreateTime();
                        message = "证件号" + bean.getSfzh() + "于" + date + "已申领，请勿重复申领";
                        return result(Constants.RESULT_MESSAGE_ERROR, message);
                    }
                }
            }
            //参保人相片信息（相片未处理）
            PicBean picBean = new PicBean();
            picBean.setPicId(bean.getPhotoBuzId());
            picBean = commService.getPicture4Cssp(picBean);
            if (picBean == null || picBean.getPicId() == 0) {
                message = "参保人相片图片不存在";
                logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPhotoBuzId());
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            //身份证正面信息
            String picUpPath = commService.photoIsExist4Cssp(bean.getPicupId(), Constants.PICTURE_TYPE_103);
            if (Constants.RESULT_MESSAGE_PARAM.equals(picUpPath)) {
                message = "身份证正面图片不存在";
                logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPicupId());
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            //身份证反面信息
            String picDownPath = commService.photoIsExist4Cssp(bean.getPicdownId(), Constants.PICTURE_TYPE_104);
            if (Constants.RESULT_MESSAGE_PARAM.equals(picDownPath)) {
                message = "身份证反面图片不存在";
                logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getPicdownId());
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            //电子签名信息 德生宝端需检测
            if (bean.getSignphotoId() != 0) {
                String signPhotoPath = commService.photoIsExist4Cssp(bean.getSignphotoId(), Constants.PICTURE_TYPE_105);
                if (Constants.RESULT_MESSAGE_PARAM.equals(signPhotoPath)) {
                    message = "电子签名图片不存在";
                    logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" + bean.getSignphotoId());
                    return this.result(Constants.RESULT_MESSAGE_PARAM, message);
                }
            }
            // 根据各地市要求 编写电子申领表
            bean.setScannPhoto("电子申领表");
            if (StringUtils.isEmpty(bean.getScannPhoto())) {
                return result(Constants.RESULT_MESSAGE_EMPTY, "电子申领表不能为空");
            }
            bean.setPhotoSource("02");
            bean.setIsUpload("N");
            bean.setUploadNum(0);
            int status = 0;
            PersonBean personBean = new PersonBean();
            BeanUtils.copyProperties(bean, personBean);
            personBean.setPhone(bean.getMobile());
            if (bean.getPersonId() != 0) {
                // 把个人基本信息表 T_YTH_BASIC_PERSON_INFO更新为采集到的数据
                status = commService.updatePersonInfo4Cssp(personBean);
                if (status > 0) status = 0;
                else
                    return this.result(Constants.RESULT_MESSAGE_ERROR, "保存个人信息出错");
            }
            if (bean.getPersonId() == 0) {
                // 把采集到的数据存到公服个人基本信息表 T_YTH_BASIC_PERSON_INFO
                status = commService.insertPersonInfo4Cssp(personBean);
                if (status > 0) bean.setPersonId(personBean.getPersonId());
                status = 0;
            }
            // 把采集到的数据存入社保卡申请信息表
            if (bean.getPersonId() != 0) {
                if (StringUtils.isNotBlank(bean.getAddrType()) || StringUtils.isNotBlank(bean.getCardAddress())) {
                    status = csspService.insertCardApplyAddr4Cssp(bean);// 把采集到的数据存入社保卡申请领卡地址表
                    if (status < 0) {
                        message = "保存领卡信息失败";
                        return result(statusCode, message);
                    }
                }
                bean.setRemark(bean.getChannelcode());//备注为申请渠道
                status = csspService.insertCardApplyInfo4Cssp(bean);
                if (status > 0) {
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                    message = "提交申领成功";
                    //重新申领提交成功  审核失败记录由未处理状态改为已处理
                    long isdealed = testCsspService.updateVerifyStatus4Other(bean.getSfzh(), bean.getXm());
                    if(isdealed > 0){
                    	 csspCardPickupVO.setSfzh(bean.getSfzh());
                         csspCardPickupVO.setXm(bean.getXm());
                         csspCardPickupVO.setTsbAddress(bean.getCardAddress());
                         return result(statusCode, message, csspCardPickupVO);
                    }else{
                    	return this.result(Constants.RESULT_MESSAGE_ERROR, "重新提交失败，请稍后再试...");
                    }
                    //测试 社保卡申请-存入制卡进度--项目使用可删除
//                    CardProgressVO vo=new CardProgressVO();
//                    vo.setSfzh(bean.getSfzh());
//                    vo.setXm(bean.getXm());
//                    vo.setApplytime(CommUtil.getNowDateLongStr());
//                    vo.setStatus("资料已成功提交，正在审核中");
//                    testCsspService.insertCardProgress4Other(vo);
                    //测试
                   
                }
            } else {
                logger.error("applyCard 保存个人基本信息失败" + JsonHelper.javaBeanToJson(bean));
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "申领失败";
            }
        } catch (Exception e) {
            logger.error("applyCard 保存数据错误：" + JsonHelper.javaBeanToJson(bean));
            logger.error("保存数据错误", e);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "申领失败";
        }
        return result(statusCode, message);
    }
    
    
    /* 查询吉林卡管数据字典 */
	@RequestMapping(value = "/getDicList", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getDicList(@RequestBody SecQueryBean bean) {

		logger.info("------------getDicList------------");
		
		 if (CommUtil.isEmptyStr(bean.getDicType())) {
			return error("数据字典类型不能为空");
		}
		
		try {
			List<CardDictVO> cardDictVO = csspService.getDicList4skcj(bean);
			
            return ok("查询数据字典成功",cardDictVO);
            
        } catch (Exception e) {
            logger.error("查询数据字典失败：" + e);
        }
        return error("查询数据字典失败");
	}
    
	
}
