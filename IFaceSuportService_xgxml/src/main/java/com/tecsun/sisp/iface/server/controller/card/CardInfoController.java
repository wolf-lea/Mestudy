package com.tecsun.sisp.iface.server.controller.card;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import com.tecsun.sisp.iface.server.util.Config;
import com.tecsun.sisp.iface.common.util.Constants;
import com.tecsun.sisp.iface.common.vo.CardInfoBean;
import com.tecsun.sisp.iface.common.vo.Result;
import com.tecsun.sisp.iface.common.vo.TransBean;
import com.tecsun.sisp.iface.common.vo.card.CardAllInfo;
import com.tecsun.sisp.iface.common.vo.card.CardBaseInfo;
import com.tecsun.sisp.iface.common.vo.card.SettingCardQuery;
import com.tecsun.sisp.iface.server.controller.BaseController;
import com.tecsun.sisp.iface.server.outerface.card.CardInfoBus;
import com.tecsun.sisp.iface.server.util.DictionaryUtil;
import com.tecsun.sisp.iface.server.util.ThreadPoolTask;
import com.tecsun.sisp.iface.server.util.ThreadPoolUtil;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 1.社保卡卡数据、制卡查询 2. 临时挂失、解挂、领卡
 *
 * @author thl
 * @ClassName: CardInfoController
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @date 2015年5月31日 下午8:06:25
 */
@Controller
@RequestMapping(value = "/iface/cardInfo")
public class CardInfoController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(CardInfoController.class);

  /*  @Autowired
    private NetUserServiceImpl netUserService;*/

    /**
     * SISP_IFACE_CARD_001 卡基础数据（不包含个人照片)  社保卡号和身份证号码至少有一项不为空
     */
    @RequestMapping(value = "/getCardInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardInfo(@RequestBody CardInfoBean bean)throws Exception{
        insertTransLog(bean,Constants.BUSINESSCODE.get("getCardInfo"));
        String cardno = bean.getCardNo()==null?"":bean.getCardNo();
        String cernum = bean.getId()==null?"":bean.getId();
        String result = "";
        String message = "";
        if ((cardno != null && !cardno.equals("")) || (cernum != null && !cernum.equals(""))) {
            CardInfoBus bus = new CardInfoBus();
            CardBaseInfo info = bus.getCardBaseInfo(cardno,cernum);
            if (info != null && info.getErr().equals("OK")) {
                result = Constants.RESULT_MESSAGE_SUCCESS;
                message = "卡基础数据（不包含个人照片)获取成功";
                info.setSex(DictionaryUtil.getDictName(Constants.SEX_GROUP, info.getSex()));
                info.setEthnic(DictionaryUtil.getDictName(Constants.PARAM_NATION_GROUP, info.getEthnic()));
                info.setPersonstatus(DictionaryUtil.getDictName(Constants.PERATATUS_GROUP, info.getPersonstatus()));
                info.setRegisttype(DictionaryUtil.getDictName(Constants.REGNATURE_GROUP, info.getRegisttype()));
            } else {
                result = Constants.RESULT_MESSAGE_ERROR;
                message = info.getErr();
            }
            return this.result(result, message, info);
        } else {
            result = Constants.RESULT_MESSAGE_ERROR;
            message = "社保卡号和身份证号码至少有一项不为空";
            return this.result(result, message, null);
        }

    }

    
    
    @RequestMapping(value = "/getCardBaseInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardBaseInfo(@RequestBody CardInfoBean bean)throws Exception{
        insertTransLog(bean,Constants.BUSINESSCODE.get("getCardInfo"));
        String cardno = bean.getCardNo()==null?"":bean.getCardNo();
        String cernum = bean.getId()==null?"":bean.getId();
        String result = "";
        String message = "";
        if ((cardno != null && !cardno.equals("")) || (cernum != null && !cernum.equals(""))) {
            CardInfoBus bus = new CardInfoBus();
            CardBaseInfo info = bus.getCardBaseInfo(cardno,cernum);
            if (info != null && info.getErr().equals("OK")) {
                result = Constants.RESULT_MESSAGE_SUCCESS;
                message = "卡基础数据（不包含个人照片)获取成功";
                info.setSex(info.getSex());
                info.setEthnic(info.getEthnic());
                info.setPersonstatus(info.getPersonstatus());
                info.setRegisttype(info.getRegisttype());
            } else {
                result = Constants.RESULT_MESSAGE_ERROR;
                message = info.getErr();
            }
            return this.result(result, message, info);
        } else {
            result = Constants.RESULT_MESSAGE_ERROR;
            message = "社保卡号和身份证号码至少有一项不为空";
            return this.result(result, message, null);
        }

    }
    /**
     * SISP_IFACE_CARD_002 卡基础数据（包含个人照片） 社保卡号和身份证号码至少有一项不为空
     */
    @RequestMapping(value = "/getCardAllInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardAllInfo(@RequestBody CardInfoBean bean)throws Exception{
        insertTransLog(bean,Constants.BUSINESSCODE.get("getCardAllInfo"));
        String cardno = bean.getCardNo()==null?"":bean.getCardNo();
        String cernum = bean.getId()==null?"":bean.getId();
        String result = "";
        String message = "";
        if ((cardno != null && !cardno.equals("")) || (cernum != null && !cernum.equals(""))) {
            CardInfoBus bus = new CardInfoBus();
            CardAllInfo info = bus.getCardAllInfo(cardno, cernum);
            if (info != null && info.getErr().equals("OK")) {
                info.setSex(DictionaryUtil.getDictName(Constants.SEX_GROUP, info.getSex()));
                info.setEthnic(DictionaryUtil.getDictName(Constants.PARAM_NATION_GROUP, info.getEthnic()));
                info.setPersonstatus(DictionaryUtil.getDictName(Constants.PERATATUS_GROUP, info.getPersonstatus()));
                info.setRegisttype(DictionaryUtil.getDictName(Constants.REGNATURE_GROUP, info.getRegisttype()));
                result = Constants.RESULT_MESSAGE_SUCCESS;
                message = "卡基础数据获取成功";
            } else {
                result = Constants.RESULT_MESSAGE_ERROR;
                message = info.getErr();
            }
            return this.result(result, message, info);
        } else {
            result = Constants.RESULT_MESSAGE_ERROR;
            message = "社保卡号和身份证号码至少有一项不为空";
            return this.result(result, message, null);
        }

    }
    /**
     * 卡数据 社保卡号和身份证号码至少有一项不为空
     */
   /* @RequestMapping(value = "/getCard", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCard(@RequestBody CardInfoBean bean)throws Exception{
        //insertTransLog(bean,Constants.BUSINESSCODE.get("getCardAllInfo"));
        String cardno = bean.getCardNo()==null?"":bean.getCardNo();
        String cernum = bean.getId()==null?"":bean.getId();
        String result = "";
        String message = "";
        if ((cardno != null && !cardno.equals("")) || (cernum != null && !cernum.equals(""))) {
            CardInfoBus bus = new CardInfoBus();
            CardAllInfo allInfo = bus.getCard(cardno, cernum);
           // CardAllInfo allInfo=bus.getCardAllInfo(cardno,cernum);
            if (allInfo != null && allInfo.getErr().equals("OK")) {//省厅存在该用户
                result = Constants.RESULT_MESSAGE_SUCCESS;
                message = "卡数据获取成功";
                return result(result,message,allInfo);
             }
            else{//省厅不存在该用户
                result = Constants.RESULT_MESSAGE_ERROR;
                message = "您的用户名输入错误，请重新输入";
                logger.error("省厅不存在该用户");
                return result(result, message,null);
            }
        } else {
            result = Constants.RESULT_MESSAGE_ERROR;
            message = "社保卡号和身份证号码至少有一项不为空";
            return this.result(result, message, null);
        }
    }*/

    /**
     * SISP_IFACE_CARD_003 设置卡的维护状态
     * 1.当server为01临时挂失、02正式挂失、03解除挂失时，系统对bank、bankno、cityno不作判断，允许跨地区执行这些请求。
     * 2.当server为04注销、11领卡启用时，系统不判断bankno，其他全部数据项都将做严格判断，不允许跨地区执行这两项请求。
     *
     * @param bean
     * @return message 为返回信息 ，00为成功，01为之前已经成功，失败原因
     * @throws
     */
//    @RequestMapping(value = "/setCard", method = RequestMethod.POST, produces = "application/json")
//    public Result setCardStatus(CardInfoBean bean) {
//        String server = bean.getServer();
//        String cardno = bean.getCardNo();
//        String cernum = bean.getId();
//        String name = bean.getName();
//        String bank = bean.getBank();
//        String cityno = bean.getCityno();
//        String bankno = bean.getBankno();
//        CardInfoBus bus = new CardInfoBus();
//        String message = bus.setCard(server, cardno, cernum, name, bank, bankno, cityno);
//        String result = Constants.RESULT_MESSAGE_SUCCESS;
//        if (message == null) {
//            result = Constants.RESULT_MESSAGE_ERROR;
//            message = "调用省厅接口异常";
//        }
//        return this.result(result, message, null);
//    }

    /**
     * SISP_IFACE_CARD_004 制卡进度查询
     * TSB传入身份证，大终端微信传入身份证和姓名
     */
    @RequestMapping(value = "/getCardStatus", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCardStatus(@RequestBody CardInfoBean bean)throws Exception{
        insertTransLog(bean,Constants.BUSINESSCODE.get("getCardStatus"));
        String cernum = bean.getId();
        String name = bean.getName();
        String result = "";
        String message = "";
        CardInfoBus bus = new CardInfoBus();
        if(StringUtils.isEmpty(name)){
            name=bus.getCardAllInfo("", cernum).getName();
        }
        if ((name != null && !name.equals("")) && (cernum != null && !cernum.equals(""))) {
            SettingCardQuery info = bus.getCardProgressQuery(cernum, name);
            if (info != null && info.getErr().equals("OK")) {
                info.setCardtype(DictionaryUtil.getDictName(Constants.CARDTYPE_GROUP, info.getCardtype()));
                info.setTransacttype(DictionaryUtil.getDictName(Constants.APPLYTYPE_GROUP, info.getTransacttype()));
                info.setBank(DictionaryUtil.getDictName(Constants.BANK_GROUP, info.getBank()));
                result = Constants.RESULT_MESSAGE_SUCCESS;
                message = "制卡进度查询成功";
            } else {
                result = Constants.RESULT_MESSAGE_ERROR;
                message = info.getErr();
            }
            return this.result(result, message, info);
        } else {
            result = Constants.RESULT_MESSAGE_ERROR;
            if(Constants.TSB.equals(bean.getChannelcode())){
                message = "请输入正确的身份证";
            }else{
                message = "请输入正确的身份证和姓名";
            }
            return this.result(result, message, null);
        }
    }

    /**
     * SISP_IFACE_CARD_005 领卡启用
     *
     * @param bean
     * @return 1.当result为1时，调用接口成功，根据message来判断调用的情况。
     * 2.message： 00为成功，01为之前已经成功，失败原因
     * 3.当result为0时，调用接口失败，message为失败原因
     * @throws
     */
    @RequestMapping(value = "/setStart", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result setStart(@RequestBody CardInfoBean bean) {
        insertTransLog(bean,Constants.BUSINESSCODE.get("setStart"));
        String cardno = bean.getCardNo();
        String cernum = bean.getId();
        String name = bean.getName();
        String result = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "";
        if ((!"".equals(cardno) && cardno != null) && (!"".equals(cernum) && cernum != null)
                && (!"".equals(name) && name != null)) {
            CardInfoBus bus = new CardInfoBus();
            message = bus.setStart(cardno, cernum, name);
            if (message == null || "".equals(message)) {
                result = Constants.RESULT_MESSAGE_ERROR;
                message = "调用省厅接口异常";
            }else if(message.equals("00")){
            	result = Constants.RESULT_MESSAGE_SUCCESS;
            	message = "社保卡激活成功";
            }else if(message.equals("01")){
            	 result = Constants.RESULT_MESSAGE_ERROR;
                 message = "社保卡已激活";
            }else{
            	result = Constants.RESULT_MESSAGE_ERROR;
            }
        } else {
            result = Constants.RESULT_MESSAGE_ERROR;
            message = "领卡条件数据不能为空";
        }
        return this.result(result, message, null);
    }

    /**
     * SISP_IFACE_CARD_006 临时挂失
     *
     * @param bean
     * @return 1.当result为1时，调用接口成功，根据message来判断调用的情况。
     * 2.message： 00为成功，01为之前已经成功，失败原因
     * 3.当result为0时，调用接口失败，message为失败原因
     * @throws
     */
    @RequestMapping(value = "/setLoss", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result setLoss(@RequestBody CardInfoBean bean) {
        insertTransLog(bean,Constants.BUSINESSCODE.get("setLoss"));
        String cardno = bean.getCardNo();
        String cernum = bean.getId();
        String name = bean.getName();
        String result = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "";
        if ((!"".equals(cardno) && cardno != null) && (!"".equals(cernum) && cernum != null)
                && (!"".equals(name) && name != null)) {
            CardInfoBus bus = new CardInfoBus();
            message = bus.setLoss(cardno, cernum, name);
            if (message == null || "".equals(message)) {
                result = Constants.RESULT_MESSAGE_ERROR;
                message = "调用省厅接口异常";
            }else if(message.equals("00")){
            	result = Constants.RESULT_MESSAGE_SUCCESS;
            	message = "社保卡临时挂失成功";
            }else if(message.equals("01")){
            	 result = Constants.RESULT_MESSAGE_ERROR;
                 message = "之前已经挂失";
            }else{
            	result = Constants.RESULT_MESSAGE_ERROR;
            }
        } else {
            result = Constants.RESULT_MESSAGE_ERROR;
            message = "领卡条件数据不能为空";
        }
        return this.result(result, message, null);
    }

    /**
     * SISP_IFACE_CARD_007 解挂
     *
     * @param bean
     * @return 1.当result为1时，调用接口成功，根据message来判断调用的情况。
     * 2.message： 00为成功，01为之前已经成功，失败原因
     * 3.当result为0时，调用接口失败，message为失败原因
     * @throws
     */
    @RequestMapping(value = "/setHanging", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result setHanging(@RequestBody CardInfoBean bean) {
        insertTransLog(bean,Constants.BUSINESSCODE.get("setHanging"));
        String cardno = bean.getCardNo();
        String cernum = bean.getId();
        String name = bean.getName();
        String result = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "";
        if ((!"".equals(cardno) && cardno != null) && (!"".equals(cernum) && cernum != null)
                && (!"".equals(name) && name != null)) {
            CardInfoBus bus = new CardInfoBus();
            message = bus.setHanging(cardno, cernum, name);
            if (message == null || "".equals(message)) {
                result = Constants.RESULT_MESSAGE_ERROR;
                message = "调用省厅接口异常";
            }else if(message.equals("00")){
            	result = Constants.RESULT_MESSAGE_SUCCESS;
            	message = "社保卡解挂成功";
            }else if(message.equals("01")){
            	 result = Constants.RESULT_MESSAGE_ERROR;
                 message = "之前已经解挂";
            }else{
            	result = Constants.RESULT_MESSAGE_ERROR;
            }
        } else {
            result = Constants.RESULT_MESSAGE_ERROR;
            message = "领卡条件数据不能为空";
        }
        return this.result(result, message, null);
    }
    
    
    /**
	 * 
	 * @author 作者名  付伟锋
	 * @date 日期   2017-03-30 15:22
	 * @version 版本标识 V.1.0.0
	 * @parameter 参数及其意义
	 * 
	 * @return Result结果
	 * @throws 异常类及抛出条件
	 * @方法说明-省厅卡管接口-卡状态接口
	 */
    @RequestMapping(value = "/getCard", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
	public   Result getCard(@RequestBody CardInfoBean bean){
    	 insertTransLog(bean,Constants.BUSINESSCODE.get("getCard"));
		String result  = Constants.RESULT_MESSAGE_SUCCESS;
		String message = "操作成功";
		String wsResult = "";
		if(bean!=null){
			String idCard = bean.getId();//身份证号码；
			String cardNo = bean.getCardNo();//社保卡号码；
			String name = bean.getName();//名称；
			
			if((idCard!=null && idCard.length()>0) &&(cardNo!=null && cardNo.length()>0) && (name!=null && cardNo.length()>0)){
				Service service = new Service();
				Call call;
				Map<String , String > map = new HashMap<String , String>();
				try {
					String url = Config.getInstance().get("CARDBUESS_URL");//公共就业服务;
					call = (Call) service.createCall();
		            call.setTargetEndpointAddress( new URL(url));
		            call.setOperationName( new QName("http://ws.apache.org/axis2","getCard"));// 设置要调用哪个方法
		            call.setSOAPActionURI("http://ws.apache.org/axis2" + "getCard");
		            call.addParameter(new QName("http://ws.apache.org/axis2", "user"), XMLType.SOAP_STRING, ParameterMode.IN);//设置传入参数的属性
		            call.addParameter(new QName("http://ws.apache.org/axis2", "pass"), XMLType.SOAP_STRING, ParameterMode.IN);//设置传入参数的属性
		            call.addParameter(new QName("http://ws.apache.org/axis2", "aaz500"), XMLType.SOAP_STRING, ParameterMode.IN);//设置传入参数的属性
		            call.addParameter(new QName("http://ws.apache.org/axis2", "aac002"), XMLType.SOAP_STRING, ParameterMode.IN);//设置传入参数的属性
		            call.addParameter(new QName("http://ws.apache.org/axis2", "aac003"), XMLType.SOAP_STRING, ParameterMode.IN);//设置传入参数的属性
		            call.setReturnType(XMLType. SOAP_STRING); // 要返回的数据类型（自定义类型）
		            wsResult = (String) call.invoke(new String[]{"szytj","szytj12333",cardNo, idCard, name});/// 调用方法并传递参数
		            if((wsResult!=null && wsResult.length()>0) && wsResult.equals("OK")){
		            	 return this.result(result, message, wsResult);
		            }else{
		            	 return this.result(result, message);
		            }
				} catch (Exception e) {
					result =  Constants.RESULT_MESSAGE_ERROR;
					message = "获取卡状态接口getCard接口失败"+e.getMessage();
				}
				 return this.result(result, message);
			}else{
				result =  Constants.RESULT_MESSAGE_ERROR;
				message ="社保卡号、身份证号码及姓名都不能为空";
				return this.result(result, message);
			}
			
		}else{
			result =  Constants.RESULT_MESSAGE_ERROR;
			message = "获取卡状态接口getCard接口入参不能为空";
			return this.result(result, message);
		}
		

	}

    public void insertTransLog(CardInfoBean bean,String funcName){
        try {
            TransBean transBean=new TransBean();
            transBean.setUserid(bean.getId());
            transBean.setChannelcode(bean.getChannelcode());
            transBean.setBusinesscode(funcName);
            transBean.setDeviceid(bean.getDeviceid());
            ThreadPoolUtil.getThreadPool().execute(new ThreadPoolTask(transBean));
        } catch (Exception e) {
            logger.error("添加卡业务操作记录到业务分析子系统出错：" + e.getMessage());
        }
    }

}
