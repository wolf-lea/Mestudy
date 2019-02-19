package com.tecsun.sisp.net.modules.entity.response;

import java.util.List;


//6.5.1.1事项相关出参
public class ItemVo {
	
	private long  id;
	private String  sxmc; 
	private String  sxbm; 
	private String  sxlx; 
	private String  blbm; 
	private String  blsj; 
	private String  sfsf; 
	private String  bltj; 
	private String  sxsm; 
	private String  bslc;
	private List<InformationVo> informationVos;
	
	
	public List<InformationVo> getInformationVos() {
		return informationVos;
	}
	public void setInformationVos(List<InformationVo> informationVos) {
		this.informationVos = informationVos;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getSxmc() {
		return sxmc;
	}
	public void setSxmc(String sxmc) {
		this.sxmc = sxmc;
	}
	public String getSxbm() {
		return sxbm;
	}
	public void setSxbm(String sxbm) {
		this.sxbm = sxbm;
	}
	public String getSxlx() {
		return sxlx;
	}
	public void setSxlx(String sxlx) {
		this.sxlx = sxlx;
	}
	public String getBlbm() {
		return blbm;
	}
	public void setBlbm(String blbm) {
		this.blbm = blbm;
	}
	public String getBlsj() {
		return blsj;
	}
	public void setBlsj(String blsj) {
		this.blsj = blsj;
	}
	public String getSfsf() {
		return sfsf;
	}
	public void setSfsf(String sfsf) {
		this.sfsf = sfsf;
	}
	public String getBltj() {
		return bltj;
	}
	public void setBltj(String bltj) {
		this.bltj = bltj;
	}
	public String getSxsm() {
		return sxsm;
	}
	public void setSxsm(String sxsm) {
		this.sxsm = sxsm;
	}
	public String getBslc() {
		return bslc;
	}
	public void setBslc(String bslc) {
		this.bslc = bslc;
	} 
	
	
	
	
	
	
	

}
