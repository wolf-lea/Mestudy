package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.util.Constants;

/**
 * Created by jerry on 2015/5/31.
 */
public class TssydyffxxVO {

    private final String TABLE_NAME = Constants.TS_V_BP_SYDYFFXX;
    private 	String	grbh	;//	个人编号
    private 	String	jsrq	;//	结算日期
    private 	String	sfny	;//	实付年月
    private 	double	syj	;//	失业金
    private 	double	ylbzj	;//	医疗补助金
    private 	double	sydyze	;//	失业保险待遇总额
    private 	String	dwmc	;//	单位名称

    public String getGrbh() {
        return grbh;
    }

    public void setGrbh(String grbh) {
        this.grbh = grbh;
    }

    public String getJsrq() {
        return jsrq;
    }

    public void setJsrq(String jsrq) {
        this.jsrq = jsrq;
    }

    public String getSfny() {
        return sfny;
    }

    public void setSfny(String sfny) {
        this.sfny = sfny;
    }

    public double getSyj() {
        return syj;
    }

    public void setSyj(double syj) {
        this.syj = syj;
    }

    public double getYlbzj() {
        return ylbzj;
    }

    public void setYlbzj(double ylbzj) {
        this.ylbzj = ylbzj;
    }

    public double getSydyze() {
        return sydyze;
    }

    public void setSydyze(double sydyze) {
        this.sydyze = sydyze;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }
}
