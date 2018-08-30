package com.tecsun.sisp.adapter.modules.ine.entity.response;


/**岗位信息vo
 * @author sipeng
 * @email zsp_banyue@163.com
 * @date 2017年10月26日
 */
public class PositionVo {
	
	private String pCode;// 岗位代码 001   001001 001002
//	private String level;//岗位级别
	private String type;//岗位类别
	private String pName;// 岗位名称
//	private Date createTime;//创建时间
//	private Date updateTime;//更新时间
	private String parentCode;//父级代码
//	private Boolean isValid ; //是否有效
	public String getpCode() {
		return pCode;
	}
	public void setpCode(String pCode) {
		this.pCode = pCode;
	}
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getParentCode() {
		return parentCode;
	}
	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}
	

	
	
}
