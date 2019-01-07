package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;

/**
* @author  wunuanchan
* @version 
* 创建时间：2018年3月12日 下午7:25:38
* 说明：城镇居民缴费信息
*/

public class TRPaymentVO {
	
	@Field(empty="-")
	private String jtbh;//家庭编号
	@Field(empty="-")
	private String xzlx;//险种类型
	@Field(empty="-")
	private String kx;//款项
	@Field(empty="-")
	private String ssq;//款费所属期
	@Field(empty="-")
	private String jflx;//缴费类型
	@Field(empty="-")
	private String sjje;//实缴费金额
	@Field(empty="-")
	private String sjrq;//实缴费日期
	public String getJtbh() {
		return jtbh;
	}
	public void setJtbh(String jtbh) {
		this.jtbh = jtbh;
	}
	public String getXzlx() {
		return xzlx;
	}
	public void setXzlx(String xzlx) {
		this.xzlx = xzlx;
	}
	public String getKx() {
		return kx;
	}
	public void setKx(String kx) {
		this.kx = kx;
	}
	public String getSsq() {
		return ssq;
	}
	public void setSsq(String ssq) {
		this.ssq = ssq;
	}
	public String getJflx() {
		return jflx;
	}
	public void setJflx(String jflx) {
		this.jflx = jflx;
	}
	public String getSjje() {
		return sjje;
	}
	public void setSjje(String sjje) {
		this.sjje = sjje;
	}
	public String getSjrq() {
		return sjrq;
	}
	public void setSjrq(String sjrq) {
		this.sjrq = sjrq;
	}
	
	
	
	

}
