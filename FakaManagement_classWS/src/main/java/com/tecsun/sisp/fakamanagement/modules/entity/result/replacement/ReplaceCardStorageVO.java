package com.tecsun.sisp.fakamanagement.modules.entity.result.replacement;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;
/**
 * @author tanzhiyong
 * @version
 * 
 *          创建时间：2018年10月24日 上午11:30:12 说明：
 */
public class ReplaceCardStorageVO extends BaseVO{
	
	private String id;			
	private String boxNo;			//盒号
	private String cardNoStart;		//预制卡卡编号_开始
	private String cardNoEnd;		//预制卡卡编号_结束
	private String status;			//状态 00-未下发 01-已接收 02-部分下发 03-全部下发
	private String bank;			//下发银行 (编码 当状态为00、01时必填)
	private String rkwd;			//入库网点 (编码 当状态为02、03时必填)
	private Integer cardNoSum;		//总数
	private Integer surplusSum;		//剩余数量
	private String claim;			//	领卡人
	private Integer userid;			//操作人	
	private String cardNOPerBox;	
	
	
	
	
	public String getCardNOPerBox() {
		return cardNOPerBox;
	}
	public void setCardNOPerBox(String cardNOPerBox) {
		this.cardNOPerBox = cardNOPerBox;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBoxNo() {
		return boxNo;
	}
	public void setBoxNo(String boxNo) {
		this.boxNo = boxNo;
	}
	public String getCardNoStart() {
		return cardNoStart;
	}
	public void setCardNoStart(String cardNoStart) {
		this.cardNoStart = cardNoStart;
	}
	public String getCardNoEnd() {
		return cardNoEnd;
	}
	public void setCardNoEnd(String cardNoEnd) {
		this.cardNoEnd = cardNoEnd;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getRkwd() {
		return rkwd;
	}
	public void setRkwd(String rkwd) {
		this.rkwd = rkwd;
	}
	public Integer getCardNoSum() {
		return cardNoSum;
	}
	public void setCardNoSum(Integer cardNoSum) {
		this.cardNoSum = cardNoSum;
	}
	public Integer getSurplusSum() {
		return surplusSum;
	}
	public void setSurplusSum(Integer surplusSum) {
		this.surplusSum = surplusSum;
	}
	public String getClaim() {
		return claim;
	}
	public void setClaim(String claim) {
		this.claim = claim;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	
	
	
}
