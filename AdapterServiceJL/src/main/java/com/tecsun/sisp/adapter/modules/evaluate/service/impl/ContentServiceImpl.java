package com.tecsun.sisp.adapter.modules.evaluate.service.impl;

import com.tecsun.sisp.adapter.common.util.UID;
import com.tecsun.sisp.adapter.modules.common.service.BaseService;
import com.tecsun.sisp.adapter.modules.evaluate.entity.request.ContentBean;
import com.tecsun.sisp.adapter.modules.evaluate.entity.request.EvaluateBean;
import com.tecsun.sisp.adapter.modules.evaluate.entity.request.EvaluateDetailBean;
import com.tecsun.sisp.adapter.modules.evaluate.entity.response.ContentVo;
import com.tecsun.sisp.adapter.modules.evaluate.entity.response.EvaluateDetailVo;
import com.tecsun.sisp.adapter.modules.evaluate.entity.response.EvaluateVo;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by xumaohao on 2017/10/18.
 */
@Service("ContentServiceImpl")
public class ContentServiceImpl extends BaseService{
    private static String NAMESPACE = "com.tecsun.sisp.adapter.modules.evaluate.service.impl.ContentServiceImpl.";

    public static final Logger logger = Logger.getLogger(ContentServiceImpl.class);

    /**
     * 获取评价内容
     * @param bean
     * @return
     * @throws Exception
     */
    public List<ContentVo> selectContent4cssp(ContentBean bean) throws Exception {
        List<ContentVo> list = getSqlSessionTemplate().selectList(NAMESPACE + "selectContent",bean);
        //如查询结果不为空
        if (list != null && list.size() > 0) {
            for (int i = 0; i < list.size(); i++) {
                //如果内容选项不为空，将内容选项转为key_value类型
                if(list.get(i).getContentOption() != null) {
                    String[] str = list.get(i).getContentOption().split("\\|");
                    List temp = new LinkedList();
                    for (int j = 0; j < str.length; j++) {
                        ContentVo.change change = new ContentVo.change();
                        change.setKey(str[j].split(":")[0]);
                        change.setValue(str[j].split(":")[1]);
                        temp.add(change);
                    }
                    list.get(i).setContentOptionList(temp);
                }
            }
        }
        return list;
    }

    /**
     * 新增评价结果
     * 1、如完成一次业务 则新增一条数据并返回serviceNumber 表示尚未评价
     * 2、评价时通过上次生成的serviceNumber 更新评价数据
     * @param bean
     * @return
     * @throws Exception
     */
    public String insertEvaluate4cssp(EvaluateBean bean) throws Exception {
        //如果serviceNumber为空 表示新增数据 返回唯一serviceNumber 用于评论
        if(StringUtils.isEmpty(bean.getServiceNumber())) {
            //生成uuid作为唯一
            String serviceNumber = "tecsun"+UID.dateTimes();
            //为防止产生的编码与其他接口产生的编码小概率相同 加上字母作
            bean.setServiceNumber(serviceNumber);
            //新增一条数据 记录完成一次业务但尚未评价
            int count = getSqlSessionTemplate().insert(NAMESPACE + "insertEvaluate", bean);
            if(count > 0) {
                return serviceNumber;
            }
        }else {
            //查询插入用户评价表后返回id
            EvaluateVo evaluateVo = getSqlSessionTemplate().selectOne(NAMESPACE + "selectId", bean);
            int count = 0;
            long evaluateId = 0;
            if(evaluateVo != null) {
                evaluateId = evaluateVo.getEvaluateId();
                if(evaluateId != 0){
                    //更新用户评价表
                    count = getSqlSessionTemplate().update(NAMESPACE + "updateEvaluate", bean);
                }
            } else {
                //新增一条数据 此时的serviceNumber是办理业务产生的唯一标识 非本接口产生
                count = getSqlSessionTemplate().insert(NAMESPACE + "insertEvaluate", bean);
                if(!(count > 0 && bean.getEvaluateId() != 0)) {
                    logger.info("评价结果记录失败===》业务类型:" + bean.getEvaluateService() + "业务id:" + bean.getEvaluatedNumber());
                    return null;
                }else {
                    evaluateId = bean.getEvaluateId();
                }
            }
            //将评价内容插入详细表
            for (int i = 0; i < bean.getData().size(); i++) {
                //取出评价信息
                EvaluateDetailBean detail = new EvaluateDetailBean();
                detail = bean.getData().get(i);
                //赋值对应的评价表id
                detail.setEvaluateId(evaluateId);
                count = getSqlSessionTemplate().insert(NAMESPACE + "insertEvaluateDetail", detail);
            }
        }
        return null;
    }

    /**
     * 查询评价结果
     * @param bean
     * @return
     * @throws Exception
     */
    public EvaluateVo selectEvaluate4cssp(EvaluateBean bean) throws Exception {
        List<EvaluateVo> list = getSqlSessionTemplate().selectList(NAMESPACE + "selectEvaluate",bean);
        //如存在评价记录 则查询评价详情
        if(list != null && list.size() > 0){
            EvaluateVo evaluateVo = new EvaluateVo();
            evaluateVo = list.get(0);//取最新一条记录 实际上 对应的评价记录只存在一条
            List<EvaluateDetailVo> data = getSqlSessionTemplate().selectList(NAMESPACE + "selectEvaluateDetail",evaluateVo);
            evaluateVo.setData(data);
            return evaluateVo;
        }
        return null;
    }
}
