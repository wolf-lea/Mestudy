package com.tecsun.sisp.adapter.modules.card.entity.request;


import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 */
public class CsspApplyBean extends SecQueryBean {
    private long applyId;//
    private long personId;//
    private String birthday;//出生日期
    private String sex;//性别
    private String namePinyin;//姓名拼音
    private String personType;//人员类别 1：城镇职工 2：城镇居民 3：农村居民 9：其他
    private String nation;//民族
    private String certType;//证件类型 A身份证  B临时身份证 C户口本 D军官证 E士兵证 F警官证 G港澳通行证 H护照
    private String guoji;//国籍
    private String isForeigner;//是否是外国人 1-是，0否
    private String mobile;//手机号码
    private String phone;//固定电话
    private String domicile;//户籍地址
    private String certIssuingOrg;//签发机关
    private String address;//联系地址
    private String tsbAddress;//领卡地址
    private String certValidity;//证件有效期 截止时间yyyyMMdd/长期
    private String bankCode;//银行编码
    private String bankName;//银行名称
    private String personStatus;//人员状态 1:就业 2:离休 3: 退休 4:退职 5:失业 6:无业 7:从未就业 8:其他
    private String accountProties;//户口性质 11:本省非农业;12:外省非农业;21:本省农业;22:外省农业;30:台港澳人员;40:外籍人士;
    private String zipCode;//邮编
    private String agentCertNo;//监护人证件号码
    private String agentCertType;//监护证件类型(同证件类型)
    private String agentPhone;//监护人联系电话
    private String agentName;//监护人姓名
    private String scannPhoto;//电子申领表
    private String isUpload;//是否已经被上传成功Y 表示上传成功 N  表示尚未上传或失败
    private String uploadtime;//上传时间
    private String photoSource;//01代表相片无需处理（如来自公安库） 02代表相片需（等待）处理（如自拍）
    private long photoBuzId;//参保人相片ID
    private long picupId;//身份证正面id
    private long picdownId;//身份证反面id
    private long signphotoId;//身份证签名id
    private long accountphotoId;//未成年人户口本照片id
    private String remark;//备注
    private String createTime;
    private String photo64String;//参保人相片64编码string
    private String sign64String;//电子签名64编码string
    private String idcard64StringUp;//身份证正面64编码string
    private String idcard64StringDown;//身份证反面64编码string
    private String account64String;//未成年人户口本照片64编码string
    private String companyNo;//单位编码
    private String companyName;//单位名称
    private long uploadNum;//上传次数
    private String addrType;//领卡方式：1：快递邮寄；2：到网点领取；3：德生宝所在地领取
    private String cardAddress;//领卡地址（邮寄地址、网点地址）
    private String cardAddressShort;//地址简称或网点
    private String addrOrgCode;//网点编码
    private String addrPhone;//收件人手机号
    private String addressee;//收件人姓名
    private long applyAddrId;//领卡地址表ID
    private String distinctCode;//区县编码
    private String orgCode;//网点编码
    private byte[] photo64Byte;//参保人相片64编码Byte
    private byte[] idcard64ByteUp;//身份证正面64编码Byte
    private byte[] idcard64ByteDown;//身份证反面64编码Byte
    private byte[] account64Byte;//未成年人户口本照片64编码Byte

    public long getApplyAddrId() {
        return applyAddrId;
    }

    public void setApplyAddrId(long applyAddrId) {
        this.applyAddrId = applyAddrId;
    }

    public String getAddrType() {
        return addrType;
    }

    public void setAddrType(String addrType) {
        this.addrType = addrType;
    }

    public String getCardAddress() {
        return cardAddress;
    }

    public void setCardAddress(String cardAddress) {
        this.cardAddress = cardAddress;
    }

    public String getCardAddressShort() {
        return cardAddressShort;
    }

    public void setCardAddressShort(String cardAddressShort) {
        this.cardAddressShort = cardAddressShort;
    }

    public String getAddrOrgCode() {
        return addrOrgCode;
    }

    public void setAddrOrgCode(String addrOrgCode) {
        this.addrOrgCode = addrOrgCode;
    }

    public String getAddrPhone() {
        return addrPhone;
    }

    public void setAddrPhone(String addrPhone) {
        this.addrPhone = addrPhone;
    }

    public String getAddressee() {
        return addressee;
    }

    public void setAddressee(String addressee) {
        this.addressee = addressee;
    }

    public String getCertType() {
        return certType;
    }

    public void setCertType(String certType) {
        this.certType = certType;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getNamePinyin() {
        return namePinyin;
    }

    public void setNamePinyin(String namePinyin) {
        this.namePinyin = namePinyin;
    }

    public long getApplyId() {
        return applyId;
    }

    public void setApplyId(long applyId) {
        this.applyId = applyId;
    }

    public long getPersonId() {
        return personId;
    }

    public void setPersonId(long personId) {
        this.personId = personId;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPersonType() {
        return personType;
    }

    public void setPersonType(String personType) {
        this.personType = personType;
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getGuoji() {
        return guoji;
    }

    public void setGuoji(String guoji) {
        this.guoji = guoji;
    }

    public String getIsForeigner() {
        return isForeigner;
    }

    public void setIsForeigner(String isForeigner) {
        this.isForeigner = isForeigner;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDomicile() {
        return domicile;
    }

    public void setDomicile(String domicile) {
        this.domicile = domicile;
    }

    public String getCertIssuingOrg() {
        return certIssuingOrg;
    }

    public void setCertIssuingOrg(String certIssuingOrg) {
        this.certIssuingOrg = certIssuingOrg;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTsbAddress() {
        return tsbAddress;
    }

    public void setTsbAddress(String tsbAddress) {
        this.tsbAddress = tsbAddress;
    }

    public String getCertValidity() {
        return certValidity;
    }

    public void setCertValidity(String certValidity) {
        this.certValidity = certValidity;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getPersonStatus() {
        return personStatus;
    }

    public void setPersonStatus(String personStatus) {
        this.personStatus = personStatus;
    }

    public String getAccountProties() {
        return accountProties;
    }

    public void setAccountProties(String accountProties) {
        this.accountProties = accountProties;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getAgentCertNo() {
        return agentCertNo;
    }

    public void setAgentCertNo(String agentCertNo) {
        this.agentCertNo = agentCertNo;
    }

    public String getAgentCertType() {
        return agentCertType;
    }

    public void setAgentCertType(String agentCertType) {
        this.agentCertType = agentCertType;
    }

    public String getAgentPhone() {
        return agentPhone;
    }

    public void setAgentPhone(String agentPhone) {
        this.agentPhone = agentPhone;
    }

    public String getScannPhoto() {
        return scannPhoto;
    }

    public void setScannPhoto(String scannPhoto) {
        this.scannPhoto = scannPhoto;
    }

    public String getIsUpload() {
        return isUpload;
    }

    public void setIsUpload(String isUpload) {
        this.isUpload = isUpload;
    }

    public String getUploadtime() {
        return uploadtime;
    }

    public void setUploadtime(String uploadtime) {
        this.uploadtime = uploadtime;
    }

    public String getPhotoSource() {
        return photoSource;
    }

    public void setPhotoSource(String photoSource) {
        this.photoSource = photoSource;
    }

    public long getPhotoBuzId() {
        return photoBuzId;
    }

    public void setPhotoBuzId(long photoBuzId) {
        this.photoBuzId = photoBuzId;
    }

    public long getPicupId() {
        return picupId;
    }

    public void setPicupId(long picupId) {
        this.picupId = picupId;
    }

    public long getPicdownId() {
        return picdownId;
    }

    public void setPicdownId(long picdownId) {
        this.picdownId = picdownId;
    }

    public long getSignphotoId() {
        return signphotoId;
    }

    public void setSignphotoId(long signphotoId) {
        this.signphotoId = signphotoId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

   
    public String getSign64String() {
        return sign64String;
    }

    public void setSign64String(String sign64String) {
        this.sign64String = sign64String;
    }

    public String getCompanyNo() {
        return companyNo;
    }

    public void setCompanyNo(String companyNo) {
        this.companyNo = companyNo;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public long getUploadNum() {
        return uploadNum;
    }

    public void setUploadNum(long uploadNum) {
        this.uploadNum = uploadNum;
    }

	public String getAgentName() {
		return agentName;
	}

	public void setAgentName(String agentName) {
		this.agentName = agentName;
	}

	public String getDistinctCode() {
		return distinctCode;
	}

	public void setDistinctCode(String distinctCode) {
		this.distinctCode = distinctCode;
	}

	public long getAccountphotoId() {
		return accountphotoId;
	}

	public void setAccountphotoId(long accountphotoId) {
		this.accountphotoId = accountphotoId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getPhoto64String() {
		return photo64String;
	}

	public void setPhoto64String(String photo64String) {
		this.photo64String = photo64String;
	}

	public String getIdcard64StringUp() {
		return idcard64StringUp;
	}

	public void setIdcard64StringUp(String idcard64StringUp) {
		this.idcard64StringUp = idcard64StringUp;
	}

	public String getIdcard64StringDown() {
		return idcard64StringDown;
	}

	public void setIdcard64StringDown(String idcard64StringDown) {
		this.idcard64StringDown = idcard64StringDown;
	}

	public String getAccount64String() {
		return account64String;
	}

	public void setAccount64String(String account64String) {
		this.account64String = account64String;
	}

	public byte[] getPhoto64Byte() {
		return photo64Byte;
	}

	public void setPhoto64Byte(byte[] photo64Byte) {
		this.photo64Byte = photo64Byte;
	}

	public byte[] getIdcard64ByteUp() {
		return idcard64ByteUp;
	}

	public void setIdcard64ByteUp(byte[] idcard64ByteUp) {
		this.idcard64ByteUp = idcard64ByteUp;
	}

	public byte[] getIdcard64ByteDown() {
		return idcard64ByteDown;
	}

	public void setIdcard64ByteDown(byte[] idcard64ByteDown) {
		this.idcard64ByteDown = idcard64ByteDown;
	}

	public byte[] getAccount64Byte() {
		return account64Byte;
	}

	public void setAccount64Byte(byte[] account64Byte) {
		this.account64Byte = account64Byte;
	}

	
	

	
    
    
}