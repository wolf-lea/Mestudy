package com.tecsun.sisp.adapter.modules.fairJob.controller;

import com.tecsun.sisp.adapter.common.util.*;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.common.entity.request.PicBean;
import com.tecsun.sisp.adapter.modules.common.entity.request.PicBusBean;
import com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl;
import com.tecsun.sisp.adapter.modules.fairJob.entity.request.*;
import com.tecsun.sisp.adapter.modules.fairJob.entity.response.*;
import com.tecsun.sisp.adapter.modules.fairJob.service.impl.TestFairJobServiceImpl;
import com.tecsun.sisp.adapter.modules.fairJob.service.impl.UserServiceImpl;
import com.tecsun.sisp.adapter.modules.ine.entity.request.IneQueryBean;
import com.tecsun.sisp.adapter.modules.ine.entity.response.PositionVo;
import com.tecsun.sisp.adapter.modules.resume.entity.request.ResumeBean;
import com.tecsun.sisp.adapter.modules.resume.entity.response.Resume;
import com.tecsun.sisp.adapter.modules.resume.entity.response.ResumeVO;
import com.tecsun.sisp.adapter.modules.resume.service.impl.TestResumeServiceImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2017/11/3 0003.
 */
@Controller
@RequestMapping(value = "/adapter/fairJob")
public class FairJobController extends BaseController {
	//角色id定义
	private static long roleId = Integer.parseInt(Config.getInstance().get("fairJob_role_id"));
	
    public final static Logger logger = Logger.getLogger(FairJobController.class);
    @Autowired
    private TestFairJobServiceImpl fairJobService;
    @Autowired
    private CommServiceImpl commService;
    @Autowired
    private TestResumeServiceImpl resumeService;
    @Autowired
    private UserServiceImpl userService;

    /**
     * 获得招聘会列表
     *
     * @param jobFairBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getJobFairList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getJobFairList(@RequestBody JobFairBean jobFairBean) throws Exception {
        logger.info("getJobFairList" + CommUtil.getNowDateLongStr());
        String statusCode;
        String message;
        Page<JobFairVO> page=new Page<>(jobFairBean.getPageno(),jobFairBean.getPagesize());
        List<JobFairVO> list=new ArrayList<>();
            jobFairBean.setBeginTime(CommUtil.getNowDateLongStr());//beginTime当作当前时间
        try {
            jobFairBean.setPage(page);
            list= fairJobService.getJobFairList4cssp(jobFairBean);
            if (list.size() > 0) {
                message = "招聘会信息列表查询成功";
                int i = list.size();
                for (int j = 0; j < i; j++) {
                    Integer a = compareDate(list.get(j).getBeginTime(), list.get(j).getEndTime());
                    if (a == 0) {
                        list.get(j).setStatus("未开始");
                    } else if (a == 1) {
                        list.get(j).setStatus("进行中");
                    } else {
                        list.get(j).setStatus("已结束");
                    }
                }
                page.setData(list);
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
            } else {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "暂无招聘会信息";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("查询招聘会信息列表出错：", e);
        }
        return result(statusCode, message, page);
    }

    //日期比较方法
    private Integer compareDate(String beginTime, String endTime) throws Exception {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date beginDate = df.parse(beginTime);
        Date endDate = df.parse(endTime);
        Date currentDate = new Date();
        if (currentDate.getTime() < beginDate.getTime()) {
            return 0;//未开始
        } else if (currentDate.getTime() > endDate.getTime()) {
            return 2;//已结束
        } else {
            return 1;//进行中
        }
    }

    /**
     * 查询指定招聘会的单位列表
     *
     * @param fairCoBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getJobCoList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getJobCoList(@RequestBody JobFairBean jobfairbean) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(jobfairbean.getId())) return error("招聘会id不能为空");
        String statusCode;
        String message;
        Page<FairCoVO> page=new Page<>(jobfairbean.getPageno(),jobfairbean.getPagesize());
        List<FairCoVO> list=new ArrayList<>();
        try {
        	jobfairbean.setPage(page);
           list = fairJobService.getJobCoList4cssp(jobfairbean);
            if (list.size() > 0) {
                message = "查询指定招聘会的企业列表成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                page.setCount(list.size());
                page.setData(list);
            } else {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "查询指定招聘会的企业列表暂无";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("查询指定招聘会的企业列表异常：", e);
        }
        return result(statusCode, message, page);
    }

    /**
     * 查询指定单位的岗位列表
     *
     * @param fairCoBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getCoPosList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCoPosList(@RequestBody FairCoBean fairCoBean) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(fairCoBean.getId())) return error("企业id不能为空");
        String statusCode;
        String message;
        Page<FairPositionVo> page=new Page<>(fairCoBean.getPageno(),fairCoBean.getPagesize());
        List<FairPositionVo> list=new ArrayList<>();
        try {
        	 fairCoBean.setPage(page);
            list = fairJobService.getCoPosList4cssp(fairCoBean);
            if (list.size() > 0) {
                message = "查询指定企业的岗位列表成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                page.setData(list);
            } else {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "查询指定企业的岗位列表暂无";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("查询指定企业的岗位列表异常：", e);
        }
        return result(statusCode, message, page);
    }

    /**
     * 指定岗位详情查询
     *
     * @param jobBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getJobDetail", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getJobDetail(@RequestBody JobBean jobBean) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(jobBean.getId())) return error("岗位id不能为空");
        String statusCode;
        String message;
        try {
            JobVO jobVO = fairJobService.getJobDetail4cssp(jobBean);
            statusCode = Constants.RESULT_MESSAGE_SUCCESS;
            if (jobVO != null) {
                message = "查询指定岗位详情成功";
                String areaName=fairJobService.getArea4sisp(jobVO.getAreaId());
                Map map1=new HashMap();
                map1.put("welfareName",ttt(jobVO.getWelfare()));
                List<String> welfare=fairJobService.getWelfare4sisp(map1);
                jobVO.setWelfareName(welfare);
                jobVO.setAreaIdName(areaName);
                jobVO.setUrl("http://192.168.1.35:180/sisp/resume/detail.html?id="+jobBean.getId());
                Map<String,Object> map2=new HashMap();
                map2.put("jobId",jobVO.getId());
                Integer counts=resumeService.getLookCounts4cssp(map2);
                if (counts!=null){
                    resumeService.updateLookCounts4cssp(map2);
                    counts=resumeService.getLookCounts4cssp(map2);
                    jobVO.setLookCounts(counts);
                }else {
                    Integer num=resumeService.addJobLook4cssp(map2);
                    if(num>0){
                        counts=resumeService.getLookCounts4cssp(map2);
                        jobVO.setLookCounts(counts);
                    }
                }
                return result(statusCode, message, jobVO);
            } else {
                message = "查询指定岗位详情失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("查询指定岗位详情异常：", e);
        }
        return result(statusCode, message);
    }

    private List ttt(String strr){
        if(strr != null && strr != ""){
            String[] str = strr.split(",");
            List list = new ArrayList();
            for (int i = 0; i < str.length; i++) {
                list.add(str[i]);
            }
            return list;
        }else {
            return null;
        }
    }

    /**
     * 查询指定公司简介
     *
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getCoDetail", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCoDetail(@RequestBody Map<String, Object> map) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(map.get("id"))) return error("企业id不能为空");
        String statusCode;
        String message;
        try {
            FairCoVO fairCoVo = fairJobService.getCoDetail4cssp(map);
            statusCode = Constants.RESULT_MESSAGE_SUCCESS;
            if (fairCoVo != null) {
                message = "查询指定企业简介成功";
                return result(statusCode, message, fairCoVo);
            } else {
                message = "查询指定企业简介失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("查询指定企业简介异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 查询招聘会指定企业岗位详情
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getFairJobDetail", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getFairJobDetail(@RequestBody Map<String, Object> map) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(map.get("id"))) return error("企业id不能为空");
        String statusCode;
        String message;
        try {
            FairPositionVo fairPositionVo = fairJobService.getFairJobDetail4cssp(map);
            statusCode = Constants.RESULT_MESSAGE_SUCCESS;
            if (fairPositionVo != null) {
                message = "查询招聘会指定企业岗位详情成功";
                if(fairPositionVo.getCity()!=null){
                    fairPositionVo.setCity(fairJobService.getArea4sisp(fairPositionVo.getCity()));
                }
                return result(statusCode, message, fairPositionVo);
            } else {
                message = "查询招聘会指定企业岗位详情失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("查询招聘会指定企业岗位详情异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 企业招聘个人端职位列表查询getJobVOList
     *
     * @param jobBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getJobVOList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getJobVOList(@RequestBody JobBean jobBean) throws Exception {

        String statusCode;
        String message;
        Page<JobVO> page = new Page<>(jobBean.getPageno(),jobBean.getPagesize());
        List<JobVO> list=new ArrayList<>();
        try {
            jobBean.setPage(page);
            if(jobBean.getIndustryList()!=null && jobBean.getIndustryList().size()>0){
                List<String> list1=fairJobService.getParentPosition4cssp();
                for( int i = 0; i<jobBean.getIndustryList().size();i++){
                    for( int j = 0; j<list1.size();j++){
                        if(jobBean.getIndustryList().get(i).contains(list1.get(j))){
                            Map<String,Object> map=new HashMap<>();
                            map.put("parent_code",list1.get(j));
                            jobBean.getIndustryList().addAll(fairJobService.getPositionCodeList4cssp(map));
//                            jobBean.getIndustryList(fairJobService.getPositionList4cssp(map));
                        }
                    }
                }
            }
            list = fairJobService.getJobVOList4cssp(jobBean);
            if (list.size() > 0) {
                message = "查询职位列表成功";
                for(int i=0;i<list.size();i++){
                    if (list.get(i).getAreaId()!=null){
                        String areaName=fairJobService.getArea4sisp(list.get(i).getAreaId());
                        list.get(i).setAreaIdName(areaName);
                    }
                    if(list.get(i).getApplyType()==null){
                        list.get(i).setApplyType("申请");
                    }
                }
                page.setData(list);
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
            } else {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "暂无职位列表";
            }

        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("查询指定企业的岗位列表异常：", e);
        }
        return result(statusCode, message,page);
    }


    /**
     * 职位收藏
     *
     * @param collectJobBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/collectJob", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result collectJob(@RequestBody CollectJobBean collectJobBean) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(collectJobBean.getJobId())) return error("岗位id不能为空");
        if (StringUtils.isEmpty(collectJobBean.getSfzh())) return error("用户身份证不能为空");
        String statusCode;
        String message;
        try {
            Integer num = fairJobService.collectJob4cssp(collectJobBean);
            if (num > 0) {
                message = "岗位收藏成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "岗位收藏失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("岗位收藏异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 获取职位收藏列表
     *
     * @param collectJobBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getCollectJobList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCollectJobList(@RequestBody CollectJobBean collectJobBean) throws Exception {
        if (StringUtils.isEmpty(collectJobBean.getSfzh())) return error("sfzh不能为空");
        String statusCode;
        String message;
        Page<CollectJobVO> page=new Page<>(collectJobBean.getPageno(),collectJobBean.getPagesize());
        List<CollectJobVO> list=new ArrayList<>();
        try {
            collectJobBean.setPage(page);
            list = fairJobService.getCollectJobList4cssp(collectJobBean);
            if (list.size() > 0) {
                message = "获取岗位收藏列表成功";
                for (int i=0;i<list.size();i++){
                    if(list.get(i).getAreaId()!=null){
                        list.get(i).setAreaIdName(fairJobService.getArea4sisp(list.get(i).getAreaId()));
                    }
                }
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                page.setData(list);
            } else {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "暂无收藏记录";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("获取岗位收藏列表异常：", e);
        }
        return result(statusCode, message, page);
    }

    /**
     * 取消收藏
     *
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delCollectJob", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result delCollectJob(@RequestBody Map<String,Object> map) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(map.get("jobId"))) return error("岗位收藏表职位id不能为空");
        if (StringUtils.isEmpty(map.get("sfzh"))) return error("sfzh不能为空");
        String statusCode;
        String message;
        try {
            Integer num = fairJobService.delCollectJob4cssp(map);
            if (num > 0) {
                message = "取消收藏成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "取消收藏失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("取消收藏异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 职位申请
     *
     * @param applyJobBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/applyJob", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result applyJob(@RequestBody ApplyJobBean applyJobBean) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(applyJobBean.getJobId())) return error("职位id不能为空");
        if (StringUtils.isEmpty(applyJobBean.getSfzh())) return error("用户身份证不能为空");
        String statusCode;
        String message;
        Map<String,Object> map =new HashMap<>();
        map.put("sfzh",applyJobBean.getSfzh());
        ResumeVO resumeVO= resumeService.getResume4cssp(map);
        if(resumeVO!=null){
            if(resumeVO.getBasicMsgVo()==null || resumeVO.getExceptJobVo() ==null ){
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "简历未完善";
                return result(statusCode, message);
            }
        }else {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "简历未创建";
            return result(statusCode, message);
        }
        try {
            Integer num = fairJobService.applyJob4cssp(applyJobBean);
            if (num > 0) {
                message = "职位申请成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "职位申请失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("职位申请异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 查看申请职位列表
     * @param applyJobBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getApplyJobList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getApplyJobList(@RequestBody ApplyJobBean applyJobBean) throws Exception {
        if (StringUtils.isEmpty(applyJobBean.getSfzh())) return error("sfzh不能为空");
        String statusCode;
        String message;
        Page<ApplyJobVO> page=new Page<>(applyJobBean.getPageno(),applyJobBean.getPagesize());
        List<ApplyJobVO> list=new ArrayList<>();
        try {
            applyJobBean.setPage(page);
            list = fairJobService.getApplyJobList4cssp(applyJobBean);
            if (list.size() > 0) {
                message = "获取职位申请列表成功";
                for (int i=0;i<list.size();i++){
                    if(list.get(i).getAreaId()!=null){
                        list.get(i).setAreaIdName(fairJobService.getArea4sisp(list.get(i).getAreaId()));
                    }
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                    java.util.Date date_util = sdf.parse(list.get(i).getApplyTime());
                    String date = sdf.format(date_util);
                    list.get(i).setApplyTime(date);
                }
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
               page.setData(list);
            } else {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "获取职位申请列表暂无";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("获取职位申请列表异常：", e);
        }
        return result(statusCode, message, page);
    }

    /**
     * 获取岗位列表
     *
     * @param ineQueryBean
     * @return
     */
    @RequestMapping(value = "/getPositionList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getPositionList(@RequestBody IneQueryBean ineQueryBean) {
        logger.info("getIneInfoList" + CommUtil.getNowDateLongStr());
        String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "查询成功";
        try {
            ineQueryBean.setQueryType(Constants.T_INFO_TYPE_FAIRJOB);
            Page<PositionVo> poList = fairJobService.getPositionList4Cssp(ineQueryBean);
            return result(statusCode, message, poList);
        } catch (Exception e) {
            logger.error("服务器异常，请稍后再试!", e);
            return result(Constants.RESULT_MESSAGE_ERROR, "操作失败");
        }
    }

    /**
     * 获得城市
     *
     * @return
     */
    @RequestMapping(value = "/getCity", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCity() {
        String statusCode = Constants.RESULT_MESSAGE_SUCCESS;
        String message = "查询成功";
        try {
            AllCity cityVo = new AllCity();
            cityVo.setRedCityVo(fairJobService.getHotCity4sisp());
            cityVo.setAllCitys(fairJobService.getAllCity4sisp());
            return result(statusCode, message, cityVo);
        } catch (Exception e) {
            logger.error("服务器异常，请稍后再试!", e);
            return result(Constants.RESULT_MESSAGE_ERROR, "操作失败");
        }
    }


    /**
     * 新增招聘会
     *
     * @param jobFairBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addJobFair", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result addJobFair(@RequestBody JobFairBean jobFairBean) throws Exception {
        String statusCode;
        String message;
        try {
            Integer num = fairJobService.addJobFair4cssp(jobFairBean);
            if (num > 0) {
                message = "新增招聘会成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "新增招聘会失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("新增招聘会异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 修改招聘会信息
     *
     * @param jobFairBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateJobFair", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result updateJobFair(@RequestBody JobFairBean jobFairBean) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(jobFairBean.getId())) return error("招聘会id不能为空");
        String statusCode;
        String message;
        try {
            Integer num = fairJobService.updateJobFair4cssp(jobFairBean);
            if (num > 0) {
                message = "修改招聘会成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "修改招聘会失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("新增招聘会异常：", e);
        }
        return result(statusCode, message);
    }


    /**
     * 查看招聘会信息详情
     *
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/jobFairDetail", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result jobFairDetail(@RequestBody Map<String, Object> map) throws Exception {
        String statusCode;
        String message;
        try {
            JobFairVO jobFairVo = fairJobService.jobFairDetail4cssp(map);
            if (jobFairVo != null) {
                message = "查看招聘会信息详情成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message, jobFairVo);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "查看招聘会信息详情失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("新增招聘会异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 查询企业信息列表包含模糊查询
     *
     * @param coUserBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getCoUserList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCoUserList(@RequestBody CoUserBean coUserBean) throws Exception {
        String statusCode;
        String message;
        Page<CoUserVO> page=new Page<>(coUserBean.getPageno(),coUserBean.getPagesize());
        List<CoUserVO> list=new ArrayList<>();
        try {
            coUserBean.setPage(page);
            list = fairJobService.getCoUserList4cssp(coUserBean);
            if (list.size() > 0) {
                message = "查询企业信息列表成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
               page.setData(list);
            } else {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "查询企业信息列表暂无";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("查询企业信息列表异常：", e);
        }
        return result(statusCode, message, page);
    }

    /**
     * 企业账号状态设置
     *
     * @param coUserBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/checkCoUser", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result checkCoUser(@RequestBody CoUserBean coUserBean) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(coUserBean.getId())) return error("企业id不能为空");
        String statusCode;
        String message;
        Integer num = 0 ;
        Integer it = 0 ;
        Map<String,String> map = new HashMap<>();
        try {
    		boolean flag = false;
    		//判断 用户时候存在 如果不存在  则是下面 第一种情况    存在则是第二种情况
//    		flag = fairJobService.getUserVoByLogName4Sisp(coUserBean.getLogName());  //不存在  flag值为true
    		CoUserVO vo = userService.checkUserByLogName4Sisp(coUserBean);
    		if(vo == null){
    			flag = true;
    		}
    		if(flag && "3".equals(coUserBean.getStatus())){
    			//1. 由审核不通过或者未审核 变成审核通过
        		//表示审核通过  添加账号并且绑定相应的角色
        		//通过企业信息id  找出对应的用户名密码
        		map.put("id", coUserBean.getId());
        		CoUserVO coUserVO = fairJobService.getLogInfoBycoUserId4Cssp(map);
        		coUserBean.setLogName(coUserVO.getLogName());
        		coUserBean.setPassword(coUserVO.getPassword());
        		//添加用户并且绑定角色
        		long userId = fairJobService.addUser4sisp(coUserBean,roleId);
        		if(userId < 0 ){
        			return result(Constants.RESULT_MESSAGE_ERROR, "审核失败");
        		}
        		coUserBean.setUserId(userId);
    		}else{
    			//2. 由停用变成启用     或者是由启用变成停用  
    			it = fairJobService.updateUserStatusByLogName4Sisp(coUserBean.getLogName(),coUserBean.getStatus());
    		}
        	if(it < 0){
        		return result(Constants.RESULT_MESSAGE_ERROR, "审核失败");
        	}
        	num = fairJobService.checkCoUser4cssp(coUserBean);
     
            if (num > 0) {
                message = "帐号状态设置成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "帐号状态设置失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("企业帐号状态设置异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 删除企业信息
     *
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delCoUser", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result delCoUser(@RequestBody Map<String, Object> map) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(map.get("id"))) return error("企业id不能为空");
        String statusCode;
        String message;
        try {
            Integer num = 0;
        	CoUserVO coUserVO = fairJobService.getLogInfoBycoUserId4Cssp(map);
        	if(coUserVO == null){
        		return result(Constants.RESULT_MESSAGE_ERROR, "非法操作!");
        	}
        	String logName = coUserVO.getLogName();
        	Integer it = fairJobService.updateUserStatusByLogName4Sisp(logName,"2");
        	if(it>0){
        		num = fairJobService.delCoUser4cssp(map);
        	}else{
        		logger.error("用户状态更改失效，删除失败!");
        		return result(Constants.RESULT_MESSAGE_ERROR, "用户状态更改失效，删除失败!");
        	}
            
            if (num > 0) {
                message = "删除企业信息成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "删除企业信息失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("删除企业信息异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 查看企业信息详情
     *
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/detailCoUser", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result detailCoUser(@RequestBody Map<String, Object> map) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(map.get("id"))) return error("企业id不能为空");
        String statusCode;
        String message;
        try {
            CoUserVO coUserVO = fairJobService.detailCoUser4cssp(map);
            if (coUserVO != null) {
                long id;
                String picPath;
                if(coUserVO.getLicence() != null){
                    id= Long.parseLong(coUserVO.getLicence());
                    picPath = commService.photoIsExist4Cssp(id,"005");
                    coUserVO.setLicence((ImageChangeUtil.getImageStr(picPath)));
                }
                if(coUserVO.getLogo() != null){
                    id= Long.parseLong(coUserVO.getLogo());
                    picPath= commService.photoIsExist4Cssp(id, "006");
                    coUserVO.setLogo((ImageChangeUtil.getImageStr(picPath)));
                }
                message = "查看企业信息详情成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message, coUserVO);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "查看企业信息详情失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("查看企业信息详情异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 招聘会企业列表查询
     *
     * @param fairCoBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getFairCoList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getFairCoList(@RequestBody FairCoBean fairCoBean) throws Exception {
        String statusCode;
        String message;
        Page<FairCoVO> page=new Page<>(fairCoBean.getPageno(),fairCoBean.getPagesize());
        List<FairCoVO> list=new ArrayList<>();
        try {
            fairCoBean.setPage(page);
            list = fairJobService.getFairCoList4cssp(fairCoBean);
            if (list.size() > 0) {
                message = "招聘会单位列表查询成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
               page.setData(list);
            } else {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "招聘会单位列表查询失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("招聘会单位列表查询异常：", e);
        }
        return result(statusCode, message, page);
    }


    /**
     * 查询未结束招聘会信息
     *
     * @param
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getJobFairName", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getJobFairName() throws Exception {
        //入参校验
        String statusCode;
        String message;
        try {
            List<Map<String,Object>> fairName = fairJobService.getJobFairName4cssp();
            if (fairName.size() > 0) {
                message = "查询未结束招聘会信息成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message, fairName);
            } else {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "查询未结束招聘会信息暂无";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("查询未结束招聘会信息异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 添加参加招聘会的公司
     *
     * @param fairCoBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addFairCo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result addFairCo(@RequestBody FairCoBean fairCoBean) throws Exception {
        String statusCode;
        String message;
        try {
            Integer num = fairJobService.addFairCo4cssp(fairCoBean);
            if (num > 0 ) {
                message = "添加招聘会单位成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "添加招聘会单位失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("添加招聘会公司异常：", e);
        }
        return result(statusCode, message);
    }


    /**
     * 添加招聘会公司职位信息
     *
     * @param list
     * @return
     * @throws Exception
     */
    /*@RequestMapping(value = "/addFairPosition", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result addFairPosition(@RequestBody List<FairPositionBean> list) throws Exception {
        //入参校验
        *//*if(StringUtils.isEmpty( fairCoBean.getId())) return error("招聘会公司id不能为空");*//*
        String statusCode;
        String message;
        try {
            Integer num = fairJobService.addFairPosition4cssp(list);
            if (num > 0) {
                message = "添加招聘会公司职位信息成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "添加招聘会公司职位信息失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("添加招聘会公司职位信息异常：", e);
        }
        return result(statusCode, message);
    }*/

    /**
     * 检测该公司所参与的招聘会是否已结束
     *
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/checkUpdate", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result checkUpdate(@RequestBody Map map) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(map.get("id"))) return error("招聘会公司id不能为空");
        String statusCode;
        String message;
        try {
            FairCoVO fairCoVO = fairJobService.checkUpdate4cssp(map);
            if (fairCoVO != null) {
                message = "该公司所参与的招聘会未结束";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message, fairCoVO);
            } else {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "该公司所参与的招聘会已结束";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("检测该公司所参与的招聘会是否已结束异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 修改招聘会公司信息
     *
     * @param fairCoBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateFairCo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result updateFairCo(@RequestBody FairCoBean fairCoBean) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(fairCoBean.getId())) return error("招聘会公司id不能为空");
        String statusCode;
        String message;
        try {
            Integer num = fairJobService.updateFairCo4cssp(fairCoBean);
            if (num > 0) {
                message = "修改招聘会单位信息成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "修改招聘会单位信息失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("修改招聘会单位信息异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 修改招聘会岗位信息
     *
     * @param fairPositionBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateFairPosition", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result updateFairPosition(@RequestBody FairPositionBean fairPositionBean) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(fairPositionBean.getId())) return error("招聘会岗位id不能为空");
        String statusCode;
        String message;
        try {
            Integer num = fairJobService.updateFairPosition4cssp(fairPositionBean);
            if (num > 0) {
                message = "修改招聘会岗位信息成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "修改招聘会岗位信息失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("修改招聘会岗位信息异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 修改招聘会企业所招聘岗位的信息
     *
     * @param list
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updatePosList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result updatePosList(@RequestBody List<FairPositionBean> list) throws Exception {
        String statusCode;
        String message;
        try {
            Integer num = fairJobService.updatePosList4cssp(list);
            if (num > 0) {
                message = "修改招聘会企业所招聘岗位的信息成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "修改招聘会企业所招聘岗位的信息失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("修改招聘会企业所招聘岗位的信息异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 删除招聘会公司信息
     *
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delFairCo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result delFairCo(@RequestBody Map<String, Object> map) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(map.get("id"))) return error("招聘会单位id不能为空");
        String statusCode;
        String message;
        try {
            Integer num = fairJobService.delFairCo4cssp(map);
            Integer num1 = fairJobService.delFairPositionByCoId4cssp(map);
//            Integer num2 = fairJobService.updateUserStatusByLogName4Sisp((String)map.get("logName"),"2");
            if (num > 0 && num1 > 0) {
                message = "删除招聘会单位成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "删除招聘会单位失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("删除招聘会单位异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 删除招聘会岗位信息
     *
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delFairPosition", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result delFairPosition(@RequestBody Map<String, Object> map) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(map.get("id"))) return error("岗位id不能为空");
        String statusCode;
        String message;
        try {
            Integer num = fairJobService.delFairPosition4cssp(map);
            if (num > 0) {
                message = "删除招聘会岗位信息成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "删除招聘会岗位信息失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("删除招聘会岗位信息异常：", e);
        }
        return result(statusCode, message);
    }


    /**
     * 查询招聘会企业的详情
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getFariCoDetail", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getFariCoDetail(@RequestBody Map<String, Object> map) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(map.get("id"))) return error("单位id不能为空");
        String statusCode;
        String message;
        try {
            FairCoVO fairCoVO = fairJobService.getFariCoDetail4cssp(map);
            if (fairCoVO !=null) {
                message = "查询招聘会企业信息详情成功";
               List<FairPositionVo> list= fairJobService.getFairPosition4cssp(map);
                fairCoVO.setFairPositionVos(list);
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message,fairCoVO);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "查询招聘会企业信息详情失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("查询招聘会企业信息详情异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 人才搜索
     *
     * @param resumeBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getResumeList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getResumeList(@RequestBody ResumeBean resumeBean) throws Exception {
        String statusCode;
        String message;
        Page<Resume> page=new Page<>(resumeBean.getPageno(),resumeBean.getPagesize());
        List<Resume> list=new ArrayList<>();
        try {
            resumeBean.setPage(page);
            list = fairJobService.getResumeList4cssp(resumeBean);
            if (list.size() > 0) {
                for(int i=0;i<list.size();i++){
                    if(list.get(i).getPicture()!=null){
                        long id= Long.parseLong(list.get(i).getPicture());
                        String picPath= commService.photoIsExist4Cssp(id,"004");
                        list.get(i).setPicture((ImageChangeUtil.getImageStr(picPath)));
                    }
                }
                message = "人才搜索成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                page.setData(list);
            } else {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "人才搜索暂无";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("人才搜索异常：", e);
        }
        return result(statusCode, message, page);
    }

    /**
     * 企业注册
     *
     * @param coUserBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addCoUser", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result addCoUser(@RequestBody CoUserBean coUserBean) throws Exception {
        String statusCode;
        String message;
        try {
            if( coUserBean.getLicence()!=null) {
                PicBean bean=new PicBean();
                bean.setPicBase64(coUserBean.getLicence());
                bean.setPicType("005");
                long picId = commService.uploadPicture4Cssp(bean);
//               Result result = commController.uploadPicture(bean);
                if(picId>0){
                    //图片关联业务
                    PicBusBean picBusBean = new PicBusBean();
                    picBusBean.setPicId(picId);
                    picBusBean.setPicStatus(Constants.PIXEL_BUSINESS_STATUS_00);
                    picBusBean.setPicType(bean.getPicType());
                    commService.insertPictureBus4Cssp(picBusBean);
                }
//               basicMsgBean.setPicture((String) JSONObject.parseObject((String) result.getData()).get("picId"));
                coUserBean.setLicence(String.valueOf(picId));
            }

            if( coUserBean.getLogo()!=null) {
                PicBean bean=new PicBean();
                bean.setPicBase64(coUserBean.getLogo());
                bean.setPicType("006");
                long picId = commService.uploadPicture4Cssp(bean);
//               Result result = commController.uploadPicture(bean);
                if(picId>0){
                    //图片关联业务
                    PicBusBean picBusBean = new PicBusBean();
                    picBusBean.setPicId(picId);
                    picBusBean.setPicStatus(Constants.PIXEL_BUSINESS_STATUS_00);
                    picBusBean.setPicType(bean.getPicType());
                    commService.insertPictureBus4Cssp(picBusBean);
                }
//               basicMsgBean.setPicture((String) JSONObject.parseObject((String) result.getData()).get("picId"));
                coUserBean.setLogo(String.valueOf(picId));
            }
            Integer num = fairJobService.addCoUser4cssp(coUserBean);
            if (num > 0) {
                message = "资料提交成功,请等待审核";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "资料提交失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("企业注册资料提交异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 企业信息修改
     *
     * @param coUserBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateCoUser", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result updateCoUser(@RequestBody CoUserBean coUserBean) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(coUserBean.getId())) return error("企业id不能为空");
        String statusCode;
        String message;
        try {
            if( coUserBean.getLogo()!=null) {
            PicBean bean=new PicBean();
            bean.setPicBase64(coUserBean.getLogo());
            bean.setPicType("006");
            long picId = commService.uploadPicture4Cssp(bean);
            if(picId>0){
                //图片关联业务
                PicBusBean picBusBean = new PicBusBean();
                picBusBean.setPicId(picId);
                picBusBean.setPicStatus(Constants.PIXEL_BUSINESS_STATUS_00);
                picBusBean.setPicType(bean.getPicType());
                commService.insertPictureBus4Cssp(picBusBean);
            }
                coUserBean.setLogo(String.valueOf(picId));
        }
            Integer num = fairJobService.updateCoUser4cssp(coUserBean);
            if (num > 0) {
                message = "企业信息修改成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "企业信息修改失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("企业信息修改异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 发布新职位
     * @param jobBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addJob", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result addJob(@RequestBody JobBean jobBean) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(jobBean.getName())) return error("职位名称不能为空");
        if (StringUtils.isEmpty(jobBean.getIndustry())) return error("行业类别不能为空");
        if (StringUtils.isEmpty(jobBean.getAreaId())) return error("工作地址编码不能为空");
        if (StringUtils.isEmpty(jobBean.getArea())) return error("工作详细地址不能为空");
        if (StringUtils.isEmpty(jobBean.getHireNum())) return error("招聘人数不能为空");
        if (StringUtils.isEmpty(jobBean.getEducation())) return error("学历要求不能为空");
        if (StringUtils.isEmpty(jobBean.getWorkingSeniority())) return error("工作年限不能为空");
        if (StringUtils.isEmpty(jobBean.getSalaryMin())) return error("最低薪资不能为空");
        if (StringUtils.isEmpty(jobBean.getSalaryMax())) return error("最高薪资不能为空");
        if (StringUtils.isEmpty(jobBean.getTel())) return error("联系座机不能为空");
        if (StringUtils.isEmpty(jobBean.getContact())) return error("联系人不能为空");
        if (StringUtils.isEmpty(jobBean.getSummary())) return error("职位描述不能为空");
        if (StringUtils.isEmpty(jobBean.getWelfare())) return error("任职福利不能为空");
        if (StringUtils.isEmpty(jobBean.getJobProperty())) return error("工作性质不能为空");
        if (StringUtils.isEmpty(jobBean.getJobTypes())) return error("职位类型不能为空");
        String statusCode;
        String message;
        try {
            Integer num = fairJobService.addJob4cssp(jobBean);
            if (num > 0) {
                message = "发布新职位成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "发布新职位失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("发布新职位异常：", e);
        }
        return result(statusCode, message);
    }
    /**
     * 修改职位信息
     * @param jobBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/editJob", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result editJob(@RequestBody JobBean jobBean) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(jobBean.getId())) return error("职位id不能为空");
        String statusCode;
        String message;
        try {
            Integer num = fairJobService.editJob4cssp(jobBean);
            if (num > 0) {
                message = "职位信息修改成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "职位信息修改失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("职位信息修改异常：", e);
        }
        return result(statusCode, message);
    }
    /**
     * 查询企业岗位列表
     * @param jobBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getJobList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getJobList(@RequestBody JobBean jobBean) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(jobBean.getCoId())) return error("公司id不能为空");
        String statusCode;
        String message;
        Page<JobVO> page=new Page<>(jobBean.getPageno(),jobBean.getPagesize());
        List<JobVO> list=new ArrayList<>();
        try {
            jobBean.setPage(page);
            list= fairJobService.getJobList4cssp(jobBean);
            if (list.size() > 0) {
               for (int i=0;i<list.size();i++ ){
                   Map<String,Object> map=new HashMap<>();
                   map.put("jobId", list.get(i).getId());
                   Integer num=resumeService.getNoReadCounts4cssp(map);
                   list.get(i).setNoRead(num);
                   Integer counts=resumeService.getLookCounts4cssp(map);
                   if(counts==null){
                       list.get(i).setLookCounts(0);
                   }else{
                       list.get(i).setLookCounts(counts);
                   }
               }
                message = "查询企业岗位列表成功";
                page.setData(list);
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
            } else {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "暂无企业岗位列表";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("查询企业岗位列表出错：", e);
        }
        return result(statusCode, message, page);
    }

    @RequestMapping(value = "/getPositionName", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getPositionName(@RequestBody Map<String,Object> map) throws Exception {
        //入参校验
        String statusCode;
        String message;
        try {
            PositionVo positionVo= fairJobService.getPositionName4cssp(map);
            if (positionVo != null) {
                message = "查询企业岗位行业名称成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message, positionVo);
            } else {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "暂无企业岗位行业名称";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("查询企业岗位行业名称出错：", e);
        }
        return result(statusCode, message);
    }

    //上传图片的方法url地址转换成base64字符串
    public static String getPicture(String url) {
        String imgFile = url;// 待处理的voice
        InputStream in;
        byte[] data = null;
        // 读取照片字节数组
        try {
            in = new FileInputStream(imgFile);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);// 返回Base64编码过的字节数组字符串
    }

    public static boolean generateImage(String imgStr, String path) {
        if (imgStr == null) //照片数据为空
            return false;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            // Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for (int i = 0; i < b.length; ++i) {
                if (b[i] < 0) {// 调整异常数据
                    b[i] += 256;
                }
            }
            // 生成照片
            // C:\Users\Sandwich\Desktop\base64\1.jpg
            String imgFilePath = path;// 新生成的照片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    @RequestMapping(value = "/getWelfareList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public  Result getWelfareList() throws  Exception{
            String statusCode;
            String message;
            try {
                List<WelfareVo> list= fairJobService.getWelfareList4sisp();
                if (list.size()>0) {
                    message = "查询福利成功";
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                    return result(statusCode, message, list);
                } else {
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                    message = "暂无福利";
                }
            } catch (Exception e) {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "操作失败";
                logger.error("查询福利出错：", e);
            }
            return result(statusCode, message);
        }

    /**
     * PC端调用
     * @param jobBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getJobDetailPC", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getJobDetailPC(@RequestBody JobBean jobBean) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(jobBean.getId())) return error("岗位id不能为空");
        String statusCode;
        String message;
        try {
            JobVO jobVO = fairJobService.getJobDetail4cssp(jobBean);
            statusCode = Constants.RESULT_MESSAGE_SUCCESS;
            if (jobVO != null) {
                message = "查询指定岗位详情成功";
                String areaName=fairJobService.getArea4sisp(jobVO.getAreaId());
                Map map1=new HashMap();
                map1.put("welfareName",ttt(jobVO.getWelfare()));
                List<String> welfare=fairJobService.getWelfare4sisp(map1);
                jobVO.setWelfareName(welfare);
                jobVO.setAreaIdName(areaName);
                return result(statusCode, message, jobVO);
            } else {
                message = "查询指定岗位详情失败";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("查询指定岗位详情异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 刷新指定职位
     * @param jobBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/refreshJob", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result refreshJob(@RequestBody JobBean jobBean) throws Exception {
        //入参校验
        if (StringUtils.isEmpty(jobBean.getId())) return error("岗位id不能为空");
        String statusCode;
        String message;
        try {
            Integer num = fairJobService.refreshJob4cssp(jobBean);

            if ( num >0 ) {
                message = "指定岗位刷新成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            } else {
                message = "查询指定岗位详情失败";
                statusCode = Constants.RESULT_MESSAGE_ERROR;
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("查询指定岗位详情异常：", e);
        }
        return result(statusCode, message);
    }
}