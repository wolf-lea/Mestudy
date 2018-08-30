package com.tecsun.sisp.adapter.modules.cyt.entity.response;

import com.tecsun.sisp.adapter.common.util.Constants;
import org.apache.commons.lang3.StringUtils;

/**
 * Created by xumaohao on 2017/8/1.
 */
public class CollectBean {
    private String yljgmc;  //医疗机构名称
    public String byljgmc; //本医疗机构名称
    private String hzlsh;   //汇总流水号
    private String hzsj;    //汇总时间
    private String hzrc;    //汇总人次
    private String jehj;    //金额合计
    private String ybkkje;  //医保扣款金额
    private String tczfje;  //统筹自费金额
    private String jbr;   //经办人（操作人姓名）
    private String yhzh;    //银行账号
    private String hzzt;    //汇总状态

    public String getYljgmc() {
        return yljgmc;
    }

    public void setYljgmc(String yljgmc) {
        this.yljgmc = yljgmc;
    }

    public String getByljgmc() {
        return byljgmc;
    }

    public void setByljgmc(String byljgmc) {
        this.byljgmc = byljgmc;
    }

    public String getHzlsh() {
        return hzlsh;
    }

    public void setHzlsh(String hzlsh) {
        this.hzlsh = hzlsh;
    }

    public String getHzsj() {
        return hzsj;
    }

    public void setHzsj(String hzsj) {
        this.hzsj = hzsj;
    }

    public String getHzrc() {
        return hzrc;
    }

    public void setHzrc(String hzrc) {
        this.hzrc = hzrc;
    }

    public String getJehj() {
        return jehj;
    }

    public void setJehj(String jehj) {
        this.jehj = jehj;
    }

    public String getYbkkje() {
        return ybkkje;
    }

    public void setYbkkje(String ybkkje) {
        this.ybkkje = ybkkje;
    }

    public String getTczfje() {
        return tczfje;
    }

    public void setTczfje(String tczfje) {
        this.tczfje = tczfje;
    }

    public String getJbr() {
        return jbr;
    }

    public void setJbr(String jbr) {
        this.jbr = jbr;
    }

    public String getYhzh() {
        return yhzh;
    }

    public void setYhzh(String yhzh) {
        this.yhzh = yhzh;
    }

    public String getHzzt() {
        String _hzzt = Constants.CYT_COLLECT_STATUS.get(hzzt);
        return StringUtils.isNotEmpty(_hzzt) ? _hzzt :hzzt ;
    }

    public void setHzzt(String hzzt) {
        this.hzzt = hzzt;
    }
}
