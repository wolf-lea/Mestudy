package com.tecsun.sisp.adapter.modules.cyt.entity.response;

import com.tecsun.sisp.adapter.common.util.Constants;
import org.apache.commons.lang3.StringUtils;

import java.math.BigDecimal;

/**
 * Created by xumaohao on 2017/7/28.
 */
public class PersonBean {

    private String id;  //id
    private String xm;  //姓名
    private String xb;  //性别
    private String sfzh;//身份证号
    private String csrq;//出生日期
    private String xzqh;//行政区划
    private String cbzt;//参保状态
    private String lxdz;//联系地址
    private String lxdh;//联系电话
    private String jgmc;//机构名称
    private String tcye;//统筹余额
    private String sbkh;//社保卡号

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
        return csrq;
    }

    public void setCsrq(String csrq) {
        this.csrq = csrq;
    }

    public String getXzqh() {
        return xzqh;
    }

    public void setXzqh(String xzqh) {
        this.xzqh = xzqh;
    }

    public String getCbzt() {
        String _hzzt = Constants.CYT_CBZT_STATUS.get(cbzt);
        return StringUtils.isNotEmpty(_hzzt) ? _hzzt :cbzt ;
    }

    public void setCbzt(String cbzt) {
        this.cbzt = cbzt;
    }

    public String getLxdz() {
        return lxdz;
    }

    public void setLxdz(String lxdz) {
        this.lxdz = lxdz;
    }

    public String getLxdh() {
        return lxdh;
    }

    public void setLxdh(String lxdh) {
        this.lxdh = lxdh;
    }

    public String getJgmc() {
        return jgmc;
    }

    public void setJgmc(String jgmc) {
        this.jgmc = jgmc;
    }

    public String getTcye() {
        return change(2,tcye);
    }

    public void setTcye(String tcye) {
        this.tcye = tcye;
    }

    private String change(int num ,String value) {
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(num, BigDecimal.ROUND_HALF_UP);
        return String.valueOf(bd);
    }

    public String getSbkh() {
        return sbkh;
    }

    public void setSbkh(String sbkh) {
        this.sbkh = sbkh;
    }
}
