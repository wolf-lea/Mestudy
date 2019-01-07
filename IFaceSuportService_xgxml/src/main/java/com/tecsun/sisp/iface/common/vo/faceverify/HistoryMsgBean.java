package com.tecsun.sisp.iface.common.vo.faceverify;

import com.tecsun.sisp.iface.common.vo.SecQueryBean;

/**
 * Created by Sandwich on 2015/12/14.
 */
public class HistoryMsgBean extends SecQueryBean{
    private String idCard;
    private String token;
    private String verifyType;
    private String startTime;
    private String endTime;
    private String personId;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(String verifyType) {
        this.verifyType = verifyType;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}
    
    
}
