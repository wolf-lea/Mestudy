package com.tecsun.sisp.fakamanagement.modules.entity.param.card;

import com.tecsun.sisp.fakamanagement.modules.entity.TSBVO;

/**
 * 
* @ClassName: CardCabinetBean 
* @Description: TODO(卡柜操作接口输入参数) 
* @author huangtao
* @date 2017年7月10日 下午2:05:35 
*
 */
public class CardCabinetBean extends TSBVO{
	
	private Integer  type;//新增类型，1卡柜操作，2柜层操作，3卡盒操作
	private Integer  cbid;//卡盒id
	private Integer  cfid;//层id
	private Integer  ccaid;//柜子id
	private Integer  maxnum;//柜子id
	private String  userid;//发卡机构
	private String  ccid;//卡柜名称
	
	public Integer getCfid() {
		return cfid;
	}
	public void setCfid(Integer cfid) {
		this.cfid = cfid;
	}
	public String getCcid() {
		return ccid;
	}
	public void setCcid(String ccid) {
		this.ccid = ccid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getCbid() {
		return cbid;
	}
	public void setCbid(Integer cbid) {
		this.cbid = cbid;
	}
	public Integer getCcaid() {
		return ccaid;
	}
	public void setCcaid(Integer ccaid) {
		this.ccaid = ccaid;
	}
	public Integer getMaxnum() {
		return maxnum;
	}
	public void setMaxnum(Integer maxnum) {
		this.maxnum = maxnum;
	}
	
	

}
