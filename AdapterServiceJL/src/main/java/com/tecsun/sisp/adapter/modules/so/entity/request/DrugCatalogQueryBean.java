package com.tecsun.sisp.adapter.modules.so.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

public class DrugCatalogQueryBean extends SecQueryBean {
	
	private String ypbm;	//药品编码
	
	private String zwmc;	//中文名称

	/**
	 * @return the ypbm
	 */
	public String getYpbm() {
		return ypbm;
	}

	/**
	 * @param ypbm the ypbm to set
	 */
	public void setYpbm(String ypbm) {
		this.ypbm = ypbm;
	}

	/**
	 * @return the zwmc
	 */
	public String getZwmc() {
		return zwmc;
	}

	/**
	 * @param zwmc the zwmc to set
	 */
	public void setZwmc(String zwmc) {
		this.zwmc = zwmc;
	}
	
	
	

}
