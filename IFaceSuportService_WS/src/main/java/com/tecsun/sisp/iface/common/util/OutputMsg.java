package com.tecsun.sisp.iface.common.util;

/**
 * Created by DG on 2015/11/6.
 */
public class OutputMsg {

    public static final String QUERY_SUCCESS = "查询成功";   //查询成功

    public static final String OPERATE_SUCCESS = "操作成功";   //操作成功

    public static final String LOGIN_SUCCESS = "登录成功";   //登录成功
    
    public static final String SIGN_SUCCESS = "签到成功";    //签到成功

    public static final String LOGIN_PASSWORD_WRONG = "登录失败，密码错误";   //密码错误

    public static final String SERVER_FAILURE = "不好意思，无法连接到后台服务器";   //失败，由于本地后台导致的失败，如数据库，网络等

    public static final String TOKEN_WRONG = "验证操作权限失败，请重新登陆或签到";        //身份验证的错误

    public static final String PARAMETER_FAILURE = "请核对参数是否传输正确";    //参数传值错误，如不能为空的字段，或者时间格式

    public static final String NO_THIS_CITY = "查无此城市";

    public static final String QUERY_NOT_DATA = "没有查询结果";       //查询无结果

    public static final String USER_NOT_EXIST = "用户不存在";      //用户、地市或者设备不存在

    public static final String APPLY_NOT_EXIST = "没有申请记录";    //没有申请记录
    
    public static final String USER_IS_EXIST = "用户已存在";      //新增用户或者设备时，已存在

    public static final String IS_APPLY = "您已报名";

    public static final String IS_COLLECT = "您已收藏";

    public static final String SHANP_DISCONNECT = "不好意思，招聘服务暂时关闭(6)";      //连接第三方合作商接口失败

    public static final String SHANP_DATA_PARSE_FAILURE = "不好意思，招聘服务暂时关闭(7)";  //解析第三方接口的json数据出错

    public static final String REGISTER_SUCCESS = "登记成功";   //登记成功

    public static final String SERVER_EXCEPTION = "不好意思，程序出现异常";

    public static final String NO_VERSION = "不好意思，找不到最新版本文件";

}
