package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;

/**
 * 查询参保信息险种 Created by danmeng on 2016/8/9.
 */
public class IncureTypeVO {

	@Field(empty = "无")
	private String cbxz; // 险种类型
	@Field(empty = "无")
	private String cbzt; // 参保状态
	@Field(empty = "-")
	private String ksny;// 开始年月
	@Field(empty = "-")
	private String zzny;// 终止年月
	@Field(empty = "-")
	private String jbsj;// 经办日期
	@Field(empty = "无")
	private String dwmc;// 单位名称；

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
		return ksny;
	}

	public void setKsny(String ksny) {
		this.ksny = ksny;
	}

	public String getZzny() {
		return zzny;
	}

	public void setZzny(String zzny) {
		this.zzny = zzny;
	}

	public String getJbsj() {
		return jbsj;
	}

	public void setJbsj(String jbsj) {
		this.jbsj = jbsj;
	}

	public String getDwmc() {
		return dwmc;
	}

	public void setDwmc(String dwmc) {
		this.dwmc = dwmc;
	}
}
