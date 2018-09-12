package com.tecsun.sisp.iface.common.vo;

/**
 * Created by yinteng on 2016/4/13.
 */
public class NewPicInfoPO {

	private String picName;
	private String picPath;
	private String msg;
	private String isCheck;//状态：01-TSB传照片；02-照片处理成功；03-照片处理失败；04-TSB审核成功；05-TSB审核失败；06-制卡失败；07-制卡成功
	private String cernum;
	private String name;

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	public String getPicName() {
		return picName;
	}

	public void setPicName(String picName) {
		this.picName = picName;
	}

	public String getPicPath() {
		return picPath;
	}

	public void setPicPath(String picPath) {
		this.picPath = picPath;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
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

}
