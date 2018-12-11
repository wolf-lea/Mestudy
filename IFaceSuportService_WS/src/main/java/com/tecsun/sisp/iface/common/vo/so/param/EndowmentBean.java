package com.tecsun.sisp.iface.common.vo.so.param;

import com.tecsun.sisp.iface.common.vo.common.query.SecQueryBean;
/**
 * 养老保险查询
 * @author liang
 *
 */
public class EndowmentBean extends SecQueryBean{
	
	private String aae080;//实付年月
	private String aae117;//支付状态
	private String aae114;//缴费标志
	
	
	public String getAae114() {
		return aae114;
	}

	public void setAae114(String aae114) {
		this.aae114 = aae114;
	}

	public String getAae080() {
		return aae080;
	}

	public void setAae080(String aae080) {
		this.aae080 = aae080;
	}

	public String getAae117() {
		return aae117;
	}

	public void setAae117(String aae117) {
		this.aae117 = aae117;
	}

}
