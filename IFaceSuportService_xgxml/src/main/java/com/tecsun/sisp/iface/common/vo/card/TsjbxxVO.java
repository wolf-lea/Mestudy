package com.tecsun.sisp.iface.common.vo.card;

import com.tecsun.sisp.iface.common.util.Constants;

/**
 * Created by jerry on 2015/5/31.
 */
public class TsjbxxVO {


    private final String TABLE_NAME = Constants.TS_V_JBXX;

    private String grbh;//个人编号
    private String dwmc;//单位名称
    private String sfzh;//身份证号
    private String xm;//姓名
    private String xb;//性别
    private String mz;//民族
    private String cjgzsj;//参加工作日期
    private String ryzt;//人员状态
    private String cbzt;//参保状态
    private String ssqh;//所属区号
    private String kh;//社会保障卡卡号
    private String ltxsj;//离退休日期
    private String lxdh;//联系电话

    public String getGrbh() {
        return grbh;
    }

    public void setGrbh(String grbh) {
        this.grbh = grbh;
    }

    public String getDwmc() {
        return dwmc==null?"":dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
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

    public String getMz() {
        return mz;
    }

    public void setMz(String mz) {
        this.mz = mz;
    }

    public String getCjgzsj() {
        return cjgzsj;
    }

    public void setCjgzsj(String cjgzsj) {
        this.cjgzsj = cjgzsj;
    }

    public String getRyzt() {
        return ryzt;
    }

    public void setRyzt(String ryzt) {
        this.ryzt = ryzt;
    }

    public String getCbzt() {
        return cbzt;
    }

    public void setCbzt(String cbzt) {
        this.cbzt = cbzt;
    }

    public String getSsqh() {
        return ssqh;
    }

    public void setSsqh(String ssqh) {
        this.ssqh = ssqh;
    }

    public String getKh() {
        return kh;
    }

    public void setKh(String kh) {
        this.kh = kh;
    }

    public String getLtxsj() {
        return ltxsj;
    }

    public void setLtxsj(String ltxsj) {
        this.ltxsj = ltxsj;
    }

    public String getLxdh() {
        return lxdh==null?"":lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }
}
