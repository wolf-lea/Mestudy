package com.tecsun.sisp.adapter.modules.train.service.impl;

import com.tecsun.sisp.adapter.common.util.Page;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.train.entity.request.ApplyBean;
import com.tecsun.sisp.adapter.modules.train.entity.request.MessageSelectBean;
import com.tecsun.sisp.adapter.modules.train.entity.response.TrainApplyBean;
import com.tecsun.sisp.adapter.modules.train.entity.response.TrainDetailBean;
import com.tecsun.sisp.adapter.modules.train.entity.response.TrainMessageBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xumaohao on 2017/8/29.
 */
@Service("TestTrainServiceImpl")
public class TestTrainServiceImpl extends BaseService {

    private static Logger logger = LoggerFactory.getLogger(TestTrainServiceImpl.class);

    public final static String NAMESPACE = "com.tecsun.sisp.adapter.modules.train.service.impl.TestTrainServiceImpl.";

    @Autowired
    ApplyServiceImpl applyService;

    /**
     * 获取培训信息列表
     * @param bean
     * @return
     */
    public Page<TrainMessageBean> selectMessageList4Other(MessageSelectBean bean) throws Exception{
        Page<TrainMessageBean> page =  new Page<>(bean.getPageno(),bean.getPagesize());
        bean.setPage(page);
        //获取培训数据
        List<TrainMessageBean> trainMessage = this.getSqlSessionTemplate().selectList(NAMESPACE + "selectMessageList", bean);
        if(trainMessage != null && trainMessage.size() > 0){
            page.setData(trainMessage);
        }
        //判断身份证姓名是否为空
        if(!(bean.getSfzh()==null ||bean.getSfzh()=="")  && !(bean.getXm()==null|| bean.getXm()=="")){
            //判断是否已经报名
            ApplyBean applyBean = new ApplyBean();
            applyBean.setXm(bean.getXm());
            applyBean.setSfzh(bean.getSfzh());
            List<TrainApplyBean> apply = applyService.seleceAllApply4cssp(applyBean);
            if(apply.size()>0 && trainMessage.size() > 0) {
                for (int i = 0; i < trainMessage.size(); i++) {
                    for (int j = 0; j < apply.size(); j++) {
                        if (page.getData().get(i).getTrainId() == apply.get(j).getTrainId()) {
                            page.getData().get(i).setIsApply("已报名");
                        }
                    }
                }
            }
        }

        return page;
    }

    /**
     * 获取培训信息详情
     * @param bean
     * @return
     * @throws Exception
     */
    public TrainDetailBean selectMessageDetail4Other(ApplyBean bean) throws Exception{
        //获取培训详情（含课程介绍）
        TrainDetailBean trainDetailBean = this.getSqlSessionTemplate().selectOne(NAMESPACE + "selectCourseSchool", bean.getTrainId());

        //判断身份证姓名是否为空
        if(!bean.getSfzh().isEmpty() && !bean.getXm().isEmpty() ) {
            //判断是否已经报名
            List<TrainApplyBean> apply = applyService.seleceAllApply4cssp(bean);
            if (apply.size() > 0 && trainDetailBean != null) {
                for (int i = 0; i < apply.size(); i++) {
                    if (apply.get(i).getTrainId() == trainDetailBean.getTrainId()) {
                        trainDetailBean.setIsApply("已报名");
                    }
                }
            }
        }
        return trainDetailBean;
    }
}
