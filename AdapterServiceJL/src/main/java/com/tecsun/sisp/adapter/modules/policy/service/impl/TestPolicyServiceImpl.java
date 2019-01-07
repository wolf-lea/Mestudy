package com.tecsun.sisp.adapter.modules.policy.service.impl;

import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.policy.entity.request.PolicyTitleBean;
import com.tecsun.sisp.adapter.modules.policy.entity.response.PolicyTitleVO;
import com.tecsun.sisp.adapter.modules.policy.entity.response.PolicyTypeVO;
import com.tecsun.sisp.adapter.modules.policy.entity.response.PolicyVO;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

/**政策法规模拟
 * Created by danmeng on 2017/07/11.
 */

@Service("TestPolicyService")
public class TestPolicyServiceImpl extends BaseService {
    public static final Logger logger = Logger.getLogger(TestPolicyServiceImpl.class);

    public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.policy.service.impl.TestPolicyServiceImpl.";


    public List<PolicyTypeVO> getPolicyType4Other() {
        //获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getPolicyType");
    }

    public List<PolicyTitleVO> getPolicyList4Other( PolicyTitleBean bean) {
        //获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE+"getPolicyList",bean);
    }
    public PolicyVO getPolicyContext4Other( PolicyTitleBean bean) {
        //获取测试数据
        return this.getSqlSessionTemplate().selectOne(NAMESPACE+"getPolicyContext",bean);
    }

}
