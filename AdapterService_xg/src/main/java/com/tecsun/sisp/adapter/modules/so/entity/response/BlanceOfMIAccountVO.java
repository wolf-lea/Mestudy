package com.tecsun.sisp.adapter.modules.so.entity.response;

import com.tecsun.sisp.adapter.common.annotation.Field;
import com.tecsun.sisp.adapter.common.util.CommUtil;

public class BlanceOfMIAccountVO {
    @Field(empty="无")
    private    String  xm; 	//姓名
    @Field(empty="无")
    private    String  sfzh;//身份证号
	@Field(empty="0")
	private String poolAccount; //	医保统筹账户金额
	@Field(empty="0")
	private String individualAccount; //	医保个人账户金额

    public String getXm(){
        return xm;
    }

    public void setXm(String xm) {
        this.xm = xm;
    }

    public String getSfzh() {
        return sfzh;
    }

    public void setSfzh(String sfzh) {
        this.sfzh = sfzh;
    }
	public String getPoolAccount() {
		if(poolAccount == null || poolAccount.isEmpty())
			return poolAccount;
		
		return CommUtil.formatNumberic(poolAccount);
	}

    public String getIndividualAccount() {
		if(individualAccount == null || individualAccount.isEmpty())
			return individualAccount;

		return CommUtil.formatNumberic(individualAccount);
	}

    public void setPoolAccount(String poolAccount) {
        this.poolAccount = poolAccount;
    }

    public void setIndividualAccount(String individualAccount) {
        this.individualAccount = individualAccount;
    }
}