package com.tecsun.sisp.iface.common.vo.common.param;

/**
 * Created by DG on 2015/9/27.
 */
public class LoginBean {

	private String channelcode; // 渠道类型：网办，德生宝，自助终端

	private String username; // 用户名

	private String password;// 密码

	public String getChannelcode() {
		return channelcode;
	}

	public void setChannelcode(String channelcode) {
		this.channelcode = channelcode;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
