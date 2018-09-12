package com.tecsun.sisp.iface.common.util;

/**
 * Created by DG on 2015/11/6.
 */
public class OutputStatusCode {

    public static final String SUCCESS = "200";   //成功

    public static final String SERVER_FAILURE = "500";   //失败，由于本地后台导致的失败，如数据库，网络等

    public static final String TOKEN_WRONG = "1";        //身份验证的错误

    public static final String PARAMETER_FAILURE = "2";    //参数传值错误，如不能为空的字段，或者时间格式

    public static final String QUERY_NOT_DATA = "3";       //查询无结果

    public static final String USER_NOT_EXIST = "4";      //用户、地市或者设备不存在
    
    public static final String USER_IS_EXIST = "5";      //新增用户或者设备时，已存在

    public static final String SHANP_DISCONNECT = "6";      //连接第三方合作商接口失败

    public static final String SHANP_DATA_PARSE_FAILURE = "7";  //解析第三方接口的数据出错
    
    public static final String APPLY_NOT_EXIST = "8";  //无申请记录

}


