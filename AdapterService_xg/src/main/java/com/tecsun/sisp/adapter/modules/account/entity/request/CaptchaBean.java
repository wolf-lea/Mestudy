package com.tecsun.sisp.adapter.modules.account.entity.request;

import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;

/**短信容器类
 * Created by danmeng on 2017/5/5.
 */
public class CaptchaBean extends SecQueryBean{
    private String accountId;//用户账号
    private String smsType;//短信类别：1-注册；2-重置密码；3-修改手机号码
    private String phone;// 手机号码：用英文逗号或中文逗号隔开的手机号码集
    private String captcha;//验证码
    private String msContent;// 短信内容：短信内容。普通短信：最大支持2000个汉字的短信，超过2000时会被截断。PDU短信：最大支持140个字节的短信，超过140 个字节，发送时会被截断

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getSmsType() {
        return smsType;
    }

    public void setSmsType(String smsType) {
        this.smsType = smsType;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMsContent() {
        return msContent;
    }

    public void setMsContent(String msContent) {
        this.msContent = msContent;
    }

}
