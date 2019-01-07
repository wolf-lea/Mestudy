package com.tecsun.sisp.adapter.modules.treatment.controller;

import com.tecsun.sisp.adapter.common.util.*;
import com.tecsun.sisp.adapter.common.util.treatment.TreatmentIface;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.common.entity.request.PicBean;
import com.tecsun.sisp.adapter.modules.common.entity.request.PicBusBean;
import com.tecsun.sisp.adapter.modules.common.entity.request.SecQueryBean;
import com.tecsun.sisp.adapter.modules.common.service.impl.CommServiceImpl;
import com.tecsun.sisp.adapter.modules.treatment.entity.request.*;
import com.tecsun.sisp.adapter.modules.treatment.entity.response.*;
import com.tecsun.sisp.adapter.modules.treatment.service.impl.TreatServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**待遇资格认证业务-基础接口
 * Created by danmeng on 2017/3/13.
 */

@Controller
@RequestMapping(value = "/adapter/treatment")
public class TreatmentController extends BaseController {

    private static Logger logger = LoggerFactory.getLogger(TreatmentController.class);
    @Autowired
    private TreatServiceImpl treatService;

    @Autowired
    private CommServiceImpl commService;

    //系统内部调用 根据身份证获取PERSON_ID,TREAT_ID,判断个人数据是否存在平台
    public TreatPersonBean isPersonTreat(String sfzh,String xm) throws Exception {
        return treatService.isPersonTreat4Cssp(sfzh,xm);
    }

    /**
     * 待遇资格认证-个人基本信息
     * 用姓名+身份证查询：先查平台数据，无则查第三方（认证云或其他来源）
     * 操作一：先查平台数据(不包括头像)
     * 操作二：平台无个人信息，从第三方获取（根据项目要求，来源为认证云或其他）
     * 操作三 基本数据若第三方获取，如果用身份证号查询且数据只有一条，则将个人信息存入公服平台数据库
     * 操作四（返回数据为一条时需包括头像personPicPhoto）：查看认证云采集数据接口（将建模照片充当头像）,如果认证云未采集，则头像为第三方来源基本信息中的头像
     * 存储数据要求：
     * 个人基础数据T_YTH_BASIC_PERSON_INFO，待遇基础数据 T_YTH_TREAT_INFO
     * 性别	01男性;02女性;03未知
     * 返回民族为中文，存数据库为编码
     * 待完善
     * *用姓名+性别直接查询第三方数据(项目上完善)
     * 用姓名+性别或其他查询为多条记录（不入存平台，且不返回头像）
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getTreatPersonInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getTreatPersonInfo(@RequestBody TreatPersonBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        //姓名+身份证 、姓名+性别(项目上完善)，看项目具体需求
//        if (StringUtils.isNotBlank(bean.getXm()) && (StringUtils.isNotBlank(bean.getSfzh()) || StringUtils.isNotBlank(bean.getSex()))) {
        if (StringUtils.isNotBlank(bean.getXm()) && StringUtils.isNotBlank(bean.getSfzh())) {
            bean.setPageno(bean.getPageno() <= 0 ? 1 : bean.getPageno());
            bean.setPagesize(bean.getPagesize() <= 0 ? 10 : bean.getPagesize());
            Page<TreatPersonVO> page = new Page(bean.getPageno(), bean.getPagesize());
            try {
                bean.setPage(page);
                String personPicBase64 = "";//第三方获取头像
                String picBase64 = null;//查看认证云采集数据（获取头像）
                //操作一：用姓名+身份证查询：先查平台数据(不包括头像)
                List<TreatPersonVO> list = treatService.getTreatPersonInfo4Cssp(bean);
                if ( list != null && list.size() == 1 && list.get(0).getPersonId() != 0) {
                    bean.setPersonId(list.get(0).getPersonId());//获取个人id
                }
                if ( list == null || list.size() == 0 || list.get(0).getPersonId() == 0 || list.get(0).getTreatId() == 0) {
                    //操作二：平台无个人信息，从第三方获取（根据项目要求，来源为认证云或其他）
                    //示例 来源一：认证云Start-----------
                    list = new ArrayList<TreatPersonVO>();
//                    认证云暂时不提供性别查询
                    Map map = TreatmentIface.selectPersonInfo(bean.getSfzh(), bean.getXm());
                    statusCode = String.valueOf(map.get("statusCode"));
                    message = String.valueOf(map.get("message"));
                    if (Constants.RESULT_MESSAGE_SUCCESS.equals( String.valueOf(map.get("statusCode"))) && map.get("result") != null) {
                        String data = JsonMapper.buildNormalMapper().toJson(map.get("result"));
                        RZYPersonVO rzyPersonVO = JsonMapper.buildNormalMapper().fromJson(data, RZYPersonVO.class);
                        TreatPersonVO personVO = new TreatPersonVO();
                        if (rzyPersonVO != null) {
                            personVO.setSfzh(rzyPersonVO.getIdcard());
                            personVO.setXm(rzyPersonVO.getName());
                            personVO.setSex(rzyPersonVO.getSex());
                            personVO.setAddress(rzyPersonVO.getAddress());
                            personVO.setPhone(rzyPersonVO.getPhone());
                            personVO.setSbkh(rzyPersonVO.getSbkh());
                            personVO.setCompany(rzyPersonVO.getCompany());
                            //返回民族如：汉
                            String nation = StringUtils.isNotBlank(rzyPersonVO.getNation()) ? rzyPersonVO.getNation().replace("族", "") : "";
                            nation = Constants.NATION_Code.get(nation); //将中文转为编码以便存入数据库
                            personVO.setNation(nation);
                            list.add(personVO);
                        }
                    }
                    //认证云end-----------
                    //来源二：其他Start(如使用其他来源，可删除认证云)-----------
                    personPicBase64 = "";//保存第三方获取的头像
                    //来源二：其他end-----------
                    //操作三 基本数据若第三方获取，如果用身份证号查询且数据只有一条，将信息赋予personBean，并存入平台数据库
                    if (StringUtils.isNotBlank(bean.getSfzh()) && list != null && list.size() == 1) {
                        TreatPersonBean personBean = new TreatPersonBean();
                        BeanUtils.copyProperties(list.get(0), personBean);
                        personBean.setPersonId(bean.getPersonId());
                        TreatPersonVO treatPersonVO = treatService.saveTreatPersonInfo4Cssp(personBean);
                        if (treatPersonVO != null && treatPersonVO.getPersonId() != 0) {
                            list.get(0).setPersonId(treatPersonVO.getPersonId());
                        } else {
                            logger.error("待遇资格认证-个人基本信息失败：存入数据库失败");
                            return result(Constants.RESULT_MESSAGE_ERROR, "个人信息查询失败");
                        }
                    }
                }
                if (list != null && !list.isEmpty()) {
                    if (list.size() == 1) {
                        //操作四：查看认证云采集数据（获取头像）
                        Map colMap = TreatmentIface.queryCollectInfo(bean.getSfzh(), bean.getXm(), "01");//认证方式 人脸
                        if (Constants.RESULT_MESSAGE_SUCCESS.equals(String.valueOf(colMap.get("statusCode"))) && colMap.get("result") != null) {
                            String data = JsonMapper.buildNormalMapper().toJson(colMap.get("result"));
                            List<CollIntoVO> collIntoVOs = JsonHelper.jsonToList(data, CollIntoVO.class);
                            if (StringUtils.isNotBlank(collIntoVOs.get(0).getCollectdata()))
                                picBase64 = collIntoVOs.get(0).getCollectdata();
                        }
                        //如果认证云未采集，则头像为第三方获取头像
                        if (StringUtils.isBlank(picBase64)) {
                            picBase64 = personPicBase64;
                        }
                        list.get(0).setPersonPicPhoto(picBase64);
                    }
                    page.setCount(0);
                    //将民族\性别编码转为中文
                    for (TreatPersonVO vo : list) {
                        vo.setSex(DictionaryUtil.getDictName(Constants.SEX_GROUP, vo.getSex()));
                        vo.setNation(Constants.NATION.get(vo.getNation()));
                    }
                    //count为总条数：如查询第三方则由第三方返回
                    page.setCount(list.size());
                    page.setData(list);
                    statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                    message = "查询成功";
                }else{
                    message = "暂无该用户信息";
                }
            } catch (Exception e) {
                statusCode = Constants.RESULT_MESSAGE_ERROR;
                message = "操作失败";
                logger.error("待遇资格认证-个人基本信息失败：", e);
            }
            return result(statusCode, message, page);
        } else return this.result(Constants.RESULT_MESSAGE_PARAM, "查询姓名、证件号不能为空");
    }

    /**
     * 待遇资格认证-保存个人基本信息
     * 注意 民族编码格式：汉 ，不是汉族
     * 性别	01男性;02女性;03未知
     * 存储数据要求：
     * 个人基础数据T_YTH_BASIC_PERSON_INFO，待遇基础数据 T_YTH_TREAT_INFO
     * @param bean
     * @return
     */
    @RequestMapping(value = "/saveTreatPersonInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result saveTreatPersonInfo(@RequestBody TreatPersonBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        TreatPersonVO treatPersonVO = new TreatPersonVO();
        if (StringUtils.isBlank(bean.getSfzh()) || StringUtils.isBlank(bean.getXm())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "姓名、证件号不能为空");
        }
        try {
            TreatPersonBean personBean = isPersonTreat(bean.getSfzh(),bean.getXm());
            if (personBean != null) {
                bean.setPersonId(personBean.getPersonId());
                bean.setTreatId(personBean.getTreatId());
            }
            //具体项目是否保存民族
            String nation = StringUtils.isNotBlank(bean.getNation()) ? bean.getNation().replace("族", "") : "";
            bean.setNation(Constants.NATION_Code.get(nation));
          /*  if (StringUtils.isBlank(bean.getNation()))
                return this.result(Constants.RESULT_MESSAGE_PARAM, "民族格式不对或未填");*/
            treatPersonVO = treatService.saveTreatPersonInfo4Cssp(bean);
            //根据实际需求调用第三方接口保存数据至第三方
            if (treatPersonVO != null && treatPersonVO.getPersonId() != 0) {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "操作成功";
            } else return this.result(Constants.RESULT_MESSAGE_ERROR, "身份证信息不全或保存失败");
        } catch (Exception e) {
            logger.error("待遇资格认证-保存个人基本信息失败：", e);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
        }
        return result(statusCode, message, treatPersonVO);
    }

    /**
     * 待遇资格认证-查询个人已采集项
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getCollection", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getCollection(@RequestBody SecQueryBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh())||StringUtils.isBlank(bean.getXm())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "身份证号或姓名不能为空");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        Page<CollistVO> page = new Page(1, bean.getPagesize());
        List<CollistVO> list = new ArrayList<CollistVO>();
        bean.setPage(page);
        try {
            Map map = TreatmentIface.collectList(bean);
            statusCode = String.valueOf(map.get("statusCode"));
            message = String.valueOf(map.get("message"));
            if (Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode) && map.get("result") != null) {
                String data = JsonMapper.buildNormalMapper().toJson(map.get("result"));
                list = JsonHelper.jsonToList(data, CollistVO.class);
            } else if (Constants.RESULT_MESSAGE_ERROR.equals(statusCode)) {
                message = "暂无该用户采集信息";
            }
            page.setCount(list.size());
            page.setPagesize(list.size());
            page.setData(list);
        } catch (Exception e) {
            logger.error("待遇资格认证-查询个人已采集项异常：", e);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
        }
        return result(statusCode, message, page);
    }

    /**
     * 待遇资格认证-个人认证记录(除初始状态数据)
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getVerifyRecord", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getVerifyRecord(@RequestBody VerifyListBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh())||StringUtils.isBlank(bean.getXm())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "身份证号或姓名不能为空");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        bean.setPageno(bean.getPageno() <= 0 ? 1 : bean.getPageno());
        bean.setPagesize(bean.getPagesize() <= 0 ? 10 : bean.getPagesize());
        Page<VerifyListVO> page = new Page(bean.getPageno(), bean.getPagesize());
        bean.setPage(page);
        try {
            if(StringUtils.isNotBlank(bean.getBeginTime())) {
                try {
                    String beginTime = CommUtil.getFormatDateString(bean.getBeginTime(), "yyyy-MM-dd");
                    bean.setBeginTime(beginTime + " 00:00:00");
                } catch (Exception e) {
                    return this.result(Constants.RESULT_MESSAGE_PARAM, "开始时间不符合格式:yyyy-MM-dd");
                }
            }if(StringUtils.isNotBlank(bean.getEndTime())) {
                try {
                    String endTime = CommUtil.getFormatDateString(bean.getEndTime(), "yyyy-MM-dd");
                    bean.setEndTime(endTime+" 23:59:59");
                }catch (Exception e){
                    return this.result(Constants.RESULT_MESSAGE_PARAM, "结束时间不符合格式:yyyy-MM-dd");
                }
            }
            if ("1".equals(bean.getVerifyResult()) || "01".equals(bean.getVerifyResult()))
                bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_01);
            else if ("0".equals(bean.getVerifyResult()) || "02".equals(bean.getVerifyResult()))
                bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_02);
            List<VerifyListVO> list = treatService.getVerifyRecord4Cssp(bean);
            if (list == null || list.isEmpty()) {
                message = "暂无该用户认证记录";
            } else {
                String verifyTypeName = "";
                for (VerifyListVO vo : list) {
                    String[] verifyType = vo.getType().split(",");
                    for (String type : verifyType) {
                        verifyTypeName += Constants.VERIFY_TYPE.get(type) + ",";
                    }
                    if (verifyTypeName.contains(",")) {
                        verifyTypeName = verifyTypeName.substring(0, verifyTypeName.length() - 1);
                    }
                    vo.setVerifyTypeName(verifyTypeName);
                    vo.setVerifyResult(DictionaryUtil.getDictName(Constants.BUSINESS_STATUS_GROUP, vo.getVerifyResult()));
                    verifyTypeName = "";
                }
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "查询成功";
            }
            page.setData(list);
        } catch (Exception e) {
            logger.error("待遇资格认证-个人认证记录异常：", e);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
        }
        return result(statusCode, message, page);
    }

    /**
     * 待遇资格认证-是否符合认证条件
     * 待完成 (第三方) 入参  sfzh xm
     * @param bean
     * @return
     */
    @RequestMapping(value = "/isVerification", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result isVerification(@RequestBody VerifyBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh()) || StringUtils.isBlank(bean.getXm())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        Object jsonData = "";
        try {
            //调用第三方接口
           /* JsonObject json = new JsonObject();
            String url = null;//第三方提供
            jsonData = DictionaryUtil.postClientRequest(String.valueOf(json), url);
            Result data = JsonMapper.buildNormalMapper().fromJson(jsonData.toString(), Result.class);
            if (data != null) {
                statusCode = data.getStatusCode();
                message = data.getMessage();
                jsonData = data.getData();
            }*/
            statusCode = Constants.RESULT_MESSAGE_SUCCESS;
            message = "符合认证条件";
        } catch (Exception e) {
            logger.error("待遇资格认证-是否符合认证条件异常：", e);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
        }
        return result(statusCode, message, null);
    }


    /**
     * 预存认证结果
     * @param bean
     * @return
     */
    @RequestMapping(value = "/insertVerifyResult", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result insertVerifyResult(@RequestBody VerifyBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "预存失败";
        VerifyResult vResult = new VerifyResult();
        try {
            bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_00);
            treatService.insertVerify4Cssp(bean);
            vResult.setVerifyId(bean.getVerifyId());
            statusCode = Constants.RESULT_MESSAGE_SUCCESS;
            message = "预存成功";
        } catch (Exception e) {
            logger.error("预存认证结果异常：", e);
        }
        return result(statusCode, message, vResult);
    }

    /**
     * 保存最终认证结果
     * @param bean
     * @return
     */
    @RequestMapping(value = "/saveVerifyResult", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result saveVerifyResult(@RequestBody VerifyBean bean) throws Exception {
        if (bean.getVerifyId() == 0 || StringUtils.isBlank(bean.getVerifyResult())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        if ("1".equals(bean.getVerifyResult())||"01".equals(bean.getVerifyResult()))
            bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_01);
        else bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_02);
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "保存失败";
        try {
            if (Constants.PIXEL_BUSINESS_STATUS_02.equals(bean.getVerifyResult()) && StringUtils.isEmpty(bean.getFailReason()))
                bean.setFailReason("认证失败");
            if (treatService.updateVerify4Cssp(bean) > 0) {
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "保存成功";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "保存失败";
            logger.error("保存最终认证结果异常：", e);
        }
        return result(statusCode, message);
    }


    /**
     * 查询个人采集数据
     * @param bean
     * @return
     */
    @RequestMapping(value = "/queryCollectInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result queryCollectInfo(@RequestBody CollectBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh())
                || StringUtils.isBlank(bean.getXm())
                || StringUtils.isBlank(bean.getColType())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        bean.setPageno(bean.getPageno() <= 0 ? 1 : bean.getPageno());
        bean.setPagesize(bean.getPagesize() <= 0 ? 10 : bean.getPagesize());
        Page<CollIntoVO> page = new Page(bean.getPageno(), bean.getPagesize());
        List<CollIntoVO> list = new ArrayList<CollIntoVO>();
        bean.setPage(page);
        try {
            Map map = TreatmentIface.queryCollectInfo(bean.getSfzh(),bean.getXm(),bean.getColType());
            statusCode = String.valueOf(map.get("statusCode"));
            message = String.valueOf(map.get("message"));
            if (Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode) && map.get("result") != null) {
                String data = JsonMapper.buildNormalMapper().toJson(map.get("result"));
                list = JsonHelper.jsonToList(data, CollIntoVO.class);
                page.setCount(list.size());
            } else if (Constants.RESULT_MESSAGE_ERROR.equals(statusCode)) {
                message = "暂无该用户采集数据";
            }
            page.setData(list);
        } catch (Exception e) {
            logger.error("待遇资格认证-查询个人采集数据异常：", e);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
        }
        return result(statusCode, message, page);
    }
    /**
     * 保存认证操作（不上传认证云）
     * @param bean
     * @return
     */
    @RequestMapping(value = "/saveVerifyOperation", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result saveVerifyOperation(@RequestBody VerifyBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getSfzh()) || StringUtils.isBlank(bean.getXm()) || bean.getVerifyId() == 0
                || StringUtils.isBlank(bean.getDeviceid()) || StringUtils.isBlank(bean.getVerifyBus())
                || StringUtils.isBlank(bean.getVerifyType()) || StringUtils.isBlank(bean.getVerifyResult())
                || StringUtils.isBlank(bean.getVerifyChannel())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "参数不全");
        }
        if ("1".equals(bean.getVerifyResult()) || "01".equals(bean.getVerifyResult()))
            bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_01);
        else bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_02);
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "保存失败";
        VerifyResult vResult = new VerifyResult();
        try {
            if (treatService.isExistVerify4Cssp(bean.getVerifyId()) <= 0) {
                message = "查不到该认证id对应数据";
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            // 渠道类型：德生宝  地址为空则根据设备号查设备所在地
            if ((Constants.TSB).equals(bean.getChannelcode()) && StringUtils.isEmpty(bean.getVerifyAddress())) {
                bean.setVerifyAddress(commService.getTsbAddress4Sisp(bean.getDeviceid()));
            }
            TreatPersonBean personBean = isPersonTreat(bean.getSfzh(),bean.getXm());
            if (personBean == null || personBean.getPersonId() == 0 || personBean.getTreatId() == 0) {
                message = "暂无该用户信息";
                bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_02);
                return this.result(Constants.RESULT_MESSAGE_PARAM, message);
            }
            bean.setPersonId(personBean.getPersonId());
            //如果认证图片不为空，则查询图片相关信息
            String picType = Constants.PICTURE_TYPE_000;
            if (bean.getVerifyData() != 0) {
                if ("01".equals(bean.getVerifyType()) || "08".equals(bean.getVerifyType()))
                    picType = Constants.PICTURE_TYPE_203;
                else if ("06".equals(bean.getVerifyType())) picType = Constants.PICTURE_TYPE_206;
                else if ("07".equals(bean.getVerifyType())) picType = Constants.PICTURE_TYPE_209;
                String picPath = commService.photoIsExist4Cssp(bean.getVerifyData(), picType);
                if (Constants.RESULT_MESSAGE_PARAM.equals(picPath)) {
                    bean.setVerifyResult(Constants.PIXEL_BUSINESS_STATUS_02);
                    logger.error("sfzh=" + bean.getSfzh() + " picId=" + bean.getVerifyData() + " 图片不存在");
                    return this.result(Constants.RESULT_MESSAGE_PARAM, "认证图片不存在");
                }
            }
            //如果认证图片不为空，则存储图片相关信息
            if (bean.getVerifyData() != 0) {
                commService.photoTOPersonAndBus4Cssp(bean.getVerifyData(), bean.getPersonId(), picType, bean.getVerifyResult());
            }
            if (Constants.PIXEL_BUSINESS_STATUS_02.equals(bean.getVerifyResult()) && StringUtils.isEmpty(bean.getFailReason()))
                bean.setFailReason("认证失败");
            bean.setVerifyTime(CommUtil.getNowDateLongStr());
            treatService.insertVerifyOperation4Cssp(bean);
            vResult.setOperationId(bean.getOperationId());
            vResult.setVerifyTime(bean.getVerifyTime());
            treatService.insertVerifyAndOpera4Cssp(bean);
            message="保存成功";
            statusCode=Constants.RESULT_MESSAGE_SUCCESS;
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "保存失败";
            logger.error("保存认证操作异常：", e);
        }
        return result(statusCode, message);
    }
}