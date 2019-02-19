package com.tecsun.sisp.iface.common.vo.card;

import com.tecsun.sisp.iface.common.util.Constants;

/**
 * Created by jerry on 2015/5/31.
 */
public class TsyldyffxxVO {

    private final String TABLE_NAME = Constants.TS_V_BP_YLDYFFXX;

    private 	String	grbh	;//	个人编号
    private 	String	jsrq	;//	结算期
    private 	String	ltxlb	;//	离退休类别
    private 	double	dyksny	;//	补充养老帐户返还
    private 	double	ylj	;//	养老待遇支付合计

    public String getGrbh() {
        return grbh;
    }

    public void setGrbh(String grbh) {
        this.grbh = grbh;
    }

    public String getJsrq() {
        return jsrq;
    }

    public void setJsrq(String jsrq) {
        this.jsrq = jsrq;
    }

    public String getLtxlb() {
        return ltxlb;
    }

    public void setLtxlb(String ltxlb) {
        this.ltxlb = ltxlb;
    }

    public double getDyksny() {
        return dyksny;
    }

    public void setDyksny(double dyksny) {
        this.dyksny = dyksny;
    }

    public double getYlj() {
        return ylj;
    }

    public void setYlj(double ylj) {
        this.ylj = ylj;
    }
}
