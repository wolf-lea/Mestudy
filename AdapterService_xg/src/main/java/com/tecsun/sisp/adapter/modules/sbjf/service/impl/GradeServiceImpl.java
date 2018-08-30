package com.tecsun.sisp.adapter.modules.sbjf.service.impl;

import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.sbjf.entity.GradeEntity;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by linzetian on 2017/6/6.
 */
@Service("GradeServiceImpl")
public class GradeServiceImpl extends BaseService {
    private static String NAMESPACE = "com.tecsun.sisp.adapter.modules.sbjf.service.impl.GradeServiceImpl.";

    /**
     * 获取缴费档次
     */
    public List<GradeEntity> list4other(String insureCode) throws Exception{
        List<GradeEntity> res = this.getSqlSessionTemplate().selectList(NAMESPACE+"list",insureCode);
        return res;
    }

}
