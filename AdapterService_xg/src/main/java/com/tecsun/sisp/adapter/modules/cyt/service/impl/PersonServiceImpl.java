package com.tecsun.sisp.adapter.modules.cyt.service.impl;

import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.DoctorBean;
import com.tecsun.sisp.adapter.modules.cyt.entity.response.PersonBean;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by xumaohao on 2017/7/28.
 */

@Service("PersonServiceImpl")
public class PersonServiceImpl extends BaseService {

    private static String NAMESPACE = "com.tecsun.sisp.adapter.modules.cyt.service.impl.PersonServiceImpl.";

    /**
     * 查询个人参保信息
     * @param bean
     * @return
     * @throws Exception
     */
    public PersonBean PersonSelect4other(Map<String,Object> bean) throws Exception{

        PersonBean person = this.getSqlSessionTemplate().selectOne(NAMESPACE + "personSelect", bean.get("sfzh"));

        return person;
    }

    /**
     * 更新统筹余额
     * @param bean
     * @return
     * @throws Exception
     */
    public boolean PerSonUpdate4other(Map<String,Object> bean) throws Exception {
        int count = this.getSqlSessionTemplate().update(NAMESPACE + "personUpdate", bean);

        return count > 0 ? true : false;
    }

    /**
     * 登录信息查询
     * @param bean
     * @return
     * @throws Exception
     */
    public DoctorBean DoctorSelect4other(Map<String,Object> bean) throws Exception {
        DoctorBean doctor = this.getSqlSessionTemplate().selectOne(NAMESPACE + "doctorSelect", bean);

        return doctor != null ? doctor : null;
    }

}
