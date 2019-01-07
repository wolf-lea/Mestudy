package com.tecsun.sisp.adapter.modules.common.entity.request;

/**
 * 第三方登陆返回信息
 * @author tan
 *
 */
public class ThirdUserBean {

	private long id;// 用户id
	private long areaid;// 用户区域

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getAreaid() {
		return areaid;
	}

	public void setAreaid(long areaid) {
		this.areaid = areaid;
	}

}
