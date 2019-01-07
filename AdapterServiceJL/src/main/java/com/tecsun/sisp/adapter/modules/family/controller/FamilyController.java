package com.tecsun.sisp.adapter.modules.family.controller;

import com.tecsun.sisp.adapter.common.util.CommUtil;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.IdCardUtil;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.family.entity.request.MyFamilyBean;
import com.tecsun.sisp.adapter.modules.family.service.impl.MyFamilyServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 家庭业务
 *
 * @author admin
 *
 */
@Controller
@RequestMapping(value = "/adapter/family")
public class FamilyController extends BaseController {
	private static final Logger log = Logger.getLogger(FamilyController.class);
	@Autowired
	MyFamilyServiceImpl myFamilyService;

	@RequestMapping(value = "/getMyFamilyMember", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getMyFamilyMember(@RequestBody MyFamilyBean bean) {
		log.info("-----getMyFamilyMember:------");
		if (CommUtil.isEmptyStr(bean.getSfzh())) {
			return error("身份证号不能为空");
		}
		List<MyFamilyBean> myFamilyList = myFamilyService.getFamilyList4cssp(bean
				.getSfzh());
		if (myFamilyList.size() > 0) {
			return new Result(Constants.RESULT_MESSAGE_SUCCESS, "查询成功",
					myFamilyList);
		} else {
			return error("家庭成员信息不存在");
		}
	}

	@RequestMapping(value = "/addMember", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result addMember(@RequestBody MyFamilyBean bean) {

		log.info("------------addMember------------");

		if (CommUtil.isEmptyStr(bean.getMasterSfzh())) {
			return error("帐号人身份证号不能为空");
		} else if (CommUtil.isEmptyStr(bean.getSfzh())) {
			return error("成员身份证号不能为空");
		} else if (CommUtil.isEmptyStr(bean.getName())) {
			return error("成员姓名不能为空");
		} else if (CommUtil.isEmptyStr(bean.getRelationship())) {
			return error("与该成员的关系不能为空");
		}
//		bean.setPhone("");
		bean.setSex(String.valueOf(IdCardUtil.getGenderById(bean.getSfzh())));
		if("-1".equals(bean.getSex())) {
			return error("身份证号有误");
		}

		//添加成员前判断该成员是否已存在
		MyFamilyBean info = myFamilyService.getFamilyPersonInfo4cssp(bean);
		if(!CommUtil.isEmptyStr(info)){
			//是否删除 0：是  1：否
			if("1".equals(info.getIsDelete())){
				return error("添加失败，该成员信息已存在");
			}else{
				//修改状态
				int one = myFamilyService.updateStatusById4cssp(info);
				if(one > 0){
					return ok("添加成员成功!");
				}else{
					return error("添加成员失败");
				}
			}
		}

		if (myFamilyService.addFamily4cssp(bean) > 0) {
			return ok("添加成员成功!");
		} else {
			return error("添加成员失败");
		}
	}

	@RequestMapping(value = "/updateMember", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result updateMember(@RequestBody MyFamilyBean bean) {
		log.info("------------updateMember------------");
		if (CommUtil.isEmptyStr(bean.getId())) {
			return error("成员ID不能为空");
		}
		if (myFamilyService.updateFamilyById4cssp(bean) > 0) {
			return result(Constants.RESULT_MESSAGE_SUCCESS, "修改成员信息成功");
		} else {
			return error("修改成员信息失败");
		}
	}

	@RequestMapping(value = "/delMember", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result delMember(@RequestBody MyFamilyBean bean) {
		log.info("------------delMember------------");
		if (CommUtil.isEmptyStr(bean.getId())) {
			return error("成员ID不能为空");
		} else if(CommUtil.isEmptyStr(bean.getMasterSfzh())){
			return error("帐号所属人身份证号不能为空");
		}
		if (myFamilyService.deleteFamily4cssp(bean) > 0) {
			return result(Constants.RESULT_MESSAGE_SUCCESS, "删除成员成功");
		} else {
			return error("删除成员失败");
		}
	}
}
