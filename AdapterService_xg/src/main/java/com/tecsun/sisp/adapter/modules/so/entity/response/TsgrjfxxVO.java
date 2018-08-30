package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.util.Constants;

/**
 * Created by jerry on 2015/5/31.
 */
public class TsgrjfxxVO{

    private final String TABLE_NAME = Constants.TS_V_BP_GRJFXX;

    private 	String	grbh	;//	个人编号
    private 	String	tzny	;//	台帐年月
    private 	String	fkssq	;//	费款所属期
    private 	String	xzlx	;//	险种类型
    private 	String	kx	;//	款项
    private 	double	jfjs	;//	缴费基数
    private 	double	bqyj	;//	本期应缴
    private 	double	hrje	;//	划入个人帐户金额
    private 	String	jfbz	;//	缴费标志
    private 	String	hzbz	;//	划帐标志

    private 	String	hzrq	;//	划帐日期
    private 	String	dwbh	;//	单位编号
    private 	String	dwmc	;//	单位名称

    public String getGrbh() {
        return grbh;
    }

    public void setGrbh(String grbh) {
        this.grbh = grbh;
    }

    public String getTzny() {
        return tzny;
    }

    public void setTzny(String tzny) {
        this.tzny = tzny;
    }

    public String getFkssq() {
        return fkssq;
    }

    public void setFkssq(String fkssq) {
        this.fkssq = fkssq;
    }

    public String getXzlx() {
        return xzlx==null?"":xzlx;
    }

    public void setXzlx(String xzlx) {
        this.xzlx = xzlx;
    }

    public String getKx() {
        return kx==null?"":kx;
    }

    public void setKx(String kx) {
        this.kx = kx;
    }

    public double getJfjs() {
        return jfjs;
    }

    public void setJfjs(double jfjs) {
        this.jfjs = jfjs;
    }

    public double getBqyj() {
        return bqyj;
    }

    public void setBqyj(double bqyj) {
        this.bqyj = bqyj;
    }

    public double getHrje() {
        return hrje;
    }

    public void setHrje(double hrje) {
        this.hrje = hrje;
    }

    public String getJfbz() {
        return jfbz==null?"":jfbz;
    }

    public void setJfbz(String jfbz) {
        this.jfbz = jfbz;
    }

    public String getHzbz() {
        return hzbz==null?"":hzbz;
    }

    public void setHzbz(String hzbz) {
        this.hzbz = hzbz;
    }

    public String getHzrq() {
        return hzrq==null?"":hzrq;
    }

    public void setHzrq(String hzrq) {
        this.hzrq = hzrq;
    }

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
}
