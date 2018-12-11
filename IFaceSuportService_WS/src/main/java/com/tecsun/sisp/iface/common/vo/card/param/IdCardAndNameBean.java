package com.tecsun.sisp.iface.common.vo.card.param;

import com.tecsun.sisp.iface.common.vo.common.query.SecQueryBean;


/**
 * Created by DG on 2015/10/19.
 */
public class IdCardAndNameBean extends SecQueryBean{

	private String aab301 = "";//所属城市
	private String aac003;
    public String getAab301() {
		return aab301;
	}

	public void setAab301(String aab301) {
		this.aab301 = aab301;
	}


    public String getAac003() {
        return aac003;
    }

    public void setAac003(String aac003) {
        this.aac003 = aac003;
    }

}
