package com.tecsun.sisp.iface.common.vo.employment.result;

import com.tecsun.sisp.iface.common.util.Constants;

/**
 * Created by pear on 2015/8/5.
 */
public class EmploymentInfoBean {

    private String acc030;    //就业登记流水号
    private String acc0m1;    //登记证编号
    private String acc032;    //就业形式
    private String acc031;    //就业时间
    private String aab004;    //就业单位名称
    private String acc033;    //是否签订劳动合同
    private String aae030;    //合同起始日期
    private String aae031;    //合同终止日期
    private String aca111;    //录用工种代码
    private String aca112;    //录用工种名称
    private String aac013;    //用工形式
    private String acc034;    //是否参加社会保险
    private String acc036;    //提供材料目录
    private String aae100;    //有效标记
    private String aaea36;    //注销时间
    private String acc035;    //注销原因
    private String aae013;    //备注
    private String aae011;    //经办人代码
    private String aae017;    //经办机构代码
    private String aae019;    //经办人
    private String aae020;    //经办机构名称
    private String aae022;    //经办机构行政区划
    private String aae036;    //经办日期


    public String getAcc030() {
        return acc030;
    }

    public void setAcc030(String acc030) {
        this.acc030 = acc030;
    }

    public String getAcc0m1() {
        return acc0m1;
    }

    public void setAcc0m1(String acc0m1) {
        this.acc0m1 = acc0m1;
    }

    public String getAcc032() {
        String s = Constants.Work_Kind.get(acc032);
        s = s == null ? "" : s;
        return s;
    }

    public void setAcc032(String acc032) {
        this.acc032 = acc032;
    }

    public String getAcc031() {
        return acc031;
    }

    public void setAcc031(String acc031) {
        this.acc031 = acc031;
    }

    public String getAab004() {
        return aab004;
    }

    public void setAab004(String aab004) {
        this.aab004 = aab004;
    }

    public String getAcc033() {
        if(acc033 == null){
            return "";
        }else if(acc033.equals("0")){
            return "否";
        }else if(acc033.equals("1")){
            return "是";
        }else{
            return "";
        }
    }

    public void setAcc033(String acc033) {
        this.acc033 = acc033;
    }

    public String getAae030() {
        return aae030;
    }

    public void setAae030(String aae030) {
        this.aae030 = aae030;
    }

    public String getAae031() {
        return aae031;
    }

    public void setAae031(String aae031) {
        this.aae031 = aae031;
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

    public String getAcc034() {
        if(acc034 == null){
            return "";
        }else if(acc034.equals("0")){
            return "否";
        }else if(acc034.equals("1")){
            return "是";
        }else{
            return "";
        }
    }

    public void setAcc034(String acc034) {
        this.acc034 = acc034;
    }

    public String getAcc036() {
        String s = Constants.Material_List.get(acc036);
        s = s == null ? "" : s;
        return s;
    }

    public void setAcc036(String acc036) {
        this.acc036 = acc036;
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

    public String getAcc035() {
        String s = Constants.EMPLOYMENT_LOGOUT_REASON.get(acc035);
        s = s == null ? "" : s;
        return s;
    }

    public void setAcc035(String acc035) {
        this.acc035 = acc035;
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
