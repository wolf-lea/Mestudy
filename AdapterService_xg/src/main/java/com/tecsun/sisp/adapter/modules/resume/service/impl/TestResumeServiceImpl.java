package com.tecsun.sisp.adapter.modules.resume.service.impl;

import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.resume.entity.request.*;
import com.tecsun.sisp.adapter.modules.resume.entity.response.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by gongshuqi on 2017/10/31 0031.
 */
@Service("TestResumeServiceImpl")
public class TestResumeServiceImpl extends BaseService {

    public static Logger logger = LoggerFactory.getLogger(TestResumeServiceImpl.class);
    public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.resume.service.impl.TestResumeServiceImpl.";
    //获取个人简历信息
    public ResumeVO getResume4cssp(Map map) throws Exception{
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getResume", map);
    }
    //新建简历
    public  Integer addResume4cssp(Map map) throws Exception{
        return this.getSqlSessionTemplate().insert( NAMESPACE + "addResume" ,map);
    }
    //增加期望工作
    public  Integer addExpjob4cssp(Map map) throws Exception{
        return this.getSqlSessionTemplate().insert( NAMESPACE + "addExpjob" ,map);
    }
    //修改个人简历基本信息
    public Integer updateBasicMsg4cssp(BasicMsgBean basicMsgBean) throws Exception{
        return this.getSqlSessionTemplate().update( NAMESPACE + "updateBasicMsg" ,basicMsgBean );
    }
    //查看指定工作经历
    public WorkExpVO getWorkMsg4cssp (Map map)throws Exception{
        return this.getSqlSessionTemplate().selectOne( NAMESPACE + "getWorkMsg" , map);
    }
    //修改工作经历
    public Integer updateWorkMsg4cssp (WorkExpBean workExpBean) throws Exception{
        return this.getSqlSessionTemplate().update( NAMESPACE + "updateWorkMsg" , workExpBean );
    }

    //增加工作经历
    public Integer addWorkMsg4cssp(WorkExpBean workExpBean) throws Exception{
       return this.getSqlSessionTemplate().insert( NAMESPACE + "addWorkMsg" , workExpBean);
    }
    //删除工作经历
    public Integer delWorkMsg4cssp(Map map) throws Exception{
        return this.getSqlSessionTemplate().delete( NAMESPACE + "delWorkMsg" , map);
    }
    //查询指定教育经历
    public EduExpVO getEduMsg4cssp(Map map) throws Exception{
        return this.getSqlSessionTemplate().selectOne( NAMESPACE + "getEduMsg" , map);
    }
    //修改教育经历
    public Integer updateEduMsg4cssp(EduExpBean eduExpBean) throws Exception{
        return this.getSqlSessionTemplate().update( NAMESPACE + "updateEduMsg" ,eduExpBean );
    }
    //增加教育经历
    public Integer addEduMsg4cssp(EduExpBean eduExpBean) throws Exception{
        return this.getSqlSessionTemplate().insert(NAMESPACE + "addEduMsg" , eduExpBean);
    }
    //删除教育经历
    public Integer delEduMsg4cssp(Map map) throws Exception{
        return this.getSqlSessionTemplate().delete( NAMESPACE + "delEduMsg" , map );
    }

    //修改期望工作
    public Integer updateExpMsg4cssp(ExceptJobBean exceptJobBean) throws Exception {
        return this.getSqlSessionTemplate().update( NAMESPACE + "updateExpMsg" , exceptJobBean );
    }


    //查看指定项目经验
    public ProExpVO getProMsg4cssp(Map map)throws Exception{
        return this.getSqlSessionTemplate().selectOne( NAMESPACE + "getProMsg" , map);
    }
    //修改项目经历
    public Integer updateProMsg4cssp (ProExpBean proExpBean) throws Exception {
        return this.getSqlSessionTemplate().update( NAMESPACE + "updateProMsg" , proExpBean );
    }
    //增加项目经历
    public Integer addProMsg4cssp (ProExpBean proExpBean) throws Exception {
        return this.getSqlSessionTemplate().insert(NAMESPACE + "addProMsg" , proExpBean);
    }
    //删除项目经历
    public Integer delProMsg4cssp (Map map) throws Exception {
        return this.getSqlSessionTemplate().delete(NAMESPACE + "delProMsg" , map);
    }
    //修改自我描述
    public Integer updateSumMsg4cssp (Map map) throws Exception {
        return this.getSqlSessionTemplate().update( NAMESPACE + "updateSumMsg" , map );
    }
    //修改技能特长
    public Integer updateSkillMsg4cssp (Map map ) throws Exception {
        return this.getSqlSessionTemplate().update( NAMESPACE + "updateSkillMsg" , map );
    }
    //修改获奖情况
    public Integer updatePrizesMsg4cssp (Map map) throws Exception {
        return this.getSqlSessionTemplate().update( NAMESPACE + "updatePrizesMsg" , map );
    }

    //重新发布简历(修改简历的更新时间)
    public Integer republish4cssp(Map map) throws  Exception{
        return this.getSqlSessionTemplate().update( NAMESPACE + "republish" , map );
    }

    //投递简历
//    public Integer deliverResume4cssp(DeliverResumeVO deliverResumeVO) throws  Exception{
//        return this.getSqlSessionTemplate().insert(NAMESPACE + "deliverResume" , deliverResumeVO );
//    }
    //查询投递的简历列表
    public List<DeliverResumeVO> getDeliverResumeList4cssp(DeliverResumeBean deliverResumeBean) throws  Exception{
        List<DeliverResumeVO> list = this.getSqlSessionTemplate().selectList(NAMESPACE + "getDeliverResumeList" , deliverResumeBean );
        return list;
    }
    //查询投递该职位的简历的总数
    public Integer getCounts4cssp(Map map)throws Exception{
        return this.getSqlSessionTemplate().selectOne( NAMESPACE +"getCounts",map);
    }
    //查询投递该职位的简历未阅读的简历数量
    public Integer getNoReadCounts4cssp(Map map)throws Exception{
        return this.getSqlSessionTemplate().selectOne( NAMESPACE +"getNoReadCounts",map);
    }
    //简历阅读
    public Integer updateType4cssp(Map map)throws Exception{
        return this.getSqlSessionTemplate().update( NAMESPACE +"updateType",map);
    }
    //查询投递该企业的简历的数量
    public Integer getDeliverCounts4cssp(Map map)throws Exception{
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getDeliverCounts", map);
    }
    public Integer getJobCounts4cssp (Map map)throws Exception{
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getJobCounts", map);
    }
    public Integer getLookCounts4cssp(Map map)throws Exception{
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getLookCounts",map);
    }
    public Integer addJobLook4cssp(Map map)throws  Exception{
        return this.getSqlSessionTemplate().insert(NAMESPACE+"addJobLook",map);
    }
    public Integer updateLookCounts4cssp(Map map) throws Exception{
        return this.getSqlSessionTemplate().update(NAMESPACE + "updateLookCounts" ,map);
    }
}
