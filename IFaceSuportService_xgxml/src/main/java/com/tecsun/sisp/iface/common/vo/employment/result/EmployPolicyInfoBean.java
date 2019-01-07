package com.tecsun.sisp.iface.common.vo.employment.result;

import com.tecsun.sisp.iface.common.util.Constants;

/**
 * Created by pear on 2015/8/5.
 */
public class EmployPolicyInfoBean {

    private String aac140;    //享受就业扶持政策流水号
    private String acc0m1;    //登记证编号
    private String adc450;    //享受政策类型
    private String aae034;    //核准日期
    private String aae030;    //政策享受开始日期
    private String aae031;    //政策享受终止日期
    private String acc0s4;    //享受金额
    private String aae013;    //备注
    private String aae011;    //经办人代码
    private String aae017;    //经办机构代码
    private String aae019;    //经办人
    private String aae020;    //经办机构名称
    private String aae022;    //经办机构行政区划
    private String aae036;    //经办日期

    public String getAac140() {
        return aac140;
    }

    public void setAac140(String aac140) {
        this.aac140 = aac140;
    }

    public String getAcc0m1() {
        return acc0m1;
    }

    public void setAcc0m1(String acc0m1) {
        this.acc0m1 = acc0m1;
    }

    public String getAdc450() {
        String s = Constants.ENJOY_POLICY_TYPE.get(adc450);
        s = s == null ? "" : s;
        return s;
    }

    public void setAdc450(String adc450) {
        this.adc450 = adc450;
    }

    public String getAae034() {
        return aae034;
    }

    public void setAae034(String aae034) {
        this.aae034 = aae034;
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

    public String getAcc0s4() {
        return acc0s4;
    }

    public void setAcc0s4(String acc0s4) {
        this.acc0s4 = acc0s4;
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
