package com.tecsun.sisp.adapter.common.util;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by DG on 2015/9/27.
 */
public class Constants {
	public static String RESULT_KEY_STATUSCODE = "statusCode";
	public static String RESULT_KEY_MESSAGE = "message";
	public static String RESULT_MESSAGE_SUCCESS = "200";
    public static String RESULT_MESSAGE_EXCEPTION = "999";
    public static String RESULT_MESSAGE_ERROR = "0";
    public static String RESULT_MESSAGE_EMPTY = "201";//参数缺失（社保卡业务）
    public static String RESULT_MESSAGE_PARAM = "-300";//参数错误
    public static String RESULT_MESSAGE_BEFORE = "202";//过去已激活

    public static final String TSB = "TSB";//德生宝
    public static final String APP = "App";//手机应用
    public static final String NETPORTAL = "NetPortal";//网办
    public static final String SELFSERVICE = "SelfService";//自助终端
    public static final String WECHAT = "WeChat";//微信
    public static final String ALIPAY = "Alipay";//支付宝（生活号）
	public static final String PC = "PC";//pc端

    public final static String CONFIG_YES = "1";//配置文件config.properties （0:否；1:是）
    public final static String CONFIG_NO = "0";//配置文件config.properties （0:否；1:是）


    //自助终端，网办等渠道签到用到的key前缀+uuid,value为当前客户端的ip
    public static String TOKENNAME = "sisp_iface:token:";
    
    public static String USERKEY = "userkey:";
    
    public static String USERAREAID = "userAreaid:";
    
    //自助终端超时时间 单位分钟
    public static Integer SELFSERVICETIMEOUT = 120;

    public static String DATEOATTER="yyyy-MM-dd HH:mm:ss";

    /**
     * 字典表---渠道字典组*
     */
    public final static String CHANNELCODE = "channeltype";
    public static final String SEX_GROUP = "SEX";//性别：01男性;02女性;03未知
    public static final String VERIFY_TYPE_GROUP = "VERIFY_TYPE";//认证方式
    public static final String BUSINESS_STATUS_GROUP = "BUSINESS_STATUS";//业务结果编码
    public static final String PAYMENT_TYPE_GROUP = "PAYMENT_TYPE";//支付方式编码
    public static final String HIS_ORDER_STATUS_GROUP = "HIS_ORDER_STATUS";//医院业务结果编码

    //字典组 key
    public static String DICTIONGROUPKEY = "sisp_public:cmp_core:dictionary:{groupId}:detaillist";
    
  //业务分析子系统请求地址
    public static final String TRANSANALYSIS_URI="/ans/translog/addTransLog";
    

    //字典表查询，根据输入的groupid查询({groupid})
    public static final String CHANNLCODES_URI="/core/dictionary/queryRedisDictionByGroupId";
    //查询设备管理子系统校验设备是否存在({deviceId})
    public static final String DEV_URI="/dev/deviceRegist/checkDevExist";
    //根据区域id获取区域信息
    public static final String QUERYDISTINCT_URL = "/core/distinct/queryDistinct";
    //第三方登陆
    public static final String INTERFACE_USERLOGIN = "/interfacefunction/user/userLogin";
    //根据用户id，用户方法检测接口用户权限
    public static final String INTERFACE_CHECKUSER = "/interfacefunction/user/checkInterfaceUserRoleFunc";



    //社保卡业务
    public static final String CARD_SETLOSS="iface/card/setLoss";//临时挂失
    public static final String CARD_SETHANGING="iface/card/setHanging";//解挂
    public static final String CARD_SETSTART="iface/card/setStart";//社保卡激活

    //认证云业务
    public static final String RZY_COLLECTPHOTO="/tscaiface/face/common/collectPhoto";//照片采集
    public static final String RZY_COLLECTLIST="/tscaiface/query/collectList";//查询个人已采集项目
    public static final String RZY_UPLOADLIVEDETECTRESULT="/tscaiface/face/lantian/uploadLiveDetectResult";//提交活体检测结果采集项目
    public static final String RZY_FACEVERIFICATION="/tscaiface/face/lantian/onlineauth";//人脸对比/1:1认证
    public static final String RZY_VERIFYRESULT="/tscaiface/query/verifyResult";//查询认证结果
    public static final String RZY_COMPAREPHOTO ="/tscaiface/face/lantian/comparePhoto";// 人脸对比(两张照片)
    public static final String RZY_LIVEDETECTION="/tscaiface/face/lantian/liveDetection";//人脸检测（红外活体）
    public static final String RZY_GZTFACECOMPARE="/tscaiface/face/gzt/gztFaceCompare";//国政通人像比对

    public static final String RZY_ISCOLLECTFINGERPRINT = "/tscaiface/finger/yimu/isCollectFingerprint";//判断指纹是否已采集
    public static final String RZY_COLLECTFINGERPRINT="/tscaiface/finger/yimu/collectFingerprint";//指纹采集
    public static final String RZY_FINGERPRINTAUTH="/tscaiface/finger/yimu/fingerprintAuth";//指纹认证
    public static final String RZY_FINGERVERIFYRESULT="/tscaiface/finger/yimu/fingerVerifyResult";//查询指纹认证结果
    public static final String RZY_FINGERONLINEAUTH ="/tscaiface/finger/yimu/fingerOnlineAuth";//指纹对比/1:1认证
    public static final String RZY_GETFINGERPRINTDATA ="";// 获取指纹采集信息（终端认证）
    public static final String RZY_UPLOADFINGERPRINTVERIFY ="/tscaiface/finger/yimu/saveVerifyResult";// 上传指纹认证结果（终端）

    public static final String RZY_ISCOLLECTFINGERVEIN = "";//判断指静脉是否已采集
    public static final String RZY_COLLECTFINGERVEIN="/tscaiface/vein/yimu/collectFingervein";//指静脉采集
    public static final String RZY_FINGERVEINAUTH="";//指静脉认证
    public static final String RZY_FINGERVEINVERIFYRESULT="";//查询指静脉认证结果
    public static final String RZY_FINGERVEINONLINEAUTH ="";//指静脉对比/1:1认证
    public static final String RZY_GETFINGERVEINDATA ="";// 获取指静脉采集信息（终端认证）
    public static final String RZY_UPLOADFINGERVEINVERIFY ="/tscaiface/vein/yimu/saveVerifyResult";// 上传指静脉认证结果（终端）

    public static final String RZY_PERSONINFO ="/tscaiface/query/selectPersonInfo";//查询个人基本信息
    public static final String RZY_QUERYCOLLECTION ="/tscaiface/query/queryCollectInfo";//查询个人采集数据


    //处理是否成功；00-- 初始状态；01—成功；02—失败 ; 03--预处理成功 ； 04--预处理失败；11—待审核
    public static final String PIXEL_BUSINESS_STATUS_00 = "00";
    public static final String PIXEL_BUSINESS_STATUS_01 = "01";
    public static final String PIXEL_BUSINESS_STATUS_02 = "02";
    public static final String PIXEL_BUSINESS_STATUS_03 = "03";
    public static final String PIXEL_BUSINESS_STATUS_04 = "04";
    public static final String PIXEL_BUSINESS_STATUS_11 = "11";


    //    图片类型编码说明 picType
    public static final String PICTURE_TYPE_000= "000";//默认类型
    public static final String PICTURE_TYPE_101= "101";//社保卡申请—参保人相片（待处理）
    public static final String PICTURE_TYPE_102= "102";//社保卡申请—参保人相片（已处理）
    public static final String PICTURE_TYPE_103= "103";//社保卡申请—身份证正面相片
    public static final String PICTURE_TYPE_104= "104";//社保卡申请—身份证反面相片
    public static final String PICTURE_TYPE_105= "105";//社保卡申请—电子签名相片
    public static final String PICTURE_TYPE_201= "201";//待遇资格认证—个人信息头像
    public static final String PICTURE_TYPE_202= "202";//待遇资格认证—人脸采集照片
    public static final String PICTURE_TYPE_203= "203";//待遇资格认证—人脸认证照片
    public static final String PICTURE_TYPE_204= "204";//待遇资格认证—指纹（左手）采集照片
    public static final String PICTURE_TYPE_205= "205";//待遇资格认证—指纹（右手）采集照片
    public static final String PICTURE_TYPE_206= "206";//待遇资格认证—指纹认证照片
    public static final String PICTURE_TYPE_207= "207";//待遇资格认证—指静脉（左手）采集照片
    public static final String PICTURE_TYPE_208= "208";//待遇资格认证—指静脉（右手）采集照片
    public static final String PICTURE_TYPE_209= "209";//待遇资格认证—指静脉认证照片
    public static final String PICTURE_TYPE_210= "210";//待遇资格认证—活体检测图片
    public static final String PICTURE_TYPE_001= "001";//比对照片
    public static final String PICTURE_TYPE_002= "002";//实名认证照片
    public static final String PICTURE_TYPE_111= "111";//社保卡业务精准发卡—本人相片
    public static final String PICTURE_TYPE_112= "112";//社保卡业务精准发卡—本人签名照
    public static final String PICTURE_TYPE_113= "113";//社保卡业务精准发卡—代办人相片
    public static final String PICTURE_TYPE_114= "114";//社保卡业务精准发卡—代办人签名照
	public static final String PICTURE_TYPE_004= "004";//个人简历照片
    public static final String PICTURE_TYPE_005= "005";//公司营业执照照片
    public static final String PICTURE_TYPE_006= "006";//公司logo照片
	public static final String PICTURE_TYPE_301 = "301";// 全民参保登记-身份证正面
	public static final String PICTURE_TYPE_302 = "302";// 全民参保登记-身份证反面
	public static final String PICTURE_TYPE_303 = "303";// 全民参保登记-户口簿户主页
	public static final String PICTURE_TYPE_304 = "304";// 全民参保登记-户口簿本人页
	public static final String PICTURE_TYPE_305 = "305";// 全民参保登记-个人相片
	
	//个人用工信息查询 
    public static final String Ine_QueryType_A = "0" ; //表示是求职
    public static final String Ine_QueryType_R = "1" ; //表示是招工
    
    //个人用工操作类型
    public static final String Ine_OpType_Edit = "0" ;//表示是编辑发布的信息
    public static final String Ine_OpType_Add  = "1" ;//标记是新增发布信息
    //地区级别
    public static final String AREA_LEVEL_CITY  = "city" ;//城市级别
    public static final String AREA_LEVEL_STREET  = "street" ;//街道级别
    public static final String AREA_LEVEL_DISTRICT  = "district" ;//区域级别
    
    public static final String UPDATE_TIME_NAME_01 = "前一天";
    public static final String UPDATE_TIME_NAME_02 = "前两天";
    public static final String UPDATE_TIME_NAME_03 = "前三天";
    public static final String UPDATE_TIME_NAME_00 = "今天";
    
    //信息类型  用于查询岗位
    public static final String T_INFO_TYPE_INE  = "ine" ;//表示个人用工
    public static final String T_INFO_TYPE_FAIRJOB  = "fairJob" ;//表示招聘

    //    图片类型编码对应存放地址说明 picture_path
    public static Map<String, String> picture_path = new HashMap<String, String>();
    static {
        picture_path.put("000", "picture_path");//默认类型
        picture_path.put("101", "picture_path_101");//社保卡申请—参保人相片（待处理）
        picture_path.put("102", "picture_path_102");//社保卡申请—参保人相片（已处理）
        picture_path.put("103", "picture_path_103");//社保卡申请—身份证正面(国徽)相片
        picture_path.put("104", "picture_path_104");//社保卡申请—身份证反面(人像)相片
        picture_path.put("105", "picture_path_105");//社保卡申请—电子签名相片
        picture_path.put("201", "picture_path_201");//待遇资格认证—个人信息头像
        picture_path.put("202", "picture_path_202");//待遇资格认证—人脸采集照片
        picture_path.put("203", "picture_path_203");//待遇资格认证—人脸认证照片
        picture_path.put("204", "picture_path_204");//待遇资格认证—指纹（左手）采集照片
        picture_path.put("205", "picture_path_205");//待遇资格认证—指纹（右手）采集照片
        picture_path.put("206", "picture_path_206");//待遇资格认证—指纹认证照片
        picture_path.put("207", "picture_path_207");//待遇资格认证—指静脉（左手）采集照片
        picture_path.put("208", "picture_path_208");//待遇资格认证—指静脉（右手）采集照片
        picture_path.put("209", "picture_path_209");//待遇资格认证—指静脉认证照片
        picture_path.put("210", "picture_path_210");//待遇资格认证—活体检测图片
        picture_path.put("001", "picture_path_001");//比对照片
        picture_path.put("002", "picture_path_002");//实名认证照片
        picture_path.put("111", "picture_path_111");//社保卡业务精准发卡—本人相片
        picture_path.put("112", "picture_path_112");//社保卡业务精准发卡—本人签名照
        picture_path.put("113", "picture_path_113");//社保卡业务精准发卡—代办人相片
        picture_path.put("114", "picture_path_114");//社保卡业务精准发卡—代办人签名照
        picture_path.put("121", "picture_path_121");//社保卡业务-密码修改/重置-本人照片
        picture_path.put("003", "picture_path_003");//账号头像（APP、支付宝）
		picture_path.put("004", "picture_path_004");//个人简历照片
        picture_path.put("005", "picture_path_005");//公司营业执照照片
        picture_path.put("006", "picture_path_006");//公司logo照片
		picture_path.put("301", "picture_path_301");// 全民参保登记-身份证正面
		picture_path.put("302", "picture_path_302");// 全民参保登记-身份证反面
		picture_path.put("303", "picture_path_303");// 全民参保登记-户口簿户主页
		picture_path.put("304", "picture_path_304");// 全民参保登记-户口簿本人页
		picture_path.put("305", "picture_path_305");// 全民参保登记-个人相片
    }

    public static Map<String, String> NATION = new HashMap<String, String>();
    static {
        NATION.put("01", "汉");
        NATION.put("02", "蒙古");
        NATION.put("03", "回");
        NATION.put("04", "藏");
        NATION.put("05", "维吾尔");
        NATION.put("06", "苗");
        NATION.put("07", "彝");
        NATION.put("08", "壮");
        NATION.put("09", "布依");
        NATION.put("10", "朝鲜");
        NATION.put("11", "满");
        NATION.put("12", "侗");
        NATION.put("13", "瑶");
        NATION.put("14", "白");
        NATION.put("15", "土家");
        NATION.put("16", "哈尼");
        NATION.put("17", "哈萨克");
        NATION.put("18", "傣");
        NATION.put("19", "黎");
        NATION.put("20", "傈傈");
        NATION.put("21", "佤");
        NATION.put("22", "畲");
        NATION.put("23", "高山");
        NATION.put("24", "拉祜");
        NATION.put("25", "水");
        NATION.put("26", "东乡");
        NATION.put("27", "纳西");
        NATION.put("28", "景颇");
        NATION.put("29", "柯尔克孜");
        NATION.put("30", "土");
        NATION.put("31", "达翰尔");
        NATION.put("32", "仫佬");
        NATION.put("33", "羌");
        NATION.put("34", "布朗");
        NATION.put("35", "撒拉");
        NATION.put("36", "毛南");
        NATION.put("37", "仡佬");
        NATION.put("38", "锡伯");
        NATION.put("39", "阿昌");
        NATION.put("40", "普米");
        NATION.put("41", "塔吉克");
        NATION.put("42", "怒");
        NATION.put("43", "乌孜别克");
        NATION.put("44", "俄罗斯");
        NATION.put("45", "鄂温克");
        NATION.put("46", "德昂");
        NATION.put("47", "保安");
        NATION.put("48", "裕固");
        NATION.put("49", "京");
        NATION.put("50", "塔塔尔");
        NATION.put("51", "独龙");
        NATION.put("52", "鄂伦春");
        NATION.put("53", "赫哲");
        NATION.put("54", "门巴");
        NATION.put("55", "珞巴");
        NATION.put("56", "基诺");
        NATION.put("57","穿青人");
        NATION.put("99","其他");
    }
    public static Map<String, String> NATION_Code = new HashMap<String, String>();
    static {
        NATION_Code.put("汉","01");
        NATION_Code.put("蒙古","02");
        NATION_Code.put("回","03");
        NATION_Code.put("藏","04");
        NATION_Code.put("维吾尔","05");
        NATION_Code.put("苗", "06");
        NATION_Code.put("彝", "07");
        NATION_Code.put("壮", "08");
        NATION_Code.put("布依", "09");
        NATION_Code.put("朝鲜","10");
        NATION_Code.put("满","11");
        NATION_Code.put("侗","12");
        NATION_Code.put("瑶","13");
        NATION_Code.put("白","14");
        NATION_Code.put("土家","15");
        NATION_Code.put("哈","16");
        NATION_Code.put("哈萨克","17");
        NATION_Code.put("傣","18");
        NATION_Code.put("黎","19");
        NATION_Code.put("傈傈","20");
        NATION_Code.put("佤","20");
        NATION_Code.put("畲","22");
        NATION_Code.put("高山","23");
        NATION_Code.put("拉祜","24");
        NATION_Code.put("水","25");
        NATION_Code.put("东乡","26");
        NATION_Code.put("纳西","27");
        NATION_Code.put("景颇","28");
        NATION_Code.put("柯尔克孜","29");
        NATION_Code.put("土","30");
        NATION_Code.put("达翰尔","31");
        NATION_Code.put("仫佬","32");
        NATION_Code.put("羌","33");
        NATION_Code.put("布朗","34");
        NATION_Code.put("撒拉","35");
        NATION_Code.put("毛南","36");
        NATION_Code.put("仡佬","37");
        NATION_Code.put("锡伯","38");
        NATION_Code.put("阿昌","39");
        NATION_Code.put("普米","40");
        NATION_Code.put("塔吉克","41");
        NATION_Code.put("怒","42");
        NATION_Code.put("乌孜别克","43");
        NATION_Code.put("俄罗斯","44");
        NATION_Code.put("鄂温克","45");
        NATION_Code.put("德昂","46");
        NATION_Code.put("保安","47");
        NATION_Code.put("裕固","48");
        NATION_Code.put("京","49");
        NATION_Code.put("塔塔尔","50");
        NATION_Code.put("独龙","51");
        NATION_Code.put("鄂伦春","52");
        NATION_Code.put("赫哲","53");
        NATION_Code.put("门巴","54");
        NATION_Code.put("珞巴","55");
        NATION_Code.put("基诺","56");
        NATION_Code.put("穿青人","57");
        NATION_Code.put("其他","99");
    }
    //    认证方式 说明
    public final static Map<String, String>  VERIFY_TYPE= new HashMap<String, String>();
    static {
        VERIFY_TYPE.put("01", "人脸");//
        VERIFY_TYPE.put("02", "声纹");//
        VERIFY_TYPE.put("03", "指纹");//指纹（云端)
        VERIFY_TYPE.put("04", "指静脉");//指静脉（云端)
        VERIFY_TYPE.put("05", "活体检测（终端）");//
        VERIFY_TYPE.put("06", "指纹（终端）");//
        VERIFY_TYPE.put("07", "指静脉（终端）");//
        VERIFY_TYPE.put("08", "活体检测");//活体检测（云端)
    }


    public static final String DYZT_0 = "0";//凭条打印状态 0-未打印,
    public static final String DYZT_1 = "1";//凭条打印状态 1-打印,

    public final static String BUS_TYPE_MOBILE = "mobile";//手机充值

    public final static Map<String,Integer>  TRANS_TYPE= new HashMap<String,Integer>();//交易类型 : 1-消费；2-转账；3-取款;4-缴费；5-取款
    static {
        TRANS_TYPE.put("consume", 1);//消费
        TRANS_TYPE.put("transfer", 2);//转账
        TRANS_TYPE.put("zn_withdrawals", 3);//助农取款
        TRANS_TYPE.put("payment", 4);//缴费
        TRANS_TYPE.put("sb_payment", 4);//社保缴费
        TRANS_TYPE.put("sh_payment", 4);//生活缴费
        TRANS_TYPE.put("credit", 4);//信用卡还款-金融三项
        TRANS_TYPE.put("mobile", 4);//手机充值-金融三项
        TRANS_TYPE.put("remaining", 5);//余额查询-金融三项
    }


    //===================社保缴费================
    //页码
    public final static String PAGE = "page";
    public final static String PAGE_NO = "pageno";
    public final static String PAGE_SIZE = "pagesize";
    public final static String DATA_COUNT = "count";

    //   缴费状态
    public final static String SBJF_STATUS_OF_INIT = "0";//初始状态
    public final static String SBJF_STATUS_OF_IS_PAY = "1";//已缴费
    public final static String SBJF_STATUS_OF_FAIL_PAY = "2";//缴费失败
    public final static String SBJF_STATUS_OF_ON_PAY = "3";//在途中

    //冲正状态
    public final static String SBJF_STATUS_OF_IS_REVERSE= "1";//冲正成功
    public final static String SBJF_STATUS_OF_FAIL_REVERSE= "2";//冲正失败

    //险种编码
    public final static String SBJF_INSURE_OF_EI = "EI";//养老保险
    public final static String SBJF_INSURE_OF_MI = "MI";//医疗保险
    public final static String SBJF_INSURE_OF_FE = "FE";//自由就业

    // 操作类型
    public final static int SBJF_OPERATE_SUBMIT = 0;//提交缴费
    public final static int SBJF_OPERATE_REVERSE = 1005;//冲正操作

    //社保缴费打印次数控制
    public final static String SBJF_PRINT_COUNT = "sbjf.print.count";
    public final static String SBJF_PRINT_TIMEOUT = "sbjf.print.timeout";//控制失效时间

    //险种类型编码
    public final static Map<String,String> INSURE_TYPE = new HashMap<>();
    static {
        INSURE_TYPE.put("EI", "城乡居民养老保险");
        INSURE_TYPE.put("MI", "城乡居民医疗保险");
        INSURE_TYPE.put("FE", "灵活就业缴费");
    }
    //需对应赋值 医院编码--对应接口类（模拟5家医院）
    public final static Map<String, String> HIS_IFACE = new HashMap<String, String>();
    static {
        HIS_IFACE.put("001", "com.tecsun.sisp.adapter.modules.his.hisUtils.impl.TestHisFirstImpl");
        HIS_IFACE.put("002", "com.tecsun.sisp.adapter.modules.his.hisUtils.impl.TestHisSecondImpl");
        HIS_IFACE.put("1003", "com.tecsun.sisp.adapter.modules.his.hisUtils.impl.TestHisFirstImpl");
        HIS_IFACE.put("1004", "com.tecsun.sisp.adapter.modules.his.hisUtils.impl.TestHisFirstImpl");
        HIS_IFACE.put("1005", "com.tecsun.sisp.adapter.modules.his.hisUtils.impl.TestHisFirstImpl");
    }

    public final static Map<String, String> HIS_CLINIC_FLAG = new HashMap<String, String>();
    static {
        HIS_CLINIC_FLAG.put("1", "预约");
        HIS_CLINIC_FLAG.put("2", "已满");
    }
    //医院订单状态
    // 0--初始状态;1--预约成功;2--预约失败;3--取消成功;4--取消失败;5--缴费成功;6--缴费失败;7--退费成功;8--退费失败;9--取号成功;10--取号失败;11-未就诊(已过就诊时间）
    public final static String HIS_ORDER_STATUS_0 = "0";
    public final static String HIS_ORDER_STATUS_1 = "1";
    public final static String HIS_ORDER_STATUS_2 = "2";
    public final static String HIS_ORDER_STATUS_3 = "3";
    public final static String HIS_ORDER_STATUS_4 = "4";
    public final static String HIS_ORDER_STATUS_5 = "5";
    public final static String HIS_ORDER_STATUS_6 = "6";
    public final static String HIS_ORDER_STATUS_7 = "7";
    public final static String HIS_ORDER_STATUS_8 = "8";
    public final static String HIS_ORDER_STATUS_9 = "9";
    public final static String HIS_ORDER_STATUS_10 = "10";
    public final static String HIS_ORDER_STATUS_11 = "11";

    //村医通门诊结算、汇总
    public static Map<String, String> CYT_ACCOUNT_STATUS = new HashMap<>();
    static {
        CYT_ACCOUNT_STATUS.put("0", "已结算");
        CYT_ACCOUNT_STATUS.put("1", "已取消");
    }
    public static Map<String, String> CYT_COLLECT_STATUS = new HashMap<>();
    static {
        CYT_COLLECT_STATUS.put("0", "未汇总");
        CYT_COLLECT_STATUS.put("1", "已汇总");
        CYT_COLLECT_STATUS.put("2", "已取消");
        CYT_COLLECT_STATUS.put("3", "已审核");
    }
    public static Map<String, String> CYT_YXBZ_STATUS = new HashMap<>();
    static {
        CYT_YXBZ_STATUS.put("0", "有效");
        CYT_YXBZ_STATUS.put("1", "无效");
    }
    //参保状态
    public static Map<String, String> CYT_CBZT_STATUS = new HashMap<>();
    static {
        CYT_CBZT_STATUS.put("0", "未参保");
        CYT_CBZT_STATUS.put("1", "正常参保");
        CYT_CBZT_STATUS.put("2", "暂停参保");
        CYT_CBZT_STATUS.put("3", "终止参保");
    }

    //我的家庭
    public static Map<String, String> FAMILY_SEX_STATUS = new HashMap<>();
    static {
        FAMILY_SEX_STATUS.put("0", "女");
        FAMILY_SEX_STATUS.put("1", "男");
    }

    //消息类型
    public static final String MESSAGE_TEXT = "text";
    public static final String MESSAGE_IMAGE = "image";
    public static final String MESSAGE_VOICE = "voice";

    //智能客服-用户问答机器人的渠道编号（捷通华声):智能客服系统配置
    public static final Map<String, String> ROBOT_CONNTYPE = new HashMap<>();
    static {
        ROBOT_CONNTYPE.put("WeChat", "1");
        ROBOT_CONNTYPE.put("NetPortal", "2");
        ROBOT_CONNTYPE.put("App", "3");
        ROBOT_CONNTYPE.put("TSB", "4");
        ROBOT_CONNTYPE.put("Alipay", "5");
        ROBOT_CONNTYPE.put("SelfService", "6");
    }

    //培训就业年份
    public static Map<String, String> TRAIN_MONTH_STATUS = new HashMap<>();
    static {
        TRAIN_MONTH_STATUS.put("1月", "01");
        TRAIN_MONTH_STATUS.put("2月", "02");
        TRAIN_MONTH_STATUS.put("3月", "03");
        TRAIN_MONTH_STATUS.put("4月", "04");
        TRAIN_MONTH_STATUS.put("5月", "05");
        TRAIN_MONTH_STATUS.put("6月", "06");
        TRAIN_MONTH_STATUS.put("7月", "07");
        TRAIN_MONTH_STATUS.put("8月", "08");
        TRAIN_MONTH_STATUS.put("9月", "09");
        TRAIN_MONTH_STATUS.put("10月", "10");
        TRAIN_MONTH_STATUS.put("11月", "11");
        TRAIN_MONTH_STATUS.put("12月", "12");
    }
    
    //孝感
    public static final String TS_V_JBXX = "ts_v_jbxx";//17.个人基本信息
    public static final String TS_V_BP_GRCBXX = "ts_v_bp_grcbxx";//1.个人社保基本信息
    public static final String TS_V_BP_GRJFJSXX = "ts_v_bp_grjfjsxx";//2.个人缴费基数信息
    public static final String TS_V_BP_GRJFXX = "ts_v_bp_grjfxx";//3.个人养老保险缴费信息
    public static final String TS_V_BP_GSBXJBXX = "ts_v_bp_gsbxjbxx";//4.个人工伤保险基本信息
    public static final String TS_V_BP_LYBXJBXX = "ts_v_bp_lybxjbxx";//5.失业保险基本信息
    public static final String TS_V_BP_MTBXFYJSXX = "ts_v_bp_mtbxfyjsxx";//6.医疗保险费用结算信息
    public static final String TS_V_BP_MTBXGRJBXX = "ts_v_bp_mtbxgrjbxx";//7.医疗保险个人帐户信息
    public static final String TS_V_BP_MTBXGRJFMX = "ts_v_bp_mtbxgrjfmx";//8.医疗保险个人缴费信息
    public static final String TS_V_BP_SYBXJBXX = "ts_v_bp_sybxjbxx";//9.生育保险基本信息
    public static final String TS_V_BP_SYDYFFXX = "ts_v_bp_sydyffxx";//10.失业人员待遇支付明细表
    public static final String TS_V_BP_YLBXGRJFMX = "ts_v_bp_ylbxgrjfmx";//11.养老保险个人缴费明细
    public static final String TS_V_BP_YLBXJBXX = "ts_v_bp_ylbxjbxx";//12.养老保险基本信息
    public static final String TS_V_BP_YLBXLTXRYDYXX = "ts_v_bp_ylbxltxrydyxx";//13.离退休人员待遇信息
    public static final String TS_V_BP_YLDYFFXX = "ts_v_bp_yldyffxx";//14.养老保险待遇支付信息
    public static final String TS_V_BP_YLGRZHXX = "ts_v_bp_ylgrzhxx";//15.养老保险个人年帐户
    public static final String TS_V_BP_YLYZHXX = "ts_v_bp_ylyzhxx";//16.养老保险月账户
    public static final String PERSONINSUREDSTATE_GROUP = "PERSONINSUREDSTATE";//个人参保状态
    public static final String RYLB_CODE = "9";//医疗类型； 9开关的是居民，非9开头的是职工；(select * from hbsimis_xgjb.AA10 where aaa100='AKC021')
    public static final String RYLB_VALUE1 ="居民";
    public static final String RYLB_VALUE2 ="职工";
    public static final String INSURECANCETYPE_GROUP = "INSURECANCETYPE";//险种类型
    public static final String MONEYITEMTYPE_GROUP = "MONEYITEMTYPE";//款项类型
    public static final String FIVEPERATATUS_GROUP = "FIVEPERATATUS";//(五险)人员状态
    public static final String PARAM_NATION_GROUP = "PARAM_NATION";//民族
    //参保险种
    public static Map<String, String> CBXZ_TYPE = new HashMap<>();
    static {
    	CBXZ_TYPE.put("11", "企业基本养老保险");
    	CBXZ_TYPE.put("12", "机关事业养老保险");
    	CBXZ_TYPE.put("14", "企事业年金");
    	CBXZ_TYPE.put("15", "乡镇事业养老保险");
    	CBXZ_TYPE.put("21", "失业保险");
    	CBXZ_TYPE.put("31", "基本医疗保险");
    	CBXZ_TYPE.put("32", "大额医疗保险");
    	CBXZ_TYPE.put("33", "公务员补助");
    	CBXZ_TYPE.put("34", "补充医疗保险");
    	CBXZ_TYPE.put("35", "离休医疗保险");
    	CBXZ_TYPE.put("36", "伤残军人医疗保险");
    	CBXZ_TYPE.put("37", "住院统筹保险");
    	CBXZ_TYPE.put("41", "工伤保险");
    	CBXZ_TYPE.put("51", "生育保险");
    	CBXZ_TYPE.put("61", "城镇居民医疗保险");
    }
    
    public static final String DW_CODE = "20";//单位编码；
    public static final String GR_CODE = "10";//个人编码；
    
    //人员状态
    public static Map<String, String> RYZT_STATUS = new HashMap<>();
    static {
    	RYZT_STATUS.put("11", "在职");
    	RYZT_STATUS.put("21", "退休");
    	RYZT_STATUS.put("31", "离休类");
    	RYZT_STATUS.put("41", "居民");
    }
    
    //款项类型
    public static Map<String, String> MONEYITEMTYPE = new HashMap<>();
    static {
    	MONEYITEMTYPE.put("10", "个人缴纳");
    	MONEYITEMTYPE.put("20", "单位缴纳");
    	MONEYITEMTYPE.put("41", "单位缴利息");
    	MONEYITEMTYPE.put("42", "个人缴利息");
    	MONEYITEMTYPE.put("50", "滞纳金");
    	MONEYITEMTYPE.put("81", "社平划转");
    	MONEYITEMTYPE.put("90", "风险调剂金");
    }
}

