package com.tecsun.sisp.iface.common.vo.employment.result;

/**
 * Created by pear on 2015/8/5.
 */
public class InspectionInfoBean {

    private String aac210;    //年度审验流水号
    private String acc0m1;    //登记证编号
    private String aae001;    //审验年度
    private String aac215;    //审验情况记载
    private String aac216;    //年审状态
    private String aae013;    //备注
    private String aae011;    //经办人代码
    private String aae017;    //经办机构代码
    private String aae019;    //经办人
    private String aae020;    //经办机构名称
    private String aae022;    //经办机构行政区划
    private String aae036;    //经办日期

    public String getAac210() {
        return aac210;
    }

    public void setAac210(String aac210) {
        this.aac210 = aac210;
    }

    public String getAcc0m1() {
        return acc0m1;
    }

    public void setAcc0m1(String acc0m1) {
        this.acc0m1 = acc0m1;
    }

    public String getAae001() {
        return aae001;
    }

    public void setAae001(String aae001) {
        this.aae001 = aae001;
    }

    public String getAac215() {
        return aac215;
    }

    public void setAac215(String aac215) {
        this.aac215 = aac215;
    }

    public String getAac216() {
        if(aac216 == null){
            return "";
        }else if(aac216.equals("0")){
            return "未年审";
        }else if(aac216.equals("1")){
            return "已年审";
        }else{
            return "";
        }
    }

    public void setAac216(String aac216) {
        this.aac216 = aac216;
    }

    public String getAae013() {
        return aae013;
    }

    public void setAae013(String aae013) {
        this.aae013 = aae013;
    }

    public String getAae011() {
        return aae011;
    }

    public void setAae011(String aae011) {
        this.aae011 = aae011;
    }

    public String getAae017() {
        return aae017;
    }

    public void setAae017(String aae017) {
        this.aae017 = aae017;
    }

    public String getAae019() {
        return aae019;
    }

    public void setAae019(String aae019) {
        this.aae019 = aae019;
    }

    public String getAae020() {
        return aae020;
    }

    public void setAae020(String aae020) {
        this.aae020 = aae020;
    }

    public String getAae022() {
        return aae022;
    }

    public void setAae022(String aae022) {
        this.aae022 = aae022;
    }

    public String getAae036() {
        return aae036;
    }

    public void setAae036(String aae036) {
        this.aae036 = aae036;
    }
}
