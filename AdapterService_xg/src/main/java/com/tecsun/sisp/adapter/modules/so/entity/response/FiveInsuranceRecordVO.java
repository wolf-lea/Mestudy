package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;
import com.tecsun.sisp.adapter.common.util.CommUtil;

public class FiveInsuranceRecordVO {

	@Field(empty = "无")
	private String ssq;// 所属期
	@Field(empty = "-")
	private String jfjs;// 缴费基数
	@Field(empty = "-")
	private String dwjn;// 单位缴纳
	@Field(empty = "-")
	private String grjn;// 个人缴纳
	@Field(empty = "-")
	private String jnze;// 缴纳总额

	@Field(empty = "-")
	private String dzrq;// 到账日期
	@Field(empty = "-")
	private String cbdq;// 参保地区
	@Field(empty = "无")
	private String kx;// 款项
	@Field(empty = "无")
	private String xzlx;// 险种类型
	@Field(empty = "0")
	private String sjje;// 实缴金额

	public String getSsq() {
		return ssq;
	}

	public void setSsq(String ssq) {
		this.ssq = ssq;
	}

	public String getJfjs() {
		if (jfjs == null || jfjs.isEmpty())
			return jfjs;

		return CommUtil.formatNumberic(jfjs);
	}

	public void setJfjs(String jfjs) {
		this.jfjs = jfjs;
	}

	public String getDwjn() {
		if (dwjn == null || dwjn.isEmpty())
			return dwjn;

		return CommUtil.formatNumberic(dwjn);
	}

	public void setDwjn(String dwjn) {
		this.dwjn = dwjn;
	}

	public String getGrjn() {
		if (grjn == null || grjn.isEmpty())
			return grjn;

		return CommUtil.formatNumberic(grjn);
	}

	public void setGrjn(String grjn) {
		this.grjn = grjn;
	}

	public String getJnze() {
		if (jnze == null || jnze.isEmpty())
			return jnze;

		return CommUtil.formatNumberic(jnze);
	}

	public void setJnze(String jnze) {
		this.jnze = jnze;
	}

	public String getDzrq() {
		return dzrq;
	}

	public void setDzrq(String dzrq) {
		this.dzrq = dzrq;
	}

	public String getCbdq() {
		return cbdq;
	}

	public void setCbdq(String cbdq) {
		this.cbdq = cbdq;
	}

	public String getKx() {
		return kx;
	}

	public void setKx(String kx) {
		this.kx = kx;
	}

	public String getXzlx() {
		return xzlx;
	}

	public void setXzlx(String xzlx) {
		this.xzlx = xzlx;
	}

	public String getSjje() {
		return sjje;
	}

	public void setSjje(String sjje) {
		this.sjje = sjje;
	}

}
