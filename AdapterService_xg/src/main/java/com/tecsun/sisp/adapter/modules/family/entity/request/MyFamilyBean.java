package com.tecsun.sisp.adapter.modules.family.entity.request;

import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
import org.apache.commons.lang3.StringUtils;

/**
 * @author wunuanchan
 * @version
 */

public class MyFamilyBean extends SecQueryBean {

	private String name;// 姓名
	private String relationship;// 与关联账户所属人关系
	private String sex;// 性别
	private String sfzh;// 身份证号码
	private String phone;// 联系电话
	private String masterSfzh;// 关联账户所属人身份证号码
	private String id;//
	private String isDelete;//是否删除(0：是  1：否（默认1）)

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getSex() {
		String _sex = Constants.FAMILY_SEX_STATUS.get(sex);
		return StringUtils.isNotEmpty(_sex) ? _sex :sex ;
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMasterSfzh() {
		return masterSfzh;
	}

	public void setMasterSfzh(String masterSfzh) {
		this.masterSfzh = masterSfzh;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}
}
