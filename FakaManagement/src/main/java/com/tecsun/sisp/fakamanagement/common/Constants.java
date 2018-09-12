package com.tecsun.sisp.fakamanagement.common;


import java.util.HashMap;
import java.util.Map;

/**
 * Created by thl on 2015/9/27.
 */
public class Constants {

	public static String RESULT_MESSAGE_SUCCESS = "200";
    public static String RESULT_MESSAGE_EXCEPTION = "999";
    public static String RESULT_MESSAGE_ERROR = "0";
    public static String RESULT_MESSAGE_PARAM = "-300";//参数错误

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
    
    //系统公告-- 类型
    public static String SYSTEM_NOTICE_TYPE = "system_notice_type";

    //用户类型
    public static final long TYPE_OF_ADMIN = 1;//1管理员
    public static final long TYPE_OF_PERSON = 2;//2个人
    public static final long TYPE_OF_UNIT = 3;//1单位
    
    //角色名称
    public static final String ROLE_PERSON = "个人角色";
    public static final String ROLE_UNIT = "单位角色";

    //是否修改过密码
    public static final long IS_NOT_UPDATE_PWD =1;//未修改过密码
    public static final long IS_UPDATE_PWD = 2;//修改过密码
    
    //用户初始密码
    public static final String USER_INIT_PASSWORD = "123456";
    
    //短信业务类型
    public static final long MESSAGE_BUS_TYPE_PASSWORD = 1; //修改密码发送短信验证码
    public static final long MESSAGE_BUS_TYPE_PAY = 2; //缴费发送短信验证码

    //redis键值
    //参数字典
    public static final String RK_PF_OF_PARAMETER = "siweb:cmp_core:parameter:{%s}";

    //批次主表状态
    public static final String BATCH_STATUS_00="00";//未下载
    public static final String BATCH_STATUS_01="01";//下载中
    public static final String BATCH_STATUS_02="02";//已下载
    public static final String BATCH_STATUS_03="03";//下载失败

    //批次箱盒表状态
    public static final String BATCH_BIN_BOX_STATUS_00="00";//未入库
    public static final String BATCH_BIN_BOX_STATUS_01="01";//已入库

    //操作批次日志表类型
    public static final String BATCH_LOG_STATUS_00="00";//批次新建
    public static final String BATCH_LOG_STATUS_01="01";//批次下载

    //临时卡盒表状态
    public static final String TEMPCARD_BOX_01="01";//已接收
    public static final String TEMPCARD_BOX_02="02";//已下发

    public static Map<String,String> TEMPCARD_STATUS=new HashMap<>();
    static{
        TEMPCARD_STATUS.put("00","初始（入库）");
        TEMPCARD_STATUS.put("01","已发放");
        TEMPCARD_STATUS.put("02","已注销");
        TEMPCARD_STATUS.put("03","已作废");
        TEMPCARD_STATUS.put("04","已回收");
        TEMPCARD_STATUS.put("05","注销");
    }

    //字典组 key 临时卡盒状态
    public static String TEMPCARD_BOX_STATUS_KEY = "TEMPCARD_BOX_STATUS_KEY";

    //临时卡盒状态
    public static final String TEMPCARD_BOX_STATUS_00="00";//未下发
    public static final String TEMPCARD_BOX_STATUS_01="01";//已接收
    public static final String TEMPCARD_BOX_STATUS_02="02";//已下发

    public static Map<String,String> TEMPCARD_BOX_STATUS=new HashMap<>();
    static{
        TEMPCARD_BOX_STATUS.put("00","未下发");
        TEMPCARD_BOX_STATUS.put("01","已接收");
        TEMPCARD_BOX_STATUS.put("02","已下发");
    }

    //字典组 key 临时卡明细状态
    public static String TEMPCARD_DETAIL_STATUS_KEY = "TEMPCARD_DETAIL_STATUS_KEY";

    //临时卡明细状态
    public static final String TEMPCARD_DETAIL_STATUS_00="00";//00-	初始（入库）
    public static final String TEMPCARD_DETAIL_STATUS_01="01";// 01-	已发放
    public static final String TEMPCARD_DETAIL_STATUS_02="02";//02-	已注销（遗失）
    public static final String TEMPCARD_DETAIL_STATUS_03="03";//03-	已作废（损坏）
    public static final String TEMPCARD_DETAIL_STATUS_04="04";//04-	已回收
    public static final String TEMPCARD_DETAIL_STATUS_05="05";//05-	注销（不在使用）

    public static Map<String,String> TEMPCARD_DETAIL_STATUS=new HashMap<>();
    static{
        TEMPCARD_DETAIL_STATUS.put("00","初始（入库）");
        TEMPCARD_DETAIL_STATUS.put("01","已发放");
        TEMPCARD_DETAIL_STATUS.put("02","已注销（遗失");
        TEMPCARD_DETAIL_STATUS.put("03","已作废（损坏");
        TEMPCARD_DETAIL_STATUS.put("04","已回收");
        TEMPCARD_DETAIL_STATUS.put("05","注销（不在使用");
    }


    //字典组 key 银行
    public static String TEMPCARD_BANK_KEY = "BANK";
}
