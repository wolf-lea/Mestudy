package com.tecsun.sisp.iface.common.vo.card;

import com.tecsun.sisp.iface.common.util.Constants;

/**
 * Created by jerry on 2015/5/31.
 */
public class TsylyzhxxVO {

    private final String TABLE_NAME = Constants.TS_V_BP_YLYZHXX;

    private 	String	grbh	;//	个人编号
    private 	String	jzny	;//	记帐年月
    private 	String	dwbh	;//	单位编号
    private 	String	ssq	;//	对应费款所属期
    private 	String	jfjs	;//	缴费基数
    private 	double	spgz	;//	社平工资
    private 	double	grjnje	;//	个人缴纳金额
    private 	double	dwhzbje	;//	单位划拨金额
    private 	String	ys	;//	月数

    public String getGrbh() {
        return grbh;
    }

    public void setGrbh(String grbh) {
        this.grbh = grbh;
    }

    public String getJzny() {
        return jzny;
    }

    public void setJzny(String jzny) {
        this.jzny = jzny;
    }

    public String getDwbh() {
        return dwbh;
    }

    public void setDwbh(String dwbh) {
        this.dwbh = dwbh;
    }

    public String getSsq() {
        return ssq;
    }

    public void setSsq(String ssq) {
        this.ssq = ssq;
    }

    public String getJfjs() {
        return jfjs;
    }

    public void setJfjs(String jfjs) {
        this.jfjs = jfjs;
    }

    public double getSpgz() {
        return spgz;
    }

    public void setSpgz(double spgz) {
        this.spgz = spgz;
    }

    public double getGrjnje() {
        return grjnje;
    }

    public void setGrjnje(double grjnje) {
        this.grjnje = grjnje;
    }

    public double getDwhzbje() {
        return dwhzbje;
    }

    public void setDwhzbje(double dwhzbje) {
        this.dwhzbje = dwhzbje;
    }

    public String getYs() {
        return ys;
    }

    public void setYs(String ys) {
        this.ys = ys;
    }
}
