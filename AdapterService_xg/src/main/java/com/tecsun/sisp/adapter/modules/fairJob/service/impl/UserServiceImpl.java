package com.tecsun.sisp.adapter.modules.fairJob.service.impl;

import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.fairJob.entity.request.CoUserBean;
import com.tecsun.sisp.adapter.modules.fairJob.entity.request.ResetPasswordBean;
import com.tecsun.sisp.adapter.modules.fairJob.entity.response.CoUserVO;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/11/2 0002.
 */
@Service("UserServiceImpl")
public class UserServiceImpl extends BaseService {
    public static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    public final static String NAMESPACE ="com.tecsun.sisp.adapter.modules.fairJob.service.impl.UserServiceImpl.";

    //查询用户信息
    public CoUserVO selectUserByLogName4cssp(CoUserBean bean) throws Exception {
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "selectUserByLogName",bean);
    }

    //新增修改密码记录
    public int insertResetPassword4cssp(ResetPasswordBean bean) throws Exception {
        return this.getSqlSessionTemplate().insert(NAMESPACE + "insertResetPassword", bean);
    }

    //查询修改密码记录
    public ResetPasswordBean selectResetPassword4cssp(ResetPasswordBean bean) throws Exception {
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "selectResetPassword", bean);
    }

    //更新修改密码记录
    public int updateResetPassword4cssp(ResetPasswordBean bean) throws Exception {
        return this.getSqlSessionTemplate().update(NAMESPACE + "updateResetPassword", bean);
    }

    //根据用户名修改用户信息sisp
    public int updateUserByLogName4cssp(CoUserBean bean) throws Exception {
        if(!StringUtils.isBlank(bean.getId())){
            String email = getSqlSessionTemplate().selectOne(NAMESPACE + "getEmail", bean);
            bean.setEmail(email);
        }
        getSqlSessionTemplate().update(NAMESPACE + "updateUserByLogName", bean);
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getUserId", bean);
    }
    //根据用户名修改用户信息sisp_public
    public int updateUserByLogName4sisp(CoUserBean bean) throws Exception {
        return this.getSqlSessionTemplate().update(NAMESPACE + "updateUserByLogNameSP", bean);
    }

    //根据用户名修改用户信息
    public CoUserVO selectUserId4cssp(CoUserBean bean) throws Exception {
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "selectUserId", bean);
    }

	/**
	 * @param coUserBean
	 * @return
	 */
	public CoUserVO checkUserByLogName4Sisp(CoUserBean coUserBean) {
		
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "checkUserByLogName", coUserBean);
	}
}
