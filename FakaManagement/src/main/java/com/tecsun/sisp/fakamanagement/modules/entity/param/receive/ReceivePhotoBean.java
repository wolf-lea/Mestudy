package com.tecsun.sisp.fakamanagement.modules.entity.param.receive;

import com.tecsun.sisp.fakamanagement.modules.entity.TSBVO;

public class ReceivePhotoBean  extends TSBVO{
	
	private String base64code;
	private Integer type;//照片类型；1头像2身份证正面3身份证反面4签名
	private String photoname;//照片名 用于转换后保存
	public String getBase64code() {
		return base64code;
	}
	public void setBase64code(String base64code) {
		this.base64code = base64code;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getPhotoname() {
		return photoname;
	}
	public void setPhotoname(String photoname) {
		this.photoname = photoname;
	}

	
}
