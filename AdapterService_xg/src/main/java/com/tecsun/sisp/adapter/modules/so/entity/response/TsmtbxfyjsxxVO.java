package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.util.Constants;

/**
 * Created by jerry on 2015/5/31.
 */
public class TsmtbxfyjsxxVO {

    private final String TABLE_NAME = Constants.TS_V_BP_MTBXFYJSXX;

    private 	String	sbkh	;//	社会保障卡卡号
    private 	String	grbh	;//	个人编号
    private 	String	sfzh	;//	身份证号
    private 	String	jsrq	;//	结算日期
    private 	String	year	;//	结算年份
    private 	String	yljgbh	;//	定点医疗机构编号
    private 	String	yljgmc	;//	服务机构名称1、医院4、药店9、虚拟会测试
    private 	String	yllb	;//	医疗类别
    private 	double	zfy	;//	医疗费总额
    private 	double	zffy	;//	上次个人帐户支付累计
    private 	double	xjzf	;//	个人现金支付
    private 	double	grzhzf	;//	上次个人帐户支付累计
    private 	double	tczf	;//	统筹支付金额

    public String getSbkh() {
        return sbkh;
    }


    public void setSbkh(String sbkh) {
        this.sbkh = sbkh;
    }

    public String getGrbh() {
        return grbh;
    }

    public void setGrbh(String grbh) {
        this.grbh = grbh;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getJsrq() {
        return jsrq;
    }

    public void setJsrq(String jsrq) {
        this.jsrq = jsrq;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getYljgbh() {
        return yljgbh;
    }

    public void setYljgbh(String yljgbh) {
        this.yljgbh = yljgbh;
    }

    public String getYljgmc() {
        return yljgmc;
    }

    public void setYljgmc(String yljgmc) {
        this.yljgmc = yljgmc;
    }

    public String getYllb() {
        return yllb;
    }

    public void setYllb(String yllb) {
        this.yllb = yllb;
    }

    public double getZfy() {
        return zfy;
    }

    public void setZfy(double zfy) {
        this.zfy = zfy;
    }

    public double getZffy() {
        return zffy;
    }

    public void setZffy(double zffy) {
        this.zffy = zffy;
    }

    public double getXjzf() {
        return xjzf;
    }

    public void setXjzf(double xjzf) {
        this.xjzf = xjzf;
    }

    public double getGrzhzf() {
        return grzhzf;
    }

    public void setGrzhzf(double grzhzf) {
        this.grzhzf = grzhzf;
    }

    public double getTczf() {
        return tczf;
    }

    public void setTczf(double tczf) {
        this.tczf = tczf;
    }
}
