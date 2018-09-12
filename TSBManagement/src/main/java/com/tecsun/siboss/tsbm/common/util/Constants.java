package com.tecsun.siboss.tsbm.common.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangqingjie on 15-5-11.
 */
public class Constants {

    public static String RESULT_MESSAGE_SUCCESS = "200";    //成功
    public static String RESULT_MESSAGE_EXCEPTION = "999";  //失败，由于本地后台导致的失败，如数据库，网络等
    public static String RESULT_MESSAGE_FAILURE = "500";
    public static String RESULT_MESSAGE_ERROR = "0";

    public static String CHANNELCODE = "channeltype";

    //德生宝签到 存入redis的key
    public static String TSBTOKENCODE = "siboss:iface:token:tsb_{md5}";
    //微信签到 存入redis的key
    public static String WECHATTOKENCODE = "siboss:iface:token:wechat_{md5}";

    public static String DATEPATTERN = "yyyy-MM-dd HH:mm:ss";

    //字典组 key
    public static String DICTIONGROUPKEY = "siboss:cmp:dictionary_detaillist:{groupId}";

    //业务分析子系统请求地址
    public static final String TRANSANALYSIS_URI = "/ans/translog/addTransLog";
    //查询设备管理子系统校验设备是否存在({deviceId})
    public static final String DEV_URI = "/dev/deviceRegist/checkDevExist";


    //发送短信地址
    public static String MSGURL = "http://61.28.113.182:8482/sms/send";
    public static String ORGANIZATION = "德生科技";//发送短信机构名称


    public static Map<String, String> NATION = new HashMap<String, String>();

    static {
        NATION.put("01", "汉族");
        NATION.put("02", "蒙古族");
        NATION.put("03", "回族");
        NATION.put("04", "藏族");
        NATION.put("05", "维吾尔族");
        NATION.put("06", "苗族");
        NATION.put("07", "彝族");
        NATION.put("08", "壮族");
        NATION.put("09", "布依族");
        NATION.put("10", "朝鲜族");
        NATION.put("11", "满族");
        NATION.put("12", "侗族");
        NATION.put("13", "瑶族");
        NATION.put("14", "白族");
        NATION.put("15", "土家族");
        NATION.put("16", "哈尼族");
        NATION.put("17", "哈萨克族");
        NATION.put("18", "傣族");
        NATION.put("19", "黎族");
        NATION.put("20", "傈傈族");
        NATION.put("21", "佤族");
        NATION.put("22", "畲族");
        NATION.put("23", "高山族");
        NATION.put("24", "拉祜族");
        NATION.put("25", "水族");
        NATION.put("26", "东乡族");
        NATION.put("27", "纳西族");
        NATION.put("28", "景颇族");
        NATION.put("29", "柯尔克孜族");
        NATION.put("30", "土族");
        NATION.put("31", "达翰尔族");
        NATION.put("32", "仫佬族");
        NATION.put("33", "羌族");
        NATION.put("34", "布朗族");
        NATION.put("35", "撒拉族");
        NATION.put("36", "毛南族");
        NATION.put("37", "仡佬族");
        NATION.put("38", "锡伯族");
        NATION.put("39", "阿昌族");
        NATION.put("40", "普米族");
        NATION.put("41", "塔吉克族");
        NATION.put("42", "怒族");
        NATION.put("43", "乌孜别克族");
        NATION.put("44", "俄罗斯族");
        NATION.put("45", "鄂温克族");
        NATION.put("46", "德昂族");
        NATION.put("47", "保安族");
        NATION.put("48", "裕固族");
        NATION.put("49", "京族");
        NATION.put("50", "塔塔尔族");
        NATION.put("51", "独龙族");
        NATION.put("52", "鄂伦春族");
        NATION.put("53", "赫哲族");
        NATION.put("54", "门巴族");
        NATION.put("55", "珞巴族");
        NATION.put("56", "基诺族");
    }

    public static Map<String, String> EDUCATION = new HashMap<String, String>();

    static {
        EDUCATION.put("10", "研究生及以上");
        EDUCATION.put("11", "博士研究生");
        EDUCATION.put("14", "硕士研究生");
        EDUCATION.put("20", "大学本科");
        EDUCATION.put("30", "大学专科");
        EDUCATION.put("40", "中专技校");
        EDUCATION.put("41", "中等专科");
        EDUCATION.put("44", "职业高中");
        EDUCATION.put("47", "技工学校");
        EDUCATION.put("61", "高中");
        EDUCATION.put("70", "初中及以下");
        EDUCATION.put("71", "初级中学");
        EDUCATION.put("81", "小学");
        EDUCATION.put("90", "其他");
    }

    public static Map<String, String> MARITALSTATUS = new HashMap<String, String>();

    static {
        MARITALSTATUS.put("1", "已婚");
        MARITALSTATUS.put("2", "未婚");
        MARITALSTATUS.put("3", "离异");
        MARITALSTATUS.put("4", "丧偶");
    }

    public static Map<String, String> POLITICALOUTLOOK = new HashMap<String, String>();

    static {
        POLITICALOUTLOOK.put("01", "中共党员");
        POLITICALOUTLOOK.put("02", "中共预备党员");
        POLITICALOUTLOOK.put("03", "共青团员");
        POLITICALOUTLOOK.put("04", "民革会员");
        POLITICALOUTLOOK.put("05", "民盟盟员");
        POLITICALOUTLOOK.put("06", "民建会员");
        POLITICALOUTLOOK.put("07", "民进会员");
        POLITICALOUTLOOK.put("08", "农工党党员");
        POLITICALOUTLOOK.put("09", "致公党党员");
        POLITICALOUTLOOK.put("10", "九三学社社员");
        POLITICALOUTLOOK.put("11", "台盟盟员");
        POLITICALOUTLOOK.put("12", "无党派民主人士");
        POLITICALOUTLOOK.put("13", "群众");
    }


    public static Map<String, String> PHYSICAL = new HashMap<String, String>();

    static {
        PHYSICAL.put("1", "健康");
        PHYSICAL.put("5", "残疾");
    }

    public static Map<String, String> RESIDENCE = new HashMap<String, String>();

    static {
        RESIDENCE.put("10", "非农业户口（城镇）");
        RESIDENCE.put("11", "本地非农业户口（本地城镇）");
        RESIDENCE.put("12", "外地非农业户口（外地城镇）");
        RESIDENCE.put("20", "农业户口（农村）");
        RESIDENCE.put("21", "本地农业户口（本地农村）");
        RESIDENCE.put("22", "外地农业户口（外地农村）");
        RESIDENCE.put("30", "台港澳人员");
        RESIDENCE.put("40", "外籍人士");
        RESIDENCE.put("50", "居民户");
    }

    public static Map<String, String> Voca_qual_level = new HashMap<String, String>();

    static {
        Voca_qual_level.put("1", "职业资格一级(高级技师)");
        Voca_qual_level.put("2", "职业资格二级(技师）");
        Voca_qual_level.put("3", "职业资格三级(高级)");
        Voca_qual_level.put("4", "职业资格四级(中级)");
        Voca_qual_level.put("5", "职业资格五级(初级)");
    }

    public static Map<String, String> InsuranceTypes = new HashMap<String, String>();

    static {
        InsuranceTypes.put("1", "养老");
        InsuranceTypes.put("2", "医疗");
        InsuranceTypes.put("3", "失业");
        InsuranceTypes.put("4", "工伤");
        InsuranceTypes.put("5", "生育");
    }

    public static Map<String, String> Current_State = new HashMap<String, String>();

    static {
        Current_State.put("1", "无效");
        Current_State.put("2", "有效");
        Current_State.put("3", "冻结");
        Current_State.put("4", "注销");
    }

    public static Map<String, String> Employment_Kind = new HashMap<String, String>();

    static {
        Employment_Kind.put("10", "原固定职工");
        Employment_Kind.put("20", "城镇合同制");
        Employment_Kind.put("30", "农民合同制");
        Employment_Kind.put("40", "临时工");
        Employment_Kind.put("50", "聘任制");
        Employment_Kind.put("60", "全日制");
        Employment_Kind.put("70", "非全日制");
        Employment_Kind.put("90", "其他");
    }

    public static Map<String, String> Work_Kind = new HashMap<String, String>();

    static {
        Work_Kind.put("010", "单位就业");
        Work_Kind.put("020", "自主就业");
        Work_Kind.put("021", "个体经营");
        Work_Kind.put("022", "灵活就业");
        Work_Kind.put("023", "其他自主就业");
        Work_Kind.put("030", "公益性岗位安置");
        Work_Kind.put("990", "其它就业形式");
    }

    public static Map<String, String> Material_List = new HashMap<String, String>();

    static {
        Material_List.put("1", "劳动合同");
        Material_List.put("2", "解除劳动关系证明");
        Material_List.put("3", "毕业证书（明）");
        Material_List.put("4", "参保缴费证明");
        Material_List.put("5", "工商营业执照（组织起来就业证书）");
        Material_List.put("6", "失地（林）证明灵活就业证明");
        Material_List.put("7", "其他证明");
    }

    public static Map<String, String> Employ_Diff_Level = new HashMap<String, String>();

    static {
        Employ_Diff_Level.put("010", "国企下岗失业人员");
        Employ_Diff_Level.put("020", "国企关闭破产安置人员");
        Employ_Diff_Level.put("030", "集体企业下岗职工");
        Employ_Diff_Level.put("040", "享受低保的长期失业人员");
        Employ_Diff_Level.put("050", "大龄就业困难人员");
        Employ_Diff_Level.put("060", "残疾人");
        Employ_Diff_Level.put("070", "零就业家庭成员");
        Employ_Diff_Level.put("080", "失地农民");
        Employ_Diff_Level.put("090", "农村零转移就业贫困家庭成员");
        Employ_Diff_Level.put("100", "毕业一年以上未就业的高校毕业生");
        Employ_Diff_Level.put("110", "各级社会福利机构供养的成年孤儿和社会成年孤儿");
        Employ_Diff_Level.put("990", "其他");
    }

    public static Map<String, String> EMPLOYMENT_LOGOUT_REASON = new HashMap<String, String>();

    static {
        EMPLOYMENT_LOGOUT_REASON.put("01", "参军");
        EMPLOYMENT_LOGOUT_REASON.put("02", "升学");
        EMPLOYMENT_LOGOUT_REASON.put("03", "移居外省市");
        EMPLOYMENT_LOGOUT_REASON.put("04", "移居境外");
        EMPLOYMENT_LOGOUT_REASON.put("05", "退休");
        EMPLOYMENT_LOGOUT_REASON.put("06", "两劳");
        EMPLOYMENT_LOGOUT_REASON.put("07", "失踪");
        EMPLOYMENT_LOGOUT_REASON.put("08", "死亡");
        EMPLOYMENT_LOGOUT_REASON.put("99", "其他");
    }

    public static Map<String, String> UNEMPLOYMENT_LOGOUT_REASON = new HashMap<String, String>();

    static {
        UNEMPLOYMENT_LOGOUT_REASON.put("10", "享受基本养老保险待遇的和男满60女满55周岁");
        UNEMPLOYMENT_LOGOUT_REASON.put("20", "完全丧失劳动能力");
        UNEMPLOYMENT_LOGOUT_REASON.put("30", "入学、服兵役、移居境外");
        UNEMPLOYMENT_LOGOUT_REASON.put("40", "被判刑收监执行或被劳动教养");
        UNEMPLOYMENT_LOGOUT_REASON.put("50", "连续3个月未与失业登记机构联系");
        UNEMPLOYMENT_LOGOUT_REASON.put("60", "已从事有稳定收入工作且收入不低于当地城市居民最低生活保障标准");
        UNEMPLOYMENT_LOGOUT_REASON.put("70", "死亡");
        UNEMPLOYMENT_LOGOUT_REASON.put("99", "其他");
    }

    public static Map<String, String> UNEMPLOYMENT_REASON = new HashMap<String, String>();

    static {
        UNEMPLOYMENT_REASON.put("10", "年满16周岁，从各类学校毕业、肄业，未能继续升学的");
        UNEMPLOYMENT_REASON.put("20", "与企业解除或终止劳动关系的");
        UNEMPLOYMENT_REASON.put("30", "从机关事业单位辞职或被辞退解聘");
        UNEMPLOYMENT_REASON.put("40", "由农业户口转为非农业户口，并失去承包土地的（含转产渔民和牧民）");
        UNEMPLOYMENT_REASON.put("50", "军人退出现役、且未纳入国家统一安置的");
        UNEMPLOYMENT_REASON.put("60", "刑满释放或假释、监外执行的");
        UNEMPLOYMENT_REASON.put("70", "劳动教养期满或提前解除劳动教养的");
        UNEMPLOYMENT_REASON.put("80", "个体工商户业主或私营企业业主停止经营的");
        UNEMPLOYMENT_REASON.put("90", "其他");
    }

    public static Map<String, String> ACCEPT_ASSISTANCE_WAY = new HashMap<String, String>();

    static {
        ACCEPT_ASSISTANCE_WAY.put("1", "推荐就业");
        ACCEPT_ASSISTANCE_WAY.put("2", "职业培训");
        ACCEPT_ASSISTANCE_WAY.put("3", "公益性岗位安置");
        ACCEPT_ASSISTANCE_WAY.put("4", "创业服务");
        ACCEPT_ASSISTANCE_WAY.put("5", "灵活就业");
        ACCEPT_ASSISTANCE_WAY.put("6", "社会保险补贴");
        ACCEPT_ASSISTANCE_WAY.put("7", "其他");
    }

    public static Map<String, String> SUBMIT_RECORD_CONTENTS = new HashMap<String, String>();

    static {
        SUBMIT_RECORD_CONTENTS.put("1", "国有（集体）企业下岗失业证明");
        SUBMIT_RECORD_CONTENTS.put("2", "低保证");
        SUBMIT_RECORD_CONTENTS.put("3", "零就业家庭证明");
        SUBMIT_RECORD_CONTENTS.put("4", "其他");
    }

    public static Map<String, String> LOGOUT_REASON = new HashMap<String, String>();

    static {
        LOGOUT_REASON.put("010", "单位就业");
        LOGOUT_REASON.put("020", "个体就业");
        LOGOUT_REASON.put("030", "灵活就业");
        LOGOUT_REASON.put("040", "退休");
        LOGOUT_REASON.put("050", "丧失劳动能力");
        LOGOUT_REASON.put("060", "退出市场");
        LOGOUT_REASON.put("070", "判刑劳教");
        LOGOUT_REASON.put("080", "不接受服务");
        LOGOUT_REASON.put("090", "未主动联系");
        LOGOUT_REASON.put("990", "其他");
    }

    public static Map<String, String> ENJOY_POLICY_TYPE = new HashMap<String, String>();

    static {
        ENJOY_POLICY_TYPE.put("010", "公益性岗位补贴");
        ENJOY_POLICY_TYPE.put("020", "社会保险补贴");
        ENJOY_POLICY_TYPE.put("030", "职业介绍补贴");
        ENJOY_POLICY_TYPE.put("040", "职业培训补贴");
        ENJOY_POLICY_TYPE.put("050", "职业技能鉴定补贴");
        ENJOY_POLICY_TYPE.put("060", "税收优惠政策");
        ENJOY_POLICY_TYPE.put("070", "行政事业性收费减免政策");
        ENJOY_POLICY_TYPE.put("080", "小额担保贷款贴息政策");
        ENJOY_POLICY_TYPE.put("990", "其他就业扶持政策");
    }

    public static Map<String, String> SERVICE_DETAILS = new HashMap<String, String>();

    static {
        SERVICE_DETAILS.put("010", "职业介绍");
        SERVICE_DETAILS.put("020", "职业指导");
        SERVICE_DETAILS.put("030", "创业服务");
        SERVICE_DETAILS.put("040", "职业培训");
        SERVICE_DETAILS.put("050", "其他服务");
    }

    public static Map<String, String> UNEMPLOYMENT_INSURANCE_TREATMENT_STATUS = new HashMap<String, String>();

    static {
        UNEMPLOYMENT_INSURANCE_TREATMENT_STATUS.put("10", "不符合享受条件");
        UNEMPLOYMENT_INSURANCE_TREATMENT_STATUS.put("20", "正在享受");
        UNEMPLOYMENT_INSURANCE_TREATMENT_STATUS.put("30", "本次享受期满");
        UNEMPLOYMENT_INSURANCE_TREATMENT_STATUS.put("40", "停止领取失业保险金");
        UNEMPLOYMENT_INSURANCE_TREATMENT_STATUS.put("41", "重新就业");
        UNEMPLOYMENT_INSURANCE_TREATMENT_STATUS.put("42", "应征服兵役");
        UNEMPLOYMENT_INSURANCE_TREATMENT_STATUS.put("43", "移居境外");
        UNEMPLOYMENT_INSURANCE_TREATMENT_STATUS.put("44", "享受基本养老保险待遇");
        UNEMPLOYMENT_INSURANCE_TREATMENT_STATUS.put("45", "被判刑收监执行或者被劳动教养");
        UNEMPLOYMENT_INSURANCE_TREATMENT_STATUS.put("46", "无正当理由，拒不接受政府机构介绍的工作");
        UNEMPLOYMENT_INSURANCE_TREATMENT_STATUS.put("47", "有法律、行政法规定的其他情形");
        UNEMPLOYMENT_INSURANCE_TREATMENT_STATUS.put("90", "其他");

    }

    //省农保接口
    public static final String DXSERVEICURL = "http://10.128.8.17:8080/dxservice";
    // DataExchange服务登录用户名，默认为admin
    public static final String SNBusername = "admin";
    // DataExchange服务登录用户名，默认为1
    public static final String SNBpassword = "1";
    // 任务OID，定义的数据查询任务业务标识，可以到“任务”页面查看
    public static final String SNBJBXXtaskOId = "QUERY_JBXX_FROM_DB";//省农保-个人基本参保信息查询
    public static final String SNBBGXXtaskOId = "QUERY_BGXX_FROM_DB";// 省农保-变更信息查询
    public static final String SNBJFXXtaskOId = "QUERY_JFXX_FROM_DB";// 省农保-缴费信息查询
    public static final String SNBNZHtaskOId = "QUERY_NZH_FROM_DB";// 省农保-个人年账户信息查询
    public static final String SNBDYBFXXtaskOId = "QUERY_DYBFXX_FROM_DB";// 省农保-待遇支付查询
    //人社局单位
    public static final String ORGANIZATION_NAME = "孝感人社局";

    // 客户端应用名称，可以为空
    public static final String SNBappName = "城乡居保公共服务查询平台";


}
