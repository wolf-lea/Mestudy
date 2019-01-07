package com.tecsun.sisp.fakamanagement.modules.entity.result.card;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

/**
 * 
* @ClassName: CardBatchVO 
* @Description: TODO(批次 箱 盒 数量) 
* @author huangtao
* @date 2017年7月11日 下午4:53:29 
*
 */
public class CardBatchVO extends BaseVO{
	
	private String batchno;//批次号
	private String casenum;//箱
	private String fkwd;//发卡机构
	private String boxnum;//盒
	private String counts;//数量
	private Integer type;//获取类型，1获取批次号、2.获取箱数及数量，3获取盒数及数量
	public String getBatchno() {
		return batchno;
	}
	public void setBatchno(String batchno) {
		this.batchno = batchno;
	}
	public String getCasenum() {
		return casenum;
	}
	public void setCasenum(String casenum) {
		this.casenum = casenum;
	}
	public String getBoxnum() {
		return boxnum;
	}
	public void setBoxnum(String boxnum) {
		this.boxnum = boxnum;
	}
	public String getCounts() {
		return counts;
	}
	public void setCounts(String counts) {
		this.counts = counts;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * @return the fkwd
	 */
	public String getFkwd() {
		return fkwd;
	}
	/**
	 * @param fkwd the fkwd to set
	 */
	public void setFkwd(String fkwd) {
		this.fkwd = fkwd;
	}
	
	

}
