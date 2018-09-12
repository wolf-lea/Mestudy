package com.tecsun.sisp.fakamanagement.modules.controller.card;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tecsun.sisp.fakamanagement.common.Result;
import com.tecsun.sisp.fakamanagement.modules.controller.BaseController;
import com.tecsun.sisp.fakamanagement.modules.entity.param.card.CardCabinetBean;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardBoxVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardCabinetInitVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardCabinetVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardFloorInitVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardFloorVO;
import com.tecsun.sisp.fakamanagement.modules.entity.result.card.CardInfoVO;
import com.tecsun.sisp.fakamanagement.modules.service.impl.card.CardCabinetServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.card.CardInfoServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.service.impl.loginuser.LoginUserServiceImpl;
import com.tecsun.sisp.fakamanagement.modules.util.PublicMethod;

/**
 * 
* @ClassName: CardCabinetController 
* @Description: TODO(卡柜操作接口类) 
* @author huangtao
* @date 2017年7月10日 上午11:50:36 
*
 */
@Controller
@RequestMapping(value = "/faka/cardCabinet")
public class CardCabinetController extends BaseController {
	
	public final static Logger logger = Logger.getLogger(CardCabinetController.class);
	
	@Autowired
	private CardCabinetServiceImpl  cardCabinetService;
	
	@Autowired
    private CardInfoServiceImpl cardInfoService;
	
	@Autowired
	private LoginUserServiceImpl loginUserService;
	
	/**
	 *新建卡柜 层 卡盒
	 *根据传入类型选择新增，新增卡柜，则默认新增卡柜下一层卡层，一个卡盒 需要传入用户。
	 *新增卡层， 则默认新增卡层下一个卡盒 需要传入卡柜id。
	 *新增卡盒，  需要传入卡层id。
	 * @param bean
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/newCardCabinet", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result newCardCabinet(@RequestBody CardCabinetBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		CardCabinetVO ccvo=null;
		CardFloorVO cfvo=null;
		CardBoxVO cbvo=null;
		String result="";
		switch (bean.getType()) {
		case 1:
			String re=useridToFkwd(bean);
			if(!re.equals("")){
				return error(re);
			}
			if(null==bean.getCcid()||bean.getCcid().equals("")){
				return error("卡柜名称为空");
			}
			ccvo=new CardCabinetVO();
			ccvo.setUserid(bean.getUserid());
			ccvo.setCcid(bean.getCcid());
			result=cardCabinetService.createCabinet(ccvo);
			if (result.indexOf("失败")>0) {
				return error(result);
			}
			break;
		case 2:
			if(null==bean.getCcaid()||bean.getCcaid()==0){
				return error("卡柜id为空");
			}
			cfvo=new CardFloorVO();
			cfvo.setCcid(bean.getCcaid());
			result=cardCabinetService.createFloor(cfvo);
			if (result.indexOf("失败")>0) {
				return error(result);
			}
			break;
		case 3:
			if(null==bean.getCfid()||bean.getCfid()==0){
				return error("卡柜层id为空");
			}
			if(null==bean.getMaxnum()||bean.getMaxnum()==0){
				return error("卡盒存放最大数量为空");
			}
			cbvo=new CardBoxVO();
			cbvo.setCfid(bean.getCfid());
			cbvo.setMaxnum(bean.getMaxnum());
			try {
				cardCabinetService.insertBox(cbvo);
			} catch (Exception e) {
				e.printStackTrace();
				return error("创建卡盒失败"+e.getMessage());
			}
			break;
		default:
			return error("没有这个新建类型！");
		}
		return ok("新建成功");
	}
	
	public String useridToFkwd(CardCabinetBean vo) {
		if(null==vo.getUserid()||vo.getUserid().equals("")){
			return "发卡机构为空";
		}
		String fkwd=loginUserService.getFkwd(vo.getUserid());
		if(null==fkwd||fkwd.equals("")){
			return "发卡机构获取编码为空";
		}
		vo.setUserid(fkwd);
		return "";
	}
	
	/**
	 * 查询卡柜 层 盒信息
	 * 查询下级需要传入上级id 如查询层需要传入柜id
	 * @param bean 
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/queryCardCabinet", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result queryCardCabinet(@RequestBody CardCabinetBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		List<Object> list =new ArrayList<>();
		try {
			switch (bean.getType()) {
			case 1:
				String re=useridToFkwd(bean);
				if(!re.equals("")){
					return error(re);
				}
				CardCabinetVO vo=new CardCabinetVO();
				vo.setUserid(bean.getUserid());
				List<CardCabinetVO> vos=cardCabinetService.queryCabinets(vo);
				list.addAll(vos);
				break;
			case 2:
				if(null==bean.getCcaid()||bean.getCcaid()==0){
					return error("卡柜id为空");
				}
				CardFloorVO fvo=new CardFloorVO();
				fvo.setCcid(bean.getCcaid());
				List<CardFloorVO> fvos=cardCabinetService.queryFloors(fvo);
				list.addAll(fvos);
				break;
			case 3:
				if(null==bean.getCfid()||bean.getCfid()==0){
					return error("卡柜层id为空");
				}
				CardBoxVO bvo=new CardBoxVO();
				bvo.setCfid(bean.getCfid());
				List<CardBoxVO> bvos=cardCabinetService.queryBoxs(bvo);
				list.addAll(getCardNums(bvos));
				break;
			default:
				return error("没有这个查询类型！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("查询卡柜信息出错", e);
			return error(e.getMessage());
		}
		return ok(list.size(), "查询成功",	list);
	}
	
	/**
	 * 获取卡盒里存放的卡数量
	 * @param list
	 * @return
	 */
	public List<CardBoxVO> getCardNums(List<CardBoxVO> list) {
		List<CardBoxVO> bvos=new ArrayList<>();
		for (CardBoxVO cardBoxVO : list) {//遍历盒获取盒类卡数
			CardBoxVO tempvo=cardInfoService.queryBoxMaxAndCardNum(cardBoxVO.getId());
			cardBoxVO.setCardnum(tempvo.getCardnum());
			bvos.add(cardBoxVO);
		}
		return bvos;
	}
	
	/**
	 * 传入卡柜id查询下面所有信息
	 * @param bean
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/initCabinet", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result initCabinet(@RequestBody CardCabinetBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		CardCabinetInitVO ccivo=new CardCabinetInitVO();
		List<CardFloorInitVO> flist =new ArrayList<>();
		if(null==bean.getCcaid()||bean.getCcaid()==0){
			return error("卡柜id为空");
		}
		CardFloorVO fvo=new CardFloorVO();
		fvo.setCcid(bean.getCcaid());
		List<CardFloorVO> fvos=cardCabinetService.queryFloors(fvo);//获取层
		for (CardFloorVO cardFloorVO : fvos) {//遍历层获取盒
			CardBoxVO bvo=new CardBoxVO();
			bvo.setCfid(cardFloorVO.getId());
			List<CardBoxVO> list=cardCabinetService.queryBoxs(bvo);
			List<CardBoxVO> bvos=getCardNums(list);
			CardFloorInitVO cfivo=new CardFloorInitVO();
			cfivo=(CardFloorInitVO) PublicMethod.createBean(CardFloorInitVO.class, cardFloorVO);
			cfivo.setBoxs(bvos);
			flist.add(cfivo);
		}
		ccivo.setFloors(flist);
		return ok("查询成功",ccivo);
	}
	
	/**
	 * 根据id删除卡柜、层、卡盒
	 * 只能删除空的卡柜、层、卡盒  如果有卡存放信息将不会被删除并返回错误信息
	 * @param bean
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/deleteCardCabinet", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result deleteCardCabinet(@RequestBody CardCabinetBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		try {
			Integer nums=cardCabinetService.isHaveCard(bean);
			if (nums>0) {
				return error("存放有卡片，不能删除！");
			}
			switch (bean.getType()) {
			case 1:
				CardCabinetVO vo=new CardCabinetVO();
				vo.setId(bean.getCcaid());
				cardCabinetService.deleteCabinet(vo);
				break;
			case 2:
				CardFloorVO fvo=new CardFloorVO();
				fvo.setId(bean.getCfid());
				cardCabinetService.deleteFloor(fvo);
				break;
			case 3:
				CardBoxVO bvo=new CardBoxVO();
				bvo.setId(bean.getCbid());
				cardCabinetService.deleteBoxs(bvo);
				break;
			default:
				return error("没有这个删除类型！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("删除卡柜信息出错", e);
			return errorORA(e, "删除失败");
		}
		return ok("删除成功");
	}
	
	/**
	 * 修改卡盒最大数量
	 * @param bean
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateBoxMaxNum", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result updateBoxMaxNum(@RequestBody CardCabinetBean bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		if(null==bean.getCbid()||bean.getCbid()==0){
			return error("卡盒id为空");
		}
		if(null==bean.getMaxnum()||bean.getMaxnum()==0){
			return error("最大数量为空");
		}
		try{
		cardCabinetService.updateMaxNum(bean);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("修改最大数量出错", e);
			return errorORA(e, "修改失败");
		}
		return ok("修改成功");
	}
	/**
	 * 修改卡柜名称
	 * @param bean
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/updateCcid", method = RequestMethod.POST, produces = "application/json")
	@ResponseBody
	public Result updateCcid(@RequestBody CardCabinetVO bean, @Context HttpServletRequest request, @Context HttpServletResponse response) {
		if(null==bean.getId()||bean.getId()==0){
			return error("卡柜id为空");
		}
		if(null==bean.getCcid()||bean.getCcid().equals("")){
			return error("卡柜名称为空");
		}
		try{
			cardCabinetService.updateCcid(bean);
		}catch(Exception e){
			e.printStackTrace();
			logger.error("修改卡柜名称出错", e);
			return errorORA(e, "修改失败");
		}
		return ok("修改成功");
	}
}
