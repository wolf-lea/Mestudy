package com.tecsun.sisp.adapter.modules.version.service.impl;

import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.version.entity.request.VersionBean;
import com.tecsun.sisp.adapter.modules.version.entity.response.VersionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * Created by xumaohao on 2017/11/16.
 */
@Service("VersionServiceImpl")
public class VersionServiceImpl extends BaseService {

    private static Logger logger = LoggerFactory.getLogger(VersionServiceImpl.class);
    public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.version.service.impl.VersionServiceImpl.";

    /**
     * 校验当前版本信息
     * @param bean
     * @return
     * @throws Exception
     */
    public VersionVo checkVersion4sisp(VersionBean bean) throws Exception {
        VersionVo versionVo = this.getSqlSessionTemplate().selectOne(NAMESPACE + "checkVersion" , bean);
        return versionVo;
    }

    /**
     * 获取最新版本
     * @param bean
     * @return
     * @throws Exception
     */
    public VersionVo selectVersion4sisp(VersionBean bean) throws Exception {
        VersionVo versionVo = this.getSqlSessionTemplate().selectOne(NAMESPACE + "selectVersion", bean);
        return versionVo;
    }

    /**
     * 更新下载量
     * @param bean
     * @return
     * @throws Exception
     */
    public int updateDownloadNumber4sisp(VersionBean bean) throws Exception {
        int count = this.getSqlSessionTemplate().update(NAMESPACE + "updateDownloadNumber", bean);
        return count;
    }

    /**
     * 新增版本
     * @param bean
     * @return
     * @throws Exception
     */
    public int addVersion4sisp(VersionVo bean) throws Exception {
        int count = this.getSqlSessionTemplate().insert(NAMESPACE + "addVersion", bean);
        return count;
    }

    public int updateVersion4sisp(VersionVo bean) throws Exception {
        int count = this.getSqlSessionTemplate().update(NAMESPACE + "updateVersion", bean);
        return count;
    }
}
