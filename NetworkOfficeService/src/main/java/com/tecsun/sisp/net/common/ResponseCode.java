package com.tecsun.sisp.net.common;

/**
 * 
 * 响应状态码
 */
public enum  ResponseCode {
    ok,
    bad_request,
    unauthorized,
    forbidden,
    notfound,
    business,
    accessdenied,
    internal_srever_error,
    system_maintenance
    ;


    /**
     * 操作成功
     */
    public static final String OK = "200";
    /**
     * 语义错误、请求参数错误
     */
    public static final String BAD_REQUEST = "400";
    /**
     * 未登录
     */
    public static final String UNAUTHORIZED = "401";
    /**
     * 无权限操作
     */
    public static final String FORBIDDEN = "403";
    /**
     * 找不到网页或者接口
     */
    public static final String NOTFOUND = "404";
    /**
     * 业务失败
     */
    public static final String BUSINESS = "405";
    /**
     * 服务器拒绝访问
     */
    public static final String ACCESSDENIED = "406";
    /**
     * 系统维护
     */
    public static final String SYSTEM_MAINTENANCE = "407";
    /**
     * 服务端错误
     */
    public static final String INTERNAL_SREVER_ERROR = "500";

    //错误码
    private String code;

    static  {
        ok.code = OK;
        bad_request.code = BAD_REQUEST;
        unauthorized.code = UNAUTHORIZED;
        forbidden.code = FORBIDDEN;
        notfound.code = NOTFOUND;
        business.code = BUSINESS;
        accessdenied.code = ACCESSDENIED;
        internal_srever_error.code = INTERNAL_SREVER_ERROR;
        system_maintenance.code = SYSTEM_MAINTENANCE;
    }

    public String getCode(){
        return code;
    }

}
