package com.tecsun.sisp.adapter.modules.his.controller;

import com.tecsun.sisp.adapter.common.util.*;
import com.tecsun.sisp.adapter.modules.common.controller.BaseController;
import com.tecsun.sisp.adapter.modules.his.entity.request.*;
import com.tecsun.sisp.adapter.modules.his.entity.response.AllHisInfoVO;
import com.tecsun.sisp.adapter.modules.his.entity.response.HisOrderDetailVO;
import com.tecsun.sisp.adapter.modules.his.entity.response.HisOrderVO;
import com.tecsun.sisp.adapter.modules.his.hisUtils.HisFactory;
import com.tecsun.sisp.adapter.modules.his.hisUtils.HisIface;
import com.tecsun.sisp.adapter.modules.his.service.impl.HisServiceImpl;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**医院相关:根据前端要求--医院模块医院相关信息无论是否查询到数据，只要接口调用成功就返回200
 * 使用工厂模式：案例类 HisIface、 TestHisFirstImpl、 TestHisSecondImpl
 * 医院基本信息及编码需存入平台数据库sisp 用户的表T_YTH_HIS_ALL_INFO
 *注意：需保证平台数据库数据的正确性（查看记录、取消挂号均只操作本平台数据库已有数据）
 * Created by danmeng on 2017/7/10.
 */
@Controller
@RequestMapping(value = "/adapter/his")
public class HisController extends BaseController {

    public final static Logger logger = Logger.getLogger(HisController.class);
    public final static String HIS_PATH = Config.getInstance().get("picture_path_his");  //医院图片

    @Autowired
    private HisServiceImpl hisService;
    /**
     * 获取所有医院信息：需手动将对接医院基本信息添加到sisp用户的表T_YTH_HIS_ALL_INFO
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getAllHisInfo", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getAllHisInfo(@RequestBody HisBean bean) throws Exception {
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {//支持对医院名称模糊查询
            Page<AllHisInfoVO> page = new Page(bean.getPageno(), bean.getPagesize());
            bean.setPage(page);
            List<AllHisInfoVO> voList = hisService.getAllHisInfo4Cssp(bean);
            statusCode = Constants.RESULT_MESSAGE_SUCCESS;
            if (voList != null && voList.size() > 0) {
                //生活号需要计算当前位置与医院的距离
                if(StringUtils.isNotEmpty(bean.getLocation())){
                    voList = getDis(bean.getLocation(),voList);
                }
                for (int i = 0; i < voList.size(); i++) {
                    if (StringUtils.isNotBlank(voList.get(i).getHospitalPicture()))
                        voList.get(i).setHospitalPictureBase64(ImageChangeUtil.getImageStr(HIS_PATH + File.separator + voList.get(i).getHospitalPicture()));
                }
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "查询成功";
                page.setData(voList);
                return result(statusCode, message, page);
            }else message="暂无医院信息";
        } catch (Exception e) {
            logger.error("获取所有医院信息失败：", e);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
        }
        return this.result(statusCode, message);
    }

    /**
     * 获取医院详情
     * @param bean
     * @return 200-查询成功有数据 0-失败或无数据
     */
    @RequestMapping(value = "/getHospitalDetail", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getHospitalDetail(@RequestBody HisBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getHospitalId())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入医院信息");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        Map<String, String> map = JsonMapper.buildNormalMapper().fromJson(JsonHelper.javaBeanToJson(bean), HashMap.class);
        try {
            HisIface hisIface = HisFactory.getInstance(bean.getHospitalId());
            Map<String, Object> resultMap = hisIface.getHospitalDetail(map);
            if(resultMap!=null&&!resultMap.isEmpty()) {
                statusCode = (String) resultMap.get("statusCode");
                message = (String) resultMap.get("message");
                Object detailVO = resultMap.get("data");//HisDetailVO类
                if (Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode)) {
                    if (detailVO != null) {
                        return result(statusCode, message, detailVO);
                    } else {
                        return result(Constants.RESULT_MESSAGE_SUCCESS, "暂无医院详情");
                    }
                }
            }
         } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("获取医院详情异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 获取科室信息
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getDeptmentList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getDeptmentList(@RequestBody HisBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getHospitalId())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入查询信息");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            Map<String, Object> map = JsonMapper.buildNormalMapper().fromJson(JsonHelper.javaBeanToJson(bean), HashMap.class);
            //加分页及默认字段
            map=ParamUtil.addPage(map);
            HisIface hisIface = HisFactory.getInstance(bean.getHospitalId());
            Map<String, Object> resultMap = hisIface.getDeptment(map);
            if(resultMap!=null&&!resultMap.isEmpty()) {
                statusCode = (String) resultMap.get("statusCode");
                message = (String) resultMap.get("message");
                Object voList = resultMap.get("data");//DeptmentVO
                if (Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode)) {
                    if (voList != null && voList != "[]") {
                        Object page = ParamUtil.backPage(map.get("pageno"), map.get("pagesize"), resultMap.get("total"), voList);
                        return result(statusCode, message, page);
                    } else {
                        return result(Constants.RESULT_MESSAGE_SUCCESS, "暂无科室信息");
                    }
                }
            }
         } catch (Exception e) {
            logger.error("获取所有科室信息失败：", e);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
        }
        return this.result(statusCode, message);
    }

    /**
     * 获取科室详情
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getDeptmentDetail", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getDeptmentDetail(@RequestBody HisBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getHospitalId())||StringUtils.isBlank(bean.getDeptCode())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入查询信息");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            Map<String, Object> map = JsonMapper.buildNormalMapper().fromJson(JsonHelper.javaBeanToJson(bean), HashMap.class);
            HisIface hisIface = HisFactory.getInstance(bean.getHospitalId());
            Map<String, Object> resultMap = hisIface.getDeptmentDetail(map);
            if(resultMap!=null&&!resultMap.isEmpty()) {
                statusCode = (String) resultMap.get("statusCode");
                message = (String) resultMap.get("message");
                Object deptmentVO = resultMap.get("data");//DeptmentVO类
                if (Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode)) {
                    if (deptmentVO != null) {
                        return result(statusCode, message, deptmentVO);
                    } else {
                        return result(Constants.RESULT_MESSAGE_SUCCESS, "暂无科室详情");
                    }
                }
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("获取科室详情异常：", e);
        }
        return result(statusCode, message);
    }
    /**
     * 获取所有医生
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getDoctorList", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getDoctorList(@RequestBody HisBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getHospitalId()) || StringUtils.isBlank(bean.getDeptCode())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入查询信息");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            Map<String, Object> map = JsonMapper.buildNormalMapper().fromJson(JsonHelper.javaBeanToJson(bean), HashMap.class);
            //加分页及默认字段
            map = ParamUtil.addPage(map);
            HisIface hisIface = HisFactory.getInstance(bean.getHospitalId());
            Map<String, Object> resultMap = hisIface.getDoctor(map);
            if (resultMap != null && !resultMap.isEmpty()) {
                statusCode = (String) resultMap.get("statusCode");
                message = (String) resultMap.get("message");
                Object voList = resultMap.get("data");//DoctorVO
                if (Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode)) {
                    if (voList != null && voList != "[]") {
                        Object page = ParamUtil.backPage(map.get("pageno"), map.get("pagesize"), resultMap.get("total"), voList);
                        return result(statusCode, message, page);
                    } else {
                        return result(Constants.RESULT_MESSAGE_SUCCESS, "暂无医生信息");
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取所有医生信息失败：", e);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
        }
        return this.result(statusCode, message);
    }

    /**
     * 获取医生详情
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getDoctorDetail", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getDoctorDetail(@RequestBody HisBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getHospitalId()) || StringUtils.isBlank(bean.getDeptCode()) || StringUtils.isBlank(bean.getDoctorNo())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入查询信息");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            Map<String, Object> map = JsonMapper.buildNormalMapper().fromJson(JsonHelper.javaBeanToJson(bean), HashMap.class);
            HisIface hisIface = HisFactory.getInstance(bean.getHospitalId());
            Map<String, Object> resultMap = hisIface.getDoctorDetail(map);
            if (resultMap != null && !resultMap.isEmpty()) {
                statusCode = (String) resultMap.get("statusCode");
                message = (String) resultMap.get("message");
                Object doctorVO = resultMap.get("data");//DoctorVO类
                if (Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode)) {
                    if (doctorVO != null) {
                        return result(statusCode, message, doctorVO);
                    } else {
                        return result(Constants.RESULT_MESSAGE_SUCCESS, "暂无医生详情");
                    }
                }
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("获取医生详情异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 获取医生对应排班表
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getDoctorSchedule", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getDoctorSchedule(@RequestBody HisBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getHospitalId()) || StringUtils.isBlank(bean.getDeptCode()) || StringUtils.isBlank(bean.getDoctorNo())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入查询信息");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            Map<String, Object> map = JsonMapper.buildNormalMapper().fromJson(JsonHelper.javaBeanToJson(bean), HashMap.class);
            //加分页及默认字段
            map = ParamUtil.addPage(map);
            HisIface hisIface = HisFactory.getInstance(bean.getHospitalId());
            Map<String, Object> resultMap = hisIface.getDoctorSchedule(map);
            if (resultMap != null && !resultMap.isEmpty()) {
                statusCode = (String) resultMap.get("statusCode");
                message = (String) resultMap.get("message");
                Object voList = resultMap.get("data");//List<DoctorScheduleVO>  类
                if (Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode)) {
                    if (voList != null && voList != "[]") {
                        Object page = ParamUtil.backPage(map.get("pageno"), map.get("pagesize"), resultMap.get("total"), voList);
                        return result(statusCode, message, page);
                    } else {
                        return result(Constants.RESULT_MESSAGE_SUCCESS, "暂无医生对应排班表");
                    }
                }
            }
        } catch (Exception e) {
            logger.error("获取医生对应排班表失败：", e);
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
        }
        return this.result(statusCode, message);
    }


    /**
     * 预约：当天之后 预约后记录必须保存在本平台数据库以供查询和取消
     * 要求：同一天同一时间段每人只能预约1次；同一医院、同一个科室，每人每天只能预约1次。
     * @param bean
     * @return
     */
    @RequestMapping(value = "/clinicAppoints", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result clinicAppoints(@RequestBody AppointBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getHospitalId()) || StringUtils.isBlank(bean.getHospitalName())
                || StringUtils.isBlank(bean.getDoctorName()) || StringUtils.isBlank(bean.getDeptCode())
                || StringUtils.isBlank(bean.getDoctorNo()) || StringUtils.isBlank(bean.getDoctorName())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入医院信息");
        }
        if (StringUtils.isBlank(bean.getClinicLabel()) || StringUtils.isBlank(bean.getClinicDate())
                || StringUtils.isBlank(bean.getTimeInterval()) || bean.getRegistrationFee() == 0
                || bean.getClinicFee() == 0) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入号源信息");
        }
        if (StringUtils.isBlank(bean.getCardType()) || StringUtils.isBlank(bean.getPatientIdcard())
                || StringUtils.isBlank(bean.getPatientName())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入预约者信息");
        }
        if(StringUtils.isNotBlank(bean.getClinicDate())) {
            try {
                String startDate = CommUtil.getFormatDateString(bean.getClinicDate(), "yyyy-MM-dd");
            } catch (Exception e) {
                return this.result(Constants.RESULT_MESSAGE_PARAM, "预约不符合格式:yyyy-MM-dd");
            }
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        int appointStatus = hisService.canHisAppoint4Cssp(bean);//appointStatus=1符合预约要求
        if (appointStatus != 1) {
            if (appointStatus == -2) {
                message = "同一天同一时间段每人只能预约1次,请重新选择";
            } else if (appointStatus == -1) {
                message = "同一医院、同一个科室，每人每天只能预约1次,请重新选择";
            } else if (appointStatus == -999) {
                message = "检测查询是否符合预约要求失败,请重新选择";
            }
            return this.result(statusCode, message);
        }
        String appointMessage = "预约失败";
        String cancelMessage = "取消失败";
        // 模拟医院预约动作
        String orderId = null;//订单号
        boolean cancelOrder = false;//是否有取消预约操作
        HisOrderVO orderVO = new HisOrderVO();

        try {
            Map<String, Object> map = JsonMapper.buildNormalMapper().fromJson(JsonHelper.javaBeanToJson(bean), HashMap.class);
            HisIface hisIface = HisFactory.getInstance(bean.getHospitalId());
            Map<String, Object> resultMap = hisIface.clinicAppoints(map);
            if (resultMap != null && !resultMap.isEmpty()) {
                statusCode = (String) resultMap.get("statusCode");
                appointMessage = (String) resultMap.get("message");
                orderId = (String) resultMap.get("orderId");
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            appointMessage = "预约失败";
            logger.error("医院-预约插入数据库错误：", e);
        }
        // 模拟医院预约动作结束
        try {
            if (StringUtils.isNotBlank(orderId)) {
                orderVO.setOrderId(orderId);
            }
            if (Constants.RESULT_MESSAGE_ERROR.equals(statusCode)) {
                //取消预约 --根据项目需求挂号失败是否需要 取消预约：如果有则cancelOrder=true
                cancelOrder = false;
            }
            if (!cancelOrder) {
                if (Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode)) {
                    message = "预约成功";
                    bean.setOrderStatus(Constants.HIS_ORDER_STATUS_1);
                } else {
                    bean.setOrderStatus(Constants.HIS_ORDER_STATUS_2);
                    message = appointMessage;
                }
            } else {
                if (Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode)) {
                    message = "预约失败，已成功取消";
                    bean.setOrderStatus(Constants.HIS_ORDER_STATUS_3);
                } else {
                    bean.setOrderStatus(Constants.HIS_ORDER_STATUS_4);
                    message = appointMessage + " " + cancelMessage;
                }
            }
            bean.setOrderId(orderId);
            int flag = hisService.insertHisBusRecord4Cssp(bean);
            if (flag > 0) {
                orderVO.setHisbusId(bean.getHisbusId());
                AppointOrderBean appointOrderBean = new AppointOrderBean();
                BeanUtils.copyProperties(bean, appointOrderBean);
                appointOrderBean.setAppointStatus(bean.getOrderStatus());
                if (Constants.HIS_ORDER_STATUS_2.equals(appointOrderBean.getAppointStatus())) {
                    appointOrderBean.setFailReason(message);
                }
                appointOrderBean.setAppointTime(CommUtil.getNowDateLongStr());
                hisService.insertHisAppointInfo4Cssp(appointOrderBean);
            } else {
                //取消预约
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "预约失败";
            logger.error("医院-预约插入数据库错误入参：" + JsonHelper.javaBeanToJson(bean), e);
            //取消预约
        }
        return result(statusCode, message, orderVO);
    }

    /**
     * 取消挂号订单:只能取消本平台的订单记录
     * @param bean
     * @return
     */
    @RequestMapping(value = "/cancelHisOrder", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result cancelHisOrder(@RequestBody AppointBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getHospitalId()) || StringUtils.isBlank(bean.getDeptCode())
                || StringUtils.isBlank(bean.getDoctorNo())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入医院信息");
        }
        if (StringUtils.isBlank(bean.getClinicLabel()) || StringUtils.isBlank(bean.getOrderId())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入号源信息");
        }
        if (StringUtils.isBlank(bean.getPatientIdcard()) || StringUtils.isBlank(bean.getPatientName())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入就诊人信息");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        String hisMessage = "操作失败";
        Map<String, Object> map = JsonMapper.buildNormalMapper().fromJson(JsonHelper.javaBeanToJson(bean), HashMap.class);
        try {
            List<HisOrderDetailVO> voList = hisService.getHisBusRecord4Cssp(map);
            if (voList != null && voList.size() > 0) {
                if (voList.size() == 1) {
                    //模拟获取取消结果
                    try {
                        HisIface hisIface = HisFactory.getInstance(bean.getHospitalId());
                        Map<String, Object> resultMap = hisIface.cancelHisOrder(map);
                        if (resultMap != null && !resultMap.isEmpty()) {
                            statusCode = (String) resultMap.get("statusCode");
                            hisMessage = (String) resultMap.get("message");
                        }
                    } catch (Exception e) {
                        statusCode = Constants.RESULT_MESSAGE_ERROR;
                        hisMessage = "取消失败";
                        logger.error("取消异常：", e);
                    }
                    //模拟获取取消结果结束
                    try {
                        if (Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode)) {
                            message = "取消成功";
                            bean.setOrderStatus(Constants.HIS_ORDER_STATUS_3);
                        } else {
                            bean.setOrderStatus(Constants.HIS_ORDER_STATUS_4);
                            message = hisMessage;
                        }
                        map.put("orderStatus",bean.getOrderStatus());
                        int flag = hisService.updateHisBusRecord4Cssp(map);
                        if (flag > 0) {
                            CancelOrderBean cancelOrderBean = new CancelOrderBean();
                            BeanUtils.copyProperties(bean, cancelOrderBean);
                            cancelOrderBean.setCancelStatus(bean.getOrderStatus());
                            if (Constants.HIS_ORDER_STATUS_4.equals(bean.getOrderStatus())) {
                                cancelOrderBean.setFailReason(message);
                            }
                            cancelOrderBean.setCancelTime(CommUtil.getNowDateLongStr());
                            hisService.insertHisCancelOrder4Cssp(cancelOrderBean);
                        } else {
                            //更改数据失败
                            //项目需求
                            logger.error("医院-取消挂号更改数据库异常入参：" + JsonHelper.javaBeanToJson(map));
                        }
                    } catch (Exception e) {
                        statusCode = Constants.RESULT_MESSAGE_ERROR;
                        message = "取消失败";
                        logger.error("医院-取消订单记录异常入参：" + JsonHelper.javaBeanToJson(bean), e);
                        //项目需求操作
                        //更改数据失败（注意：需保证平台数据库数据的正确性）
                    }
                } else {
                    message = "该信息对应多个号源信息，请重新选择";
                }
            } else {
                message = "该信息无对应号源信息，请重新选择";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "取消失败";
            logger.error("取消异常：", e);
        }
        return result(statusCode, message);
    }

    /**
     * 获取订单记录-本平台订单记录：
     * 根据产品经理要求：默认取最新10条记录，且订单基本信息取自本平台数据库，订单状态取自医院信息
     * 原因：用户可能在其他渠道 缴费或取号，会导致医院订单状态和本平台不一致，以医院订单状态为准
     * 订单在本平台状态为已取消或已取号则无需查询医院订单状态
     * @param bean
     * @return
     */
    @RequestMapping(value = "/getHisOrderRecord", method = RequestMethod.POST, produces = "application/json")
    @ResponseBody
    public Result getHisOrderRecord(@RequestBody HisOrderBean bean) throws Exception {
        if (StringUtils.isBlank(bean.getPatientIdcard()) || StringUtils.isBlank(bean.getPatientName())) {
            return this.result(Constants.RESULT_MESSAGE_PARAM, "请输入就诊人信息");
        }
        String statusCode = Constants.RESULT_MESSAGE_ERROR;
        String message = "操作失败";
        try {
            if(StringUtils.isNotBlank(bean.getStartDate())) {
                try {
                    String startDate = CommUtil.getFormatDateString(bean.getStartDate(), "yyyy-MM-dd");
                 } catch (Exception e) {
                    return this.result(Constants.RESULT_MESSAGE_PARAM, "开始时间不符合格式:yyyy-MM-dd");
                }
            }if(StringUtils.isNotBlank(bean.getEndDate())) {
                try {
                    String endDate = CommUtil.getFormatDateString(bean.getEndDate(), "yyyy-MM-dd");
                 }catch (Exception e){
                    return this.result(Constants.RESULT_MESSAGE_PARAM, "结束时间不符合格式:yyyy-MM-dd");
                }
            }
            Map<String, Object> map = JsonMapper.buildNormalMapper().fromJson(JsonHelper.javaBeanToJson(bean), HashMap.class);
            map = ParamUtil.addPage(map);
            List<HisOrderDetailVO> voList = hisService.getHisBusRecord4Cssp(map);
            if (voList != null && voList.size() > 0) {
                for (HisOrderDetailVO vo : voList) {
                    HisBean his = new HisBean();
                    if(StringUtils.isEmpty(vo.getDoctorTitle())) {
                        his.setHospitalId(vo.getHospitalId());
                        his.setDoctorNo(vo.getDoctorNo());
                        his.setDeptCode(vo.getDeptCode());
                        Result res = getDoctorDetail(his);
                        if(Constants.RESULT_MESSAGE_SUCCESS.equals(res.getStatusCode())){
                            vo.setDoctorTitle((String)JSONObject.fromObject(res.getData()).get("doctorTitle"));
                        }
                    }
                }
                String orderStatus = null;
                for (HisOrderDetailVO vo : voList) {
                    //订单在本平台状态为已取消或已取号则无需查询医院订单状态
                    if (!Constants.HIS_ORDER_STATUS_3.equals(vo.getOrderStatus()) && !Constants.HIS_ORDER_STATUS_9.equals(vo.getOrderStatus())
                            &&StringUtils.isNotBlank(vo.getOrderId())) {
                        //订单状态取自医院信息
                        Map<String, Object> orderMap = new HashMap<String, Object>();
                        orderMap.put("orderId", vo.getOrderId());
                        orderMap.put("hospitalId", vo.getHospitalId());
                        orderMap.put("patientName", vo.getPatientName());
                        orderMap.put("patientIdcard", vo.getPatientIdcard());
                        HisIface hisIface = HisFactory.getInstance(vo.getHospitalId());
                        Map<String, Object> resultMap = hisIface.getHisOrderStatus(orderMap);
                        if (resultMap != null && !resultMap.isEmpty()) {
                            statusCode = (String) resultMap.get("statusCode");
                            orderStatus = (String) resultMap.get("orderStatus");
                            if (Constants.RESULT_MESSAGE_SUCCESS.equals(statusCode) && StringUtils.isNotBlank(orderStatus)) {
                                vo.setOrderStatus(orderStatus);
                            }
                        }
                    }
                }
                statusCode = Constants.RESULT_MESSAGE_SUCCESS;
                message = "查询成功";
                Page<HisOrderDetailVO> page = (Page<HisOrderDetailVO>) map.get(Constants.PAGE);
                page.setData(voList);
                return result(statusCode, message, page);
            } else {
                message = "就诊人暂无订单记录";
            }
        } catch (Exception e) {
            statusCode = Constants.RESULT_MESSAGE_ERROR;
            message = "操作失败";
            logger.error("获取订单记录异常：", e);
        }
        return result(statusCode, message);
    }

    //计算每个医院的距离并排序
    public List<AllHisInfoVO> getDis(String location,List<AllHisInfoVO> voList) {
        if (StringUtils.isEmpty(location)) {
            return voList;
        }
        //起始位置的经纬度
        String startLonLat = Distance.getCoord(location);
        if(StringUtils.isEmpty(startLonLat)) {
            return voList;
        }
        for (int i = 0; i < voList.size(); i++) {
            if (StringUtils.isEmpty(voList.get(i).getHospitalAddr())) {
                return voList;
            }
            //每个医院位置的经纬度 存在距离的字段中
            String endLonLat = Distance.getCoord(voList.get(i).getHospitalAddr());
            //每个医院位置的距离
            String distance = -1 == Distance.getDistanceByLocal(startLonLat, endLonLat) ? "":String.valueOf(Distance.getDistanceByLocal(startLonLat, endLonLat));
            voList.get(i).setDistance(distance);
        }
        //根据距离排序
        Sort.sort(voList, "distance", true);
        for (int i = 0; i < voList.size(); i++) {
            DecimalFormat df   =new DecimalFormat("#.0");
            String distance = Double.parseDouble(voList.get(i).getDistance()) < 1000 ?
                    (voList.get(i).getDistance().split("\\.")[0] + "m")
                    : (df.format(Double.parseDouble(voList.get(i).getDistance())/1000) + "km");
            voList.get(i).setDistance(distance);
        }
        return voList;
    }
}
