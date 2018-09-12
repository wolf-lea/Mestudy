package com.tecsun.sisp.fakamanagement.modules.entity.param.card;

import com.tecsun.sisp.fakamanagement.modules.entity.TSBVO;

/**
 * 
* @ClassName: CardStoreBean 
* @Description: TODO(卡入库参数类) 
* @author huangtao
* @date 2017年7月10日 下午5:22:10 
*
 */
public class CardStoreBean  extends TSBVO{
	
	private String batch_no;//批次号
	private Integer ccid;//柜号
	private Integer userid;//发卡机构
	private Integer cfid;//层号
	private Integer cbid;//盒号
	private String cfwz;//旧存放位置 箱号-盒号
	private String idcard;//身份证号码
	private String cardid;//社会保障卡卡号
	public String getBatch_no() {
		return batch_no;
	}
	public void setBatch_no(String batch_no) {
		this.batch_no = batch_no;
	}
	public Integer getCcid() {
		return ccid;
	}
	public void setCcid(Integer ccid) {
		this.ccid = ccid;
	}
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public Integer getCfid() {
		return cfid;
	}
	public void setCfid(Integer cfid) {
		this.cfid = cfid;
	}
	public Integer getCbid() {
		return cbid;
	}
	public void setCbid(Integer cbid) {
		this.cbid = cbid;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public String getCardid() {
		return cardid;
	}
	public void setCardid(String cardid) {
		this.cardid = cardid;
	}
	public String getCfwz() {
		return cfwz;
	}
	public void setCfwz(String cfwz) {
		this.cfwz = cfwz;
	}
	
	

}
