package com.tecsun.sisp.adapter.modules.card.controller.schedule;

import com.tecsun.sisp.adapter.common.util.Config;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.ImageChangeUtil;
import com.tecsun.sisp.adapter.common.util.PicUtil;
import com.tecsun.sisp.adapter.modules.card.entity.request.CsspApplyBean;
import com.tecsun.sisp.adapter.modules.card.entity.request.XgApplyBean;
import com.tecsun.sisp.adapter.modules.card.service.impl.CsspServiceImpl;
import com.tecsun.sisp.adapter.modules.card.service.impl.SssmBusServiceImpl;
import com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class UploadScedule {
	private static Logger logger = LoggerFactory.getLogger(UploadScedule.class);

	@Autowired
	private CsspServiceImpl csspService;
	@Autowired
	private CommServiceImpl commService;

	@Autowired
	private SssmBusServiceImpl sssmBusService;

	// 定时上传社保卡照片到补换卡系统：条件isUpload（N 表示尚未上传或失败）参保人相片状态（01
	// 成功），上传次数小于规定次数uploadnumber
	public void upload() {
		// 对应xml quartz.xml
		logger.info("....定时任务.....上传处理成功照片至补换卡系统.....开始执行.......");
		try {
			String message = "";
			// 照片上传次数限制
			long uploadNum = 0;
			String uploadnumber = Config.getInstance().get("card_apply_upload_num");
			if (StringUtils.isNotBlank(uploadnumber)
					&& ImageChangeUtil.isIntegerByString(uploadnumber))
				uploadNum = Long.parseLong(uploadnumber);

			// 固定条件 isUpload（N 表示尚未上传或失败）参保人相片状态（01 成功）
			// 社保卡申请上传次数限制(可选)
			List<CsspApplyBean> list = csspService.queryUploadData4Cssp(uploadNum);

			// 2、上传数据
			if (list != null && list.size() != 0) {
				for (CsspApplyBean bean : list) {
					if (StringUtils.isNotBlank(bean.getSfzh())) {
						// 调用提供接口上传
						boolean upload = false;// 是否成功上传
						String faileResonal = null;// 上传失败原因

						// 孝感--申领照片上传补换卡系统开始
						XgApplyBean applyBean = new XgApplyBean();
						applyBean.setCertNum(bean.getSfzh());
						if(bean.getPicStatus().equals("01")){
						System.out.println("=====照片地址:" + bean.getPicPath());
						applyBean.setPhotoUrl(Config.getInstance().get("picture_path_102") + "/"
								+ bean.getPicPath());
						applyBean.setIsSuccessPhoto("Y");
						
						}else{
						   CsspApplyBean csspBean = new CsspApplyBean();
						   csspBean = csspService.queryPicPath4Cssp(bean.getSfzh());
						   System.out.println("=====照片地址:" + csspBean.getPicPath());
						   applyBean.setPhotoUrl(Config.getInstance().get("picture_path_101") + "/"
									+ csspBean.getPicPath());
						   applyBean.setIsSuccessPhoto("N");
						}
						int status = sssmBusService.updateBasePersonInfo4sssm(applyBean);
						logger.info("====定时上传执行结果:{}===", status);
						if (status > 0) {
							upload = true;
							bean.setUploadNum(bean.getUploadNum() + 1);
						}
						if (upload) {
							bean.setIsUpload("Y");
							// 设置已上传标记
							logger.info("------设置已上传标志成功-------");
						} else {
							bean.setIsUpload("N");
							logger.info("-------上传照片到补换卡系统时接口返回插入失败---" + "身份证：" + bean.getSfzh()
									+ ",姓名：" + bean.getXm() + "，插入失败原因：" + faileResonal);
						}
						csspService.updateCardApplyInfo4Cssp(bean.getPersonId(),
								bean.getIsUpload(), bean.getUploadNum());
					} else
						logger.info("-------上传照片到补换卡系统时接口返回插入失败:无身份证 applyid:" + bean.getApplyId());

				}
			} else {
				logger.info("====没有要上传的数据===");
			}
		} catch (Exception e) {
			logger.error("....定时任务.....上传处理成功照片至补换卡系统.....执行失败.......", e);
		}
		logger.info("....定时任务.....上传处理成功照片至补换卡系统.....执行结束.......");
	}

	// 定时上传社保卡申请数据：条件isUpload（N 表示尚未上传或失败）参保人相片状态（01 成功），上传次数小于规定次数uploadnumber
	/*
	 * public void upload() { //对应xml quartz.xml
	 * logger.info("....定时任务.....上传本地个人申领数据至第三方.....开始执行......."); try { String
	 * message=""; //社保卡申请上传次数限制 long uploadNum=0; String uploadnumber=
	 * Config.getInstance().get("card_apply_upload_num");
	 * if(StringUtils.isNotBlank(uploadnumber)&&
	 * ImageChangeUtil.isIntegerByString(uploadnumber)) uploadNum=
	 * Long.parseLong(uploadnumber);
	 * 
	 * //1、查询可上传的个人申领数据 返回数据性别 01男性;02女性;03未知 民族存数据库为编码 // 固定条件 isUpload（N
	 * 表示尚未上传或失败）参保人相片状态（01 成功） //社保卡申请上传次数限制(可选) List<CsspApplyBean> list =
	 * csspService.queryUploadData4Cssp(uploadNum); //2、上传数据 if (list != null &&
	 * list.size() != 0) { for (CsspApplyBean bean : list) { if
	 * (StringUtils.isNotBlank(bean.getSfzh())) { //个人相片信息 String perPhotoPath =
	 * commService.photoIsExist4Cssp(bean.getPhotoBuzId(),
	 * Constants.PICTURE_TYPE_102); if
	 * (Constants.RESULT_MESSAGE_PARAM.equals(perPhotoPath)) { message =
	 * "个人相片不存在"; logger.error(message + "：sfzh=" + bean.getSfzh() + " picId=" +
	 * bean.getPhotoBuzId()); } else {
	 * bean.setPhoto64String(ImageChangeUtil.getImageStr(perPhotoPath)); }
	 * //身份证正面信息 String picUpPath =
	 * commService.photoIsExist4Cssp(bean.getPicupId(),
	 * Constants.PICTURE_TYPE_103); if
	 * (Constants.RESULT_MESSAGE_PARAM.equals(picUpPath)) { message =
	 * "身份证正面图片不存在"; logger.error(message + "：sfzh=" + bean.getSfzh() +
	 * " picId=" + bean.getPicupId()); } else {
	 * bean.setIdcard64StringUp(ImageChangeUtil.getImageStr(picUpPath)); }
	 * //身份证反面信息 String picDownPath =
	 * commService.photoIsExist4Cssp(bean.getPicdownId(),
	 * Constants.PICTURE_TYPE_104); if
	 * (Constants.RESULT_MESSAGE_PARAM.equals(picDownPath)) { message =
	 * "身份证反面图片不存在"; logger.error(message + "：sfzh=" + bean.getSfzh() +
	 * " picId=" + bean.getPicdownId()); } else {
	 * bean.setIdcard64StringDown(ImageChangeUtil.getImageStr(picDownPath)); }
	 * //电子签名信息 if(bean.getSignphotoId()!=0) { String signPhotoPath =
	 * commService.photoIsExist4Cssp(bean.getSignphotoId(),
	 * Constants.PICTURE_TYPE_105); if
	 * (Constants.RESULT_MESSAGE_PARAM.equals(signPhotoPath)) { message =
	 * "电子签名图片不存在"; logger.error(message + "：sfzh=" + bean.getSfzh() + " picId="
	 * + bean.getSignphotoId()); } else {
	 * bean.setSign64String(ImageChangeUtil.getImageStr(signPhotoPath)); } }
	 * //调用提供接口上传 boolean upload = false;//是否成功上传 String faileResonal =
	 * null;//上传失败原因
	 * 
	 * //孝感--采集数据上传补换卡系统开始 XgApplyBean applyBean = new XgApplyBean();
	 * applyBean.setName(bean.getXm());
	 * applyBean.setSex(bean.getSex().equals("03")?"05":bean.getSex()); String
	 * org = ""; if(StringUtils.isNotEmpty(bean.getDistinctId())){ org =
	 * sssmBusService.queryIssuingOrg4sssm(bean.getDistinctId()).getSoName();
	 * applyBean.setCertIssuingOrg(org); } applyBean.setCertType("03");
	 * applyBean.setCertNum(bean.getSfzh());
	 * applyBean.setCertValidity(bean.getCertValidity());
	 * applyBean.setNation(bean.getNation());
	 * applyBean.setBirthday(bean.getBirthday());
	 * applyBean.setPhotoUrl(Config.getInstance
	 * ().get("picture_path_102")+"/"+bean.getPicPath());
	 * applyBean.setAddress(bean.getAddress());
	 * applyBean.setPhone(bean.getPhone());
	 * applyBean.setMobile(bean.getMobile()); applyBean.setStatus("00");
	 * applyBean.setAddDate(bean.getCreateTime());
	 * applyBean.setPersonType(bean.getPersonType());
	 * applyBean.setPersonStatus(bean.getPersonStatus());
	 * applyBean.setAccountProties(bean.getAccountProties());
	 * applyBean.setZipCode(bean.getZipCode());
	 * applyBean.setGuoji(bean.getGuoji()); //
	 * applyBean.setApplyChannel(bean.getRemark());
	 * applyBean.setIsEnabled("01");
	 * applyBean.setAddUserId(Integer.parseInt(bean.getDistinctId()));
	 * applyBean.setOperatorId(Integer.parseInt(bean.getDistinctId()));
	 * //参保人历史记录表 applyBean.setBusType("01");//申领
	 * applyBean.setBusStatus("103");//业务已受理
	 * applyBean.setHisOperatorId(Integer.parseInt(bean.getDistinctId()));//操作人
	 * //个人申领业务表 applyBean.setBusDataType("00");//正常发起业务数据
	 * applyBean.setBankOpenType("01");//银行开户类型 -- 新申领/新开户
	 * applyBean.setProcessDataType("01");//表单数据处理 -- 本网点录入
	 * applyBean.setMakeCardType("00");//非现场制卡
	 * applyBean.setApStatus("03");//业务已受理 applyBean.setNetType("01");//网点类型 --
	 * 社保机构 Integer orgId =
	 * Integer.parseInt(sssmBusService.queryIssuingOrg4sssm(
	 * bean.getDistinctId()).getId()); applyBean.setNetId(orgId);//所属网点id
	 * applyBean.setChannel(bean.getRemark());
	 * if(StringUtils.isNotEmpty(bean.getAgentCertNo())){
	 * applyBean.setApplyType("02");//别人代办 }else {
	 * applyBean.setApplyType("01");//自行办理 }
	 * 
	 * applyBean.setIswritedata(1);//是否网点已对申领表单录入数据 -- 已经录入
	 * applyBean.setCardIssueNetType("01");//领卡网点类型 -- 社保机构
	 * applyBean.setCardIssueNetId(orgId);//领卡网点ID
	 * 
	 * int status = sssmBusService.insertBasePersonInfo4sssm(applyBean);
	 * 
	 * if(status>0){ applyBean.setPersonId(applyBean.getId()); Integer r =
	 * sssmBusService.insertBusPersonalApply4sssm(applyBean);//返回业务表主键id if(r >
	 * 0){ applyBean.setBusId(applyBean.getApId()); Integer t =
	 * sssmBusService.insertBusHisInfo4sssm(applyBean); if(t >0){ upload = true;
	 * } } }
	 * 
	 * //孝感--采集数据上传补换卡系统结束
	 * 
	 * bean.setUploadNum(bean.getUploadNum() + 1); if (upload) {
	 * bean.setIsUpload("Y"); //设置已上传标记 logger.info("------设置已上传标志成功-------"); }
	 * else { bean.setIsUpload("N"); logger.info("-------上传数据时接口返回插入失败---" +
	 * "身份证：" + bean.getSfzh() + ",姓名：" + bean.getXm() + "，插入失败原因：" +
	 * faileResonal); } csspService.updateCardApplyInfo4Cssp(bean.getPersonId(),
	 * bean.getIsUpload(), bean.getUploadNum()); }else
	 * logger.info("-------上传数据时接口返回插入失败:无身份证 applyid:"+bean.getApplyId()) ;
	 * 
	 * } } } catch (Exception e) {
	 * logger.error("....定时任务.....上传本地个人申领数据至第三方.....执行失败.......",e); }
	 * logger.info("....定时任务.....上传本地个人申领数据至第三方.....执行结束......."); }
	 */

	// 照片转化成base64字符串 path为照片路径
	public String getImgStr(String path) {
		logger.info("------定时任务图片path:" + path + "-----");
		if (StringUtils.isNotBlank(path)) {
			return PicUtil.GetImageStr(path);
		}
		return null;
	}
}