package com.tecsun.sisp.iface.common.vo.faceverify;

import com.tecsun.sisp.iface.common.vo.SecQueryBean;

/**
 * Created by Sandwich on 2015/12/15.
 */
public class RegistBean extends SecQueryBean{
    private String photo;    //单一照片
    private String name;     //姓名
    private String idCard;   //身份证
    private String type;//类型；
    
    private String ethnic;//民族
    private String birthday; //出生日期

	//三张照片-- base64
    private String base64String1;
    private String base64String2;
    private String base64String3;
    
    private String password; //密码
    public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	private String xb;//性别
	private String tel;//联系方式 ；
	private String address;//住址；
    
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

	private long registtype; //照片注册类型;用于照片一张或三张注册;1为一张、2为三张、3为德生宝采集
    
    private String token;
    private String aab001; //单位编号
    private String justCheck;//00只人工，01人工+人脸
    
    private String devicetype; //区分TSB-01 手机APP-02 微信-03
    
    private String aae279;//认证方式
    
    
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

    public String getAae279() {
		return aae279;
	}

	public void setAae279(String aae279) {
		this.aae279 = aae279;
	}

	public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

	public String getBase64String1() {
		return base64String1;
	}

	public void setBase64String1(String base64String1) {
		this.base64String1 = base64String1;
	}

	public String getBase64String2() {
		return base64String2;
	}

	public void setBase64String2(String base64String2) {
		this.base64String2 = base64String2;
	}

	public String getBase64String3() {
		return base64String3;
	}

	public void setBase64String3(String base64String3) {
		this.base64String3 = base64String3;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getRegisttype() {
		return registtype;
	}

	public void setRegisttype(long registtype) {
		this.registtype = registtype;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getAab001() {
		return aab001;
	}

	public void setAab001(String aab001) {
		this.aab001 = aab001;
	}

	public String getJustCheck() {
		return justCheck;
	}

	public void setJustCheck(String justCheck) {
		this.justCheck = justCheck;
	}

	public String getDevicetype() {
		return devicetype;
	}

	public void setDevicetype(String devicetype) {
		this.devicetype = devicetype;
	}
	
	   
    


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
}
