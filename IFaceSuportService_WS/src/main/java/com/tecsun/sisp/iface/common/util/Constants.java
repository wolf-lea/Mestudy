package com.tecsun.sisp.iface.common.util;


import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by DG on 2015/9/27.
 */
public class Constants {

	public static String RESULT_MESSAGE_SUCCESS = "200";
    public static String RESULT_MESSAGE_EXCEPTION = "999";
    public static String RESULT_MESSAGE_ERROR = "0";

    public static final String TSB = "TSB";//德生宝
    public static final String APP = "App";//手机应用
    public static final String NETPORTAL = "NetPortal";//网办
    public static final String SELFSERVICE = "SelfService";//自助终端
    public static final String WECHAT = "WeChat";//微信
    
    //自助终端，网办等渠道签到用到的key前缀+uuid,value为当前客户端的ip
    public static String TOKENNAME = "sisp_iface:token:";
    
    public static String USERKEY = "userkey:";
    
    public static String USERAREAID = "userAreaid:";
    
    //自助终端超时时间 单位分钟
    public static Integer SELFSERVICETIMEOUT = 120;

	    
    public static final Set<String> NOT_FILTER_URL = new HashSet<String>();
    static{
        NOT_FILTER_URL.add("/");
        NOT_FILTER_URL.add("/iface/common/login");
    }

    public static final Map<String,String> INSTITUTION_ENCODING = new HashMap<String, String>();
    static{
        INSTITUTION_ENCODING.put("430000","湖南省");
        INSTITUTION_ENCODING.put("439900","湖南省省本级");
        INSTITUTION_ENCODING.put("430100","长沙市");
        INSTITUTION_ENCODING.put("430199","长沙市市本级");
        INSTITUTION_ENCODING.put("430102","芙蓉区");
        INSTITUTION_ENCODING.put("430103","天心区");
        INSTITUTION_ENCODING.put("430104","岳麓区");
        INSTITUTION_ENCODING.put("430105","开福区");
        INSTITUTION_ENCODING.put("430111","雨花区");
        INSTITUTION_ENCODING.put("430121","长沙县");
        INSTITUTION_ENCODING.put("430122","望城县");
        INSTITUTION_ENCODING.put("430124","宁乡县");
        INSTITUTION_ENCODING.put("430181","浏阳市");
        INSTITUTION_ENCODING.put("430200","株洲市");
        INSTITUTION_ENCODING.put("430299","株洲市市本级");
        INSTITUTION_ENCODING.put("430202","荷塘区");
        INSTITUTION_ENCODING.put("430203","芦淞区");
        INSTITUTION_ENCODING.put("430204","石峰区");
        INSTITUTION_ENCODING.put("430211","天元区");
        INSTITUTION_ENCODING.put("430221","株洲县");
        INSTITUTION_ENCODING.put("430223","攸县");
        INSTITUTION_ENCODING.put("430224","茶陵县");
        INSTITUTION_ENCODING.put("430900","益阳市");
        INSTITUTION_ENCODING.put("430999","益阳市市本级");
        INSTITUTION_ENCODING.put("430902","资阳区");
        INSTITUTION_ENCODING.put("430903","赫山区");
        INSTITUTION_ENCODING.put("430921","南县");
        INSTITUTION_ENCODING.put("430922","桃江县");
        INSTITUTION_ENCODING.put("430923","安化县");
        INSTITUTION_ENCODING.put("430940","大通湖区");
        INSTITUTION_ENCODING.put("430981","沅江市");
        INSTITUTION_ENCODING.put("431000","郴州市");
        INSTITUTION_ENCODING.put("431099","郴州市市本级");
        INSTITUTION_ENCODING.put("431002","北湖区");
        INSTITUTION_ENCODING.put("431003","苏仙区");
        INSTITUTION_ENCODING.put("431021","桂阳县");
        INSTITUTION_ENCODING.put("431022","宜章县");
        INSTITUTION_ENCODING.put("431023","永兴县");
        INSTITUTION_ENCODING.put("431024","嘉禾县");
        INSTITUTION_ENCODING.put("431025","临武县");
        INSTITUTION_ENCODING.put("431026","汝城县");
        INSTITUTION_ENCODING.put("431027","桂东县");
        INSTITUTION_ENCODING.put("431028","安仁县");
        INSTITUTION_ENCODING.put("431081","资兴市");
        INSTITUTION_ENCODING.put("430225","炎陵县");
        INSTITUTION_ENCODING.put("430281","醴陵市");
        INSTITUTION_ENCODING.put("430240","云龙区");
        INSTITUTION_ENCODING.put("430300","湘潭市");
        INSTITUTION_ENCODING.put("430399","湘潭市市本级");
        INSTITUTION_ENCODING.put("430302","雨湖区");
        INSTITUTION_ENCODING.put("430304","岳塘区");
        INSTITUTION_ENCODING.put("430321","湘潭县");
        INSTITUTION_ENCODING.put("430340","高新区");
        INSTITUTION_ENCODING.put("430381","湘乡市");
        INSTITUTION_ENCODING.put("430382","韶山市");
        INSTITUTION_ENCODING.put("430400","衡阳市");
        INSTITUTION_ENCODING.put("430499","衡阳市市本级");
        INSTITUTION_ENCODING.put("430405","珠晖区");
        INSTITUTION_ENCODING.put("430406","雁峰区");
        INSTITUTION_ENCODING.put("430407","石鼓区");
        INSTITUTION_ENCODING.put("430408","蒸湘区");
        INSTITUTION_ENCODING.put("430412","南岳区");
        INSTITUTION_ENCODING.put("430421","衡阳县");
        INSTITUTION_ENCODING.put("430422","衡南县");
        INSTITUTION_ENCODING.put("430423","衡山县");
        INSTITUTION_ENCODING.put("430424","衡东县");
        INSTITUTION_ENCODING.put("430426","祁东县");
        INSTITUTION_ENCODING.put("430481","耒阳市");
        INSTITUTION_ENCODING.put("430482","常宁市");
        INSTITUTION_ENCODING.put("430500","邵阳市");
        INSTITUTION_ENCODING.put("430599","邵阳市市本级");
        INSTITUTION_ENCODING.put("431100","永州市");
        INSTITUTION_ENCODING.put("431199","永州市市本级");
        INSTITUTION_ENCODING.put("431102","零陵区");
        INSTITUTION_ENCODING.put("431103","冷水滩区");
        INSTITUTION_ENCODING.put("431121","祁阳县");
        INSTITUTION_ENCODING.put("431122","东安县");
        INSTITUTION_ENCODING.put("431123","双牌县");
        INSTITUTION_ENCODING.put("431124","道县");
        INSTITUTION_ENCODING.put("431125","江永县");
        INSTITUTION_ENCODING.put("431126","宁远县");
        INSTITUTION_ENCODING.put("431127","蓝山县");
        INSTITUTION_ENCODING.put("431128","新田县");
        INSTITUTION_ENCODING.put("431129","江华瑶族自治县");
        INSTITUTION_ENCODING.put("431140","回龙圩区");
        INSTITUTION_ENCODING.put("431200","怀化市");
        INSTITUTION_ENCODING.put("431299","怀化市市本级");
        INSTITUTION_ENCODING.put("431202","鹤城区");
        INSTITUTION_ENCODING.put("431221","中方县");
        INSTITUTION_ENCODING.put("431222","沅陵县");
        INSTITUTION_ENCODING.put("431223","辰溪县");
        INSTITUTION_ENCODING.put("431224","溆浦县");
        INSTITUTION_ENCODING.put("431225","会同县");
        INSTITUTION_ENCODING.put("431226","麻阳苗族自治县");
        INSTITUTION_ENCODING.put("431227","新晃侗族自治县");
        INSTITUTION_ENCODING.put("431228","芷江侗族自治县");
        INSTITUTION_ENCODING.put("431229","靖州苗族侗族自治县");
        INSTITUTION_ENCODING.put("431230","通道侗族自治县");
        INSTITUTION_ENCODING.put("430502","双清区");
        INSTITUTION_ENCODING.put("430503","大祥区");
        INSTITUTION_ENCODING.put("430511","北塔区");
        INSTITUTION_ENCODING.put("430521","邵东县");
        INSTITUTION_ENCODING.put("430522","新邵县");
        INSTITUTION_ENCODING.put("430523","邵阳县");
        INSTITUTION_ENCODING.put("430524","隆回县");
        INSTITUTION_ENCODING.put("430525","洞口县");
        INSTITUTION_ENCODING.put("430527","绥宁县");
        INSTITUTION_ENCODING.put("430528","新宁县");
        INSTITUTION_ENCODING.put("430529","城步苗族自治县");
        INSTITUTION_ENCODING.put("430581","武冈市");
        INSTITUTION_ENCODING.put("430600","岳阳市");
        INSTITUTION_ENCODING.put("430699","岳阳市市本级");
        INSTITUTION_ENCODING.put("430602","岳阳楼区");
        INSTITUTION_ENCODING.put("430603","云溪区");
        INSTITUTION_ENCODING.put("430611","君山区");
        INSTITUTION_ENCODING.put("430621","岳阳县");
        INSTITUTION_ENCODING.put("430623","华容县");
        INSTITUTION_ENCODING.put("430624","湘阴县");
        INSTITUTION_ENCODING.put("430626","平江县");
        INSTITUTION_ENCODING.put("430640","开发区");
        INSTITUTION_ENCODING.put("430641","屈原区");
        INSTITUTION_ENCODING.put("430681","汨罗市");
        INSTITUTION_ENCODING.put("430682","临湘市");
        INSTITUTION_ENCODING.put("431300","娄底市");
        INSTITUTION_ENCODING.put("431399","娄底市市本级");
        INSTITUTION_ENCODING.put("431240","洪江区");
        INSTITUTION_ENCODING.put("431281","洪江市");
        INSTITUTION_ENCODING.put("430800","张家界市");
        INSTITUTION_ENCODING.put("430899","张家界市市本级");
        INSTITUTION_ENCODING.put("430802","永定区");
        INSTITUTION_ENCODING.put("430811","武陵源区");
        INSTITUTION_ENCODING.put("430821","慈利县");
        INSTITUTION_ENCODING.put("430822","桑植县");
        INSTITUTION_ENCODING.put("433100","湘西土家族苗族自治州");
        INSTITUTION_ENCODING.put("433199","湘西土家族苗族自治州州本级");
        INSTITUTION_ENCODING.put("433101","吉首市");
        INSTITUTION_ENCODING.put("433122","泸溪县");
        INSTITUTION_ENCODING.put("433123","凤凰县");
        INSTITUTION_ENCODING.put("433124","花垣县");
        INSTITUTION_ENCODING.put("433125","保靖县");
        INSTITUTION_ENCODING.put("433126","古丈县");
        INSTITUTION_ENCODING.put("433127","永顺县");
        INSTITUTION_ENCODING.put("433130","龙山县");
        INSTITUTION_ENCODING.put("430700","常德市");
        INSTITUTION_ENCODING.put("430799","常德市市本级");
        INSTITUTION_ENCODING.put("430702","武陵区");
        INSTITUTION_ENCODING.put("430703","鼎城区");
        INSTITUTION_ENCODING.put("430721","安乡县");
        INSTITUTION_ENCODING.put("430722","汉寿县");
        INSTITUTION_ENCODING.put("430723","澧县");
        INSTITUTION_ENCODING.put("430724","临澧县");
        INSTITUTION_ENCODING.put("430725","桃源县");
        INSTITUTION_ENCODING.put("431302","娄星区");
        INSTITUTION_ENCODING.put("431321","双峰县");
        INSTITUTION_ENCODING.put("431322","新化县");
        INSTITUTION_ENCODING.put("431381","冷水江市");
        INSTITUTION_ENCODING.put("431382","涟源市");
        INSTITUTION_ENCODING.put("430726","石门县");
        INSTITUTION_ENCODING.put("430740","洞庭湖区");
        INSTITUTION_ENCODING.put("430741","西湖区");
        INSTITUTION_ENCODING.put("430781","津市市");
    }

    public static final Map<String,String> IDENTIFY_DOC_TYPE = new HashMap<String, String>();
    static {
        IDENTIFY_DOC_TYPE.put("1","居民身份证（户口簿）");
        IDENTIFY_DOC_TYPE.put("3","中国人民武装警察警官证");
        IDENTIFY_DOC_TYPE.put("5","澳门特区护照/身份证明");
        IDENTIFY_DOC_TYPE.put("8","外国人护照");
        IDENTIFY_DOC_TYPE.put("2","中国人民解放军军官证");
        IDENTIFY_DOC_TYPE.put("4","香港特区护照/身份证明");
        IDENTIFY_DOC_TYPE.put("6","台湾居民来往大陆通行证");
    }

    public static final Map<String,String> APPLICATION_TYPE = new HashMap<String, String>();
    static {
        APPLICATION_TYPE.put("110","企业职工养老保险系统");
        APPLICATION_TYPE.put("150","新农保（含城居养老）系统");
        APPLICATION_TYPE.put("310","大医保系统");
        APPLICATION_TYPE.put("410","工伤保险系统");
        APPLICATION_TYPE.put("601","卡管理系统");
        APPLICATION_TYPE.put("603","社保卡短信平台应用");
        APPLICATION_TYPE.put("605","12333电话应用系统");
        APPLICATION_TYPE.put("607","档案系统");
        APPLICATION_TYPE.put("609","手机APP");
        APPLICATION_TYPE.put("120","机关养老保险系统");
        APPLICATION_TYPE.put("210","失业就业系统");
        APPLICATION_TYPE.put("380","新型农村合作医疗保险系统");
        APPLICATION_TYPE.put("602","社保卡网站应用");
        APPLICATION_TYPE.put("604","社保卡自助服务平台");
        APPLICATION_TYPE.put("606","社保卡数据采集系统");
        APPLICATION_TYPE.put("608","个人网上查询系统");
        APPLICATION_TYPE.put("810","工商银行应用系统");
        APPLICATION_TYPE.put("811","长沙银行应用系统");
        APPLICATION_TYPE.put("812","建设银行应用系统");
        APPLICATION_TYPE.put("813","光大银行应用系统");
        APPLICATION_TYPE.put("819","农业银行应用系统");
        APPLICATION_TYPE.put("821","交通银行应用系统");
        APPLICATION_TYPE.put("823","民生银行应用系统");
        APPLICATION_TYPE.put("825","浦发银行应用系统");
        APPLICATION_TYPE.put("827","广发银行应用系统");
        APPLICATION_TYPE.put("998","人力资源与劳动力市场");
        APPLICATION_TYPE.put("815","中国银行应用系统");
        APPLICATION_TYPE.put("816","华融湘江银行应用系统");
        APPLICATION_TYPE.put("817","中国邮政储蓄银行应用系统");
        APPLICATION_TYPE.put("818","农村信用合作社应用系统");
        APPLICATION_TYPE.put("820","中信银行应用系统");
        APPLICATION_TYPE.put("822","招商银行应用系统");
        APPLICATION_TYPE.put("824","兴业银行应用系统");
        APPLICATION_TYPE.put("826","北京银行应用系统");
        APPLICATION_TYPE.put("999","其他应用系统");
    }

    public static final Map<String,String> SEX_TYPE = new HashMap<String, String>();
    static {
        SEX_TYPE.put("1","男性");
        SEX_TYPE.put("2","女性");
        SEX_TYPE.put("9","未说明的性别");
    }

    public static final Map<String,String> ALL_NATIONAL_CONSTANTS = new HashMap<String, String>();
    static {
        ALL_NATIONAL_CONSTANTS.put("1","汉族");
        ALL_NATIONAL_CONSTANTS.put("2","蒙古族");
        ALL_NATIONAL_CONSTANTS.put("3","回族");
        ALL_NATIONAL_CONSTANTS.put("4","藏族");
        ALL_NATIONAL_CONSTANTS.put("5","维吾尔族");
        ALL_NATIONAL_CONSTANTS.put("6","苗族");
        ALL_NATIONAL_CONSTANTS.put("7","彝族");
        ALL_NATIONAL_CONSTANTS.put("8","壮族");
        ALL_NATIONAL_CONSTANTS.put("9","布依族");
        ALL_NATIONAL_CONSTANTS.put("10","朝鲜族");
        ALL_NATIONAL_CONSTANTS.put("11","满族");
        ALL_NATIONAL_CONSTANTS.put("12","侗族");
        ALL_NATIONAL_CONSTANTS.put("13","瑶族");
        ALL_NATIONAL_CONSTANTS.put("14","白族");
        ALL_NATIONAL_CONSTANTS.put("15","土家族");
        ALL_NATIONAL_CONSTANTS.put("16","哈尼族");
        ALL_NATIONAL_CONSTANTS.put("17","哈萨克族");
        ALL_NATIONAL_CONSTANTS.put("18","傣族");
        ALL_NATIONAL_CONSTANTS.put("19","黎族");
        ALL_NATIONAL_CONSTANTS.put("20","傈傈族");
        ALL_NATIONAL_CONSTANTS.put("21","佤族");
        ALL_NATIONAL_CONSTANTS.put("22","畲族");
        ALL_NATIONAL_CONSTANTS.put("23","高山族");
        ALL_NATIONAL_CONSTANTS.put("24","拉祜族");
        ALL_NATIONAL_CONSTANTS.put("25","水族");
        ALL_NATIONAL_CONSTANTS.put("26","东乡族");
        ALL_NATIONAL_CONSTANTS.put("27","纳西族");
        ALL_NATIONAL_CONSTANTS.put("28","景颇族");
        ALL_NATIONAL_CONSTANTS.put("99","其他");
        ALL_NATIONAL_CONSTANTS.put("29","柯尔克孜族");
        ALL_NATIONAL_CONSTANTS.put("30","土族");
        ALL_NATIONAL_CONSTANTS.put("31","达翰尔族");
        ALL_NATIONAL_CONSTANTS.put("32","仫佬族");
        ALL_NATIONAL_CONSTANTS.put("33","羌族");
        ALL_NATIONAL_CONSTANTS.put("34","布朗族");
        ALL_NATIONAL_CONSTANTS.put("35","撒拉族");
        ALL_NATIONAL_CONSTANTS.put("36","毛南族");
        ALL_NATIONAL_CONSTANTS.put("37","仡佬族");
        ALL_NATIONAL_CONSTANTS.put("38","锡伯族");
        ALL_NATIONAL_CONSTANTS.put("39","阿昌族");
        ALL_NATIONAL_CONSTANTS.put("40","普米族");
        ALL_NATIONAL_CONSTANTS.put("41","塔吉克族");
        ALL_NATIONAL_CONSTANTS.put("42","怒族");
        ALL_NATIONAL_CONSTANTS.put("43","乌孜别克族");
        ALL_NATIONAL_CONSTANTS.put("44","俄罗斯族");
        ALL_NATIONAL_CONSTANTS.put("45","鄂温克族");
        ALL_NATIONAL_CONSTANTS.put("46","德昂族");
        ALL_NATIONAL_CONSTANTS.put("47","保安族");
        ALL_NATIONAL_CONSTANTS.put("48","裕固族");
        ALL_NATIONAL_CONSTANTS.put("49","京族");
        ALL_NATIONAL_CONSTANTS.put("50","塔塔尔族");
        ALL_NATIONAL_CONSTANTS.put("51","独龙族");
        ALL_NATIONAL_CONSTANTS.put("52","鄂伦春族");
        ALL_NATIONAL_CONSTANTS.put("53","赫哲族");
        ALL_NATIONAL_CONSTANTS.put("54","门巴族");
        ALL_NATIONAL_CONSTANTS.put("55","珞巴族");
        ALL_NATIONAL_CONSTANTS.put("56","基诺族");
    }

    public static final Map<String,String> BANK_ENCODING = new HashMap<String, String>();
    static {
        BANK_ENCODING.put("10","工商银行");
        BANK_ENCODING.put("11","长沙银行");
        BANK_ENCODING.put("12","建设银行");
        BANK_ENCODING.put("13","光大银行");
        BANK_ENCODING.put("19","农业银行");
        BANK_ENCODING.put("21","交通银行");
        BANK_ENCODING.put("23","民生银行");
        BANK_ENCODING.put("25","浦发银行");
        BANK_ENCODING.put("27","广发银行");
        BANK_ENCODING.put("15","中国银行");
        BANK_ENCODING.put("16","华融湘江银行");
        BANK_ENCODING.put("17","中国邮政储蓄银行");
        BANK_ENCODING.put("18","农村信用合作社");
        BANK_ENCODING.put("20","中信银行");
        BANK_ENCODING.put("22","招商银行");
        BANK_ENCODING.put("24","兴业银行");
        BANK_ENCODING.put("26","北京银行");
    }

    public static final Map<String,String> CARD_STATUS = new HashMap<String, String>();
    static {
        CARD_STATUS.put("0","封存");
        CARD_STATUS.put("2","挂失");
        CARD_STATUS.put("4","临时挂失/口头挂失");
        CARD_STATUS.put("1","正常");
        CARD_STATUS.put("3","应用锁定");
        CARD_STATUS.put("9","注销");
    }

    public static final Map<String,String> CARD_PROGRESS = new HashMap<String, String>();
    static {
        CARD_PROGRESS.put("00","数据已提交");
        CARD_PROGRESS.put("01","开户申请");
        CARD_PROGRESS.put("02","开户审核");
        CARD_PROGRESS.put("03","银行开户中");
        CARD_PROGRESS.put("04","开户完成");
        CARD_PROGRESS.put("05","开户失败");
        CARD_PROGRESS.put("06","制卡中");
        CARD_PROGRESS.put("07","制卡完成");
        CARD_PROGRESS.put("08","制卡失败");
        CARD_PROGRESS.put("09","卡配送中");
        CARD_PROGRESS.put("10","已领卡");
    }
    
    public static Map<String, String> BUSINESSCODE = new HashMap<String, String>();

    static {
        BUSINESSCODE.put("smsSygrjbxx", "SISP_IFACE_SO_001"); //生育保险个人参保信息
        BUSINESSCODE.put("smsSyjfxxcx", "SISP_IFACE_SO_002");//生育保险个人缴费信息查询
        BUSINESSCODE.put("smsSyylfyxxcx", "SISP_IFACE_SO_003");//生育保险医疗费用信息查询
        BUSINESSCODE.put("smsSyjtxxcx", "SISP_IFACE_SO_004");//生育保险生育津贴查询
        
        BUSINESSCODE.put("smsYlgrjbxx", "SISP_IFACE_SO_005");//医疗保险个人参保信息  
        BUSINESSCODE.put("smsYljfxxcx", "SISP_IFACE_SO_006");//医疗保险个人缴费信息查询
        BUSINESSCODE.put("smsYlgrzhxxcx", "SISP_IFACE_SO_007");//医疗保险个人账户信息查询
        BUSINESSCODE.put("smsYlgrjsxxcx", "SISP_IFACE_SO_008");//医疗保险费用结算信息查询
        BUSINESSCODE.put("smsMxbxxcx", "SISP_IFACE_SO_009");//慢性病审批信息查询
        BUSINESSCODE.put("smsYdanzxxcx", "SISP_IFACE_SO_010");//异地安置审批信息查询
        
        BUSINESSCODE.put("simisGetEndowInfoPerson", "SISP_IFACE_SO_011");//城乡养老保险个人信息
        BUSINESSCODE.put("simisGetEndowPayPerson", "SISP_IFACE_SO_012");//城乡养老保险个人缴费
        BUSINESSCODE.put("simisGetEndowAccountPerson", "SISP_IFACE_SO_013");//城乡养老保险个人账户信息
        BUSINESSCODE.put("simisGetEndowAnnuityPerson", "SISP_IFACE_SO_014");//城乡养老保险个人养老金发放
        
        BUSINESSCODE.put("cardInfo", "SISP_IFACE_CARD_001");//卡基本信息查询
        BUSINESSCODE.put("cardProgress", "SISP_IFACE_CARD_002");//卡进度查询
        BUSINESSCODE.put("cardStatus", "SISP_IFACE_CARD_003");//卡状态查询
        BUSINESSCODE.put("cardLossReport", "SISP_IFACE_CARD_004");//卡挂失查询
    }
    
    //字典组 key
    public static String DICTIONGROUPKEY = "sisp_public:cmp_core:dictionary:{groupId}:detaillist";
    
  //业务分析子系统请求地址
    public static final String TRANSANALYSIS_URI="/ans/translog/addTransLog";
    
    public static String CHANNELCODE = "channeltype";
    
    public static final String PARAM_NATION_GROUP = "PARAM_NATION";//名族

    public static final String BANK_GROUP = "bank";//银行
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

    //redis 德生宝软件版本待更新设备数
    public static final String KEY_VERSION_UPDATE_TOTAL = "siboss:tsbm:version_update_total";
}
