package com.tecsun.sisp.adapter.modules.card.entity.request;

public class ResultBusMakeDetal {
	private long binId;
	private long boxId;
	private Integer cardno;
	private Integer cardsn;
	private String boxNo;
	private String binNo;
	private String orgAddress;
	private String certNum;
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCertNum() {

		return certNum;
	}

	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}

	public String getOrgAddress() {
		return orgAddress;
	}

	public void setOrgAddress(String orgAddress) {
		this.orgAddress = orgAddress;
	}

	public long getBinId() {
		return binId;
	}

	public void setBinId(long binId) {
		this.binId = binId;
	}

	public long getBoxId() {
		return boxId;
	}

	public void setBoxId(long boxId) {
		this.boxId = boxId;
	}

	public Integer getCardno() {
		return cardno;
	}

	public void setCardno(Integer cardno) {
		this.cardno = cardno;
	}

	public Integer getCardsn() {
		return cardsn;
	}

	public void setCardsn(Integer cardsn) {
		this.cardsn = cardsn;
	}

	public String getBoxNo() {
		return boxNo;
	}

	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}

	public String getBinNo() {
		return binNo;
	}

	public void setBinNo(String binNo) {
		this.binNo = binNo;
	}

	private String regisStatus;// 1， 待发放 0已发

	public String getRegisStatus() {
		return regisStatus;
	}

	public void setRegisStatus(String regisStatus) {
		this.regisStatus = regisStatus;
	}

	private String BATCHNO;

	public String getBATCHNO() {
		return BATCHNO;
	}

	public void setBATCHNO(String BATCHNO) {
		this.BATCHNO = BATCHNO;
	}
}
