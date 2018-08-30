package com.tecsun.sisp.adapter.modules.his.hisUtils;

import java.util.Map;

/**
 * Created by danmeng on 2017/7/12.
 */

public interface HisIface {
    public Map<String, Object> getHospitalDetail(Map map);//获取医院详情

    public Map<String, Object> getDeptment(Map map);//获取科室信息

    public Map<String, Object> getDeptmentDetail(Map map);//获取科室详情

    public Map<String, Object> getDoctor(Map map);//获取所有医生

    public Map<String, Object> getDoctorDetail(Map map);//获取医生详情

    public Map<String, Object> getDoctorSchedule(Map map);//获取医生对应排班表

    public Map<String, Object> clinicAppoints(Map map);//预约

    public Map<String, Object> cancelHisOrder(Map map);//取消挂号订单

    public Map<String, Object> getHisOrderStatus(Map map);//获取单条订单记录状态
}
