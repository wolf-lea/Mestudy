package com.tecsun.sisp.adapter.modules.cyt.entity.response;

import com.tecsun.sisp.adapter.common.util.Constants;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by xumaohao on 2017/7/31.
 */
public class AccountQueryOutBean {
    private String jslsh;    //结算业务流水号
    private String bxfs;     //医保门诊统筹报销方式
    private String zfje;     //自费金额
    private String sfzh;     //身份证号
    private String xm;       //姓名
    private String sbkh;     //社保卡号
    private String jbbm;     //疾病icd-10编码
    private String jbmc;     //疾病名称
    private String bxje;     //报销金额
    private String jssj;     //结算时间
    private String jsqxsj;   //结算取消时间
    private String jszt;     //结算状态
    private String hzzt;     //汇总状态
    private String zje;      //总金额


    public String getJslsh() {
        return jslsh;
    }

    public void setJslsh(String jslsh) {
        this.jslsh = jslsh;
    }

    public String getBxfs() {
        return bxfs;
    }

    public void setBxfs(String bxfs) {
        this.bxfs = bxfs;
    }

    public String getZfje() {
        return zfje;
    }

    public void setZfje(String zfje) {
        this.zfje = zfje;
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

    public String getSbkh() {
        return sbkh;
    }

    public void setSbkh(String sbkh) {
        this.sbkh = sbkh;
    }

    public String getJbbm() {
        return jbbm;
    }

    public void setJbbm(String jbbm) {
        this.jbbm = jbbm;
    }

    public String getJbmc() {
        return jbmc;
    }

    public void setJbmc(String jbmc) {
        this.jbmc = jbmc;
    }

    public String getBxje() {
        return bxje;
    }

    public void setBxje(String bxje) {
        this.bxje = bxje;
    }

    public String getJssj() {
        return jssj;
    }

    public void setJssj(String jssj) {
        this.jssj = jssj;
    }

    public String getJsqxsj() {
        return jsqxsj;
    }

    public void setJsqxsj(String jsqxsj) {
        this.jsqxsj = jsqxsj;
    }

    public String getJszt() {
        String _jszt = Constants.CYT_ACCOUNT_STATUS.get(jszt);
        return StringUtils.isNotEmpty(_jszt) ? _jszt :jszt ;
    }

    public void setJszt(String jszt) {
        this.jszt = jszt;
    }

    public String getHzzt() {
        String _hzzt = Constants.CYT_COLLECT_STATUS.get(hzzt);
        return StringUtils.isNotEmpty(_hzzt) ? _hzzt :hzzt ;
    }

    public void setHzzt(String hzzt) {
        this.hzzt = hzzt;
    }

    public String getZje() {
        return zje;
    }

    public void setZje(String zje) {
        this.zje = zje;
    }
}
