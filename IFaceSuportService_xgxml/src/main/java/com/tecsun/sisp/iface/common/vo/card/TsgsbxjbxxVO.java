package com.tecsun.sisp.iface.common.vo.card;

import com.tecsun.sisp.iface.common.util.Constants;

/**
 * Created by jerry on 2015/5/31.
 */
public class TsgsbxjbxxVO {

    private final String TABLE_NAME = Constants.TS_V_BP_GSBXJBXX;
    private 	String	sbkh	;//	社会保障卡卡号
    private 	String	grbh	;//	个人编号
    private 	String	xm	;//	姓名
    private 	String	sfzh	;//	身份证号
    private 	String	cbzt	;//	参保状态
    private 	String	cbsj	;//	参保年月

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

    public String getCbzt() {
        return cbzt;
    }

    public void setCbzt(String cbzt) {
        this.cbzt = cbzt;
    }

    public String getCbsj() {
        return cbsj==null?"":cbsj;
    }

    public void setCbsj(String cbsj) {
        this.cbsj = cbsj;
    }
}
