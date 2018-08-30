package com.tecsun.sisp.adapter.modules.account.entity.response;

/**验证码
 * Created by danmeng on 2017/5/10.
 */
public class CaptchaVO {
    private String captcha;//验证码
    private int captchaTimeout;//验证码缓存时间
    private boolean validity;//有效性

    public boolean getValidity() {
        return validity;
    }

    public void setValidity(boolean validity) {
        this.validity = validity;
    }

    public String getCaptcha() {

        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public int getCaptchaTimeout() {
        return captchaTimeout;
    }

    public void setCaptchaTimeout(int captchaTimeout) {
        this.captchaTimeout = captchaTimeout;
    }
}
