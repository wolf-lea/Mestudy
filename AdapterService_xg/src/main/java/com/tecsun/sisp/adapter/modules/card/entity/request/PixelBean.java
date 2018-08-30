package com.tecsun.sisp.adapter.modules.card.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

public class PixelBean extends SecQueryBean{
	
	private String base64String; //照片信息，base64位
	private String picType; //照片规格：01—社保卡证件照，02—待定
    private long picId;

    private String busId ; // 业务id
	private String callbackUrl; // 回调路径（url）
	
	private String status;//处理是否成功；01—成功；02—失败
	private String message;//失败原因，成功可不填
    private String sfzh;//身份证号码
    private String sbkh;//社保卡号

    public long getPicId() {
        return picId;
    }

    public void setPicId(long picId) {
        this.picId = picId;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }
    
    public String getSbkh() {
        return sbkh;
    }

    public void setSbkh(String sbkh) {
        this.sbkh = sbkh;
    }
	public String getBase64String() {
		return base64String;
	}
	public void setBase64String(String base64String) {
		this.base64String = base64String;
	}
	public String getPicType() {
		return picType;
	}
	public void setPicType(String picType) {
		this.picType = picType;
	}
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public String getCallbackUrl() {
		return callbackUrl;
	}
	public void setCallbackUrl(String callbackUrl) {
		this.callbackUrl = callbackUrl;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
