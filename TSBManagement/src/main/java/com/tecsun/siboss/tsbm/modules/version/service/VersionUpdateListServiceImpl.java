package com.tecsun.siboss.tsbm.modules.version.service;

import com.tecsun.siboss.tsbm.common.bean.Page;
import com.tecsun.siboss.tsbm.modules.version.entity.VersionUpdateListParam;
import com.tecsun.siboss.tsbm.modules.version.entity.VersionUpdateListVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by huanghailiang on 15-12-17.
 * 软件版本更新队列
 */
@Service("versionUpdateListService")
public class VersionUpdateListServiceImpl {
    private SqlSessionTemplate sqlSessionTemplate;

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Resource
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }


    public List<VersionUpdateListVO> getList(VersionUpdateListParam param) {
        List<VersionUpdateListVO> list = sqlSessionTemplate.selectList("com.tecsun.siboss.tsbm.modules.version.service.VersionUpdateListMapper.getList", param);
        return list;
    }
    /**
     * @param page  myBatis 分页插件对象
     * @param param 查询条件
     * @return
     */
    public Page<VersionUpdateListVO> getList(Page<VersionUpdateListVO> page, VersionUpdateListParam param) throws Exception{
        param.setPage(page);
        List<VersionUpdateListVO> list = sqlSessionTemplate.selectList("com.tecsun.siboss.tsbm.modules.version.service.VersionUpdateListMapper.getList", param);
        page.setLists(list);
        return page;
    }

    /**
     * 获取单条记录
     * @param id
     * @return
     */
    public VersionUpdateListVO getUpdateListVO(Long id) throws Exception{
        VersionUpdateListVO listVO = sqlSessionTemplate.selectOne("com.tecsun.siboss.tsbm.modules.version.service.VersionUpdateListMapper.getVersionUpdateList", id);
        return listVO;
    }

    /**
     * 新增
     * @param
     * @return
     */
    public int addVerUpdateList(Map param) throws Exception{
        return sqlSessionTemplate.insert("com.tecsun.siboss.tsbm.modules.version.service.VersionUpdateListMapper.addVersionUpdateList", param);
    }

    /**
     * 修改
     * @param
     * @return
     */
    public int modifyVerUpdateList(VersionUpdateListVO vo) throws Exception{
        Map param = new HashMap();
        Long id = vo.getId();
        param.put("id", id);
        param.put("updateType", vo.getUpdateType());
        param.put("remark",vo.getRemark());
        param.put("modTime",new Date());
        param.put("modUser",vo.getModUser());
        param.put("versionId",vo.getVersionId());
        return sqlSessionTemplate.update("com.tecsun.siboss.tsbm.modules.version.service.VersionUpdateListMapper.modifyVersionUpdateList", param);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteVerUpdateList(int id) throws Exception{
        return sqlSessionTemplate.delete("com.tecsun.siboss.tsbm.modules.version.service.VersionUpdateListMapper.deleteVersionUpdateList", id);
    }

    public VersionUpdateListVO check(String deviceNo, String versionId) throws Exception{
        Map map = new HashMap();
        map.put("deviceNo", deviceNo);
        map.put("versionId", versionId);
        map.put("status","1");
        map.put("updateType","2");
        return sqlSessionTemplate.selectOne("com.tecsun.siboss.tsbm.modules.version.service.VersionUpdateListMapper.check", map);
    }

    /**
     * 根据软件版本id查找更新记录
     * @param id
     * @return
     */
    public List<VersionUpdateListVO> getByVerId(Long id) throws Exception{
        return sqlSessionTemplate.selectList("com.tecsun.siboss.tsbm.modules.version.service.VersionUpdateListMapper.getByVerId", id);
    }
}
