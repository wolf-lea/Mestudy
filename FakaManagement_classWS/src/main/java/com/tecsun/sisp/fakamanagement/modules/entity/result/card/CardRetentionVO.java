package com.tecsun.sisp.fakamanagement.modules.entity.result.card;

import java.util.Date;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

/**
 * 
* @ClassName: CardRetentionVO 
* @Description: TODO(问题卡登记信息) 
* @author huangtao
* @date 2017年7月14日 上午11:33:37 
*
 */
public class CardRetentionVO extends BaseVO {
	
	  private Integer id;
	  private Integer ciid;
	  private String name;
	  private String idcard;
	  private String cardid;
	  private String remark;//原因
	  private Date retentiontime;//登记时间
	  private String retentionuser;//登记人
	  private Date handletime;//处理时间
	  private String handle;//处理方式
	  private Integer status;//处理状态
	  private String userid;
	  
	  
	public Date getHandletime() {
		return handletime;
	}
	public void setHandletime(Date handletime) {
		this.handletime = handletime;
	}
	public String getHandle() {
		return handle;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getCiid() {
		return ciid;
	}
	public void setCiid(Integer ciid) {
		this.ciid = ciid;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getRetentiontime() {
		return retentiontime;
	}
	public void setRetentiontime(Date retentiontime) {
		this.retentiontime = retentiontime;
	}
	public String getRetentionuser() {
		return retentionuser;
	}
	public void setRetentionuser(String retentionuser) {
		this.retentionuser = retentionuser;
	}

}
