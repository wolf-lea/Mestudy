package com.tecsun.sisp.fakamanagement.modules.entity.param.receive;

import com.tecsun.sisp.fakamanagement.modules.entity.BaseVO;

/**
 * 
* @ClassName: QueryCardReceiveBean 
* @Description: TODO(查询领卡记录参数) 
* @author huangtao
* @date 2017年7月13日 下午2:43:27 
*
 */
public class QuerySpeedOfCardBean extends BaseVO {
	
	private String name;
	private String idcard;
	private String reginal;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	/**
	 * @return the reginal
	 */
	public String getReginal() {
		return reginal;
	}
	/**
	 * @param reginal the reginal to set
	 */
	public void setReginal(String reginal) {
		this.reginal = reginal;
	}
	

}
