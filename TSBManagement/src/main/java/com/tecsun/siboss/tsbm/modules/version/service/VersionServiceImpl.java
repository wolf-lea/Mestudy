package com.tecsun.siboss.tsbm.modules.version.service;

import com.tecsun.siboss.tsbm.common.bean.Page;
import com.tecsun.siboss.tsbm.modules.version.entity.*;
import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhuxiaokai on 15-12-17.
 */
@Service("versionService")
public class VersionServiceImpl {
    private SqlSessionTemplate sqlSessionTemplate;

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Resource
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    public List<VersionVO> getList(VersionParam param) throws Exception{
        List<VersionVO> list = sqlSessionTemplate.selectList("com.tecsun.siboss.tsbm.modules.version.service.VersionMapper.getList", param);
        return list;
    }

    /**
     * @param page  myBatis 分页插件对象
     * @param param 查询条件
     * @return
     */
    public Page<VersionVO> getList(Page<VersionVO> page, VersionParam param) throws Exception{
        param.setPage(page);
        List<VersionVO> list = sqlSessionTemplate.selectList("com.tecsun.siboss.tsbm.modules.version.service.VersionMapper.getList", param);
        page.setLists(list);
        return page;
    }

    /**
     * 获取所有软件版本
     * @param page  myBatis 分页插件对象
     * @return
     */
    public Page<VersionVO> getAllDataList(Page<VersionVO> page, VersionParam param) throws Exception{
        param.setPage(page);
        List<VersionVO> list = sqlSessionTemplate.selectList("com.tecsun.siboss.tsbm.modules.version.service.VersionMapper.getAllDataList", param);
        page.setLists(list);
        return page;
    }

    /**
     * 获取单条记录
     * @param id
     * @return
     */
    public VersionVO getVersion(Long id) throws Exception{
        VersionVO versionVO = sqlSessionTemplate.selectOne("com.tecsun.siboss.tsbm.modules.version.service.VersionMapper.getVersion", id);
        return versionVO;
    }

    /**
     * 新增
     * @param
     * @return
     */
    public int addVersion(Map param) throws Exception{
        return sqlSessionTemplate.insert("com.tecsun.siboss.tsbm.modules.version.service.VersionMapper.addVersion", param);
    }

    /**
     * 修改
     * @param
     * @return
     */
    public int updateVersion(Map param) throws Exception{
        return sqlSessionTemplate.update("com.tecsun.siboss.tsbm.modules.version.service.VersionMapper.updateVersion", param);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteVersion(Long id) throws Exception{
        return sqlSessionTemplate.delete("com.tecsun.siboss.tsbm.modules.version.service.VersionMapper.deleteVersion", id);
    }

    /*查询所有软件版本，去重*/
     public Page<VersionVO> queryAllVersion(Page<VersionVO> page,String deviceType, String name, String version) throws Exception{
         VersionParam param = new VersionParam();
         param.setPage(page);
         param.setDeviceType(deviceType);
         param.setIsDelete("N");
         if(StringUtils.isNotBlank(name)){
             param.setName(name);
         }
         if(StringUtils.isNotBlank(version)){
            param.setVersion(version);
         }
         //以发布状态
         param.setStatus("2");

         List<VersionVO> list = sqlSessionTemplate.selectList("com.tecsun.siboss.tsbm.modules.version.service.VersionMapper.getList",param);
         page.setLists(list);
         return page;
    }

    /**
     * 获取版本表序列
     * @return
     */
    public long getSequence() throws Exception{
        long id = sqlSessionTemplate.selectOne("com.tecsun.siboss.tsbm.modules.version.service.VersionMapper.getSequence");
        return id;
    }

    /**
     * 更新设备信息表的设备软件版本信息
     */
    public int updateDeviceInfo(DeviceVO po) {
        return this.getSqlSessionTemplate().update("com.tecsun.siboss.tsbm.modules.version.service.VersionMapper.updateDeviceInfo", po);
    }

    /**
     * 更新队列表内的记录的状态
     */
    public int updateList(VersionUpdateListVO po){
        return this.getSqlSessionTemplate().update("com.tecsun.siboss.tsbm.modules.version.service.VersionMapper.updateList", po);
    }

    /*根据ID查询*/
    public VersionUpdateListVO getByDeviceId(Long deviceNo){
        return this.getSqlSessionTemplate().selectOne("com.tecsun.siboss.tsbm.modules.version.service.VersionMapper.getByDeviceId", deviceNo);
    }

    /**
     * 根据软件版本号查找软件版本信息
     */
    public VersionVO getByVersion(String version, String appType) {
        Map map = new HashMap<String, String>();
        map.put("version", version);
        map.put("appType", appType);
        return this.getSqlSessionTemplate().selectOne("com.tecsun.siboss.tsbm.modules.version.service.VersionMapper.getByVersion", map);
    }
}
