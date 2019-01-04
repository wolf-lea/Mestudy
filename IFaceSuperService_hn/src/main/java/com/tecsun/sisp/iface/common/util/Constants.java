package com.tecsun.sisp.iface.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangqingjie on 15-5-11.
 */
public class Constants {

	public static String RESULT_MESSAGE_SUCCESS = "200";
	public static String RESULT_MESSAGE_EXCEPTION = "999";
	public static String RESULT_MESSAGE_ERROR = "0";
	public static String RESULT_MESSAGE_EMPTY = "201";//参数缺失

	//调用生物检测
	public static final String RETURN_SUCCESS_00="00";//获取的数据正常
	public static final String PID="人员查询";//获取的数据正常
	public static final String QAID="认证查询";//认证查询
	public static final String BID="批量查询";//获取的数据正常
	public static final String FID="认证流水";//获取的数据正常
	public static final String LID="地市认证";//获取的数据正常
	public static final String SIGN="广东德生科技股份有限公司";//签名
	
	public static final String TSB = "TSB";// 德生宝
	public static final String APP = "App";// 手机应用
	public static final String NETPORTAL = "NetPortal";// 网办
	public static final String SELFSERVICE = "SelfService";// 自助终端
	public static final String WECHAT = "WeChat";// 微信

	public static String RESULT_MESSAGE_MSG= "调用接口出现异常";
	// 自助终端，网办等渠道签到用到的key前缀+uuid,value为当前客户端的ip
	public static String TOKENNAME = "sisp_iface:token:";

	// 终端金融业务签到用到的key,value:pik;mac;tdk;patchNo;zdh;shh;seq
	public static String SECRETWK = "sisp_iface:fee:bank_code:zdh:shh";

	// 字典组 key
	public static String DICTIONGROUPKEY = "sisp_public:cmp_core:dictionary:{groupId}:detaillist";

	// 自助终端超时时间 单位分钟
	public static Integer SELFSERVICETIMEOUT = 120;

	
	
	// 渠道接口类型 大众端-02、德生宝-01
	public static final String CHANNELTYPE_01 = "01";
	public static final String CHANNELTYPE_02 = "02";

	// 业务分析子系统请求地址
	public static final String TRANSANALYSIS_URI = "/ans/translog/addTransLog";
	// 字典表查询，根据输入的groupid查询({groupid})
	public static final String CHANNLCODES_URI = "/core/dictionary/queryRedisDictionByGroupId";
	// 查询设备管理子系统校验设备是否存在({deviceId})
	public static final String DEV_URI = "/dev/deviceRegist/checkDevExist";

	// 查询设备表是否需要更新秘钥(/getIsupdateWKByMac/{macAddress})
	public static final String ISUPDATEWK_URI = "/dev/deviceRegist/getIsupdateWKByMac";
	// 客户端更新完秘钥后需要把设备表是否更新秘钥的状态改为 0 已更新（/updateIsupdateWKByMac/{macAddress}）
	public static final String UPDATEWKBYMAC_URI = "/dev/deviceRegist/updateIsupdateWKByMac";
	// 根据设备编码获取银行，终端号，商户号，用于请求到bankservice(queryBankByDeviceId/{deviceId})
	public static final String GETBANK_URI = "/core/bank/queryBankByDeviceId";

	// 统筹区域
	public static final String DISTRICT_GROUP = "DISTRICT";

	


}
