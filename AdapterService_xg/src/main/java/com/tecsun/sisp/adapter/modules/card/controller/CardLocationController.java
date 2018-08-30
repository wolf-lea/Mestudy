package com.tecsun.sisp.adapter.modules.card.controller;

import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.card.entity.request.PersonInfoVO;
import com.tecsun.sisp.adapter.modules.card.entity.request.ResultBusMakeDetal;
import com.tecsun.sisp.adapter.modules.card.service.impl.CssCardServiceImpl;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;

/**
 * 卡位置查询
 * @author 菜鸟
 *
 */
@Controller
@RequestMapping(value="/adapter/csscard")
public class CardLocationController extends BaseController {
	
	@Autowired
	private CssCardServiceImpl cssCardService;
	
	/**
	 * 卡位置查询
	 * 
	 * @param bean
	 * @return
	 */
	@RequestMapping(value = "/getApplyCard", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result getApplyCard(@RequestBody PersonInfoVO bean) {
		ResultBusMakeDetal resultBusMakeDetal = new ResultBusMakeDetal();
		//String[] data = new String[] {};
		JSONObject data = new JSONObject();
		String kwz = "";
		if (StringUtils.isNotEmpty(bean.getCertNum()) && !" ".equals(bean.getCertNum())) {
			// 先去滞留卡中找卡位置
			resultBusMakeDetal = cssCardService.getCardwz4Card(bean.getCertNum()); // 用身份证号到滞留卡表中找条件regis为1对应的盒ID,箱ID，卡编号
			if (resultBusMakeDetal != null) {
				if (resultBusMakeDetal.getOrgAddress() != null
						&& resultBusMakeDetal.getBinNo() != null
						&& resultBusMakeDetal.getBoxNo() != null
						&& resultBusMakeDetal.getCardsn() != null) {
					if (resultBusMakeDetal.getOrgAddress().endsWith("网点")) {
						resultBusMakeDetal.setOrgAddress(resultBusMakeDetal.getOrgAddress()
								.substring(0, resultBusMakeDetal.getOrgAddress().length() - 2));
					}
					kwz = resultBusMakeDetal.getOrgAddress() + "网点" + resultBusMakeDetal.getBinNo()
							+ "箱" + resultBusMakeDetal.getBoxNo() + "盒"
							+ resultBusMakeDetal.getCardsn() + "张";
					if (resultBusMakeDetal.getBATCHNO() != null) {
						kwz = resultBusMakeDetal.getOrgAddress() + "网点"
								+ resultBusMakeDetal.getBinNo() + "箱"
								+ resultBusMakeDetal.getBoxNo() + "盒"
								+ resultBusMakeDetal.getCardsn() + "张"
								+ resultBusMakeDetal.getBATCHNO() + "批次";
					}
					String name = resultBusMakeDetal.getName();
					String certnum = resultBusMakeDetal.getCertNum();
					data.put("kwz", kwz);
					data.put("name", name);
					data.put("certnum", certnum);
					if (resultBusMakeDetal.getRegisStatus().equals("1")) {
						String kzt = "未申领";
						String kzg = "是";
						//data = new String[] { kwz, kzt, kzg, name, certnum };
						data.put("kzt", kzt);
						data.put("kzg", kzg);
						
						return result("200", "查找成功", data);
						// System.out.println("==="+page.getData().get(0)+page.getData().get(1)+page.getData().get(2));
					} else {
						// List<BusApplyPicVO>
						// applybeans=csspQueryService.getApplytime(bean.getCertNum());
						String kzt = "已申领";
						String kzg = "否";
						data.put("kzt", kzt);
						data.put("kzg", kzg);
						//data = new String[] { kwz, kzt, kzg, name, certnum };
						return result("201", "卡已发放", data);
						// System.out.println("==="+page.getData().get(0)+page.getData().get(1)+page.getData().get(2));
					}
				} else {
					return result("202", "查找失败");
				}
			} else {
				return result("202", "卡片不存在");
			}
		} else {
			return result("0", "未发现身份证信息！");
		}
	}
}
