package com.tecsun.sisp.fakamanagement.modules.entity.param.receive;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

/**
 * 
* @ClassName: QueryCardReceiveBean 
* @Description: TODO(查询领卡记录参数) 
* @author huangtao
* @date 2017年7月13日 下午2:43:27 
*
 */
public class QueryCardReceiveBean extends BaseVO {

    private Integer id;
	private String name;
	private String idcard;
	private String cardid;
	private String company_code;
	private Integer receivenum;
	private Integer status;
	private String type;
	private String userid;
	private Integer ciid;
    private String fkwd;
    private String orgid;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
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
	public String getCompany_code() {
		return company_code;
	}
	public void setCompany_code(String company_code) {
		this.company_code = company_code;
	}
	public Integer getReceivenum() {
		return receivenum;
	}
	public void setReceivenum(Integer receivenum) {
		this.receivenum = receivenum;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	/**
	 * @return the ciid
	 */
	public Integer getCiid() {
		return ciid;
	}
	/**
	 * @param ciid the ciid to set
	 */
	public void setCiid(Integer ciid) {
		this.ciid = ciid;
	}

    public String getFkwd() {
        return fkwd;
    }

    public void setFkwd(String fkwd) {
        this.fkwd = fkwd;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }
}
