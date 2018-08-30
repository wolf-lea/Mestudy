package com.tecsun.sisp.adapter.modules.ine.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;

import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.ine.entity.request.IneBusBean;
import com.tecsun.sisp.adapter.modules.ine.entity.request.IneQueryBean;
import com.tecsun.sisp.adapter.modules.ine.entity.request.InfoBean;
import com.tecsun.sisp.adapter.modules.ine.entity.response.ApplicationListVo;
import com.tecsun.sisp.adapter.modules.ine.entity.response.ApplicationVo;
import com.tecsun.sisp.adapter.modules.ine.entity.response.PersonalApplicationsVo;
import com.tecsun.sisp.adapter.modules.ine.entity.response.PersonalRecruitsVo;
import com.tecsun.sisp.adapter.modules.ine.entity.response.PositionVo;
import com.tecsun.sisp.adapter.modules.ine.entity.response.RecordVo;
import com.tecsun.sisp.adapter.modules.ine.entity.response.RecruitInfoVo;
import com.tecsun.sisp.adapter.modules.ine.entity.response.RecruitListVo;
import com.tecsun.sisp.adapter.modules.ine.entity.response.RecruitRecordVo;
import com.tecsun.sisp.adapter.modules.ine.util.SqlQueryBean;

/**
 * @author sipeng
 * @email zsp_banyue@163.com
 * @date 2017年10月26日
 */

@Service("IneService")
public class IneServiceImpl extends BaseService{
	
	public static final Logger logger = Logger.getLogger(IneServiceImpl.class);
	
	public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.ine.service.impl.IneServiceImpl.";

	
	/**
	 * 以map返回查询条件
	 * @param ineQueryBean
	 * @return
	 */
	protected Map<String,Object> coverIneQueryBeanToMap(IneQueryBean ineQueryBean){
		Map<String,Object> map = new HashMap<>();
		map.put("sfzh", ineQueryBean.getSfzh());
		map.put("areaCode", ineQueryBean.getAreaCode());
		if(StringUtils.isNotBlank(ineQueryBean.getpCode())){
			
			String d[] = ineQueryBean.getpCode().split(",");
	        List<String> list = new ArrayList<String>();
	        for (int i = 0; i < d.length; i++) {
	        	list.add(d[i]);
	        }
	        map.put("positionCode",  list);
		}
		if(StringUtils.isNotBlank(ineQueryBean.getWorkDate())){
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
			try {
				map.put("workDate", format.parse(ineQueryBean.getWorkDate()));
			} catch (ParseException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
		
		map.put("accountMethod", ineQueryBean.getAccountMethod());
		map.put("infoId", ineQueryBean.getInfoId());
		map.put("opType", ineQueryBean.getOpType());
		map.put("queryType", ineQueryBean.getQueryType());
		map.put("infoType", Constants.T_INFO_TYPE_INE);
		return map;
	}
	
	
	/**
	 * 个人用工-查询求职列表
	 * @param ineQueryBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<ApplicationListVo> getApplicationList4Cssp(IneQueryBean ineQueryBean) {
		Page<ApplicationListVo> page = new Page<>(ineQueryBean.getPageno(),ineQueryBean.getPagesize());
		ineQueryBean.setPage(page);
		List<ApplicationListVo> list = this.getSqlSessionTemplate().selectList(NAMESPACE+"getApplicationList",coverIneQueryBeanToSqlQueryBean(ineQueryBean));
		if(list.size()>0){
		for (int i = 0 ; i < list.size();i++) {
				list.get(i).setPositionName(getPositionName(list.get(i).getPositionCode()));
				list.get(i).setInfoUrl(createInfoUrl(list.get(i).getInfoUrl(),list.get(i).getIneCode(),ineQueryBean.getQueryType()));
				list.get(i).setUpdateTime(getUpdateTimeName(list.get(i).getUpdateTime()));
			}
		}
		page.setData(list);
		return page;
		
	}

	/**
	 * 个人用工-查询招聘列表
	 * @param ineQueryBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<RecruitListVo> getRecruitList4Cssp(IneQueryBean ineQueryBean) {
		//按照区域  按照岗位  按照时间  按照结算方式  获取招工信息
		Page<RecruitListVo> page = new Page<>(ineQueryBean.getPageno(),ineQueryBean.getPagesize());
		ineQueryBean.setPage(page);
		List<RecruitListVo> list = this.getSqlSessionTemplate().selectList(NAMESPACE+"getRecruitList",coverIneQueryBeanToSqlQueryBean(ineQueryBean));
		if(list.size()>0){
			for (RecruitListVo recruitInfoVo : list) {
				recruitInfoVo.setInfoUrl(createInfoUrl(recruitInfoVo.getInfoUrl(),recruitInfoVo.getIneCode(),ineQueryBean.getQueryType()));
				recruitInfoVo.setUpdateTime(getUpdateTimeName(recruitInfoVo.getUpdateTime()));
			}
			page.setData(list);
		}
		return page;
		
	}
	

	/**个人用工-查询单个求职信息详情
	 * @param ineQueryBean
	 * @return
	 */
	public ApplicationVo getApplicationInfoByIneCode4Cssp(
			IneQueryBean ineQueryBean) {
		SqlSessionTemplate session = this.getSqlSessionTemplate();
		ApplicationVo vo =  session.selectOne(NAMESPACE+"getApplicationInfoByIneCode",coverIneQueryBeanToMap(ineQueryBean));
		if(vo != null){
			vo.setPositionName(getPositionName(vo.getPositionCode()));
		}
		return vo;
	}

	/**
	 * 个人用工-查询单个招聘信息详情
	 * @param ineQueryBean
	 * @return
	 */
	public RecruitInfoVo getRecruitInfoByIneCode4Cssp(IneQueryBean ineQueryBean) {
		return this.getSqlSessionTemplate().selectOne(NAMESPACE+"getRecruitInfoByIneCode",coverIneQueryBeanToMap(ineQueryBean));
	}


	/**
	 * 个人用工 - 邀请报名
	 * @param bean
	 * @return
	 */
	public long Register4Cssp(IneBusBean bean) {
		long picId = 0;
        try {
            picId = this.getSqlSessionTemplate().insert(NAMESPACE + "insertRegister", bean);
        } catch (Exception e) {
            logger.error("报名/邀请业务：", e);
        }
        return picId;
	}


	/**
	 * 个人用工- 检查记录是否有效
	 * @param ineQueryBean
	 * @return
	 */
	public boolean checkIsValid4Cssp(IneQueryBean ineQueryBean) {
		boolean f = false;
		try {
			if(Constants.Ine_QueryType_A.equals(ineQueryBean.getQueryType())){
				ApplicationVo applicationVo =  this.getSqlSessionTemplate().selectOne(NAMESPACE+"getApplicationInfoByIneCode",coverIneQueryBeanToMap(ineQueryBean));
				if(applicationVo != null && applicationVo.isValid()){
					f = true;
				}
			}else{
				RecruitInfoVo recruitInfoVo =  this.getSqlSessionTemplate().selectOne(NAMESPACE+"getRecruitInfoByIneCode",coverIneQueryBeanToMap(ineQueryBean));
				if(recruitInfoVo != null && recruitInfoVo.getIsValid()){
					f = true;
				}
			}
		} catch (Exception e) {
			logger.error("检查记录是否有效：", e);
		}
		
		return f;
	}


	/**
	 * 个人用工-删除招工-求职信息（将信息状态更改为无效）
	 * @param ineQueryBean
	 * @return
	 */
	public long deleteWork4Cssp(IneQueryBean ineQueryBean) {
		long infoId = 0;
		try {
			if(Constants.Ine_QueryType_A.equals(ineQueryBean.getQueryType())){
				infoId = this.getSqlSessionTemplate().update(NAMESPACE+"updateApplicationInfoState",coverIneQueryBeanToMap(ineQueryBean));
			}else{
				infoId = this.getSqlSessionTemplate().update(NAMESPACE+"updateRecruitInfoState",coverIneQueryBeanToMap(ineQueryBean));
			}
		} catch (Exception e) {
			logger.error("删除记录：", e);
		}
		return infoId;
	}


	/**
	 * 个人用工- 刷新信息 只更新时间
	 * @param ineQueryBean
	 * @return
	 */
	public long updateIneInfo4Cssp(IneQueryBean ineQueryBean) {
		long infoId = 0;
		try {
			if(Constants.Ine_QueryType_A.equals(ineQueryBean.getQueryType())){
				infoId = this.getSqlSessionTemplate().update(NAMESPACE+"updateApplicationInfo",coverIneQueryBeanToMap(ineQueryBean));
			}else{
				infoId = this.getSqlSessionTemplate().update(NAMESPACE+"updateRecruitInfo",coverIneQueryBeanToMap(ineQueryBean));
			}
		} catch (Exception e) {
			logger.error("刷新纪录：", e);
		}
		return infoId;
	}


	/**个人用工-查询个人招工记录
	 * @param ineQueryBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<PersonalRecruitsVo> getRecruitListBySfzh4Cssp(
			IneQueryBean ineQueryBean) {
		Page<PersonalRecruitsVo> page = new Page<>(ineQueryBean.getPageno(),ineQueryBean.getPagesize());
		ineQueryBean.setPage(page);
		List<PersonalRecruitsVo> list = this.getSqlSessionTemplate().selectList(NAMESPACE+"getRecruitListBySfzh",coverIneQueryBeanToSqlQueryBean(ineQueryBean));
		if(list.size()>0){
			page.setData(list);
		}
		return page;

	}


	/**个人用工 - 查询个人求职记录
	 * @param ineQueryBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Page<PersonalApplicationsVo> getApplicationListBySfzh4Cssp(
			IneQueryBean ineQueryBean) {
		Page<PersonalApplicationsVo> page = new Page<>(ineQueryBean.getPageno(),ineQueryBean.getPagesize());
		ineQueryBean.setPage(page);
		List<PersonalApplicationsVo> list = this.getSqlSessionTemplate().selectList(NAMESPACE+"getApplicationListBySfzh",coverIneQueryBeanToSqlQueryBean(ineQueryBean));
		if(list.size()>0){
			//设置岗位名称
			for(int i= 0 ; i<list.size() ; i++){
				list.get(i).setPositionName(getPositionName(list.get(i).getPositionCode()));
			}
			page.setData(list);
		}
		return page;

	}


	/**
	 * @param infoBean
	 * @return
	 */
	public long createWork4Cssp(InfoBean infoBean) {
		long infoId = 0;
		infoBean.setPositionCode(infoBean.getPositionCode().replace(" ", ""));
		try {
			if(Constants.Ine_QueryType_A.equals(infoBean.getQueryType())){
				infoId = this.getSqlSessionTemplate().insert(NAMESPACE+"insertApplicationInfo", infoBean);
			}else{
				//发布求职信息可以发布多个岗位
				infoId = this.getSqlSessionTemplate().insert(NAMESPACE+"insertRecruitInfo", infoBean);
			}
		} catch (Exception e) {
			logger.error("发布信息业务：", e);
		}
		
		return infoId;
	}


	/**
	 * 修改发布的信息
	 * @param infoBean
	 * @return
	 */
	public long editIneInfo4Cssp(InfoBean infoBean) {
		long infoId = 0;
		try {
			if(Constants.Ine_QueryType_A.equals(infoBean.getQueryType())){
				infoId = this.getSqlSessionTemplate().update(NAMESPACE+"editApplicationInfo",infoBean);
			}else{
				infoId = this.getSqlSessionTemplate().update(NAMESPACE+"editRecruitInfo",infoBean);
			}
		} catch (Exception e) {
			logger.error("刷新纪录：", e);
		}
		return infoId;
	}

	/**
	 * @param ineQueryBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> Page<T> getPersonalRecordsBySfzh4Cssp(
			IneQueryBean ineQueryBean) {
		Page<T> page = new Page<>(ineQueryBean.getPageno(),ineQueryBean.getPagesize());
		ineQueryBean.setPage(page);
		List<RecordVo> list = new ArrayList<>();
		List<RecruitRecordVo> list2 = new ArrayList<>();
		
		if(Constants.Ine_QueryType_A.equals(ineQueryBean.getQueryType())){
			list = this.getSqlSessionTemplate().selectList(NAMESPACE+"getApplicationRecordsBySfzh",coverIneQueryBeanToSqlQueryBean(ineQueryBean));
			for (RecordVo recordVo : list) {
				recordVo.setPositionName(getPositionName(recordVo.getPositionCode()));
			}
		}else{
			list2 = this.getSqlSessionTemplate().selectList(NAMESPACE+"getRecruitRecordsBySfzh",coverIneQueryBeanToSqlQueryBean(ineQueryBean));
		}
		
		if(list.size()>0){
			page.setData((List<T>) list);
		}
		if(list2.size()>0){
			page.setData((List<T>) list2);
		}
		return page;
	}


	/**
	 * 检查是否已经报名或者已经邀请
	 * @param ineQueryBean
	 * @return
	 */
	public boolean checkIsExistInHis4Cssp(IneQueryBean ineQueryBean) {
		long reId = 0;
		reId = this.getSqlSessionTemplate().selectOne(NAMESPACE+"checkIsExistInHis",coverIneQueryBeanToMap(ineQueryBean));
		if(reId > 0 )  return true;
			return false;
	}

	


	/**
	 * 根据id将报名/邀请记录设置为无效
	 * @param recordId
	 * @return
	 */
	public long delPersonalRecord4Cssp(long recordId) {
		long infoId = 0;
		Map<String,Long> map = new HashMap<>();
		map.put("recordId", recordId);
		try {
			infoId = this.getSqlSessionTemplate().update(NAMESPACE+"updatePersonalRecordState",map);
		} catch (Exception e) {
			logger.error("删除记录：", e);
		}
		return infoId;
	}
	/**
	 * 根据岗位code字符串得到名称字符串
	 * @param positionCode
	 * @return
	 */
	protected String getPositionName(String positionCode){
		Map<String,List<String>> map = new HashMap<>();
		List<String> list = new ArrayList<String>();
		if(StringUtils.isNotBlank(positionCode)){
			String d[] = positionCode.split(",");
	        for (int i = 0; i < d.length; i++) {
	        	list.add(d[i]);
	        }
		}
		map.put("positionCode", list);
		return this.getSqlSessionTemplate().selectOne(NAMESPACE+"getPositionName",map);
		
	}
	
	protected List<String> getChildrenPosition(String positionCode) throws Exception{
		List<String> list = new ArrayList<String>();
		Map<String,List<String>> map = new HashMap<>();
		if(StringUtils.isNotBlank(positionCode)){
			String d[] = positionCode.split(",");
	        for (int i = 0; i < d.length; i++) {
	        	list.add(d[i]);
	        }
		}
		map.put("positionCode", list);
		return this.getSqlSessionTemplate().selectList(NAMESPACE+"getChildrenPosition",map);
	}
	
	/**
	 * 拼接url详情
	 * @param infoUrl
	 * @param infoId
	 * @param queryType
	 * @return
	 */
	protected String createInfoUrl(String infoUrl ,long infoId,String queryType){
		return infoUrl+"?infoId="+infoId+"&queryType="+queryType;
	}
	
	/**根据父级岗位编码获取子级岗位信息
	 * @param ineQueryBean
	 * @return
	 */
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

	
	/**
	 * 获取日期显示
	 * @param updateTime
	 * @return
	 */
	protected String getUpdateTimeName(String updateTime){
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			long nowDate = sdf.parse(sdf.format(new Date())).getTime();
			long time = sdf.parse(updateTime.substring(0, 10)).getTime();
			long day = (nowDate - time)/(1000*3600*24);
			if(nowDate == time){
				updateTime = Constants.UPDATE_TIME_NAME_00;
			}else if( day> 1 && day < 2){
				updateTime = Constants.UPDATE_TIME_NAME_01;
			}else if(day>= 2 && day < 3){
				updateTime = Constants.UPDATE_TIME_NAME_02;
			}else if(day>= 3 && day < 4){
				updateTime = Constants.UPDATE_TIME_NAME_03;
			}
			
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return updateTime;
	}
	
	/**
	 * 参数bean转换为sqlbean
	 * @param ineQueryBean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	protected SqlQueryBean coverIneQueryBeanToSqlQueryBean(IneQueryBean ineQueryBean){
		SqlQueryBean bean = new SqlQueryBean();
		List<String> list = new ArrayList<String>();
		bean.setSfzh(ineQueryBean.getSfzh());
		bean.setAreaCode(ineQueryBean.getAreaCode());
		List<String> lists = new ArrayList<>();
		if(StringUtils.isNotBlank(ineQueryBean.getpCode())){
			try {
				lists = getChildrenPosition(ineQueryBean.getpCode());
			} catch (Exception e) {
				logger.error("coverIneQueryBeanToSqlQueryBean：获取子级岗位查询失败", e);
			}
			for (String p : lists) {
				if(Constants.Ine_QueryType_A.equals(ineQueryBean.getQueryType())){
	        		list.add(","+p+",");
	        	}else{
	        		list.add(p);
	        	}
			}
	        bean.setPositionCode(list);
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
		if(StringUtils.isNotBlank(ineQueryBean.getWorkDate())){
			
			try {
				bean.setWorkDate(format.parse(ineQueryBean.getWorkDate()));
			} catch (ParseException e) {
				logger.error("coverIneQueryBeanToSqlQueryBean：日期类型转换失败", e);
			}
		}
		bean.setInfoId(ineQueryBean.getInfoId());
		bean.setInfoType(ineQueryBean.getQueryType());
		bean.setOpType(ineQueryBean.getOpType());
		bean.setQueryType(ineQueryBean.getQueryType());
		bean.setAccountMethod(ineQueryBean.getAccountMethod());
		bean.setPage(ineQueryBean.getPage());
		return  bean;
		
	}

}
