package com.tecsun.sisp.iface.common.vo.card;

import com.tecsun.sisp.iface.common.util.Constants;

/**
 * Created by jerry on 2015/5/31.
 */
public class TsylgrzhxxVO {

    private final String TABLE_NAME = Constants.TS_V_BP_YLGRZHXX;
    private 	String	grbh	;//	个人编号
    private 	String	nd	;//	年度
    private 	double	zsndwhzbx	;//	截止上年末个人帐户单位划拨部分累计本息
    private 	double	zsngrjfbx	;//	截止上年末个人帐户个人缴费部分累计本息
    private 	String	zsnjfys	;//	截止上年末累计缴费月数
    private 	String	bnjfys	;//	养老本年缴费月数
    private 	double	dwhzbj	;//	基本养老保险本年帐户单位缴费划拨部分本金
    private 	double	dwhzlx	;//	本年缴纳单位划拨部分本年利息
    private 	double	bngrbj	;//	本年帐户个人缴费部分本金
    private 	double	bngrlx	;//	本年缴纳个人缴费部分本年利息

    public String getGrbh() {
        return grbh;
    }

    public void setGrbh(String grbh) {
        this.grbh = grbh;
    }

    public String getNd() {
        return nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public double getZsndwhzbx() {
        return zsndwhzbx;
    }

    public void setZsndwhzbx(double zsndwhzbx) {
        this.zsndwhzbx = zsndwhzbx;
    }

    public double getZsngrjfbx() {
        return zsngrjfbx;
    }

    public void setZsngrjfbx(double zsngrjfbx) {
        this.zsngrjfbx = zsngrjfbx;
    }

    public String getZsnjfys() {
        return zsnjfys;
    }

    public void setZsnjfys(String zsnjfys) {
        this.zsnjfys = zsnjfys;
    }

    public String getBnjfys() {
        return bnjfys;
    }

    public void setBnjfys(String bnjfys) {
        this.bnjfys = bnjfys;
    }

    public double getDwhzbj() {
        return dwhzbj;
    }

    public void setDwhzbj(double dwhzbj) {
        this.dwhzbj = dwhzbj;
    }

    public double getDwhzlx() {
        return dwhzlx;
    }

    public void setDwhzlx(double dwhzlx) {
        this.dwhzlx = dwhzlx;
    }

    public double getBngrbj() {
        return bngrbj;
    }

    public void setBngrbj(double bngrbj) {
        this.bngrbj = bngrbj;
    }

    public double getBngrlx() {
        return bngrlx;
    }

    public void setBngrlx(double bngrlx) {
        this.bngrlx = bngrlx;
    }
}
