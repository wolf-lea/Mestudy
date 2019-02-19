package com.tecsun.sisp.iface.common.vo.faceverify;

/**
 * Created by Sandwich on 2015/12/12.
 */
public class XGPersonPO {
    private String id;//
    private String password;//密码
    private String person_name;//姓名
    private String role;//性别
    private String idCard;//身份证号
    private String sex;//性别
    private String company;//公司
    private String tel;//手机号
    private String address;//联系地址
    private String face_Status;//认证状态
    private String verify_time;//最近一次认证成功时间
    private String token;//登陆token
    private String isCheck;//是否人工审核 01未审核，0审核成功，1审核失败
    private String cj_sbPhotoPath;//采集的证件照

    private String ethnic;//名族
    public String getEthnic() {
		return ethnic;
	}

	public void setEthnic(String ethnic) {
		this.ethnic = ethnic;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	private String birthday;//出生日期
    
    public String getCj_sbPhotoPath() {
        return cj_sbPhotoPath;
    }

    public void setCj_sbPhotoPath(String cj_sbPhotoPath) {
        this.cj_sbPhotoPath = cj_sbPhotoPath;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPerson_name() {
        return person_name;
    }

    public void setPerson_name(String person_name) {
        this.person_name = person_name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getFace_Status() {
        return face_Status;
    }

    public void setFace_Status(String face_Status) {
        this.face_Status = face_Status;
    }

    public String getVerify_time() {
        return verify_time;
    }

    public void setVerify_time(String verify_time) {
        this.verify_time = verify_time;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getIsCheck() {
        return isCheck;
    }

    public void setIsCheck(String isCheck) {
        this.isCheck = isCheck;
    }
}
