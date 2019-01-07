package com.tecsun.sisp.adapter.modules.collection.entity.request;

/**
 * 
 * 全民采集bean
 * 
 * @author 菜鸟
 * 
 */
public class CollectionBean {

	private String deviceid;// 设备ID
	private String certNum;// 证件号
	private String name;// 姓名
	private String sex;// 性别
	private String nation;// 民族
	private String guoji;// 国籍
	private String mobile;// 手机号
	private String address;// 户籍地址
	private String photo;// 个人照片
	private String certValidity;// 证件有效期
	private String bankName;// 银行名称
	private String idcard64StringUp;// 身份证正面64编码
	private String idcard64StringDown;// 身份证反面64编码
	private String adultFlag;// 成年人标志，1_成年人，0_未成年人
	private String phone;// 固定电话和手机号码必填一项
	private String regionalCode;// 区域编码
	private String hkProperty;// 户口性质
	private String hkbNo;// 户口编号
	private String familyNo;// 家庭编号
	private String cbPlace;// 参保地
	private String fkPlace;// 发卡地
	private String parmanentAddress;// 常住地所在地址
	private String certType;// 证件类型（身份证等）
	private String bussType;// 业务类型（01个人申领等）
	private String gis;// GIS定位信息
	private String personNo;// 个人编号
	private String politicalStatus;// 政治面貌（1群众，2党员，3团员，4民主党派）
	private String isMarry;// 婚姻状态
	private String education;// 文化水平
	private String personStatus;// 个人身份
	private String hkType;// 户口类型
	private String guardianName;// 监护人姓名
	private String guardianCertNo;// 监护人证件号码
	private String guardianContact;// 监护人联系方式
	private String dealStatus;// 数据处理状态
	private String isbaby;// 婴儿卡标记
	private String synchroStatus;// 同步状态
	private String insuredSituation;// 参保情况
	private String noInsuredReason;// 未参保原因
	private String jobSituation;// 就业情况
	private String departmentNo;// 单位编号
	private String departmentName;// 单位名称
	private String personInfo;// 人员基本情况

	private long idcardPicIdUp; // 身份证正面照片ID
	private long idcardPicIdDown;// 身份证反面照片ID
	private long photoId; // 个人照片ID
	private String noJobReason; // 未就业原因

	private String isExpress;// 是否邮寄00否,01是
	private String expressName;// 收件人姓名
	private String expressPhone;// 收件人电话
	private String expressAddress;// 收件人地址

	public String getDeviceid() {
		return deviceid;
	}

	public void setDeviceid(String deviceid) {
		this.deviceid = deviceid;
	}

	public String getCertNum() {
		return certNum;
	}

	public void setCertNum(String certNum) {
		this.certNum = certNum;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getGuoji() {
		return guoji;
	}

	public void setGuoji(String guoji) {
		this.guoji = guoji;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String getCertValidity() {
		return certValidity;
	}

	public void setCertValidity(String certValidity) {
		this.certValidity = certValidity;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
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

	public String getAdultFlag() {
		return adultFlag;
	}

	public void setAdultFlag(String adultFlag) {
		this.adultFlag = adultFlag;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegionalCode() {
		return regionalCode;
	}

	public void setRegionalCode(String regionalCode) {
		this.regionalCode = regionalCode;
	}

	public String getHkProperty() {
		return hkProperty;
	}

	public void setHkProperty(String hkProperty) {
		this.hkProperty = hkProperty;
	}

	public String getHkbNo() {
		return hkbNo;
	}

	public void setHkbNo(String hkbNo) {
		this.hkbNo = hkbNo;
	}

	public String getFamilyNo() {
		return familyNo;
	}

	public void setFamilyNo(String familyNo) {
		this.familyNo = familyNo;
	}

	public String getCbPlace() {
		return cbPlace;
	}

	public void setCbPlace(String cbPlace) {
		this.cbPlace = cbPlace;
	}

	public String getFkPlace() {
		return fkPlace;
	}

	public void setFkPlace(String fkPlace) {
		this.fkPlace = fkPlace;
	}

	public String getParmanentAddress() {
		return parmanentAddress;
	}

	public void setParmanentAddress(String parmanentAddress) {
		this.parmanentAddress = parmanentAddress;
	}

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getBussType() {
		return bussType;
	}

	public void setBussType(String bussType) {
		this.bussType = bussType;
	}

	public String getGis() {
		return gis;
	}

	public void setGis(String gis) {
		this.gis = gis;
	}

	public String getPersonNo() {
		return personNo;
	}

	public void setPersonNo(String personNo) {
		this.personNo = personNo;
	}

	public String getPoliticalStatus() {
		return politicalStatus;
	}

	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}

	public String getIsMarry() {
		return isMarry;
	}

	public void setIsMarry(String isMarry) {
		this.isMarry = isMarry;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getPersonStatus() {
		return personStatus;
	}

	public void setPersonStatus(String personStatus) {
		this.personStatus = personStatus;
	}

	public String getHkType() {
		return hkType;
	}

	public void setHkType(String hkType) {
		this.hkType = hkType;
	}

	public String getGuardianName() {
		return guardianName;
	}

	public void setGuardianName(String guardianName) {
		this.guardianName = guardianName;
	}

	public String getGuardianCertNo() {
		return guardianCertNo;
	}

	public void setGuardianCertNo(String guardianCertNo) {
		this.guardianCertNo = guardianCertNo;
	}

	public String getGuardianContact() {
		return guardianContact;
	}

	public void setGuardianContact(String guardianContact) {
		this.guardianContact = guardianContact;
	}

	public String getDealStatus() {
		return dealStatus;
	}

	public void setDealStatus(String dealStatus) {
		this.dealStatus = dealStatus;
	}

	public String getIsbaby() {
		return isbaby;
	}

	public void setIsbaby(String isbaby) {
		this.isbaby = isbaby;
	}

	public String getSynchroStatus() {
		return synchroStatus;
	}

	public void setSynchroStatus(String synchroStatus) {
		this.synchroStatus = synchroStatus;
	}

	public String getInsuredSituation() {
		return insuredSituation;
	}

	public void setInsuredSituation(String insuredSituation) {
		this.insuredSituation = insuredSituation;
	}

	public String getNoInsuredReason() {
		return noInsuredReason;
	}

	public void setNoInsuredReason(String noInsuredReason) {
		this.noInsuredReason = noInsuredReason;
	}

	public String getJobSituation() {
		return jobSituation;
	}

	public void setJobSituation(String jobSituation) {
		this.jobSituation = jobSituation;
	}

	public String getDepartmentNo() {
		return departmentNo;
	}

	public void setDepartmentNo(String departmentNo) {
		this.departmentNo = departmentNo;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public String getPersonInfo() {
		return personInfo;
	}

	public void setPersonInfo(String personInfo) {
		this.personInfo = personInfo;
	}

	public long getIdcardPicIdUp() {
		return idcardPicIdUp;
	}

	public void setIdcardPicIdUp(long idcardPicIdUp) {
		this.idcardPicIdUp = idcardPicIdUp;
	}

	public long getIdcardPicIdDown() {
		return idcardPicIdDown;
	}

	public void setIdcardPicIdDown(long idcardPicIdDown) {
		this.idcardPicIdDown = idcardPicIdDown;
	}

	public long getPhotoId() {
		return photoId;
	}

	public void setPhotoId(long photoId) {
		this.photoId = photoId;
	}

	public String getNoJobReason() {
		return noJobReason;
	}

	public void setNoJobReason(String noJobReason) {
		this.noJobReason = noJobReason;
	}

	public String getIsExpress() {
		return isExpress;
	}

	public void setIsExpress(String isExpress) {
		this.isExpress = isExpress;
	}

	public String getExpressName() {
		return expressName;
	}

	public void setExpressName(String expressName) {
		this.expressName = expressName;
	}

	public String getExpressPhone() {
		return expressPhone;
	}

	public void setExpressPhone(String expressPhone) {
		this.expressPhone = expressPhone;
	}

	public String getExpressAddress() {
		return expressAddress;
	}

	public void setExpressAddress(String expressAddress) {
		this.expressAddress = expressAddress;
	}

}
