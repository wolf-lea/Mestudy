package com.tecsun.sisp.fakamanagement.modules.entity.result.card;

import java.util.Date;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

/**
 * 
* @ClassName: CardFloor 
* @Description: TODO(放盒子的柜层信息) 
* @author huangtao
* @date 2017年7月7日 下午5:13:27 
*
 */
public class CardFloorVO extends BaseVO {
	private Integer id;//主键
	private Integer  ccid;//柜子编号
	private Integer  cfid;//层数
	private String  createtime;//创建时间
	private String  updatetime;//更新时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	public Integer getCcid() {
		return ccid;
	}
	public void setCcid(Integer ccid) {
		this.ccid = ccid;
	}
	
}
