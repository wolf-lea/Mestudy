package com.tecsun.sisp.iface.common.vo.card;

import com.tecsun.sisp.iface.common.util.Constants;

/**
 * Created by jerry on 2015/5/31.
 */
public class TsmtbxgrjfmxVO {

    private final String TABLE_NAME = Constants.TS_V_BP_MTBXGRJFMX;

    private 	String	grbh	;//	个人编号
    private 	String	sfzh	;//	身份证号
    private 	String	sbkh	;//	社会保障卡卡号
    private 	String	cbzt	;//	参保状态
    private 	String	jfny	;//	费款所属期
    private 	String	grjfjs	;//	缴费基数
    private 	String	grjfje	;//	本期应缴

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

    public String getSbkh() {
        return sbkh;
    }

    public void setSbkh(String sbkh) {
        this.sbkh = sbkh;
    }

    public String getCbzt() {
        return cbzt;
    }

    public void setCbzt(String cbzt) {
        this.cbzt = cbzt;
    }

    public String getJfny() {
        return jfny;
    }

    public void setJfny(String jfny) {
        this.jfny = jfny;
    }

    public String getGrjfjs() {
        return grjfjs;
    }

    public void setGrjfjs(String grjfjs) {
        this.grjfjs = grjfjs;
    }

    public String getGrjfje() {
        return grjfje;
    }

    public void setGrjfje(String grjfje) {
        this.grjfje = grjfje;
    }
}
