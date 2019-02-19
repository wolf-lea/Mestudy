package com.tecsun.sisp.iface.common.vo.faceverify;

import com.tecsun.sisp.iface.common.vo.SecQueryBean;

/**
 * Created by Sandwich on 2015/12/11.
 */
public class PersonLoginBean extends SecQueryBean {
	private String idCard; // 身份证
	private String password;// 密码
	private String oldpassword;// 原始密码
	private String name; // 用户名
	private String photo;// base64Str
	private int pageno;
	private int pagesize;
	private String grbh;// 个人编号

	private int loginType; // 登陆的类别，1是使用身份证+姓名+密码，2是使用身份证+姓名+照片 进行登陆

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

	public String getPassword() {
		return password;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getPageno() {
		return pageno;
	}

	public void setPageno(int pageno) {
		this.pageno = pageno;
	}

	public int getPagesize() {
		return pagesize;
	}

	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	public String getGrbh() {
		return grbh;
	}

	public void setGrbh(String grbh) {
		this.grbh = grbh;
	}

	public int getLoginType() {
		return loginType;
	}

	public void setLoginType(int loginType) {
		this.loginType = loginType;
	}

	public String getOldpassword() {
		return oldpassword;
	}

	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}

}
