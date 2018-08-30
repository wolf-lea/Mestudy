package com.tecsun.sisp.adapter.modules.fairJob.controller;

import com.tecsun.sisp.adapter.common.util.Config;
import com.tecsun.sisp.adapter.common.util.EmilUtil;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.fairJob.entity.request.CoUserBean;
import com.tecsun.sisp.adapter.modules.fairJob.entity.request.ResetPasswordBean;
import com.tecsun.sisp.adapter.modules.fairJob.entity.response.CoUserVO;
import com.tecsun.sisp.adapter.modules.fairJob.service.impl.UserServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by xumaohao on 2017/11/29.
 */

@Controller
@RequestMapping(value = "/adapter/fairJob")
public class UserManageController extends BaseController {
    public final static Logger logger = Logger.getLogger(UserManageController.class);

    //设置邮件的有效时间
    private static int time = Integer.parseInt(Config.getInstance().get("commom.emil.overtime"));
    //邮件的内容
    private static String emailContent = Config.getInstance().get("commom.emil.emailContent");
    //显示的发件人名称
    private static String myEmailName = Config.getInstance().get("commom.emil.myEmailName");
    //邮件主题
    private static String emilSubject = Config.getInstance().get("commom.emil.emilSubject");
    //邮件主题
    private static String path = Config.getInstance().get("commom.emil.path");


    @Autowired
    UserServiceImpl userService;

    /**
     * 用户登录
     * @param bean
     * @return
     */
    @RequestMapping("userLogin")
    @ResponseBody
    public Result userLogin(@RequestBody CoUserBean bean) {
        //入参校验
        if (StringUtils.isBlank(bean.getLogName()) || StringUtils.isBlank(bean.getPassword())) {
            return ok("账号密码不能为空");
        }
        try {
            CoUserVO coUserVO = userService.selectUserByLogName4cssp(bean);
            //用户不存在
            if(coUserVO == null) {
                return ok("该用户不存在");
            }else {
                //密码错误
                if(!bean.getPassword().equals(coUserVO.getPassword())) {
                    return ok("密码错误");
                }
                //判断审核情况 0为未审核，1为审核不通过，2为启用，3为停用
                if("0".equals(coUserVO.getStatus())) {
                    return ok("该帐号正处于审核状态");
                }
                if("1".equals(coUserVO.getStatus())) {
                    return ok("该帐号审核不通过");
                }
                if("3".equals(coUserVO.getStatus())) {
                    return ok("登录成功", coUserVO);
                }
                if("2".equals(coUserVO.getStatus())) {
                    return ok("该帐号已停用");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("fairJob========》userLogin");
            logger.error("登录异常"+e);
        }
        return ok("登录失败");
    }

    /**
     * 验证用户是否存在
     * @param bean
     * @return
     */
    @RequestMapping("selectUser")
    @ResponseBody
    public Result selectUser(@RequestBody CoUserBean bean) {
        //入参校验
        if (StringUtils.isBlank(bean.getPhone()) && StringUtils.isBlank(bean.getEmail())
                && StringUtils.isBlank(bean.getLogName())) {
            return ok("信息不能为空");
        }
        try {
            CoUserVO coUserVO = userService.selectUserByLogName4cssp(bean);
            //用户不存在
            if(coUserVO == null) {
                return ok("该帐号不存在");
            }else {
                Map<String, Object> map = new HashMap<>();
                map.put("id", coUserVO.getId());
                return ok("查询成功", map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("fairJob========》selectUser");
            logger.error("查询用户信息异常"+e);
        }
        return ok("查询失败");
    }

    /**
     * 发送邮件
     * @param bean
     * @return
     */
    @RequestMapping("sentEmail")
    @ResponseBody
    public Result sentEmail(@RequestBody CoUserBean bean) {
        //入参校验
        if (StringUtils.isBlank(bean.getEmail())) {
            return ok("邮箱不能为空");
        }
        try {
            CoUserVO coUserVO = userService.selectUserByLogName4cssp(bean);
            //用户不存在
            if(coUserVO == null) {
                return ok("用户不存在");
            }else {
                //生成密钥
                String secretKey= UUID.randomUUID().toString();
                //设置过期时间
                Date outDate = new Date(System.currentTimeMillis() + time * 60 * 1000);
                System.out.println(System.currentTimeMillis());
                long date = outDate.getTime() / 1000 * 1000;// 忽略毫秒数

                //新增一条重设密码记录
                ResetPasswordBean resetPasswordBean = new ResetPasswordBean();
                resetPasswordBean.setUserId(Long.parseLong(coUserVO.getId()));
                resetPasswordBean.setLogName(coUserVO.getLogName());
                resetPasswordBean.setKey(secretKey);
                resetPasswordBean.setOverTime(date);
                resetPasswordBean.setEmail(coUserVO.getEmail());
                //插入数据库
                userService.insertResetPassword4cssp(resetPasswordBean);
                //生成邮件中的url
                String key = path + "?uid=" + resetPasswordBean.getId() + "&key=" + secretKey;
                //显示的发件人名称 进行解码
                String myEmailNameTemp = new String(myEmailName.getBytes("ISO-8859-1"), "UTF-8");

                //邮件主题 进行解码
                String emilSubjectTemp = new String(emilSubject.getBytes("ISO-8859-1"), "UTF-8");
                //邮件内容
                String emailContentTemp = new String(emailContent.getBytes("ISO-8859-1"), "UTF-8");

                //替换内容中的用户名 以及链接
                emailContentTemp = emailContentTemp.replace("{#name}", coUserVO.getName());
                emailContentTemp = emailContentTemp.replace("{#link}", key);

                EmilUtil.sentEmail(coUserVO.getEmail(), myEmailNameTemp, emilSubjectTemp, emailContentTemp);

                return ok("邮件发送成功");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("fairJob========》sentEmail");
            logger.error("邮件发送异常" + e);
        }
        return error("邮件发送失败");
    }

    /**
     * 校验邮箱链接的合法性、正确性
     * @param bean
     * @return
     */
    @RequestMapping("checkEmail")
    @ResponseBody
    public Result checkEmail(@RequestBody ResetPasswordBean bean) {
        //入参校验
        if(StringUtils.isBlank(bean.getKey()) || bean.getId()==0) {
            return ok("请求的链接不正确,请重新操作");
        }
        try {
            ResetPasswordBean resetPasswordBean = userService.selectResetPassword4cssp(bean);
            if(resetPasswordBean != null){
                //比较过期时间与当前时间
                if(resetPasswordBean.getOverTime() <= System.currentTimeMillis()) {
                    return ok("链接已过期");
                }
                //比较链接秘钥与数据库秘钥
                if(!resetPasswordBean.getKey().equals(bean.getKey())) {
                    return ok("请求的链接不正确,请重新操作");
                }
                if("1".equals(resetPasswordBean.getResetStatus())) {
                    return ok("该链接已使用过,请重新操作");
                }
                userService.updateResetPassword4cssp(resetPasswordBean);
                Map<String, Object> map = new HashMap<>();
                map.put("email", resetPasswordBean.getEmail());
                return ok("验证通过",map);
            }else {
                return ok("用户信息不存在");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("验证邮箱链接出错" + e);
        }
        return error("发送失败");
    }

    /**
     * 修改用户密码
     * @param bean
     * @return
     */
    @RequestMapping("updatePassword")
    @ResponseBody
    public Result updatePassword(@RequestBody CoUserBean bean) {
        //入参校验
        if(StringUtils.isBlank(bean.getEmail()) && StringUtils.isBlank(bean.getPhone())
                && StringUtils.isBlank(bean.getId())) {
            return ok("信息不能为空");
        }
        try {
            int userId = userService.updateUserByLogName4cssp(bean);
            if(userId != 0){
                bean.setUserId(userId);
                userService.updateUserByLogName4sisp(bean);
            }
            return ok("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("修改密码出错" + e);
        }
        return error("修改失败");
    }

    /**
     * 查询用户id
     * @param bean
     * @return
     */
    @RequestMapping("selectUserId")
    @ResponseBody
    public Result selectUserId(@RequestBody CoUserBean bean) {
        //入参校验
        if (bean.getUserId() == 0) {
            return ok("userId不能为空");
        }
        try {
            CoUserVO coUserVO = userService.selectUserId4cssp(bean);
            return ok("查询成功",coUserVO.getId());
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("fairJob========》selectUserId");
            logger.error("查询用户信息异常"+e);
        }
        return error("查询失败");
    }
}
