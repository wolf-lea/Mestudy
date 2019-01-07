package com.tecsun.sisp.adapter.modules.collection.controller;

import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.adapter.common.util.CommUtil;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.ImageChangeUtil;
import com.tecsun.sisp.adapter.common.util.JsonHelper;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.collection.entity.request.CollectionBean;
import com.tecsun.sisp.adapter.modules.collection.service.impl.InsuredCollectionServiceImpl;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl;

/**
 * 全民参保采集登记
 * 
 * @author 菜鸟
 * 
 */
@Controller
@RequestMapping(value = "/adapter/insuredCollection")
public class InsuredCollectionController extends BaseController {
	private final static Logger log = LoggerFactory.getLogger(InsuredCollectionController.class);
	@Autowired
	private CommServiceImpl commService;
	
	@Autowired
	private InsuredCollectionServiceImpl insuredService;

	/**
	 * 全民参保登记提交
	 * 
	 * @param bean
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/applyCardBySingle", method = RequestMethod.POST, produces = "application/json")
	public Result applyCardBySingle(@RequestBody CollectionBean bean) {
		if (CommUtil.isEmptyStr(bean.getCertNum())) {
			return error("证件号不能为空");
		} else if (CommUtil.isEmptyStr(bean.getName())) {
			return error("姓名");
		} else if (CommUtil.isEmptyStr(bean.getSex())) {
			return error("性别不能为空");
		} else if (CommUtil.isEmptyStr(bean.getNation())) {
			return error("民族不能为空");
		} else if (CommUtil.isEmptyStr(bean.getGuoji())) {
			return error("国籍不能为空");
		} else if (CommUtil.isEmptyStr(bean.getMobile()) && CommUtil.isEmptyStr(bean.getPhone())) {
			return error("手机号码或者固话必须填一个");
		} else if (CommUtil.isEmptyStr(bean.getAddress())) {
			return error("户籍地址不能为空");
		} else if (CommUtil.isEmptyStr(bean.getPhotoId())) {
			return error("个人相片不能为空");
		} else if (CommUtil.isEmptyStr(bean.getCertValidity())) {
			return error("证件有效期");
		} else if (CommUtil.isEmptyStr(bean.getIdcardPicIdDown())) {
			return error("身份证反面照片不能为空");
		} else if (CommUtil.isEmptyStr(bean.getIdcardPicIdUp())) {
			return error("身份证正面照片不能为空");
		} else if (CommUtil.isEmptyStr(bean.getRegionalCode())) {
			return error("区域编码不能为空");
		} else if (CommUtil.isEmptyStr(bean.getParmanentAddress())) {
			return error("常住地址不能为空");
		} else if (CommUtil.isEmptyStr(bean.getCertType())) {
			return error("证件类型不能为空");
		}

		JSONObject jsonData = new JSONObject();

		String photoPath = commService.photoIsExist4Cssp(bean.getPhotoId(),
				Constants.PICTURE_TYPE_305);
		if (CommUtil.isEmptyStr(photoPath)) {
			return error("个人相片不存在");
		}
		bean.setPhoto(ImageChangeUtil.getImageStr(photoPath));
		if ("01".equals(bean.getCertType())) {
			String picUpPath = commService.photoIsExist4Cssp(bean.getIdcardPicIdUp(),
					Constants.PICTURE_TYPE_301);
			String picDownPath = commService.photoIsExist4Cssp(bean.getIdcardPicIdDown(),
					Constants.PICTURE_TYPE_302);
			if (CommUtil.isEmptyStr(picUpPath) || CommUtil.isEmptyStr(picDownPath)) {
				return error("身份证照片不存在");
			}
			bean.setIdcard64StringUp(ImageChangeUtil.getImageStr(picUpPath));
			bean.setIdcard64StringDown(ImageChangeUtil.getImageStr(picDownPath));
		} else if ("07".equals(bean.getCertType())) {
			String picUpPath = commService.photoIsExist4Cssp(bean.getIdcardPicIdUp(),
					Constants.PICTURE_TYPE_303);
			String picDownPath = commService.photoIsExist4Cssp(bean.getIdcardPicIdDown(),
					Constants.PICTURE_TYPE_304);
			if (CommUtil.isEmptyStr(picUpPath) || CommUtil.isEmptyStr(picDownPath)) {
				return error("身份证照片不存在");
			}
			bean.setIdcard64StringUp(ImageChangeUtil.getImageStr(picUpPath));
			bean.setIdcard64StringDown(ImageChangeUtil.getImageStr(picDownPath));
		}

		jsonData.put("deviceid", bean.getDeviceid());
		jsonData.put("certNum", bean.getCertNum());
		jsonData.put("name", bean.getName());
		jsonData.put("sex", bean.getSex());
		jsonData.put("nation", bean.getNation());
		jsonData.put("guoji", bean.getGuoji());
		jsonData.put("mobile", bean.getMobile());
		jsonData.put("address", bean.getAddress());
		jsonData.put("photo", bean.getPhoto());
		jsonData.put("certValidity", bean.getCertValidity());
		jsonData.put("bankName", bean.getBankName());
		jsonData.put("idcard64StringUp", bean.getIdcard64StringUp());
		jsonData.put("idcard64StringDown", bean.getIdcard64StringDown());
		jsonData.put("adultFlag", bean.getAdultFlag());
		jsonData.put("phone", bean.getPhone());
		jsonData.put("regionalCode", bean.getRegionalCode());
		jsonData.put("hkProperty", bean.getHkProperty());
		jsonData.put("hkbNo", bean.getHkbNo());
		jsonData.put("familyNo", bean.getFamilyNo());
		jsonData.put("cbPlace", bean.getCbPlace());
		jsonData.put("fkPlace", bean.getFkPlace());
		jsonData.put("parmanentAddress", bean.getParmanentAddress());
		jsonData.put("certType", bean.getCertType());
		jsonData.put("bussType", bean.getBussType());
		jsonData.put("gis", bean.getGis());
		jsonData.put("personNo", bean.getPersonNo());
		jsonData.put("politicalStatus", bean.getPoliticalStatus());
		jsonData.put("isMarry", bean.getIsMarry());
		jsonData.put("education", bean.getEducation());
		jsonData.put("personStatus", bean.getPersonStatus());
		jsonData.put("hkType", bean.getHkType());
		jsonData.put("guardianName", bean.getGuardianName());
		jsonData.put("guardianCertNo", bean.getGuardianCertNo());
		jsonData.put("guardianContact", bean.getGuardianContact());
		jsonData.put("dealStatus", bean.getDealStatus());
		jsonData.put("isbaby", bean.getIsbaby());
		jsonData.put("synchroStatus", bean.getSynchroStatus());
		jsonData.put("insuredSituation", bean.getInsuredSituation());
		jsonData.put("noInsuredReason", bean.getNoInsuredReason());
		jsonData.put("jobSituation", bean.getJobSituation());
		jsonData.put("departmentNo", bean.getDepartmentNo());
		jsonData.put("departmentName", bean.getDepartmentName());
		jsonData.put("personInfo", bean.getPersonInfo());
		jsonData.put("noJobReason", bean.getNoJobReason());
		jsonData.put("isExpress", bean.getIsExpress());
		jsonData.put("expressName", bean.getExpressName());
		jsonData.put("expressPhone", bean.getExpressPhone());
		jsonData.put("expressAddress", bean.getExpressAddress());

		Map<String, Object> map = JsonHelper.jsonToMap(jsonData.toString());
		String message = "操作失败";
		String statusCode = RESULT_MESSAGE_ERROR;
		try {
			//将采集的数据添加的参保信息表中
			long id = insuredService.insertInsuredInfo4Cssp(map);
			if(id > 0){
				statusCode = RESULT_MESSAGE_SUCCESS;
				message = "操作成功";
			}
		} catch (Exception e) {
			message = "接口请求错误!";
			e.printStackTrace();
		}
		return new Result(statusCode, message, null);
	}

	/**
	 * 获取区域编码
	 * 
	 * @param params
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getDistListById", method = RequestMethod.POST, produces = "application/json")
	public Result getDistListById(@RequestBody Map<String, String> params) {
		String result = "";
		log.info("=====getDistListById-result:{}====", result);
		JSONObject res = JSONObject.fromObject(result);
		if (null == res) {
			return error("第三方接口请求错误");
		}

		return new Result(res.getString("statusCode"), res.getString("message"), res.get("result"));
	}
}
