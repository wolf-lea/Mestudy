package com.tecsun.siboss.tsbm.modules.version.service;

import com.tecsun.siboss.tsbm.common.bean.Page;
import com.tecsun.siboss.tsbm.modules.version.entity.VerCheckRecordParam;
import com.tecsun.siboss.tsbm.modules.version.entity.VerCheckRecordVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by huanghailiang on 15-12-17.
 * 软件版本校验记录
 */
@Service("verCheckRecordService")
public class VerCheckRecordServiceImpl {
    private SqlSessionTemplate sqlSessionTemplate;

    public SqlSessionTemplate getSqlSessionTemplate() {
        return sqlSessionTemplate;
    }

    @Resource
    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }


    public List<VerCheckRecordVO> getList(Map map) throws Exception{
        List<VerCheckRecordVO> list = sqlSessionTemplate.selectList("com.tecsun.siboss.tsbm.modules.version.service.VerCheckRecordMapper.getList", map);
        return list;
    }

    /**
     * @param page  myBatis 分页插件对象
     * @param param 查询条件
     * @return
     */
    public Page<VerCheckRecordVO> getList(Page<VerCheckRecordVO> page, VerCheckRecordParam param) throws Exception{
        param.setPage(page);
        List<VerCheckRecordVO> list = sqlSessionTemplate.selectList("com.tecsun.siboss.tsbm.modules.version.service.VerCheckRecordMapper.getList", param);
        page.setLists(list);
        return page;
    }

    /**
     * 获取单条记录
     * @param id
     * @return
     */
    public VerCheckRecordVO getRecord(Long id) throws Exception{
        VerCheckRecordVO recordVO = sqlSessionTemplate.selectOne("com.tecsun.siboss.tsbm.modules.version.service.VerCheckRecordMapper.getVerCheckRecord", id);
        return recordVO;
    }

    /**
     * 新增
     * @param
     * @return
     */
    public int addRecord(Map param) throws Exception{
        return sqlSessionTemplate.insert("com.tecsun.siboss.tsbm.modules.version.service.VerCheckRecordMapper.addVerCheckRecord", param);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    public int deleteRecord(Long id) throws Exception{
        return sqlSessionTemplate.delete("com.tecsun.siboss.tsbm.modules.version.service.VerCheckRecordMapper.deleteVerCheckRecord", id);
    }
}
