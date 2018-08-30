package com.tecsun.sisp.adapter.modules.resume.controller;

import com.tecsun.sisp.adapter.common.util.Constants;
import com.tecsun.sisp.adapter.common.util.ImageChangeUtil;
import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.common.util.Result;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.common.controller.CommController;
import com.tecsun.sisp.adapter.modules.common.entity.request.PicBean;
import com.tecsun.sisp.adapter.modules.common.entity.request.PicBusBean;
import com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl;
import com.tecsun.sisp.adapter.modules.resume.entity.request.*;
import com.tecsun.sisp.adapter.modules.resume.entity.response.*;
import com.tecsun.sisp.adapter.modules.resume.service.impl.TestResumeServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by gongshuqi on 2017/11/1 0001.
 */
@Controller
@RequestMapping(value = "/adapter/resume")
public class ResumeController  extends BaseController {
    public final static Logger logger = Logger.getLogger(ResumeController.class);
    @Autowired
    private TestResumeServiceImpl resumeService;
    @Autowired
    private CommServiceImpl commService;
    /**
     * 查询个人简历
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getResume", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getResume(@RequestBody Map<String,Object> map)throws Exception{
        //入参校验
        if(StringUtils.isEmpty(map.get("sfzh"))) return error("sfzh不能为空");
        String statusCode;
        String message;
        try{
            ResumeVO resumeVO =resumeService.getResume4cssp(map);
            if ( resumeVO != null ) {
                message = "查询成功";
                if(resumeVO.getBasicMsgVo().getPicture()!=null){
                    long id= Long.parseLong(resumeVO.getBasicMsgVo().getPicture());
                    String picPath= commService.photoIsExist4Cssp(id,"004");
                    resumeVO.getBasicMsgVo().setPicture((ImageChangeUtil.getImageStr(picPath)));
                }
             String age=getAge((String)map.get("sfzh"));
                resumeVO.getBasicMsgVo().setAge(age);
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message,resumeVO);
            } else {
                addResume(map);
                addExpjob(map);
                resumeVO =resumeService.getResume4cssp(map);
                if ( resumeVO != null ) {
                    message = "查询成功";
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                    return result(statusCode, message,resumeVO);
                }else{
                    message = "查询失败";
                    statusCode = Constants.RESULT_MESSAGE_ERROR;
                    return result(statusCode, message,resumeVO);
                }
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("获取个人简历异常：", e);
        }
        return result(statusCode, message);
    }
  private String getAge(String sfzh){
      String birth=sfzh.substring(6,10);
      Date date=new Date();
      DateFormat sdf = new SimpleDateFormat("yyyy");
      String s1 = sdf.format(date);
      Integer age=(Integer.parseInt(s1)-Integer.parseInt(birth));
      return age.toString();
  }
    /**
     * 增加简历
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addResume", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result addResume(@RequestBody Map<String,Object> map)throws Exception{
        String statusCode;
        String message;
        try{
            Integer num =resumeService.addResume4cssp(map);
            if ( num != null ) {
                message = "增加空简历成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "增加空简历失败";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("增加空简历异常：", e);
        }
        return result(statusCode, message);
    }
    /**
     * 新增期望工作
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addExpjob", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result addExpjob(@RequestBody Map<String,Object> map) throws Exception{
        //入参校验
        if(StringUtils.isEmpty(map.get("sfzh"))) return error("身份证号不能为空");
        String statusCode ;
        String message ;
        try{
            Integer num = resumeService.addExpjob4cssp(map);
            if( num > 0 ){
                message = "新增简历期望工作成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
               /* Map<String,Object> map=new HashMap<>();
                map.put("id",map.getResumeId());
                republish(map);*/
                return result(statusCode, message);
            }else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "新增简历期望工作失败";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("新增简历期望工作异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 修改简历基本信息
     * @param basicMsgBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateBasicMsg", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result updateBasicMsg(@RequestBody BasicMsgBean basicMsgBean) throws Exception{
        //入参校验
        if(StringUtils.isEmpty(basicMsgBean.getId())) {
            return error("简历id不能为空");
        }
        String statusCode ;
        String message ;
        try{
           if( basicMsgBean.getPicture()!=null) {
               CommController commController=new CommController();
               PicBean bean=new PicBean();
               bean.setPicBase64(basicMsgBean.getPicture());
               bean.setPicType("004");
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
               basicMsgBean.setPicture(String.valueOf(picId));
           }
            Integer num=resumeService.updateBasicMsg4cssp(basicMsgBean);
            if( num > 0 ){
                message = "修改简历基本信息成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                Map<String,Object> map=new HashMap<>();
                map.put("id",basicMsgBean.getId());
                republish(map);
                return result(statusCode, message);
            }else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "修改简历基本信息失败";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("修改简历基本信息异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 查看指定工作经历
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getWorkMsg", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getWorkMsg(@RequestBody Map<String,Object> map)throws Exception{
        //入参校验
        if(StringUtils.isEmpty(map.get("id"))) return error("工作经历id不能为空");
        String statusCode;
        String message;
        try{
            WorkExpVO workExpVo =resumeService.getWorkMsg4cssp(map);
            if ( workExpVo != null ) {
                message = "查询工作经历成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message, workExpVo);
            } else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "暂无个人简历工作经历信息";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("获取个人简历工作经历异常：", e);
        }
        return result(statusCode, message);
    }
    /**
     * 修改简历工作经历
     * @param workExpBean 简历的工作经历
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateWorkMsg", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result updateWorkMsg(@RequestBody WorkExpBean workExpBean) throws Exception{
        //入参校验
        if(StringUtils.isEmpty(workExpBean.getId())) return error("工作经历id不能为空");
        String statusCode ;
        String message ;
        try{
            Integer num=resumeService.updateWorkMsg4cssp(workExpBean);
            if( num > 0 ){
                message = "修改简历工作经历成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                Map<String,Object> map=new HashMap<>();
                map.put("id",workExpBean.getResumeId());
                republish(map);
                return result(statusCode, message);
            }else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "修改简历工作经历失败";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("修改简历工作经历异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 增加简历的工作经历
     * @param workExpBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addWorkMsg", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result addWorkMsg(@RequestBody WorkExpBean workExpBean) throws Exception{
        //入参校验
        if(StringUtils.isEmpty(workExpBean.getResumeId())) return error("关联的简历id不能为空");
        String statusCode ;
        String message;
        try{
            Integer num=resumeService.addWorkMsg4cssp(workExpBean);
            if( num > 0 ){
                message = "增加简历工作经历成功";
                String id=workExpBean.getId();
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                Map<String,Object> map=new HashMap<>();
                map.put("id",workExpBean.getResumeId());
                republish(map);
                return result(statusCode, message,id);
            }else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "增加简历工作经历失败";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("增加简历工作经历异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 删除个人简历的工作经历
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delWorkMsg", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result delWorkMsg(@RequestBody Map<String,Object> map) throws Exception{
        //入参校验
        if(StringUtils.isEmpty(map.get("id"))) return error("工作经历id不能为空");
        String statusCode ;
        String message;
        try{
            WorkExpVO workExpVO=resumeService.getWorkMsg4cssp(map);
            Integer num=resumeService.delWorkMsg4cssp(map);

            if( num > 0 ){
                message = "删除简历工作经历成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                Map<String,Object> map1=new HashMap<>();
                map1.put("id",workExpVO.getResumeId());
                republish(map1);
                return result(statusCode, message);
            }else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "删除简历工作经历失败";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("删除简历工作经历异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 查看指定教育经历
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getEduMsg", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getEduMsg(@RequestBody Map<String,Object> map)throws Exception{
        //入参校验
        if(StringUtils.isEmpty(map.get("id"))) return error("教育经历id不能为空");
        String statusCode;
        String message;
        try{
            EduExpVO eduExpVo =resumeService.getEduMsg4cssp(map);

            if ( eduExpVo != null ) {
                message = "查询教育经历成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message, eduExpVo);
            } else{
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "暂无个人简历教育经历信息";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("获取个人简历教育经历异常：", e);
        }
        return result(statusCode, message);
    }
    /**
     * 修改简历教育经历
     * @param eduExpBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateEduMsg", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result updateEduMsg(@RequestBody EduExpBean eduExpBean) throws Exception{
        //入参校验
        if(StringUtils.isEmpty(eduExpBean.getId())) return error("教育经历id不能为空");
        String statusCode ;
        String message ;
        try{
            Integer num=resumeService.updateEduMsg4cssp(eduExpBean);

            if( num > 0 ){
                message = "修改简历教育经历成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                Map<String,Object> map=new HashMap<>();
                map.put("id",eduExpBean.getResumeId());
                republish(map);
                return result(statusCode, message);
            }else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "修改简历教育经历失败";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("修改简历教育经历异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 新增简历教育经历
     * @param eduExpBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addEduMsg", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result addEduMsg(@RequestBody EduExpBean eduExpBean) throws Exception{
        //入参校验
        if(StringUtils.isEmpty(eduExpBean.getResumeId())) return error("关联的简历id不能为空");
        String statusCode ;
        String message ;
        try{
            Integer num=resumeService.addEduMsg4cssp(eduExpBean);
            if( num > 0 ){
                message = "新增简历教育经历成功";
                String id=eduExpBean.getId();
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                Map<String,Object> map=new HashMap<>();
                map.put("id",eduExpBean.getResumeId());
                republish(map);
                return result(statusCode, message,id);
            }else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "新增简历教育经历失败";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("新增简历教育经历异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 删除简历教育经历
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delEduMsg", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result delEduMsg(@RequestBody Map<String,Object> map ) throws Exception {
        //入参校验
        if(StringUtils.isEmpty(map.get("id"))) return error("教育经历id不能为空");
        String statusCode ;
        String message ;
        try{
            EduExpVO eduExpVO=resumeService.getEduMsg4cssp(map);
            Integer num=resumeService.delEduMsg4cssp(map);
            if( num > 0 ){
                message = "删除简历教育经历成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                Map<String,Object> map1=new HashMap<>();
                map1.put("id",eduExpVO.getResumeId());
                republish(map1);
                return result(statusCode, message);
            }else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "删除简历教育经历失败";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("删除简历教育经历异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 修改简历期望工作
     * @param exceptJobBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateExpMsg", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result updateExpMsg(@RequestBody ExceptJobBean exceptJobBean) throws Exception{
        //入参校验
        if(StringUtils.isEmpty(exceptJobBean.getSfzh())) return error("身份证号不能为空");
        String statusCode ;
        String message ;
        try{
            Integer num = resumeService.updateExpMsg4cssp(exceptJobBean);
            if( num > 0 ){
                message = "修改简历期望工作成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
               /* Map<String,Object> map=new HashMap<>();
                map.put("id",exceptJobVo.getSfzh());
                republish(map);*/
                return result(statusCode, message);
            }else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "修改简历期望工作失败";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("修改简历期望工作异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     *查看指定项目经验
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getProMsg", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getProMsg(@RequestBody Map<String,Object> map)throws Exception{
        if(StringUtils.isEmpty(map.get("id"))) return error("项目经验id不能为空");
        String statusCode;
        String message;
        try{
            ProExpVO proExpVo =resumeService.getProMsg4cssp(map);
            if ( proExpVo != null ) {
                message = "查询项目经验成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message, proExpVo);
            } else{
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "暂无个人简历项目经验信息";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("获取个人简历项目经验异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 修改简历项目经验
     * @param proExpBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateProMsg", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result updateProMsg(@RequestBody ProExpBean proExpBean) throws Exception{
        //入参校验
        if(StringUtils.isEmpty(proExpBean.getId())) return error("项目经验id不能为空");
        if(StringUtils.isEmpty(proExpBean.getBeginTime())) return error("项目开始时间不能为空");
        if(StringUtils.isEmpty(proExpBean.getEndTime())) return error("项目结束时间不能为空");
        if(StringUtils.isEmpty(proExpBean.getName())) return error("项目名称不能为空");
        if(StringUtils.isEmpty(proExpBean.getPosition())) return error("您的职位不能为空");
        if(StringUtils.isEmpty(proExpBean.getProSummary())) return error("项目描述不能为空");
        String statusCode ;
        String message ;
        try{
            Integer num = resumeService.updateProMsg4cssp(proExpBean);

            if( num > 0 ){
                message = "修改简历项目经验成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                Map<String,Object> map=new HashMap<>();
                map.put("id",proExpBean.getResumeId());
                republish(map);
                return result(statusCode, message);
            }else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "修改简历项目经验失败";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("修改简历项目经验异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 新增简历项目经验
     * @param proExpBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/addProMsg", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result addProMsg(@RequestBody ProExpBean proExpBean) throws Exception{
        //入参校验
        if(StringUtils.isEmpty(proExpBean.getResumeId())) return error("关联的简历id不能为空");
        if(StringUtils.isEmpty(proExpBean.getBeginTime())) return error("项目开始时间不能为空");
        if(StringUtils.isEmpty(proExpBean.getEndTime())) return error("项目结束时间不能为空");
        if(StringUtils.isEmpty(proExpBean.getName())) return error("项目名称不能为空");
        if(StringUtils.isEmpty(proExpBean.getPosition())) return error("您的职位不能为空");
        if(StringUtils.isEmpty(proExpBean.getProSummary())) return error("项目描述不能为空");
        String statusCode ;
        String message ;
        try{
            Integer num = resumeService.addProMsg4cssp(proExpBean);
            if( num > 0 ){
                message = "新增简历项目经验成功";
                String id=proExpBean.getId();
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                Map<String,Object> map=new HashMap<>();
                map.put("id",proExpBean.getResumeId());
                republish(map);
                return result(statusCode, message,id);
            }else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "新增简历项目经验失败";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("新增简历项目经验异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 删除简历项目经验
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/delProMsg", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result delProMsg(@RequestBody Map<String,Object> map ) throws Exception{
        if(StringUtils.isEmpty(map.get("id"))) return error("项目经验id不能为空");
        String statusCode ;
        String message ;
        try{
            ProExpVO proExpVO=resumeService.getProMsg4cssp(map);
            Integer num = resumeService.delProMsg4cssp(map);

            if( num > 0 ){
                message = "删除简历项目经验成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                Map<String,Object> map1=new HashMap<>();
                map1.put("id",proExpVO.getResumeId());
                republish(map1);
                return result(statusCode, message);
            }else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "删除简历项目经验失败";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = " 操作失败 ";
            logger.error( " 删除简历项目经验异常：" , e);
        }
        return result(statusCode, message);
    }

    /**
     * 修改简历自我描述
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateSumMsg", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result updateSumMsg(@RequestBody Map<String,Object> map) throws Exception{
        //入参校验
        if(StringUtils.isEmpty(map.get("id"))) return error("简历id不能为空");
        String statusCode ;
        String message ;
        try{
            Integer num = resumeService.updateSumMsg4cssp(map);

            if( num > 0 ){
                message = "修改简历自我描述成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                /*Map<String,Object> map=new HashMap<>();
                map.put("id",basicMsgBean.getId());*/
                republish(map);
                return result(statusCode, message);
            }else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "修改简历自我描述失败";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("修改简历自我描述异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 修改简历技能特长
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateSkillMsg", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result updateSkillMsg(@RequestBody Map<String,Object> map) throws Exception{
        //入参校验
        if(StringUtils.isEmpty(map.get("id"))) return error("简历id不能为空");
        String statusCode ;
        String message ;
        try{
            Integer num = resumeService.updateSkillMsg4cssp(map);

            if( num > 0 ){
                message = "修改简历技能特长成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
               /* Map<String,Object> map=new HashMap<>();
                map.put("id",basicMsgBean.getId());*/
                republish(map);
                return result(statusCode, message);
            }else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "修改简历技能特长失败";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("修改简历技能特长异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 修改简历获奖情况
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updatePrizesMsg", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result updatePrizesMsg(@RequestBody Map<String,Object> map) throws Exception{
        //入参校验
        if(StringUtils.isEmpty(map.get("id"))) return error("简历id不能为空");
        String statusCode ;
        String message ;
        try{
            Integer num = resumeService.updatePrizesMsg4cssp(map);
            if( num > 0 ){
                message = "修改简历获奖情况成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            }else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "修改简历获奖情况失败";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error( " 修改简历获奖情况异常：" , e);
        }
        return result(statusCode, message);
    }

    /**
     * 修改个人简历的更新时间
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/republish", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result republish(@RequestBody Map<String,Object> map) throws Exception{
        //入参校验
        if(StringUtils.isEmpty(map.get("id"))) return error("简历id不能为空");
        String statusCode ;
        String message ;
        try{
            Integer num = resumeService.republish4cssp(map);
            if( num > 0 ){
                message = "简历更新成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message);
            }else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "简历更新失败";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error( " 简历更新异常：" , e);
        }
        return result(statusCode, message);
    }

    /**
     * 获取投递的简历列表
     * @param deliverResumeBean
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getDeliverResumeList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getDeliverResumeList(@RequestBody DeliverResumeBean deliverResumeBean ) throws Exception {
        //入参校验
        if(StringUtils.isEmpty(deliverResumeBean.getJobId())) return error("职位id不能为空");
        String statusCode ;
        String message ;
        Page<DeliverResumeVO> page=new Page<>(deliverResumeBean.getPageno(),deliverResumeBean.getPagesize());
        List<DeliverResumeVO> list=new ArrayList<>();
        try{
            deliverResumeBean.setPage(page);
            list=resumeService.getDeliverResumeList4cssp(deliverResumeBean);
            if( list.size() > 0 ){
                message = "获取投递的简历列表成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                page.setData(list);
            }else {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "获取投递的简历列表暂无";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("获取投递的简历列表异常：", e);
        }
        return result(statusCode, message,page);
    }

    /**
     * 查询投递该职位的简历的总数
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getCounts", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCounts(@RequestBody Map<String,Object> map ) throws Exception {
        //入参校验
        if(StringUtils.isEmpty(map.get("jobId"))) return error("岗位id不能为空");
        String statusCode ;
        String message ;
        Integer count=0;
        try{
          count=resumeService.getCounts4cssp(map);
            if( count > 0 ){
                message = "查询投递该职位的简历的总数成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;

            }else {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "查询投递该职位的简历的总数暂无";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("查询投递该职位的简历的总数异常：", e);
        }
        return result(statusCode, message,count);
    }

    /**
     * 查询投递该职位的简历未阅读的简历数量
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getNoReadCounts", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getNoReadCounts(@RequestBody Map<String,Object> map ) throws Exception {
        //入参校验
        if(StringUtils.isEmpty(map.get("jobId"))) return error("岗位id不能为空");
        String statusCode ;
        String message ;
        try{
            Integer count=resumeService.getNoReadCounts4cssp(map);
            if( count > 0 ){
                message = "查询投递该职位的简历未阅读的简历数量成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message,count);
            }else {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "查询投递该职位的简历未阅读的简历数量暂无";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("查询投递该职位的简历未阅读的简历数量异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     *简历阅读
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateType", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result updateType(@RequestBody Map<String,Object> map ) throws Exception {
        //入参校验
        if(StringUtils.isEmpty(map.get("id"))) return error("id不能为空");
        String statusCode ;
        String message ;
        try{
            Integer count=resumeService.updateType4cssp(map);
            if( count > 0 ){
                message = "简历阅读成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                return result(statusCode, message,count);
            }else {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "简历阅读失败";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("简历阅读异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 查询投递该企业的简历的数量
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getDeliverCounts", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getDeliverCounts(@RequestBody Map<String,Object> map ) throws Exception {
        //入参校验
        if(StringUtils.isEmpty(map.get("coId"))) return error("公司id不能为空");
        String statusCode ;
        String message ;
        Integer count=resumeService.getDeliverCounts4cssp(map);
        try{
            if( count > 0 ){
                message = "查询数量成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
            }else {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "查询数量暂无";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("查询数量异常：", e);
        }
        return result(statusCode, message,count);
    }

    /**
     * 查询该企业的职位数量
     * @param map
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/getJobCounts", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getJobCounts(@RequestBody Map<String,Object> map ) throws Exception {
        //入参校验
        if(StringUtils.isEmpty(map.get("coId"))) return error("公司id不能为空");
        String statusCode ;
        String message ;
        Integer count=resumeService.getJobCounts4cssp(map);
        try{
            if( count > 0 ){
                message = "查询数量成功";
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
            }else {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "查询数量暂无";
            }
        }catch (Exception e){
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("查询数量异常：", e);
        }
        return result(statusCode, message,count);
    }



}
