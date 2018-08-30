package com.tecsun.sisp.adapter.modules.card.controller;

import com.tecsun.sisp.adapter.common.util.*;
import com.tecsun.sisp.adapter.modules.card.entity.request.*;
import com.tecsun.sisp.adapter.modules.card.entity.response.*;
import com.tecsun.sisp.adapter.modules.card.service.impl.SssmBusServiceImpl;
import com.tecsun.sisp.adapter.modules.card.service.impl.TestCsspServiceImpl;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
import com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl;
import com.tecsun.sisp.adapter.modules.so.entity.response.TSBSssmVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**社保卡业务：滞留卡登记、精准发卡
 * Created by danmeng on 2017/5/12.
 */
@Controller
@RequestMapping(value = "/adapter/sssmbus")
public class SssmBusController extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(SssmBusController.class);

    @Autowired
    private SssmBusServiceImpl sssmBusService;
    @Autowired
    private CommServiceImpl commService;

    @Autowired
    private TestCsspServiceImpl testCsspService;
    /**配置信息：盒中最大的卡数量*/
    private   String  CARDMAXSIZE = Config.getInstance().get("cardmaxsize");
    /**配置信息：箱中最大的盒数量*/
    private   String  CARBOXCOUNT = Config.getInstance().get("carboxcount");
    /**配置信息：滞留卡领取是否支持邮寄*/
    private   String  CARD_IS_EXPRESS = Config.getInstance().get("card.is.express");
    /**配置信息：滞留卡领取是否允许重复领取（用于测试）*/
    private   String  CARD_GET_AGAIN = Config.getInstance().get("card.get.again");

    /**
     * 社保卡业务-登录滞留卡管理
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/LoginSssm", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result LoginSssm(@RequestBody LoginSssmBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getLoginName()) || StringUtils.isBlank(bean.getPassword())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入用户名和密码");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "登录失败";
        try {
            bean.setPassword(CommUtil.generateValue(bean.getPassword()).toUpperCase());
            int isLogin = sssmBusService.isLoginSssm4Sssm(bean.getLoginName(), bean.getPassword());
            if (isLogin > 0) {
                JedisUtil.setValue("sssm_username", bean.getLoginName());
                message = "登录成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
            } else {
                message = "用户名或密码错误";
            }
        } catch (Exception e) {
            logger.error("社保卡业务-卡位置查询失败：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 社保卡业务-卡位置和领卡资格查询
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/queryCardLocation", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result queryCardLocation(@RequestBody SecQueryBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh())||StringUtils.isBlank(bean.getXm())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入身份证号和姓名");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "查找失败";
        try {
            //用身份证号到滞留卡表中找对应的盒ID,箱ID，卡编号
            List<CardLocationVO> list = sssmBusService.getCardLocationList4Sssm(bean.getSfzh(),bean.getXm());
            CardLocationVO vo =null;
            if(list!=null&&list.size()>0){//正常情况只有一条数据，测试除外
                vo=list.get(0);
            }
            String kwz = "";
            if (vo != null) {
                if (vo.getOrgAddress() != null && vo.getBinNo() != null && vo.getBoxNo() != null && vo.getCardsn() != null) {
                    if (vo.getOrgAddress().endsWith("网点")) {
                        vo.setOrgAddress(vo.getOrgAddress().substring(0, vo.getOrgAddress().length() - 2));
                    }
                    kwz = vo.getOrgAddress() + "网点" + vo.getBinNo() + "箱" + vo.getBoxNo() + "盒" + vo.getCardsn() + "张";
                    if (StringUtils.isNotBlank(vo.getBatchno())) {
                        kwz = kwz + vo.getBatchno() + "批次";
                    }
                    vo.setCardLocation(kwz);
                    if (("1").equals(vo.getRegisStatus())) {
                        vo.setCardStatus("未申领");
                        vo.setIsGetCard("是");
                        message = "查找成功";
                        statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                    } else if (("0").equals(vo.getRegisStatus())) {
                        vo.setCardStatus("已申领");
                        vo.setIsGetCard("否");
                        message = "社保卡已发放";
                        statusCode = Constants.RESULT_MESSAGE_EMPTY;
                    }
                    return result(statusCode, message, vo);
                }
            } else {
                message = "暂无该用户社保卡信息";
            }
        } catch (Exception e) {
            logger.error("社保卡业务-卡位置查询失败：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 社保卡业务-获取网点信息
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getOrgInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getOrgInfo(@RequestBody SecQueryBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getDeviceid())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入设备编号");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "查找失败";
        try {
            TSBSssmVO tsbSssmVO = commService.getTsbSssmInfo4Sisp(bean.getDeviceid());
            if (tsbSssmVO == null || (tsbSssmVO.getOrgId() == null || tsbSssmVO.getOrgId() == 0)) {
                return result("301", "请工作人员把德生宝和社保网点关联起来");
            } else {
                tsbSssmVO = sssmBusService.getOrgInfo4Sssm(tsbSssmVO.getOrgId());
                if (tsbSssmVO != null) {
                    tsbSssmVO.setCardmaxsize(Config.getInstance().get("cardmaxsize"));
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                    message = "查找成功";
                    return result(statusCode, message, tsbSssmVO);

                } else {
                    message = "未找到网点名称";
                }
            }
        } catch (Exception e) {
            logger.error("社保卡业务-获取网点名称：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 社保卡业务-查询所有网点信息
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getAllOrgInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getAllOrgInfo(@RequestBody SecQueryBean bean) throws Exception {

        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "查找失败";
        try {
                List<TSBSssmVO> list  = sssmBusService.getAllOrgInfo4Sssm();
                if (list != null&&!list.isEmpty()) {
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                    message = "查找成功";
                    return result(statusCode, message, list);
                } else {
                    message = "未找到网点";
                }
        } catch (Exception e) {
            logger.error("社保卡业务-查询所有网点信息：", e);
        }
        return result(statusCode, message);
    }
    /**
     * 社保卡业务-装卡情况查询
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getRetentionCount", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getRetentionCount(@RequestBody RetentionCardBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        if (StringUtils.isBlank(bean.getOrgName())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入网点名称");
        }
        if (StringUtils.isBlank(bean.getBoxNo())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入盒号");
        }
        if (StringUtils.isBlank(bean.getBinNo())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入箱号");
        }
        if (bean.getNowcount() == 0) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入装盒数量");
        }
        try {
            List<RetentionCardVO> cardVOList = sssmBusService.getRetentionCount4Sssm(bean);
            if (cardVOList != null && !cardVOList.isEmpty()) {
                if (cardVOList.get(0).getNowcount() < bean.getNowcount()) {
                    Integer card = bean.getNowcount() - cardVOList.get(0).getNowcount();
                    message = "本盒已装" + cardVOList.get(0).getNowcount() + "张卡，还可以装" + card + "张卡";
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                } else {
                    message = "本盒已满，不可装卡";
                }
            } else {
                message = "没有找到所要查的装卡位置";
            }
        } catch (Exception e) {
            logger.error("社保卡业务-装箱情况查询：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 社保卡业务-零散卡采集
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/colScatterCard", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result colScatterCard(@RequestBody RetentionCardBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getOrgName()) ||bean.getOrgId()==0||  StringUtils.isBlank(bean.getBoxNo())
                || StringUtils.isBlank(bean.getBinNo()) || bean.getNowcount() == 0) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入装盒信息");
        }
        if(StringUtils.isBlank(bean.getSfzh())||StringUtils.isBlank(bean.getXm())||StringUtils.isBlank(bean.getReason())){
            return result(Constants.RESULT_MESSAGE_PARAM, "请输入身份证号、姓名、滞留原因");
        }
        if(StringUtils.isBlank(bean.getSbkh())){
            return result(Constants.RESULT_MESSAGE_PARAM, "请输入社保卡号");
        }
        if (StringUtils.isBlank(bean.getRetenTime())) {
            bean.setRetenTime(CommUtil.getNowDateLongStr());
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {//查询滞留卡信息(是否登记过)
            List<RetentionCardVO> list = sssmBusService.getCardInfo4Sssm(bean.getSfzh(), bean.getSbkh());
            if (list == null || list.isEmpty()) {
                //查询卡片实时数量
                List<RetentionCardVO> cardVOList = sssmBusService.getRetentionCount4Sssm(bean);
                if (cardVOList != null && !cardVOList.isEmpty()) {
                    if (cardVOList.get(0).getNowcount() < bean.getNowcount()) {
                        String username = JedisUtil.getValue("sssm_username");
                        bean.setAdduser(username);
                        bean.setRegisStatus("1");//卡片状态 发放状态 0，已发；1，未发
                        bean.setStatus(0);//0：在盒中1：分散中2：已发放
                        bean.setBoxId(cardVOList.get(0).getBoxId());
                        bean.setCardSn(cardVOList.get(0).getNowcount() + 1);
                        int r = sssmBusService.insertRetentionCard4Sssm(bean);
                        if (r > 0) {//更改滞留卡盒信息(增加数量)
                            r = sssmBusService.updateRetentionBox4Sssm(cardVOList.get(0).getBoxId());
                            if (r > 0) {
                                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                                message = "登记成功";
                            }
                        }
                        Map<String,String>map=new HashMap<String,String>();
                        if (bean.getOrgName().endsWith("网点")) {
                            bean.setOrgName(bean.getOrgName().substring(0, bean.getOrgName().length() - 2));
                        }
                        map.put("cardLocation",bean.getOrgName() + "网点" + bean.getBinNo() + "箱" + bean.getBoxNo() + "盒" + bean.getCardSn() + "张");
                        return result(statusCode, message,map);
                    } else {
                        message = "本盒已装满,请重新选择装卡位置";
                    }
                } else {
                    message = "未查到该装盒信息，请重新填写";
                }
            } else {
                statusCode = "201";
                message = bean.getSfzh()+"已登记过";
            }
        } catch (Exception e) {
            logger.error("社保卡业务-零散卡采集失败：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 社保卡业务-滞留卡登记
     * 
     * <p>实现逻辑：社保卡放在盒子中，再把盒子放在箱子中。其中盒子中的卡数量、箱子中的盒子数量可配置。
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/registerRetentionCard", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result registerRetentionCard(@RequestBody RetentionCardBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getDeviceid())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入设备编号");
        }
        if(StringUtils.isBlank(bean.getSfzh())||StringUtils.isBlank(bean.getXm())||StringUtils.isBlank(bean.getReason())){
        	return result(Constants.RESULT_MESSAGE_PARAM, "请输入身份证号、姓名、滞留原因");
        }
        if(StringUtils.isBlank(bean.getSbkh())){
        	return result(Constants.RESULT_MESSAGE_PARAM,"请输入社保卡号");
        }
        if(StringUtils.isBlank(CARBOXCOUNT)||StringUtils.isBlank(CARDMAXSIZE)){
        	return result(Constants.RESULT_MESSAGE_PARAM,"配置文件中卡数量、盒数量参数为空");
        }
        String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "登记成功";
        try {
			TSBSssmVO tsbSssmVO = commService.getTsbSssmInfo4Sisp(bean.getDeviceid());
			if (tsbSssmVO == null || (tsbSssmVO.getOrgId() == null || tsbSssmVO.getOrgId() == 0)) {
			    return result("301", "请工作人员把德生宝和社保网点关联起来");
			}
			tsbSssmVO = sssmBusService.getOrgInfo4Sssm(tsbSssmVO.getOrgId());
			String sssm_username =JedisUtil.getValue("sssm_username");
			
			String orgName = tsbSssmVO.getOrgName();
			bean.setAdduser(sssm_username);
			bean.setOrgName(orgName);
			bean.setOrgId(tsbSssmVO.getOrgId());
			bean.setRegisStatus("1");//0，已发；1，未发
			bean.setStatus(0);//0：在盒中1：分散中2：已发放
			
			RetentionBinBean binVO = new RetentionBinBean();//箱子
			RetentionBoxBean boxVO = new RetentionBoxBean();//盒 子
			
			binVO.setOrgAddress(orgName);
			boxVO.setOrgAddress(orgName);
			
			//查询滞留卡信息(是否登记过)
			List<RetentionCardVO> list = sssmBusService.getCardInfo4Sssm(bean.getSfzh(),bean.getSbkh());
			if (list == null || list.isEmpty()) {
				//查询当前箱、盒、卡信息(最大的箱、盒、卡号)
				List<ResultXhcVO> cbbList = sssmBusService.getCurrentCardBoxBinInfo4Sssm(orgName);
				Integer boxCount = 0;
				Integer cardNowCount = 0;
				Integer cardCount = 0;
				if (null !=cbbList && cbbList.size()>0) {
					boxCount = cbbList.get(0).getXcount() == null ? boxCount : cbbList.get(0).getXcount();//箱中的盒数量
					cardNowCount = cbbList.get(0).getNowCount() == null ? cardNowCount : cbbList.get(0).getNowCount();//盒中的卡编号(等同于cardSn)
					cardCount = cbbList.get(0).getHcount() == null ? cardCount : cbbList.get(0).getHcount();//盒中装入卡片数量
				}
				//配置文件中的卡数量、盒数量
				int cardCount_conf = Integer.parseInt(CARDMAXSIZE);
				int boxCount_conf = Integer.parseInt(CARBOXCOUNT);
				/*
				 * 代码思路：
				 * 1、初始化箱-盒-卡
				 * 		新建箱、盒、卡
				 * 2、在原有数据上添加
				 * 		//判盒子数量是否超过配置参数断
				 *			//若等于或超过，并且卡数量超过配置参数（此处必须加上卡数量判断）
				 *				//新建箱、新建盒子、新建卡
				 *			//若不超过
				 *				//判断卡数量是否超过配置参数
				 *					//若等于或超过
				 *						//新建盒子、新建卡
				 *					//若不超过
				 *						//新建卡
				 */
				//初始化箱-盒-卡（新建箱、盒、卡）
				if(null !=cbbList && cbbList.isEmpty()){
					binVO.setBinNo("1");
                    boxVO.setBoxSn(1);
                    
                    commonInsertCardBoxBin4Sssm(bean, binVO, boxVO);
				}
				//在原有数据上添加
				else{
					//判断盒子数量是否超过配置参数
					//若等于或超过，并且卡数量超过配置参数（此处必须加上卡数量判断）
                    System.out.println("boxCount=" + boxCount);
                    System.out.println("boxCount_conf=" + boxCount_conf);
                    System.out.println("cardCount=" + cardCount);
                    System.out.println("cardCount_conf=" + cardCount_conf);
                    if(boxCount>=boxCount_conf && cardCount>=cardCount_conf){
						//新建箱、新建盒子、新建卡
						Integer binNo = (Integer.parseInt(cbbList.get(0).getBinNo()) + 1);
                        binVO.setBinNo(String.valueOf(binNo));
                        boxVO.setBoxSn(cbbList.get(0).getBoxsn() + 1);
                        
                        commonInsertCardBoxBin4Sssm(bean, binVO, boxVO);
					}
					//若不超过
					else{
						//判断卡数量是否超过配置参数
						//若等于或超过
						if(cardCount>=cardCount_conf){
							//新建盒子、新建卡
							Integer boxNo = Integer.parseInt(cbbList.get(0).getBoxno()) + 1;
		                    boxVO.setBoxNo(String.valueOf(boxNo));
		                    boxVO.setBoxSn(cbbList.get(0).getBoxsn() + 1);
		                    boxVO.setCount(1);
		                    boxVO.setNowCount(1);
		                    boxVO.setBinId(cbbList.get(0).getBinId());
		                    boxVO.setStatus(0);
		                    boxVO.setRemark("0");
		                    boxVO.setStoreOrgType("01");
		                    boxVO.setStreamStatus(0);
		                    //新建（插入）盒子，并返回ID
		                    sssmBusService.insertBox4Sssm(boxVO);
		                    //更改滞留卡箱信息(增加数量)
		                    sssmBusService.updateRetentionBin4Sssm(cbbList.get(0).getBinId());
		                    bean.setBoxId(boxVO.getId());
		                    bean.setCardSn(1);
		                    //新建（插入）卡信息
		                    sssmBusService.insertRetentionCard4Sssm(bean);
						}
						//若不超过
						else{
							//新建卡
							long boxId = cbbList.get(0).getBoxId();
		                    bean.setBoxId(boxId);
		                    bean.setCardSn(cardNowCount + 1);
		                    //新建（插入）卡信息
                            bean.setRetenTime(CommUtil.getNowDateLongStr());
		                    sssmBusService.insertRetentionCard4Sssm(bean);
		                    //更改滞留卡盒信息(增加数量)
		                    sssmBusService.updateRetentionBox4Sssm(boxId);
                            bean.setBoxNo(cbbList.get(0).getBinNo());
                            bean.setBinNo(cbbList.get(0).getBinNo());
						}
					}
				}
                if(Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode)){
                    Map<String,String>map=new HashMap<String,String>();
                    if (bean.getOrgName().endsWith("网点")) {
                        bean.setOrgName(bean.getOrgName().substring(0, bean.getOrgName().length() - 2));
                    }
                    map.put("cardLocation",bean.getOrgName() + "网点" + bean.getBinNo() + "箱" + bean.getBoxNo() + "盒" + bean.getCardSn() + "张");
                    return result(statusCode, message,map);
                }
			}else {
				statusCode = "201";
                message = bean.getSfzh()+"已登记过";
			}
		} catch (Exception e) {
	        message = "抱歉，程序异常了";
	        statusCode = Constants.RESULT_MESSAGE_EXCEPTION;
			logger.error("社保卡业务-滞留卡登记：", e);
		}
        return result(statusCode, message);
    }

    /**
     * 共用的方法：插入箱、盒、卡
     */
	private void commonInsertCardBoxBin4Sssm(RetentionCardBean bean,
			RetentionBinBean binVO, RetentionBoxBean boxVO) throws Exception {
		binVO.setCount(1);
		binVO.setStatus(0);//文档未定义
		binVO.setRemark("0");
		
		//新建（插入）箱子，并返回ID
		sssmBusService.insertBin4Sssm(binVO);
		boxVO.setBoxNo("1");
		boxVO.setStatus(0);
		boxVO.setCount(1);
		boxVO.setNowCount(1);
		boxVO.setBinId(binVO.getId());//插入箱子成功后，MyBatis返回的ID
		boxVO.setRemark("0");
		boxVO.setStoreOrgType("01");
		boxVO.setStreamStatus(0);
		//新建（插入）盒子，并返回ID
		sssmBusService.insertBox4Sssm(boxVO);
		bean.setBoxId(boxVO.getId());
		bean.setCardSn(1);
		//新建（插入）卡信息
		sssmBusService.insertRetentionCard4Sssm(bean);
	}

    /**
     * 社保卡业务-精准发卡
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/issueCard", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result issueCard(@RequestBody IssueCardBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "领卡失败";
        String photoPath = "";//个人照片地址
        String signPhotoPath = "";//签名照片地址
        IssueApplyBean applyBean = new IssueApplyBean();
        if (StringUtils.isBlank(bean.getDeviceid())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入设备编号");
        }
        if (StringUtils.isBlank(bean.getSbkh())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入社保卡号");
        }
        if (StringUtils.isBlank(bean.getIsPerson())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请选择领卡方式");
        }
        if (StringUtils.isBlank(bean.getSfzh()) || StringUtils.isBlank(bean.getXm())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "缺少待领卡人信息");
        }
        try {//查询滞留卡信息
            List<RetentionCardVO> list = sssmBusService.getCardInfo4Sssm(bean.getSfzh(), bean.getSbkh());
            if (list == null || list.isEmpty()) {
                message = "领卡失败，未找到社保卡";
            } else {
                if ((list.get(0).getStatus() == 2) && "0".equals(list.get(0).getRegisStatus())) {
                    message = "领卡失败，社保卡已被领走";
                } else {
                    //是否本人  0：本人，1：代他人办理
                    if ("0".equals(bean.getIsPerson())) {
                        if (bean.getPhotoId() == 0 || bean.getSignphotoId() == 0) {
                            return this.result(Constants.RESULT_MESSAGE_PARAM, "缺少待领卡人相片或签名");
                        }
                        //本人相片ID信息
                        photoPath = commService.photoIsExist4Cssp(bean.getPhotoId(), Constants.PICTURE_TYPE_111);
                        if (Constants.RESULT_MESSAGE_PARAM.equals(photoPath)) {
                            message = "本人相片不存在";
                            logger.error("sfzh=" + bean.getSfzh() + " photoId=" + bean.getPhotoId() + message);
                            return this.result(Constants.RESULT_MESSAGE_PARAM, message);
                        }
                        //本人签名照信息
                        signPhotoPath = commService.photoIsExist4Cssp(bean.getSignphotoId(), Constants.PICTURE_TYPE_112);
                        if (Constants.RESULT_MESSAGE_PARAM.equals(signPhotoPath)) {
                            message = "本人签名照不存在";
                            logger.error("sfzh=" + bean.getSfzh() + " signphotoId=" + bean.getSignphotoId() + message);
                            return this.result(Constants.RESULT_MESSAGE_PARAM, message);
                        }
                        applyBean.setName(bean.getXm());
                        applyBean.setCertNum(bean.getSfzh());
                    } else if ("1".equals(bean.getIsPerson())) {
                        if (StringUtils.isBlank(bean.getAgentSfzh()) || StringUtils.isBlank(bean.getAgentXm()) ||
                                bean.getAgentphotoId() == 0 || bean.getAgentSignphotoId() == 0) {
                            return this.result(Constants.RESULT_MESSAGE_PARAM, "缺少代办人信息或相片");
                        }
                        //代办人相片信息
                        photoPath = commService.photoIsExist4Cssp(bean.getAgentphotoId(), Constants.PICTURE_TYPE_113);
                        if (Constants.RESULT_MESSAGE_PARAM.equals(photoPath)) {
                            message = "代办人相片不存在";
                            logger.error("sfzh=" + bean.getSfzh() + " photoId=" + bean.getAgentphotoId() + message);
                            return this.result(Constants.RESULT_MESSAGE_PARAM, message);
                        }
                        //代办人签名照信息
                        signPhotoPath = commService.photoIsExist4Cssp(bean.getAgentSignphotoId(), Constants.PICTURE_TYPE_114);
                        if (Constants.RESULT_MESSAGE_PARAM.equals(signPhotoPath)) {
                            message = "代办人签名照不存在";
                            logger.error("sfzh=" + bean.getSfzh() + " signphotoId=" + bean.getAgentSignphotoId() + message);
                            return this.result(Constants.RESULT_MESSAGE_PARAM, message);
                        }
                        applyBean.setAgentName(bean.getAgentXm());
                        applyBean.setAgentcertNum(bean.getAgentSfzh());
                    }
                    applyBean.setSoCardNum(bean.getSbkh());
                    applyBean.setRphotoPath(photoPath);
                    applyBean.setQmphotoPath(signPhotoPath);
                    TSBSssmVO tsbSssmVO = commService.getTsbSssmInfo4Sisp(bean.getDeviceid());
                    if (tsbSssmVO == null || (tsbSssmVO.getOrgId() == null || tsbSssmVO.getOrgId() == 0)) {
                        return result("301", "请工作人员把德生宝和社保网点关联起来");
                    }//网点信息
                    tsbSssmVO = sssmBusService.getOrgInfo4Sssm(tsbSssmVO.getOrgId());
                    if (tsbSssmVO != null) {
                        applyBean.setLkwd(tsbSssmVO.getOrgAddress());
                        applyBean.setSoname(tsbSssmVO.getOrgName());
                    }
                    //新增精准发卡申领
                    int r = sssmBusService.insertIssueApplyCard4Sssm(applyBean);
                    if (r > 0) {//更改滞留卡片信息（所领卡状态、精准发卡后其余卡片位置）、更改滞留卡盒信息(减少数量)
                        sssmBusService.updateRetentionCard4Sssm(bean.getSbkh(),bean.getSfzh(),"2","0");//卡片状态0在盒中1分散中2已发放;滞留状态0已发1待发放 ;
                        sssmBusService.updateIssueCard4Sssm(list.get(0).getBoxId(), list.get(0).getCardSn());
                        sssmBusService.updateLowRetentionBox4Sssm(list.get(0).getBoxId());

                        //测试 社保卡申请-存入制卡进度--项目可删除
                        CardProgressVO cardProgressVO=new CardProgressVO();
                        cardProgressVO.setSfzh(bean.getSfzh());
                        cardProgressVO.setSbkh(bean.getSbkh());
                        cardProgressVO.setGettime(CommUtil.getNowDateLongStr());
                        cardProgressVO.setStatus("您的社保卡已领取");
                        testCsspService.updateCardProgress4Other(cardProgressVO);
                        //测试 --项目可删除
                        statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                        message = "领卡成功";
                        IssueCardVO vo = new IssueCardVO();
                        BeanUtils.copyProperties(bean, vo);
                        try {
                            // 渠道类型：德生宝  领卡地址则根据设备号查设备所在地
                            if ((Constants.TSB).equals(bean.getChannelcode()) && StringUtils.isNotBlank(bean.getDeviceid())) {
                                vo.setIssueAddress(commService.getTsbAddress4Sisp(bean.getDeviceid()));
                            }
                        } catch (Exception e) {
                            logger.error("社保卡业务-精准发卡获取地址失败：", e);
                        }
                        return result(statusCode, message, vo);

                    } else {
                        message = "领卡失败，更改卡片信息错误";
                    }
                }
            }
        } catch (Exception e) {
            logger.error("社保卡业务-精准发卡失败：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 滞留卡领卡
     * 原规定滞留卡如能查询到卡位置则表示卡已经存放在网点 不允许更改领取的网点或选择邮寄 只能在申领时选择
     * 现通过配置文件控制滞留卡领取是否允许通过邮寄方式发放
     * 为方便演示 可通过配置文件控制是否更改卡片发放状态
     * @param bean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getCard", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCard(@RequestBody CardGetBean bean){
        //入参校验（姓名 身份证 联系方式 领卡方式 卡原位置 领取网点或邮寄地址 渠道编码）
        if (StringUtils.isEmpty(bean.getXm()) || StringUtils.isEmpty(bean.getSfzh()))
            return error("姓名、身份证号");
        if (StringUtils.isEmpty(bean.getAddrType())) return error("请选择领卡方式");
        if (StringUtils.isEmpty(bean.getAddrNew())) return error("请选择领取网点或填写邮寄地址");
        if (StringUtils.isEmpty(bean.getAddrOld())) return error("卡原位置不能为空");
        if (StringUtils.isEmpty(bean.getChannelcode())) return error("渠道编码不能为空");

        //滞留卡领取是否支持邮寄
        if("1".equals(CARD_IS_EXPRESS) && "1".equals(bean.getAddrType()))
            return error("抱歉，暂不支持邮寄方式，请亲自到网点领取");

        try {
            //记录领卡信息到数据库
            int count = sssmBusService.cardGet4Sssm(bean);
            //是否支持重复领卡（测试用，允许则不修改发放状态 regisStatus）
            if("1".equals(CARD_GET_AGAIN)) {
                String sbkh = null;
                List<Map<String,String>> maps = sssmBusService.getCardNum4Sssm(bean);
                if (maps != null && maps.size() > 0) {
                    if("0".equals(maps.get(0).get("REGISSTATUS"))) {
                        return error("请勿重复领取");
                    }
                    //获取第一条记录的社保卡号用于改变卡片领取状态
                    sbkh = maps.get(0).get("SBKH");
                }
                if (StringUtils.isNotBlank(sbkh))
                    sssmBusService.updateRetentionCard4Sssm(sbkh, bean.getSfzh(), "2", "0");
            }
            if(count == 1){
                if("1".equals(bean.getAddrType()))
                    return ok("登记成功，请注意查收快递");
                if("2".equals(bean.getAddrType()))
                    return ok("登记成功，请亲自到该网点领取");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok("登记失败");
    }

}