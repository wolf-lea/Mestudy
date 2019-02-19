package com.tecsun.sisp.iface.common.vo.card;

import com.tecsun.sisp.iface.common.util.Constants;

/**
 * Created by jerry on 2015/5/31.
 */
public class TsylbxltxrydyxxVO {

    private final String TABLE_NAME = Constants.TS_V_BP_YLBXLTXRYDYXX;
    private 	String	sfzh	;//	身份证号
    private 	String	grbh	;//	个人编号
    private 	String	xm	;//	姓名
    private 	String	ltxny	;//	离退休日期
    private 	String	ffny	;//	发放年月
    private 	double	ylj	;//	养老待遇金额

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getGrbh() {
        return grbh;
    }

    public void setGrbh(String grbh) {
        this.grbh = grbh;
    }

    public String getXm() {
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getLtxny() {
        return ltxny;
    }

    public void setLtxny(String ltxny) {
        this.ltxny = ltxny;
    }

    public String getFfny() {
        return ffny;
    }

    public void setFfny(String ffny) {
        this.ffny = ffny;
    }

    public double getYlj() {
        return ylj;
    }

    public void setYlj(double ylj) {
        this.ylj = ylj;
    }
}
