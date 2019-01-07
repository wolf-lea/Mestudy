package com.tecsun.sisp.iface.common.vo.card;

import com.tecsun.sisp.iface.common.util.Constants;

/**
 * Created by jerry on 2015/5/31.
 */
public class TsylbxgrjfmxVO {

    private final String TABLE_NAME = Constants.TS_V_BP_YLBXGRJFMX;
    private 	String	dwbh	;//	单位编号
    private 	String	dwmc	;//	单位名称
    private 	String	grbh	;//	个人编号
    private 	String	sbkh	;//	社会保障卡卡号
    private 	String	sfzh	;//	公民身份号码
    private 	String	jfny	;//	费款所属期
    private 	double	jfjs	;//	缴费基数
    private 	double	dwjfje	;//	单位缴费金额
    private 	double	grjfje	;//	个人缴费金额
    private 	String	dzrq	;//	台帐年月

    public String getDwbh() {
        return dwbh;
    }

    public void setDwbh(String dwbh) {
        this.dwbh = dwbh;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getGrbh() {
        return grbh;
    }

    public void setGrbh(String grbh) {
        this.grbh = grbh;
    }

    public String getSbkh() {
        return sbkh;
    }

    public void setSbkh(String sbkh) {
        this.sbkh = sbkh;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getJfny() {
        return jfny;
    }

    public void setJfny(String jfny) {
        this.jfny = jfny;
    }

    public double getJfjs() {
        return jfjs;
    }

    public void setJfjs(double jfjs) {
        this.jfjs = jfjs;
    }

    public double getDwjfje() {
        return dwjfje;
    }

    public void setDwjfje(double dwjfje) {
        this.dwjfje = dwjfje;
    }

    public double getGrjfje() {
        return grjfje;
    }

    public void setGrjfje(double grjfje) {
        this.grjfje = grjfje;
    }

    public String getDzrq() {
        return dzrq;
    }

    public void setDzrq(String dzrq) {
        this.dzrq = dzrq;
    }
}
