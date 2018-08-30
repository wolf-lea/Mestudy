package com.tecsun.sisp.adapter.modules.train.service.impl;

import com.tecsun.sisp.adapter.common.datasource.CustomerContextHolder;
import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.train.entity.request.ApplyBean;
import com.tecsun.sisp.adapter.modules.train.entity.response.TrainApplyBean;
import com.tecsun.sisp.adapter.modules.train.entity.response.TrainDetailBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xumaohao on 2017/8/30.
 */
@Service("ApplyServiceImpl")
public class ApplyServiceImpl extends BaseService {

    private static Logger logger = (Logger) LoggerFactory.getLogger(ApplyServiceImpl.class);

    public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.train.service.impl.ApplyServiceImpl.";

    @Autowired
    TestTrainServiceImpl testTrainService;

    /**
     * 新增培训报名记录
     * @param bean
     * @return
     */
    public boolean addApply4cssp(ApplyBean bean) throws Exception {
        //获取培训信息
        //数据源切换
        CustomerContextHolder.setCustomerType(CustomerContextHolder.OTHER_DATASOURCE);
        TrainDetailBean obj = testTrainService.selectMessageDetail4Other(bean);

        //新增培训信息到数据库
        if(obj != null){
            //数据源切换
            bean = setValue(obj,bean);
            CustomerContextHolder.setCustomerType(CustomerContextHolder.CSSP_DATASOURCE);
            int count = this.getSqlSessionTemplate().insert(NAMESPACE + "addApply", bean);

            if (count > 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 查询报名记录(分页)
     * @param bean
     * @return
     * @throws Exception
     */
    public Page<TrainApplyBean> seleceApply4cssp(ApplyBean bean) throws Exception {
        Page<TrainApplyBean> page = new Page<>(bean.getPageno(), bean.getPagesize());
        bean.setPage(page);

        List<TrainApplyBean> apply = this.getSqlSessionTemplate().selectList(NAMESPACE + "selectApply", bean);

        if (apply.size() > 0) {
            page.setData(apply);
        }
        return page;
    }

    /**
     * 查询报名记录(不分页)
     * @param bean
     * @return
     * @throws Exception
     */
    public List<TrainApplyBean> seleceAllApply4cssp(ApplyBean bean) throws Exception {
        List<TrainApplyBean> apply = this.getSqlSessionTemplate().selectList(NAMESPACE + "selectApply", bean);
        return apply;
    }

    /**
     * 判断是否存在报名记录
     * @param bean
     * @return
     * @throws Exception
     */
    public boolean isApply4cssp(ApplyBean bean) throws Exception {
        int count = this.getSqlSessionTemplate().selectOne(NAMESPACE + "isApply", bean);
        if(count > 0){
            return true;
        }
        return false;
    }


    //报名记录入参组装
    public static ApplyBean setValue(TrainDetailBean obj ,ApplyBean bean) {
        bean.setProfession(obj.getProfession());
        bean.setGrade(obj.getGrade());

        //获取日期，拼装格式
        String start = obj.getClassTime().substring(8,10)+"-"+
                obj.getClassTime().substring(5,7)+"-"+
                obj.getClassTime().substring(0,4);
        bean.setStartTime(start);

        String end = obj.getClassTime().substring(21,23)+"-"+
                obj.getClassTime().substring(18,20)+"-"+
                obj.getClassTime().substring(13,17);
        bean.setEndTime(end);
        bean.setPeriods(obj.getPeriods());
        bean.setOrganName(obj.getOrganName());
        return bean;
    }
}
