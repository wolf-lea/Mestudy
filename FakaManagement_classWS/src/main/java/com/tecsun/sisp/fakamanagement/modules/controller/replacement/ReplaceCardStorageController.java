package com.tecsun.sisp.fakamanagement.modules.controller.replacement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.fakamanagement.common.Constants;
import com.tecsun.sisp.fakamanagement.common.Page;
import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.replacement.ReplaceCardDetailBean;
import com.tecsun.sisp.fakamanagement.modules.entity.param.replacement.ReplaceCardStorageBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.common.DistinctAndBankVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.replacement.ReplaceCardDetailVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.replacement.ReplaceCardStorageVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.statistics.ORGVO;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.replacement.ReplaceCardStorageServiceImpl;

/**
 * @author tanzhiyong
 * @version
 * 
 *          创建时间：2018年10月23日 上午10:23:00 说明：预制卡入库
 */
@Controller
@RequestMapping(value = "/faka/cardStorage")
public class ReplaceCardStorageController extends BaseController {

	public final static Logger logger = Logger
			.getLogger(ReplaceCardStorageController.class);

	@Autowired
	private ReplaceCardStorageServiceImpl replaceCardStorageServiceImpl;

	@Autowired
	private LoginUserServiceImpl loginUserService;

	/*
	 * 查询预制卡盒号列表
	 * 
	 * @param bean
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getReplaceCardBox", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getReplaceCardBox(@RequestBody ReplaceCardStorageBean bean,
			HttpServletRequest request) {
		logger.info("---------------ReplaceCardStorageController: getReplaceCardBox---------------");
		String message = "操作成功";
		int total = 0;
		String startNum = null;
		String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
		List<ReplaceCardStorageVO> replaceCardStorageVOList = new ArrayList<>();
		int pagesize = request.getParameter("pagesize") != null ? Integer
				.parseInt(request.getParameter("pagesize")) : 10;
		int pageno = request.getParameter("pageno") != null ? Integer
				.parseInt(request.getParameter("pageno")) : 1;
		Page<ReplaceCardStorageVO> page = new Page<>(pageno, pagesize);
		if (!StringUtils.isEmpty(bean.getCardNoStart())) {
			startNum = bean.getCardNoStart().substring(1);
			bean.setCardNoStart(startNum);
		}
		try {
			// 去除查询条件两端空格
			if (null != bean.getBoxNo() && !"".equals(bean.getBoxNo())) {
				bean.setBoxNo(bean.getBoxNo().trim());
			}
			page = replaceCardStorageServiceImpl.getReplaceCardBox(bean, page);
			if (null != page && page.getData().size() > 0) {
				replaceCardStorageVOList = page.getData();
				total = replaceCardStorageVOList.size();
				for (int i = 0; i < replaceCardStorageVOList.size(); i++) {
					ReplaceCardStorageVO boxVO = replaceCardStorageVOList
							.get(i);
					String str = boxVO.getCardNOPerBox().replace("*", "");
					boxVO.setCardNOPerBox(str);
					// 数据字典转换
					// 通过银行id 查询到银行名称
					String bankName = "";
					if (!StringUtils.isEmpty(boxVO.getBank())) {

						DistinctAndBankVO distinctAndBankVO = replaceCardStorageServiceImpl
								.getFkwdById(boxVO.getBank());
						if (null != distinctAndBankVO) {
							bankName = distinctAndBankVO.getName();
						}
					}
					replaceCardStorageVOList.get(i).setBank(bankName);
					replaceCardStorageVOList.get(i).setStatus(
							Constants.REPLACECARD_BOX_STATUS.get(boxVO
									.getStatus()));
				}
			} else {
				message = "查无数据";
				statusCode = Constants.RESULT_MESSAGE_ERROR;
				return result(statusCode, message);
			}

		} catch (Exception e) {
			e.printStackTrace();
			if (null != e.getCause()) {
				message = e.getCause().getMessage();
			} else {
				message = e.getMessage();
			}
			logger.error("查询临时卡盒异常：" + message);
		}
		return result(statusCode, total, message, page);
	}

	/*
	 * 预制卡银行接收查询
	 * 
	 * @param bean
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getReplaceCardInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getReplaceCardInfo(@RequestBody ReplaceCardStorageBean bean,
			HttpServletRequest request) {
		logger.info("---------------ReplaceCardStorageController: getReplaceCardInfo---------------");
		String startNum = null;
		String message = "查询成功";
		// 判断预制卡读取格式。
		if (!StringUtils.isEmpty(bean.getCardNoStart())) {
			startNum = bean.getCardNoStart().substring(0, 1);
			if (startNum.equals("*")) {
				startNum = bean.getCardNoStart().substring(1);
			}
		}
		try {
			int pagesize = request.getParameter("pagesize") != null ? Integer
					.parseInt(request.getParameter("pagesize")) : 12;
			int pageno = request.getParameter("pageno") != null ? Integer
					.parseInt(request.getParameter("pageno")) : 1;
			Page<ReplaceCardStorageVO> page = new Page<>(pageno, pagesize);
			ORGVO orgVO = loginUserService.getRkwd(Integer.valueOf(bean
					.getUserid()));
			if (orgVO == null) {
				message = "查无数据";
				return error(message);
			}
			ReplaceCardStorageVO replaceCardStorageVO = new ReplaceCardStorageVO();
			replaceCardStorageVO.setBoxNo(bean.getBoxNo());
			replaceCardStorageVO.setCardNoStart(startNum);
			replaceCardStorageVO.setStatus(bean.getStatus());
			replaceCardStorageVO.setBank(orgVO.getOrgcode());
			page = replaceCardStorageServiceImpl.getBankNetBoxList(page,
					replaceCardStorageVO);
			if (page != null && page.getCount() > 0) {
				for (int i = 0; i < page.getData().size(); i++) {
					ReplaceCardStorageVO vo = page.getData().get(i);
					vo.setCardNOPerBox(vo.getCardNoStart() + "-"
							+ vo.getCardNoEnd());
					String status = vo.getStatus();
					String bank = replaceCardStorageServiceImpl.getBankName(vo
							.getBank());
					vo.setBank(bank);
					if ("00".equals(status)) {
						vo.setStatus("未下发");
					} else if ("01".equals(status)) {
						vo.setStatus("已接收");
					} else if ("02".equals(status)) {
						vo.setStatus("部分下发");
					} else {
						vo.setStatus("全部下发");
					}
				}
				logger.info("查询预制卡市银行接收列表成功！");
				return ok(page.getCount(), "查询预制卡市银行接收列表成功！", page);
			}
		} catch (Exception e) {
			logger.error("查询预制卡市银行接收列表异常！：" + e);
			return error(e.getMessage());
		}

		return error("查无数据");
	}

	/*
	 * 预制卡网点接收查询
	 * 
	 * @param bean
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getBankOutletReplaceCardInfo", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getBankOutletReplaceCardInfo(
			@RequestBody ReplaceCardDetailBean bean, HttpServletRequest request) {

		logger.info("---------------ReplaceCardStorageController: getBankOutletReplaceCardInfo---------------");
		String message = "";
		try {
			int pagesize = request.getParameter("pagesize") != null ? Integer
					.parseInt(request.getParameter("pagesize")) : 12;
			int pageno = request.getParameter("pageno") != null ? Integer
					.parseInt(request.getParameter("pageno")) : 1;
			Page<ReplaceCardDetailVO> page = new Page<>(pageno, pagesize);
			ORGVO orgVO = replaceCardStorageServiceImpl.getRkwd(bean
					.getUserid());
			if (orgVO == null) {
				message = "查无数据";
				return error(message);
			}
			bean.setRkwd(orgVO.getOrgcode());
			if (!StringUtils.isEmpty(bean.getReplaceCardNo())) {
				String str = bean.getReplaceCardNo().substring(0, 1);
				if (!str.equals("*")) {
					message = "卡号格式错误";
					return error(message);
				}
			}
			page = replaceCardStorageServiceImpl.getBankOutletReplaceCard(page,
					bean);
			if (page != null && page.getCount() > 0) {
				DistinctAndBankVO distinctAndBankVO = loginUserService
						.getFkwdById(Integer.parseInt(orgVO.getParentId()));
				for (int i = 0; i < page.getData().size(); i++) {
					ReplaceCardDetailVO vo = page.getData().get(i);
					vo.setBank(distinctAndBankVO.getName());
					vo.setRkwd(orgVO.getOrgname());
					String status = vo.getStatus();
					if (status.equals("00")) {
						vo.setStatus("初始（入库）");
					} else if (status.equals("01")) {
						vo.setStatus("已发放");
					} else if (status.equals("02")) {
						vo.setStatus("发放失败");
					} else if (status.equals("03")) {
						vo.setStatus("待激活");
					}
				}
				logger.info("查询预制卡网点接收列表成功！");
				message = "查询预制卡网点接收列表成功！";
				return ok(page.getCount(), message, page);
			}
		} catch (Exception e) {
			logger.error("查询预制卡网点接收列表异常！：" + e);
			return error(e.getMessage());
		}
		return error("查无数据");
	}

	/*
	 * 新增预制卡
	 * 
	 * @param bean
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insertReplaceCard", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result insertReplaceCard(@RequestBody ReplaceCardStorageBean bean) {

		logger.info("---------------ReplaceCardStorageController: insertReplaceCard---------------");

		Integer startNum = 0;
		Integer endNum = 0;
		Integer status = 0;
		Integer total = 0;
		String id = null;
		String message = "新增成功";

		if (StringUtils.isEmpty(bean.getBoxNo()))
			return this.error("请输入盒号");
		if (StringUtils.isEmpty(bean.getCardNoStart()))
			return this.error("预制卡开始编号不能为空，请输入预制卡编号");
		if (StringUtils.isEmpty(bean.getBoxNo()))
			return this.error("预制卡结束编号不能为空，请输入预制卡编号");
		if (StringUtils.isEmpty(bean.getBank()))
			return this.error("银行编号不能为空，请输入预制卡编号");
		String strStart = bean.getCardNoStart().substring(0, 1);
		String strEnd = bean.getCardNoEnd().substring(0, 1);
		if (strStart.equals("*") && strEnd.equals("*")) {
			startNum = Integer.valueOf(bean.getCardNoStart().substring(1));
			endNum = Integer.valueOf(bean.getCardNoEnd().substring(1));
			if (startNum > endNum) {
				message = ("预制卡开始编号要小于或等于结束编号。");
				return error(message);
			}
		} else {
			message = "预制卡格式不符";
			return error(message);
		}
		List<String> list = new ArrayList<>();
		try {
			// 验证盒号是否唯一
			id = replaceCardStorageServiceImpl.getBoxId(bean.getBoxNo());
			if (!StringUtils.isEmpty(id)) {
				message = "该盒号已经存在";
				return error(message);
			} else {
				// 验证预制卡数量是否与输入框一致
				total = endNum - startNum + 1;
				if (bean.getCardNoSum() != total) {
					message = "预制卡读取数量与填写数量不一致";
					return error(message);
				} else {
					bean.setStatus("00");
					bean.setSurplusSum(total);
					bean.setCardNoStart(startNum.toString());
					bean.setCardNoEnd(endNum.toString());
					// 验证临时卡编号是否存在
					list = replaceCardStorageServiceImpl
							.getBoxIdbyReplaceCardNo(bean);
					if (list.size() > 0) {
						message = "卡编号已存在";
						return error(message);
					}
					bean.setCardNoStart(strStart + startNum.toString());
					bean.setCardNoEnd(strEnd + endNum.toString());
					status = replaceCardStorageServiceImpl
							.insertReplaceCardInfo(bean);
					if (status > 0) {
						status = 0;
					} else {
						message = "新增失败";
						return error(message);
					}
				}
			}
		} catch (Exception e) {
			logger.error("新增预制卡失败：" + e);
			return error(e.getMessage());
		}
		return ok(message);
	}

	/*
	 * 预制卡下发银行
	 * 
	 * @param bean
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insertReplaceCardToBank", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result insertReplaceCardToBank(
			@RequestBody ReplaceCardStorageBean bean) {
		logger.info("---------------ReplaceCardStorageController: insertReplaceCardToBank---------------");
		Integer status = 0;
		String message = "预制卡下发银行成功";
		if (StringUtils.isEmpty(bean.getClaim()))
			return this.error("领取人不能为空");
		ReplaceCardStorageVO replaceCardStorageVO = new ReplaceCardStorageVO();
		List<Integer> list = new ArrayList<>();
		bean.setStatus("01");
		try {
			if (bean.getIds().length == 0) {
				message = "请选择需要下发银行的数据";
				return error(message);
			}

			for (int i = 0; i < bean.getIds().length; i++) {
				replaceCardStorageVO = replaceCardStorageServiceImpl
						.getReplaceCardInfobyId(bean.getIds()[i]);
				if (!bean.getBank().equals(replaceCardStorageVO.getBank())) {
					list.add(bean.getIds()[i]);
				}
			}
			if (list.size() == 0) {
				for (int j = 0; j < bean.getIds().length; j++) {
					bean.setId(bean.getIds()[j]);
					status = replaceCardStorageServiceImpl
							.updateReplaceCardBoxInfo(bean);
					if (status > 0) {
						status = 0;
					} else {
						message = "新增失败";
						return error(message);
					}
				}
			} else {
				message = "序号为" + Arrays.toString(list.toArray())
						+ "的盒子由于所选银行与分配银行不一致";
				return error(message);
			}
		} catch (Exception e) {
			logger.error("预制卡下发银行失败：" + e);
			return error(e.getMessage());
		}
		return ok(message);
	}

	/*
	 * 预制卡下发网点
	 * 
	 * @param bean
	 * 
	 * @return
	 */
	@RequestMapping(value = "/insertReplaceCardBankOutlet", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result insertReplaceCardBankOutlet(
			@RequestBody ReplaceCardStorageBean bean) {

		logger.info("---------------ReplaceCardStorageController: insertReplaceCardBankOutlet---------------");
		Integer startNum = 0;
		Integer endNum = 0;
		Integer status = 0;
		Integer total = 0;
		Integer bankStartNum = 0;
		Integer bankEndNum = 0;
		String message = "下发网点成功";

		if (bean.getCardNoSum() == null)
			return this.error("请输入库卡数量");
		if (StringUtils.isEmpty(bean.getCardNoStart()))
			return this.error("第一张预制卡不能为空");
		if (StringUtils.isEmpty(bean.getCardNoEnd()))
			return this.error("最后一张预制卡不能为空");
		if (StringUtils.isEmpty(bean.getRkwd()))
			return this.error("入库网点不能为空");
		ReplaceCardStorageVO replaceCardStorageVO = new ReplaceCardStorageVO();
		ReplaceCardDetailBean replaceCardDetailBean = new ReplaceCardDetailBean();
		List<String> list = new ArrayList<>();
		String strStart = bean.getCardNoStart().substring(0, 1);
		String strEnd = bean.getCardNoEnd().substring(0, 1);
		if (strStart.equals("*") && strEnd.equals("*")) {
			startNum = Integer.valueOf(bean.getCardNoStart().substring(1));
			endNum = Integer.valueOf(bean.getCardNoEnd().substring(1));
			if (startNum>endNum) {
				message = "预制卡开始编号不能大于结束编号";
				return error(message);
			}
		} else {
			message = "预制卡卡号格式有误";
			return error(message);
		}
		try {
			// 验证预制卡数量是否与输入框一致
			total = endNum - startNum + 1;
			if (bean.getCardNoSum() != total) {
				message = "预制卡读取数量与填写数量不一致";
				return error(message);
			} else {
				replaceCardStorageVO = replaceCardStorageServiceImpl
						.getReplaceCardInfobyId(bean.getId());
				bankStartNum = Integer.valueOf(replaceCardStorageVO
						.getCardNoStart().substring(1));
				bankEndNum = Integer.valueOf(replaceCardStorageVO
						.getCardNoEnd().substring(1));
				// 验证剩余数量
				Integer Num = replaceCardStorageVO.getSurplusSum() - total;
				if (Num < 0) {
					message = "该盒号内没有足够的预制卡可发放";
					return error(message);
				}
				// 验证预制卡编号是否在区间内
				if (startNum >= bankStartNum && endNum <= bankEndNum) {
					// 判断有无已下发的卡号
					bean.setCardNoStart(startNum.toString());
					bean.setCardNoEnd(endNum.toString());
					list = replaceCardStorageServiceImpl
							.getReplaceCardDetailListId(bean);
					if (list.size() != 0) {
						message = "该批次中存在已下发的预制卡";
						return error(message);
					}
					for (int i = 0; i < total; i++) {
						Integer replaceCardNum = startNum + i;
						String replaceCardNo = "*" + replaceCardNum.toString();
						replaceCardDetailBean.setReplaceCardNo(replaceCardNo);
						replaceCardDetailBean.setRkwd(bean.getRkwd());
						replaceCardDetailBean.setBank(replaceCardStorageVO
								.getBank());
						replaceCardDetailBean.setStatus("00");
						// 新增replacecard_detail表数据
						status = replaceCardStorageServiceImpl
								.insertReplaceCardDetail(replaceCardDetailBean);
						if (status > 0) {
							status = 0;
						} else {
							message = "新增失败";
							return error(message);
						}
					}
					total = replaceCardStorageVO.getSurplusSum() - total;
					bean.setSurplusSum(total);
					if (total == 0) {
						bean.setStatus("03");
					} else {
						bean.setStatus("02");
					}
					// 修改REPLACECARD_BOX表状态
					status = replaceCardStorageServiceImpl
							.updateReplaceCardDetailStatus(bean);
					if (status > 0) {
						status = 0;
					} else {
						message = "修改失败";
						return error(message);
					}
				} else {
					message = "该区间内的预制卡编号存在不属于该盒号的编号，请核实";
					return error(message);
				}
			}
		} catch (Exception e) {
			logger.error("下发到网点失败：" + e);
			return error(e.getMessage());
		}
		return ok(message);
	}
}
