package com.tecsun.sisp.adapter.modules.his.hisUtils.impl;

import com.tecsun.sisp.adapter.common.util.*;
import com.tecsun.sisp.adapter.modules.his.entity.response.*;
import com.tecsun.sisp.adapter.modules.his.hisUtils.HisIface;
import com.tecsun.sisp.adapter.modules.his.service.impl.TestHisServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**模拟第一家医院
 * Created by danmeng on 2017/7/11.
 */
public class TestHisFirstImpl implements HisIface {
    public final static Logger logger = Logger.getLogger(TestHisFirstImpl.class);
    public final static TestHisServiceImpl testHisService = (TestHisServiceImpl) AppContext.getInstance().getService("TestHisService");
    public final static String HIS_PATH = Config.getInstance().get("picture_path_his");  //医院图片

    //    获取医院详情  200-查询成功有数据 0-失败或无数据
    @Override
    public  Map<String, Object> getHospitalDetail(Map map) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("statusCode", Constants.RESULT_MESSAGE_SUCCESS);
        try {
            HisDetailVO vo = testHisService.getHospitalDetail4Other(map);
            if (vo != null) {
                if (StringUtils.isNotBlank(vo.getHospitalPicture()))
                    vo.setHospitalPictureBase64(ImageChangeUtil.getImageStr(HIS_PATH + File.separator + vo.getHospitalPicture()));
                resultMap.put("data", vo);
                resultMap.put("message", "查询成功");
            } else {
                resultMap.put("message", "暂无医院详情");
            }
        } catch (Exception e) {
            logger.error("TestHisSecondIface获取医院详情异常：", e);
            resultMap.put("message", "操作失败");
            resultMap.put("statusCode", Constants.RESULT_MESSAGE_ERROR);
        }
        return resultMap;
    }

    //    获取科室信息  200-查询成功有数据 0-失败或无数据
    @Override
    public Map<String, Object> getDeptment(Map map) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("statusCode", Constants.RESULT_MESSAGE_SUCCESS);
        try {
            List<DeptmentVO> voList = testHisService.getDeptment4Other(map);
            if (voList != null && voList.size() > 0) {
                for (int i = 0; i < voList.size(); i++) {
                    if (StringUtils.isNotBlank(voList.get(i).getDeptPicture()))
                        voList.get(i).setDeptPictureBase64(ImageChangeUtil.getImageStr(HIS_PATH + File.separator + voList.get(i).getDeptPicture()));
                }
                Page<DeptmentVO> page = (Page<DeptmentVO>) map.get(Constants.PAGE);
                resultMap.put("total", page.getCount());//总数
                resultMap.put("data", voList);
                resultMap.put("message", "查询成功");
            } else {
                resultMap.put("message", "暂无科室信息");
            }
        } catch (Exception e) {
            logger.error("TestHisSecondIface获取科室信息异常：", e);
            resultMap.put("message", "操作失败");
            resultMap.put("statusCode", Constants.RESULT_MESSAGE_ERROR);
        }
        return resultMap;
    }

    //    获取科室详情
    @Override
    public Map<String, Object> getDeptmentDetail(Map map) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("statusCode", Constants.RESULT_MESSAGE_SUCCESS);
        try {
            DeptmentVO vo = testHisService.getDeptmentDetail4Other(map);
            if (vo != null) {
                if (StringUtils.isNotBlank(vo.getDeptPicture()))
                    vo.setDeptPictureBase64(ImageChangeUtil.getImageStr(HIS_PATH + File.separator + vo.getDeptPicture()));
                resultMap.put("data", vo);
                resultMap.put("message", "查询成功");
            } else {
                resultMap.put("message", "暂无科室详情");
            }
        } catch (Exception e) {
            logger.error("TestHisSecondIface获取科室详情异常：", e);
            resultMap.put("message", "操作失败");
            resultMap.put("statusCode", Constants.RESULT_MESSAGE_ERROR);
        }
        return resultMap;
    }

    //    获取所有医生信息
    @Override
    public Map<String, Object> getDoctor(Map map) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("statusCode", Constants.RESULT_MESSAGE_SUCCESS);
        try {
            List<DoctorVO> voList = testHisService.getDoctor4Other(map);
            if (voList != null && voList.size() > 0) {
                for (int i = 0; i < voList.size(); i++) {
                    if (StringUtils.isNotBlank(voList.get(i).getDoctorPicture()))
                        voList.get(i).setDoctorPictBase64(ImageChangeUtil.getImageStr(HIS_PATH + File.separator + voList.get(i).getDoctorPicture()));
                }
                Page<DeptmentVO> page = (Page<DeptmentVO>) map.get(Constants.PAGE);
                resultMap.put("total", page.getCount());//总数
                resultMap.put("data", voList);
                resultMap.put("message", "查询成功");
            } else {
                resultMap.put("message", "暂无医生信息");
            }
        } catch (Exception e) {
            logger.error("TestHisSecondIface获取所有医生信息异常：", e);
            resultMap.put("message", "操作失败");
        }
        return resultMap;
    }

    //    获取医生详情
    @Override
    public Map<String, Object> getDoctorDetail(Map map) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("statusCode", Constants.RESULT_MESSAGE_SUCCESS);
        try {
            DoctorVO vo = testHisService.getDoctorDetail4Other(map);
            if (vo != null) {
                if (StringUtils.isNotBlank(vo.getDoctorPicture()))
                    vo.setDoctorPictBase64(ImageChangeUtil.getImageStr(HIS_PATH + File.separator + vo.getDoctorPicture()));
                resultMap.put("data", vo);
                resultMap.put("message", "查询成功");
            } else {
                resultMap.put("message", "暂无医生详情");
            }
        } catch (Exception e) {
            logger.error("TestHisSecondIface获取医生详情异常：", e);
            resultMap.put("message", "操作失败");
            resultMap.put("statusCode", Constants.RESULT_MESSAGE_ERROR);
        }
        return resultMap;
    }

    //    获取医生对应排班表
    @Override
    public Map<String, Object> getDoctorSchedule(Map map) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("statusCode", Constants.RESULT_MESSAGE_SUCCESS);
        try {
            List<DoctorScheduleVO> voList = testHisService.getDoctorSchedule4Other(map);
            if (voList != null && voList.size() > 0) {
                for (int i = 0; i < voList.size(); i++) {
                    voList.get(i).setRemainAmount(voList.get(i).getRegistrationlimits()-voList.get(i).getAppointAmount());
                    voList.get(i).setClinicLabelFlag(Constants.HIS_CLINIC_FLAG.get(voList.get(i).getClinicLabelFlag()));
                }
                Page<DoctorScheduleVO> page = (Page<DoctorScheduleVO>) map.get(Constants.PAGE);
                resultMap.put("total", page.getCount());//总数
                resultMap.put("data", voList);
                resultMap.put("message", "查询成功");
            } else {
                resultMap.put("message", "暂无医生对应排班表");
            }
        } catch (Exception e) {
            logger.error("TestHisSecondIface获取医生对应排班表异常：", e);
            resultMap.put("message", "操作失败");
            resultMap.put("statusCode", Constants.RESULT_MESSAGE_ERROR);
        }
        return resultMap;
    }

    //模拟预约锁号
    public final static byte[] lockz_ = new byte[0];
    //    预约
    @Override
    public Map<String, Object> clinicAppoints(Map map) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("statusCode", Constants.RESULT_MESSAGE_ERROR);
        try {
            // 模拟医院预约动作
            String orderId = null;//订单号
            List<DoctorScheduleVO> voList = testHisService.getDoctorSchedule4Other(map);
            if (voList != null && voList.size() > 0) {
                if (voList.size() == 1) {
                    if ("1".equals(voList.get(0).getClinicLabelFlag())) {
                        synchronized (lockz_) {
                            if (voList.get(0).getRegistrationlimits() > 0 && (voList.get(0).getRegistrationlimits() - voList.get(0).getAppointAmount()) >= 1) {
                                if ((voList.get(0).getRegistrationlimits() - voList.get(0).getAppointAmount()) == 1)
                                    map.put("clinicLabelFlag", 2);
                                map.put("appointAmount", voList.get(0).getAppointAmount() + 1);
                                testHisService.updateAppoint4Other(map);
                                //模拟预约成功
                                orderId = UID.dateTimes();
                                resultMap.put("statusCode", Constants.RESULT_MESSAGE_SUCCESS);
                                resultMap.put("message", "预约成功");
                                resultMap.put("orderId", orderId);
                            } else {
                                resultMap.put("statusCode", Constants.RESULT_MESSAGE_ERROR);
                                resultMap.put("message", "号源已满，请重新选择");
                            }
                        }
                    } else {
                        resultMap.put("statusCode", Constants.RESULT_MESSAGE_ERROR);
                        resultMap.put("message", "号源信息状态:" + Constants.picture_path.get(voList.get(0).getClinicLabelFlag()) + "，请重新选择");
                    }
                } else {
                    resultMap.put("message", "该信息对应多个号源信息，请重新选择");
                }
            } else {
                resultMap.put("message", "该信息无对应号源信息，请重新选择");
            }
        } catch (Exception e) {
            logger.error("TestHisSecondIface预约异常：", e);
            resultMap.put("message", "预约失败");
        }
        return resultMap;
    }

    //    取消挂号订单
    @Override
    public Map<String, Object> cancelHisOrder(Map map) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("statusCode", Constants.RESULT_MESSAGE_ERROR);
        //模拟取消成功
        try {
            HisOrderDetailVO vo = testHisService.getHisOrderStatus4Cssp(map);
            if (vo != null) {
                String orderStatus = vo.getOrderStatus();
                if (!Constants.HIS_ORDER_STATUS_1.equals(orderStatus) || !Constants.HIS_ORDER_STATUS_4.equals(orderStatus)) {
//                    过期且未就诊订单
                    if (StringUtils.isNotBlank(vo.getClinicDate()) && CommUtil.dayDiff(vo.getClinicDate(), CommUtil.getNowDateLongStr("yyyy-MM-dd"), "yyyy-MM-dd") < 0) {
                        resultMap.put("message", "该订单已过就诊时间，不可取消");

                    } else {
                        resultMap.put("statusCode", Constants.RESULT_MESSAGE_SUCCESS);
                        resultMap.put("message", "取消成功");
                    }
                } else {
                    resultMap.put("message", "该订单不可取消");
                }
            } else {
                resultMap.put("message", "暂无该订单号记录");
            }
        } catch (Exception e) {
            logger.error("TestHisFirstIface取消挂号订单异常：", e);
            resultMap.put("message", "操作失败");
        }
        return resultMap;
    }

    //获取单条订单记录状态
    @Override
    public Map<String, Object> getHisOrderStatus(Map map) {
        Map<String, Object> resultMap = new HashMap<String, Object>();
        resultMap.put("statusCode", Constants.RESULT_MESSAGE_SUCCESS);
        try {
            HisOrderDetailVO vo = testHisService.getHisOrderStatus4Cssp(map);
            if (vo != null) {
                //模拟医院状态，并将医院状态转为对应HIS_ORDER_STATUS
                // （正常情况医院状态是不会存为预约失败等，这里是用测试数据库模拟，对接医院接口可直接将医院状态（医院提供）转为对应编码 如预约成功-HIS_ORDER_STATUS_1）
                String orderStatus=vo.getOrderStatus();
                if(Constants.HIS_ORDER_STATUS_2.equals(orderStatus)) orderStatus=Constants.HIS_ORDER_STATUS_0;
                else if(Constants.HIS_ORDER_STATUS_4.equals(orderStatus)) orderStatus=Constants.HIS_ORDER_STATUS_1;
                else if(Constants.HIS_ORDER_STATUS_6.equals(orderStatus)) orderStatus=Constants.HIS_ORDER_STATUS_1;
                else if(Constants.HIS_ORDER_STATUS_8.equals(orderStatus)) orderStatus=Constants.HIS_ORDER_STATUS_5;
                else if(Constants.HIS_ORDER_STATUS_10.equals(orderStatus)) orderStatus=Constants.HIS_ORDER_STATUS_1;
                //过期且未就诊订单编码11
                if(Constants.HIS_ORDER_STATUS_1.equals(orderStatus)){
                    if(StringUtils.isNotBlank(vo.getClinicDate())&&CommUtil.dayDiff(vo.getClinicDate(),CommUtil.getNowDateLongStr("yyyy-MM-dd"),"yyyy-MM-dd")<0){
                        orderStatus=Constants.HIS_ORDER_STATUS_11;
                    }
                }
                //结束
                resultMap.put("orderStatus", orderStatus);
                resultMap.put("message", "查询成功");
            } else {
                resultMap.put("message", "暂无该订单号记录");
            }
        } catch (Exception e) {
            logger.error("TestHisFirstIface获取单条订单记录异常：", e);
            resultMap.put("message", "操作失败");
            resultMap.put("statusCode", Constants.RESULT_MESSAGE_ERROR);

        }
        return resultMap;
    }
}
