package com.tecsun.sisp.adapter.modules.fairJob.service.impl;

import com.tecsun.sisp.adapter.common.util.CommUtil;
import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.JedisUtil;
import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl;
import com.tecsun.sisp.adapter.modules.fairJob.entity.request.*;
import com.tecsun.sisp.adapter.modules.fairJob.entity.response.*;
import com.tecsun.sisp.adapter.modules.ine.entity.request.IneQueryBean;
import com.tecsun.sisp.adapter.modules.ine.entity.response.PositionVo;
import com.tecsun.sisp.adapter.modules.resume.entity.request.ResumeBean;
import com.tecsun.sisp.adapter.modules.resume.entity.response.Resume;

import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/11/2 0002.
 */
@Service("TestFairJobServiceImpl")
public class TestFairJobServiceImpl extends BaseService {
    public static Logger logger = LoggerFactory.getLogger(TestFairJobServiceImpl.class);
    @Autowired
    private CommServiceImpl commService;
    public final static String NAMESPACE ="com.tecsun.sisp.adapter.modules.fairJob.service.impl.TestFairJobServiceImpl.";
    //查询招聘会列表
    public List<JobFairVO> getJobFairList4cssp(JobFairBean jobFairBean) throws Exception{
        List<JobFairVO> list=this.getSqlSessionTemplate().selectList( NAMESPACE + "getJobFairList" ,jobFairBean );
        return  list;
    }
    //查询参加指定招聘会的企业列表
    public List<FairCoVO> getJobCoList4cssp(JobFairBean jobFairbean) throws Exception{
        List<FairCoVO> list=this.getSqlSessionTemplate().selectList( NAMESPACE + "getJobCoList" ,jobFairbean );
        return  list;
    }
    //查询指定企业的岗位列表
    public List<FairPositionVo> getCoPosList4cssp(FairCoBean fairCoBean) throws Exception{
        List<FairPositionVo> list=this.getSqlSessionTemplate().selectList( NAMESPACE + "getCoPosList" , fairCoBean);
        return list;
    }
    //查看企业指定岗位详情
    public JobVO getJobDetail4cssp(JobBean jobBean) throws Exception{
        return this.getSqlSessionTemplate().selectOne( NAMESPACE + "getJobDetail" , jobBean);
    }
    public List<String> getWelfare4sisp(Map map) throws Exception{
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getWelfare", map);
    }
    public List<String> getJobProperty4sisp(Map map) throws Exception{
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getJobProperty", map);
    }
    public String getArea4sisp(String code) throws Exception{
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getArea", code);
    }
    //查询指定公司的简介
    public FairCoVO getCoDetail4cssp(Map map) throws  Exception{
        return this.getSqlSessionTemplate().selectOne( NAMESPACE + "getCoDetail" , map);
    }
    //查看招聘会的企业的岗位详情
    public FairPositionVo getFairJobDetail4cssp(Map map) throws  Exception{
        return this.getSqlSessionTemplate().selectOne( NAMESPACE + "getFairJobDetail" , map);
    }
    protected Map<String,Object> coverJobBeanToMap(JobBean jobBean){
        Map<String,Object> map = new HashMap<>();
        map.put("sfzh", jobBean.getSfzh());
        map.put("pageno",jobBean.getPageno());
        map.put("pagesize",jobBean.getPagesize());
        //map.put("areaCode", jobBean.getAreaCode());
        if(StringUtils.isNotBlank(jobBean.getJobProperty())){
            String d[] = jobBean.getJobProperty().split(",");
            List<String> list = new ArrayList<>();
            for (int i = 0; i < d.length; i++) {
                list.add(d[i]);
            }
            map.put("jobProperty",  list);
        }
        map.put("name", jobBean.getName());
        map.put("educationList",jobBean.getEducationList());
        map.put("workingSeniorityList",jobBean.getWorkingSeniorityList());
        map.put("jobPropertyList",jobBean.getJobPropertyList());
        map.put("industryList",jobBean.getIndustryList());
        map.put("salary",jobBean.getSalary());
        map.put("areaId", jobBean.getAreaId());
        map.put("industry", jobBean.getIndustry());
        map.put("salaryMin", jobBean.getSalaryMin());
        map.put("salaryMax", jobBean.getSalaryMin());
        map.put("updateTime", jobBean.getUpdateTime());
        map.put("education", jobBean.getEducation());
        map.put("workingSeniority", jobBean.getWorkingSeniority());
        map.put("infoType", Constants.T_INFO_TYPE_FAIRJOB);

        return map;
    }

    //企业招聘个人端职位列表查询getJobVOList
    public List<JobVO> getJobVOList4cssp(JobBean jobBean) throws Exception{
        List<JobVO> list=this.getSqlSessionTemplate().selectList( NAMESPACE + "getJobVOList" , jobBean);
        return list;
    }

    //收藏职位
    public Integer collectJob4cssp(CollectJobBean collectJobBean)throws Exception{
        return this.getSqlSessionTemplate().insert(NAMESPACE + "collectJob", collectJobBean);
    }
    //查看收藏职位列表
    public List<CollectJobVO> getCollectJobList4cssp(CollectJobBean collectJobBean) throws Exception{
        List<CollectJobVO> list=this.getSqlSessionTemplate().selectList( NAMESPACE + "getCollectJobList" ,collectJobBean);
        return list;
    }
    //取消收藏
    public Integer delCollectJob4cssp(Map map) throws Exception{
        return this.getSqlSessionTemplate().delete( NAMESPACE + "delCollectJob" ,map);
    }
    //申请职位
    public Integer applyJob4cssp(ApplyJobBean applyJobBean)throws Exception{
        return this.getSqlSessionTemplate().insert(NAMESPACE + "applyJob", applyJobBean);
    }
    //查看申请职位列表
    public List<ApplyJobVO> getApplyJobList4cssp(ApplyJobBean applyJobBean) throws Exception{
        List<ApplyJobVO> list=this.getSqlSessionTemplate().selectList( NAMESPACE + "getApplyJobList" ,applyJobBean);
        return list;
    }
    //获得热门城市
    public List<CityVo> getHotCity4sisp() {
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getHotCity");
    }
    public List<CityVo> getAllCity4sisp() {
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getAllCity");
    }

    //新增招聘会
    public Integer addJobFair4cssp(JobFairBean jobFairBean) throws Exception{
        return  this.getSqlSessionTemplate().insert(NAMESPACE + "addJobFair", jobFairBean);
    }
    //修改招聘会信息
    public Integer updateJobFair4cssp(JobFairBean jobFairBean) throws Exception{
        return this.getSqlSessionTemplate().update(NAMESPACE + "updateJobFair", jobFairBean);
    }
    //查看招聘会信息详情
    public JobFairVO jobFairDetail4cssp(Map map) throws Exception{
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "jobFairDetail", map);
    }
    //查询企业信息列表包含模糊查询
    public List<CoUserVO> getCoUserList4cssp(CoUserBean coUserBean) throws Exception{
        List<CoUserVO> list=this.getSqlSessionTemplate().selectList( NAMESPACE + "getCoUserList" ,coUserBean);
        return list;
    }
    /*企业账号状态设置*/
    public Integer checkCoUser4cssp(CoUserBean coUserBean) throws  Exception{
        return this.getSqlSessionTemplate().update( NAMESPACE + "checkCoUser", coUserBean);
    }
    //删除企业信息
    public Integer delCoUser4cssp(Map map) throws  Exception{
        return this.getSqlSessionTemplate().delete(NAMESPACE + "delCoUser", map);
    }
    //查看企业信息详情
    public CoUserVO detailCoUser4cssp(Map map) throws Exception{
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "detailCoUser", map);
    }


    //招聘会企业列表查询
    public List<FairCoVO> getFairCoList4cssp(FairCoBean fairCoBean) throws Exception{
        List<FairCoVO> list=this.getSqlSessionTemplate().selectList( NAMESPACE + "getFairCoList",fairCoBean );
        return list;
    }
    //查询未结束招聘会信息
    public List<Map<String,Object>> getJobFairName4cssp() throws Exception{
        return this.getSqlSessionTemplate().selectList( NAMESPACE + "getJobFairName");
    }
    //添加招聘会公司与职位信息
    public Integer addFairCo4cssp(FairCoBean fairCoBean) throws Exception{
        int id = 0;
        SqlSessionTemplate sqlSession = this.getSqlSessionTemplate();
        id = sqlSession.insert( NAMESPACE + "addFairCo" ,fairCoBean);
        if(id > 0 ){
            String  coId = fairCoBean.getId();
            List<FairPositionBean> fairPositionBeans = fairCoBean.getFairPositionBeans();
            for (FairPositionBean fairPositionBean : fairPositionBeans) {
                fairPositionBean.setCoId(coId);
            }
            sqlSession.insert(NAMESPACE + "addFairPosition" ,fairPositionBeans);
        }
        return id;
    }
    // 检测该公司所参与的招聘会是否已结束
    public FairCoVO checkUpdate4cssp(Map map) throws Exception{
        return this.getSqlSessionTemplate().selectOne( NAMESPACE + "checkUpdate" ,map);
    }
    //修改招聘会公司信息
    public Integer updateFairCo4cssp(FairCoBean fairCoBean) throws Exception{
    	int id = 0;
        SqlSessionTemplate sqlSession = this.getSqlSessionTemplate();
        id =sqlSession.update( NAMESPACE + "updateFairCo" ,fairCoBean);
        if(id >0 ){
        	List<FairPositionBean> fairPositionBeans = fairCoBean.getFairPositionBeans();
        	List<FairPositionBean> list1 = new ArrayList<>();
        	for (FairPositionBean fairPositionBean : fairPositionBeans) {
        		if(StringUtils.isNotBlank(fairPositionBean.getCoId())){//当传递的岗位为新数据 则需要执行插入操作
        			sqlSession.update( NAMESPACE + "updateFairPosition" , fairPositionBean);
        		}else{
        			fairPositionBean.setCoId(fairCoBean.getId());
        			list1.add(fairPositionBean);
        		}
			}
        	if(list1.size()>0){
        		sqlSession.insert(NAMESPACE + "addFairPosition" ,list1);
        	}
        }
        return id;
    }
    //修改招聘会岗位信息
    public Integer updateFairPosition4cssp(FairPositionBean fairPositionBean)throws Exception{
        return this.getSqlSessionTemplate().update( NAMESPACE + "updateFairPosition" , fairPositionBean);
    }
    
    //修改招聘会企业与该企业所招聘岗位的信息
    public Integer updatePosList4cssp(List<FairPositionBean> list)throws Exception{
        return this.getSqlSessionTemplate().update( NAMESPACE + "updatePosList" ,list);
    }
    //删除招聘会公司信息
    public Integer delFairCo4cssp(Map map)throws Exception{
        return this.getSqlSessionTemplate().delete(NAMESPACE + "delFairCo", map);
    }

    //删除招聘会公司所招聘岗位信息
    public Integer delFairPositionByCoId4cssp(Map map)throws Exception{
        return this.getSqlSessionTemplate().delete( NAMESPACE  + "delFairPositionByCoId", map);
    }

    public FairCoVO getFariCoDetail4cssp(Map map)throws Exception{
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getFariCoDetail", map);
    }
    public List<FairPositionVo> getFairPosition4cssp(Map map)throws Exception{
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getFairPosition", map);
    }

    //删除招聘会岗位信息
    public Integer delFairPosition4cssp(Map map)throws Exception{
        return this.getSqlSessionTemplate().delete(NAMESPACE + "delFairPosition", map);
    }

    //人才搜索
    public List<Resume> getResumeList4cssp(ResumeBean resumeBean) throws Exception{
        List<Resume> list=this.getSqlSessionTemplate().selectList( NAMESPACE + "getResumeList" ,resumeBean);
        return list;
    }
    //增加企业信息
    public Integer addCoUser4cssp(CoUserBean coUserBean)throws Exception{
        return this.getSqlSessionTemplate().insert(NAMESPACE + "addCoUser", coUserBean);
    }
    //修改企业帐号信息
    public Integer updateCoUser4cssp(CoUserBean coUserBean)throws Exception{
        return this.getSqlSessionTemplate().insert(NAMESPACE + "updateCoUser", coUserBean);
    }
    //发布新职位
    public Integer addJob4cssp (JobBean jobBean)throws Exception{
        return this.getSqlSessionTemplate().insert(NAMESPACE +"addJob",jobBean);
    }
    //编辑职位
    public Integer editJob4cssp(JobBean jobBean)throws Exception{
        return this.getSqlSessionTemplate().update(NAMESPACE + "editJob", jobBean);
    }
    //查询指定企业的岗位列表
    public List<JobVO> getJobList4cssp(JobBean jobBean)throws Exception{
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getJobList", jobBean);
    }
	/**
	 * @param roleId 
	 * @param
	 * @param
	 * @return
	 */
	public long addUser4sisp(CoUserBean coUserBean, long roleId) {
		long userId = 0 ;
		SqlSession sql = this.getSqlSessionTemplate();
		try {
			userId = sql.insert(NAMESPACE +"addUser",coUserBean);
		} catch (Exception e) {
			userId = -1;
			logger.error("审核注册-用户添加异常:",e);
		}
		if(userId > 0){
			userId = coUserBean.getUserId();
			Map urBean = new HashMap<>();
			urBean.put("userId",userId);
    		urBean.put("roleId",roleId);
    		try {
    			sql.insert(NAMESPACE +"addUserRole",urBean);
			} catch (Exception e) {
				userId = -1;
				logger.error("审核注册-用户角色绑定异常:", e);
			}
		}else{
			userId = -1;
		}
		if(userId >0){
			try {
				JedisUtil.setValueLong("sisp_public:cmp_core:user:{" + userId + "}:rolelist", String.valueOf(roleId));
			} catch (Exception e) {
				logger.error("审核注册-用户角色权限存入redis失败:", e);
			}
		}
		return  userId;
	}
    public PositionVo getPositionName4cssp(Map map)throws Exception{
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getPositionName", map);
    }
    public List<WelfareVo> getWelfareList4sisp()throws Exception{
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getWelfareList");
    }

	/**根据用户名改变状态
	 * @param logName
	 * @param status
	 * @return
	 */
	public Integer updateUserStatusByLogName4Sisp(String logName, String status) {
		int id = 0 ;
		Map map = new HashMap<>();
		map.put("logName", logName);
		if("2".equals(status)){//当状态值为2时 表示停用    为3时表示通过
			status = "0";
		}else{
			status = "1";
		}
		map.put("status", status);
		try {
			id = this.getSqlSessionTemplate().update(NAMESPACE + "updateUserStatusByLogName",map);
		} catch (Exception e) {
			id = -1;
			logger.error("根据用户名更改用户状态失效:", e);
		}
		return id;
	}
	
	public CoUserVO getLogInfoBycoUserId4Cssp(Map map){
		return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getLogInfoBycoUserId", map);
	}

    public List<String> getParentPosition4cssp() throws Exception{
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getParentPosition");
    }
    public List<String> getPositionCodeList4cssp(Map<String,Object> map) throws Exception{
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getPositionCodeList", map);
    }
    //刷新企业职位信息
    public Integer refreshJob4cssp(JobBean jobBean) throws Exception{
        return this.getSqlSessionTemplate().update(NAMESPACE + "refreshJob" ,jobBean);
    }

    public Page<PositionVo> getPositionList4Cssp(IneQueryBean ineQueryBean) {
        Map<String,String> map = new HashMap<>();
        if(ineQueryBean == null){
            ineQueryBean = new IneQueryBean();
        }
        Page<PositionVo> page = new Page<>(ineQueryBean.getPageno(),100);
        map.put("parentCode", ineQueryBean.getpCode());
        map.put("queryType", ineQueryBean.getQueryType());
        List<PositionVo> list = new ArrayList<>();
        list = this.getSqlSessionTemplate().selectList(NAMESPACE + "getPositionList", map);
        if(list.size()>0){
            page.setData(list);
            page.setCount(list.size());
        }
        return page;
    }
}
