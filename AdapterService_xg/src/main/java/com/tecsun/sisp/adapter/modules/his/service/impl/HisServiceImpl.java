package com.tecsun.sisp.adapter.modules.his.service.impl;

 import com.tecsun.sisp.adapter.modules.common.service.BaseService;
 import com.tecsun.sisp.adapter.modules.his.entity.request.AppointBean;
 import com.tecsun.sisp.adapter.modules.his.entity.request.AppointOrderBean;
 import com.tecsun.sisp.adapter.modules.his.entity.request.CancelOrderBean;
 import com.tecsun.sisp.adapter.modules.his.entity.request.HisBean;
 import com.tecsun.sisp.adapter.modules.his.entity.response.AllHisInfoVO;
 import com.tecsun.sisp.adapter.modules.his.entity.response.HisOrderDetailVO;
 import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

 import java.util.HashMap;
 import java.util.List;
import java.util.Map;

/**医院
 * Created by danmeng on 2017/07/11.
 */

@Service("HisService")
public class HisServiceImpl extends BaseService {
    public static final Logger logger = Logger.getLogger(HisServiceImpl.class);

    public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.his.service.impl.HisServiceImpl.";


    public List<AllHisInfoVO> getAllHisInfo4Cssp(HisBean bean) {
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getAllHisInfo",bean);
    }
    public int insertHisBusRecord4Cssp(AppointBean bean) throws Exception {
        return this.getSqlSessionTemplate().update(NAMESPACE + "insertHisBusRecord",bean);
    }
    public int updateHisBusRecord4Cssp(Map map) throws Exception {
        return this.getSqlSessionTemplate().update(NAMESPACE + "updateHisBusRecord",map);
    }
    public int insertHisAppointInfo4Cssp(AppointOrderBean bean) throws Exception {
        return this.getSqlSessionTemplate().update(NAMESPACE + "insertHisAppointInfo",bean);
    }
    public int insertHisCancelOrder4Cssp(CancelOrderBean bean) throws Exception {
        return this.getSqlSessionTemplate().update(NAMESPACE + "insertHisCancelOrder",bean);
    }

    public List<HisOrderDetailVO> getHisBusRecord4Cssp(Map<String, Object> map) {
        //获取测试数据
        return this.getSqlSessionTemplate().selectList(NAMESPACE + "getHisBusRecord", map);
    }

    //预约要求：同一天同一时间段每人只能预约1次；同一医院、同一个科室，每人每天只能预约1次。
    public int canHisAppoint4Cssp(AppointBean bean) throws Exception {
        int status = 0;
        try {
            Map<String,String> map = new HashMap();
            map.put("patientIdcard", bean.getPatientIdcard());
            map.put("patientName", bean.getPatientName());
            map.put("clinicDate", bean.getClinicDate());
            map.put("timeInterval", bean.getTimeInterval());
            map.put("orderStatus", "1");//暂时只考虑预约情况：可供考虑状态orderStatusArray：1,5,6,9,10
            List<HisOrderDetailVO> list = this.getSqlSessionTemplate().selectList(NAMESPACE + "getHisBusRecord", map);
            if (list != null && list.size() > 0) {
                status = -2;//同一天同一时间段每人只能预约1次
            } else {
                map.remove("timeInterval");
                map.put("hospitalId", bean.getHospitalId());
                map.put("deptCode", bean.getDeptCode());
                list = this.getSqlSessionTemplate().selectList(NAMESPACE + "getHisBusRecord", map);
                if (list != null && list.size() > 0) {
                    status = -1;//同一医院、同一个科室，每人每天只能预约1次
                } else status = 1;
            }
        } catch (Exception e) {
            logger.error("查询是否符合预约要求失败：", e);
            status = -999;
        }
        return status;
    }

}
