package com.tecsun.sisp.iface.common.vo.so.po.endow;

/**
 * 城乡养老保险个人养老金发放情况
 * 
 * @author tan
 *
 */
public class GrffPO {

	private String aac001;// 个人编号
	private String aae002;// 发放年月
	private String aae019;// 本月养老金合计
	private String bac419;// 其中基础养老金
	private String bac418;// 其中个人账户养老金

	public String getAac001() {
		return aac001;
	}

	public void setAac001(String aac001) {
		this.aac001 = aac001;
	}

	public String getAae002() {
		return aae002;
	}

	public void setAae002(String aae002) {
		this.aae002 = aae002;
	}

	public String getAae019() {
		return aae019;
	}

	public void setAae019(String aae019) {
		this.aae019 = aae019;
	}

	public String getBac419() {
		return bac419;
	}

	public void setBac419(String bac419) {
		this.bac419 = bac419;
	}

	public String getBac418() {
		return bac418;
	}

	public void setBac418(String bac418) {
		this.bac418 = bac418;
	}
}
