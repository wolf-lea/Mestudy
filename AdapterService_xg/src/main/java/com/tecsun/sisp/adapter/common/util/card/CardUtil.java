package com.tecsun.sisp.adapter.common.util.card;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.encoding.XMLType;

import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.dom4j.Element;

import com.tecsun.sisp.adapter.common.util.Config;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.modules.card.entity.response.CardAllInfo;
import com.tecsun.sisp.adapter.modules.card.entity.response.SettingCardQuery;

/**
 * 
 * @Description: 省厅卡管接口
 * @author zhenliuqing
 * @date 2017年9月29日
 * @ClassName: ProUtil
 * 
 */
public class CardUtil {

	public static final String URL = Config.getInstance().get("CARDBUESS_URL");// 公共就业服务;
	// 用户名
	public static final String USER = Config.getInstance().get("user");
	// 密码
	public static final String PASSWORD = Config.getInstance().get("password");
	// 城市代码
	public static final String CITYNO = Config.getInstance().get("cityno");

	/**
	 * 卡状态
	 * 
	 * @param cardNo
	 *            社保卡
	 * @param cernum
	 *            身份证
	 * @param name
	 *            姓名
	 * @return
	 */
	public static String getCardStatus(String cardNo, String cernum, String name) {
		String wsResult = "";
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(URL));
			call.setOperationName(new QName("http://ws.apache.org/axis2", "getCard"));// 设置要调用哪个方法
			call.setSOAPActionURI("http://ws.apache.org/axis2" + "getCard");
			call.addParameter(new QName("http://ws.apache.org/axis2", "user"), XMLType.SOAP_STRING,
					ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "pass"), XMLType.SOAP_STRING,
					ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "aaz500"),
					XMLType.SOAP_STRING, ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "aac002"),
					XMLType.SOAP_STRING, ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "aac003"),
					XMLType.SOAP_STRING, ParameterMode.IN);// 设置传入参数的属性
			call.setReturnType(XMLType.SOAP_STRING); // 要返回的数据类型（自定义类型）
			wsResult = (String) call.invoke(new String[] { "szytj", "szytj12333", cardNo, cernum,
					name });// 调用方法并传递参数
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wsResult;
	}

	/**
	 * 卡进度
	 * 
	 * @param cernum
	 *            身份证
	 * @param name
	 *            姓名
	 * @return
	 */
	public static String getCardProgress(String cernum, String name) {
		String wsResult = "";
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(URL));
			call.setOperationName(new QName("http://ws.apache.org/axis2", "getAZ03"));// 设置要调用哪个方法
			call.setSOAPActionURI("http://ws.apache.org/axis2" + "getAZ03");
			call.addParameter(new QName("http://ws.apache.org/axis2", "user"), XMLType.SOAP_STRING,
					ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "pass"), XMLType.SOAP_STRING,
					ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "aac002"),
					XMLType.SOAP_STRING, ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "aac003"),
					XMLType.SOAP_STRING, ParameterMode.IN);// 设置传入参数的属性
			call.setReturnType(XMLType.SOAP_STRING); // 要返回的数据类型（自定义类型）
			wsResult = (String) call.invoke(new String[] { USER, PASSWORD, cernum, name });// 调用方法并传递参数
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wsResult;
	}

	/**
	 * 卡数据查询(含照片)
	 * 
	 * @param cernum
	 *            身份证
	 * @param sbkh
	 *            社保卡
	 * @return
	 */
	public static String getData(String cernum, String sbkh) {
		String wsResult = "";
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(URL));
			call.setOperationName(new QName("http://ws.apache.org/axis2", "getData"));// 设置要调用哪个方法
			// call.setSOAPActionURI("http://ws.apache.org/axis2" + "getData");
			// call.setUseSOAPAction(true);
			call.setSOAPActionURI("");
			call.addParameter(new QName("http://ws.apache.org/axis2", "user"), XMLType.SOAP_STRING,
					ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "pass"), XMLType.SOAP_STRING,
					ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "aaz500"),
					XMLType.SOAP_STRING, ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "aac002"),
					XMLType.SOAP_STRING, ParameterMode.IN);// 设置传入参数的属性
			call.setReturnType(XMLType.SOAP_STRING); // 要返回的数据类型（自定义类型）

			wsResult = (String) call.invoke(new String[] { USER, PASSWORD, sbkh, cernum });// 调用方法并传递参数
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wsResult;
	}

	/**
	 * 临时挂失(setLoss)/领卡启用(setStart)
	 * 
	 * @param cardNo
	 *            社保卡
	 * @param cernum
	 *            身份证
	 * @param name
	 *            姓名
	 * @param cityno
	 *            发卡城市代码
	 * @return
	 */
	public static String setLossOrSetStart(String method, String cardNo, String cernum,
			String name, String cityno) {
		String wsResult = "";
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(URL));
			call.setOperationName(new QName("http://ws.apache.org/axis2", method));// 设置要调用哪个方法
			call.setSOAPActionURI("http://ws.apache.org/axis2" + method);
			call.addParameter(new QName("http://ws.apache.org/axis2", "user"), XMLType.SOAP_STRING,
					ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "pass"), XMLType.SOAP_STRING,
					ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "aaz500"),
					XMLType.SOAP_STRING, ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "aac002"),
					XMLType.SOAP_STRING, ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "aac003"),
					XMLType.SOAP_STRING, ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "aab301"),
					XMLType.SOAP_STRING, ParameterMode.IN);// 设置传入参数的属性
			call.setReturnType(XMLType.SOAP_STRING); // 要返回的数据类型（自定义类型）
			wsResult = (String) call.invoke(new String[] { USER, PASSWORD, cardNo, cernum, name,
					cityno });// 调用方法并传递参数
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wsResult;
	}

	/**
	 * 设置卡的维护状态
	 * 1.当server为01临时挂失、02正式挂失、03解除挂失时，系统对bank、bankno、cityno不作判断，允许跨地区执行这些请求。
	 * 2.当server为04注销、11领卡启用时，系统不判断bankno，其他全部数据项都将做严格判断，不允许跨地区执行这两项请求。
	 * 
	 * @param server
	 *            业务代码（2位，01临时挂失、02正式挂失、03解除挂失、04注销、11领卡启用）
	 * @param cardNo
	 *            社保卡号（9位）
	 * @param cernum
	 *            身份证号
	 * @param name
	 *            姓名
	 * @param bank
	 *            服务银行
	 * @param bankno
	 *            银行卡号
	 * @param cityno
	 *            发卡城市代码
	 * @return String 00成功/01之前已经成功/失败原因
	 * @throws
	 */
	public static String SetCard(String server, String cardNo, String cernum, String name,
			String bank, String bankno, String cityno) {
		String wsResult = "";
		try {
			Service service = new Service();
			Call call = (Call) service.createCall();
			call.setTargetEndpointAddress(new URL(URL));
			call.setOperationName(new QName("http://ws.apache.org/axis2", "setCard"));// 设置要调用哪个方法
			call.setSOAPActionURI("http://ws.apache.org/axis2" + "setCard");
			call.addParameter(new QName("http://ws.apache.org/axis2", "user"), XMLType.SOAP_STRING,
					ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "pass"), XMLType.SOAP_STRING,
					ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "server"),
					XMLType.SOAP_STRING, ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "aaz500"),
					XMLType.SOAP_STRING, ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "aac002"),
					XMLType.SOAP_STRING, ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "aac003"),
					XMLType.SOAP_STRING, ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "aae008"),
					XMLType.SOAP_STRING, ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "aae010"),
					XMLType.SOAP_STRING, ParameterMode.IN);// 设置传入参数的属性
			call.addParameter(new QName("http://ws.apache.org/axis2", "aab301"),
					XMLType.SOAP_STRING, ParameterMode.IN);// 设置传入参数的属性

			call.setReturnType(XMLType.SOAP_STRING); // 要返回的数据类型（自定义类型）
			wsResult = (String) call.invoke(new String[] { USER, PASSWORD, server, cardNo, cernum,
					name, bank, bankno, cityno });// 调用方法并传递参数
		} catch (Exception e) {
			e.printStackTrace();
		}
		return wsResult;
	}

	// 制卡进度查询组装实体类
	public static SettingCardQuery getSettingCardQuery(Element element, String err) {
		SettingCardQuery po = new SettingCardQuery();
		po.setErr(err);
		String cardno = element.element("AAZ500").getText();// 社会保障卡卡号
		po.setSbkh(cardno);
		String cernum = element.element("AAC002").getText();// 社会保障号码
		po.setSfzh(cernum);
		String name = element.element("AAC003").getText();// 姓名
		po.setName(name);
		String CARDTYPE = element.element("CARDTYPE").getText();
		po.setCardtype(CARDTYPE);
		String TRANSACTTYPE = element.element("TRANSACTTYPE").getText();
		po.setTransacttype(TRANSACTTYPE);
		String BATCHNO = element.element("BATCHNO").getText();
		po.setBatchno(BATCHNO);
		String AAB301 = element.element("AAB301").getText();
		po.setCitycode(AAB301);
		String ORGANID = element.element("ORGANID").getText();
		po.setOrganid(ORGANID);
		String AAE008 = element.element("AAE008").getText();
		po.setBank(AAE008);
		String APPLYTIME = element.element("APPLYTIME").getText();
		po.setApplytime(APPLYTIME);
		String BANKTIME0 = element.element("BANKTIME0").getText();
		po.setBanktime0(BANKTIME0);
		String BANKFINISHTIME0 = element.element("BANKFINISHTIME0").getText();
		po.setBankfinishtime0(BANKFINISHTIME0);
		String INSURETIME = element.element("INSURETIME").getText();
		po.setInsuretime(INSURETIME);
		String INSUREFINISHTIME0 = element.element("INSUREFINISHTIME0").getText();
		po.setInsurefinishtime0(INSUREFINISHTIME0);
		String INSUREFINISHTIME = element.element("INSUREFINISHTIME").getText();
		po.setInsurefinishtime(INSUREFINISHTIME);
		String PROVINCETIME = element.element("PROVINCETIME").getText();
		po.setProvincetime(PROVINCETIME);
		String CITYTIME = element.element("CITYTIME").getText();
		po.setCitytime(CITYTIME);
		String GETTIME = element.element("GETTIME").getText();
		po.setGettime(GETTIME);
		String GETTIME1 = element.element("GETTIME1").getText();
		po.setGettime1(GETTIME1);
		String REMARKS = element.element("REMARKS").getText();
		po.setRemarks(REMARKS);
		String VALIDTAG = element.element("VALIDTAG").getText();
		po.setValidtag(VALIDTAG);
		return po;
	}

	// 卡基础数据查询组装实体类
	public static CardAllInfo getCardBaseInfo(Element element, String err) {
		CardAllInfo po = new CardAllInfo();
		po.setErr(err);
		// 社会保障卡卡号
		String cardno = element.element("AAZ500").getText();
		po.setSbkh(cardno);
		// 社会保障号码
		String cernum = element.element("AAC002").getText();
		po.setSfzh(cernum);
		// 姓名
		String name = element.element("AAC003").getText();
		po.setName(name);
		// 性别
		String sex = element.element("AAC004").getText();
		po.setSex(sex);
		// 民族
		String nation = element.element("AAC005").getText();
		po.setEthnic(Constants.NATION.get(nation));
		// 出生日期
		String birthday = element.element("AAC006").getText();
		po.setBirthday(birthday);
		// 人员状态
		String person_status = element.element("AAC008").getText();
		po.setPersonstatus(Constants.RYZT_STATUS.get(person_status));
		// 户口性质
		String AAC009 = element.element("AAC009").getText();
		po.setRegisttype(AAC009);
		// 户口所在地址
		String address = element.element("AAC010").getText();
		po.setRegaddr(address);
		// 联系手机
		String mobile = element.element("MOBILE").getText();
		po.setPhoneno(mobile);
		// 联系电话
		String phone = element.element("AAE005").getText();
		po.setTelno(phone);
		// 通讯地址
		String mobile_address = element.element("AAE006").getText();
		po.setMailaddr(mobile_address);
		// 邮政编码
		String AAE007 = element.element("AAE007").getText();
		po.setZipcode(AAE007);
		// 开卡银行
		String bankid = element.element("AAE008").getText();
		po.setBank(bankid);
		// 银行卡号
		String bankno = element.element("AAE010").getText();
		po.setBankno(bankno);
		// 邮箱地址
		String email = element.element("EMAIL").getText();
		po.setEmail(email);
		// 单位编号
		String unitno = element.element("AAB001").getText();
		po.setDwbh(unitno);
		// 单位名称
		String unitname = element.element("AAB004").getText();
		po.setDwmc(unitname);
		// 照片 BASE64编码的相片数据
		if (element.element("PHOTO") != null) {
			String PHOTO = element.element("PHOTO").getText();
			po.setPhoto(PHOTO);
		}
		return po;
	}
}
