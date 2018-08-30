package com.tecsun.sisp.adapter.modules.card.entity.request;

/**社保卡申领图片入参
 */
public class CsspTSBPicBean {
	private String base64String;
	private String picName;
	private String msg;
	private String isCheck;// 状态：01-TSB传照片；02-照片处理成功；03-照片处理失败；04-TSB审核成功；05-TSB审核失败；06-制卡失败；07-制卡成功
	private String sfzh;
	private String xm;
	private String phone;// 手机号

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setCernum(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public String getBase64String() {
		return base64String;
	}

	public void setBase64String(String base64String) {
		this.base64String = base64String;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}
}
