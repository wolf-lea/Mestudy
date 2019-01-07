package com.tecsun.sisp.adapter.common.util;

import com.tecsun.sisp.adapter.modules.account.entity.response.CaptchaVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

/**短信工具
 * Created by danmeng on 2017/5/5.
 */
public class SmsUtil {

    private static Logger logger = LoggerFactory.getLogger(SmsUtil.class);
    public static final String regeditMsg = Config.getInstance().get("sms.regedit_msg");  //注册短信内容

    public static final String resetMsg = Config.getInstance().get("sms.reset_msg"); //密码重置短信内容

    public static final String changeMsg = Config.getInstance().get("sms.change_msg"); //密码重置短信内容

    public static final String otherMsg = Config.getInstance().get("sms.other_msg"); //其余短信内容

    public static final String organizationName = Config.getInstance().get("sms.organization_name"); //短信机构

    public static final String msgUrl = Config.getInstance().get("sms.url"); //短信服务器地址
    /**
     * 生成随机数
     *
     * */
    public int randomInt(int from, int to) {
        Random r = new Random();
        return from + r.nextInt(to - from);
    }
    // 生成(六位)验证码
    public static String getCode() {
        return getCode(0);
    }
    // 生成验证码
    public static String getCode(int num){
        if(num==0) num=6;
        String code = "";
        for (int i = 0; i < 6; i++) {
            code = code + (int)(Math.random() * 9);
        }
        return code;

    }
    /**
     * 生成并发送验证码短信 参数：用户名，手机号码
     */
    public static CaptchaVO sendVerify4Sms(String phoneNum,String smsType) {
        CaptchaVO captchaVO=new CaptchaVO();
        if(StringUtils.isNotBlank(phoneNum)&&StringUtils.isNotBlank(smsType)) {
            // 生成六位验证码
            String captcha = getCode();
            captchaVO.setCaptcha(captcha);
            // 短信模板
            String msg = "";
            //缓存时间
            String smsExpireTime = Config.getInstance().get("sms.captcha_timeout");
            int iSmsExpireTime = 1;
            try {
                iSmsExpireTime = Integer.parseInt(smsExpireTime);
                if (smsExpireTime == null || smsExpireTime.equalsIgnoreCase(""))
                    iSmsExpireTime = 1;
            } catch (Exception ex) {
                logger.error("短信验证码过期时间转换错误：", ex);
                iSmsExpireTime = 1;
            }
            captchaVO.setCaptchaTimeout(iSmsExpireTime);
            try {
                if (smsType.equals("1")) {
                    msg = regeditMsg.replace("{#code}", captcha);
                } else if (smsType.equals("2")) {
                    msg = resetMsg.replace("{#code}", captcha);
                } else if (smsType.equals("3")) {
                    msg = changeMsg.replace("{#code}", captcha);
                }else if (smsType.equals("4")) {
                    msg = otherMsg.replace("{#code}", captcha);
                }
                msg = msg.replace("{#organizationName}", organizationName);
                msg = msg.replace("{#captchaTimeout}", String.valueOf(iSmsExpireTime));
                msg = new String(msg.getBytes("ISO-8859-1"), "UTF-8");
            } catch (Exception e) {
                logger.info("短信模板解码：" + msg);
                logger.error("短信编码转换错误：", e);
                captchaVO.setValidity(false);
                return captchaVO;
            }

            //缓存iSmsExpireTime（1分钟）
            try {
                JedisUtil.setValue(smsType + "_CAPTCHA_" + phoneNum, captcha, iSmsExpireTime);
            } catch (Exception e) {
                logger.error("缓存验证码错误：", e);
                captchaVO.setValidity(false);
                return captchaVO;
            }
            try {
                String postdata = assembleSms(msg, phoneNum);
                boolean iCount = sendSms(postdata);
//            boolean iCount =true;
                captchaVO.setValidity(iCount);
            } catch (Exception e) {
                captchaVO.setValidity(false);
            }
        }
        return captchaVO;

    }
    //组装短信
    public static String assembleSms(String msg, String phone) {
        String postData = null;
        if (StringUtils.isNotBlank(msg) && StringUtils.isNotBlank(phone)) {
            try {//发送短信机构
                String orgName = new String((Config.getInstance().get("sms.sendsms_name")).getBytes("ISO-8859-1"), "UTF-8");
                postData = "jsonParam=[{'content': '" + msg + "', 'mobile':'" + phone + "', 'organizationName':'" + orgName + "'}]";
            } catch (Exception e) {
                logger.error("组装短信assembleSms：" + e);
            }
        }
        return postData;
    }

        //发送验证码
    public static boolean  sendSms(String postdata) throws Exception {
        if(StringUtils.isBlank(postdata)) return  false;
        StringBuilder tempStr = new StringBuilder();
        BufferedReader rd = null;
        HttpURLConnection urlConn = null;
        try {
            URL url = new URL(msgUrl);
            urlConn = (HttpURLConnection) url.openConnection();
            urlConn.setRequestMethod("POST");// 网页提交方式“POST”
            urlConn.setDoOutput(true);// 允许连接提交信息
            urlConn.setReadTimeout(Integer.parseInt(Config.getInstance().get("sms.timeout")));// 超时时间
            urlConn.getOutputStream().write(postdata.getBytes("utf-8"));// 组装请求包体json
            rd = new BufferedReader(new InputStreamReader(urlConn.getInputStream(), "UTF-8"));
            String tmps = null;
            while ((tmps = rd.readLine()) != null)
                tempStr.append(tmps.trim());
        } catch (Exception e) {
            logger.error("发送验证码sendSms：" + e);
            return false;
        } finally {
            if (rd != null) {
                try {
                    rd.close();
                } catch (Exception e) {
                    logger.error("发送验证码BufferedReader：" + e);
                }
            }
            if (urlConn != null)
                try {
                    urlConn.disconnect();
                } catch (Exception e) {
                    logger.error("发送验证码BurlConn：" + e);
                }
        }
        String str = tempStr.toString();
        // 状态报告 （0成功，其他失败）
        if (str != null && str.substring(0, 1).equals("0")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 验证短信验证码
     * */
    public static int verifySMS(String phoneNum, String verifyCode, String smsType) {
        // 缓存中验证码
        String captcha = null;
        int status = 0;
        try {
            captcha = JedisUtil.getValue(smsType+"_CAPTCHA_" + phoneNum);
        if (StringUtils.isBlank(captcha))
            status = -1;//输入验证码超时
        // 如果输入的验证码和缓存中的验证码一致，则验证成功,并删除验证码
        else if (captcha.equals(verifyCode)) {
            status = 1;
            JedisUtil.delValue(smsType+"_CAPTCHA_" + phoneNum);
        } else
            status = -2;//输入验证码错误
        } catch (Exception e) {
            logger.error("验证短信验证码错误", e);
            status = 0;
        }
        return status;
    }
}
