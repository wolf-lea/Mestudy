package com.tecsun.sisp.net.modules.entity.business;

import java.util.Date;

import com.tecsun.sisp.net.modules.entity.BaseVO;

public class BusinessHandle extends BaseVO<BusinessHandle>{

	private String TID;
	
	private Date APPLYTIME;
	
	private String REMARKS;
	
	private String BUSINESSID;
	
	private String OFFICEID;
	
	private String SUBMITTER;
	
	private Date UPDATETIME;
	
	private String STATE;
	
	private String MID;
	
	private String MATTER_NAME;
	
	private String OID;
	
	private String SFZH;
	
	private String XM;

	public String getTID() {
		return TID;
	}

	public void setTID(String tID) {
		TID = tID;
	}

	public Date getAPPLYTIME() {
		return APPLYTIME;
	}

	public void setAPPLYTIME(Date aPPLYTIME) {
		APPLYTIME = aPPLYTIME;
	}

	public String getREMARKS() {
		return REMARKS;
	}

	public void setREMARKS(String rEMARKS) {
		REMARKS = rEMARKS;
	}

	public String getBUSINESSID() {
		return BUSINESSID;
	}

	public void setBUSINESSID(String bUSINESSID) {
		BUSINESSID = bUSINESSID;
	}

	public String getOFFICEID() {
		return OFFICEID;
	}

	public void setOFFICEID(String oFFICEID) {
		OFFICEID = oFFICEID;
	}

	public String getSUBMITTER() {
		return SUBMITTER;
	}

	public void setSUBMITTER(String sUBMITTER) {
		SUBMITTER = sUBMITTER;
	}

	public Date getUPDATETIME() {
		return UPDATETIME;
	}

	public void setUPDATETIME(Date uPDATETIME) {
		UPDATETIME = uPDATETIME;
	}

	public String getSTATE() {
		return STATE;
	}

	public void setSTATE(String sTATE) {
		STATE = sTATE;
	}

	public String getMID() {
		return MID;
	}

	public void setMID(String mID) {
		MID = mID;
	}

	public String getMATTER_NAME() {
		return MATTER_NAME;
	}

	public void setMATTER_NAME(String mATTER_NAME) {
		MATTER_NAME = mATTER_NAME;
	}

	public String getOID() {
		return OID;
	}

	public void setOID(String oID) {
		OID = oID;
	}

	public String getSFZH() {
		return SFZH;
	}

	public void setSFZH(String sFZH) {
		SFZH = sFZH;
	}

	public String getXM() {
		return XM;
	}

	public void setXM(String xM) {
		XM = xM;
	}

	
	
	
}
