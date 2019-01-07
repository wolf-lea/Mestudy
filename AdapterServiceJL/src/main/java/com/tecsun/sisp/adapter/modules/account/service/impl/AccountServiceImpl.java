package com.tecsun.sisp.adapter.modules.account.service.impl;

import com.tecsun.sisp.adapter.modules.account.entity.request.AccountBean;
import com.tecsun.sisp.adapter.modules.account.entity.response.AccountVO;
import com.tecsun.sisp.adapter.modules.account.entity.response.FunctionVO;
import com.tecsun.sisp.adapter.modules.account.entity.response.RoleVO;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by danmeng on 2017/5/5.
 */

@Service("AccountServiceImpl")
public class AccountServiceImpl extends BaseService {
    public static final Logger logger = Logger.getLogger(AccountServiceImpl.class);

    public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.account.service.impl.AccountServiceImpl.";

    public  List<AccountVO> getAccountInfo4Cssp(String accountId,String sfzh,String phone) throws Exception {
        Map map = new HashMap();
        map.put("accountId",accountId);
        map.put("sfzh",sfzh);
        map.put("phone",phone);
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getAccountInfo", map);
    }
    public  List<AccountVO> getAccountInfoByAccountId4Cssp(String accountId,String isWechat) throws Exception {
        Map map = new HashMap();
        map.put("accountId",accountId);
        map.put("isWechat", isWechat);
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getAccountInfo", map);
    }
    public  List<AccountVO> getAccountInfoByOpenid4Cssp(String openid,String isWechat) throws Exception {
        Map map = new HashMap();
        map.put("openid",openid);
        map.put("isWechat", isWechat);
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getAccountInfo", map);
    }
    public  List<AccountVO> getAccountInfoByAlipayId4Cssp(String alipayId) throws Exception {
        Map map = new HashMap();
        map.put("alipayId",alipayId);
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getAccountInfo", map);
    }

    public long insertAccountInfo4Cssp(AccountBean bean) {
        long status = 0;
        try {
            status = this.getSqlSessionTemplate().insert(NAMESPACE + "insertAccountInfo", bean);
        } catch (Exception e) {
            status = -1;
            logger.error("新增账号错误：", e);
        }
        return status;
    }

    public long updateAccountInfo4Cssp(AccountBean bean) {
        long status = 0;
        try {
            status = this.getSqlSessionTemplate().update(NAMESPACE + "updateAccountInfo", bean);
        } catch (Exception e) {
            status = -1;
            logger.error("修改个人信息错误：", e);
        }
        return status;
    }

    public long disassociateWechat4Cssp(AccountBean bean) {
        long status = 0;
        try {
            status = this.getSqlSessionTemplate().update(NAMESPACE + "disassociateWechat", bean);
        } catch (Exception e) {
            status = -1;
            logger.error("微信解绑错误：", e);
        }
        return status;
    }
    public long disassociateAlipay4Cssp(AccountBean bean) {
        long status = 0;
        try {
            status = this.getSqlSessionTemplate().update(NAMESPACE + "disassociateAlipay", bean);
        } catch (Exception e) {
            status = -1;
            logger.error("支付宝解绑错误：", e);
        }
        return status;
    }

    public long resetAccountPwd4Cssp(AccountBean bean) {
        long status = 0;
        try {
            status = this.getSqlSessionTemplate().update(NAMESPACE + "resetAccountPwd", bean);
        } catch (Exception e) {
            logger.error("修改账户密码错误：", e);
        }
        return status;
    }

    public List<RoleVO> getRoleList4Cssp(String roleCode) throws Exception {
        if(StringUtils.isBlank(roleCode)) roleCode=null;
        Map map = new HashMap();
        map.put("roleCode",roleCode);
        List<RoleVO> roleVOList = this.getSqlSessionTemplate().selectList(NAMESPACE+ "getRoleList",map);
        return roleVOList;
    }
    public List<FunctionVO> getFuncOfRoleList4Cssp(String roleCode) throws Exception {
        if(StringUtils.isBlank(roleCode)) roleCode=null;
        Map map = new HashMap();
        map.put("roleCode",roleCode);
        List<FunctionVO> functionVOList = this.getSqlSessionTemplate().selectList(NAMESPACE+ "getFuncOfRoleList",map);
        return functionVOList ;
    }

    //修改个人信息头像
    public long updateAccountPhoto4Cssp(AccountBean bean) throws Exception{
        //查询是否已有记录
        List<AccountVO> list = this.getSqlSessionTemplate().selectList(NAMESPACE + "getAccountPhoto", bean);
        if(list != null && list.size() > 0){
            //修改
            return this.getSqlSessionTemplate().update(NAMESPACE + "updateAccountPhoto", bean);
        }else {
            //新增
            return this.getSqlSessionTemplate().insert(NAMESPACE + "insertAccountPhoto", bean);
        }
    }

    //
    public String selectAccountPhoto4Cssp(AccountBean bean) throws Exception{
        //查询是否已有记录
        List<AccountVO> list = this.getSqlSessionTemplate().selectList(NAMESPACE + "getAccountPhoto", bean);
        if(list != null && list.size() > 0) {
            return list.get(0).getPic();
        }
        return null;
    }
    
    // pc端查询微信绑定/解绑列表
    public List<AccountVO> getPCBindList4Cssp(AccountBean bean) throws Exception {
        List<AccountVO> accountVOList = this.getSqlSessionTemplate().selectList(NAMESPACE+ "getPCBindList",bean);
        return accountVOList ;
    }
    
    
    // pc端查询微信绑定信息详情
    public AccountVO getPCBindDetail4Cssp(AccountBean bean) throws Exception {
       AccountVO accountVO = this.getSqlSessionTemplate().selectOne(NAMESPACE+ "getPCBindDetail",bean);
        return accountVO ;
    }
    
    // PC端微信绑定
    public long bindWechat4Cssp(AccountBean bean) {
        long status = 0;
        try {
            status = this.getSqlSessionTemplate().update(NAMESPACE + "bindWechat", bean);
        } catch (Exception e) {
            status = -1;
            logger.error("微信绑定错误：", e);
        }
        return status;
    }
}
