package com.tecsun.sisp.fakamanagement.modules.entity.result.receive;

import java.util.Date;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;


/**
 * 
* @ClassName: CardReceiveLogVO 
* @Description: TODO(领卡记录查询信息) 
* @author huangtao
* @date 2017年7月13日 下午3:06:35 
*
 */
public class CardReceiveLogVO extends BaseVO {

    private Integer id;
	private String agentname;// 领卡人
	private String cardid;// 社保卡号
	private String companyname;// 单位名称
	private Date receivetime;// 领取时间
	private String photo;// 个人照片
	private String idcardphotoup;// 个人身份证正面
	private String idcardphotodown;// 个人身份证反面
	private String signphoto;// 电子签字
	private String name;// 姓名
	private String idcard;// 身份证号码
	private String phone;// 联系电话
	private String agentidcard;// 
	private Integer receivenum;
	private String status;
	private String type;
	private String oldcfwz;
    private String domiciliaryregisterphoto;// 户口本照片

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAgentidcard() {
		return agentidcard;
	}

	public void setAgentidcard(String agentidcard) {
		this.agentidcard = agentidcard;
	}

	public Date getReceivetime() {
		return receivetime;
	}

	public void setReceivetime(Date receivetime) {
		this.receivetime = receivetime;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getIdcardphotoup() {
		return idcardphotoup;
	}

	public void setIdcardphotoup(String idcardphotoup) {
		this.idcardphotoup = idcardphotoup;
	}

	public String getIdcardphotodown() {
		return idcardphotodown;
	}

	public void setIdcardphotodown(String idcardphotodown) {
		this.idcardphotodown = idcardphotodown;
	}

	public String getSignphoto() {
		return signphoto;
	}

	public void setSignphoto(String signphoto) {
		this.signphoto = signphoto;
	}

	public String getAgentname() {
		return agentname;
	}

	public void setAgentname(String agentname) {
		this.agentname = agentname;
	}

	public String getCardid() {
		return cardid;
	}

	public void setCardid(String cardid) {
		this.cardid = cardid;
	}

	public String getCompanyname() {
		return companyname;
	}

	public void setCompanyname(String companyname) {
		this.companyname = companyname;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getReceivenum() {
		return receivenum;
	}

	public void setReceivenum(Integer receivenum) {
		this.receivenum = receivenum;
	}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOldcfwz() {
        return oldcfwz;
    }

    public void setOldcfwz(String oldcfwz) {
        this.oldcfwz = oldcfwz;
    }

    public String getDomiciliaryregisterphoto() {
        return domiciliaryregisterphoto;
    }

    public void setDomiciliaryregisterphoto(String domiciliaryregisterphoto) {
        this.domiciliaryregisterphoto = domiciliaryregisterphoto;
    }
}
