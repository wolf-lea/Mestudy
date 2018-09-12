package com.tecsun.siboss.tsbm.modules.version.service;

import com.tecsun.siboss.tsbm.common.bean.Page;
import com.tecsun.siboss.tsbm.modules.version.entity.VersionOptlogParam;
import com.tecsun.siboss.tsbm.modules.version.entity.VersionOptlogVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuxiaokai on 15-12-19.
 */
@Service("versionOptLogService")
public class VersionOptlogServiceImpl {
    private SqlSessionTemplate sqlSessionTemplate;

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Resource
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    /**
     * @param page  myBatis 分页插件对象
     * @param param 查询条件
     * @return
     */
    public Page<VersionOptlogVO> getList(Page<VersionOptlogVO> page, VersionOptlogParam param) throws Exception{
        param.setPage(page);
        List<VersionOptlogVO> list = sqlSessionTemplate.selectList("com.tecsun.siboss.tsbm.modules.version.service.VersionOptlogMapper.getList", param);
        page.setLists(list);
        return page;
    }

    public List<VersionOptlogVO> getAllDataList() {
        List<VersionOptlogVO> list = sqlSessionTemplate.selectList("com.tecsun.siboss.tsbm.modules.version.service.VersionOptlogMapper.getAllDataList");
        return list;
    }

    /**
     * 获取单条记录
     * @param id
     * @return
     */
    public VersionOptlogVO getVersionOptlog(Long id) {
        VersionOptlogVO versionOptlogVO = sqlSessionTemplate.selectOne("com.tecsun.siboss.tsbm.modules.version.service.VersionOptlogMapper.getVersionOptlog", id);
        return versionOptlogVO;
    }

    /**
     * 新增
     * @param
     * @return
     */
    public int addVersionOptlog(Map param) throws Exception{
        return sqlSessionTemplate.insert("com.tecsun.siboss.tsbm.modules.version.service.VersionOptlogMapper.addVersionOptlog", param);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteVersionOptlog(Long id) {
        return sqlSessionTemplate.delete("com.tecsun.siboss.tsbm.modules.version.service.VersionOptlogMapper.deleteVersionOptlog", id);
    }
}
