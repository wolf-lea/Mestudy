package com.tecsun.sisp.net.modules.entity.request;

//证照库入参
public class PersonBeanRequest {
	
	private String holderCode;
	
	private String fileNumber;
	
	private String fileId;

	public String getHolderCode() {
		return holderCode;
	}

	public void setHolderCode(String holderCode) {
		this.holderCode = holderCode;
	}

	public String getFileNumber() {
		return fileNumber;
	}

	public void setFileNumber(String fileNumber) {
		this.fileNumber = fileNumber;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	
	

}
