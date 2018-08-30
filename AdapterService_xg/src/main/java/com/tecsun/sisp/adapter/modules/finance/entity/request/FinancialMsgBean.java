package com.tecsun.sisp.adapter.modules.finance.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**
 * Created by danmeng on 2017/6/5.
 */
public class FinancialMsgBean extends SecQueryBean{
    private String hexStr;// 请求银行完整的报文，德生宝提供
    private int auxiliaryType;// 辅助操作：0-无（业务本身）;1001-签到;1002-银行卡账户验证;1003-账单费用查询;1004-下载主密钥;1005-冲正;1006-缴费（查询）;1009-其他透传操作
    private String financialType;//业务类型:remaining-余额查询;credit-信用卡还款;mobile-手机充值;payment-缴费;sb_payment-社保缴费;sh_payment-生活缴费;zn_withdrawals-助农取款;transfer-转账;consume-消费;
    private int codeType;//编码类型 64域、128域

    public String getFinancialType() {
        return financialType;
    }

    public void setFinancialType(String financialType) {
        this.financialType = financialType;
    }

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

    public int getCodeType() {
        return codeType;
    }

    public void setCodeType(int codeType) {
        this.codeType = codeType;
    }
}
