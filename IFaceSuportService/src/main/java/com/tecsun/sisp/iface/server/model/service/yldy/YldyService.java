package com.tecsun.sisp.iface.server.model.service.yldy;

import com.tecsun.sisp.iface.common.vo.Result;

public interface YldyService {

	/**
	 * 养老待遇个性化测算
	 * @param idcard 身份证号
	 * @param aic161  退休类别
	 * @param aic162  预计退休日期
	 * @param jfdc  灵活就业人员预估缴费档次
	 * @return
	 */
	Result getGxhcs(String idcard, String aic161, String aic162, String jfdc);

}
