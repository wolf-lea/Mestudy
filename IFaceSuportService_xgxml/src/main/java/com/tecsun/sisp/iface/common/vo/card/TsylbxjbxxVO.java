package com.tecsun.sisp.iface.common.vo.card;

import com.tecsun.sisp.iface.common.util.Constants;

/**
 * Created by jerry on 2015/5/31.
 */
public class TsylbxjbxxVO {

    private final String TABLE_NAME = Constants.TS_V_BP_YLBXJBXX;
    private 	String	dwbh	;//	单位编号
    private 	String	sbkh	;//	社会保障卡卡号
    private 	String	dwmc	;//	单位名称
    private 	String	grbh	;//	个人编号
    private 	String	sfzh	;//	身份证号
    private 	String	xm	;//	姓名
    private 	String	xb	;//	性别
    private 	String	csrq	;//	出生日期
    private 	String	hj	;//	户口所在地
    private 	String	cjgzsj	;//	参加工作日期
    private 	String	sccbsj	;//	参保时间
    private 	String	jfzt	;//	缴费状态
    private 	String	stjfnx	;//	视同缴费年限
    private 	String	ljjfnx	;//	实际缴费年限
    private 	String	jfjs	;//	基本养老保险缴费基数
    private 	String	grzt	;//	人员状态
    private 	String	ltxsj	;//	离退休日期
    private 	String	ltxlb	;//	离退休类别
    private 	String	nd	;//	年度
    private 	String	snzhljcce	;//	上年账号累计
    private 	String	bnzhcce	;//	本年账号
    private 	String	grzhljje	;//	个人账号累计金额

    public String getDwbh() {
        return dwbh;
    }

    public void setDwbh(String dwbh) {
        this.dwbh = dwbh;
    }

    public String getSbkh() {
        return sbkh;
    }

    public void setSbkh(String sbkh) {
        this.sbkh = sbkh;
    }

    public String getDwmc() {
        return dwmc==null?"":dwmc;
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

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getXb() {
        return xb;
    }

    public void setXb(String xb) {
        this.xb = xb;
    }

    public String getCsrq() {
        return csrq==null?"":csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getHj() {
        return hj==null?"":hj;
    }

    public void setHj(String hj) {
        this.hj = hj;
    }

    public String getCjgzsj() {
        return cjgzsj==null?"":cjgzsj;
    }

    public void setCjgzsj(String cjgzsj) {
        this.cjgzsj = cjgzsj;
    }

    public String getSccbsj() {
        return sccbsj==null?"":sccbsj;
    }

    public void setSccbsj(String sccbsj) {
        this.sccbsj = sccbsj;
    }

    public String getJfzt() {
        return jfzt==null?"":jfzt;
    }

    public void setJfzt(String jfzt) {
        this.jfzt = jfzt;
    }

    public String getStjfnx() {
        return stjfnx==null?"":stjfnx;
    }

    public void setStjfnx(String stjfnx) {
        this.stjfnx = stjfnx;
    }

    public String getLjjfnx() {
        return ljjfnx==null?"":ljjfnx;
    }

    public void setLjjfnx(String ljjfnx) {
        this.ljjfnx = ljjfnx;
    }

    public String getJfjs() {
        return jfjs==null?"":jfjs;
    }

    public void setJfjs(String jfjs) {
        this.jfjs = jfjs;
    }

    public String getGrzt() {
        return grzt==null?"":grzt;
    }

    public void setGrzt(String grzt) {
        this.grzt = grzt;
    }

    public String getLtxsj() {
        return ltxsj==null?"":ltxsj;
    }

    public void setLtxsj(String ltxsj) {
        this.ltxsj = ltxsj;
    }

    public String getLtxlb() {
        return ltxlb==null?"":ltxlb;
    }

    public void setLtxlb(String ltxlb) {
        this.ltxlb = ltxlb;
    }

    public String getNd() {
        return nd==null?"":nd;
    }

    public void setNd(String nd) {
        this.nd = nd;
    }

    public String getSnzhljcce() {
        return snzhljcce==null?"":snzhljcce;
    }

    public void setSnzhljcce(String snzhljcce) {
        this.snzhljcce = snzhljcce;
    }

    public String getBnzhcce() {
        return bnzhcce==null?"":bnzhcce;
    }

    public void setBnzhcce(String bnzhcce) {
        this.bnzhcce = bnzhcce;
    }

    public String getGrzhljje() {
        return grzhljje==null?"":grzhljje;
    }

    public void setGrzhljje(String grzhljje) {
        this.grzhljje = grzhljje;
    }
}
