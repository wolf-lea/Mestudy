package com.tecsun.sisp.iface.common.vo.employment.result;

import com.tecsun.sisp.iface.common.util.Constants;

/**
 * Created by pear on 2015/8/5.
 */
public class UnEmploymentInfoBean {

    private String acc020;    //失业登记流水号
    private String acc0m1;    //登记证编号
    private String aab004;    //单位名称
    private String ajc090;    //失业时间
    private String ajc093;    //失业原因
    private String aca111;    //原从事工种代码
    private String aca112;    //原从事工种名称
    private String aac013;    //原用工形式
    private String acc02d;    //拟接收公共就业人才服务方式
    private String acc02e;    //提供材料目录
    private String acc026;    //求职意愿
    private String acc027;    //培训意愿
    private String aae100;    //有效标记
    private String aaea36;    //注销时间
    private String acc025;    //注销原因
    private String aae013;    //备注
    private String aae011;    //经办人代码
    private String aae017;    //经办机构代码
    private String aae019;    //经办人
    private String aae020;    //经办机构名称
    private String aae022;    //经办机构行政区划
    private String aae036;    //经办日期

    public String getAcc020() {
        return acc020;
    }

    public void setAcc020(String acc020) {
        this.acc020 = acc020;
    }

    public String getAcc0m1() {
        return acc0m1;
    }

    public void setAcc0m1(String acc0m1) {
        this.acc0m1 = acc0m1;
    }

    public String getAab004() {
        return aab004;
    }

    public void setAab004(String aab004) {
        this.aab004 = aab004;
    }

    public String getAjc090() {
        return ajc090;
    }

    public void setAjc090(String ajc090) {
        this.ajc090 = ajc090;
    }

    public String getAjc093() {
        String s = Constants.UNEMPLOYMENT_REASON.get(ajc093);
        s = s == null ? "" : s;
        return s;
    }

    public void setAjc093(String ajc093) {
        this.ajc093 = ajc093;
    }

    public String getAca111() {
        return aca111;
    }

    public void setAca111(String aca111) {
        this.aca111 = aca111;
    }

    public String getAca112() {
        return aca112;
    }

    public void setAca112(String aca112) {
        this.aca112 = aca112;
    }

    public String getAac013() {
        String s = Constants.Employment_Kind.get(aac013);
        s = s == null ? "" : s;
        return s;
    }

    public void setAac013(String aac013) {
        this.aac013 = aac013;
    }

    public String getAcc02d() {
        return acc02d;
    }

    public void setAcc02d(String acc02d) {
        this.acc02d = acc02d;
    }

    public String getAcc02e() {
        String s = Constants.Material_List.get(acc02e);
        s = s == null ? "" : s;
        return s;
    }

    public void setAcc02e(String acc02e) {
        this.acc02e = acc02e;
    }

    public String getAcc026() {
        return acc026;
    }

    public void setAcc026(String acc026) {
        this.acc026 = acc026;
    }

    public String getAcc027() {
        return acc027;
    }

    public void setAcc027(String acc027) {
        this.acc027 = acc027;
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

    public String getAaea36() {
        return aaea36;
    }

    public void setAaea36(String aaea36) {
        this.aaea36 = aaea36;
    }

    public String getAcc025() {
        String s = Constants.UNEMPLOYMENT_LOGOUT_REASON.get(acc025);
        s = s == null ? "" : s;
        return s;
    }

    public void setAcc025(String acc025) {
        this.acc025 = acc025;
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
