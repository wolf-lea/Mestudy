package com.tecsun.sisp.iface.common.vo.card;

import com.tecsun.sisp.iface.common.util.Constants;

/**
 * Created by jerry on 2015/5/31.
 */
public class TslybxjbxxVO  {

    private final String TABLE_NAME = Constants.TS_V_BP_LYBXJBXX;

    private 	String	sbkh	;//	社会保障卡卡号
    private 	String	grbh	;//	个人编号
    private 	String	xm	;//	姓名
    private 	String	sfzh	;//	身份证号
    private 	String	rmgbz	;//	户口性质
    private 	String	bxjfsj	;//	累计缴费月数
    private 	String	syzbm	;//	失业证编号
    private 	String	bxdjyf	;//	应享受失业保险待遇月数
    private 	String	bxjsyyf	;//	本次剩余可享受失业保险待遇月数
    private 	String	sydyje	;//	失业待遇金额

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

    public String getRmgbz() {
        return rmgbz==null?"":rmgbz;
    }

    public void setRmgbz(String rmgbz) {
        this.rmgbz = rmgbz;
    }

    public String getBxjfsj() {
        return bxjfsj==null?"":bxjfsj;
    }

    public void setBxjfsj(String bxjfsj) {
        this.bxjfsj = bxjfsj;
    }

    public String getSyzbm() {
        return syzbm==null?"":syzbm;
    }

    public void setSyzbm(String syzbm) {
        this.syzbm = syzbm;
    }

    public String getBxdjyf() {
        return bxdjyf==null?"":bxdjyf;
    }

    public void setBxdjyf(String bxdjyf) {
        this.bxdjyf = bxdjyf;
    }

    public String getBxjsyyf() {
        return bxjsyyf==null?"":bxjsyyf;
    }

    public void setBxjsyyf(String bxjsyyf) {
        this.bxjsyyf = bxjsyyf;
    }

    public String getSydyje() {
        return sydyje==null?"":sydyje;
    }

    public void setSydyje(String sydyje) {
        this.sydyje = sydyje;
    }
}
