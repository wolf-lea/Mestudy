package com.tecsun.sisp.adapter.modules.account.controller;

import com.tecsun.sisp.adapter.common.util.*;
import com.tecsun.sisp.adapter.modules.account.entity.request.CaptchaBean;
import com.tecsun.sisp.adapter.modules.account.entity.response.CaptchaVO;
import com.tecsun.sisp.adapter.modules.account.entity.response.FunctionVO;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.account.entity.request.AccountBean;
import com.tecsun.sisp.adapter.modules.account.entity.response.AccountVO;
import com.tecsun.sisp.adapter.modules.account.entity.response.RoleVO;
import com.tecsun.sisp.adapter.modules.account.service.impl.AccountServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
/*
*App 端、微信端账号管理
 * Created by danmeng on 2017/5/3.
 * */
@Controller
@RequestMapping(value = "/adapter/account")
public class AccountController  extends BaseController {
    private static Logger logger = LoggerFactory.getLogger(AccountController.class);
    @Autowired
    private AccountServiceImpl accountService;

    /**
     * 账号管理-注册账号（个人版）
     * 注册时账号、身份证号转大写
     * 注册分为两种：普通注册、静默注册
     * 静默注册[绑定]（生活号、微信渠道，只传账号、姓名、身份证号、默认密码、角色、状态status=0）:
     * 1、平台无对应数据直接新增；2、如果有数据则判断是否绑定，无则绑定，有则提示
     * 普通注册（APP等）：1、平台无对应数据直接注册；2、如果有数据[静默注册status=0]则修改个人信息、密码
     * @param bean
     * @return
     */
    @RequestMapping(value = "/registerAccount", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result registerAccount(@RequestBody AccountBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getAccountId()) || StringUtils.isBlank(bean.getAccountName())
                || StringUtils.isBlank(bean.getSfzh()) || StringUtils.isBlank(bean.getAccountPwd())
                || StringUtils.isBlank(bean.getReAccountPwd()) || StringUtils.isBlank(bean.getChannelcode())
                || StringUtils.isBlank(bean.getRoleCode()) || StringUtils.isBlank(bean.getStatus())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        if (Constants.WECHAT.equals(bean.getChannelcode()) && StringUtils.isBlank(bean.getOpenid())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "缺失微信绑定号");
        }
        if (Constants.ALIPAY.equals(bean.getChannelcode()) && StringUtils.isBlank(bean.getAlipayId())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "缺失支付宝绑定号");
        }
        //手机号必填，除默认注册[绑定]（status=0，无手机号）
        if (!"0".equals(bean.getStatus())) {
            if (StringUtils.isBlank(bean.getPhone()))
                return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入手机号码");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        List<AccountVO> list = new ArrayList<AccountVO>();
        int listSize = 0;
        String status = null;
        String channelcode = null;
        try {
            if (bean.getAccountPwd().equals(bean.getReAccountPwd())) {
                bean.setAccountPwd(bean.getAccountPwd().toLowerCase());
                bean.setReAccountPwd(bean.getReAccountPwd().toLowerCase());
                bean.setAccountId(bean.getAccountId().toUpperCase());
                bean.setSfzh(bean.getSfzh().toUpperCase());
                //静默注册（绑定）不传手机号
                if (listSize == 0 && StringUtils.isNotEmpty(bean.getPhone())) {
                    list = accountService.getAccountInfo4Cssp(null, null, bean.getPhone());
                    if (list != null && !list.isEmpty()) {
                        message = "该手机号码已被其他账号绑定";
                        listSize = list.size();
                    }
                }
                if (listSize == 0 && StringUtils.isNotEmpty(bean.getAccountId())) {
                    list = accountService.getAccountInfoByAccountId4Cssp(bean.getAccountId());
                    if (list != null && !list.isEmpty()) {
                        if ("1".equals(list.get(0).getStatus())) {
                            message = "该账号已注册";
                        }
                        status = list.get(0).getStatus();
                        channelcode = list.get(0).getChannelcode();
                        listSize = list.size();
                    }
                }
                if (listSize == 0 && StringUtils.isNotEmpty(bean.getSfzh())) {
                    list = accountService.getAccountInfo4Cssp(null, bean.getSfzh(), null);
                    if (list != null && !list.isEmpty()) {
                        message = "该身份证号已注册";
                        listSize = list.size();
                    }
                }
                if (listSize == 0 && StringUtils.isNotEmpty(bean.getOpenid())) {
                    list = accountService.getAccountInfoByOpenid4Cssp(bean.getOpenid());
                    if (list != null && !list.isEmpty()) {
                        message = "该微信号已被其他账号绑定";
                        listSize = list.size();
                    }
                }
                if (listSize == 0 && StringUtils.isNotEmpty(bean.getAlipayId())) {
                    list = accountService.getAccountInfoByAlipayId4Cssp(bean.getAlipayId());
                    if (list != null && !list.isEmpty()) {
                        message = "该支付宝已被其他账号绑定";
                        listSize = list.size();
                    }
                }
                //平台无对应数据直接新增
                if (listSize == 0) {
                    //查看对应角色是否存在
                    List<RoleVO> roleVOs = accountService.getRoleList4Cssp(bean.getRoleCode());
                    if (roleVOs == null || roleVOs.isEmpty()) {
                        message = "不存在该用户角色";
                    } else {
                        bean.setPwdIsupdate("1");
                        long addStatus = accountService.insertAccountInfo4Cssp(bean);
                        if (addStatus != 0) {
                            message = "注册成功";
                            statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                        } else {
                            message = "注册失败";
                        }
                    }
                } else {//平台有对应账号数据：则绑定微信号或支付宝号
                    //静默注册[绑定]：只传账号、姓名、身份证号、默认密码、角色、状态status=0
                    if ("0".equals(bean.getStatus())) {
                        bean.setStatus("");//不改变原有状态
                        bean.setAccountName("");//姓名已从生活号获取，不修改;微信已通过实名认证,不修改
                        bean.setSfzh("");//身份证号已从生活号获取，不修改;微信已通过实名认证,不修改
                        //微信渠道 [绑定]
                        if (Constants.WECHAT.equals(bean.getChannelcode()) && StringUtils.isNotBlank(bean.getOpenid())) {
                            if (StringUtils.isNotBlank(list.get(0).getOpenid())) {
                                message = "本账号已绑定其它微信号，请先解绑";
                                statusCode = "-200";
                            } else {//静默注册[绑定],未绑定微信号都直接绑定
                                long updateStatus = accountService.updateAccountInfo4Cssp(bean);//修改个人信息：绑定微信号
                                if (updateStatus > 0) {
                                    message = "绑定成功";
                                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                                } else {
                                    message = "绑定失败";
                                    statusCode = "-100";
                                }
                            }
                        }
                        //生活号渠道 [绑定]
                        if (Constants.ALIPAY.equals(bean.getChannelcode()) && StringUtils.isNotBlank(bean.getAlipayId())) {
                            if (StringUtils.isNotBlank(list.get(0).getAlipayId())) {
                                message = "本账号已绑定其它支付宝号，请先解绑";
                                statusCode = "-200";
                            } else {//静默注册[绑定],未绑定支付宝号都直接绑定
                                long updateStatus = accountService.updateAccountInfo4Cssp(bean);//修改个人信息：绑定支付宝号
                                if (updateStatus > 0) {
                                    message = "绑定成功";
                                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                                } else {
                                    message = "绑定失败";
                                    statusCode = "-100";
                                }
                            }
                        }
                    } else {//普通注册：平台数据状态 status=0，则修改个人信息、密码
                        if ("0".equals(status)) {
                            //生活号、微信暂不提供普通注册功能
                            if (Constants.ALIPAY.equals(bean.getChannelcode()) || Constants.WECHAT.equals(bean.getChannelcode())) {
                                message = "暂未提供注册功能，敬请期待";
                                statusCode = "-400";
                            } else {
                                //平台数据原始注册渠道为生活号或微信：姓名、身份证不修改
                                if (Constants.ALIPAY.equals(channelcode) || Constants.WECHAT.equals(channelcode)) {
                                    bean.setAccountName("");//姓名已从生活号获取，不修改;微信已通过实名认证,不修改
                                    bean.setSfzh("");//身份证号已从生活号获取，不修改;微信已通过实名认证,不修改
                                }
                                long updateStatus = accountService.updateAccountInfo4Cssp(bean);//修改个人信息
                                accountService.resetAccountPwd4Cssp(bean);//修改密码
                                if (updateStatus > 0) {
                                    message = "注册成功";
                                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                                } else message = "注册失败";
                            }
                        }
                    }
                }
            } else {
                message = "密码和确认密码不一致";
            }
        } catch (Exception e) {
            logger.error("账号管理-注册账号（个人版）失败：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 账号管理-查询角色列表
     *
     * @param bean
     * @return
     */

    @RequestMapping(value = "/getRoleList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getRoleList(@RequestBody AccountBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            List<RoleVO> roleVOList = accountService.getRoleList4Cssp(bean.getRoleCode());
            if (roleVOList != null && roleVOList.size() > 0) {
                message = "查询成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message, roleVOList);
            } else {
                message = "暂无角色信息";
            }
        } catch (Exception e) {
            logger.error("账号管理-查询角色列表失败：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 账号管理-查询角色对应功能
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getFuncOfRole", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getFuncOfRole(@RequestBody AccountBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getRoleCode())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入角色编码");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            List<FunctionVO> functionVOList = accountService.getFuncOfRoleList4Cssp(bean.getRoleCode());
            if (functionVOList != null && functionVOList.size() > 0) {
                List<FunctionVO> allFunction = getChildFunctions(0, functionVOList);
                message = "查询成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message, allFunction);
            } else {
                message = "该角色无对应功能";
            }
        } catch (Exception e) {
            logger.error("账号管理-查询角色列表失败：", e);
        }
        return result(statusCode, message);
    }

    private List<FunctionVO> getChildFunctions(long parentFuncId, List<FunctionVO> functionVOs) {
        List<FunctionVO> list = new ArrayList<FunctionVO>();
        for (FunctionVO functionVO : functionVOs) {
            if (parentFuncId == functionVO.getParentFuncId()) {
                functionVO.setChildFunctions(getChildFunctions(functionVO.getFuncId(), functionVOs));
                list.add(functionVO);
            }
        }
        return list;
    }


    /**
     * 账号管理-校验密码
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/checkAccountPwd", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result checkAccountPwd(@RequestBody AccountBean bean) throws Exception {
        if ((StringUtils.isBlank(bean.getAccountId()) && StringUtils.isBlank(bean.getPhone()))
                || StringUtils.isBlank(bean.getAccountPwd())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            List<AccountVO> list = accountService.getAccountInfo4Cssp(bean.getAccountId() ,null, bean.getPhone());
            if (list != null && !list.isEmpty()) {
                if (bean.getAccountPwd().toLowerCase().equals(list.get(0).getAccountPwd())) {
                    message = "密码正确";
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                } else message = "密码错误";
            } else {
                message = "该用户不存在";
            }
        } catch (Exception e) {
            logger.error("账号管理-校验密码失败：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 账号管理-重置密码
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/resetAccountPwd", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result resetAccountPwd(@RequestBody AccountBean bean) throws Exception {
        if ((StringUtils.isBlank(bean.getAccountId()) && StringUtils.isBlank(bean.getPhone()))
                || StringUtils.isBlank(bean.getAccountPwd())){
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            if (bean.getAccountPwd().equals(bean.getReAccountPwd())) {
                List<AccountVO> list = accountService.getAccountInfo4Cssp(bean.getAccountId() ,null, bean.getPhone());
                if (list != null && !list.isEmpty()) {
                    if (StringUtils.isBlank(bean.getAccountId()))
                    bean.setAccountId(list.get(0).getAccountId());
                    bean.setAccountPwd(bean.getAccountPwd().toLowerCase());
                    bean.setReAccountPwd(bean.getReAccountPwd().toLowerCase());
                    long status = accountService.resetAccountPwd4Cssp(bean);
                    if (status > 0) {
                        message = "修改密码成功";
                        statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                    } else message = "重置失败";
                } else {
                    message = "该用户不存在";
                }
            } else {
                message = "密码和确认密码不一致";
            }
        } catch (Exception e) {
            logger.error("账号管理-重置密码失败：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 账号管理-账号登录
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/accountLogin", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result accountLogin(@RequestBody AccountBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getAccountId()) || StringUtils.isBlank(bean.getAccountPwd())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            List<AccountVO> list = accountService.getAccountInfo4Cssp(bean.getAccountId(), bean.getSfzh(), bean.getPhone());
            if (list != null && !list.isEmpty()) {
                AccountVO accountVO = list.get(0);
                if (bean.getAccountPwd().toLowerCase().equals(accountVO.getAccountPwd())) {
                    if ("0".equals(accountVO.getStatus()))
                        message = "抱歉，您无登录权限，请重新注册";
                    else {
                        accountVO.setSex(DictionaryUtil.getDictName(Constants.SEX_GROUP, accountVO.getSex()));
                        accountVO.setNation(Constants.NATION.get(accountVO.getNation()));
                        accountVO.setAccountPwd(null);
                        message = "登录成功";
                        statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                        return result(statusCode, message, accountVO);
                    }
                } else message = "密码错误";
            } else {
                message = "该用户不存在";
            }
        } catch (Exception e) {
            logger.error("账号管理-账号登录失败：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 账号管理-查看个人信息
     *用户账号、微信绑定号、支付宝绑定好至少填一个
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getAccountInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getAccountInfo(@RequestBody AccountBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getAccountId())&&StringUtils.isBlank(bean.getOpenid())&&StringUtils.isBlank(bean.getAlipayId())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        List<AccountVO> list = new ArrayList<AccountVO>();
        try {
            if (StringUtils.isNotEmpty(bean.getOpenid()))
                list = accountService.getAccountInfoByOpenid4Cssp(bean.getOpenid());
            else if (StringUtils.isNotEmpty(bean.getAlipayId()))
                list = accountService.getAccountInfoByAlipayId4Cssp(bean.getAlipayId());
            else if (StringUtils.isNotEmpty(bean.getAccountId()))
                list = accountService.getAccountInfoByAccountId4Cssp(bean.getAccountId());
            if (list != null && !list.isEmpty()) {
                for (AccountVO accountVO : list) {
                    accountVO.setSex(DictionaryUtil.getDictName(Constants.SEX_GROUP, accountVO.getSex()));
                    accountVO.setNation(Constants.NATION.get(accountVO.getNation()));
                    accountVO.setAccountPwd(null);
                }
                Page<AccountVO> page = new Page(1, bean.getPagesize());
                page.setData(list);
                page.setCount(list.size());
                message = "查询成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message, page);
            } else {
                message = "该用户不存在";
            }
        } catch (Exception e) {
            logger.error("账号管理-查看个人信息失败：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 账号管理-修改个人信息
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/saveAccountInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result saveAccountInfo(@RequestBody AccountBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getAccountId())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "用户账号不能为空");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        int listSize = 0;
        List<AccountVO> list = new ArrayList<AccountVO>();
        try {
            List<AccountVO> accountVOList = accountService.getAccountInfoByAccountId4Cssp(bean.getAccountId());
            if (accountVOList != null && !accountVOList.isEmpty()) {
                if (listSize == 0 && StringUtils.isNotEmpty(bean.getPhone())) {
                    list = accountService.getAccountInfo4Cssp(null, null, bean.getPhone());
                    if (list != null && !list.isEmpty()) {
                        for (AccountVO accountVO : list) {
                            if (!bean.getAccountId().equals(accountVO.getAccountId())) {
                                listSize = list.size();
                                message = "手机号" + bean.getPhone() + "已被其他用户使用";
                                break;
                            }
                        }
                    }
                }
                if (listSize == 0 && StringUtils.isNotEmpty(bean.getSfzh())) {
                    list = accountService.getAccountInfo4Cssp(null, bean.getSfzh(), null);
                    if (list != null && !list.isEmpty()) {
                        for (AccountVO accountVO : list) {
                            if (!bean.getAccountId().equals(accountVO.getAccountId())) {
                                listSize = list.size();
                                message = "身份证号" + bean.getSfzh() + "已被其他用户使用";
                                break;
                            }
                        }
                    }
                }
                if (listSize == 0 && StringUtils.isNotEmpty(bean.getOpenid())) {
                    list = accountService.getAccountInfoByOpenid4Cssp(bean.getOpenid());
                    if (list != null && !list.isEmpty()) {
                        for (AccountVO accountVO : list) {
                            if (!bean.getAccountId().equals(accountVO.getAccountId())) {
                                listSize = list.size();
                                message = "微信号" + bean.getOpenid() + "已被其他用户使用";
                                break;
                            }
                        }
                    }
                }
                if (listSize == 0 && StringUtils.isNotEmpty(bean.getAlipayId())) {
                    list = accountService.getAccountInfoByAlipayId4Cssp(bean.getAlipayId());
                    if (list != null && !list.isEmpty()) {
                        for (AccountVO accountVO : list) {
                            if (!bean.getAccountId().equals(accountVO.getAccountId())) {
                                listSize = list.size();
                                message = "支付宝号" + bean.getAlipayId() + "已被其他用户使用";
                                break;
                            }
                        }
                    }
                }
                if (listSize == 0) {
                    bean.setNation(Constants.NATION_Code.get(bean.getNation()));
                    long status = accountService.updateAccountInfo4Cssp(bean);
                    if (status > 0) {
                        message = "保存成功";
                        statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                    } else message = "保存失败";
                }
            } else {
                message = "该用户不存在";
            }
        } catch (Exception e) {
            logger.error("账号管理-修改个人信息失败：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 账号管理-发送短信验证码
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/sendCaptcha", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result sendCaptcha(@RequestBody CaptchaBean bean) throws Exception {
        if ((StringUtils.isBlank(bean.getAccountId()) && StringUtils.isBlank(bean.getSfzh())
                && StringUtils.isBlank(bean.getPhone())) || StringUtils.isBlank(bean.getSmsType())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入账号信息");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "发送错误";
        String accountIdBySfzh = null;
        String sfzhByAccountId = null;
        try {
            List<AccountVO> accountVOs = new ArrayList<AccountVO>();
            if (StringUtils.isNotBlank(bean.getAccountId())) {
                accountVOs = accountService.getAccountInfo4Cssp(bean.getAccountId(), null, null);
                if (accountVOs != null && !accountVOs.isEmpty()) {
                    sfzhByAccountId = accountVOs.get(0).getSfzh();
                }
            }
            List<AccountVO> sfzhVOs = new ArrayList<AccountVO>();
            if (StringUtils.isNotBlank(bean.getSfzh())) {
                sfzhVOs = accountService.getAccountInfo4Cssp(null, bean.getSfzh(), null);
                if (sfzhVOs != null && !sfzhVOs.isEmpty()) {
                    accountIdBySfzh = sfzhVOs.get(0).getAccountId();
                }
            }
            List<AccountVO> phoneVOs = new ArrayList<AccountVO>();
            if (StringUtils.isNotBlank(bean.getPhone())) {
                phoneVOs = accountService.getAccountInfo4Cssp(null, null, bean.getPhone());
            }
            if (bean.getSmsType().equals("1")) {  //注册
                if (StringUtils.isBlank(bean.getPhone())) {
                    return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入手机号");
                } else if (phoneVOs != null && !phoneVOs.isEmpty()) {
                    message = "该手机号已被绑定";
                    return result(statusCode, message);
                }
                if (StringUtils.isNotBlank(bean.getAccountId())) {
                    if (accountVOs != null && !accountVOs.isEmpty()&&"1".equals(accountVOs.get(0).getStatus())) {
                        message = "您已注册，请直接登录";
                        return result(statusCode, message);
                    }
                }
            } else if (bean.getSmsType().equals("2")) {//2-重置密码
                if (StringUtils.isBlank(bean.getPhone())) {
                    if (StringUtils.isBlank(bean.getSfzh())) {
                        if (StringUtils.isBlank(bean.getAccountId())) {
                            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入账号");
                        }
                    }
                    if (StringUtils.isNotBlank(bean.getAccountId())) {
                        if (accountVOs != null && !accountVOs.isEmpty()) {
                            if (StringUtils.isNotBlank(bean.getSfzh())) {
                                if (!bean.getSfzh().equals(sfzhByAccountId)) {
                                    message = "输入的账号与身份证不属于同一用户";
                                    return result(statusCode, message);
                                }
                            }
                            bean.setPhone(accountVOs.get(0).getPhone());
                        }
                    }
                    if (StringUtils.isNotBlank(bean.getSfzh())) {
                        if (sfzhVOs != null && !sfzhVOs.isEmpty()) {
                            if (StringUtils.isNotBlank(bean.getAccountId())) {
                                if (!bean.getAccountId().equals(accountIdBySfzh)) {
                                    message = "输入的身份证与账号不属于同一用户";
                                    return result(statusCode, message);
                                }
                            }
                            bean.setPhone(accountVOs.get(0).getPhone());
                        }
                    }
                    if (StringUtils.isBlank(bean.getPhone())) {
                        message = "该账号未注册，请先注册";
                        return result(statusCode, message);
                    }
                } else {
                    if (phoneVOs != null && !phoneVOs.isEmpty()) {
                        List<AccountVO> list = accountService.getAccountInfo4Cssp(bean.getAccountId(), bean.getSfzh(), bean.getPhone());
                        if (list == null || list.isEmpty()) {
                            message = "输入的手机号码与您绑定的手机号码不符";
                            return result(statusCode, message);
                        }
                    } else {
                        message = "输入的手机号码未绑定任何用户";
                        return result(statusCode, message);
                    }
                }
            } else if (bean.getSmsType().equals("3")) {//3-修改手机号码
                if (StringUtils.isBlank(bean.getPhone())) {
                    return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入手机号");
                }
                if (StringUtils.isBlank(bean.getSfzh())) {
                    if (StringUtils.isBlank(bean.getAccountId())) {
                        return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入账号");
                    }
                }
                if ((sfzhVOs == null || sfzhVOs.isEmpty()) && (accountVOs == null || accountVOs.isEmpty())) {
                    message = "该账号未注册，请先注册";
                    return result(statusCode, message);
                } else {
                    List<AccountVO> list = accountService.getAccountInfo4Cssp(bean.getAccountId(), bean.getSfzh(), null);
                    if (list == null || list.isEmpty()) {
                        message = "输入的账号与身份证不属于同一用户";
                        return result(statusCode, message);
                    } else if (phoneVOs != null && !phoneVOs.isEmpty()) {
                        if ((phoneVOs.get(0).getAccountId()).equals(bean.getAccountId())) {
                            message = "该手机号已被您的账号绑定,请勿重复绑定";
                            return result(statusCode, message);
                        } else if (StringUtils.isNotEmpty(phoneVOs.get(0).getSfzh())
                                && (phoneVOs.get(0).getSfzh()).equals(bean.getSfzh())) {
                            message = "该手机号已被您的身份证号绑定,请勿重复绑定";
                            return result(statusCode, message);
                        } else {
                            message = "该手机号已被其他用户绑定";
                            return result(statusCode, message);
                        }
                    }
                }
            } else if (bean.getSmsType().equals("4")) {
                if (StringUtils.isBlank(bean.getPhone())) {
                    return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入手机号");
                }
                if (StringUtils.isNotBlank(bean.getAccountId())) {
                    return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入账号");
                }
                if (accountVOs != null && !accountVOs.isEmpty()) {
                    if(StringUtils.isNotBlank(accountVOs.get(0).getPhone())&& !bean.getPhone().equals(accountVOs.get(0).getPhone())){
                        message = "输入的手机号码与您绑定的手机号码不符";
                        return result(statusCode, message);
                    }
                }else {
                    message = "该账号未注册，请先注册";
                    return result(statusCode, message);
                }
            }
            if (StringUtils.isNotEmpty(bean.getPhone())) {
                CaptchaVO captchaVO = SmsUtil.sendVerify4Sms(bean.getPhone(), bean.getSmsType());
                if (captchaVO != null && captchaVO.getValidity() == true) {
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                    message = "发送成功";
                    return result(statusCode, message, captchaVO);
                }
            } else {
                message = "该用户未绑定手机号码";
            }
        } catch (Exception e) {
            logger.error("账号管理-获取短信验证码失败：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 账号管理-校验验证码
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/checkCaptcha", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result checkCaptcha(@RequestBody CaptchaBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getCaptcha())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入验证码");
        }
        if ((StringUtils.isBlank(bean.getAccountId()) && StringUtils.isBlank(bean.getSfzh())
                && StringUtils.isBlank(bean.getPhone())) || StringUtils.isBlank(bean.getSmsType())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "校验失败";
        try {
            List<AccountVO> list = accountService.getAccountInfo4Cssp(bean.getAccountId(), bean.getSfzh(), bean.getPhone());
            //获取手机号码
            if (StringUtils.isBlank(bean.getPhone())) {
                if (list != null && !list.isEmpty()) {
                    bean.setPhone(list.get(0).getPhone());
                }
            }
            if (StringUtils.isNotEmpty(bean.getPhone())) {
                int status = SmsUtil.verifySMS(bean.getPhone(), bean.getCaptcha(), bean.getSmsType());
                if (status == -1)
                    message = "输入验证码超时，请重新获取";
                else if (status == -2)
                    message = "输入验证码错误，请重新输入";
                else if (status == 1) {
                    message = "验证码正确";
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                } else {
                    message = "校验失败";
                }
            } else {
                message = "未找到有效手机号码";
            }
        } catch (Exception e) {
            logger.error("账号管理-校验验证码失败：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 账号管理-微信解绑
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/disassociateWechat", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result disassociateWechat(@RequestBody AccountBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getAccountId())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "用户账号不能为空");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            List<AccountVO> list = accountService.getAccountInfoByAccountId4Cssp(bean.getAccountId());
            if (list != null && !list.isEmpty()) {
                long status = accountService.disassociateWechat4Cssp(bean);
                if (status > 0) {
                    message = "解绑成功";
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                } else message = "解绑失败";
            } else {
                message = "该用户不存在";
            }
        } catch (Exception e) {
            logger.error("账号管理-微信解绑失败：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 账号管理-支付宝解绑
     *
     * @param bean
     * @return
     */
    @RequestMapping(value = "/disassociateAlipay", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result disassociateAlipay(@RequestBody AccountBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getAccountId())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "用户账号不能为空");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            List<AccountVO> list = accountService.getAccountInfoByAccountId4Cssp(bean.getAccountId());
            if (list != null && !list.isEmpty()) {
                long status = accountService.disassociateAlipay4Cssp(bean);
                if (status > 0) {
                    message = "解绑成功";
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                } else message = "解绑失败";
            } else {
                message = "该用户不存在";
            }
        } catch (Exception e) {
            logger.error("账号管理-支付宝解绑失败：", e);
        }
        return result(statusCode, message);
    }

}
