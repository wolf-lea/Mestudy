package com.tecsun.sisp.adapter.modules.card.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/*社保卡业务入参
 */
public class CardInfoBean extends SecQueryBean{


	private String sbkh; // 社保卡号

	public String getSbkh() {
		return sbkh;
	}

	public void setSbkh(String sbkh) {
		this.sbkh = sbkh;
	}
}