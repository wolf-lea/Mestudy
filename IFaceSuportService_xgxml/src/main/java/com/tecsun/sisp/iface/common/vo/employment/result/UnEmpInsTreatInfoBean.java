package com.tecsun.sisp.iface.common.vo.employment.result;

import com.tecsun.sisp.iface.common.util.Constants;

/**
 * Created by pear on 2015/8/5.
 */
public class UnEmpInsTreatInfoBean {

    private String aac160;    //流水号
    private String acc0m1;    //登记证编号
    private String aae170;    //失业保险待遇状态
    private String aac161;    //缴费月数
    private String aac162;    //实际缴费月数
    private String aac163;    //视同缴费月数
    private String aac164;    //核定开始年月
    private String aac165;    //核定终止年月
    private String aac166;    //核定领取月数
    private String aac167;    //上次核定剩余月数
    private String aac168;    //本次核定月数
    private String aac169;    //失业金标准
    private String aac16a;    //医疗补助金标准
    private String ajc158;    //生育补助金
    private String ajc00e;    //丧葬抚恤补助金
    private String aae013;    //备注
    private String aae011;    //经办人代码
    private String aae017;    //经办机构代码
    private String aae019;    //经办人
    private String aae020;    //经办机构名称
    private String aae022;    //经办机构行政区划
    private String aae036;    //经办日期

    public String getAac160() {
        return aac160;
    }

    public void setAac160(String aac160) {
        this.aac160 = aac160;
    }

    public String getAcc0m1() {
        return acc0m1;
    }

    public void setAcc0m1(String acc0m1) {
        this.acc0m1 = acc0m1;
    }

    public String getAae170() {
        String s = Constants.UNEMPLOYMENT_INSURANCE_TREATMENT_STATUS.get(aae170);
        s = s == null ? "" : s;
        return s;
    }

    public void setAae170(String aae170) {
        this.aae170 = aae170;
    }

    public String getAac161() {
        return aac161;
    }

    public void setAac161(String aac161) {
        this.aac161 = aac161;
    }

    public String getAac162() {
        return aac162;
    }

    public void setAac162(String aac162) {
        this.aac162 = aac162;
    }

    public String getAac163() {
        return aac163;
    }

    public void setAac163(String aac163) {
        this.aac163 = aac163;
    }

    public String getAac164() {
        return aac164;
    }

    public void setAac164(String aac164) {
        this.aac164 = aac164;
    }

    public String getAac165() {
        return aac165;
    }

    public void setAac165(String aac165) {
        this.aac165 = aac165;
    }

    public String getAac166() {
        return aac166;
    }

    public void setAac166(String aac166) {
        this.aac166 = aac166;
    }

    public String getAac167() {
        return aac167;
    }

    public void setAac167(String aac167) {
        this.aac167 = aac167;
    }

    public String getAac168() {
        return aac168;
    }

    public void setAac168(String aac168) {
        this.aac168 = aac168;
    }

    public String getAac169() {
        return aac169;
    }

    public void setAac169(String aac169) {
        this.aac169 = aac169;
    }

    public String getAac16a() {
        return aac16a;
    }

    public void setAac16a(String aac16a) {
        this.aac16a = aac16a;
    }

    public String getAjc158() {
        return ajc158;
    }

    public void setAjc158(String ajc158) {
        this.ajc158 = ajc158;
    }

    public String getAjc00e() {
        return ajc00e;
    }

    public void setAjc00e(String ajc00e) {
        this.ajc00e = ajc00e;
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
