package com.tecsun.sisp.net.modules.service.business;

import java.util.List;
import java.util.Map;

import com.tecsun.sisp.net.modules.entity.business.QueryVo;


public interface QueryService {

	/**
	 * 个人实缴记录单查询
	 * @param grbh 身份证号
	 * @return
	 */
	public Map<String, Object> getGrsjjl(QueryVo qv);
	
	/**
	 * 个人参保证明查询
	 * @param grbh 身份证号
	 * @return
	 */
	public List<Map<String,Object>> getGrcbzm(String idcard);
	
	
	/**
	 * 利用query包装对象去分页查询
	 * @param qv
	 * @return
	 */
	public Map<String, Object> getGrylbxnzhxx(QueryVo qv);
	
	/**
	 * 灵活就业人员缴费基数查询
	 * @param grbh 身份证号
	 * @return
	 */
	public Map<String,Object> getLhjyryjfjs(QueryVo qv);

	/**
	 * 根据身份证号码查询个人编号
	 * @param xz 险种
	 * @param grbh
	 * @return
	 */
	public List<String> getGrbh(String idcard, String xz);




}
