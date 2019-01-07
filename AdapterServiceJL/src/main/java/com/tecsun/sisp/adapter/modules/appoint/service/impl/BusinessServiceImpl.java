package com.tecsun.sisp.adapter.modules.appoint.service.impl;

import com.tecsun.sisp.adapter.common.util.Config;
import com.tecsun.sisp.adapter.common.util.Distance;
import com.tecsun.sisp.adapter.common.util.Sort;
import com.tecsun.sisp.adapter.common.util.UID;
import com.tecsun.sisp.adapter.modules.appoint.entity.request.*;
import com.tecsun.sisp.adapter.modules.appoint.entity.response.*;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by xumaohao on 2017/10/25.
 */
@Service("BusinessServiceImpl")
public class BusinessServiceImpl extends BaseService {
    private static String NAMESPACE1 = "com.tecsun.sisp.adapter.modules.appoint.service.impl.BusinessServiceImpl.";

    //预约成功是否允许重复预约
    private String APPOINT_AGAIN = Config.getInstance().get("appoint.again");
    /**
     * 获取办理业务
     * @param bean
     * @return
     * @throws Exception
     */
    public List<ServiceVo> getService4cssp(ServiceBean bean) throws Exception{
        List<ServiceVo> serviceVos = getSqlSessionTemplate().selectList(NAMESPACE1 + "getService", bean);
        if (serviceVos != null && serviceVos.size() > 0) {
            for (int i = 0; i < serviceVos.size(); i++) {
                bean.setParentCode(serviceVos.get(i).getServiceCode());
                List<ServiceVo> list = getService4cssp(bean);
                serviceVos.get(i).setData(list);
            }
        }
        return serviceVos;
    }

    /**
     * 获取办事网点
     * @param bean
     * @return
     * @throws Exception
     */
    public List<DistrictVo> getDistrict4cssp(DistrictBean bean) throws Exception{
        List<DistrictVo> districtVos = getSqlSessionTemplate().selectList(NAMESPACE1 + "getDistrict", bean);
        if (districtVos != null && districtVos.size() > 0) {
            for (int i = 0; i < districtVos.size(); i++) {
                DistrictBean dis = new DistrictBean();
                dis.setParentCode(districtVos.get(i).getDistrictCode());
                List<DistrictVo> list = getDistrict4cssp(dis);
                districtVos.get(i).setData(list);
            }
        }
        return districtVos;
    }

    /**
     * 获取办事网点
     * @param bean
     * @return
     * @throws Exception
     */
    public List<BranchVo> getBranch4cssp(BranchBean bean) throws Exception{
        List<BranchVo> branchVos = getSqlSessionTemplate().selectList(NAMESPACE1 + "getBranch", bean);
        if (branchVos != null && branchVos.size() > 0) {
            String destination = null;
            for (int i = 0; i < branchVos.size(); i++) {
                destination = Distance.getCoord(branchVos.get(i).getBranchAddress());
                branchVos.get(i).setLatitude(destination.split(",")[1]);
                branchVos.get(i).setLongitude(destination.split(",")[0]);
            }
            if (bean.getLocation() != null && bean.getLocation() != "") {
                String origin = Distance.getCoord(bean.getLocation());
                for (int i = 0; i < branchVos.size(); i++) {
                    destination = Distance.getCoord(branchVos.get(i).getBranchAddress());
                    branchVos.get(i).setDistanceTemp(Distance.getDistanceByLocal(origin, destination));
                }
                //根据距离排序
                Sort.sort(branchVos, "distanceTemp", true);
                //增加单位
                for (int i = 0; i < branchVos.size(); i++) {
                    DecimalFormat df = new DecimalFormat("#.0");
                    String distance = branchVos.get(i).getDistanceTemp() < 1000 ?
                            (String.valueOf(branchVos.get(i).getDistanceTemp()).split("\\.")[0] + "m")
                            : String.valueOf(df.format(branchVos.get(i).getDistanceTemp() / 1000) + "km");
                    branchVos.get(i).setDistance(distance);
                }
            }
        }
        return branchVos;
    }

    /**
     * 获取预约时间
     * @param bean
     * @return
     * @throws Exception
     */
    public List<TimeVo> getTime4cssp(TimeBean bean) throws Exception {
        List<TimeTempVo> timeTempVos = getSqlSessionTemplate().selectList(NAMESPACE1 + "getTime", bean);
        List<TimeVo> timeVos = new LinkedList<>();
        if (timeTempVos != null && timeTempVos.size() > 0) {
            //如预约时间不为空 则返回一个时间段
            if (!(bean.getAppointTime() == null || bean.getAppointTime() == "")) {
                List<TimeVo.Detail> list = new LinkedList<>();
                TimeVo time = new TimeVo();
                time.setAppointTime(bean.getAppointTime());
                time.setServiceCode(bean.getServiceCode());
                TimeVo.Detail detail = new TimeVo.Detail();
                for (int i = 0; i < timeTempVos.size(); i++) {
                    detail.setTimeInterval(timeTempVos.get(i).getTimeInterval());
                    detail.setAppointAmountLimit(timeTempVos.get(i).getAppointAmountLimit());
                    detail.setAppointAmount(timeTempVos.get(i).getAppointAmount());
                    detail.setAppointStatus(timeTempVos.get(i).getAppointStatus());
                    list.add(detail);
                }
                time.setDetails(list);
                timeVos.add(time);
            }
            //预约时间为空 返回七天预约时间可选
            else {
                //获取当前时间
                Date dt = new Date();
                for (int i = 1; i < 8; i++) {
                    TimeVo time = new TimeVo();
                    //循环遍历区分一周的时间段
                    Date date = getOtherDay(dt,i);
                    //将时间格式化
                    SimpleDateFormat matter=new SimpleDateFormat("yyyy-MM-dd");
                    String appointTime = matter.format(date);
                    //提取出预约时间 业务编码
                    time.setAppointTime(appointTime);
                    time.setServiceCode(bean.getServiceCode());
                    List<TimeVo.Detail> list = new LinkedList<>();
                    for (int j = 0; j < timeTempVos.size(); j++) {
                        if (appointTime.equals(timeTempVos.get(j).getAppointTime())) {
                            //取出同一天的时间段
                            TimeVo.Detail detail = new TimeVo.Detail();
                            detail.setTimeInterval(timeTempVos.get(j).getTimeInterval());
                            detail.setAppointAmountLimit(timeTempVos.get(j).getAppointAmountLimit());
                            detail.setAppointAmount(timeTempVos.get(j).getAppointAmount());
                            detail.setAppointStatus(timeTempVos.get(j).getAppointStatus());
                            list.add(detail);
                        }
                    }
                    time.setDetails(list);
                    timeVos.add(time);
                }
            }
        }
        return timeVos;
    }

    /**
     * 预约
     * @param bean
     * @return
     * @throws Exception
     */
    public Map<String,Object> insertRecord4cssp(RecordBean bean) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("message","当前系统预约人数过多，请重新选择");
        //检查配置文件是否允许重复预约
        if ("1".equals(APPOINT_AGAIN)) {
            //检查是否已经预约过该业务
            List<RecordVo> recordVo = getSqlSessionTemplate().selectList(NAMESPACE1 + "getRecord", bean);
            if (recordVo != null && recordVo.size() > 0) {
                map.put("message", "您已预约该业务，请勿重复预约");
                map.put("statusCode", "-200");
                return map;
            }
        }
        //组装查询预约时间信息入参
        TimeBean timeBean = new TimeBean();
        timeBean.setServiceCode(bean.getServiceCode());   //业务编码
        timeBean.setAppointTime(bean.getAppointTime());   //预约时间
        timeBean.setTimeInterval(bean.getTimeInterval()); //预约时间段
        List<TimeVo> timeVos = getTime4cssp(timeBean);
        //判断是否存在此时间
        if (!(timeVos != null && timeVos.size() > 0)) {
            map.put("message", "抱歉，无对应时间段可预约");
            map.put("statusCode", "-200");
            return map;
        }
        TimeVo timeVo = timeVos.get(0);//获取第一条数据 实际上也只存在一条数据
        //预约已满 状态为不可预约 =1
        if ("1".equals(timeVo.getDetails().get(0).getAppointStatus())) {
            map.put("message", "该时间段预约已满，请选择其他时间段");
            map.put("statusCode", "-200");
            return map;
        }
        //获取uuid作为唯一预约号
        String appointNum = UID.dateTimes();
        bean.setAppointNum(appointNum);
        int record = getSqlSessionTemplate().insert(NAMESPACE1 + "insertRecord", bean);
        if(record > 0){
            //如完成此预约后预约量已满 则修改预约状态
            if(timeVo.getDetails().get(0).getAppointAmount() + 1 == timeVo.getDetails().get(0).getAppointAmountLimit()){
                timeBean.setAppointStatus("1");
            }
            //更新该时间段的预约量
            getSqlSessionTemplate().update(NAMESPACE1 + "updateTime", timeBean);

            map.put("message","恭喜您，预约成功！");
            map.put("appointNum", appointNum);
            map.put("statusCode", "200");
        }
        return map;
    }

    //获取某个时间的前后时间
    public static Date getOtherDay(Date date,int i) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, + i);//今天的时间+i天
        date = calendar.getTime();
        return date;
    }


}
