package com.tecsun.sisp.iface.common.vo;

/**
 * Created by yinteng on 2016/4/13.
 */
public class picInfo {
	private String base64String;
	private String picName;
	private String msg;
	private String isCheck;// 状态：01-TSB传照片；02-照片处理成功；03-照片处理失败；04-TSB审核成功；05-TSB审核失败；06-制卡失败；07-制卡成功
	private String cernum;
	private String name;
	private String phone;// 手机号

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getCernum() {
		return cernum;
	}

	public void setCernum(String cernum) {
		this.cernum = cernum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
