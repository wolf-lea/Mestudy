package com.tecsun.sisp.adapter.modules.account.entity.response;

/**账号信息
 * Created by danmeng on 2017/5/5.
 */
public class AccountVO {
    private String accountId;//用户账号
    private String accountName;//用户名
    private String accountPwd;//密码
    private String phone;//手机号
    private String sfzh;//身份证号
    private String sex;//性别01男性;02女性;03未知
    private String nation;//民族（中文，如：汉）
    private String address;//通讯地址
    private String company;//单位名称
    private String status;//状态:1=有效（默认），0=无效
    private String roleCode;//角色编码
    private String description;//描述
    private String openid;//微信绑定号
    private String alipayId;//支付宝绑定号
    private String channelcode;//注册渠道
    private String pic;//
    
    private String isWechat;//微信人脸比对是否通过，1=通过，0=不通过
    private String comparePic;//微信比对照片
    private String createTime;
    private String cardPic;//卡管照片

    public String getChannelcode() {
        return channelcode;
    }

    public void setChannelcode(String channelcode) {
        this.channelcode = channelcode;
    }

    public String getAlipayId() {
        return alipayId;
    }

    public void setAlipayId(String alipayId) {
        this.alipayId = alipayId;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }

    public String getRoleCode() {
        return roleCode;
    }

    public void setRoleCode(String roleCode) {
        this.roleCode = roleCode;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getAccountPwd() {
        return accountPwd;
    }

    public void setAccountPwd(String accountPwd) {
        this.accountPwd = accountPwd;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }

	public String getIsWechat() {
		return isWechat;
	}

	public void setIsWechat(String isWechat) {
		this.isWechat = isWechat;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getComparePic() {
		return comparePic;
	}

	public void setComparePic(String comparePic) {
		this.comparePic = comparePic;
	}

	public String getCardPic() {
		return cardPic;
	}

	public void setCardPic(String cardPic) {
		this.cardPic = cardPic;
	}
    
    
}
