package com.tecsun.sisp.adapter.modules.so.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

public class PaymentQueryBean extends SecQueryBean {

	//起始年月
	private String ksny;
	//结束年月
	private String jsny;
	
	public String getKsny() {
		return ksny;
	}
	public void setKsny(String ksny) {
		this.ksny = ksny;
	}
	public String getJsny() {
		return jsny;
	}
	public void setJsny(String jsny) {
		this.jsny = jsny;
	}
	
}
