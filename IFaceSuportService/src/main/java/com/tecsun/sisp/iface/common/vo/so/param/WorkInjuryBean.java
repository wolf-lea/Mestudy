package com.tecsun.sisp.iface.common.vo.so.param;

import com.tecsun.sisp.iface.common.vo.common.query.SecQueryBean;
/**
 * 工伤保险查询
 * @author liang
 *
 */
public class WorkInjuryBean extends SecQueryBean{
	private String aae080;//实付年月
	private String aae117;//支付状态
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
