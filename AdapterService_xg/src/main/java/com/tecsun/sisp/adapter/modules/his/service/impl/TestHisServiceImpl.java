package com.tecsun.sisp.adapter.modules.his.service.impl;

 import com.tecsun.sisp.adapter.modules.common.service.BaseService;
 import com.tecsun.sisp.adapter.modules.his.entity.response.*;
 import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**模拟医院获取测试数据
 * Created by danmeng on 2017/07/11.
 */

@Service("TestHisService")
public class TestHisServiceImpl extends BaseService {
    public static final Logger logger = Logger.getLogger(TestHisServiceImpl.class);

    public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.his.service.impl.TestHisServiceImpl.";


    public HisDetailVO getHospitalDetail4Other(Map map) {
        //获取测试数据
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getHospitalDetail", map);
    }

    public  List<DeptmentVO>  getDeptment4Other(Map map) {
        //获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getDeptment", map);
    }

    public DeptmentVO getDeptmentDetail4Other(Map map) {
        //获取测试数据
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getDeptmentDetail", map);
    }
    public List<DoctorVO> getDoctor4Other(Map map) {
        //获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getDoctor", map);
    }

    public DoctorVO getDoctorDetail4Other(Map map) {
        //获取测试数据
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getDoctorDetail", map);
    }

    public List<DoctorScheduleVO> getDoctorSchedule4Other(Map map) {
        //获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getDoctorSchedule", map);
    }

    public int updateAppoint4Other(Map map) throws Exception {
        return this.getSqlSessionTemplate().update(NAMESPACE + "updateAppoint",map);
    }
//    获取单条订单记录
    public HisOrderDetailVO getHisOrderStatus4Cssp(Map map) throws Exception {
        return this.getSqlSessionTemplate().selectOne(NAMESPACE + "getHisOrderStatus", map);
    }
}
