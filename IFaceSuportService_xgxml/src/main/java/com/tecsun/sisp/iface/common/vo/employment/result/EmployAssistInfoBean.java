package com.tecsun.sisp.iface.common.vo.employment.result;

import com.tecsun.sisp.iface.common.util.Constants;

/**
 * Created by pear on 2015/8/5.
 */
public class EmployAssistInfoBean {

    private String aac130;    //就业援助流水号
    private String acc0m1;    //登记证编号
    private String adc310;    //就业困难人员类别
    private String acc05s;    //提供材料目录
    private String acc05q;    //拟接收援助方式
    private String acc05t;    //拟采取援助措施
    private String acc05c;    //认定日期
    private String acc05f;    //认定机构
    private String aae100;    //有效标记
    private String acc05d;    //注销日期
    private String acc05e;    //注销原因
    private String aae013;    //备注
    private String aae011;    //经办人代码
    private String aae017;    //经办机构代码
    private String aae019;    //经办人
    private String aae020;    //经办机构名称
    private String aae022;    //经办机构行政区划
    private String aae036;    //经办日期

    public String getAac130() {
        return aac130;
    }

    public void setAac130(String aac130) {
        this.aac130 = aac130;
    }

    public String getAcc0m1() {
        return acc0m1;
    }

    public void setAcc0m1(String acc0m1) {
        this.acc0m1 = acc0m1;
    }

    public String getAdc310() {
        String s = Constants.Employ_Diff_Level.get(adc310);
        s = s == null ? "" : s;
        return s;
    }

    public void setAdc310(String adc310) {
        this.adc310 = adc310;
    }

    public String getAcc05s() {
        String s = Constants.SUBMIT_RECORD_CONTENTS.get(acc05s);
        s = s == null ? "" : s;
        return s;
    }

    public void setAcc05s(String acc05s) {
        this.acc05s = acc05s;
    }

    public String getAcc05q() {
        String s = Constants.ACCEPT_ASSISTANCE_WAY.get(acc05q);
        s = s == null ? "" : s;
        return s;
    }

    public void setAcc05q(String acc05q) {
        this.acc05q = acc05q;
    }

    public String getAcc05t() {
        return acc05t;
    }

    public void setAcc05t(String acc05t) {
        this.acc05t = acc05t;
    }

    public String getAcc05c() {
        return acc05c;
    }

    public void setAcc05c(String acc05c) {
        this.acc05c = acc05c;
    }

    public String getAcc05f() {
        return acc05f;
    }

    public void setAcc05f(String acc05f) {
        this.acc05f = acc05f;
    }

    public String getAae100() {
        if(aae100 == null){
            return "";
        }else if(aae100.equals("0")){
            return "无效";
        }else if(aae100.equals("1")){
            return "有效";
        }else{
            return "";
        }
    }

    public void setAae100(String aae100) {
        this.aae100 = aae100;
    }

    public String getAcc05d() {
        return acc05d;
    }

    public void setAcc05d(String acc05d) {
        this.acc05d = acc05d;
    }

    public String getAcc05e() {
        String s = Constants.LOGOUT_REASON.get(acc05e);
        s = s == null ? "" : s;
        return s;
    }

    public void setAcc05e(String acc05e) {
        this.acc05e = acc05e;
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
