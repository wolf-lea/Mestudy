package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.util.Constants;

/**
 * Created by jerry on 2015/5/31.
 */
public class TsgrcbxxVO {
    private final String TABLE_NAME = Constants.TS_V_BP_GRCBXX;

    private 	String	dwbh;//	单位编号
    private 	String	grbh;//	个人编号
    private 	String	cbxz;//	参保险种类型
    private 	String	cbzt;//	参保状态
    private 	String	ksny;//	开始年月
    private 	String	zzny;//	终止年月
    private 	String	dwmc;//	单位名称
    private 	String	jbsj;//	经办日期
    private 	String	mobile;//手机号码
    private 	String	phone;// 联系方式
    

    public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDwbh() {
        return dwbh;
    }

    public void setDwbh(String dwbh) {
        this.dwbh = dwbh;
    }

    public String getGrbh() {
        return grbh;
    }

    public void setGrbh(String grbh) {
        this.grbh = grbh;
    }

    public String getCbxz() {
        return cbxz;
    }

    public void setCbxz(String cbxz) {
        this.cbxz = cbxz;
    }

    public String getCbzt() {
        return cbzt;
    }

    public void setCbzt(String cbzt) {
        this.cbzt = cbzt;
    }

    public String getKsny() {
        return ksny==null?"":ksny;
    }

    public void setKsny(String ksny) {
        this.ksny = ksny;
    }

    public String getZzny() {
        return zzny==null?"":zzny;
    }

    public void setZzny(String zzny) {
        this.zzny = zzny;
    }

    public String getDwmc() {
        return dwmc;
    }

    public void setDwmc(String dwmc) {
        this.dwmc = dwmc;
    }

    public String getJbsj() {
        return jbsj;
    }

    public void setJbsj(String jbsj) {
        this.jbsj = jbsj;
    }
}
