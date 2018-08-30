package com.tecsun.sisp.adapter.modules.sbjf.service.impl.bank;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 * Created by linzetian on 2017/6/19.
 * 调银行接口请求入参
 */
public class BankReqMsg extends SecQueryBean {
    private String hexStr;// 请求银行完整的报文，德生宝提供
    private int auxiliaryType;// 辅助操作：0-无（业务本身）;1001-签到;1002-银行卡账户验证;1003-账单费用查询;1004-下载主密钥;1005-冲正;1006-缴费（查询）;1009-其他透传操作
    private String financialType;//业务类型:1001-余额查询;2001-社保缴费;3001-助农取款;9995-转账;9996-消费;9997-生活缴费;9998-信用卡还款;9999-手机充值;
    private int codeType;//编码类型 64位、128位

    public String getHexStr() {
        return hexStr;
    }

    public void setHexStr(String hexStr) {
        this.hexStr = hexStr;
    }

    public int getAuxiliaryType() {
        return auxiliaryType;
    }

    public void setAuxiliaryType(int auxiliaryType) {
        this.auxiliaryType = auxiliaryType;
    }

    public String getFinancialType() {
        return financialType;
    }

    public void setFinancialType(String financialType) {
        this.financialType = financialType;
    }

    public int getCodeType() {
        return codeType;
    }

    public void setCodeType(int codeType) {
        this.codeType = codeType;
    }
}
