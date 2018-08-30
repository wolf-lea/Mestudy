package com.tecsun.sisp.adapter.modules.cyt.entity.response;

import java.math.BigDecimal;

/**
 * Created by xumaohao on 2017/7/24.
 */
public class ClaimBean {
    private String xm;      //姓名
    private String sfzh;    //身份证号码
    private String jslsh;   //报销业务（结算）流水号
    private String bxje;    //医保门诊统筹报销金额
    private String tcye;    //门诊统筹余额
    private String zfje;    //自费金额
    private String zje;     //总金额
    private String jssj;    //结算时间

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getJslsh() {
        return jslsh;
    }

    public void setJslsh(String jslsh) {
        this.jslsh = jslsh;
    }

    public String getBxje() {
        return change(2,bxje);
    }

    public void setBxje(String bxje) {
        this.bxje = bxje;
    }

    public String getTcye() {
        return change(2,tcye);
    }

    public void setTcye(String tcye) {
        this.tcye = tcye;
    }

    public String getZfje() {
        return change(2,zfje);
    }

    public void setZfje(String zfje) {
        this.zfje = zfje;
    }

    public String getZje() {
        return change(2,zje);
    }

    public void setZje(String zje) {
        this.zje = zje;
    }

    public String getJssj() {
        return jssj;
    }

    public void setJssj(String jssj) {
        this.jssj = jssj;
    }

    private String change(int num ,String value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(num, BigDecimal.ROUND_HALF_UP);
        return String.valueOf(bd);
    }
}
