package com.tecsun.sisp.adapter.modules.family.entity.response;
/**
* @author  wunuanchan
* @version 
* 创建时间：2017年6月2日 下午4:18:19
* 说明：
*/

public class FamilyOwnerBean {

	private String accountName;//姓名
	private String sex;//性别
	private String sfzh;//身份证号码
	private String phone;//联系电话
	private String count;//家庭成员数

	private String photo;//头像
	private String tokenid;//权限验证码
	
	public String getAccountName() {
		return accountName;
	}
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getSfzh() {
		return sfzh;
	}
	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getCount() {
		return count;
	}
	public void setCount(String count) {
		this.count = count;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public String getTokenid() {
		return tokenid;
	}
	public void setTokenid(String tokenid) {
		this.tokenid = tokenid;
	}
	

}
