package com.tecsun.sisp.adapter.modules.so.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

public class HospitalQueryBean extends SecQueryBean{
	
	private String jgmc;	//机构等级

	/**
	 * @return the jgmc
	 */
	public String getJgmc() {
		return jgmc;
	}

	/**
	 * @param jgmc the jgmc to set
	 */
	public void setJgmc(String jgmc) {
		this.jgmc = jgmc;
	}

	
}