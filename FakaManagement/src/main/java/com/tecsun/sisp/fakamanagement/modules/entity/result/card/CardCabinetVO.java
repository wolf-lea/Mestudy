package com.tecsun.sisp.fakamanagement.modules.entity.result.card;

import java.util.Date;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

/**
 * 
* @ClassName: CardCabinet 
* @Description: TODO(存放卡的柜子信息) 
* @author huangtao
* @date 2017年7月7日 下午5:15:37 
*
 */
public class CardCabinetVO extends BaseVO {
	private Integer id;//主键
	private String  ccid;//柜子名称
	private String  userid;//用户编号
	private String  createtime;//创建时间
	private String  updatetime;//更新时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
}
