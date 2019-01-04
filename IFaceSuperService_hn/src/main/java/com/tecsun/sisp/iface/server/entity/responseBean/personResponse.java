package com.tecsun.sisp.iface.server.entity.responseBean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @title 人员查询出参
 * @author zengyunhua
 * @2019年1月3日-上午9:47:10
 * @version2019
 */
@Setter
@Getter
@ToString
public class personResponse {
	 private String nowTime;//当前时间
	 private String model;//模型描述
	 private String organization;//所属机构
	 private String sbkh;//社会保障号码
	 private String name;//姓名
	 private String sex;
	 private String birthDay;//出生日期
	 private String phone;//移动电话
	 private String tel;//固定电话
	 private String unitNum;//单位编号
	 private String unitName;//单位名称
	 private String authenticationTime;//认证时间
	 private String authenticationMethod;//认证方式
	 
	

}
