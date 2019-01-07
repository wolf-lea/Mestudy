package com.tecsun.sisp.iface.common.vo.employment.result;

import com.tecsun.sisp.iface.common.util.Constants;

/**
 * Created by pear on 2015/8/5.
 */
public class PublicServiceInfoBean {

    private String aac150;    //公共就业人才服务流水号
    private String acc0m1;    //登记证编号
    private String aac151;    //接受服务时间
    private String aac152;    //接受服务内容
    private String aae013;    //备注
    private String aae011;    //经办人代码
    private String aae017;    //经办机构代码
    private String aae019;    //经办人
    private String aae020;    //经办机构名称
    private String aae022;    //经办机构行政区划
    private String aae036;    //经办日期

    public String getAac150() {
        return aac150;
    }

    public void setAac150(String aac150) {
        this.aac150 = aac150;
    }

    public String getAcc0m1() {
        return acc0m1;
    }

    public void setAcc0m1(String acc0m1) {
        this.acc0m1 = acc0m1;
    }

    public String getAac151() {
        return aac151;
    }

    public void setAac151(String aac151) {
        this.aac151 = aac151;
    }

    public String getAac152() {
        String s = Constants.SERVICE_DETAILS.get(aac152);
        s = s == null ? "" : s;
        return s;
    }

    public void setAac152(String aac152) {
        this.aac152 = aac152;
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
