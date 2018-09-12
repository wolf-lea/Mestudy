package com.tecsun.sisp.fakamanagement.modules.entity.result.card;

import java.util.Date;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

/**
 * 
* @ClassName: CardBox 
* @Description: TODO(装卡的盒子的信息) 
* @author huangtao
* @date 2017年7月7日 下午5:07:58 
*
 */
public class CardBoxVO extends BaseVO {
	private Integer id;//主键
	private Integer  cbid;//盒子编号
	private Integer  cfid;//层数
	private Integer  maxnum;//层数
	private Integer  cardnum;//卡数
	private String  createtime; //创建盒子的时间
	private String  updatetime;//更新盒子信息的时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCbid() {
		return cbid;
	}
	public void setCbid(Integer cbid) {
		this.cbid = cbid;
	}
	public Integer getCfid() {
		return cfid;
	}
	public void setCfid(Integer cfid) {
		this.cfid = cfid;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public Integer getMaxnum() {
		return maxnum;
	}
	public void setMaxnum(Integer maxnum) {
		this.maxnum = maxnum;
	}
	public Integer getCardnum() {
		return cardnum;
	}
	public void setCardnum(Integer cardnum) {
		this.cardnum = cardnum;
	}
	
}
