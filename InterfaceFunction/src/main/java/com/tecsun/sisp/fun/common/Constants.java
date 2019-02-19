package com.tecsun.sisp.fun.common;

/**
 * Created by zhangqingjie on 15-5-11.
 */
public class Constants {

    public static String RESULT_MESSAGE_SUCCESS="200";
    public static String RESULT_MESSAGE_ERROR="300";

    /**字典表－渠道字典组**/
    public static String CHANNELCODE="channeltype";
    /**字典表－业务类型字典组**/
    public static String BUSCODE="bus_code";
    /**字典表－渠道字典组**/
    public static String TRANSRESULT="transresult";

    //字典组 key
    public static String DICTIONGROUPKEY="sisp_public:cmp_core:dictionary:{groupId}:detaillist";

    //获取其他子系统数据所需要的uri
    //1.字典表查询，根据输入的groupid查询({groupid})
    public static String CHANNLCODES_URI="/core/dictionary/queryRedisDictionByGroupId";
    //2.根据设备编码获取对应负责人信息
    public static String manager_uri="/dev/deviceRegist/getManagerByEqNo";
    //3.获取所有设备负责人信息
    public static String  managerinfo_uri="/dev/manager/queryAllMana";


}
